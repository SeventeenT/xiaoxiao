package com.xiaoxiao.mainlayout.activity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.actionsheet.ActionSheet;
import com.xiaoxiao.mainlayout.R;
import com.xiaoxiao.mainlayout.adapter.PhotoGridAdapter;
import com.xiaoxiao.mainlayout.base.BaseActivity;
import com.xiaoxiao.mainlayout.listener.GlidePauseOnScrollListener;
import com.xiaoxiao.mainlayout.utils.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.PauseOnScrollListener;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by Administrator 2016/8/5.
 */
public class ChoosePhotoActivity extends BaseActivity {
    private final int REQUEST_CODE_CAMERA = 1000;
    private final int REQUEST_CODE_GALLERY = 1001;

    private ArrayList<PhotoInfo> mPhotoList = new ArrayList<>();
    private PhotoGridAdapter photoGridAdapter;

    @Bind(R.id.grid_photo)
    GridView grid_photo;
    @Bind(R.id.tv_base_title)
    TextView tv_base_title;

    @Override
    public void initIntentData() {
        setChildLayout(R.layout.activity_choose_photo);
    }

    @Override
    public void initView() {
        tv_base_title.setText("晒单");
        photoGridAdapter = new PhotoGridAdapter(mPhotoList, this);
        grid_photo.setAdapter(photoGridAdapter);

        grid_photo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               /* if (mPhotoList.size() == 3) {
                    return;
                } else {*/
                if (position == mPhotoList.size() || mPhotoList.size() == 0) {
                    openPhoto();
                } else {
                    return;
                }
//                }
            }
        });

    }

    @Override
    public void initData() {

    }

    public void openPhoto() {
        ThemeConfig themeConfig = ThemeConfig.DEFAULT;
        FunctionConfig.Builder functionConfigBuilder = new FunctionConfig.Builder();
        ImageLoader imageLoader = new GlideImageLoader();
        PauseOnScrollListener pauseOnScrollListener = new GlidePauseOnScrollListener(false, true);
        functionConfigBuilder.setMutiSelectMaxSize(3 - mPhotoList.size()); //最大数量
        functionConfigBuilder.setEnableEdit(false); //不可编辑
        functionConfigBuilder.setEnableCrop(false); //不可裁剪
        functionConfigBuilder.setEnableRotate(false); //不可旋转
        functionConfigBuilder.setEnableCamera(true); //显示相机
        functionConfigBuilder.setEnablePreview(true); //启动预览

//        functionConfigBuilder.setSelected(mPhotoList);//添加过滤集合
        final FunctionConfig functionConfig = functionConfigBuilder.build();

        CoreConfig coreConfig = new CoreConfig.Builder(this, imageLoader, themeConfig)
                .setFunctionConfig(functionConfig)
                .setPauseOnScrollListener(pauseOnScrollListener)
                .build();
        GalleryFinal.init(coreConfig);


        ActionSheet.createBuilder(this, getSupportFragmentManager())
                .setCancelButtonTitle("取消")
                .setOtherButtonTitles("打开相册", "拍照")
                .setCancelableOnTouchOutside(true)
                .setListener(new ActionSheet.ActionSheetListener() {
                    @Override
                    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

                    }

                    @Override
                    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
                        String path = "/sdcard/pk1-2.jpg";
                        switch (index) {
                            case 0:
                                GalleryFinal.openGalleryMuti(REQUEST_CODE_GALLERY, functionConfig, mOnHanlderResultCallback);
                                break;
                            case 1:
                                GalleryFinal.openCamera(REQUEST_CODE_CAMERA, functionConfig, mOnHanlderResultCallback);
                                break;
                            default:
                                break;
                        }
                    }
                })
                .show();

    }

    //图片返回
    private GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            Log.e("111", "aaa===" + resultList.size());
            if (resultList != null) {
                mPhotoList.addAll(resultList);
                Log.e("111", "ddd===" + mPhotoList.size());
//                mChoosePhotoListAdapter.notifyDataSetChanged();
                photoGridAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            Toast.makeText(ChoosePhotoActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
        }
    };

}
