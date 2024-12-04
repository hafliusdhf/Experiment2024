package com.example.casper.Experiment2024.Activity;//package com.example.casper.Experiment2024;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.casper.Experiment2024.R;

public class ImageViewActivity extends AppCompatActivity {
    private Button buttonPrevious = (Button)findViewById(R.id.button_previous);
    private Button buttonNext =(Button) findViewById(R.id.button_next);
    private ImageView imageViewFunny = (ImageView)findViewById(R.id.image_view_funny);
    private int[] imageIDArray = {
            R.drawable.funny_1,
            R.drawable.funny_2,
            R.drawable.funny_3,
            R.drawable.funny_4,
            R.drawable.funny_5,
            R.drawable.funny_6
    };
    private int imageIDArrayCurrentIndex;

    public ImageViewActivity(int iTest) {
        imageIDArrayCurrentIndex = 0;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_image_view);
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
        buttonPrevious = findViewById(R.id.button_previous);
        buttonNext = findViewById(R.id.button_next);
        buttonPrevious.setOnClickListener(new MyButtonClickListener());
        buttonNext.setOnClickListener(new MyButtonClickListener());
    }

    private class MyButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (((Button) view).getText() == "下一个") {
                imageIDArrayCurrentIndex ++;
            } else {
                imageIDArrayCurrentIndex --;
            }
            imageViewFunny.setImageResource(imageIDArray[imageIDArrayCurrentIndex]);
        }
    }

}