package com.ycx.former.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by 李小明 on 17/3/16.
 * 邮箱:287907160@qq.com
 */

public class ShimmerView extends TextView {

    private float gradientX = 50;

    private LinearGradient linearGradient;

    private Matrix linearGradientMatrix;


    private Paint paint;

    public ShimmerView(Context context) {
        super(context);
        linearGradientMatrix = new Matrix();
        paint = new Paint();
    }

    public ShimmerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        linearGradientMatrix = new Matrix();
        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(5);
        paint.setTextSize(80);
    }

    //初始化线性渐变
    private void resetLinearGradient() {
        linearGradient = new LinearGradient(0, 0, getMeasuredWidth() / 3, 0,
                new int[]{Color.WHITE, Color.GRAY, Color.WHITE},
                new float[]{0, 0.5f, 1},
                Shader.TileMode.CLAMP
        );

        paint.setShader(linearGradient);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        resetLinearGradient();
        animator = new ValueAnimator().ofInt(-getMeasuredWidth() / 3, getMeasuredWidth() );
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);

        canvas.drawText("毛澤東思想,鄧小平理論,江澤民三個代表,胡錦濤八榮八恥", 0, getHeight() / 2, paint);

        linearGradientMatrix.setTranslate(grentX, 0);

        // this is required in order to invalidate the shader's position
        linearGradient.setLocalMatrix(linearGradientMatrix);

        Log.i("lxm",".....grentX = "+grentX);

    }

    int grentX = 10;

    ValueAnimator animator;

    public void startAnim() {
        animator.setDuration(2000);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(Integer.MAX_VALUE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                grentX  = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();
    }
}
