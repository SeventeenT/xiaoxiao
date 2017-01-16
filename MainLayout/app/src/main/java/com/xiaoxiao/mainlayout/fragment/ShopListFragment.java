package com.xiaoxiao.mainlayout.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaoxiao.mainlayout.R;
import com.xiaoxiao.mainlayout.base.BaseFragment;

import butterknife.Bind;

/**
 * A simple  subclass.
 */
@SuppressLint("ValidFragment")
public class ShopListFragment extends BaseFragment {
    TextView tv_text;


//    String text;
/*
    public ShopListFragment (String string) {
        this.text = string;
    }*/

    @Override
    protected View getChildView(LayoutInflater inflater, ViewGroup container) {
        View view  = inflater.inflate(R.layout.fragment_shop_list, container, false);
        tv_text = (TextView) view.findViewById(R.id.tv_text);
        return view;
    }

    @Override
    protected void initView() {
        Bundle bundle = getArguments();
        String text = bundle.getString("string");
        tv_text.setText(text);

    }

    @Override
    protected void initData() {

    }

    public static ShopListFragment newInstance(String string) {

        Bundle args = new Bundle();
        args.putString("string", string);
        ShopListFragment fragment = new ShopListFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
