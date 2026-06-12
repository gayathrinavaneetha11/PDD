package com.example.smartsaftytravel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SaveTripPlanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_trip_plan);

        // Retrieve data from intent
        Intent intentData = getIntent();
        final String destination = intentData.getStringExtra("destination");
        final String budget = intentData.getStringExtra("budget");
        final String startDate = intentData.getStringExtra("startDate");
        final String endDate = intentData.getStringExtra("endDate");
        final int duration = intentData.getIntExtra("duration", 0);

        // Update Summary UI
        TextView tvDest = findViewById(R.id.summaryDestination);
        TextView tvDates = findViewById(R.id.summaryDates);
        TextView tvDuration = findViewById(R.id.summaryDuration);
        TextView tvBudget = findViewById(R.id.summaryBudget);
        TextView tvBudgetType = findViewById(R.id.summaryBudgetType);

        if (tvDest != null) {
            tvDest.setText(destination != null ? destination : getString(R.string.not_available));
        }
        
        if (tvDates != null) {
            String start = startDate != null ? startDate : "";
            String end = endDate != null ? endDate : "";
            tvDates.setText(getString(R.string.date_range_format, start, end));
        }
        
        if (tvDuration != null) {
            tvDuration.setText(getString(R.string.trip_duration_format, duration));
        }
        
        // Dynamic Budget Text
        final String budgetDisplay = (budget != null && !budget.isEmpty()) ? budget : getString(R.string.not_available);
        if (tvBudget != null) {
            tvBudget.setText(budgetDisplay);
        }
        if (tvBudgetType != null) {
            tvBudgetType.setText(getString(R.string.budget_plan_format, budgetDisplay));
        }

        MaterialButton btnBackToHome = findViewById(R.id.btnBackToHome);
        if (btnBackToHome != null) {
            btnBackToHome.setOnClickListener(v -> {
                saveTripToPreferences(destination, startDate, endDate, duration, budgetDisplay);
                saveTripToFirebase(destination, startDate, endDate, duration, budgetDisplay);
                
                Intent homeIntent = new Intent(this, HomeActivity.class);
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(homeIntent);
                finish();
            });
        }

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
        
        findViewById(R.id.btnDownload).setOnClickListener(v -> 
            Toast.makeText(this, R.string.downloading_pdf, Toast.LENGTH_SHORT).show());
            
        findViewById(R.id.btnShare).setOnClickListener(v -> 
            Toast.makeText(this, R.string.opening_share, Toast.LENGTH_SHORT).show());
    }

    private void saveTripToPreferences(String dest, String start, String end, int dur, String budget) {
        SharedPreferences sharedPref = getSharedPreferences("SmartTravelPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("hasSavedTrip", true);
        editor.putString("lastDest", dest != null ? dest : "");
        editor.putString("lastDates", getString(R.string.date_range_format, start != null ? start : "", end != null ? end : ""));
        editor.putString("lastDuration", getString(R.string.duration_days_only, dur));
        editor.putString("lastBudget", budget != null ? budget : "");
        editor.apply();
    }

    private void saveTripToFirebase(String dest, String start, String end, int dur, String budget) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) return;

        Map<String, Object> trip = new HashMap<>();
        trip.put("destination", dest != null ? dest : "");
        trip.put("dates", getString(R.string.date_range_format, start != null ? start : "", end != null ? end : ""));
        trip.put("duration", getString(R.string.duration_days_only, dur));
        trip.put("budget", budget != null ? budget : "");
        trip.put("userId", currentUser.getUid());
        trip.put("timestamp", Timestamp.now());

        FirebaseFirestore.getInstance().collection("trips").document(currentUser.getUid())
                .set(trip)
                .addOnSuccessListener(aVoid -> Toast.makeText(this, R.string.trip_synced, Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> {
                    String message = e.getMessage();
                    String errorMsg = getString(R.string.sync_failed, message != null ? message : "");
                    Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
                });
    }
}
