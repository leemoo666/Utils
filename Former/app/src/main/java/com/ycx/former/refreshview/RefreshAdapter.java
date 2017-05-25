package com.ycx.former.refreshview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ycx.former.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李小明 on 17/5/22.
 * 邮箱:287907160@qq.com
 */

public class RefreshAdapter extends RecyclerView.Adapter<RefreshAdapter.MyViewHolder> {

    private Context context;
    private List<String> list = new ArrayList<>();

    public RefreshAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_refresh, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.tvName);
        }
    }
}

