package com.ycx.former.tablinkviewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ycx.former.verticalviewpager.OneFragment;

import java.util.ArrayList;

/**
 * Created by 李小明 on 17/3/15.
 * 邮箱:287907160@qq.com
 */

public class VerticalPagerAdapter extends FragmentStatePagerAdapter{

    private ArrayList<Fragment> list = new ArrayList<>();

    ArrayList<String> titles = new ArrayList<>();


    public VerticalPagerAdapter(FragmentManager fm) {
        super(fm);

        for (int i = 0; i < 20; i++) {
            titles.add("第" + i + "个");
            list.add(new OneFragment());
        }
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

}
