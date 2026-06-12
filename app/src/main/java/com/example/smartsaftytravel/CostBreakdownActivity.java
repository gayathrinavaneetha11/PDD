package com.example.smartsaftytravel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import java.util.Locale;

public class CostBreakdownActivity extends AppCompatActivity {

    private String currency, budget, destination;
    private int duration, travelers;
    private double baseRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost_breakdown);

        // Retrieve trip data
        currency = getIntent().getStringExtra("currency");
        if (currency == null) currency = "₹";
        
        budget = getIntent().getStringExtra("budget");
        duration = getIntent().getIntExtra("duration", 5);
        travelers = getIntent().getIntExtra("travelers", 1);
        destination = getIntent().getStringExtra("destination");
        
        String country = getIntent().getStringExtra("country");
        setupBaseRate(country);

        calculateAndDisplayCosts();

        MaterialButton btnSaveTrip = findViewById(R.id.btnSaveTrip);
        if (btnSaveTrip != null) {
            btnSaveTrip.setOnClickListener(v -> {
                Intent intent = new Intent(this, SaveTripPlanActivity.class);
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    intent.putExtras(extras);
                }
                startActivity(intent);
            });
        }

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    private void setupBaseRate(String country) {
        if (country == null) country = "India";
        switch (country) {
            case "India": baseRate = 10000; break;
            case "UAE": baseRate = 1750; break;
            case "Japan": baseRate = 220000; break;
            case "Singapore": baseRate = 2000; break;
            case "USA":
            default: baseRate = 1500; break;
        }
    }

    private void calculateAndDisplayCosts() {
        double multiplier = 2.0; 
        String low = getString(R.string.low_budget);
        String high = getString(R.string.high_budget);
        
        if (low.equals(budget)) multiplier = 0.8;
        else if (high.equals(budget)) multiplier = 4.5;

        long totalBase = (long) (baseRate * multiplier * duration * travelers);
        
        // Expense distribution values (Percentages)
        long hotel = (long) (totalBase * 0.45);
        long food = (long) (totalBase * 0.20);
        long transport = (long) (totalBase * 0.15);
        long attraction = (long) (totalBase * 0.10);
        long emergency = (long) (totalBase * 0.10);
        
        TextView totalVal = findViewById(R.id.totalCostValue);
        if (totalVal != null) totalVal.setText(String.format(Locale.getDefault(), "%s %d", currency, totalBase));
        
        TextView summaryLabel = findViewById(R.id.tripSummaryLabel);
        if (summaryLabel != null) {
            summaryLabel.setText(String.format(Locale.getDefault(), "%d Days in %s for %d Travelers", duration, destination, travelers));
        }

        // Updating the Visualization (Pie Chart Simulation)
        CircularProgressIndicator chart = findViewById(R.id.chartIndicator);
        if (chart != null) {
            // Animating to show primary expense (Hotel)
            chart.setProgress(45); 
        }

        // Update Itemized Breakdown Rows
        updateRow(R.id.rowHotel, "🏨 Hotel & Stay", hotel, 45);
        updateRow(R.id.rowFood, "🍴 Food & Dining", food, 20);
        updateRow(R.id.rowTransport, "🚕 Local Transport", transport, 15);
        updateRow(R.id.rowAttraction, "🎟️ Sightseeing", attraction, 10);
        updateRow(R.id.rowEmergency, "🆘 Emergency Reserve", emergency, 10);

        TextView budgetTip = findViewById(R.id.budgetTipText);
        if (budgetTip != null) {
            String tip = "AI Suggestion: " + (destination != null ? destination : "your trip") + " is best explored using " + (low.equals(budget) ? "public transport" : "private chauffeurs") + " to maximize your " + (budget != null ? budget : "plan") + ".";
            budgetTip.setText(tip);
        }
    }

    private void updateRow(int rowId, String label, long cost, int progress) {
        View row = findViewById(rowId);
        if (row != null) {
            TextView lbl = row.findViewById(R.id.costLabel);
            TextView val = row.findViewById(R.id.costValue);
            LinearProgressIndicator lp = row.findViewById(R.id.costProgress);
            
            if (lbl != null) lbl.setText(label);
            if (val != null) val.setText(String.format(Locale.getDefault(), "%s %d", currency, cost));
            if (lp != null) lp.setProgress(progress);
        }
    }
}
