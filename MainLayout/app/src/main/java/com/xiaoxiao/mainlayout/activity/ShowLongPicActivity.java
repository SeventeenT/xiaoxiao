package com.xiaoxiao.mainlayout.activity;

import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.xiaoxiao.mainlayout.R;
import com.xiaoxiao.mainlayout.base.BaseActivity;
import com.xiaoxiao.mainlayout.utils.WebViewUtils;

import butterknife.Bind;

/**
 * Created by Administrator 2017/3/30.
 */

public class ShowLongPicActivity extends BaseActivity {
    @Bind(R.id.tv_base_title)
    TextView tv_title;

    @Bind(R.id.web_view)
    WebView webView;

    @Override
    public void initIntentData() {
        setChildLayout(R.layout.activity_show_long_pic);
    }

    @Override
    public void initView() {
        tv_title.setText("仿淘宝加载长图");
    }

    @Override
    public void initData() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setBlockNetworkImage(false);//解决图片加载不出来的问题


        String St = "https://img.alicdn.com/imgextra/i3/1891562466/TB2InnNXSjz11Bjy0FnXXcnxXXa_!!1891562466.jpg," +
                "https://img.alicdn.com/imgextra/i3/1891562466/TB2InnNXSjz11Bjy0FnXXcnxXXa_!!1891562466.jpg," +
                "https://img.alicdn.com/imgextra/i3/1891562466/TB2InnNXSjz11Bjy0FnXXcnxXXa_!!1891562466.jpg,"+
                "http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg";

        if (!TextUtils.isEmpty(St)) {
            if (St.contains(",")) {
                String[] sss = St.split(",");
                String htmlData = WebViewUtils.stylebigimg;
                for (int i = 0; i < sss.length; i++) {
                    htmlData = htmlData + WebViewUtils.firimg + sss[i] + WebViewUtils.enimg;
                }
                webView.loadData(htmlData, "text/html", "utf-8");
                Log.e("wly", "=====" + htmlData);
            } else {
                String htmlData = WebViewUtils.stylebigimg + WebViewUtils.firimg + St + WebViewUtils.enimg;
                webView.loadData(htmlData, "text/html", "utf-8");
                Log.e("wly", "=====" + htmlData);
            }
        }

    }
}
