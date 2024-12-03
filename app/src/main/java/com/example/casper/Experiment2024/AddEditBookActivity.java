package com.example.casper.Experiment2024;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AddEditBookActivity extends AppCompatActivity {
    private EditText editTextTitle;
    private Button buttonSave;
    private boolean isEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_book);
        editTextTitle = findViewById(R.id.edit_text_title);
        buttonSave = findViewById(R.id.button_save);
        if (getIntent().hasExtra("book")) {
            isEditMode = true;
            Book book = (Book) getIntent().getSerializableExtra("book");
            editTextTitle.setText(book.getTitle());
        }
        buttonSave.setOnClickListener(v -> {
            String title = editTextTitle.getText().toString();
            Book newBook = new Book(title, R.drawable.book_no_name); // 默认图片
            Intent resultIntent = new Intent();
            resultIntent.putExtra("book", newBook);
            if (isEditMode) {
                int position = getIntent().getIntExtra("position", -1);
                if (position != -1) {
                    resultIntent.putExtra("position", position);
                }
            }
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}