package com.tifone.spwp.view.custom;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import com.tifone.spwp.view.R;


public class MyCursorView extends View {
    private OnValueChangeListener mOnValueChangeListener;
    private float viewWidth;
    private float viewHeight;
    private Bitmap cursorMap;
    private float cursorLocation;
    private int cursorColor = Color.RED;
    //the width of big scale
    private int cursorWidth = 6;
    //the width of small scale
    private int scaleWidth = 2;
    //the default number of the view to show the big scale
    private int visibleItemCount = 3;
    //the start location of the ruler
    private float currLocation = 0;
    //the current location of the ruler
    private int mCurrentLocation = 0;
    //the max value of the scale
    private int maxValue = 210;
    //the min value of the scale
    private int minValue = 0;

    private int oneItemValue = 1;
    //the width between the two scale,it decide by visibleItemCount
    private int scaleDistance;
    //the default height of the scale
    private float scaleHeight = 180;
    //the color of the scale text
    private int scaleTextColor = Color.BLACK;
    //the size of the scale text
    private int scaleTextSize = 24;
    private GestureDetector mGestureDetector;
    private Scroller mScroller;
    private int maxX;
    private int minX;

    private boolean mDisableCurrentView;
    private Paint mNeedlePaint;
    private Paint mCursorPaint;

    public MyCursorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MyCursorView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyCursorView);
        visibleItemCount = ta.getInteger(R.styleable.MyCursorView_maxShowItem, visibleItemCount);
        maxValue = ta.getInteger(R.styleable.MyCursorView_maxValue, maxValue);
        minValue = ta.getInteger(R.styleable.MyCursorView_maxValue, minValue);
        oneItemValue = ta.getInteger(R.styleable.MyCursorView_oneItemValue, oneItemValue);
        scaleTextColor = ta.getColor(R.styleable.MyCursorView_scaleTextColor, scaleTextColor);
        cursorColor = ta.getColor(R.styleable.MyCursorView_cursorColor, cursorColor);
        int cursorMapId = ta.getResourceId(R.styleable.MyCursorView_cursorBitmap, -1);
        if (cursorMapId != -1) {
            cursorMap = BitmapFactory.decodeResource(getResources(), cursorMapId);
        }
        ta.recycle();
        mScroller = new Scroller(context);
