package com.xiaoxiao.mainlayout.base;

import android.app.Activity;
import android.app.Application;
import android.os.Environment;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.xiaoxiao.mainlayout.utils.UnCeHandler;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by xiaoxiao 2016/8/3.
 */
public class MyApplication extends Application {

    ArrayList<Activity> list = new ArrayList<Activity>();
    public static File file;

    public void init() {
        //设置该CrashHandler为程序的默认处理器
        UnCeHandler catchExcep = new UnCeHandler(this);
        Thread.setDefaultUncaughtExceptionHandler(catchExcep);
    }

    /**
     * Activity关闭时，删除Activity列表中的Activity对象
     */
    public void removeActivity(Activity a) {
        list.remove(a);
    }

    /**
     * 向Activity列表中添加Activity对象
     */
    public void addActivity(Activity a) {
        list.add(a);
    }

    /**
     * 关闭Activity列表中的所有Activity
     */
    public void finishActivity() {
        for (Activity activity : list) {
            if (null != activity) {
                activity.finish();
            }
        }
        //杀死该应用进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public static String newFolder() {
        if (file == null) {
            file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "MainLayout");
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    //    友盟分享
    {
        PlatformConfig.setWeixin("wx328791b5c29ae220", "0e300cd2fc9904059b276775faf4a2a7");
        PlatformConfig.setSinaWeibo("1440985512", "111f31141294527d3857c929c083f5d3");
        //新浪微博的授权回调页地址
        Config.REDIRECT_URL = "http://sns.whalecloud.com";
        PlatformConfig.setQQZone("1105857919", "tRslUTCElpQU6P5T");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        UMShareAPI.get(this);
        Config.DEBUG = true;
    }

    /*  @Override
    public void onCreate() {
        super.onCreate();
//        设置主题
//        ThemeConfig theme = ThemeConfig.CYAN
        ThemeConfig theme = new ThemeConfig.Builder()
                .build();
        //配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnablePreview(true)
                .build();
        CoreConfig coreConfig = new CoreConfig.Builder(this, new UILImageLoader(), theme)
                .setFunctionConfig(functionConfig)
                .setPauseOnScrollListener(new UILPauseOnScrollListener(false, true))
                .build();
        GalleryFinal.init(coreConfig);
    }*/
}
