package com.ycx.former.banner;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.ycx.former.R;

/**
 */
public class BannerLine extends View {

    private Paint mPaint;  //画笔
    private float mWidth;  //总长度
    private int mPageSize; //页数
    private float mPageWidth = 0f; //每一个item长度
    private int mPosition;  //位置
    private float mPositionOffset;//偏移量
    private int mLineColor = ContextCompat.getColor(getContext(), R.color.colorPrimaryDark);

    public BannerLine(Context context) {
        this(context, null);
    }

    public BannerLine(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        mWidth = dm.widthPixels;
        mPaint = new Paint();
        mPaint.setColor(mLineColor);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(1000f);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (mPosition == 0) {
            float startX = (mPageSize - 3) * mPageWidth + mPageWidth * mPositionOffset;
            float startY = startX + mPageWidth;
            canvas.drawLine(startX, 0, startY, 0, mPaint);
            canvas.drawLine(0, 0, mPageWidth * mPositionOffset, 0, mPaint);
        } else if (mPosition == mPageSize - 2) {
            float startX = (mPosition - 1) * mPageWidth + mPageWidth * mPositionOffset;
            float startY = startX + mPageWidth;

            canvas.drawLine(startX, 0, startY, 0, mPaint);
            canvas.drawLine(0, 0, mPageWidth * mPositionOffset, 0, mPaint);
        } else {
            float startX = (mPosition - 1) * mPageWidth + mPageWidth * mPositionOffset;
            float startY = startX + mPageWidth;

            canvas.drawLine(startX, 0, startY, 0, mPaint);
        }

    }

    public void setPageSize(int pageSize) {
        mPageSize = pageSize;
        calcPageWidth();
    }

    private void calcPageWidth() {
        this.mPageWidth = this.mWidth / (this.mPageSize - 2);
    }

    public void setPageScrolled(int position, float positionOffset) {
        mPosition = position;
        mPositionOffset = positionOffset;
        invalidate();
    }

    public void setLineColor(int lineColor) {
        mLineColor = lineColor;
        mPaint.setColor(mLineColor);
    }
}
