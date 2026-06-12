package com.example.smartsaftytravel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        TextView userName = findViewById(R.id.userName);
        TextView userEmail = findViewById(R.id.userEmail);

        if (user != null) {
            if (userEmail != null) userEmail.setText(user.getEmail());
            fetchUserData(user.getUid(), userName);
        }

        // Edit Profile Button
        findViewById(R.id.btnEditProfile).setOnClickListener(v -> 
            startActivity(new Intent(this, AccountManagementActivity.class)));

        // Menu buttons
        findViewById(R.id.btnTravelPrefs).setOnClickListener(v -> startActivity(new Intent(this, TravelPreferencesActivity.class)));
        findViewById(R.id.btnEmergencyContacts).setOnClickListener(v -> startActivity(new Intent(this, EmergencyContactsActivity.class)));
        findViewById(R.id.btnLanguage).setOnClickListener(v -> startActivity(new Intent(this, LanguageRegionActivity.class)));
        findViewById(R.id.btnPrivacy).setOnClickListener(v -> startActivity(new Intent(this, PrivacySettingsActivity.class)));
        findViewById(R.id.btnAccount).setOnClickListener(v -> startActivity(new Intent(this, AccountManagementActivity.class)));

        findViewById(R.id.btnSignOut).setOnClickListener(v -> {
            mAuth.signOut();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    private void fetchUserData(String uid, TextView userName) {
        db.collection("users").document(uid).get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String name = documentSnapshot.getString("fullName");
                        if (userName != null && name != null && !name.isEmpty()) {
                            userName.setText(name);
                        }
                    }
                })
                .addOnFailureListener(e -> Toast.makeText(this, "Error syncing profile", Toast.LENGTH_SHORT).show());
    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            TextView userName = findViewById(R.id.userName);
            fetchUserData(user.getUid(), userName);
        }
    }
}
