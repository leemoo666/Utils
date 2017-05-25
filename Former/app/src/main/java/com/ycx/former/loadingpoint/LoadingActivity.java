package com.ycx.former.loadingpoint;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.ycx.former.R;

/**
 * Created by 李小明 on 17/4/5.
 * 邮箱:287907160@qq.com
 */

public class LoadingActivity extends FragmentActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadingpoint);
    }
}
