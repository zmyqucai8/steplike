package com.zmy.steplike;


/**
 * @Description: 常量类
 * @Author: zhangmengyun
 * @CreateDate: 2020-02-11 11:43
 * @Notice 无
 */
public class AppConstant {


    /**
     * 配置相关
     */
    public static class Config {

        //debug模式 无网络：允许登录和绑定设备
        public static final boolean DEBUG_NO_NETWORK = false;
        //ble是否是前台服务
        public static final boolean IS_START_FOREGROUD_SERIVECE = true;

        //(生产环境）
        public static final String BASE_URL = "http://112.74.113.42:8080/voice";

        //(测试环境）
//        public static final String BASE_URL = "http://112.74.113.42:8081/voice";
        //反馈文本最小长度
        public static final int FEEDBACK_TEXT_MIN_LENGTH = 10;
        //反馈文本最大长度：（修改也要修改xml属性）
        public static final int FEEDBACK_TEXT_MAX_LENGTH = 300;
        //启动页延时时间
        public static final int SPLASH_LOGIN_DELAY = 1500;
        //管理员电话号码
        public static final String ADMIN_PHONENUMBER = "15356105789";
        //管理员密码；
        public static final String ADMIN_PASSWORD = "z723526144";

    }


    //微信包名
    public static final String WECAHT_PACKAGENAME = "com.tencent.mm";
    //1.百度地图包名
    public static final String BAIDUMAP_PACKAGENAME = "com.baidu.BaiduMap";
    //2.高德地图包名
    public static final String GAODEMAP_PACKAGENAME = "com.autonavi.minimap";
    //3.腾讯地图包名
    public static final String QQMAP_PACKAGENAME = "com.tencent.map";


    /**
     * API接口
     */
    public static class Api {

    }


    public static class WechatClass {
        //微信首页
        public static final String WECHAT_CLASS_LAUNCHUI = "com.tencent.mm.ui.LauncherUI";


        //微信运动排行榜页面
        public static final String WECHAT_CLASS_RANK_INFO = "com.tencent.mm.plugin.exdevice.ui.ExdeviceRankInfoUI";


        public static final String WECHAT_CLASS_LISTVIEW = "android.widget.ListView";



        //微信通讯录联系人-个人资料页面
        public static final String WECHAT_CLASS_CONTACTINFOUI = "com.tencent.mm.plugin.profile.ui.ContactInfoUI";

        //微信聊天页面
        public static final String WECHAT_CLASS_CHATUI = "com.tencent.mm.ui.chatting.ChattingUI";

        //微信语音/视频 通话页面
        public static final String WECHAT_CLASS_VOIP_VIDEO = "com.tencent.mm.plugin.voip.ui.VideoActivity";


        //文本消息详情预览界面
        public static final String WECHAT_CLASS_MESSAGE_TEXT_PREVIEW = "com.tencent.mm.ui.chatting.TextPreviewUI";

        //位置消息详情页面
        public static final String WECHAT_CLASS_MESSAGE_MAP_PREVIEW = "com.tencent.mm.plugin.location_soso.SoSoProxyUI";


        //打开位置消息详情页面的时候，有时候回出现这个window
//        ClassName: com.tencent.mm.ui.base.p; Text: [正在获取位置信息]
        public static final String WECHAT_CLASS_MESSAGE_MAP_PREVIEW_LOADING = "com.tencent.mm.ui.base.";


    }


    public static class WechatId {
        /**
         * 通讯录界面
         */
        //listview Id
        //(7.0.9) com.tencent.mm:id/oc
        //(7.0.13) com.tencent.mm:id/f4
        //(7.0.14) com.tencent.mm:id/f4
        public static final String WECHATID_CONTACTUI_LISTVIEW_ID = "com.tencent.mm:id/f4";
        //item id 但要父++层级才可以点击
//        public static final String WECHATID_CONTACTUI_ITEM_ID = "com.tencent.mm:id/p7";
        //昵称文本id
        //（7.0.9）com.tencent.mm:id/pa
        //（7.0.13 不是TextView了,不知道你能不能取到Text，如果不能取到读content-desc）com.tencent.mm:id/dux
        //(7.0.14)com.tencent.mm:id/dy5
        public static final String WECHATID_CONTACTUI_NAME_ID = "com.tencent.mm:id/dy5";
        //联系人数量id  TextView
        //(7.0.9)com.tencent.mm:id/b5o
        //(7.0.13)com.tencent.mm:id/ax_
        //(7.0.14)com.tencent.mm:id/azb
        public static final String WECHATID_CONTACTUI_COUNT_ID = "com.tencent.mm:id/azb";


