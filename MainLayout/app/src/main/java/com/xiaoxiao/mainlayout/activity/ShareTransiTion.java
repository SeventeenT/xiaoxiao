package com.xiaoxiao.mainlayout.activity;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.Window;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaoxiao.mainlayout.R;
import com.xiaoxiao.mainlayout.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by xiaoxiao 2016/9/26.
 */
public class ShareTransiTion extends BaseActivity {

    @Bind(R.id.tv_base_title)
    TextView tv_title;

    @Bind(R.id.img_btn_circle)
    ImageView img_btn_circle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 允许使用transitions
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initIntentData() {
        setChildLayout(R.layout.activity_transition_share);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initView() {
        tv_title.setText("转场动画");
        // 设置一个exit transition
       /* Explode explode = new Explode();
        explode.setDuration(2000);
        getWindow().setSharedElementExitTransition(explode);*/

        /*Fade fade = new Fade();
        fade.setDuration(2000);
        getWindow().setReenterTransition(fade);*/
    }

    @Override
    public void initData() {

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.img_btn_circle})
    public void setClick(View view) {
        switch (view.getId()) {
            case R.id.img_btn_circle:
                Intent intent = new Intent(this, RotateCircleActivity.class);
                // shareView: 需要共享的视图
                // "shareName": 设置的android:transitionName="shareName"
                View shareView = img_btn_circle;
                String transitionName = "xiaoxiao";
                ActivityOptions options = ActivityOptions
                        .makeSceneTransitionAnimation(this, img_btn_circle, "xiaoxiao");
                startActivity(intent, options.toBundle());
                break;
        }
    }

}
