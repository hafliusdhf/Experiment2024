package com.example.casper.Experiment2024;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //使用布局文件创建控件
        setContentView(R.layout.activity_main);
        TextView textViewHelloWorld = findViewById(R.id.textview_hello_world);
        textViewHelloWorld.setText(R.string.hello_china);
        textViewHelloWorld.setText(R.string.hello_jnu);

        /*
        RelativeLayout relativeLayout = new RelativeLayout(this);

        RelativeLayout.LayoutParams params =  new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);  //设置布局中的控件居中显示
        TextView textView = new TextView(this);                       //创建TextView控件
        textView.setText("Java 代码实现界面布局");                     //设置TextView的文字内容
        textView.setTextColor(Color.RED);                                  //设置TextView的文字颜色
        textView.setTextSize(18);                                                //设置TextView的文字大小
        relativeLayout.addView(textView, params);                  //添加TextView对象和TextView的布局属性

        setContentView(relativeLayout);                                  //设置在Activity中显示RelativeLayout
        */

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
}