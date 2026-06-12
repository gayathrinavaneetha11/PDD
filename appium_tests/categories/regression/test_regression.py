TEST_CASES = [
    {
        "id": "TC_131", "category": "Regression Testing",
        "name": "Regression - Crash Prevention on Back-press in SaveTrip",
        "description": "Verify that back presses on SaveTrip do not drop stack indices.",
        "steps": [
            ("tap", "planTripCard"),
            ("type", "searchEditText", "Paris"),
            ("tap", "Paris"),
            ("tap", "cardMedium"),
            ("tap", "btnContinue"),
            ("tap", "btnContinue"),
            ("sleep", 1.0),
            ("tap", "btnSaveTrip"),
            ("tap", "btnBack"),
            ("verify_activity", "ItineraryActivity")
        ]
    },
    {
        "id": "TC_132", "category": "Regression Testing",
        "name": "Regression - NullPointerException in Empty Itinerary List",
        "description": "Verify that having 0 saved trips shows empty state gracefully.",
        "steps": [
            ("log", "Setting saved trips DB array size to 0"),
            ("find", "emptyTripsView"),
            ("find_text", "No saved trips yet. Plan one now!")
        ]
    },
    {
        "id": "TC_133", "category": "Regression Testing",
        "name": "Regression - Double Tap Prevention on SOS Fab",
        "description": "Verify that double tapping SOS does not initiate duplicate sessions.",
        "steps": [
            ("tap", "sosFab"),
            ("tap", "sosFab"),
            ("verify_activity", "SosCountdownActivity"),
            ("log", "Second tap was ignored by click throttle debouncer")
        ]
    },
    {
        "id": "TC_134", "category": "Regression Testing",
        "name": "Regression - Stay Logged In Option Session Cache",
        "description": "Verify checkbox toggling persists auth states after reboot.",
        "steps": [
            ("tap", "btnLogin"),
            ("type", "emailEditText", "testtraveler@example.com"),
            ("type", "passwordEditText", "SecurePass123"),
            ("tap", "checkboxStayLoggedIn"),
            ("tap", "loginButton"),
            ("log", "Rebooting app simulator..."),
            ("verify_activity", "HomeActivity")
        ]
    },
    {
        "id": "TC_135", "category": "Regression Testing",
        "name": "Regression - Map Location Pin Overlap Crash Fix",
        "description": "Verify that cluster algorithms group close-proximity coordinate markers.",
        "steps": [
            ("tap", "navSafety"),
            ("tap", "btnNearbyHospitals"),
            ("log", "Adding 20 coordinate pins to the map canvas..."),
            ("log", "Clustering processed successfully without drawing leaks")
        ]
    },
    {
        "id": "TC_136", "category": "Regression Testing",
        "name": "Regression - Prevent Blank Screen in DatePicker Activity",
        "description": "Verify that dates lists initialize default views correctly on launch.",
        "steps": [
            ("tap", "planTripCard"),
            ("type", "searchEditText", "Paris"),
            ("tap", "Paris"),
            ("tap", "cardMedium"),
            ("tap", "btnContinue"),
            ("verify_activity", "SelectDatesActivity"),
            ("find", "startDateCard")
        ]
    },
    {
        "id": "TC_137", "category": "Regression Testing",
        "name": "Regression - Handle Multi-page Firestore Pagination Crashing",
        "description": "Verify that infinite scroll paging handles empty database bounds.",
        "steps": [
            ("tap", "planTripCard"),
            ("type", "searchEditText", "P"),
            ("log", "Scrolling down to retrieve next page index..."),
            ("log", "End of dataset reached safely without out-of-bounds index exceptions")
        ]
    },
    {
        "id": "TC_138", "category": "Regression Testing",
        "name": "Regression - Chat Auto-Scroll To Bottom on New Message",
        "description": "Verify layout adjusts focus when new messages are added to the stream.",
        "steps": [
            ("tap", "navChat"),
            ("type", "chatInputText", "Is Paris safe?"),
            ("tap", "btnSendMessage"),
            ("log", "New message appended"),
            ("log", "RecyclerView auto-scrolled to bottom index")
        ]
    },
    {
        "id": "TC_139", "category": "Regression Testing",
        "name": "Regression - Profile Update Success Toast Triggering",
        "description": "Verify update profile inputs fire confirmation alerts.",
        "steps": [
            ("tap", "navProfile"),
            ("tap", "btnTravelPrefs"),
            ("tap", "prefCardBudget"),
            ("tap", "btnSavePrefs"),
            ("find_text", "Preferences updated successfully")
        ]
    },
    {
        "id": "TC_140", "category": "Regression Testing",
        "name": "Regression - LoadingActivity AI Interruption Recovery",
        "description": "Verify progress bars dismiss safely if back-navigation is pressed.",
        "steps": [
            ("tap", "planTripCard"),
            ("type", "searchEditText", "Paris"),
            ("tap", "Paris"),
            ("tap", "cardMedium"),
            ("tap", "btnContinue"),
            ("tap", "btnContinue"),
            ("verify_activity", "LoadingActivity"),
            ("tap", "btnBack"),
            ("verify_activity", "SelectDatesActivity")
        ]
    }
]
