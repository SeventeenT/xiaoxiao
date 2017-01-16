package com.xiaoxiao.mainlayout.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.unionpay.UPPayAssistEx;
import com.xiaoxiao.mainlayout.R;
import com.xiaoxiao.mainlayout.base.BaseActivity;
import com.xiaoxiao.mainlayout.entity.ResultBean;
import com.xiaoxiao.mainlayout.utils.RSAUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator 2017/1/4.
 */

public class UPPayActivity extends BaseActivity {
    private static final String TAG = "UPPayActivity";
    private String tn = "660622314929734955601";
    private String mMode = "01"; // mMode参数解释： "00" - 启动银联正式环境 "01" - 连接银联测试环境

    @Bind(R.id.tv_base_title)
    TextView tv_title;

    @Override
    public void initIntentData() {
        setChildLayout(R.layout.activity_uppay);
    }

    @Override
    public void initView() {
        tv_title.setText("银联支付");
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_btn_open_up})
    public void setClick (View view) {
        switch (view.getId()) {
            case R.id.tv_btn_open_up:
                UPPayAssistEx.startPay(UPPayActivity.this,null,null,tn,mMode);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        String str = data.getExtras().getString("pay_result");
        if (str.equalsIgnoreCase("success")) {
            // 支付成功后，extra中如果存在result_data，取出校验
            // result_data结构见c）result_data参数说明
            if (data.hasExtra("result_data")) {
                String resultdate = data.getExtras().getString("result_data");
                Log.e("resultdate","resultdate=="+resultdate);
                try {
                    ResultBean resultBean = new Gson().fromJson(resultdate,ResultBean.class);
                    String sign = resultBean.getSign();
                    String date_result = resultBean.getData();
                    // 验签证书同后台验签证书
                    // 此处的verify，商户需送去商户后台做验签
                    if (RSAUtil.verify(date_result,sign,mMode)) {
                        //验证通过后，显示支付结果
//                    showResultDialog(" 支付成功！ ");
                        Toast.makeText(UPPayActivity.this,"支付成功！",Toast.LENGTH_SHORT).show();
                    } else {
                        // 验证不通过后的处理
                        // 建议通过商户后台查询支付结果
                    }
                } catch (Exception e) {
                    Log.e(TAG,"Exception=="+e.toString());
                }
            } else {
                // 未收到签名信息
                // 建议通过商户后台查询支付结果
            }
        } else if (str.equalsIgnoreCase("fail")) {
//            showResultDialog(" 支付失败！ ");
            Toast.makeText(UPPayActivity.this,"支付失败！",Toast.LENGTH_SHORT).show();
        } else if (str.equalsIgnoreCase("cancel")) {
//            showResultDialog(" 你已取消了本次订单的支付！ ");
            Toast.makeText(UPPayActivity.this,"你已取消了本次订单的支付！",Toast.LENGTH_SHORT).show();
        }
    }
}
