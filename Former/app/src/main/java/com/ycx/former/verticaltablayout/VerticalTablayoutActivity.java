package com.ycx.former.verticaltablayout;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.ycx.former.R;

import java.util.ArrayList;

import q.rorbin.verticaltablayout.VerticalTabLayout;

/**
 * 垂直tabLayout
 *
 *
 * */
public class VerticalTablayoutActivity extends AppCompatActivity {

    private ViewPager viewPager;

    private VerticalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical);
        viewPager = (ViewPager) findViewById(R.id.vpVertical);

        ArrayList<String> titles = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            titles.add("第" + i + "个");
        }
        adapter = new VerticalAdapter(this, titles);

        viewPager.setAdapter(adapter);

        VerticalTabLayout tablayout = (VerticalTabLayout) findViewById(R.id.vtlVertical);
        tablayout.setupWithViewPager(viewPager);

    }
}
