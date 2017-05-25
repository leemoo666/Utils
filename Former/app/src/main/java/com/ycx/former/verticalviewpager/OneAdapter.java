package com.ycx.former.verticalviewpager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ycx.former.R;

import java.util.ArrayList;

/**
 * Created by 李小明 on 17/3/15.
 * 邮箱:287907160@qq.com
 */

public class OneAdapter extends BaseAdapter {
    ArrayList<String> list = new ArrayList<>();
    Context context;
    LayoutInflater inflater;

    public OneAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_one_fragment, parent, false);
            holder = new ViewHolder();
            holder.tvItemOneFragment = (TextView) convertView.findViewById(R.id.tvItemOneFragment);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.tvItemOneFragment.setText(list.get(position));

        return convertView;
    }

    public static class ViewHolder {
        public TextView tvItemOneFragment;
    }
}
