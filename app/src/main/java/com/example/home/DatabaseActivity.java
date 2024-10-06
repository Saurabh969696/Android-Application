package com.example.home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DatabaseActivity extends AppCompatActivity {

    EditText etId, etName, etAge, etCity, etPhone;
    Button btnInsert, btnUpdate, btnDelete, btnView;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        etId = findViewById(R.id.editTextText);
        etName = findViewById(R.id.editTextText2);
        etAge = findViewById(R.id.editTextText3);
        etCity = findViewById(R.id.editTextText4);
        etPhone = findViewById(R.id.editTextText5);
        btnInsert = findViewById(R.id.button);
        btnUpdate = findViewById(R.id.button2);
        btnDelete = findViewById(R.id.button3);
        btnView = findViewById(R.id.button4);

        dbHelper = new DbHelper(this);

        // Insert data with manually entered ID
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etId.getText().toString();  // Manually entered ID
                String name = etName.getText().toString();
                String age = etAge.getText().toString();
                String city = etCity.getText().toString();
                String phone = etPhone.getText().toString();

                if (id.isEmpty() || name.isEmpty() || age.isEmpty() || city.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(DatabaseActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    boolean inserted = dbHelper.insertData(id, name, Integer.parseInt(age), city, phone);
                    if (inserted) {
                        Toast.makeText(DatabaseActivity.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DatabaseActivity.this, "Insertion failed", Toast.LENGTH_SHORT).show();
                    }
                    clearFields();
                }
            }
        });

        // Update data
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etId.getText().toString();
                String name = etName.getText().toString();
                String age = etAge.getText().toString();
                String city = etCity.getText().toString();
                String phone = etPhone.getText().toString();

                if (id.isEmpty() || name.isEmpty() || age.isEmpty() || city.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(DatabaseActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    boolean updated = dbHelper.updateData(id, name, Integer.parseInt(age), city, phone);
                    if (updated) {
                        Toast.makeText(DatabaseActivity.this, "Data updated successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DatabaseActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                    }
                    clearFields();
                }
            }
        });

        // Delete data
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etId.getText().toString();

                if (id.isEmpty()) {
                    Toast.makeText(DatabaseActivity.this, "Please enter ID", Toast.LENGTH_SHORT).show();
                } else {
                    Integer deletedRows = dbHelper.deleteData(id);
                    if (deletedRows > 0) {
                        Toast.makeText(DatabaseActivity.this, "Data deleted successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DatabaseActivity.this, "Deletion failed", Toast.LENGTH_SHORT).show();
                    }
                    clearFields();
                }
            }
        });

        // Select data by ID
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etId.getText().toString();  // Get the entered ID

                if (id.isEmpty()) {
                    Toast.makeText(DatabaseActivity.this, "Please enter ID", Toast.LENGTH_SHORT).show();
                } else {
                    // Call the select query method
                    String[] result = dbHelper.getDataById(id);

                    if (result != null) {
                        // Display the retrieved data in the fields
                        etName.setText(result[0]);
                        etAge.setText(result[1]);
                        etCity.setText(result[2]);
                        etPhone.setText(result[3]);
                        Toast.makeText(DatabaseActivity.this, "Data loaded", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DatabaseActivity.this, "No data found for this ID", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    // Helper method to clear input fields
    private void clearFields() {
        etId.setText("");
        etName.setText("");
        etAge.setText("");
        etCity.setText("");
        etPhone.setText("");
    }
}
