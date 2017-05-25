package com.ycx.former.headerviewpager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.ycx.former.R;


/**
 * Created by 李小明 on 17/5/17.
 * 邮箱:287907160@qq.com
 */

public class HeaderViewPagerActivity extends FragmentActivity {

    private ViewPager viewPager;
    private ContentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_viewpager);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new ContentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }
}
