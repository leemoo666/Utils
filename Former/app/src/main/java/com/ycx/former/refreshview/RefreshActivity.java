package com.ycx.former.refreshview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.ycx.former.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李小明 on 17/5/22.
 * 邮箱:287907160@qq.com
 */

public class RefreshActivity extends Activity {

    private MListView rvRefresh;

    private ListAdapter adapter;

//    private RefreshAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_view);

        rvRefresh = (MListView) findViewById(R.id.rvRefresh);
//        rvRefresh.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new RefreshAdapter(this);

        adapter = new ListAdapter(this);
        rvRefresh.setAdapter(adapter);
        initData();
    }

    protected void initData()
    {
        List<String> list = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++)
        {
            list.add("" + (char) i);
        }
        adapter.setList(list);
    }

}
