//package com.ucmed.pushcenterlib.tslpushcenter;
//
//import android.text.TextUtils;
//
//import com.alibaba.fastjson.JSONObject;
//import com.taobao.weex.WXSDKInstance;
//import com.tencent.android.tpush.XGIOperateCallback;
//import com.ucmed.pushcenterlib.XgPush.XGPushUtil;
//
//import java.util.HashMap;
//
///**
// * Created by liaochengzong on 2018/4/12.
// */
//
//public class TSLXgPushAdapterIml extends TSLAdapterIml implements TSLPushAdapterInterface {
//
//    @Override
//    public void register(WXSDKInstance mWXSDKInstance, JSONObject jsonObject, final TSLModuleAdapterCallBack tslModuleAdapterCallBack) {
//        XGPushUtil.registerHWPush(mWXSDKInstance.getContext(), new XGIOperateCallback() {
//            @Override
//            public void onSuccess(Object o, int i) {
//                tslModuleAdapterCallBack.success("register:ok");
//            }
//
//            @Override
//            public void onFail(Object o, int i, String s) {
//                tslModuleAdapterCallBack.error("register:fail errorCode=" + i + "," + s);
//            }
//        });
//
//    }
//
//    @Override
//    public void unregister(WXSDKInstance mWXSDKInstance, JSONObject jsonObject, final TSLModuleAdapterCallBack tslModuleAdapterCallBack) {
//        XGPushUtil.unregisterPush(mWXSDKInstance.getContext(), new XGIOperateCallback() {
//            @Override
//            public void onSuccess(Object o, int i) {
//                tslModuleAdapterCallBack.success("unregister:ok");
//            }
//
//            @Override
//            public void onFail(Object o, int i, String s) {
//                tslModuleAdapterCallBack.error("unregister:fail errorCode=" + i + "," + s);
//            }
//        });
//    }
//
//    @Override
//    public void bindAccount(WXSDKInstance mWXSDKInstance, JSONObject jsonObject, final TSLModuleAdapterCallBack tslModuleAdapterCallBack) {
//
//        String account = (String) initParam(jsonObject, "bindAccount", "account", tslModuleAdapterCallBack);
//        if (account == null) return;
//
//        XGPushUtil.bindAccount(mWXSDKInstance.getContext(), account, new XGIOperateCallback() {
//            @Override
//            public void onSuccess(Object o, int i) {
//                tslModuleAdapterCallBack.success("bindAccount:ok");
//            }
//
//            @Override
//            public void onFail(Object o, int i, String s) {
//                tslModuleAdapterCallBack.success("bindAccount:fail errorCode=" + i + "," + s);
//            }
//        });
//
//
//    }
//
//    @Override
//    public void deleteAccount(WXSDKInstance mWXSDKInstance, JSONObject jsonObject, final TSLModuleAdapterCallBack tslModuleAdapterCallBack) {
//        String account = (String) initParam(jsonObject, "deleteAccount", "account", tslModuleAdapterCallBack);
//        if (account == null) return;
//
//        XGPushUtil.deleteAccount(mWXSDKInstance.getContext(), account, new XGIOperateCallback() {
//            @Override
//            public void onSuccess(Object o, int i) {
//                tslModuleAdapterCallBack.success("deleteAccount:ok");
//            }
//
//            @Override
//            public void onFail(Object o, int i, String s) {
//                tslModuleAdapterCallBack.success("deleteAccount:fail errorCode=" + i + "," + s);
//            }
//        });
//    }
//
//    @Override
//    public void setTag(WXSDKInstance mWXSDKInstance, JSONObject jsonObject, TSLModuleAdapterCallBack tslModuleAdapterCallBack) {
//        String tag = (String) initParam(jsonObject, "setTag", "tag", tslModuleAdapterCallBack);
//        if (tag == null) return;
//        XGPushUtil.setTag(mWXSDKInstance.getContext(), tag);
//        tslModuleAdapterCallBack.success("setTag:ok");
//    }
//
//    @Override
//    public void deleteTag(WXSDKInstance mWXSDKInstance, JSONObject jsonObject, TSLModuleAdapterCallBack tslModuleAdapterCallBack) {
//        String tag = (String) initParam(jsonObject, "deleteTag", "tag", tslModuleAdapterCallBack);
//        if (tag == null) return;
//        XGPushUtil.deleteTag(mWXSDKInstance.getContext(), tag);
//        tslModuleAdapterCallBack.success("deleteTag:ok");
//    }
//
//    @Override
//    public void getToken(WXSDKInstance mWXSDKInstance, JSONObject jsonObject, TSLModuleAdapterCallBack tslModuleAdapterCallBack) {
//        String token = XGPushUtil.getToken(mWXSDKInstance.getContext());
//        if (TextUtils.isEmpty(token)) {
//            tslModuleAdapterCallBack.error("getToken:fail token is null ");
//        }
//        HashMap map = new HashMap();
//        map.put("token", token);
//        map.put(TSLGlobal.ERRMSG, "getToken:ok");
//        tslModuleAdapterCallBack.success(map);
//    }
//
//    @Override
//    public String getTokenSync(WXSDKInstance mWXSDKInstance) {
//        return XGPushUtil.getToken(mWXSDKInstance.getContext());
//    }
//
//    @Override
//    public void showLocalNotification(WXSDKInstance mWXSDKInstance, JSONObject jsonObject, TSLModuleAdapterCallBack tslModuleAdapterCallBack) {
//        String title = (String) initParam(jsonObject, "showLocalNotification", "title", tslModuleAdapterCallBack);
//        if (title == null) return;
//        String content = (String) initParam(jsonObject, "showLocalNotification", "content", tslModuleAdapterCallBack);
//        if (content == null) return;
//        String customContent = jsonObject.getString("customContent");
//
//        boolean result = XGPushUtil.showLocalNotification(mWXSDKInstance.getContext(), title, content, customContent);
//        if (result) {
//            tslModuleAdapterCallBack.success("showLocalNotification:ok");
//        } else {
//            tslModuleAdapterCallBack.success("showLocalNotification:fail");
//        }
//    }
//
//
//    @Override
//    public int getPushSdkTypeSync(WXSDKInstance mWXSDKInstance) {
//        return 1;
//    }
//}
//
