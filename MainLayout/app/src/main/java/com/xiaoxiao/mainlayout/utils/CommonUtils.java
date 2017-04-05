package com.xiaoxiao.mainlayout.utils;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import java.util.List;

/**
 * Created by xiaoxiao 2017/4/5.
 */

public class CommonUtils {

    //获取手机上安装的所有应用名称和应用包名
    public static void getAllAppNames(Context context) {
        int j = 0;
        PackageManager pm = context.getPackageManager();
        //PackageManager.GET_UNINSTALLED_PACKAGES==8192
        List<PackageInfo> list2 = pm.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
        //PackageManager.GET_SHARED_LIBRARY_FILES==1024
        //List<PackageInfo> list2=pm.getInstalledPackages(PackageManager.GET_SHARED_LIBRARY_FILES);
        //PackageManager.GET_META_DATA==128
        //List<PackageInfo> list2=pm.getInstalledPackages(PackageManager.GET_META_DATA);
        //List<PackageInfo> list2=pm.getInstalledPackages(0);
        //List<PackageInfo> list2=pm.getInstalledPackages(-10);
        //List<PackageInfo> list2=pm.getInstalledPackages(10000);
        for (PackageInfo packageInfo : list2) {
            //得到手机上已经安装的应用的名字,即在AndriodMainfest.xml中的app_name。
            String appName = packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString();
            //得到应用所在包的名字,即在AndriodMainfest.xml中的package的值。
            String packageName = packageInfo.packageName;
            Log.i("111", "应用的名字:" + appName);
            Log.i("111", "应用的包名字:" + packageName);

            j++;
        }
        Log.i("222", "应用的总个数:" + j);
    }

    /**
     * 实现粘贴功能
     * add by wangqianzhou
     *
     * @param context
     * @return
     */
    public static String paste(Context context) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        CharSequence text = cmb.getText();
        if (TextUtils.isEmpty(text)) return "";
        return text.toString().trim();
    }

    /**
     * 复制并打开淘宝     天猫 com.tmall.wireless
     * @param content
     * @param context
     */
    public static void copyOpenJingDong(String content, Context context) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
        openJingDong(paste(context), context);
    }

    /**
     * 打开京东app
     * @param kwd
     * @param context
     */
    public static void openJingDong (String kwd,Context context) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage("com.jingdong.app.mall");
        if (intent == null) {
            Uri uri = Uri.parse("http://m.jd.com/");
            intent = new Intent(Intent.ACTION_VIEW,uri);
            context.startActivity(intent);
        } else {
            if (!TextUtils.isEmpty(kwd))
                intent.putExtra("kwd", kwd);
            context.startActivity(intent);
        }
    }

    /**
     * 复制并打开淘宝
     * @param content
     * @param context
     */
    public static void copyOpenTaobao(String content, Context context) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
        openApp(paste(context), context);
    }

    //打开淘宝App
    public static void openApp(String kwd, Context context) {
//        Intent intent = context.getPackageManager().getLaunchIntentForPackage("com.tmall.wireless");
        Intent intent = context.getPackageManager().getLaunchIntentForPackage("com.taobao.taobao");
        if (intent == null) {
            Toasts.showShort(context,"请安装淘宝客户端");
        } else {
            if (!TextUtils.isEmpty(kwd))
                intent.putExtra("kwd", kwd);
            context.startActivity(intent);
        }
    }

}
