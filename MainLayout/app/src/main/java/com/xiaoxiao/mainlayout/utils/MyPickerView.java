package com.xiaoxiao.mainlayout.utils;

import android.content.Context;

import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.Gson;
import com.xiaoxiao.mainlayout.entity.ProvinceBean;
import com.xiaoxiao.mainlayout.interfaces.SimpleListener;
import com.xiaoxiao.mainlayout.interfaces.SimpleListenerInt;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by xuwenting 2016/4/28.
 */
public class MyPickerView {
    public SimpleListener mSimpleListener = null;
    public SimpleListenerInt mSimpleListenerInt = null;

    public static ArrayList<ProvinceBean> options1Items = new ArrayList<>();
    public static ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    public static ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    public OptionsPickerView pvOptions;
    public OptionsPickerView ageOptions;

    //一级  内容上一个界面通过序列化传递
    //年龄
    public void setAge(Context context, final ArrayList<Integer> arrayList) {

        ageOptions = new OptionsPickerView(context);
        ageOptions.setPicker(arrayList);
        ageOptions.setTitle("选择年龄");
        ageOptions.setCyclic(false);
        ageOptions.setSelectOptions(0);
        ageOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                int credit = arrayList.get(options1);
                mSimpleListenerInt.onSimpleListenerInt(credit, options1);
            }
        });
    }

    //三级联动选择省市区
    public void City(final Context context) {
        //选项选择器
        pvOptions = new OptionsPickerView(context);
        //三级联动效果
        pvOptions.setPicker(options1Items, options2Items, options3Items, true);
        //设置选择的三级单位
//        pwOptions.setLabels("省", "市", "区");
        pvOptions.setTitle("选择城市");
        pvOptions.setCyclic(false, true, true);
        //设置默认选中的三级项目
        //监听确定选择按钮
        pvOptions.setSelectOptions(0, 0, 0);
        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                //返回的分别是三个级别的选中位置
                String state = options1Items.get(options1).getPickerViewText();
                String city = options2Items.get(options1).get(option2);
                String region = options3Items.get(options1).get(option2).get(options3);
                /*String tx = options1Items.get(options1).getPickerViewText()
                        + options2Items.get(options1).get(option2)
                        + options3Items.get(options1).get(option2).get(options3);*/
                mSimpleListener.onSimpleListener(state, city, region);
            }
        });
    }

    public static void initData(Context context) {
        try {
            //解析json数据b
            InputStream is = context.getResources().getAssets().open("city.json");

            int available = is.available();

            byte[] b = new byte[available];
            int read = is.read(b);
            //注意格式，utf-8 或者gbk  否则解析出来可能会出现乱码
            String json = new String(b, "GBK");

            System.out.println(json);

            Gson gson = new Gson();
            China china = gson.fromJson(json, China.class);
            ArrayList<China.Province> citylist = china.citylist;
            //======省级
            for (China.Province province : citylist
                    ) {
                String provinceName = province.p;

                // System.out.println("provinceName==="+provinceName);
                ArrayList<China.Province.Area> c = province.c;
                //选项1
                options1Items.add(new ProvinceBean(0, provinceName, "", ""));
                ArrayList<ArrayList<String>> options3Items_01 = new ArrayList<ArrayList<String>>();
                //区级
                //选项2

                ArrayList<String> options2Items_01 = new ArrayList<String>();
                if (c != null) {
                    for (China.Province.Area area : c
                            ) {
                        //System.out.println("area------" + area.n + "------");

                        options2Items_01.add(area.n);
                        ArrayList<China.Province.Area.Street> a = area.a;
                        ArrayList<String> options3Items_01_01 = new ArrayList<String>();

                        //县级
                        if (a != null) {
                            for (China.Province.Area.Street street : a
                                    ) {
                                // System.out.println("street/////" + street.s);
                                options3Items_01_01.add(street.s);
                            }
                            options3Items_01.add(options3Items_01_01);
                        } else {
                            //县级为空的时候
                            options3Items_01_01.add("");
                            options3Items_01.add(options3Items_01_01);
                        }
                    }
                    options2Items.add(options2Items_01);
                } else {
                    //区级为空的时候  国外
                    options2Items_01.add("");
                }
                options3Items.add(options3Items_01);
                ArrayList<String> options3Items_01_01 = new ArrayList<String>();
                options3Items_01_01.add("");
                options3Items_01.add(options3Items_01_01);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setSimpleListenerInt (SimpleListenerInt simpleListenerInt) {
        this.mSimpleListenerInt = simpleListenerInt;
    }

    public void setSimpleListener(SimpleListener simpleListener) {
        this.mSimpleListener = simpleListener;
    }

}
