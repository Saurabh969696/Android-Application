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

public class CalculatorActivity extends AppCompatActivity {

        int id=0;
        String temp,res;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_calculator);
            Button num0=findViewById(R.id.num0);
            Button num1=findViewById(R.id.num1);
            Button num2=findViewById(R.id.num2);
            Button num3=findViewById(R.id.num3);
            Button num4=findViewById(R.id.num4);
            Button num5=findViewById(R.id.num5);
            Button num6=findViewById(R.id.num6);
            Button num7=findViewById(R.id.num7);
            Button num8=findViewById(R.id.num8);
            Button num9=findViewById(R.id.num9);
            Button C=findViewById(R.id.C);
            Button AC=findViewById(R.id.AC);
            Button open=findViewById(R.id.open);
            Button close=findViewById(R.id.close);
            Button add=findViewById(R.id.add);
            Button sub=findViewById(R.id.sub);
            Button mul=findViewById(R.id.mul);
            Button div=findViewById(R.id.div);
            Button dot=findViewById(R.id.dot);
            Button equal=findViewById(R.id.equal);

            TextView screen=findViewById(R.id.screen);

            AC.setOnClickListener(view -> {
                screen.setText("0");
            });
            num1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String currentText = screen.getText().toString();
                    if (currentText.equals("0")) {
                        screen.setText("1");
                    } else {
                        screen.setText(currentText + "1");
                    }
                }
            });

            num2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String currentText = screen.getText().toString();
                    if (currentText.equals("0")) {
                        screen.setText("2");
                    } else {
                        screen.setText(currentText + "2");
                    }
                }
            });
            num3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String currentText = screen.getText().toString();
                    if (currentText.equals("0")) {
                        screen.setText("3");
                    } else {
                        screen.setText(currentText + "3");
                    }
                }
            });
            num4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String currentText = screen.getText().toString();
                    if (currentText.equals("0")) {
                        screen.setText("4");
                    } else {
                        screen.setText(currentText + "4");
                    }
                }
            });
            num5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String currentText = screen.getText().toString();
                    if (currentText.equals("0")) {
                        screen.setText("5");
                    } else {
                        screen.setText(currentText + "5");
                    }
                }
            });
            num6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String currentText = screen.getText().toString();
                    if (currentText.equals("0")) {
                        screen.setText("6");
                    } else {
                        screen.setText(currentText + "6");
                    }
                }
            });
            num7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String currentText = screen.getText().toString();
                    if (currentText.equals("0")) {
                        screen.setText("1");
                    } else {
                        screen.setText(currentText + "7");
                    }
                }
            });
            num8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String currentText = screen.getText().toString();
                    if (currentText.equals("0")) {
                        screen.setText("8");
                    } else {
                        screen.setText(currentText + "8");
                    }
                }
            });
            num9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String currentText = screen.getText().toString();
                    if (currentText.equals("0")) {
                        screen.setText("9");
                    } else {
                        screen.setText(currentText + "9");
                    }
                }
            });
            num0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String currentText = screen.getText().toString();
                    if (!currentText.equals("0")) {
                        screen.setText(currentText + "0");
                    }
                }
            });
            dot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String currentText = screen.getText().toString();
                    if (!currentText.contains(".")) {
                        screen.setText(currentText + ".");
                    }
                }
            });

            C.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String currentText = screen.getText().toString();
                    if (currentText.length() > 1) {
                        screen.setText(currentText.substring(0, currentText.length() - 1));
                    } else {
                        screen.setText("0");
                    }
                }
            });
            open.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String currentText = screen.getText().toString();
                    try {
                        int number = Integer.parseInt(currentText);
                        int square = number * number;
                        screen.setText(String.valueOf(square));
                    } catch (NumberFormatException e) {
                        screen.setText("Error");
                    }
                }
            });
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    temp = screen.getText().toString();
                    screen.setText("");
                    id = 1;
                }
            });

            sub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    temp=screen.getText().toString();
                    screen.setText("");
                    id=2;
                }
            });
            mul.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    temp=screen.getText().toString();
                    screen.setText("");
                    id=3;
                }
            });
            div.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    temp=screen.getText().toString();
                    screen.setText("");
                    id=4;
                }
            });
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    temp=screen.getText().toString();
                    screen.setText("");
                    id=5;
                }
            });
            equal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String currentText = screen.getText().toString();
                    if (temp == null || temp.isEmpty()) {
                        screen.setText("0");
                        return;
                    }


                    try {
                        double num1 = Double.parseDouble(temp);
                        double num2 = Double.parseDouble(currentText);
                        double result = 0;

                        switch (id) {
                            case 1: // Addition
                                result = num1 + num2;
                                break;
                            case 2: // Subtraction
                                result = num1 - num2;
                                break;
                            case 3: // Multiplication
                                result = num1 * num2;
                                break;
                            case 4: // Division
                                if (num2 != 0) {
                                    result = num1 / num2;
                                } else {
                                    screen.setText("Error");
                                    return;
                                }
                                break;
                            case 5: // Modulo
                                result = num1 % num2;
                                break;
                            default:
                                screen.setText("Error");
                                return;
                        }

                        // Format the result to remove ".0" for whole numbers
                        if (result == (int) result) {
                            screen.setText(String.valueOf((int) result));
                        } else {
                            screen.setText(String.valueOf(result));
                        }

                        id = 0; // Reset operation identifier
                    } catch (NumberFormatException e) {
                        screen.setText("Error");
                    }
                }
            });



        }
    }