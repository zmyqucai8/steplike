package com.zmy.steplike;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.text.TextUtils;
import android.widget.Toast;


import com.blankj.utilcode.util.Utils;

import org.greenrobot.eventbus.EventBus;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * @Description: java类作用描述
 * @Author: zhangmengyun
 * @CreateDate: 2020-08-26 16:02
 * @Notice 无
 */
public class MyUtils {
    /**
     * 判断无障碍是否开启
     *
     * @param mContext
     * @return
     */
    public static boolean isAccessibilitySettingsOn(Context mContext) {

        Class<? extends AccessibilityService> clazz = WeChatService.class;
        int accessibilityEnabled = 0;
        final String service = mContext.getPackageName() + "/" + clazz.getCanonicalName();
        try {
            accessibilityEnabled = Settings.Secure.getInt(mContext.getApplicationContext().getContentResolver(),
                    Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        TextUtils.SimpleStringSplitter mStringColonSplitter = new TextUtils.SimpleStringSplitter(':');
        if (accessibilityEnabled == 1) {
            String settingValue = Settings.Secure.getString(mContext.getApplicationContext().getContentResolver(),
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (settingValue != null) {
                mStringColonSplitter.setString(settingValue);
                while (mStringColonSplitter.hasNext()) {
                    String accessibilityService = mStringColonSplitter.next();
                    if (accessibilityService.equalsIgnoreCase(service)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * 打开无障碍服务设置
     *
     * @param context
     */
    public static void openAccessibilitySettings(Context context) {
        try {
            //打开系统设置中辅助功能
            Intent intent = new Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            String text = String.format("请开启%s", context.getString(R.string.accessibility_name));
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打开微信
     */
    public static void openWechat() {
        Intent intent = new Intent();
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        intent.setClassName(AppConstant.WECAHT_PACKAGENAME, AppConstant.WechatClass.WECHAT_CLASS_LAUNCHUI);
        Utils.getApp().startActivity(intent);
    }

    /**
     * 发送一个event
     * 不携带value
     *
     * @param eventType {@link MessageEvent}
     */
    public static void post(int eventType) {
        MessageEvent event = new MessageEvent(eventType);
        EventBus.getDefault().post(event);
    }


    /**
     * 发送一个event
     * 携带一个object参数
     *
     * @param eventType
     */
    public static void post(int eventType, Object object) {
        MessageEvent event = new MessageEvent(eventType);
        event.objectValue = object;
        EventBus.getDefault().post(event);
    }

}
