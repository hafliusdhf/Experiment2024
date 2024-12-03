package com.example.casper.Experiment2024;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView; // 添加此行
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Hello1880Activity extends AppCompatActivity {
    private String clickedNumber = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello1880);
        // 初始化边缘到边缘支持
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // 添加按钮的点击事件
        setupButtonListeners();
    }

    private void setupButtonListeners() {
        // 为数字按钮添加点击事件
        for (int i = 0; i <= 9; i++) {
            final int number = i;
            Button button = findViewById(getResources().getIdentifier("buttonview_number_" + i, "id", getPackageName()));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickedNumber += number; // 将点击的数字添加到clickedNumber中
                    updateDisplay(); // 更新显示
                }
            });
        }
        // Clean按钮点击事件
        Button cleanButton = findViewById(R.id.buttonview_clean);
        cleanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Hello1880Activity.this)
                        .setMessage("Are you sure to clean the number?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                clickedNumber = ""; // 清空clickedNumber
                                updateDisplay(); // 更新显示
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });
        // Input按钮点击事件
        Button inputButton = findViewById(R.id.buttonview_input);
        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!clickedNumber.isEmpty()) {
                    Toast.makeText(Hello1880Activity.this, "You have input number " + clickedNumber, Toast.LENGTH_SHORT).show();
                    clickedNumber = ""; // 输入后清空clickedNumber
                    updateDisplay(); // 更新显示
                } else {
                    Toast.makeText(Hello1880Activity.this, "Please enter a number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateDisplay() {
        // 假设你有一个TextView用于显示当前输入的数字
        TextView display = findViewById(R.id.display);
        if (display != null) {
            display.setText(clickedNumber);
        }
    }
}