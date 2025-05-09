package com.ucmed.pushcenterlib.OPPOPush;

import android.content.Context;

import com.coloros.mcssdk.PushManager;
import com.coloros.mcssdk.callback.PushCallback;
import com.coloros.mcssdk.mode.SubscribeResult;
import com.ucmed.pushcenterlib.PushAdapterIml;
import com.ucmed.pushcenterlib.PushCenterInterface;
import com.ucmed.pushcenterlib.PushResultCallBack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OPPOPushAdapterIml extends PushAdapterIml implements PushCenterInterface, PushCallback {

    public OPPOPushAdapterIml() {
        super("OPPO");
    }

    @Override
    public void init(Context context, PushResultCallBack callBack) {
        OPPOPushCallbackManager.getInstance().setInitCallback(callBack);
        PushManager.getInstance().setPushCallback(this);
        Map res = new HashMap();
        res.put("SDKtype", "OPPO");
        res.put("SDKVersion",PushManager.getInstance().getSDKVersion());
        invokeResult(true, res, callBack);
    }

    @Override
    public void register(Context context, Map map, PushResultCallBack callback) {
        if (!initParam(map, "OPPO_appKey", callback)) return;
        if (!initParam(map, "OPPO_appSecret", callback)) return;
        OPPOPushCallbackManager.getInstance().setRegisterCallback(callback);
        PushManager.getInstance().register(context, map.get("OPPO_appKey").toString(), map.get("OPPO_appSecret").toString(), this);
    }

    @Override
    public void unregister(Context context, Map map, PushResultCallBack callback) {
        OPPOPushCallbackManager.getInstance().setUnRegisterCallback(callback);
        PushManager.getInstance().unRegister();
    }

    @Override
    public void bindAccount(Context context, Map map, PushResultCallBack callback) {
        if (!initParam(map, "account", callback)) return;
        OPPOPushCallbackManager.getInstance().setSetUserAccountsCallback(callback);
        PushManager.getInstance().setUserAccount(map.get("account").toString());
    }

    @Override
    public void deleteAccount(Context context, Map map, PushResultCallBack callback) {
        if (!initParam(map, "account", callback)) return;
        OPPOPushCallbackManager.getInstance().setUnsetUserAccountsCallback(callback);
        PushManager.getInstance().unsetUserAccounts(Arrays.asList(new String[]{map.get("account").toString()}));
    }

    @Override
    public void setTag(Context context, Map map, PushResultCallBack callback) {
        if (!initParam(map, "tag", callback)) return;
        OPPOPushCallbackManager.getInstance().setSetTagsCallback(callback);
        PushManager.getInstance().setTags(Arrays.asList(new String[]{map.get("tag").toString()}));
    }

    @Override
    public void deleteTag(Context context, Map map, PushResultCallBack callback) {
        if (!initParam(map, "tag", callback)) return;
        OPPOPushCallbackManager.getInstance().setUnsetTagsCallback(callback);
        PushManager.getInstance().unsetTags(Arrays.asList(new String[]{map.get("tag").toString()}));
    }

    @Override
    public void getToken(Context context, PushResultCallBack callback) {
        OPPOPushCallbackManager.getInstance().setGetRegisterCallback(callback);
        PushManager.getInstance().getRegister();
    }

    @Override
    public String getTokenSync(Context context) {
        return null;
    }

    @Override
    public void getNotificationStatus(Context context, PushResultCallBack callback) {
        OPPOPushCallbackManager.getInstance().setGetNotificationStatusCallback(callback);
        PushManager.getInstance().getNotificationStatus();
    }

    @Override
    public void openNotificationSettings(Context context, PushResultCallBack callback) {
        PushManager.getInstance().openNotificationSettings();
        invokeResult(true,null,callback);
    }

    @Override
    public void onRegister(int code, String s) {
        Map map = new HashMap();
        map.put("code", code);
        map.put("PushVersionCode",PushManager.getInstance().getPushVersionCode());
        map.put("PushVersionName",PushManager.getInstance().getPushVersionName());
        if (code == 0) {
            map.put("token", s);
            invokeResult(true, map, OPPOPushCallbackManager.getInstance().getRegisterCallback());
            invokeResult(true, map, OPPOPushCallbackManager.getInstance().getGetRegisterCallback());
        } else {
            map.put("msg", s);
            invokeResult(false, map, OPPOPushCallbackManager.getInstance().getRegisterCallback());
            invokeResult(false, map, OPPOPushCallbackManager.getInstance().getGetRegisterCallback());
        }
//        if (OPPOPushCallbackManager.getInstance().getRegisterCallback() != null)
//            if (code == 0) {
//                map.put("token", s);
//                OPPOPushCallbackManager.getInstance().getRegisterCallback().success(map);
//            } else {
//                map.put("msg", s);
//                OPPOPushCallbackManager.getInstance().getRegisterCallback().failure(map);
//            }
//        if (OPPOPushCallbackManager.getInstance().getGetRegisterCallback() != null)
//            if (code == 0) {
//                map.put("token", s);
//                OPPOPushCallbackManager.getInstance().getGetRegisterCallback().success(map);
//
//            } else {
//                map.put("msg", s);
//                OPPOPushCallbackManager.getInstance().getGetRegisterCallback().failure(map);
//            }
    }

    @Override
    public void onUnRegister(int code) {
        Map map = new HashMap();
        map.put("code", code);
        invokeResult(code == 0, map, OPPOPushCallbackManager.getInstance().getUnRegisterCallback());
    }

    @Override
    public void onGetAliases(int code, List<SubscribeResult> list) {
        Map map = new HashMap();
        map.put("code", code);
        map.put("aliases", Arrays.toString(list.toArray()));
        invokeResult(code == 0, map, OPPOPushCallbackManager.getInstance().getGetAliasesCallback());
    }

    @Override
    public void onSetAliases(int code, List<SubscribeResult> list) {
        Map map = new HashMap();
        map.put("code", code);
        map.put("aliases", Arrays.toString(list.toArray()));
        invokeResult(code == 0, map, OPPOPushCallbackManager.getInstance().getSetAliasesCallback());
    }

    @Override
    public void onUnsetAliases(int code, List<SubscribeResult> list) {
        Map map = new HashMap();
        map.put("code", code);
        map.put("aliases", Arrays.toString(list.toArray()));
        invokeResult(code == 0, map, OPPOPushCallbackManager.getInstance().getUnsetAliasesCallback());
    }

    @Override
    public void onSetUserAccounts(int code, List<SubscribeResult> list) {
        Map map = new HashMap();
        map.put("code", code);
        map.put("accounts", Arrays.toString(list.toArray()));
        invokeResult(code == 0, map, OPPOPushCallbackManager.getInstance().getSetUserAccountsCallback());
    }

    @Override
    public void onUnsetUserAccounts(int code, List<SubscribeResult> list) {
        Map map = new HashMap();
        map.put("code", code);
        map.put("accounts", Arrays.toString(list.toArray()));
        invokeResult(code == 0, map, OPPOPushCallbackManager.getInstance().getUnsetUserAccountsCallback());
    }

    @Override
    public void onGetUserAccounts(int code, List<SubscribeResult> list) {
        Map map = new HashMap();
        map.put("code", code);
        map.put("accounts", Arrays.toString(list.toArray()));
        invokeResult(code == 0, map, OPPOPushCallbackManager.getInstance().getGetUserAccountsCallback());
    }

    @Override
    public void onSetTags(int code, List<SubscribeResult> list) {
        Map map = new HashMap();
        map.put("code", code);
        map.put("tags", Arrays.toString(list.toArray()));
        invokeResult(code == 0, map, OPPOPushCallbackManager.getInstance().getSetTagsCallback());
    }

    @Override
    public void onUnsetTags(int code, List<SubscribeResult> list) {
        Map map = new HashMap();
        map.put("code", code);
        map.put("tags", Arrays.toString(list.toArray()));
        invokeResult(code == 0, map, OPPOPushCallbackManager.getInstance().getUnsetTagsCallback());
    }

    @Override
    public void onGetTags(int code, List<SubscribeResult> list) {
        Map map = new HashMap();
        map.put("code", code);
        map.put("tags", Arrays.toString(list.toArray()));
        invokeResult(code == 0, map, OPPOPushCallbackManager.getInstance().getGetTagsCallback());
    }

    @Override
    public void onGetPushStatus(int code, int status) {
        Map map = new HashMap();
        map.put("code", code);
        map.put("status", status);
        invokeResult(code == 0, map, OPPOPushCallbackManager.getInstance().getGetPushStatusCallback());
    }

    @Override
    public void onSetPushTime(int code, String s) {
        Map map = new HashMap();
        map.put("code", code);
        map.put("time", s);
        invokeResult(code == 0, map, OPPOPushCallbackManager.getInstance().getSetPushTimeCallback());
    }

    @Override
    public void onGetNotificationStatus(int code, int status) {
        Map map = new HashMap();
        map.put("code", code);
        map.put("status", status == 0);
        invokeResult(code == 0, map, OPPOPushCallbackManager.getInstance().getGetNotificationStatusCallback());
    }
}