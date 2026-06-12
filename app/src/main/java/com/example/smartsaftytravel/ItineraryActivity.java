package com.example.smartsaftytravel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class ItineraryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary);

        // Get Data from Intent
        String destination = getIntent().getStringExtra("destination");
        String budget = getIntent().getStringExtra("budget");
        String currency = getIntent().getStringExtra("currency");
        int duration = getIntent().getIntExtra("duration", 1);

        // Update Header
        TextView tripTitle = findViewById(R.id.tripTitle);
        if (tripTitle != null) {
            tripTitle.setText(String.format(Locale.getDefault(), "%d-Day %s Trip", duration, destination));
        }

        TextView planBadge = findViewById(R.id.planBadge);
        if (planBadge != null) {
            planBadge.setText(String.format(Locale.getDefault(), "AI Generated Plan • %s Budget", budget));
        }

        // Generate Days List dynamically
        LinearLayout daysList = findViewById(R.id.daysList);
        String[] genericTitles = {
            "Arrival & City Exploration",
            "Local Culture & Food Tour",
            "Hidden Gems & Sightseeing",
            "Nature & Relaxation",
            "Adventure Activities",
            "Shopping & Local Markets",
            "Departure & Last Souvenirs"
        };

        if (daysList != null) {
            daysList.removeAllViews();
            for (int i = 0; i < duration; i++) {
                View itemView = LayoutInflater.from(this).inflate(R.layout.item_day, daysList, false);
                TextView dayNumText = itemView.findViewById(R.id.dayNumber);
                TextView dayLabel = itemView.findViewById(R.id.dayLabel);
                TextView dayTitle = itemView.findViewById(R.id.dayTitle);

                int displayDay = i + 1;
                
                dayNumText.setText(String.valueOf(displayDay));
                dayLabel.setText(String.format(Locale.getDefault(), "Day %d", displayDay));
                
                // Cycle through titles if duration > 7
                String title = genericTitles[i % genericTitles.length];
                dayTitle.setText(title);

                itemView.setOnClickListener(v -> {
                    Intent intent = new Intent(ItineraryActivity.this, DayDetailActivity.class);
                    intent.putExtra("day", displayDay);
                    intent.putExtra("title", title);
                    intent.putExtra("destination", destination);
                    intent.putExtra("currency", currency);
                    startActivity(intent);
                });

                daysList.addView(itemView);
            }
        }

        findViewById(R.id.btnCostBreakdown).setOnClickListener(v -> {
            Intent intent = new Intent(this, CostBreakdownActivity.class);
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                intent.putExtras(extras);
            }
            startActivity(intent);
        });

        findViewById(R.id.btnViewHotels).setOnClickListener(v -> {
            Intent intent = new Intent(this, ViewHotelsActivity.class);
            intent.putExtra("budget", budget);
            intent.putExtra("currency", currency);
            startActivity(intent);
        });

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }
}
