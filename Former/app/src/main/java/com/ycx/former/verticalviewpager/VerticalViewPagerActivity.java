package com.ycx.former.verticalviewpager;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.ycx.former.R;
import com.ycx.former.widget.VerticalTransformer;
import com.ycx.former.widget.VerticalViewPager;


/**
 * 垂直viewpager
 */

public class VerticalViewPagerActivity extends FragmentActivity {


    private VerticalViewPager verticalViewPager;

    private VerticalPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_viewpager);

        verticalViewPager = (VerticalViewPager) findViewById(R.id.pagerVertical);
        verticalViewPager.setPageTransformer(true, new VerticalTransformer());

        adapter = new VerticalPagerAdapter(getSupportFragmentManager());

        verticalViewPager.setAdapter(adapter);

        verticalViewPager.setPageMargin(30);
        verticalViewPager.setPageMarginDrawable(new ColorDrawable(
                getResources().getColor(android.R.color.holo_green_dark)));

    }
}
