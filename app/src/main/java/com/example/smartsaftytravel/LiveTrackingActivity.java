package com.example.smartsaftytravel;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class LiveTrackingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_tracking);
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
        findViewById(R.id.btnStopTracking).setOnClickListener(v -> finish());
    }
}
