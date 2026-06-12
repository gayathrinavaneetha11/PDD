package com.example.smartsaftytravel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.chip.Chip;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SelectDestinationActivity extends AppCompatActivity {

    private LinearLayout destListContainer;
    private EditText searchEditText;
    private List<Destination> allDestinations = new ArrayList<>();
    private String currentCategory = "All";
    private LinearLayout recentList;
    private View recentSection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_destination);

        destListContainer = findViewById(R.id.destListContainer);
        searchEditText = findViewById(R.id.searchEditText);
        recentList = findViewById(R.id.recentList);
        recentSection = findViewById(R.id.recentSection);

        initDestinations();
        loadRecentSearches();

        String editDest = getIntent().getStringExtra("editDestination");
        if (editDest != null) {
            searchEditText.setText(editDest);
            filter(editDest);
        } else {
            displayDestinations(allDestinations);
        }

        setupSearch();
        setupChips();

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    private void initDestinations() {
        String[] indiaNames = {"Goa", "Manali", "Ooty", "Munnar", "Mysore", "Jaipur", "Agra", "Delhi", "Mumbai", "Hyderabad", "Chennai", "Bengaluru", "Kochi", "Darjeeling", "Shimla", "Leh Ladakh", "Andaman", "Pondicherry", "Udaipur", "Varanasi", "Amritsar", "Srinagar", "Rishikesh", "Haridwar", "Coorg", "Alleppey", "Kodaikanal", "Tirupati", "Hampi", "Mahabalipuram", "Khajuraho", "Ajanta Ellora", "Sikkim", "Gangtok", "Shillong", "Kaziranga", "Jaisalmer", "Mount Abu", "Bhubaneswar", "Konark"};
        for (String name : indiaNames) {
            allDestinations.add(new Destination(name, "India", "🇮🇳", true));
        }

        String[] intlNames = {"New York", "Los Angeles", "Las Vegas", "Paris", "London", "Dubai", "Singapore", "Tokyo", "Bangkok", "Bali", "Maldives", "Rome", "Sydney", "Toronto", "Istanbul", "Seoul", "Kuala Lumpur", "Switzerland", "Barcelona", "Amsterdam"};
        for (String name : intlNames) {
            allDestinations.add(new Destination(name, getCountry(name), getFlag(name), false));
        }
    }

    private void loadRecentSearches() {
        SharedPreferences prefs = getSharedPreferences("SmartTravelPrefs", Context.MODE_PRIVATE);
        Set<String> recent = prefs.getStringSet("recentSearches", new HashSet<>());
        
        if (recent.isEmpty()) {
            recentSection.setVisibility(View.GONE);
        } else {
            recentSection.setVisibility(View.VISIBLE);
            recentList.removeAllViews();
            for (String name : recent) {
                Chip chip = new Chip(this);
                chip.setText(name);
                chip.setChipBackgroundColorResource(R.color.white);
                chip.setOnClickListener(v -> {
                    searchEditText.setText(name);
                    filter(name);
                });
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, 0, 16, 0);
                recentList.addView(chip, params);
            }
        }
    }

    private void saveToRecent(String name) {
        SharedPreferences prefs = getSharedPreferences("SmartTravelPrefs", Context.MODE_PRIVATE);
        Set<String> recent = prefs.getStringSet("recentSearches", new HashSet<>());
        Set<String> updated = new HashSet<>(recent);
        updated.add(name);
        prefs.edit().putStringSet("recentSearches", updated).apply();
    }

    private String getCountry(String city) {
        switch (city) {
            case "New York": case "Los Angeles": case "Las Vegas": return "USA";
            case "Paris": return "France";
            case "London": return "UK";
            case "Dubai": return "UAE";
            case "Singapore": return "Singapore";
            case "Tokyo": return "Japan";
            case "Bangkok": return "Thailand";
            case "Bali": return "Indonesia";
            case "Maldives": return "Maldives";
            case "Rome": return "Italy";
            case "Sydney": return "Australia";
            case "Toronto": return "Canada";
            case "Istanbul": return "Turkey";
            case "Seoul": return "South Korea";
            case "Kuala Lumpur": return "Malaysia";
            case "Switzerland": return "Switzerland";
            case "Barcelona": return "Spain";
            case "Amsterdam": return "Netherlands";
            default: return "International";
        }
    }

    private String getFlag(String city) {
        switch (city) {
            case "New York": case "Los Angeles": case "Las Vegas": return "🇺🇸";
            case "Paris": return "🇫🇷";
            case "London": return "🇬🇧";
            case "Dubai": return "🇦🇪";
            case "Singapore": return "🇸🇬";
            case "Tokyo": return "🇯🇵";
            case "Bangkok": return "🇹🇭";
            case "Bali": return "🇮🇩";
            case "Maldives": return "🇲🇻";
            case "Rome": return "🇮🇹";
            case "Sydney": return "🇦🇺";
            case "Toronto": return "🇨🇦";
            case "Istanbul": return "🇹🇷";
            case "Seoul": return "🇰🇷";
            case "Kuala Lumpur": return "🇲🇾";
            case "Switzerland": return "🇨🇭";
            case "Barcelona": return "🇪🇸";
            case "Amsterdam": return "🇳🇱";
            default: return "🌐";
        }
    }

    private void setupSearch() {
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void setupChips() {
        findViewById(R.id.chipAll).setOnClickListener(v -> updateCategory("All"));
        findViewById(R.id.chipIndia).setOnClickListener(v -> updateCategory("India"));
        findViewById(R.id.chipIntl).setOnClickListener(v -> updateCategory("International"));
    }

    private void updateCategory(String category) {
        currentCategory = category;
        filter(searchEditText.getText().toString());
    }

    private void filter(String query) {
        List<Destination> filteredList = new ArrayList<>();
        for (Destination d : allDestinations) {
            boolean matchesSearch = d.name.toLowerCase().contains(query.toLowerCase());
            boolean matchesCategory = currentCategory.equals("All") ||
                    (currentCategory.equals("India") && d.isIndia) ||
                    (currentCategory.equals("International") && !d.isIndia);
            if (matchesSearch && matchesCategory) {
                filteredList.add(d);
            }
        }
        displayDestinations(filteredList);
    }

    private void displayDestinations(List<Destination> list) {
        destListContainer.removeAllViews();
        for (Destination d : list) {
            View itemView = LayoutInflater.from(this).inflate(R.layout.item_destination, destListContainer, false);
            TextView nameView = itemView.findViewById(R.id.destName);
            TextView countryView = itemView.findViewById(R.id.destCountry);
            TextView flagView = itemView.findViewById(R.id.destFlag);
            
            nameView.setText(d.name);
            countryView.setText(d.country);
            flagView.setText(d.flag);

            itemView.setOnClickListener(v -> {
                saveToRecent(d.name);
                Intent intent = new Intent(SelectDestinationActivity.this, SelectBudgetActivity.class);
                intent.putExtra("destination", d.name);
                intent.putExtra("country", d.country);
                startActivity(intent);
            });
            destListContainer.addView(itemView);
        }
    }

    private static class Destination {
        String name, country, flag;
        boolean isIndia;
        Destination(String name, String country, String flag, boolean isIndia) {
            this.name = name; this.country = country; this.flag = flag; this.isIndia = isIndia;
        }
    }
}
