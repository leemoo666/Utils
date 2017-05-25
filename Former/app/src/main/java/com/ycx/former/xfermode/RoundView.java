package com.ycx.former.xfermode;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.ycx.former.R;

/**
 * Created by 李小明 on 17/5/12.
 * 邮箱:287907160@qq.com
 */

public class RoundView extends ImageView {

    public RoundView(Context context) {
        super(context);
    }

    public RoundView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private int mWidth;
    private int mHeight;
    private Paint mPaint;
    private Bitmap CircleBitmap;

    private void ImgCircle() {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.FILL);
        CircleBitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(CircleBitmap);
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = Math.min(getMeasuredWidth(), getMeasuredHeight());
        Log.i("lxm", "width = " + width);
        setMeasuredDimension(width, width);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setFilterBitmap(true);
        ImgCircle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.YELLOW);
        canvas.drawCircle(200, 200, 200, mPaint);
        canvas.translate(50,0);
        int count = canvas.saveLayerAlpha(0, 0, mWidth, mHeight, 250, Canvas.HAS_ALPHA_LAYER_SAVE_FLAG);
        canvas.drawBitmap(CircleBitmap, 0, 0, mPaint);
        canvas.restoreToCount(count);

        canvas.drawRect(0,200,200,400,mPaint);
    }
}
