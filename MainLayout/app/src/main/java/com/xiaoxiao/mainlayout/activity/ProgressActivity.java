package com.xiaoxiao.mainlayout.activity;

import android.graphics.RectF;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaoxiao.mainlayout.R;
import com.xiaoxiao.mainlayout.base.BaseActivity;
import com.xiaoxiao.mainlayout.utils.Toasts;
import com.xiaoxiao.mainlayout.view.MyStepperIndicator;

import butterknife.Bind;
import butterknife.OnClick;
import zhy.com.highlight.HighLight;
import zhy.com.highlight.position.OnBaseCallback;
import zhy.com.highlight.position.OnRightPosCallback;
import zhy.com.highlight.shape.CircleLightShape;

/**
 * Created by Administrator 2016/8/22.
 */
public class ProgressActivity extends BaseActivity implements View.OnClickListener {
    private int position = 0;

    private HighLight highLight;

    @Bind(R.id.tv_base_title)
    TextView tv_title;

    @Bind(R.id.step_view)
    MyStepperIndicator step;
    @Bind(R.id.tv_win)
    TextView tv_win;
    @Bind(R.id.tv_address)
    TextView tv_address;
    @Bind(R.id.tv_get_goods)
    TextView tv_get_goods;
    @Bind(R.id.btn_subtract)
    Button btn_subtract;

    @Bind(R.id.btn_ex)
    Button btn_ex;
    @Bind(R.id.tv_ex)
    TextView tv_ex;

    @Override
    public void initIntentData() {
        setChildLayout(R.layout.activity_progress);
    }

    @Override
    public void initView() {
        tv_title.setText("物流进度");
    }

    @Override
    public void initData() {
        btn_ex.setOnClickListener(this);
        getHighlight(ProgressActivity.this,btn_subtract,R.layout.item_highlight_one,150,0,20,0,new CircleLightShape());
    }

    private void getTextColor() {
        switch (position) {
            case 0:
                tv_win.setTextColor(getResources().getColor(R.color.grey));
                tv_address.setTextColor(getResources().getColor(R.color.grey));
                tv_get_goods.setTextColor(getResources().getColor(R.color.grey));
                break;
            case 1:
                tv_win.setTextColor(getResources().getColor(R.color.main_red));
                tv_address.setTextColor(getResources().getColor(R.color.grey));
                tv_get_goods.setTextColor(getResources().getColor(R.color.grey));
                break;
            case 2:
                tv_win.setTextColor(getResources().getColor(R.color.main_red));
                tv_address.setTextColor(getResources().getColor(R.color.main_red));
                tv_get_goods.setTextColor(getResources().getColor(R.color.grey));
                break;
            case 3:
                tv_win.setTextColor(getResources().getColor(R.color.main_red));
                tv_address.setTextColor(getResources().getColor(R.color.main_red));
                tv_get_goods.setTextColor(getResources().getColor(R.color.main_red));
                break;
        }
    }

    @OnClick({R.id.btn_subtract, R.id.btn_add})
    public void setClick(View view) {
        switch (view.getId()) {
            case R.id.btn_subtract:
                if (position != 0) {
                    position--;
                }
                if (position == 0) {
                    Toast.makeText(this,"最小了",Toast.LENGTH_SHORT).show();
                    position = 0;
                }
                step.setCurrentStep(position);
                getTextColor();
                break;
            case R.id.btn_add:
                if (position != step.getStepCount()) {
                    position++;
                }
                if (position == step.getStepCount()) {
                    Toast.makeText(this,"最大了",Toast.LENGTH_SHORT).show();
                    position = 3;
                }
                step.setCurrentStep(position);
                getTextColor();
                break;
        }
    }

    @Override
    public void getRemove() {
        super.getRemove();
        Toasts.showShort(this,"啦啦啦啦");
    }

    /*private void getHighlight () {
        highLight = new HighLight(this)
                .autoRemove(false)
                .anchor(findViewById(R.id.lly_wuliu))
                .addHighLight(R.id.btn_subtract, R.layout.item_highlight_one, new OnBaseCallback() {
                    @Override
                    public void getPosition(float rightMargin, float bottomMargin, RectF rectF, HighLight.MarginInfo marginInfo) {
                        marginInfo.leftMargin = 150;
                    }
                }, new CircleLightShape());
        highLight.show();
    }*/



    /*public void setClickKnow(View view) {
        if (highLight != null) {
            //如果开启next模式
            if (highLight.isNext()) {
                highLight.next();
            } else {
                highLight.remove();
            }
        }
    }*/

    /**
     * 人为制造的异常*/
    public void press(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                tv_ex.setText("222222");
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
//        press();
//        int i = 1/0;
    }
}
