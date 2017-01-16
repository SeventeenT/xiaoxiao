package com.xiaoxiao.mainlayout.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xiaoxiao.mainlayout.R;
import com.xiaoxiao.mainlayout.base.MyBaseAdapter;

import java.util.ArrayList;

import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by Administrator 2016/8/4.
 */
public class PhotoGridAdapter extends MyBaseAdapter<PhotoInfo> {
    public PhotoGridAdapter(ArrayList<PhotoInfo> list, Context context) {
        super(list, context);
    }

    @Override
    public int getCount() {
        if (list.size() == 3) {
            return 3;
        }
        return (list.size() + 1);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        Log.d("PhotoGridAdapter", "getView==="+position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_grid_photo, parent, false);
            viewHolder.img_photo = (ImageView) convertView.findViewById(R.id.img_photo);
            viewHolder.img_close = (ImageView) convertView.findViewById(R.id.img_close);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (position ==list.size()) {
            viewHolder.img_close.setVisibility(View.GONE);
            Glide.with(context).load(R.drawable.add_image).into(viewHolder.img_photo);
        } else {
            viewHolder.img_close.setVisibility(View.VISIBLE);
            Glide.with(context).load(list.get(position).getPhotoPath())
                    .error(R.drawable.no_data).into(viewHolder.img_photo);
            Log.d("PhotoGridAdapter", "PhotoPath="+list.get(position).getPhotoPath());
        }

        viewHolder.img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    class ViewHolder {
        ImageView img_photo;
        ImageView img_close;
    }

}
