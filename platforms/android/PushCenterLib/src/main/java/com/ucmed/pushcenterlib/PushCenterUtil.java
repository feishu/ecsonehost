package com.ucmed.pushcenterlib;

import android.content.Context;
import android.content.Intent;

import com.coloros.mcssdk.PushManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;

public class PushCenterUtil {
    public static final String ACTION = "tsl2-push";
//    public static String EVENT_NAME = "";

    public static void sendPushGlobalEvent(Context context, Map info) {
        Intent intent = new Intent("wx_global_action");
        intent.putExtra("eventName", context.getPackageName()+ACTION);
        intent.putExtra("eventParams", map2JsonObject(info).toString());
        context.sendBroadcast(intent);
    }

    public static JSONObject map2JsonObject(Map map) {
        JSONObject jsonObject = new JSONObject();

        Iterator<Map.Entry> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = it.next();
            try {
                jsonObject.put(String.valueOf(entry.getKey()), entry.getValue());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return jsonObject;
    }

    public static void invokeCallback(PushResultCallBack resultCallBack){

    }
}
