package com.example.smartsaftytravel;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class LiveMapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_map);

        // Map Specific Actions
        findViewById(R.id.btnTrack).setOnClickListener(v -> startActivity(new Intent(this, LiveTrackingActivity.class)));
        findViewById(R.id.btnNavigate).setOnClickListener(v -> startActivity(new Intent(this, NavigationActivity.class)));
        findViewById(R.id.btnShare).setOnClickListener(v -> startActivity(new Intent(this, LocationSharingActivity.class)));

        // Bottom Navigation
        findViewById(R.id.navHome).setOnClickListener(v -> {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });
        findViewById(R.id.navMap).setOnClickListener(v -> {}); // Already here
        findViewById(R.id.navSafety).setOnClickListener(v -> startActivity(new Intent(this, SafetyActivity.class)));
        findViewById(R.id.navChat).setOnClickListener(v -> startActivity(new Intent(this, ChatActivity.class)));
        findViewById(R.id.navProfile).setOnClickListener(v -> startActivity(new Intent(this, ProfileActivity.class)));
    }
}
