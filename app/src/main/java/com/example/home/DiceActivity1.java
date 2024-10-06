package com.example.home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class DiceActivity1 extends AppCompatActivity {

    int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dice1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ImageView i;
        Button b;
        i=findViewById(R.id.imageView);
        b=findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random r=new Random();
                n=r.nextInt(6)+1;
                switch (n){
                    case 1: {
                        i.setImageResource(R.drawable.dice_1);
                        break;
                    }
                    case 2: {
                        i.setImageResource(R.drawable.dice_2);
                        break;
                    }
                    case 3: {
                        i.setImageResource(R.drawable.dice_3);
                        break;
                    }
                    case 4: {
                        i.setImageResource(R.drawable.dice_4);
                        break;
                    }
                    case 5: {
                        i.setImageResource(R.drawable.dice_5);
                        break;
                    }
                    case 6: {
                        i.setImageResource(R.drawable.dice_6);
                        break;
                    }
                }

            }
        });
    }
}