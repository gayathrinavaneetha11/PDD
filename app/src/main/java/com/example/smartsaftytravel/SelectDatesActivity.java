package com.example.smartsaftytravel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.util.Pair;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class SelectDatesActivity extends AppCompatActivity {

    private TextView startDateText, endDateText, tvTravelersCount, durationLabel;
    private String selectedStartDate = "", selectedEndDate = "";
    private int selectedDuration = 0;
    private int travelersCount = 1;
    private String selectedTripType = "Solo";
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());

    private MaterialButton btnSolo, btnCouple, btnFamily, btnFriends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_dates);

        // Initialize Views
        startDateText = findViewById(R.id.startDateText);
        endDateText = findViewById(R.id.endDateText);
        durationLabel = findViewById(R.id.durationLabel);
        tvTravelersCount = findViewById(R.id.tvTravelersCount);

        btnSolo = findViewById(R.id.btnSolo);
        btnCouple = findViewById(R.id.btnCouple);
        btnFamily = findViewById(R.id.btnFamily);
        btnFriends = findViewById(R.id.btnFriends);

        // Setup Date Pickers
        findViewById(R.id.startDateCard).setOnClickListener(v -> showDatePicker());
        findViewById(R.id.endDateCard).setOnClickListener(v -> showDatePicker());

        // Travelers Count Logic
        findViewById(R.id.btnPlus).setOnClickListener(v -> {
            if (travelersCount < 10) {
                travelersCount++;
                tvTravelersCount.setText(String.valueOf(travelersCount));
            }
        });

        findViewById(R.id.btnMinus).setOnClickListener(v -> {
            if (travelersCount > 1) {
                travelersCount--;
                tvTravelersCount.setText(String.valueOf(travelersCount));
            }
        });

        // Trip Type Selection
        if (btnSolo != null) btnSolo.setOnClickListener(v -> selectTripType("Solo", btnSolo));
        if (btnCouple != null) btnCouple.setOnClickListener(v -> selectTripType("Couple", btnCouple));
        if (btnFamily != null) btnFamily.setOnClickListener(v -> selectTripType("Family", btnFamily));
        if (btnFriends != null) btnFriends.setOnClickListener(v -> selectTripType("Friends", btnFriends));

        // Default selection
        selectTripType("Solo", btnSolo);

        MaterialButton btnContinue = findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(v -> {
            if (selectedDuration > 0) {
                if (selectedDuration > 15) {
                    Toast.makeText(this, "Maximum trip duration is 15 days", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                Intent intent = new Intent(SelectDatesActivity.this, LoadingActivity.class);
                // Carry forward all previous data
                intent.putExtras(getIntent().getExtras());
                // Add new data
                intent.putExtra("startDate", selectedStartDate);
                intent.putExtra("endDate", selectedEndDate);
                intent.putExtra("duration", selectedDuration);
                intent.putExtra("travelers", travelersCount);
                intent.putExtra("tripType", selectedTripType);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Please select travel dates", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    private void selectTripType(String type, MaterialButton selectedBtn) {
        selectedTripType = type;
        
        MaterialButton[] buttons = {btnSolo, btnCouple, btnFamily, btnFriends};
        for (MaterialButton btn : buttons) {
            if (btn != null) {
                btn.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
                btn.setTextColor(ContextCompat.getColor(this, R.color.text_dark));
                btn.setStrokeColorResource(R.color.light_gray);
            }
        }

        if (selectedBtn != null) {
            selectedBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.primary_blue_light));
            selectedBtn.setTextColor(ContextCompat.getColor(this, R.color.primary_blue));
            selectedBtn.setStrokeColorResource(R.color.primary_blue);
        }
    }

    private void showDatePicker() {
        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        builder.setTitleText("Select Travel Dates");
        
        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder();
        constraintsBuilder.setValidator(DateValidatorPointForward.now());
        builder.setCalendarConstraints(constraintsBuilder.build());

        MaterialDatePicker<Pair<Long, Long>> picker = builder.build();
        picker.show(getSupportFragmentManager(), "DATE_PICKER");

        picker.addOnPositiveButtonClickListener(selection -> {
            if (selection.first != null && selection.second != null) {
                selectedStartDate = sdf.format(selection.first);
                selectedEndDate = sdf.format(selection.second);
                
                long diff = selection.second - selection.first;
                selectedDuration = (int) (diff / (1000 * 60 * 60 * 24)) + 1;

                if (startDateText != null) startDateText.setText(selectedStartDate);
                if (endDateText != null) endDateText.setText(selectedEndDate);
                if (durationLabel != null) durationLabel.setText("Duration: " + selectedDuration + " days");
            }
        });
    }
}
