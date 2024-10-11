package com.example.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Lifecycle extends AppCompatActivity {

    private Button b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        showToast("onCreate called");

        // Find the button by its ID
        b4 = findViewById(R.id.Button4);

        // Set the OnClickListener for b4
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to navigate back to MainActivity
                Intent intent = new Intent(Lifecycle.this, MainActivity.class);
                startActivity(intent);
                finish(); // Optional: Close the Lifecycle activity
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        showToast("Activity is Started");
    }

    @Override
    protected void onResume() {
        super.onResume();
        showToast("Activity is Resumed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        showToast("Activity is Paused");
    }

    @Override
    protected void onStop() {
        super.onStop();
        showToast("onStop called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showToast("Activity is Destroyed");
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
