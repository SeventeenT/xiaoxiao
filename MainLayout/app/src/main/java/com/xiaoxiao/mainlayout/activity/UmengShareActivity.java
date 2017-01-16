package com.xiaoxiao.mainlayout.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xiaoxiao.mainlayout.R;
import com.xiaoxiao.mainlayout.base.BaseActivity;
import com.xiaoxiao.mainlayout.utils.Toasts;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator 2017/1/6.
 */

public class UmengShareActivity extends BaseActivity{
    private UMShareAPI umShareAPI;

    @Bind(R.id.tv_base_title)
    TextView tv_base_title;

    @Override
    public void initIntentData() {
        setChildLayout(R.layout.activity_umeng_share);
        umShareAPI = UMShareAPI.get(UmengShareActivity.this);
    }

    @Override
    public void initView() {
        tv_base_title.setText("友盟");
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_btn_share,R.id.tv_btn_qq_login,R.id.tv_btn_wx_login,R.id.tv_btn_weibo_login})
    public void setClick (View view) {
        switch (view.getId()) {
            case R.id.tv_btn_share:
                shareUmeng();
                break;
            case R.id.tv_btn_qq_login:
                umShareAPI.getPlatformInfo(UmengShareActivity.this,SHARE_MEDIA.QQ,umAuthListener);
                break;
            case R.id.tv_btn_wx_login:
                umShareAPI.getPlatformInfo(UmengShareActivity.this,SHARE_MEDIA.WEIXIN,umAuthListener);
                break;
            case R.id.tv_btn_weibo_login:
                umShareAPI.getPlatformInfo(UmengShareActivity.this,SHARE_MEDIA.SINA,umAuthListener);
                break;
        }
    }

    //分享
    private void shareUmeng () {
        final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]
                {
                        SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ,
                        SHARE_MEDIA.QZONE,SHARE_MEDIA.SINA
                };
        new ShareAction(UmengShareActivity.this).withText("尛小小")
                .setDisplayList(displaylist)
                .open();

    }

    //第三方登录回调
    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            Log.e("map","map="+map);
            Toasts.showShort(UmengShareActivity.this,"登录成功");
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            Toasts.showShort(UmengShareActivity.this,"登录失败");
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            Toasts.showShort(UmengShareActivity.this,"登录取消");
        }
    };

    /*private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA share_media) {
            Toasts.showShort(UmengShareActivity.this,"分享成功");
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            Toasts.showShort(UmengShareActivity.this,"分享失败");
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            Toasts.showShort(UmengShareActivity.this,"分享取消");
        }
    };*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //分享回调
        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }
}
