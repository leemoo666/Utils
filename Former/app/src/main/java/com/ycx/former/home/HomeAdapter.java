package com.ycx.former.home;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.ycx.former.R;
import com.ycx.former.login.LoginActivity;

import java.util.ArrayList;

/**
 * Created by 李小明 on 17/3/15.
 * 邮箱:287907160@qq.com
 */

public class HomeAdapter extends PagerAdapter {

    private int[] imagesIds = {R.drawable.guide_image_01,
            R.drawable.guide_image_02,
            R.drawable.guide_image_03,
            R.drawable.guide_image_04};

    private ArrayList<String> titles = new ArrayList<>();

    private Context context;

    public HomeAdapter(Context context, ArrayList<String> titles) {
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

        Button button = (Button) view.findViewById(R.id.btItemGuide);
        if (position == imagesIds.length - 1) {
            button.setVisibility(View.VISIBLE);
        } else {
            button.setVisibility(View.GONE);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
