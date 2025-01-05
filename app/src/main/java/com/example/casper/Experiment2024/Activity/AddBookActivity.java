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

public class AddBookActivity extends AppCompatActivity {
    private EditText editTextBookName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        editTextBookName = findViewById(R.id.edit_text_book_name);
        Button buttonAddBook = findViewById(R.id.button_add_book);

        buttonAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookName = editTextBookName.getText().toString();
                Intent resultIntent = new Intent();
                resultIntent.putExtra("book_name", bookName);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}