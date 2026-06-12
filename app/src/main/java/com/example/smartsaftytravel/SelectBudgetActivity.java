package com.example.smartsaftytravel;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import java.util.Locale;

public class SelectBudgetActivity extends AppCompatActivity {

    private String selectedBudget = "";
    private MaterialCardView cardLow, cardMedium, cardHigh;
    private String destination, country, currency;
    private double baseRate = 1.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_budget);

        destination = getIntent().getStringExtra("destination");
        country = getIntent().getStringExtra("country");
        
        setupCurrencyAndRates();

        cardLow = findViewById(R.id.cardLow);
        cardMedium = findViewById(R.id.cardMedium);
        cardHigh = findViewById(R.id.cardHigh);

        updateAllBudgetValues();

        // Use string resources for consistent comparison
        final String lowText = getString(R.string.low_budget);
        final String medText = getString(R.string.medium_budget);
        final String highText = getString(R.string.high_budget);

        if (cardLow != null) cardLow.setOnClickListener(v -> selectBudget(lowText));
        if (cardMedium != null) cardMedium.setOnClickListener(v -> selectBudget(medText));
        if (cardHigh != null) cardHigh.setOnClickListener(v -> selectBudget(highText));

        MaterialButton btnContinue = findViewById(R.id.btnContinue);
        if (btnContinue != null) {
            btnContinue.setOnClickListener(v -> {
                Intent intent = new Intent(SelectBudgetActivity.this, SelectDatesActivity.class);
                intent.putExtra("destination", destination);
                intent.putExtra("budget", selectedBudget);
                intent.putExtra("currency", currency);
                startActivity(intent);
            });
        }

        if (findViewById(R.id.btnBack) != null) {
            findViewById(R.id.btnBack).setOnClickListener(v -> finish());
        }
        
        // Default selection
        selectBudget(medText);
    }

    private void setupCurrencyAndRates() {
        if (country == null) country = "India";
        
        switch (country) {
            case "India": currency = "₹"; baseRate = 10000; break;
            case "USA": currency = "$"; baseRate = 1500; break;
            case "France": case "Italy": case "Spain": case "Netherlands": case "Switzerland": currency = "€"; baseRate = 1400; break;
            case "UK": currency = "£"; baseRate = 1200; break;
            case "UAE": currency = "AED"; baseRate = 1750; break;
            case "Japan": currency = "¥"; baseRate = 220000; break;
            case "Singapore": currency = "SGD"; baseRate = 2000; break;
            default: currency = "$"; baseRate = 1500; break;
        }
    }

    private void updateAllBudgetValues() {
        setBudgetValues(0.8, R.id.hotelLow, R.id.foodLow, R.id.transportLow, R.id.attractionLow, R.id.emergencyLow, R.id.totalLow);
        setBudgetValues(2.0, R.id.hotelMedium, R.id.foodMedium, R.id.transportMedium, R.id.attractionMedium, R.id.emergencyMedium, R.id.totalMedium);
        setBudgetValues(4.5, R.id.hotelHigh, R.id.foodHigh, R.id.transportHigh, R.id.attractionHigh, R.id.emergencyHigh, R.id.totalHigh);
    }

    private void setBudgetValues(double multiplier, int hId, int fId, int tId, int aId, int eId, int totalId) {
        long base = (long) (baseRate * multiplier);
        long hotel = (long) (base * 0.4);
        long food = (long) (base * 0.2);
        long transport = (long) (base * 0.15);
        long attraction = (long) (base * 0.15);
        long emergency = (long) (base * 0.1);
        long total = hotel + food + transport + attraction + emergency;

        setTextSafely(hId, String.format(Locale.getDefault(), "%s %d", currency, hotel));
        setTextSafely(fId, String.format(Locale.getDefault(), "%s %d", currency, food));
        setTextSafely(tId, String.format(Locale.getDefault(), "%s %d", currency, transport));
        setTextSafely(aId, String.format(Locale.getDefault(), "%s %d", currency, attraction));
        setTextSafely(eId, String.format(Locale.getDefault(), "%s %d", currency, emergency));
        setTextSafely(totalId, String.format(Locale.getDefault(), "%s %d", currency, total));
    }

    private void setTextSafely(int id, String text) {
        TextView tv = findViewById(id);
        if (tv != null) tv.setText(text);
    }

    private void selectBudget(String budget) {
        selectedBudget = budget;
        
        int blueColor = ContextCompat.getColor(this, R.color.primary_blue);
        ColorStateList blueStateList = ColorStateList.valueOf(blueColor);
        
        String low = getString(R.string.low_budget);
        String med = getString(R.string.medium_budget);
        String high = getString(R.string.high_budget);

        updateCardStyle(cardLow, low.equals(budget), blueStateList);
        updateCardStyle(cardMedium, med.equals(budget), blueStateList);
        updateCardStyle(cardHigh, high.equals(budget), blueStateList);
    }

    private void updateCardStyle(MaterialCardView card, boolean isSelected, ColorStateList color) {
        if (card != null) {
            card.setStrokeWidth(isSelected ? 8 : 0);
            card.setStrokeColor(color);
            card.setCardElevation(isSelected ? 16 : 2);
        }
    }
}
