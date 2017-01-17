package com.xiaoxiao.mainlayout.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.xiaoxiao.mainlayout.R;
import com.xiaoxiao.mainlayout.adapter.RecyclerViewAdapter;
import com.xiaoxiao.mainlayout.base.BaseActivity;
import com.xiaoxiao.mainlayout.entity.ShopRecyclerBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator 2017/1/17.
 */

public class RecyclerViewActivity extends BaseActivity {

    private List<ShopRecyclerBean> mData;
    private RecyclerViewAdapter mAdapter;

    @Bind(R.id.tv_base_title)
    TextView tv_base_title;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    public void initIntentData() {
        setChildLayout(R.layout.activity_recyclerview);
    }

    @Override
    public void initView() {
        tv_base_title.setText("RecyclerView");
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mData = generateData(50);
        mAdapter = new RecyclerViewAdapter(mData);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {

    }

    private List<ShopRecyclerBean> generateData (int number) {
        ArrayList<ShopRecyclerBean> data = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            data.add(new ShopRecyclerBean(R.drawable.default_head,"商品名称 "+i,"￥100"+i,"限量"+i+"份"));
        }
        return data;
    }

}
