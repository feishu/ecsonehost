package com.ucmed.pushcenterlib.tslpushcenter;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.ucmed.pushcenterlib.PushCenterManager;
import com.ucmed.pushcenterlib.PushResultCallBack;
import com.ucmed.pushcenterlib.TSLNotificationUtil;
import com.weex.weexextra.ModuleAdapterCallBack;

import java.util.HashMap;
import java.util.Map;

public class TSLPushCenterModule extends WXModule {

    /**
     * 初始化
     *
     * @param successCallBack
     * @param errorCallBack
     * @param completeCallBack
     */
    @JSMethod(uiThread = false)
    public void init(JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack) {
        final ModuleAdapterCallBack ModuleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);
        PushCenterManager.getInstance().defaultInit(mWXSDKInstance.getContext(), new PushResultCallBack() {
            @Override
            public void success(Map map) {
                ModuleAdapterCallBack.success(map);
            }

            @Override
            public void failure(Map map) {
                ModuleAdapterCallBack.error(map);
            }
        });
    }

    /**
     * 推送设备注册,
     * OPPO手机需要 OPPO_appKey,OPPO_appSecret
     *
     * @param successCallBack
     * @param errorCallBack   返回错误信息描述
     */
    @JSMethod(uiThread = false)
    public void register(JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack) {
        final ModuleAdapterCallBack ModuleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);
        Map map = new HashMap();
        ApplicationInfo appInfo = null;
        try {
            appInfo = mWXSDKInstance.getContext().getPackageManager().getApplicationInfo(mWXSDKInstance.getContext().getPackageName(), PackageManager.GET_META_DATA);
//            if (PushCenterManager.getInstance().getSDKtype().toLowerCase().equals("oppo")) {
                map.put("OPPO_appKey", appInfo.metaData.getString("OPPO_appKey"));
                map.put("OPPO_appSecret", appInfo.metaData.getString("OPPO_appSecret"));
//            }
//            if(PushCenterManager.getInstance().getSDKtype().toLowerCase().equals("vivo")){
//                map.put("Mi_APP_ID", appInfo.metaData.getString("Mi_APP_ID"));
//                map.put("Mi_APP_KEY", appInfo.metaData.getString("Mi_APP_KEY"));
//            }
//            if(PushCenterManager.getInstance().getManufacturer().toLowerCase().toLowerCase().equals("xiaomi")){
//                map.put("MZ_APP_ID", appInfo.metaData.getString("MZ_APP_ID"));
//                map.put("MZ_APP_KEY", appInfo.metaData.getString("MZ_APP_KEY"));
//            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        PushCenterManager.getInstance().getPushCenterInterface().register(mWXSDKInstance.getContext(), map, new PushResultCallBack() {
            @Override
            public void success(Map map) {
                ModuleAdapterCallBack.success(map);
            }

            @Override
            public void failure(Map map) {
                ModuleAdapterCallBack.error(map);
            }
        });
    }

    /**
     * 注销推送设备注册,
     *
     * @param successCallBack
     * @param errorCallBack   返回错误信息描述
     */
    @JSMethod(uiThread = false)
    public void unregister(JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack) {
        final ModuleAdapterCallBack ModuleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);
        PushCenterManager.getInstance().getPushCenterInterface().unregister(mWXSDKInstance.getContext(), null, new PushResultCallBack() {
            @Override
            public void success(Map map) {
                ModuleAdapterCallBack.success(map);
            }

            @Override
            public void failure(Map map) {
                ModuleAdapterCallBack.error(map);
            }
        });
    }


    /**
     * 推送账号注册,
     *
     * @param jsonObject      account  推送帐号
     * @param successCallBack
     * @param errorCallBack   返回错误信息描述
     */
    @JSMethod(uiThread = false)
    public void bindAccount(JSONObject jsonObject, JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack) {
        final ModuleAdapterCallBack ModuleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);
        Map map = new HashMap();
        map.put("account", jsonObject.getString("account"));
        PushCenterManager.getInstance().getPushCenterInterface().bindAccount(mWXSDKInstance.getContext(), map, new PushResultCallBack() {
            @Override
            public void success(Map map) {
                ModuleAdapterCallBack.success(map);
            }

            @Override
            public void failure(Map map) {
                ModuleAdapterCallBack.error(map);
            }
        });
    }

    /**
     * 注销推送账号注册,
     *
     * @param jsonObject      account  推送帐号
     * @param successCallBack
     * @param errorCallBack   返回错误信息描述
     */
    @JSMethod(uiThread = false)
    public void deleteAccount(JSONObject jsonObject, JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack) {
        final ModuleAdapterCallBack ModuleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);
        Map map = new HashMap();
        map.put("account", jsonObject.getString("account"));
        PushCenterManager.getInstance().getPushCenterInterface().deleteAccount(mWXSDKInstance.getContext(), map, new PushResultCallBack() {
            @Override
            public void success(Map map) {
                ModuleAdapterCallBack.success(map);
            }

            @Override
            public void failure(Map map) {
                ModuleAdapterCallBack.error(map);
            }
        });
    }

    /**
     * 设置标签
     *
     * @param jsonObject      tag  标签
     * @param successCallBack
     * @param errorCallBack   返回错误信息描述
     */
    @JSMethod(uiThread = false)
    public void setTag(JSONObject jsonObject, JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack) {
        final ModuleAdapterCallBack ModuleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);
        Map map = new HashMap();
        map.put("tag", jsonObject.getString("tag"));
        PushCenterManager.getInstance().getPushCenterInterface().setTag(mWXSDKInstance.getContext(), map, new PushResultCallBack() {
            @Override
            public void success(Map map) {
                ModuleAdapterCallBack.success(map);
            }

            @Override
            public void failure(Map map) {
                ModuleAdapterCallBack.error(map);
            }
        });
    }

    /**
     * 删除标签
     *
     * @param jsonObject      tag  标签
     * @param successCallBack
     * @param errorCallBack   返回错误信息描述
     */
    @JSMethod(uiThread = false)
    public void deleteTag(JSONObject jsonObject, JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack) {
        final ModuleAdapterCallBack ModuleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);
        Map map = new HashMap();
        map.put("tag", jsonObject.getString("tag"));
        PushCenterManager.getInstance().getPushCenterInterface().deleteTag(mWXSDKInstance.getContext(), map, new PushResultCallBack() {
            @Override
            public void success(Map map) {
                ModuleAdapterCallBack.success(map);
            }

            @Override
            public void failure(Map map) {
                ModuleAdapterCallBack.error(map);
            }
        });
    }


    /**
     * 推送token,在设备注册/绑定账号调用成功后调用才能获取到
     *
     * @param successCallBack
     * @param errorCallBack   返回错误信息描述
     */
    @JSMethod(uiThread = false)
    public void getToken(JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack) {
        final ModuleAdapterCallBack ModuleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);
        PushCenterManager.getInstance().getPushCenterInterface().getToken(mWXSDKInstance.getContext(), new PushResultCallBack() {
            @Override
            public void success(Map map) {
                ModuleAdapterCallBack.success(map);
            }

            @Override
            public void failure(Map map) {
                ModuleAdapterCallBack.error(map);
            }
        });
    }

    /**
     * 推送token,在设备注册/绑定账号调用成功后调用才能获取
     */
    @JSMethod(uiThread = false)
    public String getTokenSync() {
        return PushCenterManager.getInstance().getPushCenterInterface().getTokenSync(mWXSDKInstance.getContext());
    }


    /**
     * 显示本地通知
     *
     * @param jsonObject
     * @param successCallBack
     * @param errorCallBack   返回错误信息描述
     */
    @JSMethod
    public void showLocalNotification(JSONObject jsonObject, JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack) {
        TSLNotificationUtil.showLocalNotification(mWXSDKInstance.getContext(),
                jsonObject.getString("title"),
                jsonObject.getString("content"),
                jsonObject.getString("customContent"),
                mWXSDKInstance.getContext().getResources().getIdentifier("ic_launcher", "mipmap", mWXSDKInstance.getContext().getPackageName()));
    }

    /**
     * 获取推送SDK的类型, 1:信鸽推送,2:个推推送
     */
    @JSMethod(uiThread = false)
    public String getPushSdkTypeSync() {
        return PushCenterManager.getInstance().getSDKtype();
    }

    /**
     * 通知栏状态
     */
    @JSMethod(uiThread = false)
    public void getNotificationStatus(JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack) {
        final ModuleAdapterCallBack ModuleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);
        PushCenterManager.getInstance().getPushCenterInterface().getNotificationStatus(mWXSDKInstance.getContext(), new PushResultCallBack() {
            @Override
            public void success(Map map) {
                ModuleAdapterCallBack.success(map);
            }

            @Override
            public void failure(Map map) {
                ModuleAdapterCallBack.error(map);
            }
        });
    }

    /**
     * 打开通知栏设置 OPPO有效
     *
     * @param successCallBack
     * @param errorCallBack
     * @param completeCallBack
     */
    public void openNotificationSettings(JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack) {
        final ModuleAdapterCallBack ModuleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);
        PushCenterManager.getInstance().getPushCenterInterface().openNotificationSettings(mWXSDKInstance.getContext(), new PushResultCallBack() {
            @Override
            public void success(Map map) {
                ModuleAdapterCallBack.success(map);
            }

            @Override
            public void failure(Map map) {
                ModuleAdapterCallBack.error(map);
            }
        });
    }
}
