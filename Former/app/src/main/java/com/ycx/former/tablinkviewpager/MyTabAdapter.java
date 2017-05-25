package com.ycx.former.tablinkviewpager;

import android.graphics.Color;

import java.util.ArrayList;

import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * Created by 李小明 on 17/3/15.
 * 邮箱:287907160@qq.com
 */

public class MyTabAdapter implements TabAdapter {
    ArrayList<String> titles = new ArrayList<>();

    public MyTabAdapter() {

        for (int i = 0; i < 20; i++) {
            titles.add("第" + i + "个");
        }

    }

    @Override
    public TabView.TabBadge getBadge(int position) {
        return null;
    }

    @Override
    public TabView.TabIcon getIcon(int position) {
        return null;
    }

    @Override
    public TabView.TabTitle getTitle(int position) {
        return  new TabView.TabTitle.Builder()
                .setContent(titles.get(position))
                .setTextColor(Color.WHITE, 0xBBFFFF00)
                .build();
    }

    @Override
    public int getBackground(int position) {
        return 0;
    }

    @Override
    public int getCount() {
        return titles.size();
    }
}
