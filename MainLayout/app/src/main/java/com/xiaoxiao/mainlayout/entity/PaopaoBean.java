package com.xiaoxiao.mainlayout.entity;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by xiaoxiao  2016/10/15.
 */
public class PaopaoBean {
    private String tv_nick;
    private String tv_type;

    public PaopaoBean(String tv_nick, String tv_type) {
        this.tv_nick = tv_nick;
        this.tv_type = tv_type;
    }

    public String getTv_nick() {
        return tv_nick;
    }

    public void setTv_nick(String tv_nick) {
        this.tv_nick = tv_nick;
    }

    public String getTv_type() {
        return tv_type;
    }

    public void setTv_type(String tv_task) {
        this.tv_type = tv_task;
    }
}
