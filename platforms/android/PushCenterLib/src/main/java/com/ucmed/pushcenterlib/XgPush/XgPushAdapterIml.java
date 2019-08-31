package com.ucmed.pushcenterlib.XgPush;

import android.content.Context;
import android.text.TextUtils;

import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.ucmed.pushcenterlib.PushAdapterIml;
import com.ucmed.pushcenterlib.PushCenterInterface;
import com.ucmed.pushcenterlib.PushCenterManager;
import com.ucmed.pushcenterlib.PushResultCallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liaochengzong on 2018/4/12.
 */

public class XgPushAdapterIml extends PushAdapterIml implements PushCenterInterface {

    public XgPushAdapterIml() {
        super("xinge");
    }

    @Override
    public void init(Context context, PushResultCallBack callBack) {
        XGPushUtil.init(context, context.getResources().getIdentifier("ic_launcher", "mipmap", context.getPackageName()));
        invokeResult(true, null, callBack);
    }

    @Override
    public void register(Context context, Map params, final PushResultCallBack callBack) {
//        if (PushCenterManager.forceXinge) {
//            XGPushUtil.registerPush(context, new XGIOperateCallback() {
//                @Override
//                public void onSuccess(Object o, int i) {
//                    Map map = new HashMap();
//                    map.put("code", i);
//                    map.put("token", o.toString());
//                    invokeResult(true, map, callBack);
//                }
//
//                @Override
//                public void onFail(Object o, int i, String s) {
//                    Map map = new HashMap();
//                    map.put("code", i);
//                    map.put("token", o.toString());
//                    map.put("msg", s);
//                    invokeResult(false, map, callBack);
//                }
//            });
//        } else {
            if (PushCenterManager.getInstance().getManufacturer().toLowerCase().equals("huawei")&&PushCenterManager.getInstance().isHaveHW()) {
                XGPushUtil.registerHWPush(context, new XGIOperateCallback() {
                    @Override
                    public void onSuccess(Object o, int i) {
                        Map map = new HashMap();
                        map.put("code", i);
                        map.put("token", o.toString());
                        invokeResult(true, map, callBack);
                    }

                    @Override
                    public void onFail(Object o, int i, String s) {
                        Map map = new HashMap();
                        map.put("code", i);
                        map.put("token", o.toString());
                        map.put("msg", s);
                        invokeResult(false, map, callBack);
                    }
                });
                return;
            }
            if (PushCenterManager.getInstance().getManufacturer().toLowerCase().equals("xiaomi")&&PushCenterManager.getInstance().isHaveMi()) {
                XGPushUtil.registerMiPush(context, params.get("Mi_APP_ID").toString(), params.get("Mi_APP_KEY").toString(), new XGIOperateCallback() {
                    @Override
                    public void onSuccess(Object o, int i) {
                        Map map = new HashMap();
                        map.put("code", i);
                        map.put("token", o.toString());
                        invokeResult(true, map, callBack);
                    }

                    @Override
                    public void onFail(Object o, int i, String s) {
                        Map map = new HashMap();
                        map.put("code", i);
                        map.put("token", o.toString());
                        map.put("msg", s);
                        invokeResult(false, map, callBack);
                    }
                });
                return;
            }
            if (PushCenterManager.getInstance().getManufacturer().toLowerCase().equals("meizu")&&PushCenterManager.getInstance().isHaveMz()) {
                XGPushUtil.registerMzPush(context, params.get("MZ_APP_ID").toString(), params.get("MZ_APP_KEY").toString(), new XGIOperateCallback() {
                    @Override
                    public void onSuccess(Object o, int i) {
                        Map map = new HashMap();
                        map.put("code", i);
                        map.put("token", o.toString());
                        invokeResult(true, map, callBack);
                    }

                    @Override
                    public void onFail(Object o, int i, String s) {
                        Map map = new HashMap();
                        map.put("code", i);
                        map.put("token", o.toString());
                        map.put("msg", s);
                        invokeResult(false, map, callBack);
                    }
                });
                return;
            }
            XGPushUtil.registerPush(context, new XGIOperateCallback() {
                @Override
                public void onSuccess(Object o, int i) {
                    Map map = new HashMap();
                    map.put("code", i);
                    map.put("token", o.toString());
                    invokeResult(true, map, callBack);
                }

                @Override
                public void onFail(Object o, int i, String s) {
                    Map map = new HashMap();
                    map.put("code", i);
                    map.put("token", o.toString());
                    map.put("msg", s);
                    invokeResult(false, map, callBack);
                }
            });
//        }
    }

