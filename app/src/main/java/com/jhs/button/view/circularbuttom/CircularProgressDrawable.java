package com.jhs.button.view.circularbuttom;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

class CircularProgressDrawable extends Drawable {

    private float mSweepAngle;//扫过的角度
    private float mStartAngle;//开始的角度
    private int mSize;//圆形进度条的大小
    private int mStrokeWidth;//进度条的宽度
    private int mStrokeColor;//进度条的颜色


    private RectF mRectF;
    private Paint mPaint;
    private Path mPath;


    public CircularProgressDrawable(int size, int strokeWidth, int strokeColor) {
        mSize = size;
        mStrokeWidth = strokeWidth;
        mStrokeColor = strokeColor;
        mStartAngle = -90;
        mSweepAngle = 0;
    }

    public void setSweepAngle(float sweepAngle) {
        mSweepAngle = sweepAngle;
    }

    public int getSize() {
        return mSize;
    }

    @Override
    public void draw(Canvas canvas) {
        final Rect bounds = getBounds();

        if (mPath == null) {
            mPath = new Path();
        }
        mPath.reset();//清除path设置的所有属性
        mPath.addArc(getRect(), mStartAngle, mSweepAngle);//绘制圆弧，这个圆弧取自RectF矩形的内接椭圆上的一部分，后两个参数表示圆弧的长度

        mPath.offset(bounds.left, bounds.top);
        canvas.drawPath(mPath, createPaint());
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter cf) {
    }

    @Override
    public int getOpacity() {
        return 1;
    }


    private RectF getRect() {
        if (mRectF == null) {
            int index = mStrokeWidth / 2;
            mRectF = new RectF(index, index, getSize() - index, getSize() - index);
        }
        return mRectF;
    }

    private Paint createPaint() {
        if (mPaint == null) {
            mPaint = new Paint();
            mPaint.setAntiAlias(true);//抗锯齿
            mPaint.setStyle(Paint.Style.STROKE);//FILL，STROKE，FILL_AND_STROKE
            mPaint.setStrokeWidth(mStrokeWidth);
            mPaint.setColor(mStrokeColor);
        }

        return mPaint;
    }
}
