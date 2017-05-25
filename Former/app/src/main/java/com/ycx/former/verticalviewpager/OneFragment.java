package com.ycx.former.verticalviewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ycx.former.R;

import java.util.ArrayList;

/**
 * Created by 李小明 on 17/3/15.
 * 邮箱:287907160@qq.com
 */

public class OneFragment extends Fragment {

    private ListView listView;

    private OneAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        listView = (ListView) view.findViewById(R.id.lvOneFragment);

        adapter = new OneAdapter(getActivity());

        listView.setAdapter(adapter);

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("這是第" + i + "條數據");
        }

        adapter.setList(list);
        return view;
    }
}
