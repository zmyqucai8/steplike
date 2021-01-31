package com.zmy.steplike;

/**
 * @Description: java类作用描述
 * @Author: zhangmengyun
 * @CreateDate: 2020-08-26 15:58
 * @Notice 无
 */
public class MessageEvent {


    //开始点赞 value:int 最低点赞步数
    public static final int EVENT_TYPE_START_LIKE = 1;


    public int eventType;

    public MessageEvent(int eventType) {
        this.eventType = eventType;
    }

    public Object objectValue;

}
