package com.example.home;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Button for BMI Calculator
        Button btnBMI = findViewById(R.id.button);
        btnBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to navigate to BMICalculatorActivity
                Intent intent = new Intent(MainActivity.this, BMICalculatorActivity.class);
                startActivity(intent);
            }
        });

        // Button for Calculator
        Button btnCalculator = findViewById(R.id.button2);
        btnCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to navigate to CalculatorActivity
                Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
                startActivity(intent);
            }
        });

        // Button for Dice Game
        Button btnDice = findViewById(R.id.button3);
        btnDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to navigate to DiceActivity
                Intent intent = new Intent(MainActivity.this, DiceActivity1.class);
                startActivity(intent);
            }
        });
        Button btnDice1 = findViewById(R.id.button4);
        btnDice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to navigate to DiceActivity
                Intent intent = new Intent(MainActivity.this, DiceActivity2.class);
                startActivity(intent);
            }
        });
        Button btnLogin = findViewById(R.id.button5);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to navigate to MusicPlayerActivity
                Intent intent = new Intent(MainActivity.this, LoginIntentActivity.class);
                startActivity(intent);
            }
        });
        Button btnDb = findViewById(R.id.button6);
        btnDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to navigate to MusicPlayerActivity
                Intent intent = new Intent(MainActivity.this, DatabaseActivity.class);
                startActivity(intent);
            }
        });

        // Button for Music Player
        Button btnMusic = findViewById(R.id.button7);
        btnMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to navigate to MusicPlayerActivity
                Intent intent = new Intent(MainActivity.this, MusicPlayerActivity.class);
                startActivity(intent);
            }
        });

        Button btnLife = findViewById(R.id.button8);
        btnLife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to navigate to MusicPlayerActivity
                Intent intent = new Intent(MainActivity.this, Lifecycle.class);
                startActivity(intent);
            }
        });
        Button btnLocation = findViewById(R.id.button9);
        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to navigate to MusicPlayerActivity
                Intent intent = new Intent(MainActivity.this, LocationActivity.class);
                startActivity(intent);
            }
        });
        Button btnSpeech = findViewById(R.id.button10);
        btnSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to navigate to MusicPlayerActivity
                Intent intent = new Intent(MainActivity.this, TextToSpeechActivity.class);
                startActivity(intent);
            }
        });
        Button btnPdf = findViewById(R.id.button11);
        btnPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to navigate to MusicPlayerActivity
                Intent intent = new Intent(MainActivity.this, pdfreader.class);
                startActivity(intent);
            }
        });

    }
}
