package com.ycx.former.banner;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ycx.former.R;
import com.ycx.former.login.LoginActivity;

import java.util.ArrayList;

/**
 * Created by 李小明 on 17/3/15.
 * 邮箱:287907160@qq.com
 */

public class BannerAdapter extends PagerAdapter {
    ArrayList<String> list = new ArrayList<>();
    ArrayList<Integer> listIds = new ArrayList<>();
    Context context;

    public BannerAdapter(Context context) {
        this.context = context;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
        init();
        notifyDataSetChanged();
    }

    public void setIds(ArrayList<Integer> listIds) {
        this.listIds = listIds;
        notifyDataSetChanged();
    }

    private ArrayList<View> views = new ArrayList<>();

    private void init() {
        for (int i = 0; i < list.size(); i++) {
            views.add(LayoutInflater.from(context).inflate(R.layout.item_banner, null));
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = views.get(position);
        ImageView imageView = (ImageView) view.findViewById(R.id.ivItemBanner);
        imageView.setBackgroundResource(listIds.get(position));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
