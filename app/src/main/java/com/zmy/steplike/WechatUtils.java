package com.zmy.steplike;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityNodeInfo;

import androidx.annotation.RequiresApi;

import com.blankj.utilcode.util.LogUtils;

import java.util.List;


/**
 * Created by Clearlee
 * 2017/12/22.
 */
public class WechatUtils {

    //搜索的名称
    public static String NAME;
    //录音时长 单位毫秒
    public static long RECORDING_LENGTH = 1000 * 60;


    public static String CONTENT;

    /**
     * 在当前页面查找文字内容并点击
     *
     * @param text
     */
    public static void findTextAndClick(AccessibilityService accessibilityService, String text) {

        AccessibilityNodeInfo accessibilityNodeInfo = accessibilityService.getRootInActiveWindow();
        if (accessibilityNodeInfo == null) {
            return;
        }

        List<AccessibilityNodeInfo> nodeInfoList = accessibilityNodeInfo.findAccessibilityNodeInfosByText(text);
        if (nodeInfoList != null && !nodeInfoList.isEmpty()) {
            for (AccessibilityNodeInfo nodeInfo : nodeInfoList) {
                if (nodeInfo != null && (text.equals(nodeInfo.getText()) || text.equals(nodeInfo.getContentDescription()))) {
                    performClick(nodeInfo);
                    break;
                }
            }
        }
    }


    public static void findTextAndClick2(AccessibilityService accessibilityService, String text) {
        findTextAndClick2(accessibilityService, text, new AccessibilityService.GestureResultCallback() {
            @Override
            public void onCompleted(GestureDescription gestureDescription) {
                super.onCompleted(gestureDescription);
                LogUtils.e("点击完成");
            }

            @Override
            public void onCancelled(GestureDescription gestureDescription) {
                super.onCancelled(gestureDescription);
                LogUtils.e("点击取消");
            }
        });
    }

    /**
     * 在当前页面查找文字内容并点击
     *
     * @param text
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void findTextAndClick2(AccessibilityService accessibilityService, String text, AccessibilityService.GestureResultCallback callback) {

        AccessibilityNodeInfo accessibilityNodeInfo = accessibilityService.getRootInActiveWindow();
        if (accessibilityNodeInfo == null) {
            return;
        }

        List<AccessibilityNodeInfo> nodeInfoList = accessibilityNodeInfo.findAccessibilityNodeInfosByText(text);
        if (nodeInfoList != null && !nodeInfoList.isEmpty()) {
            for (AccessibilityNodeInfo nodeInfo : nodeInfoList) {
                if (nodeInfo != null && (text.equals(nodeInfo.getText()) || text.equals(nodeInfo.getContentDescription()))) {
//                    performClick(nodeInfo);

                    Rect rect = new Rect();
                    nodeInfo.getBoundsInScreen(rect);

                    LogUtils.e(text + " view位置：left=" + rect.left + " right=" + rect.right + " top=" + rect.top + " bottom=" + rect.bottom + " centerX=" + rect.centerX() + " centerY=" + rect.centerY());

                    // 位置：left=130 right=842 top=2118 bottom=2226 centerX=486 centerY=2172

                    GestureDescription.Builder builder = new GestureDescription.Builder();

                    //手势路径
                    Path path = new Path();

                    Point position = new Point(rect.left, rect.centerY());
                    path.moveTo(position.x, position.y);
                    long startTime = 0;
                    long duration = 1;

                    //创建手势
                    GestureDescription gestureDescription = builder
                            .addStroke(new GestureDescription.StrokeDescription(path, startTime, duration))
                            .build();

                    //开始手势
                    boolean dispatchGesture = accessibilityService.dispatchGesture(gestureDescription, callback, null);

                    break;
                }
            }
        }
    }


    /**
     * 检查view id并进行点击
     *
     * @param accessibilityService
     * @param id
     * @param callback
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void findViewIdAndClick2(AccessibilityService accessibilityService, String id, AccessibilityService.GestureResultCallback callback) {

        AccessibilityNodeInfo accessibilityNodeInfo = accessibilityService.getRootInActiveWindow();
        if (accessibilityNodeInfo == null) {
            return;
        }

        List<AccessibilityNodeInfo> nodeInfoList = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(id);
        if (nodeInfoList != null && !nodeInfoList.isEmpty()) {
            AccessibilityNodeInfo nodeInfo = nodeInfoList.get(0);
            if (nodeInfo != null) {
                Rect rect = new Rect();
                nodeInfo.getBoundsInScreen(rect);
//                LogUtils.e(text + " view位置：left=" + rect.left + " right=" + rect.right + " top=" + rect.top + " bottom=" + rect.bottom + " centerX=" + rect.centerX() + " centerY=" + rect.centerY());


                GestureDescription.Builder builder = new GestureDescription.Builder();

                //手势路径
                Path path = new Path();

                Point position = new Point(rect.left, rect.centerY());
                path.moveTo(position.x, position.y);
                long startTime = 0;
                long duration = 1;

                //创建手势
                GestureDescription gestureDescription = builder
                        .addStroke(new GestureDescription.StrokeDescription(path, startTime, duration))
                        .build();

                //开始手势
                boolean dispatchGesture = accessibilityService.dispatchGesture(gestureDescription, callback, null);


            }

        }
    }


    /**
     * 检查viewId进行点击
     *
     * @param accessibilityService
     * @param id
     */
    public static void findViewIdAndClick(AccessibilityService accessibilityService, String id) {

        AccessibilityNodeInfo accessibilityNodeInfo = accessibilityService.getRootInActiveWindow();
        if (accessibilityNodeInfo == null) {
            return;
        }

        List<AccessibilityNodeInfo> nodeInfoList = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(id);
        if (nodeInfoList != null && !nodeInfoList.isEmpty()) {
            for (AccessibilityNodeInfo nodeInfo : nodeInfoList) {
                if (nodeInfo != null) {
                    performClick(nodeInfo);
                    break;
                }
            }
        }
    }


