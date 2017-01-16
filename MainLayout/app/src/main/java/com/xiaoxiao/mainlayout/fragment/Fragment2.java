package com.xiaoxiao.mainlayout.fragment;


import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaoxiao.mainlayout.R;
import com.xiaoxiao.mainlayout.activity.MainActivity;
import com.xiaoxiao.mainlayout.adapter.PopShopTypeAdapter;
import com.xiaoxiao.mainlayout.adapter.TabAdapter;
import com.xiaoxiao.mainlayout.base.BaseFragment;
import com.xiaoxiao.mainlayout.utils.PreferencesUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends BaseFragment {
    private MainActivity mActivity;
    private FragmentPagerAdapter mFragmentPagerAdapter;
    private PopShopTypeAdapter popShopTypeAdapter;

    ArrayList<String> arrayList = new ArrayList<>();
    int index = 0;

    TabLayout tab_title;
    ViewPager mViewPager;
    TextView tv_all_type;
    ImageView img_shop_type;

    @Override
    protected View getChildView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        tab_title = (TabLayout) view.findViewById(R.id.tab_title);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tv_all_type = (TextView) view.findViewById(R.id.tv_all_type);
        img_shop_type = (ImageView) view.findViewById(R.id.img_shop_type);
        return view;
    }

    @Override
    protected void initView() {
        initTabLayout();
    }

    @Override
    protected void initData() {

    }

    private void initTabLayout() {
        String[] arr = getResources().getStringArray(R.array.shop_type);
        List<String> mListTitle = Arrays.asList(arr);
        List<Fragment> mListFragment = new ArrayList<>();

        /*for (int i = 0; i < mListTitle.size(); i++) {
            ShopListFragment shopListFragment = ShopListFragment.newInstance("i="+i);
            mListFragment.add(shopListFragment);
            arrayList.add(mListTitle.get(i));
        }*/

        //设置TabLayout的模式  固定模式
        tab_title.setTabMode(TabLayout.MODE_SCROLLABLE);

        //为TabLayout添加tab名称
        for (int i = 0; i < mListTitle.size(); i++) {
            ShopListFragment shopListFragment = ShopListFragment.newInstance("i="+i);
            mListFragment.add(shopListFragment);
            arrayList.add(mListTitle.get(i));
            tab_title.addTab(tab_title.newTab().setText(mListTitle.get(i)));
        }

        mFragmentPagerAdapter = new TabAdapter(getChildFragmentManager(), mListFragment, mListTitle);
        //ViewPager加载adapter
        mViewPager.setAdapter(mFragmentPagerAdapter);
        //TabLayout加载viewpager
        tab_title.setupWithViewPager(mViewPager);
        //设置Tab点击事件
        tab_title.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager) {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                index = tab.getPosition();
                Log.d("Fragment2", "index:" + index);
            }
        });

    }

    @OnClick(R.id.img_shop_type)
    public void setClick(View view) {
        switch (view.getId()) {
            case R.id.img_shop_type:
                tv_all_type.setVisibility(View.VISIBLE);
                tab_title.setVisibility(View.GONE);
                img_shop_type.setImageResource(R.drawable.more_type_up);
                showPopwindow(view);
                break;
        }
    }

    private void showPopwindow (View view) {
        View popView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_shop_type, null);
        final PopupWindow popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT, true);
        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        GridView gridView = (GridView) popView.findViewById(R.id.gridview_shop_type);
        LinearLayout lly = (LinearLayout) popView.findViewById(R.id.lly_pop);
        popShopTypeAdapter = new PopShopTypeAdapter(arrayList,mActivity,index);
        gridView.setAdapter(popShopTypeAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mActivity, arrayList.get(position), Toast.LENGTH_SHORT).show();
                Log.e("Fragment2", "222");
                index = position;
                popShopTypeAdapter.notifyDataSetChanged();
                popupWindow.dismiss();
                mViewPager.setCurrentItem(position); //点击后对于TabLayout的标签跟着变化
            }
        });

        lly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Fragment2", "111");
                popupWindow.dismiss();
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                tv_all_type.setVisibility(View.GONE);
                tab_title.setVisibility(View.VISIBLE);
                img_shop_type.setImageResource(R.drawable.more_type);
            }
        });
        popupWindow.showAsDropDown(view);
//        popupWindow.showAtLocation(view, Gravity.TOP, 0, 100);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (MainActivity) context;
    }
}
