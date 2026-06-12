package com.example.smartsaftytravel;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class NavigationActivity extends AppCompatActivity {

    private TextView instructionText;
    private ImageView navArrow;
    private int step = 0;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        instructionText = findViewById(R.id.instructionText);
        navArrow = findViewById(R.id.navArrow);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
        findViewById(R.id.btnEnd).setOnClickListener(v -> {
            Toast.makeText(this, "Destination reached safely!", Toast.LENGTH_SHORT).show();
            finish();
        });

        findViewById(R.id.btnMute).setOnClickListener(v -> 
            Toast.makeText(this, "Voice guidance muted", Toast.LENGTH_SHORT).show());

        startNavigationSimulation();
    }

    private void startNavigationSimulation() {
        String[] instructions = {
            "Head north on 5th Ave",
            "In 200m, turn right onto 42nd St",
            "Continue straight for 1.2km",
            "Safe Route: Avoiding crowded area ahead",
            "Turn left toward Central Park",
            "You have arrived at your destination"
        };

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (step < instructions.length) {
                    if (instructionText != null) {
                        instructionText.setText(instructions[step]);
                    }
                    
                    // Simple logic to change arrow
                    if (navArrow != null) {
                        if (instructions[step].contains("right")) {
                            navArrow.setRotation(90);
                        } else if (instructions[step].contains("left")) {
                            navArrow.setRotation(-90);
                        } else {
                            navArrow.setRotation(0);
                        }
                    }

                    step++;
                    handler.postDelayed(this, 4000);
                }
            }
        };
        handler.post(runnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
