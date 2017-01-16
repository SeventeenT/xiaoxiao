package com.xiaoxiao.mainlayout.activity;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xiaoxiao.mainlayout.R;
import com.xiaoxiao.mainlayout.base.BaseActivity;
import com.xiaoxiao.mainlayout.view.StackLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by xiaoxiao 2016/9/28.
 */
public class StackLayoutActivity extends BaseActivity {
    private List<String> mList = new ArrayList<>();

    @Bind(R.id.tv_base_title)
    TextView tv_title;

    @Bind(R.id.stack_layout)
    StackLayout stackLayout;

    @Override
    public void initIntentData() {
        setChildLayout(R.layout.activity_stack_layout);
    }

    @Override
    public void initView() {
        tv_title.setText("图片布局");
    }

    @Override
    public void initData() {

    }

    private void getImageNumber (int num) {
        mList.clear();
        Log.e("list", "list11:" + mList.size());
        for (int i = 0; i < num; i++) {
            mList.add("http://img5.duitang.com/uploads/item/201409/13/20140913171405_U2KhG.png");
        }
        Log.e("list","list22:"+mList.size());
        displayPhotos(mList, stackLayout);
    }

    private void displayPhotos (List<String> photos,StackLayout stackLayout) {
        stackLayout.removeAllViews();
        for (int i = 0; i < photos.size(); i++) {
            ImageView img = new ImageView(this);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                    , ViewGroup.LayoutParams.WRAP_CONTENT);
            img.setLayoutParams(layoutParams);
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            stackLayout.addView(img);
            Glide.with(this).load(photos.get(i)).error(R.drawable.no_data).into(img);
        }
    }

    @OnClick({R.id.tv_btn_one,R.id.tv_btn_two,R.id.tv_btn_three,R.id.tv_btn_four,R.id.tv_btn_five})
    public void setClick (View view) {
        switch (view.getId()) {
            case R.id.tv_btn_one:
                mList.clear();
                getImageNumber(1);
                break;
            case R.id.tv_btn_two:
                getImageNumber(2);
                break;
            case R.id.tv_btn_three:
                getImageNumber(3);
                break;
            case R.id.tv_btn_four:
                getImageNumber(4);
                break;
            case R.id.tv_btn_five:
                getImageNumber(5);
                break;
        }
    }

}
