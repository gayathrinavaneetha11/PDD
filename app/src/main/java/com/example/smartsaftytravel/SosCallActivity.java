package com.example.smartsaftytravel;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class SosCallActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos_call);
        findViewById(R.id.btnEndCall).setOnClickListener(v -> finish());
    }
}
