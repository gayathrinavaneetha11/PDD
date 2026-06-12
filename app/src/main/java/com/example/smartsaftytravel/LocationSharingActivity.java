package com.example.smartsaftytravel;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class LocationSharingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_sharing);
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }
}
