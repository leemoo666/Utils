package com.ycx.former.tablinkviewpager;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.ycx.former.R;
import com.ycx.former.widget.VerticalTransformer;
import com.ycx.former.widget.VerticalViewPager;

import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.widget.TabView;


/**
 * 垂直viewpager和垂直tabLayout联动
 *
 * 参考https://github.com/soulrelay/VerticalViewPagerWithTabLayout.git
 * */
public class VerticalLinkActivity extends AppCompatActivity {

    private VerticalTabLayout tabLayout;

    private MyTabAdapter myTabAdapter;

    private VerticalViewPager viewPager;

    private VerticalPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_link);

        tabLayout = (VerticalTabLayout) findViewById(R.id.vtlLink);

        myTabAdapter = new MyTabAdapter();
        tabLayout.setTabAdapter(myTabAdapter);

        viewPager = (VerticalViewPager) findViewById(R.id.vpLink);
        adapter = new VerticalPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        viewPager.setPageTransformer(true, new VerticalTransformer());
        viewPager.setPageMargin(10);
        viewPager.setPageMarginDrawable(new ColorDrawable(
                getResources().getColor(android.R.color.holo_green_dark)));

        tabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.setTabSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
