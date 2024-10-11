package com.example.home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.IOException;
import java.io.InputStream;

public class pdfreader extends AppCompatActivity {

    TextView e1;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pdfreader);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        e1 = findViewById(R.id.textview7);
        b1 = findViewById(R.id.button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                extractPdf();
            }

            private void extractPdf() {
                String text = "";
                try {
                    InputStream inputStream = getResources().openRawResource(R.raw.daa);
                    PdfReader rd = new PdfReader(inputStream);
                    int n = rd.getNumberOfPages();
                    for (int i = 0; i < n; i++) {
                        text += PdfTextExtractor.getTextFromPage(rd, i + 1).trim() + "\n";
                    }
                    e1.setText(text);
                    rd.close();
                } catch (IOException e) {
                    e1.setText("Error found: " + e.getMessage());
                }
            }
        });
    }
}
