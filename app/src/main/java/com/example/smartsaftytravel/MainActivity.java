package com.example.smartsaftytravel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        // Auto-login if user is already signed in
        if (currentUser != null) {
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_main);

        MaterialButton btnRegister = findViewById(R.id.btnRegister);
        MaterialButton btnLogin = findViewById(R.id.btnLogin);

        btnRegister.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, RegisterActivity.class)));

        btnLogin.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, LoginActivity.class)));

        // Feature cards for landing page
        View safetyCard = findViewById(R.id.safetyCardMain);
        if (safetyCard != null) {
            safetyCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SafetyActivity.class)));
        }

        View planTripCard = findViewById(R.id.planTripCardMain);
        if (planTripCard != null) {
            planTripCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SelectDestinationActivity.class)));
        }

        View alertIcon = findViewById(R.id.alertIcon);
        if (alertIcon != null) {
            alertIcon.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ReportIncidentActivity.class)));
        }

        // Link the Emergency SOS card to Report Incident or SOS Countdown
        View sosCard = findViewById(R.id.sosCardMain);
        if (sosCard != null) {
            sosCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SosCountdownActivity.class)));
        }
    }
}