        /**
         * 微信消息主界面 (注意不是聊天界面）
         */
        //主界面微信，消息列表listview
        //(7.0.9)com.tencent.mm:id/dcf
        //(7.0.13)com.tencent.mm:id/dd6
        //(7.0.14)com.tencent.mm:id/dg2
        public static final String WECHATID_MESSAGEUI_MESSAGE_LIST_ID = "com.tencent.mm:id/dg2";
        //消息列表 聊天名称 android.view.View
        //(7.0.9)com.tencent.mm:id/baj
        //(7.0.13)com.tencent.mm:id/e0n
        //(7.0.14)com.tencent.mm:id/e3x
        public static final String WECHATID_MESSAGEUI_NAME_ID = "com.tencent.mm:id/e3x";

        //消息列表 顶部标题，双击可以回到顶部  android.widget.LinearLayout
        //(7.0.9) com.tencent.mm:id/mb
        //(7.0.13)  com.tencent.mm:id/dk
        //(7.0.14)  com.tencent.mm:id/dk
        public static final String WECHATID_MESSAGEUI_TITLE_ID = "com.tencent.mm:id/dk";


        //层级关系有三层 ，这是最顶层的
        //获取微信底部菜单总的父布局， android.widget.RelativeLayout
        public static final String WECHATID_MESSAGEUI_BOTTOM_MENU_TOP_PRENT_LAYOUT_ID = "com.tencent.mm:id/czl";
        //获取最底层的菜单布局 android.widget.LinearLayout， 但它无法点击，获取上层父布局才可以点击，上层父布局属于二层（二层布局没有id）
        public static final String WECHATID_MESSAGEUI_BOTTOM_MENU_PRENT_LAYOUT_ID = "com.tencent.mm:id/cn_";


        /**
         * 通讯录-》个人资料页面
         */
        //发消息 android.widget.LinearLayout
        //(7.0.9) com.tencent.mm:id/b_d
        //(7.0.13)  com.tencent.mm:id/b0_
        //(7.0.14) com.tencent.mm:id/b2b
        public static final String WECHATID_CONTACT_INFOUI_OPEN_CHAT_ID = "com.tencent.mm:id/b2b";

        /**
         * 聊天界面
         */
        //语音文本切换id  ：切换到键盘  ：切换到按住说话 android.widget.ImageButton
        //(7.0.9)  com.tencent.mm:id/aqc
        //(7.0.13)  com.tencent.mm:id/am9
        //(7.0.14)  com.tencent.mm:id/anc
        public static final String WECHATID_CHATUI_SWITCH_ID = "com.tencent.mm:id/anc";

        //更多菜单GridView: 如果显示了，表示更多菜单展开，
        //(7.0.9)  com.tencent.mm:id/zo
        //(7.0.13) com.tencent.mm:id/pu
        //(7.0.14) com.tencent.mm:id/pw
        public static final String WECHATID_CHATUI_MORE_MENU_GRIDVIEW_ID = "com.tencent.mm:id/pw";
        //按住说话 :android.widget.Button
        //(7.0.9)  com.tencent.mm:id/aqf
        //(7.0.13) com.tencent.mm:id/gms
        //(7.0.14)com.tencent.mm:id/grk
        public static final String WECHATID_CHATUI_VOICE = "com.tencent.mm:id/grk";

        //更多菜单+号 android.widget.ImageButton
        //(7.0.9)  com.tencent.mm:id/aqk
        //(7.0.13) com.tencent.mm:id/ajp
        //(7.0.14) com.tencent.mm:id/aks
        public static final String WECHATID_CHATUI_OPEN_MORE_MENU = "com.tencent.mm:id/aks";


        //聊天页面右上角更多按钮（聊天信息），任何聊天类型都有，包括公众号等 ImageButton
        //(7.0.13)com.tencent.mm:id/cj
        //(7.0.14)com.tencent.mm:id/cj
        public static final String WECHATID_CHATUI_CHAT_INFO = "com.tencent.mm:id/cj";


        //聊天名称： TextView
        // (7.0.9) com.tencent.mm:id/lt
        //（7.0.13）com.tencent.mm:id/g7_
        //（7.0.14）com.tencent.mm:id/gas
        public static final String WECHATID_CHATUI_CHAT_NAME = "com.tencent.mm:id/gas";


        //更多菜单-位置菜单- 选项列表（0、发送位置、1、共享位置） android.widget.LinearLayout
        //(7.0.9)com.tencent.mm:id/mg
        //(7.0.13)com.tencent.mm:id/ezx
        //(7.0.14)com.tencent.mm:id/f40
        public static final String WECHATID_CHATUI_LOCATION_MENU_ITEM = "com.tencent.mm:id/f40";

        //地图选择页面：发送按钮父布局，可以根据enable状态判断是否可以发送 android.widget.LinearLayout
        //(7.0.9)com.tencent.mm:id/dnj
        //(7.0.13)com.tencent.mm:id/g7b
        //(7.0.14)com.tencent.mm:id/gau
        public static final String WECHATID_CHATUI_LOCATION_SEND = "com.tencent.mm:id/gau";


