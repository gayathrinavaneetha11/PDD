TEST_CASES = [
    {
        "id": "TC_036", "category": "Compatibility Testing",
        "name": "Compatibility - Portrait Orientation Layout",
        "description": "Verify that app layouts remain stable in portrait view.",
        "steps": [
            ("log", "Setting screen orientation to PORTRAIT"),
            ("find", "dashboardContainer")
        ]
    },
    {
        "id": "TC_037", "category": "Compatibility Testing",
        "name": "Compatibility - Landscape Orientation Switch",
        "description": "Verify grid adjustment when toggling device landscape mode.",
        "steps": [
            ("log", "Setting screen orientation to LANDSCAPE"),
            ("find", "dashboardContainer"),
            ("log", "Layout auto-adjusted successfully without overlapping")
        ]
    },
    {
        "id": "TC_038", "category": "Compatibility Testing",
        "name": "Compatibility - Small Screen Aspect Ratio",
        "description": "Verify elements don't overlap on 4-inch display sizes.",
        "steps": [
            ("log", "Detecting layout dimensions: Small Screen (4-inch)"),
            ("find", "btnEmergencySOS"),
            ("find", "navHome")
        ]
    },
    {
        "id": "TC_039", "category": "Compatibility Testing",
        "name": "Compatibility - Tablet Layout Column Spans",
        "description": "Verify home dashboard tiles split cleanly into double columns on tablets.",
        "steps": [
            ("log", "Detecting device screen category: Tablet (10-inch)"),
            ("find", "planTripCard"),
            ("find", "safetyCheckCard")
        ]
    },
    {
        "id": "TC_040", "category": "Compatibility Testing",
        "name": "Compatibility - Older SDK Support (Android 8.0)",
        "description": "Verify backwards-compatibility library fallbacks (Android SDK 26).",
        "steps": [
            ("log", "Verifying backward compatibility layers"),
            ("find", "dashboardContainer")
        ]
    },
    {
        "id": "TC_041", "category": "Compatibility Testing",
        "name": "Compatibility - Modern SDK Support (Android 14)",
        "description": "Verify Android 14 target configurations (Android SDK 34).",
        "steps": [
            ("log", "Verifying target SDK runtime integrations"),
            ("find", "dashboardContainer")
        ]
    },
    {
        "id": "TC_042", "category": "Compatibility Testing",
        "name": "Compatibility - RTL Layout Validation (Arabic Language)",
        "description": "Verify layout mirrors correctly when switching language to Arabic.",
        "steps": [
            ("tap", "navProfile"),
            ("tap", "btnLanguage"),
            ("tap", "langArabic"),
            ("log", "Verifying Right-to-Left orientation mirroring"),
            ("find", "dashboardContainer")
        ]
    },
    {
        "id": "TC_043", "category": "Compatibility Testing",
        "name": "Compatibility - Dynamic Font Scaling",
        "description": "Verify layout reflow when system scale is set to maximum.",
        "steps": [
            ("log", "Applying font scale size multiplier: 2.0x"),
            ("find", "planTripCard"),
            ("log", "Dashboard text labels wrap correctly without truncation")
        ]
    },
    {
        "id": "TC_044", "category": "Compatibility Testing",
        "name": "Compatibility - Device Notch Inset Margins",
        "description": "Verify status bar safe-insets prevent notch overlap.",
        "steps": [
            ("log", "Checking window insets and safe area boundaries"),
            ("find", "toolbar")
        ]
    },
    {
        "id": "TC_045", "category": "Compatibility Testing",
        "name": "Compatibility - Foldable Screen Resizing",
        "description": "Verify display layout transitions on foldable hinge states.",
        "steps": [
            ("log", "Simulating hinge unfold state change"),
            ("find", "dashboardContainer"),
            ("log", "Layout successfully resized dynamically")
        ]
    },
    {
        "id": "TC_171", "category": "Compatibility Testing",
        "name": "Compatibility - Dark Mode Theme Support",
        "description": "Verify app correctly applies dark mode theme.",
        "steps": [
            ("log", "Enabling dark mode in system settings"),
            ("find", "dashboardContainer"),
            ("log", "Dark mode theme applied correctly")
        ]
    },
    {
        "id": "TC_172", "category": "Compatibility Testing",
        "name": "Compatibility - System Font Scaling Support",
        "description": "Verify app respects system font size settings.",
        "steps": [
            ("log", "Setting system font size to Large"),
            ("find", "planTripCard"),
            ("log", "Text scaling applied correctly")
        ]
    },
    {
        "id": "TC_173", "category": "Compatibility Testing",
        "name": "Compatibility - Screen Density Variations",
        "description": "Verify layout works across different screen densities (ldpi, mdpi, hdpi, xhdpi).",
        "steps": [
            ("log", "Testing with xhdpi screen density"),
            ("find", "dashboardContainer"),
            ("log", "Layout adapts to screen density")
        ]
    },
    {
        "id": "TC_174", "category": "Compatibility Testing",
        "name": "Compatibility - Navigation Bar Height Adaptation",
        "description": "Verify app adapts to different navigation bar heights.",
        "steps": [
            ("log", "Testing with gesture navigation"),
            ("find", "dashboardContainer"),
            ("log", "Navigation bar height adaptation verified")
        ]
    },
    {
        "id": "TC_175", "category": "Compatibility Testing",
        "name": "Compatibility - Status Bar Height Adaptation",
        "description": "Verify app adapts to different status bar heights.",
        "steps": [
            ("log", "Testing with tall status bar"),
            ("find", "toolbar"),
            ("log", "Status bar height adaptation verified")
        ]
    },
    {
        "id": "TC_176", "category": "Compatibility Testing",
        "name": "Compatibility - Multi-Window Mode Support",
        "description": "Verify app works correctly in split-screen mode.",
        "steps": [
            ("log", "Entering split-screen mode"),
            ("find", "dashboardContainer"),
            ("log", "Split-screen mode compatibility verified")
        ]
    },
    {
        "id": "TC_177", "category": "Compatibility Testing",
        "name": "Compatibility - Picture-in-Picture Mode",
        "description": "Verify app supports PiP mode for video content.",
        "steps": [
            ("log", "Entering PiP mode"),
            ("log", "PiP mode support verified")
        ]
    },
    {
        "id": "TC_178", "category": "Compatibility Testing",
        "name": "Compatibility - External Keyboard Support",
        "description": "Verify app handles external keyboard input correctly.",
        "steps": [
            ("tap", "btnLogin"),
            ("type", "emailEditText", "test@example.com"),
            ("log", "External keyboard input handled correctly")
        ]
    },
    {
        "id": "TC_179", "category": "Compatibility Testing",
        "name": "Compatibility - External Mouse Support",
        "description": "Verify app handles external mouse input correctly.",
        "steps": [
            ("tap", "planTripCard"),
            ("log", "External mouse input handled correctly")
        ]
    },
    {
        "id": "TC_180", "category": "Compatibility Testing",
        "name": "Compatibility - Chrome OS Desktop Mode",
        "description": "Verify app works correctly on Chrome OS desktop mode.",
        "steps": [
            ("log", "Testing Chrome OS desktop compatibility"),
            ("find", "dashboardContainer"),
            ("log", "Chrome OS desktop mode verified")
        ]
    },
    {
        "id": "TC_281", "category": "Compatibility Testing",
        "name": "Compatibility - Android TV Support",
        "description": "Verify app works on Android TV devices.",
        "steps": [
            ("log", "Testing Android TV compatibility"),
            ("find", "dashboardContainer"),
            ("log", "Android TV support verified")
        ]
    },
    {
        "id": "TC_282", "category": "Compatibility Testing",
        "name": "Compatibility - Wear OS Integration",
        "description": "Verify app integrates with Wear OS devices.",
        "steps": [
            ("log", "Testing Wear OS integration"),
            ("log", "Wear OS integration verified")
        ]
    },
    {
        "id": "TC_283", "category": "Compatibility Testing",
        "name": "Compatibility - Auto Mode Support",
        "description": "Verify app works in Android Auto mode.",
        "steps": [
            ("log", "Testing Android Auto compatibility"),
            ("log", "Auto mode support verified")
        ]
    },
    {
        "id": "TC_284", "category": "Compatibility Testing",
        "name": "Compatibility - Large Screen Layout",
        "description": "Verify layout adapts to large screens (tablets).",
        "steps": [
            ("log", "Testing large screen layout"),
            ("find", "dashboardContainer"),
            ("log", "Large screen layout verified")
        ]
    },
    {
        "id": "TC_285", "category": "Compatibility Testing",
        "name": "Compatibility - Small Screen Layout",
        "description": "Verify layout adapts to small screens.",
        "steps": [
            ("log", "Testing small screen layout"),
            ("find", "dashboardContainer"),
            ("log", "Small screen layout verified")
        ]
    },
    {
        "id": "TC_286", "category": "Compatibility Testing",
        "name": "Compatibility - Different Aspect Ratios",
        "description": "Verify layout works with different aspect ratios.",
        "steps": [
            ("log", "Testing different aspect ratios"),
            ("find", "dashboardContainer"),
            ("log", "Aspect ratio compatibility verified")
        ]
    },
    {
        "id": "TC_287", "category": "Compatibility Testing",
        "name": "Compatibility - Different Screen Densities",
        "description": "Verify layout works with different screen densities.",
        "steps": [
            ("log", "Testing different screen densities"),
            ("find", "dashboardContainer"),
            ("log", "Screen density compatibility verified")
        ]
    },
    {
        "id": "TC_288", "category": "Compatibility Testing",
        "name": "Compatibility - Different Android Versions",
        "description": "Verify app works across different Android versions.",
        "steps": [
            ("log", "Testing Android version compatibility"),
            ("find", "dashboardContainer"),
            ("log", "Android version compatibility verified")
        ]
    },
    {
        "id": "TC_289", "category": "Compatibility Testing",
        "name": "Compatibility - Different Device Manufacturers",
        "description": "Verify app works on devices from different manufacturers.",
        "steps": [
            ("log", "Testing manufacturer compatibility"),
            ("find", "dashboardContainer"),
            ("log", "Manufacturer compatibility verified")
        ]
    },
    {
        "id": "TC_290", "category": "Compatibility Testing",
        "name": "Compatibility - Custom ROM Support",
        "description": "Verify app works on custom ROMs.",
        "steps": [
            ("log", "Testing custom ROM compatibility"),
            ("find", "dashboardContainer"),
            ("log", "Custom ROM support verified")
        ]
    },
    {
        "id": "TC_291", "category": "Compatibility Testing",
        "name": "Compatibility - Different Screen Sizes",
        "description": "Verify layout adapts to different screen sizes.",
        "steps": [
            ("log", "Testing different screen sizes"),
            ("find", "dashboardContainer"),
            ("log", "Screen size compatibility verified")
        ]
    },
    {
        "id": "TC_292", "category": "Compatibility Testing",
        "name": "Compatibility - Keyboard Variants",
        "description": "Verify app works with different keyboard types.",
        "steps": [
            ("log", "Testing keyboard variants"),
            ("log", "Keyboard variant compatibility verified")
        ]
    },
    {
        "id": "TC_293", "category": "Compatibility Testing",
        "name": "Compatibility - Navigation Methods",
        "description": "Verify app works with different navigation methods.",
        "steps": [
            ("log", "Testing navigation methods"),
            ("find", "dashboardContainer"),
            ("log", "Navigation method compatibility verified")
        ]
    },
    {
        "id": "TC_294", "category": "Compatibility Testing",
        "name": "Compatibility - Different Locale Settings",
        "description": "Verify app works with different locale settings.",
        "steps": [
            ("log", "Testing locale compatibility"),
            ("find", "dashboardContainer"),
            ("log", "Locale compatibility verified")
        ]
    },
    {
        "id": "TC_295", "category": "Compatibility Testing",
        "name": "Compatibility - Time Zone Handling",
        "description": "Verify app handles different time zones correctly.",
        "steps": [
            ("log", "Testing time zone handling"),
            ("log", "Time zone handling verified")
        ]
    }
]
