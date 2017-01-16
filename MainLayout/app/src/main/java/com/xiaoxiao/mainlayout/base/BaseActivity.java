package com.xiaoxiao.mainlayout.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import com.xiaoxiao.mainlayout.R;

import butterknife.ButterKnife;
import zhy.com.highlight.HighLight;
import zhy.com.highlight.position.OnBaseCallback;
import zhy.com.highlight.shape.CircleLightShape;

/**
 * Created by xiaoxiao 2016/7/12.
 */
public abstract class BaseActivity extends AppCompatActivity {
    public FrameLayout mFrameLayout;
    public View mView;
    public Intent mIntent;
    public Bundle mSavedInstanceState;
    private MyApplication myApplication;

    private HighLight highLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mSavedInstanceState = savedInstanceState;
        setContentView(R.layout.activity_base);
        setBack();

        //初始化intent传递的数据
        initIntentData();
        //初始化view
        initView();
        //初始化填充数据
        initData();

        //强制竖屏
        initWindowsPortrait();

       /* myApplication = (MyApplication) getApplication();
        myApplication.init();
        myApplication.addActivity(this);*/
    }

    public abstract void initIntentData();

    public abstract void initView();

    public abstract void initData();

    protected void setBack() {
        mFrameLayout = (FrameLayout) findViewById(R.id.frame_content);
        findViewById(R.id.img_base_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    public void setChildLayout(int layout) {
        mView = LayoutInflater.from(this).inflate(layout, mFrameLayout, false);
        mFrameLayout.addView(mView);
        //初始化ButterKnife
        ButterKnife.bind(this);
    }

    //fragment 跳转activity
    public void startActivity(Class<?> cls, Bundle bundle) {
        mIntent = new Intent(this, cls);
        if (bundle != null) {
            mIntent.putExtras(bundle);
        }
        startActivity(mIntent);
    }

    /**
     * 点击空白位置 隐藏软键盘
     */
    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus()) {
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }

    //竖屏
    public void initWindowsPortrait() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    //字体大小不受系统字体大小改变而改变
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    public void getHighlight(Context context, View viewId, int decorLayoutId, final float lift, final float right,
                             final float top, final float bottom, HighLight.LightShape lightShape) {
        highLight = new HighLight(context)
                .autoRemove(false)
                .anchor(findViewById(R.id.rly_base))
                .addHighLight(viewId, decorLayoutId, new OnBaseCallback() {
                    @Override
                    public void getPosition(float rightMargin, float bottomMargin, RectF rectF, HighLight.MarginInfo marginInfo) {
                        marginInfo.leftMargin = lift;
                        marginInfo.rightMargin = right;
                        marginInfo.topMargin = top;
                        marginInfo.bottomMargin = bottom;
                    }
                }, lightShape);
        highLight.show();
    }

    public void setClickKnow(View view) {
        //如果开启next模式
        if (highLight.isNext()) {
            highLight.next();
        } else {
            highLight.remove();
            getRemove();
        }
    }

    public void getRemove() {
    }

}