    @Override
    public void unregister(Context context, Map params, final PushResultCallBack callBack) {
        XGPushUtil.unregisterPush(context, new XGIOperateCallback() {
            @Override
            public void onSuccess(Object o, int i) {
                Map map = new HashMap();
                map.put("code", i);
                map.put("token", o.toString());
                invokeResult(true, map, callBack);
            }

            @Override
            public void onFail(Object o, int i, String s) {
                Map map = new HashMap();
                map.put("code", i);
                map.put("token", o.toString());
                map.put("msg", s);
                invokeResult(false, map, callBack);
            }
        });
    }

    @Override
    public void bindAccount(Context context, Map params, final PushResultCallBack callBack) {
        if (!initParam(params, "account", callBack)) return;
        XGPushUtil.bindAccount(context, params.get("account").toString(), new XGIOperateCallback() {
            @Override
            public void onSuccess(Object o, int i) {
                Map map = new HashMap();
                map.put("code", i);
                map.put("account", o.toString());
                invokeResult(true, map, callBack);
            }

            @Override
            public void onFail(Object o, int i, String s) {
                Map map = new HashMap();
                map.put("code", i);
                map.put("account", o.toString());
                map.put("msg", s);
                invokeResult(false, map, callBack);
            }
        });


    }

    @Override
    public void deleteAccount(Context context, Map params, final PushResultCallBack callBack) {
        if (!initParam(params, "account", callBack)) return;
        XGPushUtil.deleteAccount(context, params.get("account").toString(), new XGIOperateCallback() {
            @Override
            public void onSuccess(Object o, int i) {
                Map map = new HashMap();
                map.put("code", i);
                map.put("account", o.toString());
                invokeResult(true, map, callBack);
            }

            @Override
            public void onFail(Object o, int i, String s) {
                Map map = new HashMap();
                map.put("code", i);
                map.put("account", o.toString());
                map.put("msg", s);
                invokeResult(false, map, callBack);
            }
        });
    }

    @Override
    public void setTag(Context context, Map params, PushResultCallBack callBack) {
        if (!initParam(params, "tag", callBack)) return;
        XGPushUtil.setTag(context, params.get("tag").toString());
        invokeResult(true, null, callBack);
    }

    @Override
    public void deleteTag(Context context, Map params, PushResultCallBack callBack) {
        if (!initParam(params, "tag", callBack)) return;
        XGPushUtil.deleteTag(context, params.get("tag").toString());
        invokeResult(true, null, callBack);
    }

    @Override
    public void getToken(Context context, PushResultCallBack callback) {
        String token = XGPushUtil.getToken(context);
        Map map = new HashMap();
        map.put("token", token);
        invokeResult(!TextUtils.isEmpty(token), map, callback);
    }

    @Override
    public String getTokenSync(Context context) {
        return XGPushUtil.getToken(context);
    }

    @Override
    public void getNotificationStatus(Context context, PushResultCallBack callback) {
        invokeResult(XGPushConfig.isNotificationShowEnable(context), null, callback);
    }

    @Override
    public void openNotificationSettings(Context context, PushResultCallBack callback) {
        XGPushConfig.setNotificationShowEnable(context, true);
        invokeResult(true, null, callback);
    }

//    @Override
//    public void showLocalNotification(Context context, Map params,  PushResultCallBack callBack) {
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


//    @Override
//    public int getPushSdkTypeSync(Context context) {
//        return 1;
//    }
}
