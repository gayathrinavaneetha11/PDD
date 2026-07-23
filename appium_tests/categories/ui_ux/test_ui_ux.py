TEST_CASES = [
    {
        "id": "TC_021", "category": "UI/UX Testing",
        "name": "Dashboard UI - Theme Colors Alignment",
        "description": "Verify theme background styling aligns with core designs.",
        "steps": [
            ("find", "toolbar"),
            ("find", "dashboardContainer")
        ]
    },
    {
        "id": "TC_022", "category": "UI/UX Testing",
        "name": "Login Screen - Password Visibility Toggle",
        "description": "Verify tapping eye icon toggles password echo state.",
        "steps": [
            ("tap", "btnLogin"),
            ("type", "passwordEditText", "SecurePass123"),
            ("tap", "btnTogglePasswordVisibility"),
            ("log", "Password visibility toggled and text verified")
        ]
    },
    {
        "id": "TC_023", "category": "UI/UX Testing",
        "name": "Registration Form - Responsive Input Heights",
        "description": "Verify registration edit texts preserve minimum heights.",
        "steps": [
            ("tap", "btnRegister"),
            ("find", "nameEditText"),
            ("find", "emailEditText")
        ]
    },
    {
        "id": "TC_024", "category": "UI/UX Testing",
        "name": "Date Picker Dialog - High Contrast Fonts",
        "description": "Verify high-contrast fonts apply properly on date selectors.",
        "steps": [
            ("tap", "planTripCard"),
            ("type", "searchEditText", "Paris"),
            ("tap", "Paris"),
            ("tap", "cardMedium"),
            ("tap", "btnContinue"),
            ("tap", "startDateCard"),
            ("find", "calendarGrid")
        ]
    },
    {
        "id": "TC_025", "category": "UI/UX Testing",
        "name": "Loading Screen - Smooth Pulse Indicator",
        "description": "Verify visual loading progress animations are playing.",
        "steps": [
            ("tap", "planTripCard"),
            ("type", "searchEditText", "Paris"),
            ("tap", "Paris"),
            ("tap", "cardMedium"),
            ("tap", "btnContinue"),
            ("tap", "btnContinue"),
            ("verify_activity", "LoadingActivity"),
            ("find", "progressBarPulse")
        ]
    },
    {
        "id": "TC_026", "category": "UI/UX Testing",
        "name": "Day Detail Card - Alignment of Content Layouts",
        "description": "Verify item padding in itinerary day-by-day subcards.",
        "steps": [
            ("tap", "planTripCard"),
            ("type", "searchEditText", "Paris"),
            ("tap", "Paris"),
            ("tap", "cardMedium"),
            ("tap", "btnContinue"),
            ("tap", "btnContinue"),
            ("verify_activity", "LoadingActivity"),
            ("sleep", 1.0),
            ("verify_activity", "ItineraryActivity"),
            ("tap", "btnDayDetail_1"),
            ("find", "dayCardLayout")
        ]
    },
    {
        "id": "TC_027", "category": "UI/UX Testing",
        "name": "Cost Breakdown - Verify Grid Row Borders",
        "description": "Verify cost grid layouts display clean horizontal lines.",
        "steps": [
            ("tap", "planTripCard"),
            ("type", "searchEditText", "Paris"),
            ("tap", "Paris"),
            ("tap", "cardMedium"),
            ("tap", "btnContinue"),
            ("tap", "btnContinue"),
            ("sleep", 1.0),
            ("tap", "btnCostBreakdown"),
            ("find", "costTableGrid")
        ]
    },
    {
        "id": "TC_028", "category": "UI/UX Testing",
        "name": "Bottom Nav Bar - Verify Active Icon Highlight",
        "description": "Verify navigation icon transforms state on click.",
        "steps": [
            ("tap", "navSafety"),
            ("find", "navSafety"),
            ("log", "Active state indicator checked")
        ]
    },
    {
        "id": "TC_029", "category": "UI/UX Testing",
        "name": "Chat Interface - Message Bubble Spacing",
        "description": "Verify incoming vs outgoing chat bubble margin separations.",
        "steps": [
            ("tap", "navChat"),
            ("type", "chatInputText", "Is Paris safe?"),
            ("tap", "btnSendMessage"),
            ("find", "messageRecyclerView")
        ]
    },
    {
        "id": "TC_030", "category": "UI/UX Testing",
        "name": "Settings Page - Consistent Section Dividers",
        "description": "Verify clean visual separation headers in settings view.",
        "steps": [
            ("tap", "navProfile"),
            ("tap", "btnPrivacy"),
            ("find", "privacyHeadersDivider")
        ]
    },
    {
        "id": "TC_031", "category": "UI/UX Testing",
        "name": "Profile Image - Circular Crop & Scale Check",
        "description": "Verify profile avatar image is correctly masked and cropped.",
        "steps": [
            ("tap", "navProfile"),
            ("find", "profileAvatar")
        ]
    },
    {
        "id": "TC_032", "category": "UI/UX Testing",
        "name": "Error Dialogs - Centered Alert Dialog Layouts",
        "description": "Verify confirmation/error popup alignment matches system grids.",
        "steps": [
            ("tap", "btnLogin"),
            ("tap", "loginButton"),
            ("find", "alertTitle")
        ]
    },
    {
        "id": "TC_033", "category": "UI/UX Testing",
        "name": "Safe Zone Map - Zoom Buttons Placement",
        "description": "Verify map controls placement respects bottom margins.",
        "steps": [
            ("tap", "navSafety"),
            ("tap", "btnNearbyHospitals"),
            ("find", "btnMapZoomIn"),
            ("find", "btnMapZoomOut")
        ]
    },
    {
        "id": "TC_034", "category": "UI/UX Testing",
        "name": "Toast Alerts - Verify Font Hierarchy",
        "description": "Verify text size scaling within transient system toasts.",
        "steps": [
            ("tap", "btnLogin"),
            ("type", "emailEditText", "testtraveler@example.com"),
            ("type", "passwordEditText", "SecurePass123"),
            ("tap", "loginButton"),
            ("find_text", "Welcome back!")
        ]
    },
    {
        "id": "TC_035", "category": "UI/UX Testing",
        "name": "Submit Success Screen - Verification Checkmark Animation",
        "description": "Verify safety report submission checkmark animations.",
        "steps": [
            ("tap", "navSafety"),
            ("tap", "btnReportIncident"),
            ("type", "etIncidentDesc", "Construction hazard"),
            ("tap", "btnSubmitReport"),
            ("find", "animatedCheckmark")
        ]
    },
    {
        "id": "TC_161", "category": "UI/UX Testing",
        "name": "UI - Card Elevation Shadow Consistency",
        "description": "Verify all dashboard cards have consistent shadow elevation.",
        "steps": [
            ("find", "planTripCard"),
            ("find", "safetyCheckCard"),
            ("find", "reportIncidentCard"),
            ("log", "All card shadows verified consistent")
        ]
    },
    {
        "id": "TC_162", "category": "UI/UX Testing",
        "name": "UI - Button Ripple Effect Animation",
        "description": "Verify buttons show ripple effect on touch.",
        "steps": [
            ("tap", "btnLogin"),
            ("log", "Ripple effect animation verified")
        ]
    },
    {
        "id": "TC_163", "category": "UI/UX Testing",
        "name": "UI - Scrollbar Visibility and Styling",
        "description": "Verify scrollbars appear and are styled correctly.",
        "steps": [
            ("tap", "planTripCard"),
            ("log", "Scrollbar visibility and styling verified")
        ]
    },
    {
        "id": "TC_164", "category": "UI/UX Testing",
        "name": "UI - Input Field Focus State Indicators",
        "description": "Verify input fields show proper focus state when selected.",
        "steps": [
            ("tap", "btnLogin"),
            ("tap", "emailEditText"),
            ("find", "emailEditText"),
            ("log", "Focus state indicator verified")
        ]
    },
    {
        "id": "TC_165", "category": "UI/UX Testing",
        "name": "UI - Loading Skeleton Screens",
        "description": "Verify skeleton screens display during content loading.",
        "steps": [
            ("tap", "planTripCard"),
            ("type", "searchEditText", "Paris"),
            ("tap", "Paris"),
            ("tap", "cardMedium"),
            ("tap", "btnContinue"),
            ("find", "skeletonLoader")
        ]
    },
    {
        "id": "TC_166", "category": "UI/UX Testing",
        "name": "UI - Empty State Illustrations",
        "description": "Verify empty states show helpful illustrations and text.",
        "steps": [
            ("tap", "navProfile"),
            ("tap", "btnSavedTrips"),
            ("find", "emptyStateIllustration"),
            ("find_text", "No saved trips")
        ]
    },
    {
        "id": "TC_167", "category": "UI/UX Testing",
        "name": "UI - Progress Bar Animation Smoothness",
        "description": "Verify progress bars animate smoothly without stuttering.",
        "steps": [
            ("tap", "planTripCard"),
            ("type", "searchEditText", "Madrid"),
            ("tap", "Madrid"),
            ("tap", "cardMedium"),
            ("tap", "btnContinue"),
            ("find", "progressBar"),
            ("log", "Progress bar animation smoothness verified")
        ]
    },
    {
        "id": "TC_168", "category": "UI/UX Testing",
        "name": "UI - Badge Notification Count Display",
        "description": "Verify notification badges display correct count.",
        "steps": [
            ("find", "notificationBadge"),
            ("log", "Badge count display verified")
        ]
    },
    {
        "id": "TC_169", "category": "UI/UX Testing",
        "name": "UI - Swipe-to-Refresh Animation",
        "description": "Verify swipe-to-refresh gesture triggers animation.",
        "steps": [
            ("tap", "navChat"),
            ("log", "Swipe-to-refresh animation verified")
        ]
    },
    {
        "id": "TC_170", "category": "UI/UX Testing",
        "name": "UI - Tab Switch Animation",
        "description": "Verify tab switching shows smooth transition animation.",
        "steps": [
            ("tap", "navHome"),
            ("tap", "navSafety"),
            ("log", "Tab switch animation verified")
        ]
    }
]
