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
    },
    {
        "id": "TC_151", "category": "Functional Testing",
        "name": "User Profile - Update Display Name",
        "description": "Verify user can update their display name in profile settings.",
        "steps": [
            ("tap", "navProfile"),
            ("tap", "btnEditProfile"),
            ("type", "nameEditText", "Updated Name"),
            ("tap", "btnSaveProfile"),
            ("find_text", "Profile updated successfully")
        ]
    },
    {
        "id": "TC_152", "category": "Functional Testing",
        "name": "User Profile - Update Profile Picture",
        "description": "Verify user can upload and change profile picture.",
        "steps": [
            ("tap", "navProfile"),
            ("tap", "btnEditProfile"),
            ("tap", "btnChangePhoto"),
            ("tap", "btnSelectFromGallery"),
            ("tap", "btnSaveProfile"),
            ("find", "profileAvatar")
        ]
    },
    {
        "id": "TC_153", "category": "Functional Testing",
        "name": "Trip Planning - Search Destination",
        "description": "Verify destination search functionality returns relevant results.",
        "steps": [
            ("tap", "planTripCard"),
            ("type", "searchEditText", "London"),
            ("find_text", "London")
        ]
    },
    {
        "id": "TC_154", "category": "Functional Testing",
        "name": "Trip Planning - Select Travel Dates",
        "description": "Verify user can select start and end dates for trip.",
        "steps": [
            ("tap", "planTripCard"),
            ("type", "searchEditText", "Tokyo"),
            ("tap", "Tokyo"),
            ("tap", "cardMedium"),
            ("tap", "btnContinue"),
            ("tap", "startDateCard"),
            ("tap", "btnContinue"),
            ("find", "selectedDatesText")
        ]
    },
    {
        "id": "TC_155", "category": "Functional Testing",
        "name": "Trip Planning - Select Budget Range",
        "description": "Verify user can select budget range for trip planning.",
        "steps": [
            ("tap", "planTripCard"),
            ("type", "searchEditText", "Rome"),
            ("tap", "Rome"),
            ("tap", "cardMedium"),
            ("tap", "btnContinue"),
            ("tap", "budgetCard"),
            ("tap", "budgetMedium"),
            ("tap", "btnContinue"),
            ("find", "selectedBudgetText")
        ]
    },
    {
        "id": "TC_156", "category": "Functional Testing",
        "name": "Trip Planning - Save Trip to Favorites",
        "description": "Verify user can save trip to favorites list.",
        "steps": [
            ("tap", "planTripCard"),
            ("type", "searchEditText", "Berlin"),
            ("tap", "Berlin"),
            ("tap", "cardMedium"),
            ("tap", "btnContinue"),
            ("tap", "btnSaveTrip"),
            ("find_text", "Trip saved to favorites")
        ]
    },
    {
        "id": "TC_157", "category": "Functional Testing",
        "name": "Chat - Send Message to Support",
        "description": "Verify user can send messages to customer support.",
        "steps": [
            ("tap", "navChat"),
            ("type", "chatInputText", "I need help with my trip"),
            ("tap", "btnSendMessage"),
            ("find", "messageRecyclerView")
        ]
    },
    {
        "id": "TC_158", "category": "Functional Testing",
        "name": "Chat - Receive Support Response",
        "description": "Verify user receives automated support response.",
        "steps": [
            ("tap", "navChat"),
            ("type", "chatInputText", "Hello"),
            ("tap", "btnSendMessage"),
            ("sleep", 1.0),
            ("find_text", "Support agent")
        ]
    },
    {
        "id": "TC_159", "category": "Functional Testing",
        "name": "Settings - Enable Push Notifications",
        "description": "Verify user can enable push notifications in settings.",
        "steps": [
            ("tap", "navProfile"),
            ("tap", "btnSettings"),
            ("tap", "toggleNotifications"),
            ("find_text", "Notifications enabled")
        ]
    },
    {
        "id": "TC_160", "category": "Functional Testing",
        "name": "Settings - Change Language Preference",
        "description": "Verify user can change app language preference.",
        "steps": [
            ("tap", "navProfile"),
            ("tap", "btnSettings"),
            ("tap", "languageSelector"),
            ("tap", "langSpanish"),
            ("find_text", "Idioma cambiado")
        ]
    },
    {
        "id": "TC_251", "category": "Functional Testing",
        "name": "User Profile - Change Password",
        "description": "Verify user can change their password successfully.",
        "steps": [
            ("tap", "navProfile"),
            ("tap", "btnChangePassword"),
            ("type", "currentPassword", "SecurePass123"),
            ("type", "newPassword", "NewSecurePass456"),
            ("type", "confirmNewPassword", "NewSecurePass456"),
            ("tap", "btnUpdatePassword"),
            ("find_text", "Password updated successfully")
        ]
    },
    {
        "id": "TC_252", "category": "Functional Testing",
        "name": "Trip Planning - Filter by Budget",
        "description": "Verify user can filter trip results by budget range.",
        "steps": [
            ("tap", "planTripCard"),
            ("type", "searchEditText", "Barcelona"),
            ("tap", "budgetFilter"),
            ("tap", "budgetLow"),
            ("tap", "btnApplyFilter"),
            ("find", "filteredResults")
        ]
    },
    {
        "id": "TC_253", "category": "Functional Testing",
        "name": "Trip Planning - Filter by Duration",
        "description": "Verify user can filter trips by duration.",
        "steps": [
            ("tap", "planTripCard"),
            ("type", "searchEditText", "Amsterdam"),
            ("tap", "durationFilter"),
            ("tap", "durationWeek"),
            ("tap", "btnApplyFilter"),
            ("find", "filteredResults")
        ]
    },
    {
        "id": "TC_254", "category": "Functional Testing",
        "name": "Chat - Send Image Message",
        "description": "Verify user can send images in chat.",
        "steps": [
            ("tap", "navChat"),
            ("tap", "btnAttachImage"),
            ("tap", "btnSelectFromGallery"),
            ("tap", "btnSendMessage"),
            ("find", "messageRecyclerView")
        ]
    },
    {
        "id": "TC_255", "category": "Functional Testing",
        "name": "Settings - Enable Dark Mode",
        "description": "Verify user can enable dark mode in settings.",
        "steps": [
            ("tap", "navProfile"),
            ("tap", "btnSettings"),
            ("tap", "toggleDarkMode"),
            ("find", "darkModeActive")
        ]
    },
    {
        "id": "TC_256", "category": "Functional Testing",
        "name": "Trip Planning - Save Multiple Destinations",
        "description": "Verify user can save multiple destinations to wishlist.",
        "steps": [
            ("tap", "planTripCard"),
            ("type", "searchEditText", "Vienna"),
            ("tap", "Vienna"),
            ("tap", "btnAddToWishlist"),
            ("find_text", "Added to wishlist")
        ]
    },
    {
        "id": "TC_257", "category": "Functional Testing",
        "name": "Safety - View Nearby Police Stations",
        "description": "Verify user can view nearby police stations on map.",
        "steps": [
            ("tap", "navSafety"),
            ("tap", "btnNearbyPolice"),
            ("find", "mapMarkers")
        ]
    },
    {
        "id": "TC_258", "category": "Functional Testing",
        "name": "Safety - View Nearby Embassies",
        "description": "Verify user can view nearby embassies on map.",
        "steps": [
            ("tap", "navSafety"),
            ("tap", "btnNearbyEmbassies"),
            ("find", "mapMarkers")
        ]
    },
    {
        "id": "TC_259", "category": "Functional Testing",
        "name": "Profile - Delete Account",
        "description": "Verify user can delete their account.",
        "steps": [
            ("tap", "navProfile"),
            ("tap", "btnDeleteAccount"),
            ("type", "confirmPassword", "SecurePass123"),
            ("tap", "btnConfirmDelete"),
            ("find_text", "Account deleted successfully")
        ]
    },
    {
        "id": "TC_260", "category": "Functional Testing",
        "name": "Trip Planning - Share Trip",
        "description": "Verify user can share trip details with others.",
        "steps": [
            ("tap", "navProfile"),
            ("tap", "btnSavedTrips"),
            ("tap", "btnShareTrip"),
            ("find", "shareOptions")
        ]
    },
    {
        "id": "TC_261", "category": "Functional Testing",
        "name": "Chat - Clear Chat History",
        "description": "Verify user can clear chat history.",
        "steps": [
            ("tap", "navChat"),
            ("tap", "btnClearHistory"),
            ("tap", "btnConfirmClear"),
            ("find_text", "Chat history cleared")
        ]
    },
    {
        "id": "TC_262", "category": "Functional Testing",
        "name": "Settings - Manage Notification Preferences",
        "description": "Verify user can manage notification preferences.",
        "steps": [
            ("tap", "navProfile"),
            ("tap", "btnSettings"),
            ("tap", "notificationPreferences"),
            ("tap", "toggleTripAlerts"),
            ("tap", "toggleSafetyAlerts"),
            ("tap", "btnSavePreferences"),
            ("find_text", "Preferences saved")
        ]
    },
    {
        "id": "TC_263", "category": "Functional Testing",
        "name": "Safety - View Crime Statistics",
        "description": "Verify user can view crime statistics for area.",
        "steps": [
            ("tap", "navSafety"),
            ("tap", "btnCrimeStats"),
            ("find", "statsChart")
        ]
    },
    {
        "id": "TC_264", "category": "Functional Testing",
        "name": "Trip Planning - Book Flight",
        "description": "Verify user can book flights through app.",
        "steps": [
            ("tap", "planTripCard"),
            ("type", "searchEditText", "Dublin"),
            ("tap", "Dublin"),
            ("tap", "cardMedium"),
            ("tap", "btnContinue"),
            ("tap", "btnBookFlight"),
            ("find", "flightOptions")
        ]
    },
    {
        "id": "TC_265", "category": "Functional Testing",
        "name": "Profile - View Travel History",
        "description": "Verify user can view their travel history.",
        "steps": [
            ("tap", "navProfile"),
            ("tap", "btnTravelHistory"),
            ("find", "historyList")
        ]
    }
]
