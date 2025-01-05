package com.example.casper.Experiment2024.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.casper.Experiment2024.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class SubmarineDodgeBombsView extends SurfaceView implements Runnable {
    private Thread gameThread;
    private boolean isPlaying;
    private SurfaceHolder surfaceHolder;
    private Paint paint;
    private int dodgedBombsCount; // 记录躲过的炸弹数
    private long startTime; // 记录游戏开始时间，用于计算坚持时间
    private long currentTime; // 当前时间
    private ArrayList<Bomb> bombs; // 炸弹列表
    private Submarine submarine; // 潜艇对象

    public SubmarineDodgeBombsView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        paint = new Paint();
        dodgedBombsCount = 0;
        startTime = System.currentTimeMillis();
        initGame();
    }

    public SubmarineDodgeBombsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        surfaceHolder = getHolder();
        paint = new Paint();
        dodgedBombsCount = 0;
        startTime = System.currentTimeMillis();
        initGame();
    }

    private void initGame() {
        bombs = new ArrayList<>();
        submarine = new Submarine();
        Random random = new Random();
        for (int i = 0; i < 5; i++) { // 初始添加5个炸弹，可根据需求调整数量
            bombs.add(new Bomb());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                submarine.move(event.getX(), event.getY()); // 根据触摸位置移动潜艇
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    @Override
    public void run() {
        while (isPlaying) {
            update();
            draw();
            control();
        }
    }

    private void update() {
        // 更新炸弹状态（位置、新增炸弹等）
        updateBombs();
        // 检查潜艇与炸弹是否碰撞
        checkCollision();
    }

    private void updateBombs() {
        Random random = new Random();
        if (random.nextInt(100) < getBombSpawnRate()) { // 根据一定概率生成新炸弹，概率随时间变化增加
            bombs.add(new Bomb());
        }
        for (Bomb bomb : bombs) {
            bomb.move(); // 炸弹下落
        }
    }

    private int getBombSpawnRate() {
        // 简单示例，随时间增加炸弹生成概率，可根据实际情况调整逻辑
        currentTime = System.currentTimeMillis();
        return (int) (1 + (currentTime - startTime) / 10000);
    }

    private void checkCollision() {
        Iterator<Bomb> iterator = bombs.iterator();
        while (iterator.hasNext()) {
            Bomb bomb = iterator.next();
            if (submarine.isCollidedWith(bomb)) {
                isPlaying = false; // 碰撞则游戏结束
            } else if (bomb.isOutOfBounds()) {
                dodgedBombsCount++;
                iterator.remove(); // 炸弹超出屏幕，视为躲过，移除炸弹并计数
            }
        }
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {
            Canvas canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLUE); // 清屏为蓝色（可根据喜好调整背景色）
            paint.setColor(Color.WHITE);
            paint.setTextSize(50);
            canvas.drawText("Dodged Bombs: " + dodgedBombsCount, 50, 50, paint);
            canvas.drawText("Time Survived: " + (System.currentTimeMillis() - startTime) / 1000 + "s", 50, 100, paint);
            submarine.draw(canvas, paint);
            for (Bomb bomb : bombs) {
                bomb.draw(canvas, paint);
            }
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control() {
        try {
            Thread.sleep(16); // 控制帧率，可根据实际情况调整
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        isPlaying = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        isPlaying = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    // 内部类表示炸弹
    private class Bomb {
        private float x;
        private float y;
        private float speed; // 炸弹下落速度

        public Bomb() {
            x = (float) (Math.random() * getWidth());
            y = 0;
            speed = getInitialBombSpeed(); // 初始化速度，速度随时间增加
        }

        private float getInitialBombSpeed() {
            // 简单示例，速度随时间增加，可根据实际情况调整逻辑
            return (float) (1 + (System.currentTimeMillis() - startTime) / 10000);
        }

        public void move() {
            y += speed; // 炸弹下落
        }

        public boolean isOutOfBounds() {
            return y > getHeight();
        }

        public void draw(Canvas canvas, Paint paint) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bomb);
            canvas.drawBitmap(bitmap, x, y, paint);
        }
    }

    // 内部类表示潜艇
    private class Submarine {
        private float x;
        private float y;

        public Submarine() {
            x = getWidth() / 2;
            y = getHeight() / 2;
        }

        public void move(float touchX, float touchY) {
            x = touchX;
            y = touchY;
            // 限制潜艇在屏幕内移动，可根据实际情况完善边界处理逻辑
            if (x < 0) x = 0;
            if (x > getWidth()) x = getWidth();
            if (y < 0) y = 0;
            if (y > getHeight()) y = getHeight();
        }

        public boolean isCollidedWith(Bomb bomb) {
            // 简单的碰撞检测逻辑，可根据实际图片尺寸等调整
            float distance = (float) Math.sqrt((x - bomb.x) * (x - bomb.x) + (y - bomb.y) * (y - bomb.y));
            return distance < 100; // 假设碰撞距离阈值为100像素，根据实际图片大小调整
        }

        public void draw(Canvas canvas, Paint paint) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.submarine);
            canvas.drawBitmap(bitmap, x, y, paint);
        }
    }
}