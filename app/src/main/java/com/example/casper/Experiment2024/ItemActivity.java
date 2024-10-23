package com.example.casper.Experiment2024;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ItemActivity extends AppCompatActivity {

    private TextView textViewName;
    private TextView textViewPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_item);
        Intent intent=this.getIntent();
        String name= intent.getStringExtra("item_name");
        textViewName = findViewById(R.id.edit_text_item_name);
        textViewPrice = findViewById(R.id.edit_text_item_price);
        if(null!=name)
        {
            double price=intent.getDoubleExtra("item_price",0);
            textViewName.setText(name);
            textViewPrice.setText(""+price);
        }
        Log.i("Shoping.ItemActivity", "the intent take a key-value"+intent.getStringExtra("key"));

        Button buttonConfirm= findViewById(R.id.button_confirm);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("position", intent.getIntExtra("position",0));
                returnIntent.putExtra("item_name", textViewName.getText().toString());
                returnIntent.putExtra("item_price", Double.valueOf(textViewPrice.getText().toString()));
                setResult(RESULT_OK, returnIntent);
                ItemActivity.this.finish();
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}