//        mScheduler = Executors.newScheduledThreadPool(2);
        mGestureDetector = new GestureDetector(context, gestureListener);

        initPaints();
    }
    private void initPaints() {
        mNeedlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCursorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCursorPaint.setColor(Color.BLACK);
        mCursorPaint.setStrokeWidth(4f);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewHeight = MeasureSpec.getSize(heightMeasureSpec);
        scaleDistance = getMeasuredWidth() / (visibleItemCount * 6);
        //the count of the scale * the width of a scale
        viewWidth = getItemsCount() * scaleDistance;
        maxX = getItemsCount() * scaleDistance;
        minX = -maxX;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // set the display area
        canvas.clipRect(getPaddingStart(), getPaddingTop(), getWidth() - getPaddingRight(), viewHeight - getPaddingBottom());
        drawCursor(canvas);
        for (int i = 0; i < (maxValue - minValue) / oneItemValue; i++) {
            drawScale(canvas, i, -1);
        }
        for (int i = 0; i < (maxValue - minValue) / oneItemValue; i++) {
            drawScale(canvas, i, 1);
        }
    }

    private void drawCursor(Canvas canvas) {
        cursorLocation = visibleItemCount / 2 * 5 * scaleDistance;
        if (cursorMap == null) {
            mNeedlePaint.setStrokeWidth(cursorWidth);
            mNeedlePaint.setColor(getResources().getColor(R.color.color_cursor));
            canvas.drawLine(cursorLocation, getPaddingTop() - getPaddingBottom(), cursorLocation, viewHeight - getPaddingBottom(), mNeedlePaint);
        } else {
            float left = cursorLocation - cursorMap.getWidth() / 2;
            float top = getPaddingTop() - getPaddingBottom();
            float right = cursorLocation + cursorMap.getWidth() / 2;
            float bottom = viewHeight - getPaddingBottom();
            RectF rectF = new RectF(left, top, right, bottom);
            canvas.drawBitmap(cursorMap, null, rectF, mNeedlePaint);
        }
    }

    private void drawScale(Canvas canvas, int value, int type) {
        if (currLocation + visibleItemCount / 2 * 5 * scaleDistance >= viewWidth) {
            currLocation = -visibleItemCount / 2 * 5 * scaleDistance;
            float speed = mScroller.getCurrVelocity();
            mScroller.fling((int) currLocation, 0, (int) speed, 0, minX, maxX, 0, 0);
            setNextMessage(0);
        } else if (currLocation - visibleItemCount / 2 * 5 * scaleDistance <= -viewWidth) {
            currLocation = visibleItemCount / 2 * 5 * scaleDistance;
            float speed = mScroller.getCurrVelocity();
            mScroller.fling((int) currLocation, 0, (int) speed, 0, minX, maxX, 0, 0);
            setNextMessage(0);
        }
        float location = cursorLocation - currLocation + value * scaleDistance * type;
        if ((value + 5) % 10 == 0) {
            mCursorPaint.setAlpha(255);
            canvas.drawLine(location, viewHeight - scaleHeight - getPaddingBottom() - 160, location, viewHeight - getPaddingBottom() - 160, mCursorPaint);
            Paint paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
            paintText.setColor(scaleTextColor);
            paintText.setTextSize(scaleTextSize);
            if (type < 0) {
                value = ((maxValue - minValue) / oneItemValue - value) * oneItemValue;
                if (value == maxValue) {
                    value = 0;
                }
            } else {
                value = value * oneItemValue;
            }
            String drawStr = String.valueOf(value/10 + 88);
            Rect bounds = new Rect();
            if (location - bounds.width() / 2 < 0 || location - bounds.width() / 2 > getWidth()) {
                return;
            }
            paintText.getTextBounds(drawStr, 0, drawStr.length(), bounds);
            canvas.drawText(drawStr, location - bounds.width() / 2, viewHeight - getPaddingBottom() - 120, paintText);
        } else {
            mCursorPaint.setAlpha(30);
            canvas.drawLine(location, viewHeight - scaleHeight * 2 / 3 - getPaddingBottom() - 180, location, viewHeight - getPaddingBottom() - 180, mCursorPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mDisableCurrentView) {
            return false;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_UP:
                getIntegerPosition();
                getParent().requestDisallowInterceptTouchEvent(false);
                if (mOnValueChangeListener != null) {
                    mOnValueChangeListener.OnValueStopChanged(getCurrLocation());
                }
                break;
        }
        mGestureDetector.onTouchEvent(event);
        return true;
    }

    private GestureDetector.SimpleOnGestureListener gestureListener = new GestureDetector.SimpleOnGestureListener() {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            scrollView(distanceX);
            return true;
        }

        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (!mScroller.computeScrollOffset()) {
                mScroller.fling((int) currLocation, 0, (int) (-velocityX / 1.5), 0, minX, maxX, 0, 0);
                setNextMessage(0);
            }
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return super.onSingleTapUp(e);
        }
    };

    public void scrollView(float distance) {
        currLocation += distance;
        setCurrLocation(currLocation);
    }

    private void getIntegerPosition() {
        int currentItem = (int) (currLocation / scaleDistance);
        float fraction = currLocation - currentItem * scaleDistance;
        if (Math.abs(fraction) > 0.5 * scaleDistance) {
            if (fraction < 0) {
                currLocation = (currentItem - 1) * scaleDistance;
            } else {
                currLocation = (currentItem + 1) * scaleDistance;
            }
        } else {
            currLocation = currentItem * scaleDistance;
        }
        setCurrLocation(currLocation);
    }

    public int getItemsCount() {
        return (maxValue - minValue) / oneItemValue;
    }

    public void setCursorColor(int color) {
        this.cursorColor = color;
        invalidate();
    }

    public void setCursorWidth(int width) {
        this.cursorWidth = width;
        invalidate();
    }

    public void setCursorMap(Bitmap cursorMap) {
        this.cursorMap = cursorMap;
        invalidate();
    }

    public void setScaleWidth(int scaleWidth) {
        this.scaleWidth = scaleWidth;
        invalidate();
    }

    public void setVisibleItemCount(int visibleItemCount) {
        this.visibleItemCount = visibleItemCount;
        invalidate();
    }

    private void setCurrLocation(float currLocation) {
        this.currLocation = currLocation;
        int currentItem = (int) (currLocation / scaleDistance) * oneItemValue;
        if (mOnValueChangeListener != null) {
            if (currentItem < 0) {
                currentItem = (maxValue - minValue) + currentItem;
            }
            mOnValueChangeListener.OnValueChange(currentItem);
        }
        mCurrentLocation = currentItem;
        invalidate();
    }

    public int getCurrLocation() {
        return mCurrentLocation;
    }

    public void setScaleHeight(float scaleHeight) {
        this.scaleHeight = scaleHeight;
        invalidate();
    }

    public void setScaleTextColor(int scaleTextColor) {
        this.scaleTextColor = scaleTextColor;
        invalidate();
    }

    public void setScaleTextSize(int scaleTextSize) {
        this.scaleTextSize = scaleTextSize;
        invalidate();
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
        invalidate();
    }

    public void setOneItemValue(int oneItemValue) {
        this.oneItemValue = oneItemValue;
        invalidate();
    }

    public void setCurrentValue(int currValue) {
        if (currLocation < 0) {
            currLocation = 0;
        } else if (currLocation > maxValue) {
            currLocation = maxValue;
        }
        this.currLocation = currValue;
        invalidate();
    }

    private void setNextMessage(int message) {
        animationHandler.removeMessages(0);
        animationHandler.sendEmptyMessage(message);
    }

    private Handler animationHandler = new Handler() {
        public void handleMessage(Message msg) {
            mScroller.computeScrollOffset();
            int currX = mScroller.getCurrX();
            float delta = currX - currLocation;
            if (delta != 0) {
                scrollView(delta);
            }
            if (mScroller.isFinished()) {
                getIntegerPosition();
            }
        }
    };

    public void setOnValueChangeListener(OnValueChangeListener onValueChangeListener) {
        mOnValueChangeListener = onValueChangeListener;
    }

    public interface OnValueChangeListener {
        void OnValueChange(int newValue);
        void OnValueStopChanged(int newValue);
    }

    public void setDisableLoopScaleView(boolean disableView) {
        mDisableCurrentView = disableView;
    }
}
