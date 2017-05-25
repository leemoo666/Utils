package com.ycx.former.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ycx.former.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李小明 on 17/3/21.
 * 邮箱:287907160@qq.com
 */

public class VerticalListAdapter extends RecyclerView.Adapter<VerticalListAdapter.ViewHolder> {

    private Context context;
    private List<String> mDatas = new ArrayList<>();

    public VerticalListAdapter(Context context) {
        this.context = context;
        initData();
    }

    protected void initData() {
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_vertical_recycle, parent,
                false));
        Log.i("lxm", "onCreateViewHolder");
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.i("lxm", "onBindViewHolder = " + position);
        holder.tvItemVertical.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
//        Log.i("lxm", "getItemCount");
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvItemVertical;

        public ViewHolder(View itemView) {
            super(itemView);
            Log.i("lxm", "ViewHolder");

            tvItemVertical = (TextView) itemView.findViewById(R.id.tvItemVertical);
        }
    }

}
