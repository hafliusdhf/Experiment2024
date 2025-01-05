package com.example.casper.Experiment2024.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

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

        // 绘制时针
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);
        float hourAngle = (float) ((hour + minute / 60) * 30);
        drawHand(canvas, hourAngle, Math.min(width, height) / 4);

        // 绘制分针
        paint.setStrokeWidth(6);
        float minuteAngle = (float) ((minute + second / 60) * 6);
        drawHand(canvas, minuteAngle, Math.min(width, height) / 3);

        // 绘制秒针
        paint.setColor(Color.RED);
        paint.setStrokeWidth(4);
        float secondAngle = (float) (second * 6);
        drawHand(canvas, secondAngle, Math.min(width, height) / 2.5f);
    }

    private void drawHand(Canvas canvas, float angle, float length) {
        float x = (float) (width / 2 + length * Math.sin(Math.toRadians(angle)));
        float y = (float) (height / 2 - length * Math.cos(Math.toRadians(angle)));
        canvas.drawLine(width / 2, height / 2, x, y, paint);
    }
}
