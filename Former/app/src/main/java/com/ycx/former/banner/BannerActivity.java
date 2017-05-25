package com.ycx.former.banner;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.ycx.former.R;

import java.util.ArrayList;

/**
 * Created by 李小明 on 17/3/15.
 * 邮箱:287907160@qq.com
 *
 * 参考蜻蜓Banner  https://github.com/JeasonWong/QingtingBannerView.git
 */

public class BannerActivity extends FragmentActivity {

    private ViewPager vpBanner;

    private BannerAdapter adapter;

    ArrayList<String> list = new ArrayList<>();
    ArrayList<Integer> listIds = new ArrayList<>();

    private BannerLine mLine;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        vpBanner = (ViewPager) findViewById(R.id.vpBanner);

        adapter = new BannerAdapter(this);

        vpBanner.setAdapter(adapter);


        listIds.add(R.drawable.guide_image_04);

        listIds.add(R.drawable.guide_image_01);
        listIds.add(R.drawable.guide_image_02);
        listIds.add(R.drawable.guide_image_03);
        listIds.add(R.drawable.guide_image_04);

        listIds.add(R.drawable.guide_image_01);
        list.add("这是第" + 0 + "条数据");
        for (int i = 1; i < 5; i++) {
            list.add("这是第" + i + "条数据");
        }
        list.add("这是第" + 5 + "条数据");

        adapter.setList(list);
        adapter.setIds(listIds);

        initViewPager();


        mLine = (BannerLine) findViewById(R.id.blBanner);
        mLine.setLineColor(Color.RED);
        mLine.setPageSize(6);
    }


    private void initViewPager() {
        vpBanner.setCurrentItem(1);
        vpBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                Log.i("lxm","position = "+position+".....offset = "+positionOffset+" ....pixel = "+positionOffsetPixels);
                mLine.setPageScrolled(position, positionOffset);
                if (positionOffsetPixels == 0.0) {
                    if (position == listIds.size() - 1) {
                        vpBanner.setCurrentItem(1, false);
                    } else if (position == 0) {
                        vpBanner.setCurrentItem(listIds.size() - 2, false);
                    } else {
                        vpBanner.setCurrentItem(position);
                    }
                }
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
