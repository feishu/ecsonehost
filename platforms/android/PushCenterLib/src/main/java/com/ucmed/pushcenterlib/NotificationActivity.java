package com.ucmed.pushcenterlib;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.WXGlobalEventReceiver;

import static com.ucmed.pushcenterlib.TSLNotificationUtil.PUSH_EVENT_NAME;
import static com.ucmed.pushcenterlib.TSLNotificationUtil.setTopApp;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Intent intent = getIntent();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("title", intent.getStringExtra("title"));
        jsonObject1.put("content", intent.getStringExtra("content"));
        if (intent.getStringExtra("customContent") != null)
            jsonObject1.put("customContent", intent.getStringExtra("customContent"));
        jsonObject1.put("type", "PUSH_CLICK_CALLBACK");

        Intent intent1 = new Intent(WXGlobalEventReceiver.EVENT_ACTION);
        intent1.putExtra(WXGlobalEventReceiver.EVENT_NAME, this.getPackageName()+PUSH_EVENT_NAME);
        intent1.putExtra(WXGlobalEventReceiver.EVENT_PARAMS, jsonObject1.toJSONString());
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent1);
//        this.sendBroadcast(intent1);
        setTopApp(this);
        finish();
    }
}
