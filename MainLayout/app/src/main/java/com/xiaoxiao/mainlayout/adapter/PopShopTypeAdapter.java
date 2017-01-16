package com.xiaoxiao.mainlayout.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaoxiao.mainlayout.R;
import com.xiaoxiao.mainlayout.base.MyBaseAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator 2016/7/14.
 */
public class PopShopTypeAdapter extends MyBaseAdapter <String> {
    private int index;

    public PopShopTypeAdapter(ArrayList<String> list, Context context,int index) {
        super(list, context);
        this.index = index;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_pop_shop_type, parent, false);
            viewHolder.tv_pop_type = (TextView) convertView.findViewById(R.id.tv_pop_type);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_pop_type.setText(list.get(position));
        if (index == position) {
            viewHolder.tv_pop_type.setTextColor(context.getResources().getColor(R.color.main_red));
        } else {
            viewHolder.tv_pop_type.setTextColor(context.getResources().getColor(R.color.text_grey));
        }
        return convertView;
    }

    class ViewHolder {
        TextView tv_pop_type;
    }

}
