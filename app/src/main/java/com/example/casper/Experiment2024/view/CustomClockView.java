package com.example.casper.Experiment2024.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import com.example.casper.Experiment2024.R;

import java.util.Calendar;

public class CustomClockView extends View {
    private Paint paint;
    private int width;
    private int height;
    private float hour;
    private float minute;
    private float second;

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            invalidate(); // 刷新视图
            handler.postDelayed(this, 1000); // 每秒更新一次
        }
    };

    public CustomClockView(Context context) {
        super(context);
        init();
    }

    public CustomClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public void startUpdating() {
        handler.post(runnable); // 启动更新
    }
    public void stopUpdating() {
        handler.removeCallbacks(runnable);
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
    }
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        handler.removeCallbacks(runnable); // 停止更新
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawClockFace(canvas);
        drawClockHands(canvas);
    }

    private void drawClockFace(Canvas canvas) {
        paint.setColor(Color.BLACK);
        canvas.drawCircle(width / 2, height / 2, Math.min(width, height) / 2 - 20, paint);

        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(5);
        canvas.drawCircle(width / 2, height / 2, Math.min(width, height) / 2 - 30, paint);

        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        // 绘制数字和刻度
        for (int i = 0; i < 12; i++) {
            float angle = (float) (Math.toRadians(i * 30));
            float x = (float) (width / 2 + (Math.min(width, height) / 2 - 60) * Math.sin(angle));
            float y = (float) (height / 2 - (Math.min(width, height) / 2 - 60) * Math.cos(angle));
            canvas.drawText(String.valueOf(i==0?12:i ), x - 10, y + 10, paint);
        }
    }
    private void drawClockHands(Canvas canvas) {
        // 获取当前时间
        Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);

        // 加载时针图片资源（这里假设图片已放入res/drawable目录，且文件名分别为hour_hand.png等，根据实际情况修改）
        Bitmap hourHandBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.hour_hand);
        // 调整时针图片大小（根据实际表盘大小等因素调整合适的尺寸，以下示例代码只是示意）
        hourHandBitmap = Bitmap.createScaledBitmap(hourHandBitmap, (int) (Math.min(width, height) / 4), (int) (Math.min(width, height) / 4), true);
        // 绘制时针
        canvas.drawBitmap(hourHandBitmap, (width / 2 - hourHandBitmap.getWidth() / 2), (height / 2 - hourHandBitmap.getHeight() / 2), paint);

        // 加载分针图片资源
        Bitmap minuteHandBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.minute_hand);
        minuteHandBitmap = Bitmap.createScaledBitmap(minuteHandBitmap, (int) (Math.min(width, height) / 3), (int) (Math.min(width, height) / 2), true);
        // 绘制分针
        canvas.drawBitmap(minuteHandBitmap, (width / 2 - minuteHandBitmap.getWidth() / 2), (height / 2 - minuteHandBitmap.getHeight() / 2), paint);

        // 加载秒针图片资源
        Bitmap secondHandBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.second_hand);
        secondHandBitmap = Bitmap.createScaledBitmap(secondHandBitmap, (int) (Math.min(width, height) / 2.5f), (int) (Math.min(width, height) / 1.5f), true);
        // 绘制秒针
        canvas.drawBitmap(secondHandBitmap, (width / 2 - secondHandBitmap.getWidth() / 2), (height / 2 - secondHandBitmap.getHeight() / 2), paint);
    }

    private void drawHand(Canvas canvas, float angle, float length) {
        float x = (float) (width / 2 + length * Math.sin(Math.toRadians(angle)));
        float y = (float) (height / 2 - length * Math.cos(Math.toRadians(angle)));
        canvas.drawLine(width / 2, height / 2, x, y, paint);
    }
}
