package com.example.smartsaftytravel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import java.util.Locale;

public class ViewHotelsActivity extends AppCompatActivity {

    private String budget, currency, destination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_hotels);

        budget = getIntent().getStringExtra("budget");
        currency = getIntent().getStringExtra("currency");
        destination = getIntent().getStringExtra("destination");
        if (currency == null) currency = "₹";

        LinearLayout hotelsList = findViewById(R.id.hotelsList);
        TextView hotelHeaderSub = findViewById(R.id.hotelTitle);
        if (hotelHeaderSub != null) {
            hotelHeaderSub.setText("Best stays in " + destination);
        }
        
        // Define hotels based on budget requirements:
        // Low Budget -> Budget hotels
        // Medium Budget -> Standard hotels
        // High Budget -> Luxury resorts
        String[][] hotels;
        if (getString(R.string.low_budget).equals(budget)) {
            hotels = new String[][]{
                {"City Backpackers Hostel", "⭐ 4.2", "800", "0.2 km from center", "Shared Kitchen • WiFi • Locker"},
                {"Budget Inn", "⭐ 4.0", "1200", "1.5 km from center", "Free WiFi • AC • Breakfast"},
                {"Solo Traveler Den", "⭐ 4.5", "950", "0.8 km from center", "Central Location • WiFi • Common Area"}
            };
        } else if (getString(R.string.high_budget).equals(budget)) {
            hotels = new String[][]{
                {"Grand Hyatt Regency", "⭐ 4.9", "18000", "0.1 km from center", "Spa • Private Pool • 5-Star Dining"},
                {"The Royal Resort & Spa", "⭐ 4.8", "25000", "Beachfront", "Ocean View • Butler Service • All-inclusive"},
                {"Luxury Heritage Palace", "⭐ 5.0", "35000", "Historic District", "Royal Suites • Private Tour • Valet"}
            };
        } else {
            // Medium Budget (Standard Hotels)
            hotels = new String[][]{
                {"Comfort Plaza Hotel", "⭐ 4.5", "4500", "0.5 km from center", "Breakfast • Gym • Work Station"},
                {"Urban Boutique Stay", "⭐ 4.4", "5200", "1.2 km from center", "Rooftop Cafe • AC • Mini Bar"},
                {"Skyline City Hotel", "⭐ 4.6", "6000", "Central Area", "Pool • Restaurant • Laundry Service"}
            };
        }

        if (hotelsList != null) {
            hotelsList.removeAllViews();
            for (String[] hotel : hotels) {
                View itemView = LayoutInflater.from(this).inflate(R.layout.item_hotel, hotelsList, false);
                
                ((TextView) itemView.findViewById(R.id.hotelName)).setText(hotel[0]);
                ((TextView) itemView.findViewById(R.id.hotelRating)).setText(hotel[1]);
                ((TextView) itemView.findViewById(R.id.hotelPrice)).setText(String.format(Locale.getDefault(), "%s %s", currency, hotel[2]));
                ((TextView) itemView.findViewById(R.id.hotelDistance)).setText(hotel[3]);
                ((TextView) itemView.findViewById(R.id.hotelFacilities)).setText(hotel[4]);

                MaterialButton btnBook = itemView.findViewById(R.id.btnBookNow);
                btnBook.setOnClickListener(v -> {
                    Toast.makeText(this, "Booking " + hotel[0] + "...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, CostBreakdownActivity.class);
                    intent.putExtras(getIntent().getExtras());
                    intent.putExtra("selectedHotel", hotel[0]);
                    intent.putExtra("hotelPrice", hotel[2]);
                    startActivity(intent);
                });

                hotelsList.addView(itemView);
            }
        }

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }
}
