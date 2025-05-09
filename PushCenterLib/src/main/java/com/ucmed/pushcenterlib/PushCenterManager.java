package com.ucmed.pushcenterlib;

import android.content.Context;
import android.os.Build;

import com.coloros.mcssdk.PushManager;
import com.ucmed.pushcenterlib.OPPOPush.OPPOPushAdapterIml;
import com.ucmed.pushcenterlib.OPPOPush.OPPOPushManager;
import com.ucmed.pushcenterlib.XgPush.XgPushAdapterIml;
import com.ucmed.pushcenterlib.XgPush.XgPushManager;

public class PushCenterManager {

    private static PushCenterManager ourInstance = new PushCenterManager();

    private PushCenterManager() {

    }

    public static PushCenterManager getInstance() {
        return ourInstance;
    }

    public String getSDKtype() {
        return SDKtype;
    }

    public void setSDKtype(String SDKtype) {
        this.SDKtype = SDKtype;
    }

    private String SDKtype = "";//最终所使用的sdk

    public boolean isDebug = false;

    private PushCenterInterface IpushCenter;

//    public static boolean forceXinge = false;

    /**
     * 默认初始化步骤：1 获取手机厂商，2 对应sdk初始化
     *
     * @param context
     */
    public void defaultInit(Context context, PushResultCallBack callBack) {
        String Manufacturer = getManufacturer();
        SDKtype = "xinge";
        if (Manufacturer.toLowerCase().equals("oppo") && PushManager.isSupportPush(context) && isHaveOPPO()) {
            SDKtype = "OPPO";
            OPPOPushManager.getInstance().getPushCenterInterface().init(context, callBack);
            setPushCenterInterface(new OPPOPushAdapterIml());
            return;
        }

        if (Manufacturer.toLowerCase().equals("vivo") && isHaveVIVO()) {
            SDKtype = "VIVO";
            return;
        }

        //非vivo oppo手机 或 手机不支持自家厂商推送通道 只能使用信鸽推送
        XgPushManager.getInstance().getPushCenterInterface().init(context, callBack);
        setPushCenterInterface(new XgPushAdapterIml());
    }

    private boolean haveOPPO = false;

    public boolean isHaveOPPO() {
        return haveOPPO;
    }

    public void setHaveOPPO(boolean haveOPPO) {
        this.haveOPPO = haveOPPO;
    }

    public boolean isHaveVIVO() {
        return haveVIVO;
    }

    public void setHaveVIVO(boolean haveVIVO) {
        this.haveVIVO = haveVIVO;
    }

    private boolean haveVIVO = false;

    private boolean haveMi = false;
    private boolean haveMz = false;

    public boolean isHaveMi() {
        return haveMi;
    }

    public void setHaveMi(boolean haveMi) {
        this.haveMi = haveMi;
    }

    public boolean isHaveMz() {
        return haveMz;
    }

    public void setHaveMz(boolean haveMz) {
        this.haveMz = haveMz;
    }

    public boolean isHaveHW() {
        return haveHW;
    }

    public void setHaveHW(boolean haveHW) {
        this.haveHW = haveHW;
    }

    private boolean haveHW = false;


    public String getManufacturer() {
        return Build.MANUFACTURER;
    }

//    public static void register(Context context, Map params, PushResultCallBack callBack) {
//        if (isOPPO()) {
//            //OPPO的appkey appSecret可直接传入 String类型
//            OPPOPushManager.getInstance().getPushCenterInterface().register(context, params, callBack);
//        }
//
//        //其他手机
//        XgPushManager.getInstance().getPushCenterInterface().register(context, params, callBack);
//    }
//
//    public static void unregister(Context context, PushResultCallBack callBack) {
//        if (isOPPO()) {
//            OPPOPushManager.getInstance().getPushCenterInterface().unregister(context, null, callBack);
//        }
//
//        XgPushManager.getInstance().getPushCenterInterface().unregister(context, null, callBack);
//    }
//
//
//    public static void bindAccount(Context context, Map params, final PushResultCallBack callBack) {
//        if (isOPPO()) {
//            OPPOPushManager.getInstance().getPushCenterInterface().bindAccount(context, params, callBack);
//        }
//
//        XgPushManager.getInstance().getPushCenterInterface().bindAccount(context, params, callBack);
//    }
//
//
//    public static void deleteAccount(Context context, Map params, final PushResultCallBack callBack) {
//        if (isOPPO()) {
//            OPPOPushManager.getInstance().getPushCenterInterface().deleteAccount(context, params, callBack);
//        }
//
//        XgPushManager.getInstance().getPushCenterInterface().deleteAccount(context, params, callBack);
//    }
//
//    public static void setTag(Context context, Map params, PushResultCallBack callBack) {
//        if (isOPPO()) {
//            OPPOPushManager.getInstance().getPushCenterInterface().setTag(context, params, callBack);
//        }
//
//        XgPushManager.getInstance().getPushCenterInterface().setTag(context, params, callBack);
//    }
//
//    public static void deleteTag(Context context, Map params, PushResultCallBack callBack) {
//        if (isOPPO()) {
//            OPPOPushManager.getInstance().getPushCenterInterface().deleteTag(context, params, callBack);
//        }
//
//        XgPushManager.getInstance().getPushCenterInterface().deleteTag(context, params, callBack);
//    }
//
//    public static void getToken(Context context, PushResultCallBack callBack) {
//        if (isOPPO()) {
//            OPPOPushManager.getInstance().getPushCenterInterface().getToken(context, callBack);
//        }
//
//        XgPushManager.getInstance().getPushCenterInterface().getToken(context,callBack);
//    }
//
//    public static String getTokenSync(Context context) {
//        if(isOPPO()){
//            return OPPOPushManager.getInstance().getPushCenterInterface().getTokenSync(context);
//        }
//
//        return XgPushManager.getInstance().getPushCenterInterface().getTokenSync(context);
//    }
//
//    public static void getNotificationStatus(Context context, PushResultCallBack callback){
//        if (isOPPO()){
//            OPPOPushManager.getInstance().getPushCenterInterface().getNotificationStatus(context,callback);
//        }
//
//        XgPushManager.getInstance().getPushCenterInterface().getNotificationStatus(context,callback);
//    }
//
//    public static void openNotificationSettings(Context context, PushResultCallBack callback){
//        if (isOPPO()){
//            OPPOPushManager.getInstance().getPushCenterInterface().openNotificationSettings(context,callback);
//        }
//
//        XgPushManager.getInstance().getPushCenterInterface().openNotificationSettings(context,callback);
//    }

    public PushCenterInterface getPushCenterInterface() {
        if (IpushCenter == null)
            IpushCenter = new XgPushAdapterIml();
        return IpushCenter;
    }

    public void setPushCenterInterface(PushCenterInterface pushCenterInterface) {
        IpushCenter = pushCenterInterface;
    }
}
