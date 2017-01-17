package com.xiaoxiao.mainlayout.entity;

/**
 * Created by Administrator 2017/1/17.
 */

public class ShopRecyclerBean {
    private int img_pic;
    private String tv_name;
    private String tv_price;
    private String tv_quantity;

    public ShopRecyclerBean(int img_pic, String tv_name, String tv_price, String tv_quantity) {
        this.img_pic = img_pic;
        this.tv_name = tv_name;
        this.tv_price = tv_price;
        this.tv_quantity = tv_quantity;
    }

    public int getImg_pic() {
        return img_pic;
    }

    public void setImg_pic(int img_pic) {
        this.img_pic = img_pic;
    }

    public String getTv_name() {
        return tv_name;
    }

    public void setTv_name(String tv_name) {
        this.tv_name = tv_name;
    }

    public String getTv_price() {
        return tv_price;
    }

    public void setTv_price(String tv_price) {
        this.tv_price = tv_price;
    }

    public String getTv_quantity() {
        return tv_quantity;
    }

    public void setTv_quantity(String tv_quantity) {
        this.tv_quantity = tv_quantity;
    }
}
