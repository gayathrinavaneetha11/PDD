package com.example.smartsaftytravel;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class OtpVerificationActivity extends AppCompatActivity {

    private EditText[] otpDigits = new EditText[6];
    private String receivedOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        // Get the generated OTP from the intent
        receivedOtp = getIntent().getStringExtra("otp_code");

        otpDigits[0] = findViewById(R.id.otpDigit1);
        otpDigits[1] = findViewById(R.id.otpDigit2);
        otpDigits[2] = findViewById(R.id.otpDigit3);
        otpDigits[3] = findViewById(R.id.otpDigit4);
        otpDigits[4] = findViewById(R.id.otpDigit5);
        otpDigits[5] = findViewById(R.id.otpDigit6);

        setupOtpInputs();

        MaterialButton btnVerify = findViewById(R.id.btnVerify);
        btnVerify.setOnClickListener(v -> {
            String enteredOtp = getOtpValue();
            if (enteredOtp.length() < 6) {
                Toast.makeText(this, "Please enter all 6 digits", Toast.LENGTH_SHORT).show();
            } else if (enteredOtp.equals(receivedOtp)) {
                Toast.makeText(this, "Verification Successful!", Toast.LENGTH_SHORT).show();
                // Proceed to Login
                Intent intent = new Intent(OtpVerificationActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Incorrect OTP. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    private void setupOtpInputs() {
        for (int i = 0; i < 6; i++) {
            final int index = i;
            otpDigits[i].addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.length() == 1 && index < 5) {
                        otpDigits[index + 1].requestFocus();
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                    // Handled in onTextChanged for forward focus
                }
            });
        }
    }

    private String getOtpValue() {
        StringBuilder sb = new StringBuilder();
        for (EditText et : otpDigits) {
            sb.append(et.getText().toString());
        }
        return sb.toString();
    }
}
