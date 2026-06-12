import os
import sys
import subprocess
import time

def install_and_import_dependencies():
    """Checks and auto-installs missing Python packages."""
    required_packages = {
        "appium": "Appium-Python-Client",
        "openpyxl": "openpyxl"
    }
    
    missing_packages = []
    for module_name, package_name in required_packages.items():
        try:
            __import__(module_name)
        except ImportError:
            missing_packages.append(package_name)
            
    if missing_packages:
        print(f"[INFO] Installing missing Python dependencies: {missing_packages}...")
        try:
            # Install packages using current Python environment pip
            subprocess.check_call([sys.executable, "-m", "pip", "install"] + missing_packages)
            print("[SUCCESS] Dependencies installed successfully!")
        except Exception as e:
            print(f"[ERROR] Failed to install dependencies via pip: {e}")
            print("[WARN] Proceeding with standard library fallbacks.")

# Perform dependencies check before running imports
install_and_import_dependencies()

# Now import test suite and report generator
from test_suite import SmartSafetyTravelTestSuite
import report_generator

def check_appium_server():
    """Checks if the Appium server is responsive at the configured address."""
    import config
    # Clean host/port extraction
    url = config.APPIUM_SERVER_URL.replace("http://", "").replace("https://", "")
    host = url.split(":")[0]
    port = int(url.split(":")[1].split("/")[0])
    
    import socket
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.settimeout(2)
    try:
        s.connect((host, port))
        s.close()
        return True
    except (socket.timeout, ConnectionRefusedError):
        return False

def print_ascii_banner():
    print("=" * 70)
    print("   SMARTSAFETYTRAVEL - APPIUM E2E TEST RUNNER")
    print("=" * 70)

def main():
    print_ascii_banner()
    
    # Check server availability
    server_running = check_appium_server()
    if server_running:
        print("[INFO] Active Appium Server detected! Preparing to run tests on target device.")
    else:
        print("[WARN] Appium Server is not running on the default port.")
        print("[INFO] Runner will launch in SIMULATION MODE to verify test logic and report generation.")
        print("[INFO] (To run real tests: Start Appium server, connect your device/emulator, and run again)")
        print("-" * 70)
        time.sleep(1)

    # Initialize the test suite
    suite = SmartSafetyTravelTestSuite()
    
    # Trigger all E2E tests
    suite.run_all_tests()
    
    # Collect results
    results = suite.test_results
    
    # Output file path
    report_filename = "test_report.xlsx"
    
    # Generate reports
    print("\n" + "=" * 70)
    print("   GENERATING ANALYSIS REPORT")
    print("=" * 70)
    report_generator.generate_excel_report(results, report_filename)
    
    # Render final console summary table
    print("\n" + "=" * 70)
    print("   TEST RUN SUMMARY")
    print("=" * 70)
    print(f"{'Test ID':<10} | {'Test Name':<30} | {'Status':<8} | {'Duration':<10}")
    print("-" * 70)
    
    passed_count = 0
    failed_count = 0
    
    for r in results:
        status_symbol = "PASS" if r["status"] == "PASS" else "FAIL"
        if r["status"] == "PASS":
            passed_count += 1
        else:
            failed_count += 1
            
        print(f"{r['test_id']:<10} | {r['name'][:30]:<30} | {status_symbol:<8} | {r['duration_sec']:.2f}s")
        
    print("-" * 70)
    total = len(results)
    success_rate = (passed_count / total * 100) if total > 0 else 0.0
    print(f"Total Run: {total} | Passed: {passed_count} | Failed: {failed_count} | Success Rate: {success_rate:.1f}%")
    print("=" * 70)
    
    if failed_count > 0:
        sys.exit(1)
    else:
        sys.exit(0)

if __name__ == "__main__":
    main()
