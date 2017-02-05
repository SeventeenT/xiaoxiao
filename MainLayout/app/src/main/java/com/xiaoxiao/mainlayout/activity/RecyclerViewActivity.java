package com.xiaoxiao.mainlayout.activity;

import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.liaoinstan.springview.container.MeituanFooter;
import com.liaoinstan.springview.container.MeituanHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.xiaoxiao.mainlayout.R;
import com.xiaoxiao.mainlayout.adapter.RecyclerViewAdapter;
import com.xiaoxiao.mainlayout.base.BaseActivity;
import com.xiaoxiao.mainlayout.entity.ShopRecyclerBean;
import com.xiaoxiao.mainlayout.utils.Toasts;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator 2017/1/17.
 */

public class RecyclerViewActivity extends BaseActivity {

    private List<ShopRecyclerBean> mData;
    private RecyclerViewAdapter mAdapter;

    private int[] pullAnimSrcs = new int[]{R.drawable.mt_pull,R.drawable.mt_pull01,R.drawable.mt_pull02,R.drawable.mt_pull03,R.drawable.mt_pull04,R.drawable.mt_pull05};
    private int[] refreshAnimSrcs = new int[]{R.drawable.mt_refreshing01,R.drawable.mt_refreshing02,R.drawable.mt_refreshing03,R.drawable.mt_refreshing04,R.drawable.mt_refreshing05,R.drawable.mt_refreshing06};
    private int[] loadingAnimSrcs = new int[]{R.drawable.mt_loading01,R.drawable.mt_loading02};

    @Bind(R.id.tv_base_title)
    TextView tv_base_title;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    /*@Bind(R.id.easy_refresh)
    EasyRefreshLayout easyRefreshLayout;*/
    @Bind(R.id.spring_view)
    SpringView springView;

    @Override
    public void initIntentData() {
        setChildLayout(R.layout.activity_recyclerview);
    }

    @Override
    public void initView() {
        tv_base_title.setText("RecyclerView");
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);
        mData = generateData(10);
        mAdapter = new RecyclerViewAdapter(mData);
        recyclerView.setAdapter(mAdapter);
//        initRefresh();
        initSpringView();
    }

    @Override
    public void initData() {

    }

    private void initSpringView () {
        springView.setType(SpringView.Type.FOLLOW);
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mData.clear();
                        mData = generateData(10);
                        mAdapter.setNewData(mData);
                        Toasts.showShort(RecyclerViewActivity.this,"刷新成功");
                        springView.onFinishFreshAndLoad();
                    }
                }, 2000);
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final List<ShopRecyclerBean> list = new ArrayList<>();
                        for (int j = 0; j < 5; j++) {
                            list.add(new ShopRecyclerBean(R.drawable.default_head, "商品名称j " + j, "￥100j" + j, "限量j" + j + "份"));
                        }
                        mAdapter.getData().addAll(list);
                        mAdapter.notifyDataSetChanged();
                        springView.onFinishFreshAndLoad();
                    }
                }, 2000);
            }
        });
        springView.setHeader(new MeituanHeader(this,pullAnimSrcs,refreshAnimSrcs));
        springView.setFooter(new MeituanFooter(this,loadingAnimSrcs));
    }

    /*private void initRefresh() {
        easyRefreshLayout.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final List<ShopRecyclerBean> list = new ArrayList<>();
                        for (int j = 0; j < 5; j++) {
                            list.add(new ShopRecyclerBean(R.drawable.default_head, "商品名称j " + j, "￥100j" + j, "限量j" + j + "份"));
                        }

                        //adapter.addData(list);

                        easyRefreshLayout.loadMoreComplete(new EasyRefreshLayout.Event() {
                            @Override
                            public void complete() {
                                mAdapter.getData().addAll(list);
                                mAdapter.notifyDataSetChanged();
                            }
                        }, 500);
                    }
                }, 2000);
            }

            @Override
            public void onRefreshing() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mData.clear();
                        mData = generateData(10);
                        mAdapter.setNewData(mData);
                        easyRefreshLayout.refreshComplete();
                        Toasts.showShort(RecyclerViewActivity.this,"刷新成功");
                    }
                }, 2000);
            }
        });
    }*/

    private List<ShopRecyclerBean> generateData(int number) {
        ArrayList<ShopRecyclerBean> data = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            data.add(new ShopRecyclerBean(R.drawable.default_head, "商品名称 " + i, "￥100" + i, "限量" + i + "份"));
        }
        return data;
    }

}
