package com.example.casper.Experiment2024.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.casper.Experiment2024.R;

public class UpdateBookActivity extends AppCompatActivity {
    private EditText editTextBookName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);

        editTextBookName = findViewById(R.id.edit_text_book_name);
        Button buttonUpdateBook = findViewById(R.id.button_update_book);

        // 获取传递过来的书名和位置
        String bookName = getIntent().getStringExtra("book_name");
        int position = getIntent().getIntExtra("position", -1);
        editTextBookName.setText(bookName);

        buttonUpdateBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updatedBookName = editTextBookName.getText().toString();
                Intent resultIntent = new Intent();
                resultIntent.putExtra("book_name", updatedBookName);
                resultIntent.putExtra("position", position);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}