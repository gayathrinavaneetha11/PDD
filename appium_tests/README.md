# SmartSafetyTravel Android Appium E2E Testing Framework

This directory contains a complete, automated End-to-End (E2E) UI testing suite for the **SmartSafetyTravel** Android mobile application. The framework is written in Python using the `Appium-Python-Client` library and automatically compiles test execution statistics and steps into a stylized, color-coded Excel spreadsheet report.

---

## Directory Structure

```text
appium_tests/
├── config.py             # Capabilities configuration (package, activity, device specs)
├── requirements.txt      # Python dependencies (Appium client, Excel openpyxl library)
├── test_base.py          # Driver setup/teardown, UI wait helper wrappers, result logs
├── test_suite.py         # The E2E test cases (7 major functional flows)
├── report_generator.py   # Compiles logs and generates styled Excel (.xlsx) files
├── run_tests.py          # Master execution script (includes environment auto-setup)
└── README.md             # Setup and usage guide (this file)
```

---

## Prerequisites & Installation

To run these tests on a local machine, you must set up the mobile testing environment.

### 1. Runtimes & SDKs
- **Python 3.12+** (already installed in your environment)
- **Node.js** (already installed in your environment)
- **Android SDK & Command Line Tools**
  - Install Android Studio, which installs the Android SDK automatically.
  - Set the `ANDROID_HOME` environment variable pointing to your SDK location (e.g. `C:\Users\<username>\AppData\Local\Android\Sdk`).
  - Add the platform tools path (containing `adb.exe`) to your system Environment `Path` variables (e.g. `%ANDROID_HOME%\platform-tools`).

### 2. Appium Server Setup
Open a terminal (Command Prompt, PowerShell, or Git Bash) and run:

```bash
# Install Appium globally via npm
npm install -g appium

# Install the UiAutomator2 driver for Android automation
appium driver install uiautomator2
```

### 3. Check ADB Connection
Connect your Android phone (with **USB Debugging** enabled in Developer Options) or start an Android Virtual Device (AVD) from Android Studio. Verify it is recognized:

```bash
adb devices
```

---

## Running the Tests

1. Start the Appium Server:
   ```bash
   appium
   ```
2. Navigate to this directory and execute the runner:
   ```bash
   python run_tests.py
   ```

> [!NOTE]  
> **Simulation Fallback Mode:**  
> If the script detects that the Appium server is not running on the port `4723`, it will automatically switch to **Simulation Mode**. This will run the test logic against a simulator context to verify the E2E steps work and immediately write a sample styled `test_report.xlsx` for validation.

---

## Configuration (`config.py`)

You can edit `config.py` to specify your device parameters. Important keys:
- `deviceName`: Set this to your emulator's AVD name or phone identifier.
- `udid`: If multiple devices are connected, uncomment this and paste the identifier from `adb devices`.
- `appPackage` and `appActivity`: Points to the SmartSafetyTravel app entrypoint (defaults are preconfigured to `com.example.smartsaftytravel` and `.MainActivity`).

---

## Test Cases Covered

| Test ID | Test Name | Target Activity / Elements |
|:---|:---|:---|
| **TC_001** | User Registration | Registers new user; validates Firestore DB registration. |
| **TC_002** | User Login | Logs in via Firebase Auth credentials. |
| **TC_003** | Home Dashboard | Checks navigation icons and dashboard cards. |
| **TC_004** | Plan Trip Wizard | Automates full booking (Destination -> Budget -> Dates -> Save). |
| **TC_005** | Safety Hub Features | Tests map integration and incident reporting navigation. |
| **TC_006** | SOS Countdown | Verifies the emergency SOS screen and cancels countdown safely. |
| **TC_007** | Profile & Settings | Navigates preferences, contacts, settings, and logs out. |

---

## Analysis Reports (`test_report.xlsx`)

The script compiles run logs into a professional Excel spreadsheet with two sheets:
1. **Summary Dashboard**: Includes KPI cards (Total Run, Passed, Failed, Success Rate, Elapsed Time) and a summary overview table with color-coded status pills (green for PASS, red for FAIL).
2. **Detailed Execution Logs**: Contains the granular step-by-step logs for each test case (with text-wrapping and formatting for easy readability).
