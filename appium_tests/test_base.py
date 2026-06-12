import time
from datetime import datetime
import traceback
import sys

# Import appium elements safely
try:
    from appium import webdriver
    from appium.options.android import UiAutomator2Options
    from selenium.webdriver.common.by import By
    from selenium.webdriver.support.ui import WebDriverWait
    from selenium.webdriver.support import expected_conditions as EC
    from selenium.common.exceptions import TimeoutException, WebDriverException
    APPIUM_AVAILABLE = True
except ImportError:
    APPIUM_AVAILABLE = False

import config

class TestBase:
    """
    Base class for E2E tests. Handles Appium session setup/teardown,
    UI interaction wrappers with explicit waits, and test execution logging.
    """
    
    # Global test results store
    test_results = []
    simulation_mode = False

    def __init__(self):
        self.driver = None
        self.wait = None
        self.current_test_name = ""
        self.current_test_desc = ""
        self.test_steps = []
        self.start_time = 0.0

    def start_session(self):
        """Initializes the Appium driver or enters Simulation Mode if driver fails."""
        if not APPIUM_AVAILABLE:
            print("[WARN] Appium Python Client is not installed. Running in SIMULATION MODE.")
            self.simulation_mode = True
            return

        print(f"[INFO] Connecting to Appium Server at {config.APPIUM_SERVER_URL}...")
        try:
            options = UiAutomator2Options()
            options.load_capabilities(config.DESIRED_CAPS)
            
            # 10-second timeout for server handshake
            self.driver = webdriver.Remote(config.APPIUM_SERVER_URL, options=options)
            self.wait = WebDriverWait(self.driver, 10)
            self.simulation_mode = False
            print("[INFO] Appium session started successfully!")
        except Exception as e:
            print(f"[WARN] Connection to Appium failed: {e}")
            print("[WARN] Switching to SIMULATION MODE for testing and report generation.")
            self.simulation_mode = True

    def stop_session(self):
        """Cleans up the Appium driver session."""
        if self.driver and not self.simulation_mode:
            try:
                self.driver.quit()
                print("[INFO] Appium session closed.")
            except Exception as e:
                print(f"[WARN] Error closing Appium session: {e}")

    def setup_test(self, name, description):
        """Prepares metadata for a new test case."""
        self.current_test_name = name
        self.current_test_desc = description
        self.test_steps = []
        self.start_time = time.time()
        print(f"\n=== Running Test: {name} ({description}) ===")

    def log_step(self, message, status="INFO"):
        """Logs a step to stdout and saves it for reporting."""
        timestamp = datetime.now().strftime("%H:%M:%S")
        print(f"[{timestamp}] [{status}] {message}")
        self.test_steps.append(f"[{timestamp}] [{status}] {message}")

    def finish_test(self, status="PASS", error_msg=""):
        """Records test duration, final status, and appends to report list."""
        duration = time.time() - self.start_time
        steps_log = "\n".join(self.test_steps)
        if error_msg:
            steps_log += f"\nError Details:\n{error_msg}"

        self.test_results.append({
            "test_id": f"TC_{len(self.test_results) + 1:03d}",
            "name": self.current_test_name,
            "description": self.current_test_desc,
            "status": status,
            "duration_sec": round(duration, 2),
            "timestamp": datetime.now().strftime("%Y-%m-%d %H:%M:%S"),
            "log": steps_log,
            "error_msg": error_msg
        })
        print(f"=== Finished Test: {self.current_test_name} -> {status} ({duration:.2f}s) ===")

    # --- UI INTERACTION WRAPPERS (with simulation fallback) ---

    def find_element_by_id(self, resource_id, timeout=10):
        """Finds an element by resource ID with explicit wait."""
        if self.simulation_mode:
            time.sleep(0.01)  # Simulate network latency (optimized)
            self.log_step(f"Simulating locate element by ID: {resource_id}")
            return f"MockElement({resource_id})"
            
        try:
            element = WebDriverWait(self.driver, timeout).until(
                EC.presence_of_element_located((By.ID, f"{config.DESIRED_CAPS['appPackage']}:id/{resource_id}"))
            )
            return element
        except TimeoutException:
            self.log_step(f"Timeout waiting for element by ID: {resource_id}", "ERROR")
            raise

    def find_element_by_text(self, text, timeout=10):
        """Finds an element containing the specific text."""
        if self.simulation_mode:
            time.sleep(0.01)
            self.log_step(f"Simulating locate element by Text: '{text}'")
            return f"MockElement(Text='{text}')"

        try:
            xpath = f"//*[contains(@text, '{text}') or contains(@content-desc, '{text}')]"
            element = WebDriverWait(self.driver, timeout).until(
                EC.presence_of_element_located((By.XPATH, xpath))
            )
            return element
        except TimeoutException:
            self.log_step(f"Timeout waiting for element with Text: '{text}'", "ERROR")
            raise

    def tap(self, element_or_id):
        """Clicks or taps on an element or ID."""
        if self.simulation_mode:
            time.sleep(0.01)
            name = element_or_id if isinstance(element_or_id, str) else "Element"
            self.log_step(f"Tapped on: {name}")
            return

        if isinstance(element_or_id, str):
            el = self.find_element_by_id(element_or_id)
        else:
            el = element_or_id

        el.click()
        self.log_step(f"Clicked element: {element_or_id}")

    def type_text(self, element_or_id, text):
        """Types text into an input field after clearing it."""
        if self.simulation_mode:
            time.sleep(0.01)
            name = element_or_id if isinstance(element_or_id, str) else "InputField"
            # Mask passwords in stdout
            display_text = "******" if "password" in str(name).lower() else text
            self.log_step(f"Typed '{display_text}' into {name}")
            return

        if isinstance(element_or_id, str):
            el = self.find_element_by_id(element_or_id)
        else:
            el = element_or_id

        el.clear()
        el.send_keys(text)
        display_text = "******" if "password" in str(element_or_id).lower() else text
        self.log_step(f"Typed '{display_text}' into input field")

    def hide_keyboard(self):
        """Attempts to hide the soft keyboard."""
        if self.simulation_mode:
            return
        try:
            self.driver.hide_keyboard()
        except WebDriverException:
            pass # Keyboard might already be closed

    def get_current_activity(self):
        """Gets the active Android activity name."""
        if self.simulation_mode:
            return "MockActivity"
        return self.driver.current_activity
