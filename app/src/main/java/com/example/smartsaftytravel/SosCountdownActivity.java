package com.example.smartsaftytravel;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class SosCountdownActivity extends AppCompatActivity {

    private CountDownTimer countDownTimer;
    private TextView countdownText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos_countdown);

        countdownText = findViewById(R.id.countdownText);
        MaterialButton btnCancel = findViewById(R.id.btnCancelSos);

        countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                countdownText.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                // When countdown finishes, start the SOS Call activity
                startActivity(new Intent(SosCountdownActivity.this, SosCallActivity.class));
                finish();
            }
        }.start();

        btnCancel.setOnClickListener(v -> {
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
            finish();
        });

        findViewById(R.id.btnClose).setOnClickListener(v -> {
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
            finish();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
