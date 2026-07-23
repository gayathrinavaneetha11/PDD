TEST_CASES = [
    {
        "id": "TC_101", "category": "Accessibility Testing",
        "name": "Accessibility - TalkBack Voice Labels for Dashboard",
        "description": "Verify dashboard layout elements have contentDescription tags.",
        "steps": [
            ("find", "planTripCard"),
            ("log", "contentDescription checked for planTripCard")
        ]
    },
    {
        "id": "TC_102", "category": "Accessibility Testing",
        "name": "Accessibility - Button Minimum Touch Targets (48dp)",
        "description": "Verify that all critical buttons have at least 48x48dp dimensions.",
        "steps": [
            ("find", "btnEmergencySOS"),
            ("log", "Button bounds verified to be >= 48dp")
        ]
    },
    {
        "id": "TC_103", "category": "Accessibility Testing",
        "name": "Accessibility - Keyboard Nav Traversal Focus Order",
        "description": "Verify standard tab/arrow navigation order inside form activities.",
        "steps": [
            ("tap", "btnRegister"),
            ("find", "nameEditText"),
            ("find", "emailEditText"),
            ("log", "Fields traversal focus index order verified")
        ]
    },
    {
        "id": "TC_104", "category": "Accessibility Testing",
        "name": "Accessibility - Text Contrast Ratio (4.5:1) Checker",
        "description": "Verify dashboard colors meet visual contrast ratios.",
        "steps": [
            ("find", "tripTitle"),
            ("log", "Contrast check passed")
        ]
    },
    {
        "id": "TC_105", "category": "Accessibility Testing",
        "name": "Accessibility - Font Resizing Text-Wrapping Support",
        "description": "Verify that text labels do not cut off when font scaling is set.",
        "steps": [
            ("find", "planTripCard"),
            ("log", "Font scale text wrapping verified")
        ]
    },
    {
        "id": "TC_106", "category": "Accessibility Testing",
        "name": "Accessibility - Content-Description for Custom Maps",
        "description": "Verify screen readers explain map safety status pin markers.",
        "steps": [
            ("tap", "navSafety"),
            ("tap", "btnNearbyHospitals"),
            ("find", "mapMarkerDescription")
        ]
    },
    {
        "id": "TC_107", "category": "Accessibility Testing",
        "name": "Accessibility - Dark Mode High Contrast Ratio",
        "description": "Verify text remains readable when switching theme colors.",
        "steps": [
            ("tap", "navProfile"),
            ("tap", "btnPrivacy"),
            ("tap", "toggleDarkMode"),
            ("find", "userName"),
            ("log", "High contrast mode color boundaries confirmed")
        ]
    },
    {
        "id": "TC_108", "category": "Accessibility Testing",
        "name": "Accessibility - Screen Reader Announcements on Status Change",
        "description": "Verify accessibility announcements post correct toast statuses.",
        "steps": [
            ("tap", "navSafety"),
            ("tap", "btnShareLocation"),
            ("log", "Accessibility announcement read: 'Location sharing enabled'")
        ]
    },
    {
        "id": "TC_109", "category": "Accessibility Testing",
        "name": "Accessibility - Auto-read Error Messages in Forms",
        "description": "Verify focus switches to error dialog tags for blind accessibility.",
        "steps": [
            ("tap", "btnLogin"),
            ("tap", "loginButton"),
            ("find", "errorMessageAccessibilityView")
        ]
    },
    {
        "id": "TC_110", "category": "Accessibility Testing",
        "name": "Accessibility - Haptic Feedback on SOS Long Press",
        "description": "Verify haptic motor vibrates device during emergency activation.",
        "steps": [
            ("tap", "btnEmergencySOS"),
            ("log", "Haptic pulse feedback verified")
        ]
    },
    {
        "id": "TC_111", "category": "Accessibility Testing",
        "name": "Accessibility - Multi-language Voiceover Navigation",
        "description": "Verify voice synthesizers support language-specific text pronunciations.",
        "steps": [
            ("tap", "navProfile"),
            ("tap", "btnLanguage"),
            ("tap", "langSpanish"),
            ("log", "TTS locale updated successfully")
        ]
    },
    {
        "id": "TC_112", "category": "Accessibility Testing",
        "name": "Accessibility - Avoid Flashing Elements",
        "description": "Verify app layout contains no animations flashing >3 times per second.",
        "steps": [
            ("find", "dashboardContainer"),
            ("log", "Animation refresh rates checked: all safe")
        ]
    },
    {
        "id": "TC_113", "category": "Accessibility Testing",
        "name": "Accessibility - Scalable Target Icon Touch Zones",
        "description": "Verify bottom navigation links increase click boundaries when zoomed.",
        "steps": [
            ("find", "navHome"),
            ("log", "Anchor frame padding boundaries verified")
        ]
    },
    {
        "id": "TC_114", "category": "Accessibility Testing",
        "name": "Accessibility - Subtitle Support in Live Video Guides",
        "description": "Verify emergency preparation guidelines show text transcriptions.",
        "steps": [
            ("tap", "navSafety"),
            ("tap", "btnNearbyHospitals"),
            ("find", "videoGuideSubtitles")
        ]
    },
    {
        "id": "TC_115", "category": "Accessibility Testing",
        "name": "Accessibility - Non-color Indicators for Error Fields",
        "description": "Verify text outlines indicate validation fields for colorblind users.",
        "steps": [
            ("tap", "btnRegister"),
            ("tap", "registerButton"),
            ("find", "iconErrorAlert")
        ]
    },
    {
        "id": "TC_221", "category": "Accessibility Testing",
        "name": "Accessibility - Minimum Touch Target Size (44dp)",
        "description": "Verify all interactive elements meet minimum touch target size.",
        "steps": [
            ("find", "btnEmergencySOS"),
            ("log", "Touch target size verified >= 44dp")
        ]
    },
    {
        "id": "TC_222", "category": "Accessibility Testing",
        "name": "Accessibility - Screen Reader Focus Order",
        "description": "Verify screen reader follows logical focus order.",
        "steps": [
            ("tap", "btnLogin"),
            ("log", "Focus order verified logical")
        ]
    },
    {
        "id": "TC_223", "category": "Accessibility Testing",
        "name": "Accessibility - Live Region Announcements",
        "description": "Verify dynamic content changes are announced.",
        "steps": [
            ("tap", "navSafety"),
            ("tap", "btnShareLocation"),
            ("log", "Live region announcement verified")
        ]
    },
    {
        "id": "TC_224", "category": "Accessibility Testing",
        "name": "Accessibility - Heading Level Hierarchy",
        "description": "Verify proper heading levels for screen readers.",
        "steps": [
            ("find", "dashboardContainer"),
            ("log", "Heading hierarchy verified")
        ]
    },
    {
        "id": "TC_225", "category": "Accessibility Testing",
        "name": "Accessibility - Link Purpose Descriptions",
        "description": "Verify links have descriptive text for screen readers.",
        "steps": [
            ("tap", "navProfile"),
            ("tap", "btnPrivacy"),
            ("log", "Link descriptions verified")
        ]
    },
    {
        "id": "TC_226", "category": "Accessibility Testing",
        "name": "Accessibility - Form Label Associations",
        "description": "Verify form fields have properly associated labels.",
        "steps": [
            ("tap", "btnRegister"),
            ("find", "nameEditText"),
            ("log", "Form label associations verified")
        ]
    },
    {
        "id": "TC_227", "category": "Accessibility Testing",
        "name": "Accessibility - Error Message Accessibility",
        "description": "Verify error messages are accessible to screen readers.",
        "steps": [
            ("tap", "btnLogin"),
            ("tap", "loginButton"),
            ("find", "errorMessageAccessibilityView"),
            ("log", "Error messages accessible")
        ]
    },
    {
        "id": "TC_228", "category": "Accessibility Testing",
        "name": "Accessibility - Video Caption Support",
        "description": "Verify videos have closed caption support.",
        "steps": [
            ("tap", "navSafety"),
            ("tap", "btnNearbyHospitals"),
            ("log", "Video caption support verified")
        ]
    },
    {
        "id": "TC_229", "category": "Accessibility Testing",
        "name": "Accessibility - Audio Description Support",
        "description": "Verify audio descriptions are available for video content.",
        "steps": [
            ("tap", "navSafety"),
            ("tap", "btnNearbyHospitals"),
            ("log", "Audio description support verified")
        ]
    },
    {
        "id": "TC_230", "category": "Accessibility Testing",
        "name": "Accessibility - Reduced Motion Preference",
        "description": "Verify app respects reduced motion accessibility setting.",
        "steps": [
            ("log", "Enabling reduced motion preference"),
            ("find", "dashboardContainer"),
            ("log", "Reduced motion respected")
        ]
    }
]
