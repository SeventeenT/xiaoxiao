package com.xiaoxiao.mainlayout.fragment;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaoxiao.mainlayout.R;
import com.xiaoxiao.mainlayout.activity.ChoosePhotoActivity;
import com.xiaoxiao.mainlayout.activity.MainActivity;
import com.xiaoxiao.mainlayout.activity.PopWindowActivity;
import com.xiaoxiao.mainlayout.activity.ProgressActivity;
import com.xiaoxiao.mainlayout.activity.RecyclerViewActivity;
import com.xiaoxiao.mainlayout.activity.RotateCircleActivity;
import com.xiaoxiao.mainlayout.activity.ShareTransiTion;
import com.xiaoxiao.mainlayout.activity.StackLayoutActivity;
import com.xiaoxiao.mainlayout.activity.ViewGroupActivity;
import com.xiaoxiao.mainlayout.base.BaseFragment;
import com.xiaoxiao.mainlayout.interfaces.SimpleListener;
import com.xiaoxiao.mainlayout.interfaces.SimpleListenerInt;
import com.xiaoxiao.mainlayout.utils.MyPickerView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment3 extends BaseFragment {
    private View view;
    private TextView text_btn;

    private MainActivity mActivity;
    private MyPickerView mMyPickerView;  //选择器

    @Bind(R.id.tv_sanji_liandong)
    TextView tv_sanji_liandong;
    @Bind(R.id.tv_yiji_liandong)
    TextView tv_yiji_liandong;


    @Override
    protected View getChildView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment3, container, false);
        text_btn = (TextView) view.findViewById(R.id.btn_circle);
        return view;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mMyPickerView = new MyPickerView();
        //城市
        MyPickerView.initData(mActivity);
        mMyPickerView.City(mActivity);
        mMyPickerView.setSimpleListener(new SimpleListener() {
            @Override
            public void onSimpleListener(String state, String city, String region) {
                tv_sanji_liandong.setText(state + "," + city + "," + region);
            }
        });

        //年龄
        mMyPickerView.setAge(mActivity, age());
        mMyPickerView.setSimpleListenerInt(new SimpleListenerInt() {
            @Override
            public void onSimpleListenerInt(int number, int id) {
                tv_yiji_liandong.setText("年龄：" + number);
            }
        });

//        getHighlight();
        //透明指示层
//        mActivity.getHigh(text_btn,150,0,150,0);
    }

    /*private void getHighlight () {
        highLight = new HighLight(mActivity)
                .anchor(view.findViewById(R.id.scroll_view))
                .addHighLight(view.findViewById(R.id.btn_circle),R.layout.item_highlight_one,new OnRightPosCallback(10),new CircleLightShape());
        highLight.show();

    }*/


    @OnClick({R.id.btn_sanji_liandong, R.id.btn_yiji_liandong, R.id.btn_photo, R.id.btn_progress, R.id.btn_circle
            , R.id.btn_animation,R.id.btn_stack,R.id.btn_view_group,R.id.btn_recycler_view,R.id.btn_pop_view})
    public void setClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sanji_liandong:
                mMyPickerView.pvOptions.show();
                break;
            case R.id.btn_yiji_liandong:
                mMyPickerView.ageOptions.show();
                break;
            case R.id.btn_photo:
                startActivity(ChoosePhotoActivity.class, null);
                break;
            case R.id.btn_progress:
                startActivity(ProgressActivity.class, null);
                break;
            case R.id.btn_circle:
                startActivity(RotateCircleActivity.class, null);
                break;
            case R.id.btn_animation:
                startActivity(ShareTransiTion.class,null);
                break;
            case R.id.btn_stack:
                startActivity(StackLayoutActivity.class,null);
                break;
            case R.id.btn_view_group:
                startActivity(ViewGroupActivity.class,null);
                break;
            case R.id.btn_recycler_view:
                startActivity(RecyclerViewActivity.class,null);
                break;
            case R.id.btn_pop_view:
                startActivity(PopWindowActivity.class,null);
                break;
        }
    }


    //年龄数据
    public static ArrayList<Integer> age() {
        ArrayList<Integer> ageList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            ageList.add(i);
        }
        return ageList;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (MainActivity) context;
    }

}
