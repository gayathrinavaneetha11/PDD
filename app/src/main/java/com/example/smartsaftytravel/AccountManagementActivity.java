package com.example.smartsaftytravel;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import java.util.HashMap;
import java.util.Map;

public class AccountManagementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_management);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        EditText editFullName = findViewById(R.id.editFullName);
        EditText editEmail = findViewById(R.id.editEmail);
        EditText editPhone = findViewById(R.id.editPhone);
        MaterialButton btnSaveChanges = findViewById(R.id.btnSaveChanges);

        if (user != null) {
            if (editEmail != null) editEmail.setText(user.getEmail());
            
            // Load current data from Firestore
            db.collection("users").document(user.getUid()).get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            String name = documentSnapshot.getString("fullName");
                            String phone = documentSnapshot.getString("phone");
                            if (name != null && editFullName != null) editFullName.setText(name);
                            if (phone != null && editPhone != null) editPhone.setText(phone);
                        }
                    })
                    .addOnFailureListener(e -> Log.e("AccountMgmt", "Error loading user data", e));
        }

        if (btnSaveChanges != null) {
            btnSaveChanges.setOnClickListener(v -> saveUserChanges(db, user, editFullName, editPhone, btnSaveChanges));
        }

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    private void saveUserChanges(FirebaseFirestore db, FirebaseUser user, EditText editFullName, EditText editPhone, MaterialButton btnSaveChanges) {
        if (user == null) {
            Toast.makeText(this, "Session expired. Please login again.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (editFullName == null || editPhone == null || btnSaveChanges == null) return;

        String newName = editFullName.getText().toString().trim();
        String newPhone = editPhone.getText().toString().trim();

        if (newName.isEmpty()) {
            editFullName.setError("Name is required");
            return;
        }

        btnSaveChanges.setEnabled(false); // Prevent multiple clicks

        Map<String, Object> updates = new HashMap<>();
        updates.put("fullName", newName);
        updates.put("phone", newPhone);
        updates.put("email", user.getEmail()); // Keep email in sync

        // Use 'set' with merge to ensure it works even if the user document didn't exist
        db.collection("users").document(user.getUid()).set(updates, SetOptions.merge())
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "Profile Updated!", Toast.LENGTH_SHORT).show();
                    btnSaveChanges.setEnabled(true);
                    finish();
                })
                .addOnFailureListener(e -> {
                    btnSaveChanges.setEnabled(true);
                    Log.e("FirestoreError", "Failed to update profile", e);
                    Toast.makeText(this, "Update Error: " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                });
    }
}
