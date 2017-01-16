package com.xiaoxiao.mainlayout.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiaoxiao.mainlayout.R;
import com.xiaoxiao.mainlayout.base.BaseActivity;
import com.xiaoxiao.mainlayout.fragment.Fragment1;
import com.xiaoxiao.mainlayout.fragment.Fragment2;
import com.xiaoxiao.mainlayout.fragment.Fragment3;
import com.xiaoxiao.mainlayout.fragment.Fragment4;
import com.xiaoxiao.mainlayout.fragment.Fragment5;
import com.xiaoxiao.mainlayout.utils.Toasts;

import butterknife.Bind;
import butterknife.OnClick;
import zhy.com.highlight.HighLight;
import zhy.com.highlight.shape.CircleLightShape;

public class MainActivity extends BaseActivity {
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private Fragment4 fragment4;
    private Fragment5 fragment5;
    int red = 0xFFef4359;

    private HighLight highLight;

    @Bind(R.id.tv_base_title)
    TextView tv_base_title;
    @Bind(R.id.rly_base_title)
    RelativeLayout rly_base_title;
    @Bind(R.id.img_base_line)
    ImageView img_base_line;

    @Bind(R.id.img_1_pic)
    ImageView img_1_pic;
    @Bind(R.id.img_2_pic)
    ImageView img_2_pic;
    @Bind(R.id.img_3_pic)
    ImageView img_3_pic;
    @Bind(R.id.img_4_pic)
    ImageView img_4_pic;
    @Bind(R.id.img_5_pic)
    ImageView img_5_pic;

    @Bind(R.id.tv_1_text)
    TextView tv_1_text;
    @Bind(R.id.tv_2_text)
    TextView tv_2_text;
    @Bind(R.id.tv_3_text)
    TextView tv_3_text;
    @Bind(R.id.tv_4_text)
    TextView tv_4_text;
    @Bind(R.id.tv_5_text)
    TextView tv_5_text;

    private int highType;


    @Override
    public void initIntentData() {
        setChildLayout(R.layout.activity_main);
        setSelectFrag(0);
    }

    @Override
    public void initView() {
        tv_base_title.setText("首页");
    }

    @Override
    public void initData() {

    }



    @OnClick({R.id.lly_1_btn,R.id.lly_2_btn,R.id.lly_3_btn,R.id.lly_4_btn,R.id.lly_5_btn})
    public void setClick (View view) {
        initButton();
        switch (view.getId()) {
            case R.id.lly_1_btn:
                setSelectFrag(0);
                break;
            case R.id.lly_2_btn:
                setSelectFrag(1);
                break;
            case R.id.lly_3_btn:
                setSelectFrag(2);
                break;
            case R.id.lly_4_btn:
                setSelectFrag(3);
                break;
            case R.id.lly_5_btn:
                setSelectFrag(4);
                break;
        }
    }

    //设置选中的fragment
    private void setSelectFrag (int i) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        switch (i) {
            case 0:
                rly_base_title.setVisibility(View.VISIBLE);
                img_base_line.setVisibility(View.VISIBLE);
                if (fragment1 == null) {
                    fragment1 = new Fragment1();
                    transaction.add(R.id.frag_main,fragment1);
                } else {
                    transaction.show(fragment1);
                }
                img_1_pic.setImageResource(R.drawable.main_home_checked_1);
                tv_1_text.setTextColor(red);
                break;
            case 1:
                rly_base_title.setVisibility(View.GONE);
                img_base_line.setVisibility(View.GONE);
                if (fragment2 == null) {
                    fragment2 = new Fragment2();
                    transaction.add(R.id.frag_main,fragment2);
                } else {
                    transaction.show(fragment2);
                }
                img_2_pic.setImageResource(R.drawable.main_home_checked_2);
                tv_2_text.setTextColor(red);
                break;
            case 2:
                rly_base_title.setVisibility(View.GONE);
                img_base_line.setVisibility(View.GONE);
                if (fragment3 == null) {
                    fragment3 = new Fragment3();
                    transaction.add(R.id.frag_main,fragment3);
                } else {
                    transaction.show(fragment3);
                }
                img_3_pic.setImageResource(R.drawable.main_home_checked_3);
                tv_3_text.setTextColor(red);
                highType = 2;
                break;
            case 3:
                rly_base_title.setVisibility(View.GONE);
                img_base_line.setVisibility(View.GONE);
                if (fragment4 == null) {
                    fragment4 = new Fragment4();
                    transaction.add(R.id.frag_main,fragment4);
                } else {
                    transaction.show(fragment4);
                }
                img_4_pic.setImageResource(R.drawable.main_home_checked_4);
                tv_4_text.setTextColor(red);
                highType = 3;
                break;
            case 4:
                rly_base_title.setVisibility(View.GONE);
                img_base_line.setVisibility(View.GONE);
                if (fragment5 == null) {
                    fragment5 = new Fragment5();
                    transaction.add(R.id.frag_main,fragment5);
                } else {
                    transaction.show(fragment5);
                }
                img_5_pic.setImageResource(R.drawable.main_home_checked_5);
                tv_5_text.setTextColor(red);
                break;
        }
        transaction.commit();
    }

    public void getHigh (View view,final float lift, final float right, final float top, final float bottom) {
        getHighlight(MainActivity.this, view, R.layout.item_highlight_one, lift, right, top, bottom, new CircleLightShape());
        /*highLight = new HighLight(this)
                .autoRemove(false)
                .anchor(findViewById(R.id.rly_main_activity))
                .addHighLight(view, R.layout.item_highlight_one, new HighLight.OnPosCallback() {
                    @Override
                    public void getPos(float rightMargin, float bottomMargin, RectF rectF, HighLight.MarginInfo marginInfo) {
                        marginInfo.leftMargin = 150;
                        marginInfo.topMargin = 150;
                    }
                }, new CircleLightShape());
        highLight.show();*/
    }

    @Override
    public void getRemove() {
        super.getRemove();
        switch (highType) {
            case 2:
                Toasts.showShort(this,"控件效果");
                break;
            case 3:
                Toasts.showShort(this,"下周预告");
                break;
        }
    }

    /*public void setClickKnow (View view) {
        //如果开启next模式
        if(highLight.isNext()) {
            highLight.next();
        }else {
            highLight.remove();
        }
    }*/

    //隐藏fragment
    public void hideFragment(FragmentTransaction transaction) {
        if (fragment1 != null) {
            transaction.hide(fragment1);
        }
        if (fragment2 != null) {
            transaction.hide(fragment2);
        }
        if (fragment3 != null) {
            transaction.hide(fragment3);
        }
        if (fragment4 != null) {
            transaction.hide(fragment4);
        }
        if (fragment5 != null) {
            transaction.hide(fragment5);
        }
    }

    //清空底部导航图标文字颜色
    public void initButton () {
        int gray = 0xFF4A4A4B;
        img_1_pic.setImageResource(R.drawable.main_home_no_checked_1);
        tv_1_text.setTextColor(gray);
        img_2_pic.setImageResource(R.drawable.main_home_no_checked_2);
        tv_2_text.setTextColor(gray);
        img_3_pic.setImageResource(R.drawable.main_home_no_checked_3);
        tv_3_text.setTextColor(gray);
        img_4_pic.setImageResource(R.drawable.main_home_no_checked_4);
        tv_4_text.setTextColor(gray);
        img_5_pic.setImageResource(R.drawable.main_home_no_checked_5);
        tv_5_text.setTextColor(gray);
    }



}
