package com.example.smartsaftytravel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.google.android.material.button.MaterialButton;

public class DayDetailActivity extends AppCompatActivity {

    private int day;
    private int totalDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_detail);

        // Get Personalization Data
        day = getIntent().getIntExtra("day", 1);
        String title = getIntent().getStringExtra("title");
        String destination = getIntent().getStringExtra("destination");
        String currency = getIntent().getStringExtra("currency");
        String tripType = getIntent().getStringExtra("tripType");
        
        if (tripType == null) tripType = "Solo";
        if (currency == null) currency = "₹";
        totalDays = getIntent().getIntExtra("totalDays", 5);

        TextView topDayTitle = findViewById(R.id.topDayTitle);
        TextView dayNumberLabel = findViewById(R.id.dayNumberLabel);
        TextView dayMainTitle = findViewById(R.id.dayMainTitle);
        TextView dayDate = findViewById(R.id.dayDate);
        MaterialButton btnNextDay = findViewById(R.id.btnNextDay);

        if (topDayTitle != null) topDayTitle.setText(getString(R.string.day_format, day));
        if (dayNumberLabel != null) dayNumberLabel.setText(getString(R.string.day_format, day));
        if (dayMainTitle != null) dayMainTitle.setText(title);
        
        if (dayDate != null) {
            dayDate.setText(getString(R.string.day_scheduled_format, day, destination, tripType));
        }

        if (day >= totalDays) {
            btnNextDay.setText(R.string.view_final_breakdown);
            btnNextDay.setBackgroundTintList(android.content.res.ColorStateList.valueOf(ContextCompat.getColor(this, R.color.primary_blue)));
        }

        setupPersonalizedAIPlan(currency, tripType);

        btnNextDay.setOnClickListener(v -> {
            Class<?> targetClass = (day < totalDays) ? DayDetailActivity.class : CostBreakdownActivity.class;
            Intent intent = new Intent(this, targetClass);
            
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                intent.putExtras(extras);
            }
            
            if (day < totalDays) {
                intent.putExtra("day", day + 1);
            }
            startActivity(intent);
        });

        findViewById(R.id.btnBackToOverview).setOnClickListener(v -> finish());
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    private void setupPersonalizedAIPlan(String currency, String tripType) {
        LinearLayout activitiesList = findViewById(R.id.activitiesList);
        if (activitiesList == null) return;
        activitiesList.removeAllViews();
        
        String[][] activities;
        
        // AI Requirement: Generate different schedules based on Trip Type
        if ("Couple".equals(tripType)) {
            activities = new String[][]{
                {"09:30 AM", "Late Romantic Breakfast", "Balcony Cafe", "400", "Morning", "🚶 Walk", "9 AM - 11 AM", "Rose Garden (4.6★)"},
                {"11:30 AM", "Scenic Viewpoint Stroll", "Sky Deck", "200", "Morning", "🚕 Taxi", "11 AM - 1 PM", "N/A"},
                {"01:30 PM", "Couples' Spa & Relax", "Zen Center", "2000", "Afternoon", "🚶 Walk", "1 PM - 4 PM", "N/A"},
                {"05:30 PM", "Sunset Photography", "City Lookout", "0", "Evening", "🚕 Taxi", "5 PM - 7 PM", "N/A"},
                {"08:30 PM", "Candle-light Dinner", "Signature Rooftop", "3000", "Night", "🚕 Taxi", "8 PM - 11 PM", "Sky View (4.9★)"}
            };
        } else if ("Family".equals(tripType)) {
            activities = new String[][]{
                {"08:30 AM", "Family Buffet Breakfast", "Hotel Restaurant", "300", "Morning", "🚶 Walk", "8 AM - 10 AM", "Kid's Corner (4.3★)"},
                {"10:30 AM", "Interactive Museum", "Science Center", "800", "Morning", "🚌 Shuttle", "10 AM - 2 PM", "N/A"},
                {"02:00 PM", "Park Picnic & Games", "Central Greens", "200", "Afternoon", "🚶 Walk", "2 PM - 5 PM", "Park Cafe (4.1★)"},
                {"06:00 PM", "Animated Fountain Show", "City Plaza", "0", "Evening", "🚌 Bus", "6 PM - 8 PM", "N/A"},
                {"08:00 PM", "Casual Family Dinner", "Pizzeria", "1200", "Night", "🚕 Taxi", "8 PM - 10 PM", "Italiano (4.5★)"}
            };
        } else if ("Friends".equals(tripType)) {
            activities = new String[][]{
                {"10:00 AM", "Brunch & Plan", "Trendy Bistro", "500", "Morning", "🚶 Walk", "10 AM - 12 PM", "Bistro 24 (4.4★)"},
                {"01:00 PM", "Adventure Water Sports", "Action Pier", "1500", "Afternoon", "🚕 Taxi", "1 PM - 4 PM", "N/A"},
                {"04:30 PM", "Group Shopping Spree", "Shopping District", "0", "Evening", "🚌 Bus", "4 PM - 7 PM", "Street Snacks (4.0★)"},
                {"08:00 PM", "Live Music Event", "The Arena", "1000", "Night", "🚕 Taxi", "8 PM - 10 PM", "N/A"},
                {"10:30 PM", "Nightlife Experience", "Downtown Club", "2000", "Night", "🚕 Taxi", "10 PM - 2 AM", "N/A"}
            };
        } else {
            // Solo / Default
            activities = new String[][]{
                {"08:00 AM", "Quick Breakfast", "Local Bakery", "150", "Morning", "🚶 Walk", "7 AM - 9 AM", "Corner Bakery (4.2★)"},
                {"09:30 AM", "Photography Walk", "Old Town", "0", "Morning", "🚶 Walk", "9 AM - 12 PM", "N/A"},
                {"01:00 PM", "Authentic Local Lunch", "Street Side", "300", "Afternoon", "🚶 Walk", "12 PM - 2 PM", "Local Hub (4.5★)"},
                {"03:00 PM", "Backpacker Group Tour", "Meeting Point", "400", "Afternoon", "🚌 Bus", "3 PM - 6 PM", "N/A"},
                {"08:00 PM", "Hostel Social Dinner", "Common Room", "200", "Night", "🚶 Walk", "8 PM - 11 PM", "N/A"}
            };
        }

        long dailyTotal = 0;
        for (String[] act : activities) {
            View itemView = LayoutInflater.from(this).inflate(R.layout.item_activity, activitiesList, false);
            ((TextView) itemView.findViewById(R.id.actTime)).setText(act[0]);
            ((TextView) itemView.findViewById(R.id.actTitle)).setText(act[1]);
            ((TextView) itemView.findViewById(R.id.actLocation)).setText(act[2]);
            ((TextView) itemView.findViewById(R.id.actCategory)).setText(act[4]);
            ((TextView) itemView.findViewById(R.id.actTransport)).setText(act[5]);
            ((TextView) itemView.findViewById(R.id.actBestTime)).setText(getString(R.string.act_best_time_format, act[6]));
            
            TextView restTv = itemView.findViewById(R.id.actRestaurant);
            if ("N/A".equals(act[7])) {
                restTv.setVisibility(View.GONE);
            } else {
                restTv.setText(getString(R.string.act_nearby_format, act[7]));
                restTv.setVisibility(View.VISIBLE);
            }
            
            long cost = Long.parseLong(act[3]);
            dailyTotal += cost;
            ((TextView) itemView.findViewById(R.id.actCost)).setText(getString(R.string.cost_format, currency, cost));
            activitiesList.addView(itemView);
        }

        TextView dayTotalCost = findViewById(R.id.dayTotalCost);
        if (dayTotalCost != null) {
            dayTotalCost.setText(getString(R.string.cost_format, currency, dailyTotal));
        }
    }
}
