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
import java.util.ArrayList;
import java.util.List;

public class Hello1880Activity extends AppCompatActivity {
    private String clickedNumber = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello1880);
    }

    // 添加 getListBooks 方法
    public List<Book> getListBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("创新工程实践", R.drawable.book_no_name));
        books.add(new Book("软件项目管理案例教程（第3版）", R.drawable.book_2));
        books.add(new Book("信息安全数学基础（第2版）", R.drawable.book_1));
        return books;
    }
}