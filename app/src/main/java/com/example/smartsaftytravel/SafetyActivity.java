package com.example.smartsaftytravel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SafetyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety);

        // Get saved destination for dynamic UI
        SharedPreferences sharedPref = getSharedPreferences("SmartTravelPrefs", Context.MODE_PRIVATE);
        String destination = sharedPref.getString("lastDest", "Manhattan, NY");

        // Header Actions
        View btnBack = findViewById(R.id.btnBack);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        View btnNotifications = findViewById(R.id.btnNotifications);
        if (btnNotifications != null) {
            btnNotifications.setOnClickListener(v -> 
                Toast.makeText(this, "No new safety alerts for " + destination, Toast.LENGTH_SHORT).show());
        }

        // Update Risk SubText with destination
        TextView riskSubText = findViewById(R.id.riskSubText);
        if (riskSubText != null) {
            riskSubText.setText(getString(R.string.risk_area_format, destination));
        }

        // Update Dynamic Weather (Mock data)
        TextView weatherText = findViewById(R.id.weatherText);
        if (weatherText != null) {
            weatherText.setText(getString(R.string.mock_weather_format, destination));
        }

        View weatherCard = findViewById(R.id.weatherCard);
        if (weatherCard != null) {
            weatherCard.setOnClickListener(v -> 
                Toast.makeText(this, "Checking detailed forecast for " + destination + "...", Toast.LENGTH_SHORT).show());
        }

        // AI Risk Analysis Click Handlers
        View crimeRateCard = findViewById(R.id.crimeRateCard);
        if (crimeRateCard != null) {
            crimeRateCard.setOnClickListener(v -> 
                Toast.makeText(this, "Crime rate in " + destination + " is 15% lower than national average.", Toast.LENGTH_LONG).show());
        }

        View crowdDensityCard = findViewById(R.id.crowdDensityCard);
        if (crowdDensityCard != null) {
            crowdDensityCard.setOnClickListener(v -> 
                Toast.makeText(this, "Moderate crowd levels detected near main attractions.", Toast.LENGTH_SHORT).show());
        }

        // Emergency Services Logic
        View btnHospitals = findViewById(R.id.btnNearbyHospitals);
        if (btnHospitals != null) {
            btnHospitals.setOnClickListener(v -> {
                Toast.makeText(this, R.string.opening_maps_hospitals, Toast.LENGTH_SHORT).show();
                openMapsSearch("hospitals near " + destination);
            });
        }

        View btnPolice = findViewById(R.id.btnNearbyPolice);
        if (btnPolice != null) {
            btnPolice.setOnClickListener(v -> {
                Toast.makeText(this, R.string.opening_maps_police, Toast.LENGTH_SHORT).show();
                openMapsSearch("police station near " + destination);
            });
        }

        // Link to Report Incident
        View btnReportIncident = findViewById(R.id.btnReportIncident);
        if (btnReportIncident != null) {
            btnReportIncident.setOnClickListener(v -> {
                Intent intent = new Intent(this, ReportIncidentActivity.class);
                intent.putExtra("destination", destination);
                startActivity(intent);
            });
        }

        View btnEmergencySOS = findViewById(R.id.btnEmergencySOS);
        if (btnEmergencySOS != null) {
            btnEmergencySOS.setOnClickListener(v -> startActivity(new Intent(this, SosCountdownActivity.class)));
        }

        // Bottom Navigation Connections
        setupBottomNavigation();
    }

    private void openMapsSearch(String query) {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + query);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            // Fallback: Remove package restriction and try generic intent
            mapIntent.setPackage(null);
            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(mapIntent);
            } else {
                Toast.makeText(this, R.string.maps_not_found, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setupBottomNavigation() {
        View navHome = findViewById(R.id.navHome);
        if (navHome != null) {
            navHome.setOnClickListener(v -> {
                Intent intent = new Intent(this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            });
        }

        View navMap = findViewById(R.id.navMap);
        if (navMap != null) {
            navMap.setOnClickListener(v -> {
                startActivity(new Intent(this, LiveMapActivity.class));
                finish();
            });
        }

        // Safety is current activity (navSafety)
        
        View navChat = findViewById(R.id.navChat);
        if (navChat != null) {
            navChat.setOnClickListener(v -> {
                startActivity(new Intent(this, ChatActivity.class));
                finish();
            });
        }

        View navProfile = findViewById(R.id.navProfile);
        if (navProfile != null) {
            navProfile.setOnClickListener(v -> {
                startActivity(new Intent(this, ProfileActivity.class));
                finish();
            });
        }
    }
}
