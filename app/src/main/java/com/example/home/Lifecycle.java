package com.example.home;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Lifecycle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        showToast("onCreate called");
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
