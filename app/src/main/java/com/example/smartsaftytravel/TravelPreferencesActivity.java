package com.example.smartsaftytravel;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class TravelPreferencesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_preferences);
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
        findViewById(R.id.btnSave).setOnClickListener(v -> finish());
    }
}
