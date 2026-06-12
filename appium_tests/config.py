# Configuration settings for SmartSafetyTravel Appium E2E testing

APPIUM_SERVER_URL = "http://localhost:4723"

# Default desired capabilities for Android
DESIRED_CAPS = {
    "platformName": "Android",
    "automationName": "UiAutomator2",
    "deviceName": "Android Emulator",  # Or your physical device name/ID
    # "udid": "emulator-5554",         # Uncomment and edit to target a specific device
    "appPackage": "com.example.smartsaftytravel",
    "appActivity": ".MainActivity",
    "noReset": False,                  # Reset application state before tests start
    "fullReset": False,                # Do not completely uninstall the app between tests
    "autoGrantPermissions": True,      # Automatically grant camera, location, and storage permissions
    "newCommandTimeout": 300           # Keep session alive up to 5 minutes between commands
}
