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

public class WhackAMoleView extends SurfaceView implements Runnable {
    private Thread gameThread;
    private boolean isPlaying;
    private SurfaceHolder surfaceHolder;
    private Paint paint;
    private int score;
    private ArrayList<Spiriter> spiriters;
    private float touchedX;
    private float touchedY;
    private boolean isTouched=false;

    public WhackAMoleView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        paint = new Paint();
        score = 0;
    }
    public WhackAMoleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        surfaceHolder = getHolder();
        paint = new Paint();
        score = 0;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // 处理触摸按下事件
                break;
            case MotionEvent.ACTION_MOVE:
                // 处理触摸移动事件
                break;
            case MotionEvent.ACTION_UP:
                // 处理触摸抬起事件
                touchedX=event.getRawX();
                touchedY=event.getRawY();
                isTouched=true;
                break;
        }
        return true; // 表示事件已被处理
    }


    @Override
    public void run() {
        spiriters = new ArrayList<>();
        for(int i=0;i<10;++i){
            spiriters.add(new Spiriter());
        }
        while (isPlaying) {
            update();
            draw();
            control();
        }
    }

    private void update() {
        // 更新游戏逻辑，例如处理地鼠出现和消失
        double theTouchedX,theTouchedY;
        if(isTouched)
        {
            theTouchedX=touchedX;
            theTouchedY=touchedY;
            isTouched=false;
            for (Spiriter spiriter :
                    spiriters) {
                spiriter.collisionDetect(touchedX,touchedY);
            }
        }
        for (Spiriter spiriter :
                spiriters) {
            spiriter.move();
        }
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {
            Canvas canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.WHITE); // 清屏
            paint.setColor(Color.BLACK);
            paint.setTextSize(50);
            canvas.drawText("Score: " + score, 50, 50, paint);
            // 在这里绘制地鼠
            for (Spiriter spiriter :
                    spiriters) {
                spiriter.draw(canvas,paint);
            }
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control() {
        // 使用迭代器遍历并删除元素
        Iterator<Spiriter> iterator = spiriters.iterator();
        while (iterator.hasNext()) {
            Spiriter spiriter = iterator.next();
            if (spiriter.isDead) {
                this.increaseScore();
                iterator.remove(); // 安全地删除当前元素
            }
        }
        try {
            Thread.sleep(17); // 控制帧率
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

    public void increaseScore() {
        score++;
    }

    private class Spiriter {
        double x,y;
        double xDelta,yDelta;
        private boolean isDead;

        public Spiriter()
        {
            double direction = 2*Math.PI*Math.random();
            xDelta = 20*Math.cos(direction);
            yDelta = 20*Math.sin(direction);
            isDead=false;

            x=WhackAMoleView.this.getWidth()*Math.random();
            y=WhackAMoleView.this.getHeight()*Math.random();
        }
        public void move() {
            if(Math.random()<=0.005){
                double direction = 2*Math.PI*Math.random();
                xDelta = 20*Math.cos(direction);
                yDelta = 20*Math.sin(direction);
            }

            x+= xDelta;
            y+= yDelta;

            if(x>= WhackAMoleView.this.getWidth())
            {
                x-=WhackAMoleView.this.getWidth();
            }
            if(x<0)
            {
                x+=WhackAMoleView.this.getWidth();
            }
            if(y>= WhackAMoleView.this.getHeight())
            {
                y-=WhackAMoleView.this.getHeight();
            }
            if(y<0)
            {
                y+=WhackAMoleView.this.getHeight();
            }
        }

        public void draw(Canvas canvas, Paint paint) {
            if(this.isDead)
            {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.baicai);
                canvas.drawBitmap(bitmap, (float)x, (float)y, paint);

            }else{
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.qiqiu);
                canvas.drawBitmap(bitmap, (float)x, (float)y, paint);
            }
        }

        public void collisionDetect(float touchedX, float touchedY) {
            this.isDead=(8000>(x-touchedX)*(x-touchedX)+(y-touchedY)*(y-touchedY));
        }
    }
}