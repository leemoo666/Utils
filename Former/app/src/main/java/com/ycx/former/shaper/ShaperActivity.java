package com.ycx.former.shaper;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.ycx.former.R;
import com.ycx.former.widget.WaveView;

/**
 * Created by 李小明 on 17/3/17.
 * 邮箱:287907160@qq.com
 */

public class ShaperActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shaper);

        final WaveView waveView = (WaveView) findViewById(R.id.wave);
        waveView.setBorder(mBorderWidth, mBorderColor);

        mWaveHelper = new WaveHelper(waveView);
    }


    private WaveHelper mWaveHelper;

    private int mBorderColor = Color.parseColor("#44FFFFFF");
    private int mBorderWidth = 10;



    @Override
    protected void onPause() {
        super.onPause();
        mWaveHelper.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWaveHelper.start();
    }
}
