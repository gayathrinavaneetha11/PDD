package com.example.smartsaftytravel;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.google.android.material.button.MaterialButton;
import java.util.Calendar;
import java.util.Locale;

public class ReportIncidentActivity extends AppCompatActivity {

    private Spinner spinnerIncidentType;
    private EditText etLocation;
    private TextView tvDateTime;
    private String selectedDateTime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_incident);

        spinnerIncidentType = findViewById(R.id.spinnerIncidentType);
        etLocation = findViewById(R.id.etLocation);
        tvDateTime = findViewById(R.id.tvDateTime);

        // Requirement: Pre-fill location if given before (e.g., from destination)
        String prefilledLocation = getIntent().getStringExtra("destination");
        if (prefilledLocation != null && !prefilledLocation.isEmpty()) {
            etLocation.setText(prefilledLocation);
        }

        setupIncidentSpinner();

        // Requirement: Date and Time to set
        findViewById(R.id.cardDateTime).setOnClickListener(v -> showDateTimePicker());

        MaterialButton btnContinue = findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(v -> {
            String incidentType = spinnerIncidentType.getSelectedItem().toString();
            String location = etLocation.getText().toString().trim();
            String dateTime = tvDateTime.getText().toString().trim();

            if (location.isEmpty()) {
                Toast.makeText(this, "Please type the location", Toast.LENGTH_SHORT).show();
                return;
            }

            if (dateTime.equals("Select Date & Time") || dateTime.isEmpty()) {
                Toast.makeText(this, "Please set date and time", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(ReportIncidentActivity.this, UploadEvidenceActivity.class);
            intent.putExtra("incidentType", incidentType);
            intent.putExtra("location", location);
            intent.putExtra("dateTime", dateTime);
            startActivity(intent);
        });

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    private void setupIncidentSpinner() {
        String[] types = {
                "Theft / Robbery",
                "Harassment",
                "Suspicious Activity",
                "Medical Emergency",
                "Traffic Accident",
                "Natural Hazard",
                "Others"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, types);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIncidentType.setAdapter(adapter);
    }

    private void showDateTimePicker() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, monthOfYear, dayOfMonth) -> {
            final String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1;
            
            TimePickerDialog timePickerDialog = new TimePickerDialog(ReportIncidentActivity.this, (view1, hourOfDay, minute) -> {
                String time = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);
                selectedDateTime = date + " " + time;
                tvDateTime.setText(selectedDateTime);
                // Corrected Context for getColor
                tvDateTime.setTextColor(ContextCompat.getColor(ReportIncidentActivity.this, R.color.text_dark));
            }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true);
            timePickerDialog.show();
            
        }, year, month, day);
        datePickerDialog.show();
    }
}
