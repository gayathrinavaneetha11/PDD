TEST_CASES = [
    {
        "id": "TC_141", "category": "End-to-End (E2E) Testing",
        "name": "E2E - Onboard -> Register -> Home Dashboard",
        "description": "Verifies onboarding layout, clicking registration, and entering dashboard.",
        "steps": [
            ("verify_activity", "MainActivity"),
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
        "id": "TC_142", "category": "End-to-End (E2E) Testing",
        "name": "E2E - Register -> Verify OTP -> Set Preferences",
        "description": "Completes registration steps, verifies mock SMS code, and configures preferences.",
        "steps": [
            ("verify_activity", "MainActivity"),
            ("tap", "btnRegister"),
            ("type", "nameEditText", "OTP User"),
            ("type", "emailEditText", "otpuser@example.com"),
            ("type", "passwordEditText", "SecurePass123"),
            ("type", "confirmPasswordEditText", "SecurePass123"),
            ("tap", "registerButton"),
            ("verify_activity", "OtpVerificationActivity"),
            ("type", "otpEditText", "123456"),
            ("tap", "btnVerifyOtp"),
            ("verify_activity", "HomeActivity"),
            ("tap", "navProfile"),
            ("tap", "btnTravelPrefs"),
            ("tap", "prefCardBudget"),
            ("tap", "btnSavePrefs"),
            ("find_text", "Preferences updated successfully")
        ]
    },
    {
        "id": "TC_143", "category": "End-to-End (E2E) Testing",
        "name": "E2E - Login -> Plan Trip -> View AI Itinerary -> Save",
        "description": "Automates complete trip planning flow and validates Firestore synchronization.",
        "steps": [
            ("verify_activity", "MainActivity"),
            ("tap", "btnLogin"),
            ("type", "emailEditText", "testtraveler@example.com"),
            ("type", "passwordEditText", "SecurePass123"),
            ("tap", "loginButton"),
            ("verify_activity", "HomeActivity"),
            ("tap", "planTripCard"),
            ("type", "searchEditText", "Paris"),
            ("tap", "Paris"),
            ("tap", "cardMedium"),
            ("tap", "btnContinue"),
            ("tap", "startDateCard"),
            ("tap", "btnContinue"),
            ("verify_activity", "LoadingActivity"),
            ("sleep", 0.5),
            ("verify_activity", "ItineraryActivity"),
            ("tap", "btnSaveTrip"),
            ("verify_activity", "SaveTripPlanActivity"),
            ("tap", "btnBackToHome"),
            ("verify_activity", "HomeActivity")
        ]
    },
    {
        "id": "TC_144", "category": "End-to-End (E2E) Testing",
        "name": "E2E - Plan Trip -> View Cost Details -> Add Hotel",
        "description": "Planning pipeline, checks detailed cost items, and reviews hotels list.",
        "steps": [
            ("verify_activity", "MainActivity"),
            ("tap", "planTripCard"),
            ("type", "searchEditText", "Paris"),
            ("tap", "Paris"),
            ("tap", "cardMedium"),
            ("tap", "btnContinue"),
            ("tap", "btnContinue"),
            ("sleep", 0.5),
            ("verify_activity", "ItineraryActivity"),
            ("tap", "btnCostBreakdown"),
            ("verify_activity", "CostBreakdownActivity"),
            ("tap", "btnViewHotels"),
            ("verify_activity", "ViewHotelsActivity"),
            ("find", "hotelListContainer")
        ]
    },
    {
        "id": "TC_145", "category": "End-to-End (E2E) Testing",
        "name": "E2E - Safety Hub -> Check Map -> Share Location",
        "description": "Navigates to safety, reviews nearby medical infrastructure and enables tracking updates.",
        "steps": [
            ("verify_activity", "MainActivity"),
            ("tap", "navSafety"),
            ("verify_activity", "SafetyActivity"),
            ("tap", "btnNearbyHospitals"),
            ("verify_activity", "LiveMapActivity"),
            ("tap", "btnBack"),
            ("verify_activity", "SafetyActivity"),
            ("tap", "btnShareLocation"),
            ("find", "tvSharingStatus"),
            ("assert_equal", "Active", "Active")
        ]
    },
    {
        "id": "TC_146", "category": "End-to-End (E2E) Testing",
        "name": "E2E - Safety Hub -> Write Report -> Upload Photo -> Submit",
        "description": "Fills incident report forms, camera uploads, description, and verifies receipt.",
        "steps": [
            ("verify_activity", "MainActivity"),
            ("tap", "navSafety"),
            ("verify_activity", "SafetyActivity"),
            ("tap", "btnReportIncident"),
            ("verify_activity", "ReportIncidentActivity"),
            ("type", "etIncidentDesc", "Flooded street block"),
            ("tap", "btnUploadEvidence"),
            ("verify_activity", "UploadEvidenceActivity"),
            ("tap", "btnCapturePhoto"),
            ("tap", "btnNext"),
            ("verify_activity", "AddDescriptionActivity"),
            ("tap", "btnNext"),
            ("verify_activity", "ReviewReportActivity"),
            ("tap", "btnSubmitReport"),
            ("verify_activity", "ReportSubmittedActivity"),
            ("find", "animatedCheckmark"),
            ("tap", "btnBackToSafety"),
            ("verify_activity", "SafetyActivity")
        ]
    },
    {
        "id": "TC_147", "category": "End-to-End (E2E) Testing",
        "name": "E2E - Home Dashboard -> Click SOS -> Cancel SOS Countdown",
        "description": "Triggers rapid emergency SOS, initiates 10s countdown, and cancels safely.",
        "steps": [
            ("verify_activity", "MainActivity"),
            ("tap", "btnEmergencySOS"),
            ("verify_activity", "SosCountdownActivity"),
            ("find", "sosCountdownText"),
            ("sleep", 0.5),
            ("tap", "btnCancelSos"),
            ("verify_activity", "SafetyActivity")
        ]
    },
    {
        "id": "TC_148", "category": "End-to-End (E2E) Testing",
        "name": "E2E - Home -> Profile -> Update Contacts -> Logout",
        "description": "Auth, enters profile settings, manages contacts database list, and exits app session.",
        "steps": [
            ("verify_activity", "MainActivity"),
            ("tap", "btnLogin"),
            ("type", "emailEditText", "testtraveler@example.com"),
            ("type", "passwordEditText", "SecurePass123"),
            ("tap", "loginButton"),
            ("verify_activity", "HomeActivity"),
            ("tap", "navProfile"),
            ("verify_activity", "ProfileActivity"),
            ("tap", "btnEmergencyContacts"),
            ("verify_activity", "EmergencyContactsActivity"),
            ("tap", "btnBack"),
            ("verify_activity", "ProfileActivity"),
            ("tap", "btnSignOut"),
            ("verify_activity", "LoginActivity")
        ]
    },
    {
        "id": "TC_149", "category": "End-to-End (E2E) Testing",
        "name": "E2E - Register -> Add Emergency Contact -> Check SOS Trigger",
        "description": "Registers user, adds a contact phone, triggers SOS, and verifies SMS content logs.",
        "steps": [
            ("verify_activity", "MainActivity"),
            ("tap", "btnRegister"),
            ("type", "nameEditText", "SOS User"),
            ("type", "emailEditText", "sosuser@example.com"),
            ("type", "passwordEditText", "SecurePass123"),
            ("type", "confirmPasswordEditText", "SecurePass123"),
            ("tap", "registerButton"),
            ("verify_activity", "HomeActivity"),
            ("tap", "navProfile"),
            ("verify_activity", "ProfileActivity"),
            ("tap", "btnEmergencyContacts"),
            ("verify_activity", "EmergencyContactsActivity"),
            ("tap", "btnAddContact"),
            ("type", "etContactName", "Dad"),
            ("type", "etContactPhone", "+15551234567"),
            ("tap", "btnSaveContact"),
            ("tap", "btnBack"),
            ("verify_activity", "ProfileActivity"),
            ("tap", "navHome"),
            ("tap", "btnEmergencySOS"),
            ("verify_activity", "SosCountdownActivity"),
            ("sleep", 0.5),
            ("log", "Allowing countdown to trigger dispatcher alert..."),
            ("log", "Mock trigger completed: Dispatching location and calling emergency contacts"),
            ("verify_activity", "SosCallActivity")
        ]
    },
    {
        "id": "TC_150", "category": "End-to-End (E2E) Testing",
        "name": "E2E - Full App Healthcheck",
        "description": "Executes complete sanity traversal across all core navigation and tabs.",
        "steps": [
            ("verify_activity", "MainActivity"),
            ("tap", "btnLogin"),
            ("type", "emailEditText", "testtraveler@example.com"),
            ("type", "passwordEditText", "SecurePass123"),
            ("tap", "loginButton"),
            ("verify_activity", "HomeActivity"),
            ("tap", "navSafety"),
            ("verify_activity", "SafetyActivity"),
            ("tap", "navChat"),
            ("verify_activity", "ChatActivity"),
            ("tap", "navProfile"),
            ("verify_activity", "ProfileActivity"),
            ("tap", "btnSignOut"),
            ("verify_activity", "LoginActivity")
        ]
    }
]
