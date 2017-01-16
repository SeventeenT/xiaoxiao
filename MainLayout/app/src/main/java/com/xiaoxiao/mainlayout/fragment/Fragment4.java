package com.xiaoxiao.mainlayout.fragment;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaoxiao.mainlayout.R;
import com.xiaoxiao.mainlayout.activity.MainActivity;
import com.xiaoxiao.mainlayout.activity.UPPayActivity;
import com.xiaoxiao.mainlayout.activity.UmengShareActivity;
import com.xiaoxiao.mainlayout.base.BaseFragment;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment4 extends BaseFragment {
    private MainActivity mActivity;

    @Bind(R.id.tv_next)
    TextView tv_next;

    @Override
    protected View getChildView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment4, container, false);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        //透明指示层
//        mActivity.getHigh(tv_next,0,0,150,0);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (MainActivity) context;
    }

    @OnClick({R.id.tv_btn_yinlian,R.id.tv_btn_share})
    public void setClick(View view) {
        switch (view.getId()) {
            case R.id.tv_btn_yinlian:
                startActivity(UPPayActivity.class,null);
                break;
            case R.id.tv_btn_share:
                startActivity(UmengShareActivity.class,null);
                break;
        }
    }

}
