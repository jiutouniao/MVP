package com.soft.common.ui.base.base;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.soft.common.utils.MIUIUtils;

/**
 * description:Activity基类
 * Date: 2016/6/15
 * Time: 16:17
 */
public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //系统状态栏字体变深色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        if(MIUIUtils.isMIUI()){
            MIUIUtils.MIUISetStatusBarLightMode(getWindow(),true);
        }
        //设置每个Activity为竖屏模式
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        RunningActivityManager.getInstance().addActivity(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //避免activity里面添加fragment时，在待机过程中出现异常
//        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        RunningActivityManager.getInstance().removeActivity(this);
        super.onDestroy();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
