package com.ycx.former.refreshview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.ycx.former.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lixiaoming on 16/5/3.
 */
public class ListAdapter extends BaseAdapter {

    private Context context;

    private List<String> list = new ArrayList<>();

    private LayoutInflater inflater;

    public ListAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setList(List<String> list) {
        if (list != null) {
            this.list = list;
            notifyDataSetChanged();
        }
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
            convertView = inflater.inflate(R.layout.item_refresh, parent, false);
            holder = new ViewHolder();
            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.tvName.setText(list.get(position));

        return convertView;
    }

    public static class ViewHolder {
        public TextView tvName;
    }
}
