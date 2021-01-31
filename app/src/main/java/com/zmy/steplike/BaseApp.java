package com.zmy.steplike;

import android.app.Application;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;

/**
 * @Description: java类作用描述
 * @Author: zhangmengyun
 * @CreateDate: 2020-08-26 17:14
 * @Notice 无
 */
public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        insatnce = this;
        Utils.init(this);
        LogUtils.getConfig().setLogSwitch(BuildConfig.DEBUG);
//        initDatabase();
    }
    public static BaseApp insatnce;

    public static BaseApp getInstance() {
        return insatnce;
    }

}
