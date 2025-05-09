package com.ucmed.pushcenterlib;

import android.content.Context;

import java.util.Map;

public interface PushCenterInterface {
    /**
     * 默认初始化步骤：1 获取手机厂商，2 对应sdk初始化
     *
     * @param context
     */
    public void init(Context context, PushResultCallBack callBack);

    public void register(Context context, Map map, PushResultCallBack callback);

    public void unregister(Context context, Map map, PushResultCallBack callback);

    public void bindAccount(Context context, Map map, PushResultCallBack callback);

    public void deleteAccount(Context context, Map map, PushResultCallBack callback);

    public void setTag(Context context, Map map, PushResultCallBack callback);

    public void deleteTag(Context context, Map map, PushResultCallBack callback);

    public void getToken(Context context, PushResultCallBack callback);

    public String getTokenSync(Context context);

    public void getNotificationStatus(Context context, PushResultCallBack callback);

    public void openNotificationSettings(Context context, PushResultCallBack callback);
}
