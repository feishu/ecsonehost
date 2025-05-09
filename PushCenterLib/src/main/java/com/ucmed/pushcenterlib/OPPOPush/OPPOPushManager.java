package com.ucmed.pushcenterlib.OPPOPush;

import com.ucmed.pushcenterlib.PushCenterInterface;
import com.ucmed.pushcenterlib.XgPush.XgPushAdapterIml;

public class OPPOPushManager {

    private static OPPOPushManager ourInstance = new OPPOPushManager();

    private OPPOPushManager() {

    }

    public static OPPOPushManager getInstance() {
        return ourInstance;
    }

    public PushCenterInterface getPushCenterInterface() {
        if(OPPOPushInterface == null)
            OPPOPushInterface = new OPPOPushAdapterIml();
        return OPPOPushInterface;
    }

    public void setPushCenterInterface(PushCenterInterface OPPOPushInterface) {
        this.OPPOPushInterface = OPPOPushInterface;
    }

    PushCenterInterface OPPOPushInterface;

}
