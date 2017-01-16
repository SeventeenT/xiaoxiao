package com.xiaoxiao.mainlayout.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by xuwenting 2016/4/8.
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter{

    public ArrayList<T> list=new ArrayList<T>();
    public LayoutInflater inflater;
    public Context context;


    public MyBaseAdapter(ArrayList<T> list, Context context) {
        this.list = list;
        this.context = context;
        inflater=LayoutInflater.from(context);
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

    public void changeList(ArrayList<T> change_list,TypePull typePull){
        if (typePull== TypePull.PULLdown){
            list.clear();
        }
        list.addAll(change_list);
        notifyDataSetChanged();
    }

    public enum TypePull{
        PULLdown,PULLUP
    }

}