    public static boolean findViewByIdAndPasteContent(AccessibilityService accessibilityService, String id, String content) {
        AccessibilityNodeInfo rootNode = accessibilityService.getRootInActiveWindow();
        if (rootNode != null) {
            List<AccessibilityNodeInfo> editInfo = rootNode.findAccessibilityNodeInfosByViewId(id);
            if (editInfo != null && !editInfo.isEmpty()) {
                Bundle arguments = new Bundle();
                arguments.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, content);
                editInfo.get(0).performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, arguments);
                return true;
            }
            return false;
        }
        return false;
    }

    public static String findTextById(AccessibilityService accessibilityService, String id) {
        AccessibilityNodeInfo rootInfo = accessibilityService.getRootInActiveWindow();
        if (rootInfo != null) {
            List<AccessibilityNodeInfo> userNames = rootInfo.findAccessibilityNodeInfosByViewId(id);
            if (userNames != null && userNames.size() > 0) {
                String name = userNames.get(0).getText().toString();
                return name;
            }
        }
        return null;
    }


    /**
     * 打印该node下所有node
     *
     * @return
     */
    public static void showAllNode(AccessibilityNodeInfo node) {

        if (node != null) {
            for (int i = 0; i < node.getChildCount(); i++) {
                AccessibilityNodeInfo child = node.getChild(i);
                if (child == null) {
                    LogUtils.e("child为空=" + node.getClassName());
                    continue;
                }
                if (child.getChildCount() > 0) {
                    LogUtils.e("父节点：" + child.getClassName());
                    showAllNode(child);
                } else {
//                    LogUtils.e("     子节点：" + child.getClassName());
//
//                    if (!TextUtils.isEmpty(child.getText())) {
//                        LogUtils.e("text=" + child.getText());
//                    }
//                    if (!TextUtils.isEmpty(child.getContentDescription())) {
//                        LogUtils.e("des=" + child.getContentDescription());
//                    }
                }

            }
        }

    }

    /**
     * 从父节点下获取一个子节点
     *
     * @param className 需要返回节点的名称
     * @return
     */
    public static AccessibilityNodeInfo getNodeInfo(AccessibilityNodeInfo prentNode, String className) {

        AccessibilityNodeInfo node = null;
        if (prentNode != null) {
            for (int i = 0; i < prentNode.getChildCount(); i++) {


                AccessibilityNodeInfo child = prentNode.getChild(i);
                if (child.getClassName().equals(className)) {
                    return child;
                }
                if (child.getChildCount() > 0) {
                    node =  getNodeInfo(child, className);
                }
            }
        }

        return node;
    }


    /**
     * 在当前页面查找对话框文字内容并点击
     *
     * @param text1 默认点击text1
     * @param text2
     */
    public static void findDialogAndClick(AccessibilityService accessibilityService, String text1, String text2) {

        AccessibilityNodeInfo accessibilityNodeInfo = accessibilityService.getRootInActiveWindow();
        if (accessibilityNodeInfo == null) {
            return;
        }

        List<AccessibilityNodeInfo> dialogWait = accessibilityNodeInfo.findAccessibilityNodeInfosByText(text1);
        List<AccessibilityNodeInfo> dialogConfirm = accessibilityNodeInfo.findAccessibilityNodeInfosByText(text2);
        if (!dialogWait.isEmpty() && !dialogConfirm.isEmpty()) {
            for (AccessibilityNodeInfo nodeInfo : dialogWait) {
                if (nodeInfo != null && text1.equals(nodeInfo.getText())) {
                    performClick(nodeInfo);
                    break;
                }
            }
        }

    }

    //模拟点击事件
    public static void performClick(AccessibilityNodeInfo nodeInfo) {
        if (nodeInfo == null) {
            return;
        }
        if (nodeInfo.isClickable()) {
            nodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
        } else {
            performClick(nodeInfo.getParent());
        }
    }

    //模拟返回事件
    public static void performBack(AccessibilityService service) {
        if (service == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
        }
    }


}
