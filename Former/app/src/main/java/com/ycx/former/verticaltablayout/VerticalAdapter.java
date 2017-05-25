package com.ycx.former.verticaltablayout;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ycx.former.R;

import java.util.ArrayList;

import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * Created by 李小明 on 17/3/15.
 * 邮箱:287907160@qq.com
 */

public class VerticalAdapter extends PagerAdapter implements TabAdapter{

    private int[] imagesIds = {
            R.drawable.guide_image_01,
            R.drawable.guide_image_02,
            R.drawable.guide_image_03,
            R.drawable.guide_image_04,

            R.drawable.guide_image_01,
            R.drawable.guide_image_02,
            R.drawable.guide_image_03,
            R.drawable.guide_image_04,

            R.drawable.guide_image_01,
            R.drawable.guide_image_02,
            R.drawable.guide_image_03,
            R.drawable.guide_image_04,

            R.drawable.guide_image_01,
            R.drawable.guide_image_02,
            R.drawable.guide_image_03,
            R.drawable.guide_image_04,

            R.drawable.guide_image_01,
            R.drawable.guide_image_02,
            R.drawable.guide_image_03,
            R.drawable.guide_image_04};

    private ArrayList<String> titles = new ArrayList<>();

    private Context context;

    public VerticalAdapter(Context context, ArrayList<String> titles) {
        this.titles = titles;
        this.context = context;
    }

    @Override
    public int getCount() {
        return imagesIds.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_guide, null);
        ImageView imageView;
        imageView = (ImageView) view.findViewById(R.id.ivItemGuide);
        imageView.setBackgroundResource(imagesIds[position]);
        container.addView(view);
        return view;
    }


/*******************************************/

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
        return new TabView.TabTitle.Builder()
                .setContent(titles.get(position))
                .setTextColor(Color.WHITE, 0xBBFFFF00)
                .build();
    }

    @Override
    public int getBackground(int position) {
        return 0;
    }

/*******************************************/

}
