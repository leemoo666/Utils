package com.ycx.former.headerviewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ycx.former.headerviewpager.fragment.GridViewFragment;
import com.ycx.former.headerviewpager.fragment.ListViewFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李小明 on 17/5/17.
 * 邮箱:287907160@qq.com
 */

public class ContentAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public ContentAdapter(FragmentManager fm) {
        super(fm);
        //内容的fragment
        fragments = new ArrayList<>();
        fragments.add(ListViewFragment.newInstance());
        fragments.add(GridViewFragment.newInstance());
    }

    public String[] titles = new String[]{"ScrollView", "ListView", "GridView", "RecyclerView", "WebView"};

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}

