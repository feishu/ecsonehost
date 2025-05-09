package com.ucmed.pushcenterlib.XgPush;


import android.content.Context;
import android.util.Log;

import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushRegisterResult;
import com.tencent.android.tpush.XGPushShowedResult;
import com.tencent.android.tpush.XGPushTextMessage;
import com.ucmed.pushcenterlib.PushCenterUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liaochengzong on 2018/4/12.
 */

public class TSLXGReceiver extends XGPushBaseReceiver {
    protected static String PUSH_EVENT_NAME = "TSL2-PUSH";

    @Override
    public void onRegisterResult(Context context, int i, XGPushRegisterResult xgPushRegisterResult) {
        Log.e("TSLXGReceiver", "onRegisterResult i=" + i + xgPushRegisterResult.toJson().toString());

        if (xgPushRegisterResult.getAccount() != null) {
            if (xgPushRegisterResult.getTicketType() == 32) {
                //账号绑定
                Map map = new HashMap<>();
                map.put("type", "PUSH_BINDACCOUNT_CALLBACK");
                map.put("errCode", i);
                PushCenterUtil.sendPushGlobalEvent(context, map);
            } else {
                //账号解绑
                Map map = new HashMap<>();
                map.put("type", "PUSH_DELETEACCOUNT_CALLBACK");
                map.put("errCode", i);
                PushCenterUtil.sendPushGlobalEvent(context, map);
            }
        } else {
            Map map = new HashMap<>();
            map.put("type", "PUSH_REGISTER_CALLBACK");
            map.put("errCode", i);
            PushCenterUtil.sendPushGlobalEvent(context, map);
        }
    }

    @Override
    public void onUnregisterResult(Context context, int i) {
        Log.e("TSLXGReceiver", "onUnregisterResult i=" + i);

        Map map = new HashMap<>();
        map.put("type", "PUSH_UNREGISTER_CALLBACK");
        map.put("errCode", i);
        PushCenterUtil.sendPushGlobalEvent(context, map);
    }

    /**
     * @param context
     * @param i       i=0时表示设置成功
     * @param s
     */
    @Override
    public void onSetTagResult(Context context, int i, String s) {
        Log.e("TSLXGReceiver", "onSetTagResult i=" + i + ",s=" + s);

        Map map = new HashMap<>();
        map.put("type", "PUSH_SETTAG_CALLBACK");
        map.put("errCode", i);
        map.put("errMsg", s);
        PushCenterUtil.sendPushGlobalEvent(context, map);
    }

    @Override
    public void onDeleteTagResult(Context context, int i, String s) {
        Log.e("TSLXGReceiver", "onDeleteTagResult i=" + i + ",s=" + s);

        Map map = new HashMap<>();
        map.put("type", "PUSH_DELETETAG_CALLBACK");
        map.put("errCode", i);
        map.put("errMsg", s);
        PushCenterUtil.sendPushGlobalEvent(context, map);
    }

    @Override
    public void onTextMessage(Context context, XGPushTextMessage xgPushTextMessage) {
        Log.e("TSLXGReceiver", "onTextMessage:" + xgPushTextMessage.toString());

        Map map = new HashMap<>();
        map.put("type", "PUSH_RECEIVE_MESSAGE_CALLBACK");
        map.put("title", xgPushTextMessage.getTitle());
        map.put("content", xgPushTextMessage.getContent());
        map.put("customContent", xgPushTextMessage.getCustomContent());
        PushCenterUtil.sendPushGlobalEvent(context, map);

    }

    @Override
    public void onNotifactionClickedResult(Context context, XGPushClickedResult xgPushClickedResult) {
        Log.e("TSLXGReceiver", "onTextMessage:" + xgPushClickedResult.toString());

    }

    @Override
    public void onNotifactionShowedResult(Context context, XGPushShowedResult xgPushShowedResult) {


    }
}
