package com.xiaoxiao.mainlayout.activity;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xiaoxiao.mainlayout.R;
import com.xiaoxiao.mainlayout.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator 2017/3/3.
 */

public class PopWindowActivity extends BaseActivity {
    private PopupWindow popupWindow;

    @Bind(R.id.tv_base_title)
    TextView tvBaseTitle;
    @Bind(R.id.tv_btn_click)
    TextView tvClick;
    @Bind(R.id.view_bg)
    View viewBg;
    @Bind(R.id.view_lines)
    View viewLines;

    @Override
    public void initIntentData() {
        setChildLayout(R.layout.activity_popwindow);
    }

    @Override
    public void initView() {
        tvBaseTitle.setText("PopWindow");
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_btn_click,R.id.view_bg})
    public void setClick(View view) {
        switch (view.getId()) {
            case R.id.tv_btn_click:
                if (popupWindow != null && popupWindow.isShowing()) {
                    getDismiss();
                    viewBg.setVisibility(View.GONE);
                } else {
                    viewBg.setVisibility(View.VISIBLE);
                    showPop();
                }
                break;
            case R.id.view_bg:
                getDismiss();
                viewBg.setVisibility(View.GONE);
                break;
        }
    }

    public void showPop() {
        View popView = LayoutInflater.from(this).inflate(R.layout.pop_text_type, null);
        popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置动画
        popupWindow.setAnimationStyle(R.style.popwin_anmi_style);
        //点击窗外可取消
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
//        backgroundAlpha(100f);
//        popupWindow.setOnDismissListener(new poponDismissListener());
//        popupWindow.showAsDropDown(view,0,0);
        popupWindow.showAsDropDown(viewLines);
    }

    /**
     * PopupWindow 销毁
     */
    public void getDismiss() {
        popupWindow.dismiss();
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    public int getWindowHeight() {
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        int height = windowManager.getDefaultDisplay().getHeight();
        return height;
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    /**
     * 创建全屏的自定义PopupWindow
     *
     * @param view
     * @return
     */
    public PopupWindow createFullScreenPop(View view) {
        PopupWindow popupWindow = new PopupWindow(this);
        popupWindow.setContentView(view);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        return popupWindow;
    }

    /**
     * 添加弹出的popWin关闭的事件，主要是为了将背景透明度改回来
     *
     * @author cg
     */
    class poponDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            //Log.v("List_noteTypeActivity:", "我是关闭事件");
            backgroundAlpha(1f);
        }
    }

}
