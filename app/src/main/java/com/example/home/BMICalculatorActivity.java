package com.example.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class BMICalculatorActivity extends AppCompatActivity {

    private EditText heightInput;
    private EditText weightInput;
    private TextView resultText;
    private Button calculateButton;
    private Button resetButton;
    private Button backButton; // Added Back Button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalculator);

        heightInput = findViewById(R.id.heightInput);
        weightInput = findViewById(R.id.weightInput);
        resultText = findViewById(R.id.textView5);
        calculateButton = findViewById(R.id.button);
        resetButton = findViewById(R.id.button2);
        backButton = findViewById(R.id.backButton); // Initialize Back Button

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();
            }
        });

        // Handle Back Button to navigate back to MainActivity
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to navigate back to MainActivity
                Intent intent = new Intent(BMICalculatorActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Optional: Close BMI activity
            }
        });
    }

    private void calculateBMI() {
        String heightStr = heightInput.getText().toString();
        String weightStr = weightInput.getText().toString();

        if (!heightStr.isEmpty() && !weightStr.isEmpty()) {
            float height = Float.parseFloat(heightStr) / 100;
            float weight = Float.parseFloat(weightStr);

            float bmi = weight / (height * height);
            String bmiCategory = getBMICategory(bmi);

            resultText.setText("BMI: " + String.format("%.2f", bmi) + " (" + bmiCategory + ")");
        } else {
            Toast.makeText(this, "Please enter height and weight", Toast.LENGTH_SHORT).show();
        }
    }

    private String getBMICategory(float bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal weight";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obesity";
        }
    }

    private void resetFields() {
        heightInput.setText("");
        weightInput.setText("");
        resultText.setText("");
    }
}
