package com.zmy.steplike;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {


    EditText edit_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_main);
        edit_count = findViewById(R.id.edit_count);
    }


    public void startLike(View view) {
        //先检测微信服务是否开启
        if (MyUtils.isAccessibilitySettingsOn(this)) {
            //开启
            //获取点赞步数
            Integer count = 0;
            try {
                count = Integer.valueOf(edit_count.getText().toString());
            } catch (Exception e) {

            }

            MyUtils.post(MessageEvent.EVENT_TYPE_START_LIKE, count);
            MyUtils.openWechat();
        } else {
            //未开启
            MyUtils.openAccessibilitySettings(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        switch (event.eventType) {


        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
