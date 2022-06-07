package com.zmy.steplike;

import android.accessibilityservice.AccessibilityService;
import android.os.Handler;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


import java.util.List;

import static android.view.accessibility.AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED;
import static android.view.accessibility.AccessibilityEvent.TYPE_VIEW_CLICKED;
import static android.view.accessibility.AccessibilityEvent.TYPE_VIEW_SCROLLED;
import static android.view.accessibility.AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED;
import static android.view.accessibility.AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED;


/**
 * @Description: 微信辅助服务
 * @Author: zhangmengyun
 * @CreateDate: 2020-02-11 11:43
 * @Notice 无
 */
public class WeChatService extends AccessibilityService {


    /*流程常量start*/
    //当前流程
    public static int current_process = WeChatService.process_default;
    //当前步骤
    public static int current_step = WeChatService.step_default;
    //当前窗口名称
    public String currentWindowClassName = "";

    //默认无流程
    public static final int process_default = 0;
    //默认无步骤
    public static final int step_default = 0;


    //流程:
    public static final int process_start_like = 10;
    //步骤:
    public static final int step_like_ing = 11;//点赞中


    //如果是首次开始，第一个数据不点赞
    private boolean isFistStart = true;

    /*流程常量end*/


    /**
     * 必须重写的方法，响应各种事件。
     *
     * @param event
     */
    @Override
    public void onAccessibilityEvent(final AccessibilityEvent event) {
        int eventType = event.getEventType();

        LogUtils.d("当前流程=" + current_process + "当前步骤=" + current_step + "\n事件监听：" + event.toString());


        switch (eventType) {
            case TYPE_WINDOW_STATE_CHANGED:
                //窗口发生变化，打开微信或微信切换窗口都会
                currentWindowClassName = event.getClassName().toString();


                if (currentWindowClassName.equals(AppConstant.WechatClass.WECHAT_CLASS_RANK_INFO)
                        && current_process == process_start_like && current_step == step_default) {
                    //微信排行榜页面
                    new Handler().postDelayed(() -> handleLikeLogic(), 500);
                } else if (current_process == process_start_like && current_step == step_default) {
                    ToastUtils.showLong("请先手动进入微信运动排行榜页面");
                } else if (current_process == process_start_like) {
//                    endProcess();
                }

                break;
            case TYPE_VIEW_CLICKED:

                break;
            case TYPE_WINDOW_CONTENT_CHANGED:
                //窗口内容发生变化

                break;
            case TYPE_VIEW_SCROLLED:
                //滚动
                break;
            case TYPE_NOTIFICATION_STATE_CHANGED:
                //通知
                break;
        }
    }


    private int likeCount = 0;

    /**
     * 处理点赞逻辑
     */
    private void handleLikeLogic() {

        if (current_process != process_start_like) {
            return;
        }
        current_step = step_like_ing;
        List<AccessibilityNodeInfo> listViewNodes = getRootInActiveWindow().findAccessibilityNodeInfosByViewId("com.tencent.mm:id/cmh");//列表 listview
        AccessibilityNodeInfo listViewNode = null;
        if (null != listViewNodes && !listViewNodes.isEmpty()) {
            listViewNode = listViewNodes.get(0);
        }

        if (null != listViewNode) {
            //获取当前屏幕上的人信息
            List<AccessibilityNodeInfo> nameList = listViewNode.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/cmo");//名字
            List<AccessibilityNodeInfo> countList = listViewNode.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/cll");//步数
            List<AccessibilityNodeInfo> likeList = listViewNode.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/cma");//点赞爱心的父布局可点击的

            //结束标志：邀请朋友 TextView
            List<AccessibilityNodeInfo> endNode = listViewNode.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/f94");

//            com.tencent.mm:id/cry 邀请朋友
//            com.tencent.mm:id/gap   xxx占领了封面

            for (int i = 0; i < countList.size(); i++) {
                Integer count = Integer.valueOf(countList.get(i).getText().toString());
                LogUtils.e("名称=" + nameList.get(i).getText().toString() + " 步数=" + count + " 是否点赞=" + (count != 0 && count >= minCount));

                if (isFistStart) {
                    LogUtils.e("自己是第一个不点赞");
                    isFistStart = false;
                    continue;
                }
                if (count != 0 && count >= minCount) {
                    //可以缓存今日点赞过的名称，避免重复点赞：不想做
                    likeList.get(i).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    likeCount++;
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (count != 0 && count < minCount) {
                    ToastUtils.showLong("点赞完成");
                    LogUtils.e("点赞完成1");
                    endProcess();
                }
            }


            //判断是否滚动到底部
            if (endNode != null && !endNode.isEmpty()) {
                //滚动到底部，结束点赞
                ToastUtils.showLong("点赞完成");
                LogUtils.e("点赞完成2");
                endProcess();
            } else {
                //没有滚到底部，继续滚动
                listViewNode.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
                new Handler().postDelayed(() -> {
                    handleLikeLogic();
                }, 500);

            }

        } else {

            if (current_process == process_start_like)
                ToastUtils.showShort("没有找到排行榜列表");
            LogUtils.e("点赞完成3");
            endProcess();
        }

    }


    /**
     * 开始点赞
     */
    private void startLike() {
        startProcess(process_start_like);
    }


    @Override
    public void onInterrupt() {
        LogUtils.e("微信服务链接中断");

    }


    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        LogUtils.e("微信服务已链接");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.e("微信服务已关闭");
        EventBus.getDefault().unregister(this);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
    }


    //最小点赞步数
    private Integer minCount = 0;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {

        LogUtils.e("WechatService event事件类型=" + event.eventType);


        switch (event.eventType) {
            case MessageEvent.EVENT_TYPE_START_LIKE:
                minCount = (Integer) event.objectValue;
                startLike();
                break;
        }
    }


    /**
     * 开始流程
     *
     * @param process
     */
    public void startProcess(int process) {
        current_process = process;
        current_step = step_default;

    }

    /**
     * 结束流程
     */
    public void endProcess() {
        current_process = process_default;
        current_step = step_default;
        LogUtils.e("流程结束");
        isFistStart = true;
        likeCount = 0;
    }


}