        /**
         * 语音、视频通话界面: 接听和挂断都是同一个图片按钮
         * content_des ：接听/挂断
         */
        //不论是语音通话、还是视频通话， 接听、挂断的图片按钮，ImageView 都是同一个ID，并且可点击，需要判断content_des是'接听'还是'挂断'进行操作
        //(7.0.9)com.tencent.mm:id/czx
        //(7.0.13)com.tencent.mm:id/yz
        //(7.0.14)com.tencent.mm:id/z6
        public static final String WECHATID_VOIP_JIETING_GUADUAN = "com.tencent.mm:id/z6";
        //免提 CheckBox
        //(7.0.9)com.tencent.mm:id/fpl
        //(7.0.13)com.tencent.mm:id/gpe
        //(7.0.14)com.tencent.mm:id/gu7
        public static final String WECHATID_VOIP_MIANTI = "com.tencent.mm:id/gu7";


        //视频通话时，切换到语音通话、转换摄像头的文本id，TextView ，这两个id都是一样的，以此来判断视频是否接通
        //(7.0.9)com.tencent.mm:id/czy
        //(7.0.13)com.tencent.mm:id/z2
        //(7.0.14)com.tencent.mm:id/z9
        public static final String WECHATID_VOIP_SWITCH_MODE = "com.tencent.mm:id/z9";
        //收到微信视频邀请，单击接通时，如果不是wifi，会提示用户流量消耗，要点击确定，该按钮就是Button确定按钮,点击后接通
        //(7.0.9)com.tencent.mm:id/b49
        //(7.0.13)com.tencent.mm:id/dm3
        //(7.0.14)com.tencent.mm:id/doz
        public static final String WECHATID_VOIP_4G_CONNECT_CONFIRM = "com.tencent.mm:id/doz";


        /**
         * 微信聊天页面
         */

        //聊天界面，列表listview
        //(7.0.9)com.tencent.mm:id/ag
        //(7.0.13)com.tencent.mm:id/alz
        //(7.0.14)com.tencent.mm:id/an3
        public static final String WECHATID_CHATUI_MESSAGE_LISTVIEW = "com.tencent.mm:id/an3";


        //消息列表-语音消息:TextView（语音时间文本可点击 click=true ,如果是未播放的语音desc会显示 ：语音x秒未播放）
        //(7.0.9)com.tencent.mm:id/awd
        //(7.0.13)com.tencent.mm:id/ani
        //(7.0.14)com.tencent.mm:id/aon
        public static final String WECHATID_CHATUI_MESSAGE_VOICE = "com.tencent.mm:id/aon";
        //未读的语音消息：ImageView 那个小红点
        //(7.0.9)com.tencent.mm:id/awf
        //(7.0.13)com.tencent.mm:id/amx
        //(7.0.14)com.tencent.mm:id/ao1
        public static final String WECHATID_CHATUI_MESSAGE_VOICE_UNREAD = "com.tencent.mm:id/ao1";

        //        android.widget.RelativeLayout
        //头像左侧父布局[16,431][156,571]
        //头像右侧父布局[924,319][1064,459]
        //(7.0.9)com.tencent.mm:id/are
        //(7.0.13)com.tencent.mm:id/aju
        //(7.0.14)com.tencent.mm:id/akx
        public static final String WECHATID_CHATUI_AVATAR_LAYOUT = "com.tencent.mm:id/akx";


        //消息列表的文本消息：自定义view android.view.View(无法读取到text） ，模拟双击，进入详情页面进行获取
        //(7.0.9)com.tencent.mm:id/pq
        //(7.0.13)com.tencent.mm:id/ak8
        //(7.0.14)com.tencent.mm:id/ala
        public static final String WECHATID_CHATUI_MESSAGE_TEXT = "com.tencent.mm:id/ala";


        //消息列表的位置消息：标题和文本：TextView
        //位置标题（名称）
        public static final String WECHATID_CHATUI_MESSAGE_MAP_TITLE = "com.tencent.mm:id/an9";
        //位置文本（具体地址）
        public static final String WECHATID_CHATUI_MESSAGE_MAP_TEXT = "com.tencent.mm:id/an7";

        //位置消息详情页，导航按钮
        public static final String WECHATID_CHATUI_MESSAGE_MAP_NAVI = "com.tencent.mm:id/d4w";
        //位置消息详情页，点击导航按钮，弹出来的地图APP选择框, 地图APP名称
        public static final String WECHATID_CHATUI_MESSAGE_MAP_APP_NAME = "com.tencent.mm:id/gam";


        //文本消息详情页面的文本：TextView ，从消息列表文本消息双击进入的
        //(7.0.13)com.tencent.mm:id/c6s
        //(7.0.14)com.tencent.mm:id/c9a
        public static final String WECHATID_CHATUI_MESSAGE_TEXT_CONTNET = "com.tencent.mm:id/c9a";
    }


}
