package com.example.casper.Experiment2024;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private String clickedText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //使用布局文件创建控件
        setContentView(R.layout.activity_main);
        TextView textViewHelloWorld = findViewById(R.id.textview_hello_world);
        textViewHelloWorld.setOnClickListener(new TextViewOnClickListener());
        TextView textViewHelloChina = findViewById(R.id.textview_hello_china);
        textViewHelloChina.setOnClickListener(new TextViewOnClickListener());
        TextView textViewHelloJNU = findViewById(R.id.textview_hello_jnu);
        textViewHelloJNU.setOnClickListener(new TextViewOnClickListener());

        TextView textViewHelloWorld1 = findViewById(R.id.textview_hello_world1);
        textViewHelloWorld1.setOnClickListener(new TextViewOnClickListener());
        TextView textViewHelloChina1 = findViewById(R.id.textview_hello_china1);
        textViewHelloChina1.setOnClickListener(new TextViewOnClickListener());
        TextView textViewHelloJNU1 = findViewById(R.id.textview_hello_jnu1);
        textViewHelloJNU1.setOnClickListener(new TextViewOnClickListener());

        Button buttonShowMessage = findViewById(R.id.button_show_message);
        buttonShowMessage.setOnClickListener(buttonView -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle(R.string.message);
                    builder.setMessage(getString(R.string.you_have_clicked) + clickedText);
                    builder.setIcon(R.mipmap.ic_launcher_round);
                    //点击对话框以外的区域是否让对话框消失
                    builder.setCancelable(true);
                    //设置正面按钮
                    builder.setPositiveButton(R.string.positive, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, getString(R.string.you_clicked_yes), Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });

                    //设置反面按钮
                    builder.setNegativeButton("Negative", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, R.string.you_clicked_no, Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    //对话框显示的监听事件
                    dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                        @Override
                        public void onShow(DialogInterface dialog) {
                            Log.e("MainActivity", getString(R.string.the_dialog_shows));
                        }
                    });
                    //对话框消失的监听事件
                    dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            Log.e("MainActivity", getString(R.string.the_dialog_disppeared));
                        }
                    });
                    //显示对话框
                    dialog.show();
                }
        );

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }


    private class TextViewOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View textViewClicked) {
            TextView textView = (TextView) textViewClicked;
            clickedText = (String) ((TextView) textViewClicked).getText();
            Toast.makeText(MainActivity.this, clickedText, Toast.LENGTH_LONG)
                    .show();
        }
    }
}