package com.xiaoxiao.mainlayout.activity;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiaoxiao.mainlayout.R;
import com.xiaoxiao.mainlayout.base.BaseActivity;
import com.xiaoxiao.mainlayout.utils.ScreenUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator 2017/5/12.
 */

public class OrderReceivingAnimationActivity extends BaseActivity {
    @Bind(R.id.tv_base_title)
    TextView tv_title;
    @Bind(R.id.tv_send_order)
    TextView tvSendOrder;
    @Bind(R.id.tv_start)
    TextView tvStart;

    private int screenWidth;
    private int width2;
    private int width1;
    private boolean isClick = false;
    private ScaleAnimation scaleAnimation;
    private TranslateAnimation translateAnimation;

    @Override
    public void initIntentData() {
        setChildLayout(R.layout.activity_order_receiving_animation);
    }

    @Override
    public void initView() {
        tv_title.setText("自动接单动画");
        screenWidth = ScreenUtil.getScreenWidth(this);
        width1 = screenWidth*2/3;
        width2 = screenWidth*1/3;
        Log.e("width","width="+screenWidth+"height="+width1);
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) tvSendOrder.getLayoutParams();
        lp.width = width1;
        tvSendOrder.setLayoutParams(lp);
    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.tv_start)
    public void setClick (View view) {
        if (!isClick) {
            translateAnimation = new TranslateAnimation(0,width1,0,0);
            translateAnimation.setDuration(500);
            translateAnimation.setFillAfter(true);
            tvStart.startAnimation(translateAnimation);
            tvStart.setGravity(Gravity.CENTER_VERTICAL);
            tvStart.setPadding(50,0,0,0);
            tvStart.setText("取消接单");
            isClick = true;
        } else {
            translateAnimation = new TranslateAnimation(width1,0,0,0);
            translateAnimation.setDuration(500);
            tvStart.startAnimation(translateAnimation);
            tvStart.setGravity(Gravity.CENTER);
            tvStart.setText("开始接单");
            isClick = false;
        }

    }

}
