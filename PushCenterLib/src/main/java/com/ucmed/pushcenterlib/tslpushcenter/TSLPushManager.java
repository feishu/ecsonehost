package com.ucmed.pushcenterlib.tslpushcenter;//package com.ucmed.tslpushcenter;
//
//import android.content.Context;
//import android.os.Build;
//
//import com.coloros.mcssdk.PushManager;
//import com.ucmed.pushcenterlib.OPPOPush.OPPOPushAdapterIml;
//import com.ucmed.pushcenterlib.OPPOPush.OPPOPushManager;
//import com.ucmed.pushcenterlib.PushCenterInterface;
//import com.ucmed.pushcenterlib.PushCenterManager;
//import com.ucmed.pushcenterlib.PushResultCallBack;
//import com.ucmed.pushcenterlib.XgPush.XgPushAdapterIml;
//import com.ucmed.pushcenterlib.XgPush.XgPushManager;
//
//public class TSLPushManager {
//    public static String SDKtype = "";
//
//    private static PushCenterInterface IpushCenter;
//
//    private static TSLPushManager ourInstance = new TSLPushManager();
//
//    private TSLPushManager() {
//
//    }
//
//    public static TSLPushManager getInstance() {
//        return ourInstance;
//    }
//    /**
//     * 默认初始化步骤：1 获取手机厂商，2 对应sdk初始化
//     *
//     * @param context
//     */
//    public void init(Context context, PushResultCallBack callBack) {
//        //非vivo oppo手机 或 手机不支持自家厂商推送通道 只能使用信鸽推送
//        setPushCenterInterface(new XgPushAdapterIml());
//        XgPushManager.getInstance().getPushCenterInterface().init(context, callBack);
//        SDKtype = "xinge";
//        //如果强制使用信鸽
//        if(PushCenterManager.getInstance().forceXinge) return;
//
//        String Manufacturer = getManufacturer();
//        if (Manufacturer.toLowerCase().equals("oppo") && PushManager.isSupportPush(context)) {
//            SDKtype = "OPPO";
//            setPushCenterInterface(new OPPOPushAdapterIml());
//            OPPOPushManager.getInstance().getPushCenterInterface().init(context, callBack);
//            return;
//        }
//
//
//    }
//
////    public boolean isOPPO() {
////        return SDKtype.equals("OPPO");
////    }
////
////    public boolean isVivo() {
////        return SDKtype.equals("VIVO");
////    }
//
//    public String getManufacturer() {
//        return Build.MANUFACTURER;
//    }
//
////    public static void register(Context context, Map params, PushResultCallBack callBack) {
////        if (isOPPO()) {
////            //OPPO的appkey appSecret可直接传入 String类型
////            OPPOPushManager.getInstance().getPushCenterInterface().register(context, params, callBack);
////        }
////
////        //其他手机
////        XgPushManager.getInstance().getPushCenterInterface().register(context, params, callBack);
////    }
////
////    public static void unregister(Context context, PushResultCallBack callBack) {
////        if (isOPPO()) {
////            OPPOPushManager.getInstance().getPushCenterInterface().unregister(context, null, callBack);
////        }
////
////        XgPushManager.getInstance().getPushCenterInterface().unregister(context, null, callBack);
////    }
////
////
////    public static void bindAccount(Context context, Map params, final PushResultCallBack callBack) {
////        if (isOPPO()) {
////            OPPOPushManager.getInstance().getPushCenterInterface().bindAccount(context, params, callBack);
////        }
////
////        XgPushManager.getInstance().getPushCenterInterface().bindAccount(context, params, callBack);
////    }
////
////
////    public static void deleteAccount(Context context, Map params, final PushResultCallBack callBack) {
////        if (isOPPO()) {
////            OPPOPushManager.getInstance().getPushCenterInterface().deleteAccount(context, params, callBack);
////        }
////
////        XgPushManager.getInstance().getPushCenterInterface().deleteAccount(context, params, callBack);
////    }
////
////    public static void setTag(Context context, Map params, PushResultCallBack callBack) {
////        if (isOPPO()) {
////            OPPOPushManager.getInstance().getPushCenterInterface().setTag(context, params, callBack);
////        }
////
////        XgPushManager.getInstance().getPushCenterInterface().setTag(context, params, callBack);
////    }
////
////    public static void deleteTag(Context context, Map params, PushResultCallBack callBack) {
////        if (isOPPO()) {
////            OPPOPushManager.getInstance().getPushCenterInterface().deleteTag(context, params, callBack);
////        }
////
////        XgPushManager.getInstance().getPushCenterInterface().deleteTag(context, params, callBack);
////    }
////
////    public static void getToken(Context context, PushResultCallBack callBack) {
////        if (isOPPO()) {
////            OPPOPushManager.getInstance().getPushCenterInterface().getToken(context, callBack);
////        }
////
////        XgPushManager.getInstance().getPushCenterInterface().getToken(context,callBack);
////    }
////
////    public static String getTokenSync(Context context) {
////        if(isOPPO()){
////            return OPPOPushManager.getInstance().getPushCenterInterface().getTokenSync(context);
////        }
////
////        return XgPushManager.getInstance().getPushCenterInterface().getTokenSync(context);
////    }
////
////    public static void getNotificationStatus(Context context, PushResultCallBack callback){
////        if (isOPPO()){
////            OPPOPushManager.getInstance().getPushCenterInterface().getNotificationStatus(context,callback);
////        }
////
////        XgPushManager.getInstance().getPushCenterInterface().getNotificationStatus(context,callback);
////    }
////
////    public static void openNotificationSettings(Context context, PushResultCallBack callback){
////        if (isOPPO()){
////            OPPOPushManager.getInstance().getPushCenterInterface().openNotificationSettings(context,callback);
////        }
////
////        XgPushManager.getInstance().getPushCenterInterface().openNotificationSettings(context,callback);
////    }
//
//    public PushCenterInterface getPushCenterInterface() {
//        if(IpushCenter == null)
//            IpushCenter = new XgPushAdapterIml();
//        return IpushCenter;
//    }
//
//    public void setPushCenterInterface(PushCenterInterface pushCenterInterface) {
//        IpushCenter = pushCenterInterface;
//    }
//}
