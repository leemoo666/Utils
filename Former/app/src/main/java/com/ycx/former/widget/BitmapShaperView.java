package com.ycx.former.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by 李小明 on 17/3/17.
 * 邮箱:287907160@qq.com
 */

public class BitmapShaperView extends View {
    private BitmapShader bitmapShader = null;
    private Paint paint = null;

    private Matrix mShaderMatrix = new Matrix();

    private int mBehindWaveColor = Color.parseColor("#28FFFFFF");
    private int mFrontWaveColor = Color.parseColor("#3CFFFFFF");

    private float mDefaultAmplitude;
    private float mDefaultWaterLevel;
    private float mDefaultWaveLength;

    public BitmapShaperView(Context context) {
        super(context);
    }

    public BitmapShaperView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //得到图像
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setStrokeWidth(2);
        paint.setAntiAlias(true);
        paint.setShader(bitmapShader);
        mShaderMatrix.postTranslate(getWidth() * 4, 0);

        bitmapShader.setLocalMatrix(mShaderMatrix);

        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, paint);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        paint = new Paint();
        createShader();
    }

    /**
     * Create the shader with default waves which repeat horizontally, and clamp vertically
     */
    private void createShader() {
        double mDefaultAngularFrequency = 2.0f * Math.PI / getWidth();
        mDefaultAmplitude = getHeight() * 0.05f;  //
        mDefaultWaterLevel = getHeight() * 0.5f; //水高
        mDefaultWaveLength = getWidth();

        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        Paint wavePaint = new Paint();
        wavePaint.setStrokeWidth(2);
        wavePaint.setAntiAlias(true);

        // Draw default waves into the bitmap
        // y=Asin(ωx+φ)+h
        final int endX = getWidth() + 1;
        final int endY = getHeight() + 1;

        float[] waveY = new float[endX];

        Log.i("lxm", "........" + Math.sin(0));
        wavePaint.setColor(mBehindWaveColor);
        for (int beginX = 0; beginX < endX; beginX++) {
            double wx = beginX * mDefaultAngularFrequency;
            float beginY = (float) (mDefaultWaterLevel + mDefaultAmplitude * Math.sin(wx));
            canvas.drawLine(beginX, beginY, beginX, endY, wavePaint);

            waveY[beginX] = beginY;
        }

        wavePaint.setColor(mFrontWaveColor);
        final int wave2Shift = (int) (mDefaultWaveLength / 4);
        for (int beginX = 0; beginX < endX; beginX++) {
            canvas.drawLine(beginX, waveY[(beginX + wave2Shift) % endX], beginX, endY, wavePaint);
        }

        // use the bitamp to create the shader
        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
        paint.setShader(bitmapShader);
    }

}
