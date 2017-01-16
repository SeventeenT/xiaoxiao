package com.xiaoxiao.mainlayout.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;

import com.xiaoxiao.mainlayout.activity.MainActivity;
import com.xiaoxiao.mainlayout.base.MyApplication;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by xiaoxiao 2016/9/19.
 */
public class UnCeHandler implements Thread.UncaughtExceptionHandler {

    private Thread.UncaughtExceptionHandler mDefaultHandler;
    public static final String TAG = "CatchExcep";
    MyApplication application;

    public UnCeHandler(MyApplication application) {
        //获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        this.application = application;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {

            Intent intent = new Intent(application.getApplicationContext(), MainActivity.class);
            PendingIntent restartIntent = PendingIntent.getActivity(
                    application.getApplicationContext(), 0, intent,
                    PendingIntent.FLAG_CANCEL_CURRENT);
            //退出程序
            AlarmManager mgr = (AlarmManager) application.getSystemService(Context.ALARM_SERVICE);
            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 2000,
                    restartIntent); // 2秒钟后重启应用
            application.finishActivity();
//            System.exit(2);
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(final Throwable ex) {
        if (ex == null) {
            return false;
        }
        //使用Toast来显示异常信息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();

                StringBuilder phoneInfo = new StringBuilder();
                //手机系统版本型号
                phoneInfo.append("TIME: " + TimesTamp.getCurrentTime() + System.getProperty("line.separator"));
                phoneInfo.append("BRAND: " + Build.BRAND + System.getProperty("line.separator"));
                phoneInfo.append("MODEL: " + Build.MODEL + System.getProperty("line.separator"));
                phoneInfo.append("VERSION.RELEASE: " + Build.VERSION.RELEASE + System.getProperty("line.separator"));
                phoneInfo.append("LOG:" + ex.toString() + System.getProperty("line.separator") + "\n");

                //如果SD卡不存在或无法使用，则无法把异常信息写入SD卡
                if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    Log.e(TAG, "sdcard unmounted,skip dump exception");
                    return;
                }

                File file = new File(MyApplication.newFolder() + File.separator + "log.txt");
                try {
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileWriter fw = new FileWriter(file, true);
                    fw.append(phoneInfo);
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //没有上下文
                /*Toast.makeText(application.getApplicationContext(), "很抱歉,程序出现异常,即将退出重启.",
                        Toast.LENGTH_SHORT).show();*/
                Looper.loop();
            }
        }.start();
        return true;
    }
}
