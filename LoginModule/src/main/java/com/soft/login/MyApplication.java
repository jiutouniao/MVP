package com.soft.login;

import android.app.Application;
import android.content.Context;

/**
 * description:所有用户的公共的数据信息类
 * Date: 2016/9/8 18:04
 * User: shaobing
 */
public class MyApplication extends Application {

    private static Context mContext;

    /**
     * 获取系统Context
     * @return 返回值
     */
    public static Context getAppContext(){



        return mContext;


    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        // 注册crashHandler
//        crashHandler.init(getApplicationContext());
//		// 发送以前没发送的报告(可选)
//		crashHandler.sendPreviousReportsToServer();
    }
}
