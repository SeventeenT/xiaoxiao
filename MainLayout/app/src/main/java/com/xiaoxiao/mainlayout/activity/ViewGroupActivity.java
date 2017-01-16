package com.xiaoxiao.mainlayout.activity;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xiaoxiao.mainlayout.R;
import com.xiaoxiao.mainlayout.base.BaseActivity;
import com.xiaoxiao.mainlayout.entity.PaopaoBean;
import com.xiaoxiao.mainlayout.view.MyViewGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by xiaoxiao 2016/10/12.
 */
public class ViewGroupActivity extends BaseActivity {
    private final static String TAG = "ViewGroupActivity";
    List<PaopaoBean> mList = new ArrayList<>();
    private RelativeLayout relativeLayout;
    private boolean isStart = true;
    private Vibrator vibrator;
    private MediaPlayer mediaPlayer;

    @Bind(R.id.tv_base_title)
    TextView tv_title;

    @Bind(R.id.my_viewgroup)
    MyViewGroup my_viewgroup;
    @Bind(R.id.btn_start)
    Button btn_start;

    @Override
    public void initIntentData() {
        setChildLayout(R.layout.activity_view_group);
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);

        mediaPlayer = new MediaPlayer();//这个我定义了一个成员函数

        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.seekTo(0);
            }
        });

        AssetFileDescriptor file =this.getResources().openRawResourceFd(

                R.raw.xiaoxi);

        try{

            mediaPlayer.setDataSource(file.getFileDescriptor(),

                    file.getStartOffset(), file.getLength());

            file.close();

            mediaPlayer.setVolume(1f,1f);

            mediaPlayer.prepare();

        }catch (IOException ioe) {

            Log.w(TAG, ioe);

            mediaPlayer = null;

        }

    }

    @Override
    public void initView() {
        tv_title.setText("随机泡泡");
        btn_start.setText("开始");
    }

    @Override
    public void initData() {
        //想设置震动大小可以通过改变pattern来设定，如果开启时间太短，震动效果可能感觉不到
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    private void showDate() {
        mList.clear();
        Random random = new Random();
        int count = random.nextInt(4) + 1;

        int id_front;
        int id_after;
        int task_type;
        String task_name = "";

        for (int i = 0; i < count; i++) {
            task_type = random.nextInt(3);
            id_front = random.nextInt(9) + 1;
            id_after = random.nextInt(10);
            switch (task_type) {
                case 0:
                    task_name = "常规";
                    break;
                case 1:
                    task_name = "直通车";
                    break;
                case 2:
                    task_name = "活动";
                    break;
            }
            mList.add(new PaopaoBean("ID:" + id_front + "***" + id_after, "接到一个" + task_name + "任务"));
        }

        getPaopao(mList, my_viewgroup);
    }

    private void getPaopao(List<PaopaoBean> paopao, MyViewGroup viewgroup) {
        viewgroup.removeAllViews();
        for (int i = 0; i < paopao.size(); i++) {
            relativeLayout = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.item_viewgroup_paopao, my_viewgroup, false);
            ImageView img_head = (ImageView) relativeLayout.findViewById(R.id.img_head);
            TextView tv_nick = (TextView) relativeLayout.findViewById(R.id.tv_get_task_nick);
            TextView tv_type = (TextView) relativeLayout.findViewById(R.id.tv_get_task_type);
            viewgroup.addView(relativeLayout);
//            Glide.with(this).load(R.drawable.pop_taobao).into(img_head);
            tv_nick.setText(paopao.get(i).getTv_nick());
            tv_type.setText(paopao.get(i).getTv_type());
        }
        generateAlphaAnimation();
    }

    AlphaAnimation alphaAnimation;

    //渐变动画
    private void generateAlphaAnimation() {
        Log.e(TAG, "222");
        alphaAnimation = new AlphaAnimation(1f, 0);  //渐变动画
        alphaAnimation.setDuration(1500);
        alphaAnimation.setStartOffset(1000);
        alphaAnimation.start();
        my_viewgroup.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (!isStart) {
                    alphaAnimation.cancel();
                    my_viewgroup.clearAnimation();
                    mList.clear();
                    my_viewgroup.removeAllViews();
                    relativeLayout.removeAllViews();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (!isStart) {
                                showDate();
                            }
                        }
                    }, 1000);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @OnClick({R.id.btn_start})
    public void setClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                if (isStart) {

                    // 停止 开启 停止 开启
                    long[] pattern = {100, 400, 100, 400};
                    //重复两次上面的pattern 如果只想震动一次，index设为-1
                    vibrator.vibrate(pattern, -1);

                    mediaPlayer.start();

                    btn_start.setText("停止");
                    isStart = false;
                    showDate();
                } else {
                    alphaAnimation.cancel();
                    my_viewgroup.clearAnimation();
                    mList.clear();
                    my_viewgroup.removeAllViews();
                    btn_start.setText("开始");
                    relativeLayout.removeAllViews();
                    isStart = true;
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        vibrator.cancel();
    }
}
