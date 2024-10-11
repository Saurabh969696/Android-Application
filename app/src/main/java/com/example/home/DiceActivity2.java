package com.example.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.util.Log;


import java.util.Random;

public class DiceActivity2 extends AppCompatActivity {

        int n=0,n1=0;
        boolean isDice1Rolled = false;
        boolean isDice2Rolled = false;
        int rollCount1 = 0, rollCount2 = 0;
        int score1 = 0, score2 = 0;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_dice2);
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
            ImageView i,i1;
            Button b,b1,b2,b3,b4;
            TextView t,t1,t2;
            t=findViewById(R.id.textView3);
            t1=findViewById(R.id.textView4);
            t2=findViewById(R.id.textView5);
            i=findViewById(R.id.imageView);
            i1=findViewById(R.id.imageview2);
            b=findViewById(R.id.button);
            b1=findViewById(R.id.button2);
            b2=findViewById(R.id.button3);
            b3=findViewById(R.id.button4);
            b4=findViewById(R.id.Button1);

            b4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Intent to navigate back to MainActivity
                    Intent intent = new Intent(DiceActivity2.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Random r=new Random();
                    n=r.nextInt(6)+1;
                    rollCount1++;
                    isDice1Rolled = true;
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
                    score1 += n;
                    t1.setText("Dice 1 Rolled: " + rollCount1 + " times");

                }
            });
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Random r=new Random();
                    n1=r.nextInt(6)+1;
                    rollCount2++;
                    isDice2Rolled = true;
                    switch (n1){
                        case 1: {
                            i1.setImageResource(R.drawable.dice_1);
                            break;
                        }
                        case 2: {
                            i1.setImageResource(R.drawable.dice_2);
                            break;
                        }
                        case 3: {
                            i1.setImageResource(R.drawable.dice_3);
                            break;
                        }
                        case 4: {
                            i1.setImageResource(R.drawable.dice_4);
                            break;
                        }
                        case 5: {
                            i1.setImageResource(R.drawable.dice_5);
                            break;
                        }
                        case 6: {
                            i1.setImageResource(R.drawable.dice_6);
                            break;
                        }
                    }
                    score2 += n1;
                    t2.setText("Dice 2 Rolled: " + rollCount2 + " times");

                }
            });
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!isDice1Rolled || !isDice2Rolled) {
                        t.setText("Please roll both dice");
                    }else if (rollCount1 != rollCount2) {
                        t.setText("Both dice must be rolled the same number of times.");
                    }else {
                        if (score1 > score2) {
                            t.setText("Winner: Player 1 " +"\nscore"+"\nPlayer1:"+ score1+"\nPlayer2:"+score2);
                        } else if (score1 < score2) {
                            t.setText("Winner: Player2" +"\nscore"+"\nPlayer1:"+ score1+"\nPlayer2:"+score2);
                        } else {
                            t.setText("Winner: Tie" +"\nscore"+"\nPlayer1:"+ score1+"\nPlayer2:"+score2);
                        }

                        isDice1Rolled = false;
                        isDice2Rolled = false;
                        rollCount1 = 0;
                        rollCount2 = 0;
                        score1=0;
                        score2=0;

                    }
                }
            });
            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    t.setText("");
                    i.setImageResource(R.drawable.dice_general);
                    i1.setImageResource(R.drawable.dice_general);
                    t1.setText("");
                    t2.setText("");

                }
            });


        }
    }