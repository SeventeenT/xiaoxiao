package com.xiaoxiao.mainlayout.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaoxiao.mainlayout.R;
import com.xiaoxiao.mainlayout.entity.ShopRecyclerBean;

import java.util.List;

/**
 * Created by Administrator 2017/1/17.
 */

public class RecyclerViewAdapter extends BaseQuickAdapter<ShopRecyclerBean,BaseViewHolder> {
    public RecyclerViewAdapter(List<ShopRecyclerBean> data) {
        super(R.layout.item_recycler_view, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopRecyclerBean item) {
        helper.setText(R.id.tv_recycler_view_shop_name,item.getTv_name())
                .setText(R.id.tv_recycler_view_shop_price,item.getTv_price())
                .setText(R.id.tv_recycler_view_shop_quantity,item.getTv_quantity());
        Glide.with(mContext).load(item.getImg_pic()).crossFade().into((ImageView) helper.getView(R.id.img_recycler_view_pic));
    }
}
