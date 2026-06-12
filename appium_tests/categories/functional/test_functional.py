TEST_CASES = [
    {
        "id": "TC_001", "category": "Functional Testing",
        "name": "User Registration - Valid Data",
        "description": "Verify that a user can register with valid name, email, and password.",
        "steps": [
            ("tap", "btnRegister"),
            ("type", "nameEditText", "Test Traveler"),
            ("type", "emailEditText", "testtraveler@example.com"),
            ("type", "passwordEditText", "SecurePass123"),
            ("type", "confirmPasswordEditText", "SecurePass123"),
            ("tap", "registerButton"),
            ("verify_activity", "HomeActivity")
        ]
    },
    {
        "id": "TC_002", "category": "Functional Testing",
        "name": "User Registration - Duplicate Email",
        "description": "Verify registration failure and error handling when email already exists.",
        "steps": [
            ("tap", "btnRegister"),
            ("type", "nameEditText", "Test Traveler"),
            ("type", "emailEditText", "testtraveler@example.com"),
            ("type", "passwordEditText", "SecurePass123"),
            ("type", "confirmPasswordEditText", "SecurePass123"),
            ("tap", "registerButton"),
            ("find_text", "Email already registered")
        ]
    },
    {
        "id": "TC_003", "category": "Functional Testing",
        "name": "User Registration - Invalid Email Format",
        "description": "Verify registration validation error on malformed email address.",
        "steps": [
            ("tap", "btnRegister"),
            ("type", "emailEditText", "invalid-email"),
            ("tap", "registerButton"),
            ("find_text", "Enter a valid email address")
        ]
    },
    {
        "id": "TC_004", "category": "Functional Testing",
        "name": "User Registration - Short Password",
        "description": "Verify registration password complexity validation (minimum 6 chars).",
        "steps": [
            ("tap", "btnRegister"),
            ("type", "passwordEditText", "123"),
            ("tap", "registerButton"),
            ("find_text", "Password must be at least 6 characters")
        ]
    },
    {
        "id": "TC_005", "category": "Functional Testing",
        "name": "User Registration - Passwords Mismatch",
        "description": "Verify registration error when password and confirm password fields differ.",
        "steps": [
            ("tap", "btnRegister"),
            ("type", "passwordEditText", "SecurePass123"),
            ("type", "confirmPasswordEditText", "SecurePass456"),
            ("tap", "registerButton"),
            ("find_text", "Passwords do not match")
        ]
    },
    {
        "id": "TC_006", "category": "Functional Testing",
        "name": "User Login - Valid Credentials",
        "description": "Verify that a registered user can log in with valid credentials.",
        "steps": [
            ("tap", "btnLogin"),
            ("type", "emailEditText", "testtraveler@example.com"),
            ("type", "passwordEditText", "SecurePass123"),
            ("tap", "loginButton"),
            ("verify_activity", "HomeActivity")
        ]
    },
    {
        "id": "TC_007", "category": "Functional Testing",
        "name": "User Login - Invalid Password",
        "description": "Verify login failure with incorrect password and proper error feedback.",
        "steps": [
            ("tap", "btnLogin"),
            ("type", "emailEditText", "testtraveler@example.com"),
            ("type", "passwordEditText", "WrongPass123"),
            ("tap", "loginButton"),
            ("find_text", "Invalid credentials")
        ]
    },
    {
        "id": "TC_008", "category": "Functional Testing",
        "name": "User Login - Unregistered Email",
        "description": "Verify login failure with an email that is not in the system.",
        "steps": [
            ("tap", "btnLogin"),
            ("type", "emailEditText", "notregistered@example.com"),
            ("type", "passwordEditText", "SecurePass123"),
            ("tap", "loginButton"),
            ("find_text", "User not found")
        ]
    },
    {
        "id": "TC_009", "category": "Functional Testing",
        "name": "OTP Verification - Valid 6-Digit Code",
        "description": "Verify MFA flow using a valid 6-digit One Time Password.",
        "steps": [
            ("verify_activity", "OtpVerificationActivity"),
            ("type", "otpEditText", "123456"),
            ("tap", "btnVerifyOtp"),
            ("verify_activity", "HomeActivity")
        ]
    },
    {
        "id": "TC_010", "category": "Functional Testing",
        "name": "OTP Verification - Expired OTP Handling",
        "description": "Verify appropriate error handling when entering an expired OTP.",
        "steps": [
            ("verify_activity", "OtpVerificationActivity"),
            ("type", "otpEditText", "000000"),
            ("tap", "btnVerifyOtp"),
            ("find_text", "OTP expired")
        ]
    },
    {
        "id": "TC_011", "category": "Functional Testing",
        "name": "OTP Verification - Resend OTP Functionality",
        "description": "Verify that the resend OTP request resets timer and triggers SMS.",
        "steps": [
            ("verify_activity", "OtpVerificationActivity"),
            ("tap", "btnResendOtp"),
            ("find_text", "New OTP code sent")
        ]
    },
    {
        "id": "TC_012", "category": "Functional Testing",
        "name": "Password Reset - Send Reset Link",
        "description": "Verify sending reset password instructions link to email.",
        "steps": [
            ("tap", "btnForgotPassword"),
            ("type", "resetEmailEditText", "testtraveler@example.com"),
            ("tap", "btnSendResetLink"),
            ("find_text", "Reset link sent")
        ]
    },
    {
        "id": "TC_013", "category": "Functional Testing",
        "name": "Password Reset - Empty Email Validation",
        "description": "Verify validation error when requesting reset with empty email.",
        "steps": [
            ("tap", "btnForgotPassword"),
            ("tap", "btnSendResetLink"),
            ("find_text", "Please enter your email")
        ]
    },
    {
        "id": "TC_014", "category": "Functional Testing",
        "name": "Dashboard - Check Elements Visibility",
        "description": "Verify the dashboard card components display correctly.",
        "steps": [
            ("find", "planTripCard"),
            ("find", "safetyCheckCard"),
            ("find", "reportIncidentCard")
        ]
    },
    {
        "id": "TC_015", "category": "Functional Testing",
        "name": "Navigation - Slide-out Menu Links",
        "description": "Verify that navigating slide menu options launches target activities.",
        "steps": [
            ("tap", "btnMenuHamburger"),
            ("tap", "menuItemHelp"),
            ("find_text", "Help Support Center")
        ]
    },
    {
        "id": "TC_016", "category": "Functional Testing",
        "name": "Location Sharing - Enable Real-time Tracking",
        "description": "Verify that enabling live tracking updates coordinate logs.",
        "steps": [
            ("tap", "navSafety"),
            ("tap", "btnShareLocation"),
            ("find", "tvSharingStatus"),
            ("assert_equal", "Active", "Active")
        ]
    },
    {
        "id": "TC_017", "category": "Functional Testing",
        "name": "Safety Hub - Check Weather Alert Widget",
        "description": "Verify current location temperature updates correctly.",
        "steps": [
            ("tap", "navSafety"),
            ("find", "weatherText")
        ]
    },
    {
        "id": "TC_018", "category": "Functional Testing",
        "name": "Safety Hub - Risk Assessment Index View",
        "description": "Verify regional crime stats/safety indexing loading functions.",
        "steps": [
            ("tap", "navSafety"),
            ("find", "riskSubText")
        ]
    },
    {
        "id": "TC_019", "category": "Functional Testing",
        "name": "SOS Siren - Turn on Loud Alarm Sound",
        "description": "Verify triggers for local audio alarm siren execution.",
        "steps": [
            ("tap", "sosFab"),
            ("tap", "btnTriggerSiren"),
            ("find_text", "Siren Sound Active")
        ]
    },
    {
        "id": "TC_020", "category": "Functional Testing",
        "name": "SOS Siren - Mute / Deactivate Siren",
        "description": "Verify volume controls disable safety sound alarms.",
        "steps": [
            ("tap", "sosFab"),
            ("tap", "btnTriggerSiren"),
            ("tap", "btnMuteSiren"),
            ("find_text", "Siren Sound Muted")
        ]
    }
]
