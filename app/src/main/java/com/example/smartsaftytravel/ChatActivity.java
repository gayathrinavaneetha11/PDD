package com.example.smartsaftytravel;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class ChatActivity extends AppCompatActivity {

    private LinearLayout chatContainer;
    private EditText messageInput;
    private ScrollView chatScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatContainer = findViewById(R.id.chatContainer);
        messageInput = findViewById(R.id.messageInput);
        chatScrollView = findViewById(R.id.chatScrollView);
        MaterialButton btnSend = findViewById(R.id.btnSend);

        btnSend.setOnClickListener(v -> sendMessage());

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    private void sendMessage() {
        String message = messageInput.getText().toString().trim();
        if (message.isEmpty()) return;

        addMessage(message, true);
        messageInput.setText("");

        // Simulate AI "typing"
        new Handler().postDelayed(() -> {
            String response = getAIResponse(message.toLowerCase());
            addMessage(response, false);
        }, 1000);
    }

    private void addMessage(String text, boolean isUser) {
        int layoutId = isUser ? R.layout.item_chat_user : R.layout.item_chat_bot;
        View messageView = LayoutInflater.from(this).inflate(layoutId, chatContainer, false);
        
        TextView tv = messageView.findViewById(R.id.messageText);
        if (tv != null) {
            tv.setText(text);
        }
        
        chatContainer.addView(messageView);
        
        // Scroll to bottom
        chatScrollView.post(() -> chatScrollView.fullScroll(View.FOCUS_DOWN));
    }

    private String getAIResponse(String query) {
        if (query.contains("hi") || query.contains("hello")) {
            return "Hello! I'm your Smart Travel AI. How can I help you plan your safe trip today?";
        } else if (query.contains("safe") || query.contains("security")) {
            return "Manhattan is generally very safe. I recommend staying in well-lit areas after 10 PM. You can use our 'Safety Check' feature for real-time risk levels.";
        } else if (query.contains("budget")) {
            return "I can help you optimize your costs! For New York, a Medium Budget of $1500 is great for a mix of iconic landmarks and local food.";
        } else if (query.contains("weather")) {
            return "The current weather in your destination is 22°C and Clear. Perfect for a walk in Central Park!";
        } else if (query.contains("hospital") || query.contains("doctor")) {
            return "The nearest hospital is NYU Langone Health, located just 1.2 miles away. Would you like me to show the route?";
        } else if (query.contains("police") || query.contains("help")) {
            return "In case of emergency, press the red SOS button on your home screen. The nearest police station is the Midtown South Precinct.";
        } else {
            return "That's interesting! I'm analyzing the best travel options for you. Is there anything specific about your destination you'd like to know?";
        }
    }
}
