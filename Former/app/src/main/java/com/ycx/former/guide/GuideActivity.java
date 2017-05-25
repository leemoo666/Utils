package com.ycx.former.guide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.ycx.former.R;

import github.chenupt.springindicator.SpringIndicator;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by 李小明 on 17/3/15.
 * 邮箱:287907160@qq.com
 *
 * 参考https://github.com/ongakuer/CircleIndicator
 */

public class GuideActivity extends FragmentActivity {

    private ViewPager viewPager;
    private GuideAdapter adapter;

    private CircleIndicator indicator;

    //    private SpringIndicator indicator;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        viewPager = (ViewPager) findViewById(R.id.vpGuide);
        adapter = new GuideAdapter(this);
        viewPager.setAdapter(adapter);


        indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);

//        indicator = (SpringIndicator) findViewById(R.id.indicatorGuide);
//
//        indicator.setViewPager(viewPager);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i("lxm","position = "+position+".....offset = "+positionOffset+" ....pixel = "+positionOffsetPixels);

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
