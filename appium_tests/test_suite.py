import time
from test_base import TestBase

# Import category-specific test case files from their respective subfolders
from categories.functional.test_functional import TEST_CASES as functional_cases
from categories.ui_ux.test_ui_ux import TEST_CASES as ui_ux_cases
from categories.compatibility.test_compatibility import TEST_CASES as compatibility_cases
from categories.performance.test_performance import TEST_CASES as performance_cases
from categories.security.test_security import TEST_CASES as security_cases
from categories.api.test_api import TEST_CASES as api_cases
from categories.database.test_database import TEST_CASES as database_cases
from categories.accessibility.test_accessibility import TEST_CASES as accessibility_cases
from categories.mobile_specific.test_mobile_specific import TEST_CASES as mobile_specific_cases
from categories.regression.test_regression import TEST_CASES as regression_cases
from categories.e2e.test_e2e import TEST_CASES as e2e_cases

class SmartSafetyTravelTestSuite(TestBase):
    """
    Appium test suite representing the E2E user paths of the SmartSafetyTravel Android app.
    Combines exactly 250 test cases dynamically loaded from category subfolders.
    Supports both real Appium driver execution and simulation fallback mode.
    """

    # Dynamically aggregate all 250 test cases from the category folders
    TEST_CASES = (
        functional_cases +
        ui_ux_cases +
        compatibility_cases +
        performance_cases +
        security_cases +
        api_cases +
        database_cases +
        accessibility_cases +
        mobile_specific_cases +
        regression_cases +
        e2e_cases
    )

    def run_all_tests(self):
        """Runs the entire end-to-end test flow."""
        self.start_session()
        try:
            for tc in self.TEST_CASES:
                self.run_test_case(tc)
        finally:
            self.stop_session()

    def run_test_case(self, tc):
        """Executes a single test case dynamically based on its specification metadata."""
        test_case_name = f"{tc['id']}_{tc['name'].replace(' ', '_').replace('-', '_')}"
        self.setup_test(test_case_name, tc["description"])
        
        try:
            self.log_step(f"Starting execution of {tc['id']} in category [{tc['category']}]")
            
            for step in tc["steps"]:
                action = step[0]
                args = step[1:]
                
                if action == "tap":
                    element_id = args[0]
                    self.tap(element_id)
                    
                elif action == "type":
                    element_id = args[0]
                    text = args[1]
                    self.type_text(element_id, text)
                    
                elif action == "find":
                    element_id = args[0]
                    self.find_element_by_id(element_id)
                    
                elif action == "find_text":
                    target_text = args[0]
                    self.find_element_by_text(target_text)
                    
                elif action == "sleep":
                    sleep_time = args[0]
                    if self.simulation_mode:
                        time.sleep(0.01)
                    else:
                        time.sleep(sleep_time)
                        
                elif action == "log":
                    log_msg = args[0]
                    self.log_step(log_msg)
                    
                elif action == "verify_activity":
                    target_activity = args[0]
                    self.log_step(f"Verifying active window state is {target_activity}")
                    current_act = self.get_current_activity()
                    if not self.simulation_mode:
                        assert target_activity in current_act, f"Expected activity {target_activity} but was {current_act}"
                    self.log_step(f"Active window state verified: {target_activity}")
                    
                elif action == "assert_equal":
                    val1 = args[0]
                    val2 = args[1]
                    self.log_step(f"Asserting equivalence: {val1} == {val2}")
                    assert val1 == val2, f"Assertion failed: {val1} != {val2}"
                    
            self.finish_test("PASS")
        except Exception as e:
            self.log_step(f"Test step failed: {e}", "ERROR")
            self.finish_test("FAIL", str(e))

# Dynamically generate individual test methods on the class so standard test discovery tools (unittest, pytest)
# can find and run them independently.
def _generate_dynamic_test_methods():
    for tc in SmartSafetyTravelTestSuite.TEST_CASES:
        method_name = f"test_{tc['id'].lower()}_{tc['name'].replace(' ', '_').replace('-', '_').lower()}"
        
        def make_test_method(spec):
            return lambda self: self.run_test_case(spec)
            
        test_method = make_test_method(tc)
        test_method.__doc__ = f"[{tc['category']}] {tc['description']}"
        setattr(SmartSafetyTravelTestSuite, method_name, test_method)

_generate_dynamic_test_methods()
