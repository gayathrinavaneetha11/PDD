package com.example.smartsaftytravel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class ReviewReportActivity extends AppCompatActivity {

    private String incidentType, location, dateTime, description;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_report);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        // Get data from Intents
        incidentType = getIntent().getStringExtra("incidentType");
        location = getIntent().getStringExtra("location");
        dateTime = getIntent().getStringExtra("dateTime");
        description = getIntent().getStringExtra("description");

        // Display data using the IDs we added to the XML
        TextView typeText = findViewById(R.id.reviewIncidentType);
        TextView locText = findViewById(R.id.reviewLocation);
        TextView dateText = findViewById(R.id.reviewDateTime);
        TextView descText = findViewById(R.id.reviewDescription);

        if (typeText != null) typeText.setText(incidentType);
        if (locText != null) locText.setText(location);
        if (dateText != null) dateText.setText(dateTime);
        if (descText != null) descText.setText(description);

        CheckBox confirmCheckbox = findViewById(R.id.confirmCheckbox);

        findViewById(R.id.btnSubmitReport).setOnClickListener(v -> {
            if (confirmCheckbox != null && !confirmCheckbox.isChecked()) {
                Toast.makeText(this, "Please confirm the information", Toast.LENGTH_SHORT).show();
                return;
            }
            
            submitReportToFirebase();
        });

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    private void submitReportToFirebase() {
        Map<String, Object> report = new HashMap<>();
        report.put("incidentType", incidentType);
        report.put("location", location);
        report.put("dateTime", dateTime);
        report.put("description", description);
        report.put("timestamp", com.google.firebase.Timestamp.now());
        
        if (mAuth.getCurrentUser() != null) {
            report.put("userId", mAuth.getCurrentUser().getUid());
            report.put("userEmail", mAuth.getCurrentUser().getEmail());
        }

        db.collection("reports").add(report)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "Report Submitted Successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, ReportSubmittedActivity.class);
                    intent.putExtra("incidentType", incidentType);
                    startActivity(intent);
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Failed to submit report: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
    }
}
