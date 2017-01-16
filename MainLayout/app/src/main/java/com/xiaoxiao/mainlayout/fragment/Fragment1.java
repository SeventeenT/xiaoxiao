package com.xiaoxiao.mainlayout.fragment;


import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xiaoxiao.mainlayout.R;
import com.xiaoxiao.mainlayout.base.BaseFragment;
import com.xiaoxiao.mainlayout.view.CircleImageView;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends BaseFragment {

    @Bind(R.id.img_glide)
    ImageView img_glide;
    @Bind(R.id.img_circle)
    CircleImageView img_circle;

    @Override
    protected View getChildView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment1, container, false);
        return view;
    }

    @Override
    protected void initView() {

        Glide.with(activity).load("http://a.hiphotos.baidu.com/image/pic/item/f9dcd100baa1cd117a87fd46ba12c8fcc3ce2da6.jpg")
                .error(R.drawable.no_img).into(img_glide);
        Glide.with(activity).load(R.drawable.default_head)
                .fitCenter().error(R.drawable.no_img).into(img_circle);
    }

    @Override
    protected void initData() {

        WifiManager wifimanage = (WifiManager) activity.getSystemService(Context.WIFI_SERVICE);//获取WifiManager

        //检查wifi是否开启

        if (!wifimanage.isWifiEnabled()) {

            wifimanage.setWifiEnabled(true);

        }

        WifiInfo wifiinfo = wifimanage.getConnectionInfo();

        String ip = intToIp(wifiinfo.getIpAddress());
        Log.e("ip", "获取wifi的IP地址:" + ip);

       /* new Thread() {
            @Override
            public void run() {
                super.run();
                Log.e("ip","获取外网IP:"+ IntentIpUtils.GetNetIp());
                Log.e("ip", "获取外网IP的高端方法:" + IntentIpUtils.GetNetIpNew());
            }
        }.start();*/

    }
    //将获取的int转为真正的ip地址,参考的网上的，修改了下
    private String intToIp (int i) {
        return (i & 0xFF) + "." + ((i >> 8) & 0xFF)  +"." + ((i >> 16) & 0xFF) + "." + ((i >> 24) & 0xFF);
    }

}
