package com.xiaoxiao.mainlayout.utils;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator 2016/9/30.
 */
public class IntentIpUtils {

    // 获取外网IP
    public static String GetNetIp() {
        URL infoUrl = null;
        InputStream inStream = null;
        try {
            // http://iframe.ip138.com/ic.asp
            // infoUrl = new URL("http://city.ip138.com/city0.asp");
            infoUrl = new URL("http://ip38.com");
            URLConnection connection = infoUrl.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inStream = httpConnection.getInputStream();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inStream, "utf-8"));
                StringBuilder strber = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null)
                    strber.append(line + "\n");
                inStream.close();
                // 从反馈的结果中提取出IP地址
                // int start = strber.indexOf("[");
                // Log.d("zph", "" + start);
                // int end = strber.indexOf("]", start + 1);
                // Log.d("zph", "" + end);
                line = strber.substring(378, 395);
                line.replaceAll(" ", "");
                return line;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //另有一个获取外网IP的高端方法

    public static String GetNetIpNew()
    {
        String IP = "";
        try
        {
            String address = "http://ip.taobao.com/service/getIpInfo2.php?ip=myip";
            URL url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setUseCaches(false);

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                InputStream in = connection.getInputStream();

// 将流转化为字符串
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(in));

                String tmpString = "";
                StringBuilder retJSON = new StringBuilder();
                while ((tmpString = reader.readLine()) != null)
                {
                    retJSON.append(tmpString + "\n");
                }

                JSONObject jsonObject = new JSONObject(retJSON.toString());
                String code = jsonObject.getString("code");
                if (code.equals("0"))
                {
                    JSONObject data = jsonObject.getJSONObject("data");
                    IP = data.getString("ip") + "(" + data.getString("country")
                            + data.getString("area") + "区"
                            + data.getString("region") + data.getString("city")
                            + data.getString("isp") + ")";

                    Log.e("提示", "您的IP地址是：" + IP);
                }
                else
                {
                    IP = "";
                    Log.e("提示", "IP接口异常，无法获取IP地址！");
                }
            }
            else
            {
                IP = "";
                Log.e("提示", "网络连接异常，无法获取IP地址！");
            }
        }
        catch (Exception e)
        {
            IP = "";
            Log.e("提示", "获取IP地址时出现异常，异常信息是：" + e.toString());
        }
        return IP;
    }

}
