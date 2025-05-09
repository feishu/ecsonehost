package com.ucmed.pushcenterlib.XgPush;

import android.content.Context;

import com.ucmed.pushcenterlib.PushCenterInterface;
import com.ucmed.pushcenterlib.PushResultCallBack;

import java.util.Map;

public class XgPushManager{

    private static XgPushManager ourInstance = new XgPushManager();

    private XgPushManager() {

    }

    public static XgPushManager getInstance() {
        return ourInstance;
    }

    public PushCenterInterface getPushCenterInterface() {
        if(pushCenterInterface == null)
            pushCenterInterface = new XgPushAdapterIml();
        return pushCenterInterface;
    }

    public void setPushCenterInterface(PushCenterInterface pushCenterInterface) {
        this.pushCenterInterface = pushCenterInterface;
    }

    PushCenterInterface pushCenterInterface;


}
