package com.example.smartsaftytravel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddDescriptionActivity extends AppCompatActivity {

    private String incidentType, location, dateTime;
    private EditText descriptionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_description);

        // Get data from previous screens
        incidentType = getIntent().getStringExtra("incidentType");
        location = getIntent().getStringExtra("location");
        dateTime = getIntent().getStringExtra("dateTime");

        descriptionEditText = findViewById(R.id.descriptionEditText);

        findViewById(R.id.btnContinueToReview).setOnClickListener(v -> {
            String description = descriptionEditText.getText().toString().trim();
            if (description.isEmpty()) {
                Toast.makeText(this, "Please provide a description", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(this, ReviewReportActivity.class);
            intent.putExtra("incidentType", incidentType);
            intent.putExtra("location", location);
            intent.putExtra("dateTime", dateTime);
            intent.putExtra("description", description);
            startActivity(intent);
        });

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }
}
