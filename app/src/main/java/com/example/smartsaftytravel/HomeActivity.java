package com.example.smartsaftytravel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.chip.Chip;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashSet;
import java.util.Set;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_home);

        // Dashboard Quick Actions
        findViewById(R.id.planTripCard).setOnClickListener(v -> startActivity(new Intent(this, SelectDestinationActivity.class)));
        
        // Link Safety Activity
        findViewById(R.id.safetyCheckCard).setOnClickListener(v -> startActivity(new Intent(this, SafetyActivity.class)));

        // Link Report Incident Card
        View reportIncidentCard = findViewById(R.id.reportIncidentCard);
        if (reportIncidentCard != null) {
            reportIncidentCard.setOnClickListener(v -> {
                Intent intent = new Intent(this, ReportIncidentActivity.class);
                SharedPreferences sharedPref = getSharedPreferences("SmartTravelPrefs", Context.MODE_PRIVATE);
                intent.putExtra("destination", sharedPref.getString("lastDest", ""));
                startActivity(intent);
            });
        }

        // Link Report Incident from Notification/Alert Icon
        View alertIcon = findViewById(R.id.btnNotifications);
        if (alertIcon != null) {
            alertIcon.setOnClickListener(v -> {
                Intent intent = new Intent(this, ReportIncidentActivity.class);
                SharedPreferences sharedPref = getSharedPreferences("SmartTravelPrefs", Context.MODE_PRIVATE);
                intent.putExtra("destination", sharedPref.getString("lastDest", ""));
                startActivity(intent);
            });
        }

        // SOS FAB - Simplified to expression lambda
        View sosFab = findViewById(R.id.sosFab);
        if (sosFab != null) {
            sosFab.setOnClickListener(v -> startActivity(new Intent(this, SosCountdownActivity.class)));
        }

        // Bottom Navigation Connections
        findViewById(R.id.navHome).setOnClickListener(v -> {}); 
        findViewById(R.id.navSafety).setOnClickListener(v -> startActivity(new Intent(this, SafetyActivity.class)));
        findViewById(R.id.navChat).setOnClickListener(v -> startActivity(new Intent(this, ChatActivity.class)));
        findViewById(R.id.navProfile).setOnClickListener(v -> startActivity(new Intent(this, ProfileActivity.class)));

        // Saved Trip Action Buttons
        View btnDelete = findViewById(R.id.btnDeleteSavedTrip);
        if (btnDelete != null) btnDelete.setOnClickListener(v -> showDeleteConfirmation());
        
        View btnEdit = findViewById(R.id.btnEditSavedTrip);
        if (btnEdit != null) btnEdit.setOnClickListener(v -> editSavedTrip());

        loadAllData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAuth.getCurrentUser() != null) {
            loadAllData();
        }
    }

    private void loadAllData() {
        checkAndShowSavedTrip();
        syncTripFromFirebase();
        setupTrendingDestinations();
        setupRecommendedDestinations();
        setupRecentlyViewed();
    }

    private void syncTripFromFirebase() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) return;

        db.collection("trips").document(user.getUid()).get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String dest = documentSnapshot.getString("destination");
                        String dates = documentSnapshot.getString("dates");
                        String budget = documentSnapshot.getString("budget");
                        String duration = documentSnapshot.getString("duration");

                        SharedPreferences sharedPref = getSharedPreferences("SmartTravelPrefs", Context.MODE_PRIVATE);
                        sharedPref.edit()
                                .putBoolean("hasSavedTrip", true)
                                .putString("lastDest", dest)
                                .putString("lastDates", dates)
                                .putString("lastBudget", budget)
                                .putString("lastDuration", duration)
                                .apply();

                        checkAndShowSavedTrip();
                    }
                });
    }

    private void setupRecentlyViewed() {
        SharedPreferences prefs = getSharedPreferences("SmartTravelPrefs", Context.MODE_PRIVATE);
        Set<String> recent = prefs.getStringSet("recentSearches", new HashSet<>());
        
        LinearLayout recentContainer = findViewById(R.id.recentHomeList);
        View recentSection = findViewById(R.id.recentSection);

        if (recentContainer != null && recentSection != null) {
            if (recent.isEmpty()) {
                recentSection.setVisibility(View.GONE);
            } else {
                recentSection.setVisibility(View.VISIBLE);
                recentContainer.removeAllViews();
                for (String name : recent) {
                    Chip chip = (Chip) LayoutInflater.from(this).inflate(R.layout.item_chip_recent, recentContainer, false);
                    chip.setText(name);
                    chip.setOnClickListener(v -> {
                        Intent intent = new Intent(this, SelectDestinationActivity.class);
                        intent.putExtra("editDestination", name);
                        startActivity(intent);
                    });
                    recentContainer.addView(chip);
                }
            }
        }
    }

    private void checkAndShowSavedTrip() {
        SharedPreferences sharedPref = getSharedPreferences("SmartTravelPrefs", Context.MODE_PRIVATE);
        boolean hasSavedTrip = sharedPref.getBoolean("hasSavedTrip", false);

        LinearLayout savedTripSection = findViewById(R.id.savedTripSection);
        if (savedTripSection != null) {
            if (hasSavedTrip) {
                savedTripSection.setVisibility(View.VISIBLE);
                
                TextView tvDest = findViewById(R.id.homeSavedDest);
                TextView tvDates = findViewById(R.id.homeSavedDates);
                TextView tvBudget = findViewById(R.id.homeSavedBudget);

                String dest = sharedPref.getString("lastDest", "Trip Plan");
                String dates = sharedPref.getString("lastDates", "Dates not set");
                String budget = sharedPref.getString("lastBudget", "N/A");

                if (tvDest != null) tvDest.setText(dest);
                if (tvDates != null) tvDates.setText(dates);
                if (tvBudget != null) tvBudget.setText(getString(R.string.budget_plan_format, budget));
                
                findViewById(R.id.savedTripCard).setOnClickListener(v -> {
                    Intent intent = new Intent(this, SaveTripPlanActivity.class);
                    intent.putExtra("destination", dest);
                    if (dates.contains(" - ")) {
                        intent.putExtra("startDate", dates.split(" - ")[0]);
                        intent.putExtra("endDate", dates.split(" - ")[1]);
                    }
                    intent.putExtra("budget", budget);
                    startActivity(intent);
                });
            } else {
                savedTripSection.setVisibility(View.GONE);
            }
        }
    }

    private void showDeleteConfirmation() {
        new AlertDialog.Builder(this)
                .setTitle("Delete Trip Plan")
                .setMessage("Are you sure you want to delete this trip from cloud?")
                .setPositiveButton("Delete", (dialog, which) -> {
                    SharedPreferences sharedPref = getSharedPreferences("SmartTravelPrefs", Context.MODE_PRIVATE);
                    sharedPref.edit().remove("hasSavedTrip").apply();
                    
                    FirebaseUser user = mAuth.getCurrentUser();
                    if (user != null) {
                        db.collection("trips").document(user.getUid()).delete()
                                .addOnSuccessListener(aVoid -> {
                                    Toast.makeText(this, "Trip plan deleted from cloud", Toast.LENGTH_SHORT).show();
                                    loadAllData();
                                });
                    } else {
                        loadAllData();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void editSavedTrip() {
        SharedPreferences sharedPref = getSharedPreferences("SmartTravelPrefs", Context.MODE_PRIVATE);
        String dest = sharedPref.getString("lastDest", "");
        Intent intent = new Intent(this, SelectDestinationActivity.class);
        intent.putExtra("editDestination", dest);
        startActivity(intent);
    }

    private void setupTrendingDestinations() {
        LinearLayout trendingList = findViewById(R.id.trendingList);
        if (trendingList == null) return;

        String[][] trending = {
            {"Paris", "France", "🇫🇷"},
            {"Goa", "India", "🇮🇳"},
            {"Dubai", "UAE", "🇦🇪"},
            {"Tokyo", "Japan", "🇯🇵"}
        };

        trendingList.removeAllViews();
        for (String[] dest : trending) {
            View itemView = LayoutInflater.from(this).inflate(R.layout.item_trending, trendingList, false);
            TextView name = itemView.findViewById(R.id.trendingName);
            TextView country = itemView.findViewById(R.id.trendingCountry);
            TextView flag = itemView.findViewById(R.id.trendingFlag);

            if (name != null) name.setText(dest[0]);
            if (country != null) country.setText(dest[1]);
            if (flag != null) flag.setText(dest[2]);

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(this, SelectBudgetActivity.class);
                intent.putExtra("destination", dest[0]);
                intent.putExtra("country", dest[1]);
                startActivity(intent);
            });
            trendingList.addView(itemView);
        }
    }

    private void setupRecommendedDestinations() {
        LinearLayout recommendedList = findViewById(R.id.recommendedList);
        if (recommendedList == null) return;

        String[][] recommended = {
            {"Manali", "Himachal, India", "🇮🇳", "Mountain View • Adventure"},
            {"Bali", "Indonesia", "🇮🇩", "Beaches • Culture • Nature"},
            {"Kyoto", "Japan", "🇯🇵", "Temples • History • Food"}
        };

        recommendedList.removeAllViews();
        for (String[] dest : recommended) {
            View itemView = LayoutInflater.from(this).inflate(R.layout.item_destination, recommendedList, false);
            TextView name = itemView.findViewById(R.id.destName);
            TextView country = itemView.findViewById(R.id.destCountry);
            TextView flag = itemView.findViewById(R.id.destFlag);
            TextView tag = itemView.findViewById(R.id.destTag);

            if (name != null) name.setText(dest[0]);
            if (country != null) country.setText(dest[1]);
            if (flag != null) flag.setText(dest[2]);
            if (tag != null) tag.setText(dest[3]);

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(this, SelectBudgetActivity.class);
                intent.putExtra("destination", dest[0]);
                intent.putExtra("country", dest[1]);
                startActivity(intent);
            });
            recommendedList.addView(itemView);
        }
    }
}
