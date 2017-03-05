package com.soft.login.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.WindowManager;
import android.widget.ImageView;

import com.soft.common.ui.base.base.BaseActivity;
import com.soft.login.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity {

    private final static int SWITCH_MAIN_ACTIVITY = 1000;
    private final static int SWITCH_LOGIN_ACTIVITY = 1001;
    @BindView(R.id.iv_launch)
    ImageView ivLaunch;
    private String account;
    private String token;

    public Handler mHandler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SWITCH_MAIN_ACTIVITY:
//                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
//                    finish();
//                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    break;
                case SWITCH_LOGIN_ACTIVITY:
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        initView();
    }


    /**
     * 开机动画初始化
     */
    private void initView() {
        mHandler.sendEmptyMessageDelayed(SWITCH_LOGIN_ACTIVITY, 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
