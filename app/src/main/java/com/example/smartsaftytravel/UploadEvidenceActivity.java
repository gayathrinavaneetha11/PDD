package com.example.smartsaftytravel;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class UploadEvidenceActivity extends AppCompatActivity {

    private String incidentType, location, dateTime;
    private static final int CAMERA_PERMISSION_CODE = 100;

    // Modern way to handle activity results
    private final ActivityResultLauncher<Intent> takePictureLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Toast.makeText(this, "Photo captured!", Toast.LENGTH_SHORT).show();
                    // Navigate to next screen after capture
                    navigateNext();
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_evidence);

        // Get data from previous screen
        incidentType = getIntent().getStringExtra("incidentType");
        location = getIntent().getStringExtra("location");
        dateTime = getIntent().getStringExtra("dateTime");

        findViewById(R.id.btnTakePhoto).setOnClickListener(v -> checkPermissionAndOpeningCamera());

        findViewById(R.id.btnContinue).setOnClickListener(v -> navigateNext());
        findViewById(R.id.btnSkip).setOnClickListener(v -> navigateNext());
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    private void checkPermissionAndOpeningCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
        } else {
            openCamera();
        }
    }

    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Using resolveActivity might return null on Android 11+ without <queries> in manifest
        // I already added <queries> to the manifest, so this is safe.
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            takePictureLauncher.launch(takePictureIntent);
        } else {
            Toast.makeText(this, "Camera not available", Toast.LENGTH_SHORT).show();
        }
    }

    private void navigateNext() {
        Intent intent = new Intent(this, AddDescriptionActivity.class);
        intent.putExtra("incidentType", incidentType);
        intent.putExtra("location", location);
        intent.putExtra("dateTime", dateTime);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Toast.makeText(this, "Camera permission is required to take photos", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
