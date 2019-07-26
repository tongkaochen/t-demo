package com.tifone.spwp.view.custom;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.opengl.GLSurfaceView;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.util.AttributeSet;
import android.view.View;

import static com.tifone.spwp.common.tab.log.LogUtil.logd;

public class MyProgressView extends View {
    private Paint mArcPaint;
    private Paint mBackgroundPaint;
    private PorterDuffXfermode mXferMode;

    public MyProgressView(Context context) {
        this(context, null);
    }

    public MyProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mArcPaint.setStrokeWidth(20f);
        mArcPaint.setColor(Color.BLACK);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeCap(Paint.Cap.ROUND);
//        update();

        mBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackgroundPaint.setStyle(Paint.Style.FILL);

        mXferMode = new PorterDuffXfermode(PorterDuff.Mode.XOR);
    }
    private int progress = 0;
    private void update() {
        postDelayed(runnable, 500);
    }
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            progress += 10;
            invalidate();
            if (progress <= 100) {
                update();
            } else {
                progress = 100;
            }

        }
    };

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus) {
            int[] colors = new int[] {Color.BLUE, Color.GREEN, Color.YELLOW, Color.RED};
            LinearGradient gradient = new LinearGradient(10, 10,
                    getWidth(), getHeight(), colors, null, Shader.TileMode.MIRROR);
            mArcPaint.setShader(gradient);
            mArcPaint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.SOLID));
            ValueAnimator animator = ValueAnimator.ofInt(0, 100);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int value = (int) animation.getAnimatedValue();
                    if (value == progress) {
                        return ;
                    }
                    progress  = value;
                    invalidate();
                }
            });
            animator.setInterpolator(new FastOutLinearInInterpolator());
            animator.setDuration(500);
            animator.start();
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        logd("onDraw " + progress);
        canvas.drawColor(Color.YELLOW);
        int count = canvas.saveLayer(0, 0, getWidth(), getHeight(), mBackgroundPaint);
        mBackgroundPaint.setColor(Color.GRAY);
        canvas.drawRect(0, 0, getWidth(), getHeight(), mBackgroundPaint);
        mBackgroundPaint.setXfermode(mXferMode);
        canvas.drawCircle(getWidth()/ 2, getHeight() / 2, getWidth() / 4, mBackgroundPaint);
        mBackgroundPaint.setXfermode(null);

        int angle = (int) (progress / 100f * 360);
        canvas.drawArc(10, 10, getWidth()-10, getHeight()-10, 0, angle, false, mArcPaint);
        canvas.restoreToCount(count);
    }
}
