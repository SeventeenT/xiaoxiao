package com.xiaoxiao.mainlayout.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaoxiao.mainlayout.R;
import com.xiaoxiao.mainlayout.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by xiaoxiao 2016/9/23.
 */
public class RotateCircleActivity extends BaseActivity {

    @Bind(R.id.tv_base_title)
    TextView tv_title;

    @Bind(R.id.img_circle)
    ImageView mAnimationView;
    @Bind(R.id.tv_start)
    TextView tv_start;

    @Override
    public void initIntentData() {
        setChildLayout(R.layout.activity_rotate_circle);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initView() {
        tv_title.setText("旋转动画");
        /*Explode explode = new Explode();
        explode.setDuration(2000);
        getWindow().setSharedElementEnterTransition(explode);*/

        /*Fade fade = new Fade();
        fade.setDuration(2000);
        getWindow().setReturnTransition(fade);*/
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.img_btn_start,R.id.tv_start,R.id.img_circle})
    public void setClick (View v) {
        switch (v.getId()){
            case R.id.img_btn_start:
            case R.id.tv_start:
                startRotate();
                break;
            case R.id.img_circle:
                tv_start.setText("接单");
                mAnimationView.clearAnimation();
                mAnimationView.setVisibility(View.GONE);
                break;
        }
    }

    private void startRotate () {
        tv_start.setText("停止");
        mAnimationView.setVisibility(View.VISIBLE);
        RotateAnimation rotateAnimation = new RotateAnimation(359,0, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setDuration(2500);
        LinearInterpolator linearInterpolator = new LinearInterpolator();
        rotateAnimation.setInterpolator(linearInterpolator);
        mAnimationView.startAnimation(rotateAnimation);
    }
}
