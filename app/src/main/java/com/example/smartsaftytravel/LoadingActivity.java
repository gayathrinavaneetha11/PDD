package com.example.smartsaftytravel;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        ImageView loadingIcon = findViewById(R.id.loadingIcon);
        if (loadingIcon != null) {
            Animation rotate = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
            loadingIcon.startAnimation(rotate);
        }

        // Simulate AI generation time
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(LoadingActivity.this, ItineraryActivity.class);
            // Pass all received data to the next screen
            if (getIntent().getExtras() != null) {
                intent.putExtras(getIntent().getExtras());
            }
            startActivity(intent);
            finish();
        }, 3000);
    }
}
