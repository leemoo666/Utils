package com.ycx.former.recycleview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ycx.former.R;

/**
 * Created by 李小明 on 17/3/21.
 * 邮箱:287907160@qq.com
 */

public class RecycleActivity extends FragmentActivity {
    private RecyclerView mRecyclerView;
    private VerticalListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);


        mRecyclerView = (RecyclerView) findViewById(R.id.rvRecycleView);
//设置布局管理器
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
//设置adapter
        adapter = new VerticalListAdapter(this);
        mRecyclerView.setAdapter(adapter);

        //设置分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
//设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//添加分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.HORIZONTAL));
    }
}
