package com.weexbox.example.modules;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.lelibrary.androidlelibrary.ble.BluetoothLeDeviceStore;
import com.lelibrary.androidlelibrary.ble.BluetoothLeScanner;
import com.lelibrary.androidlelibrary.ble.BluetoothUtils;
import com.lelibrary.androidlelibrary.ble.ScannerCallback;
import com.lelibrary.androidlelibrary.ble.SmartDevice;
import com.lelibrary.androidlelibrary.ble.SmartDeviceModel;
import com.lelibrary.androidlelibrary.model.BLETagModel;
import com.lelibrary.androidlelibrary.model.HttpModel;
import com.lelibrary.androidlelibrary.sdk.InsigmaBluetoothManager;
import com.lelibrary.androidlelibrary.sdk.InsigmaSmartDevice;
import com.lelibrary.androidlelibrary.sdk.SmartServerAPI;
import com.lelibrary.androidlelibrary.sdk.callback.SmartCallback;
import com.lelibrary.androidlelibrary.sdk.callback.WSAssociationCallback;
import com.lelibrary.androidlelibrary.sdk.callback.WSCoolerCallback;
import com.lelibrary.androidlelibrary.sdk.callback.WSDeviceCallback;
import com.lelibrary.androidlelibrary.sdk.callback.WSRemoveAssociationCallback;
import com.lelibrary.androidlelibrary.sdk.callback.WSStringCallback;
import com.lelibrary.androidlelibrary.sdk.callback.WSStringProgressCallback;
import com.lelibrary.androidlelibrary.sdk.callback.WSUploadCallback;
import com.lelibrary.androidlelibrary.sdk.model.AssociationModel;
import com.lelibrary.androidlelibrary.sdk.model.CoolerModel;
import com.lelibrary.androidlelibrary.sdk.model.DeviceModel;
import com.lelibrary.androidlelibrary.sdk.model.RemoveAssociationModel;
import com.lelibrary.androidlelibrary.sdk.model.UploadStatusModel;
import com.lelibrary.androidlelibrary.sdk.utils.ValidationUtils;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.weex.weexextra.ModuleAdapterCallBack;
import com.weexbox.permissionutil.CPCallback;
import com.weexbox.permissionutil.CheckPermission;
import com.weexbox.permissionutil.Permission;
import com.weexbox.permissionutil.model.PermissionModel;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.litesuits.common.utils.HandlerUtil.runOnUiThread;

public class SmartTagModule extends WXModule implements CPCallback
{
    private SmartTagFactory smartTagFactory = null;
    private CheckPermission checkPermission=null;
    private ModuleAdapterCallBack downloadAdapterCallBack = null;
    private ModuleAdapterCallBack receiverAdapterCallBack = null;
    private BroadcastReceiver bleStateBroadCastReceiver;
    private boolean dataDownloaded = false;
//    public void isGranted(Context context, LELModule.doSomething dd){
//        AndPermission.with(context).requestCode(111).permission(CAMERA,READ_PHONE_STATE,WRITE_EXTERNAL_STORAGE,ACCESS_FINE_LOCATION,ACCESS_COARSE_LOCATION).rationale(new RationaleListener() {
//            @Override
//            public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
//                AndPermission.rationaleDialog(context,rationale).show();
//            }
//        }).callback(new PermissionListener() {
//            @Override
//            public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
//                if(dd!=null)dd.doST();
//            }
//
//            @Override
//            public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
//                WXLogUtils.w("AndPermission,onFailed");
//                ToastUtil.showLongToast(context, "Permission request Failed");
//            }
//        }).start();
//    }
//
//    interface doSomething{
//        void doST();
//    }
    public SmartTagModule(){
        receiver();
    }
    @JSMethod(uiThread = false)
    public void init(JSONObject optionObj,JSCallback successCallBack, JSCallback errorCallBack) {
        ModuleAdapterCallBack moduleAdapterCallBack = new ModuleAdapterCallBack(successCallBack,errorCallBack);
        if(optionObj==null) moduleAdapterCallBack.error("初始化参数不能为空");
        else {
            RegisterBoradCastReceiver(moduleAdapterCallBack);
            checkPermission = new CheckPermission((Activity)mWXSDKInstance.getContext(),null,this);
            checkPermission.IsPermissionsGranted();
            String _apIkey = optionObj.getString("APIkey");
            String _userName = optionObj.getString("UserName");
            Integer _server_Index = optionObj.getInteger("Server_Index");
            if (_userName != null && _userName.length() > 0 && _apIkey != null && _apIkey.length() > 0 && _server_Index != null) {
                smartTagFactory = new SmartTagFactory(_userName, _apIkey, _server_Index, mWXSDKInstance.getContext());
                JSONObject jdata = new JSONObject();
                jdata.put("status", "oninit");
                jdata.put("message","初始化成功");
                moduleAdapterCallBack.successKeepAlive("");
            } else {
                moduleAdapterCallBack.error("初始化参数填写错误，请重新填写");
            }
        }
    }

    @JSMethod(uiThread = false)
    public void checkBluetooth(JSONObject optionObj,JSCallback successCallBack, JSCallback errorCallBack){
        ModuleAdapterCallBack moduleAdapterCallBack = new ModuleAdapterCallBack(successCallBack,errorCallBack);
        if(smartTagFactory!=null){
            boolean bluetoothFlag = smartTagFactory.getBluetoothManager().isBluetoothON() && smartTagFactory.getBluetoothManager().isBluetoothLeSupported();
            JSONObject jdata = new JSONObject();
            jdata.put("status", bluetoothFlag);
            moduleAdapterCallBack.success(jdata);
        }else {
            moduleAdapterCallBack.error("没有正确的初始化，请先初始化配置");
        }

    }
    @JSMethod(uiThread = false)
    public void startScan(JSONObject optionsObj, JSCallback successCallBack, JSCallback errorCallBack) {
        ModuleAdapterCallBack moduleAdapterCallBack = new ModuleAdapterCallBack(successCallBack,errorCallBack);
        String _smartDeviceSN = optionsObj.getString("smartDeviceSN");
        if(smartTagFactory!=null){
            smartTagFactory.startScan(mWXSDKInstance.getContext(),new SmartTagCallback(){
                @Override
                public void onDeviceFound(SmartDevice var3, SmartDeviceModel var6) {
                    ArrayList<JSONObject> devices = smartTagFactory.updateDevice(var3,_smartDeviceSN);
                    Map m = new HashMap();
                    m.put("status","onDeviceFound");
                    m.put("list",devices);
                    moduleAdapterCallBack.successKeepAlive(m);
                }
                @Override
                public void onScanFinished(BluetoothLeDeviceStore var2) {
                    Map m = new HashMap();
                    m.put("status","onScanFinished");
                   // m.put("list",var2.getDeviceList());
                    moduleAdapterCallBack.successKeepAlive(m);
                }

                @Override
                public void onScanFailed(int var1) {
                    moduleAdapterCallBack.errorKeepAlive("搜索智能标签失败!");
                }
                @Override
                public void onError(String var1) {
                    moduleAdapterCallBack.errorKeepAlive(var1);
                }
            });
        }else {
            moduleAdapterCallBack.errorKeepAlive("没有正确的初始化，请先初始化配置");
        }
    }

    @JSMethod(uiThread = false)
    public void stopScan(){
        smartTagFactory.stopScan();
    }

    @JSMethod(uiThread = true)
    public void connectDevice(JSONObject optionObj, JSCallback successCallBack, JSCallback errorCallBack) {
        ModuleAdapterCallBack moduleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack);
        String _smartDeviceSN = optionObj.getString("smartDeviceSN");
        smartTagFactory.stopScan();
        smartTagFactory.connect(smartTagFactory.getDeviceBySN(_smartDeviceSN), new SmartConnectCallback() {
            @Override
            public void onDeviceConnected() {
                JSONObject jdata = new JSONObject();
                jdata.put("status", "onConnected");
                jdata.put("message","已连接成功");
                moduleAdapterCallBack.successKeepAlive(jdata);
            }
            @Override
            public void onDeviceDisconnected() {
                if(dataDownloaded)return;
                JSONObject jdata = new JSONObject();
                jdata.put("status", "onDisconnected");
                jdata.put("message","连接断开");
                moduleAdapterCallBack.successKeepAlive(jdata);
            }
            @Override
            public void onDataProgress(int currentIndex, int totalCount) {
                // 下载进度条
                Map m = new HashMap();
                JSONObject jdata = new JSONObject();
                m.put("status", "onDownProgress");
                try {
                    jdata.put("total",totalCount);
                    jdata.put("current",currentIndex);
                    jdata.put("message","下载数据...");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                m.put("progress", jdata);
                if(downloadAdapterCallBack!=null)downloadAdapterCallBack.successKeepAlive(m);
            }

            @Override
            public void onDataDownloaded(boolean isSuccess, ArrayList<BLETagModel> dataList) {
                //下载完成关闭蓝牙
                dataDownloaded = true;
                deviceDisconnect();
                if(isSuccess && dataList != null){
                    //上传数据
                    uploadData(downloadAdapterCallBack);
                }else{
                    Map m = new HashMap();
                    JSONObject jdata = new JSONObject();
                    m.put("status", "onDataDownloaded");
                    jdata.put("message","没有可以下载的数据可以直接解绑");
                    m.put("progress", jdata);
                    if(downloadAdapterCallBack!=null)downloadAdapterCallBack.successKeepAlive(m);
                }
            }
        });
    }

    @JSMethod(uiThread = false)
    public void deviceDisconnect() {
        if(smartTagFactory!=null) {
            smartTagFactory.deviceDisconnect();
        }
    }

    @JSMethod(uiThread = false)
    public void downloadUploadData(JSONObject optionObj, JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack) {
        ModuleAdapterCallBack moduleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);
        if (smartTagFactory.insigmaSmartDevice != null && smartTagFactory.insigmaSmartDevice.isDisconnected()) {
            moduleAdapterCallBack.errorKeepAlive("智能标签设备已断开, 请重新连接",10010);
            return;
        }else{
            downloadAdapterCallBack = moduleAdapterCallBack;
            smartTagFactory.insigmaSmartDevice.downloadData();
        }
    }

    @JSMethod(uiThread = false)
    public void checkDeviceAssociation(JSONObject optionObj, JSCallback successCallBack, JSCallback errorCallBack){
        ModuleAdapterCallBack moduleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack);
        String _smartDeviceSN = optionObj.getString("smartDeviceSN");
        if((_smartDeviceSN==null || _smartDeviceSN.isEmpty())){
            moduleAdapterCallBack.error("智能标签编号不能为空");
            return;
        }
        if(smartTagFactory!=null){
            smartTagFactory.checkDeviceAssociation(_smartDeviceSN,new WSDeviceCallback() {
                @Override
                public void onSuccess(DeviceModel deviceModel) {
                    JSONObject map = new JSONObject();
                    map.put("message",deviceModel.getMessage());
                    map.put("success",deviceModel.isSuccess());
                    JSONObject devicedata = new JSONObject();
                    devicedata.put("CoolerSN",deviceModel.getDeviceData().getAssociatedCoolerSerialNumber());
                    devicedata.put("isAvailableForAssociation",deviceModel.getDeviceData().isAvailableForAssociation());
                    map.put("deviceData",devicedata);
                    moduleAdapterCallBack.success(map);
                }
                @Override
                public void onFailure(String s, int i, Exception e) {
                    Map m = new HashMap();
                    m.put("message", s);
                    m.put("code", i);
                    m.put("exception", e!=null?e.getMessage():"");
                    moduleAdapterCallBack.error(m);
                }
            });
        }else {
            moduleAdapterCallBack.errorKeepAlive("没有正确的初始化，请先初始化配置");
        }
    }

    @JSMethod(uiThread = false)
    public void checkCoolerAssociation(JSONObject optionObj, JSCallback successCallBack, JSCallback errorCallBack){
        ModuleAdapterCallBack moduleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack);
        String _coolerSN = optionObj.getString("CoolerSN");
        if((_coolerSN==null || _coolerSN.isEmpty())){
            moduleAdapterCallBack.error("冰柜资产编号不能为空");
            return;
        }
        if(smartTagFactory!=null) {
            smartTagFactory.checkCoolerAssociation(_coolerSN,new WSCoolerCallback() {
                @Override
                public void onSuccess(CoolerModel coolerModel) {
                    JSONObject map = new JSONObject();
                    map.put("message",coolerModel.getMessage());
                    map.put("success",coolerModel.isSuccess());
                    JSONObject coolerdata = new JSONObject();
                    coolerdata.put("AssetSerialNumber",coolerModel.getAssetSerialNumber());
                    coolerdata.put("AssetType",coolerModel.getAssetType());
                    coolerdata.put("AssetTypeInstallationImages",coolerModel.getAssetTypeInstallationImages());
                    coolerdata.put("EquipmentNumber",coolerModel.getEquipmentNumber());
                    coolerdata.put("OutletCode",coolerModel.getOutletCode());
                    coolerdata.put("OutletName",coolerModel.getOutletName());
                    coolerdata.put("SN",coolerModel.getSmartDeviceSerial());
                    coolerdata.put("macAddress", smartTagFactory.getBluetoothManager().getDeviceSerialToMACAddress(coolerModel.getSmartDeviceSerial()));
                    coolerdata.put("isAssociated",coolerModel.isAssociated());
                    map.put("coolerData",coolerdata);
                    moduleAdapterCallBack.success(map);
                }

                @Override
                public void onFailure(String s, int i, Exception e) {
                    Map m = new HashMap();
                    m.put("message", s);
                    m.put("code", i);
                    m.put("exception", e!=null?e.getMessage():"");
                    moduleAdapterCallBack.error(m);
                }
            });
        }
    }

    @JSMethod(uiThread = false)
    public void doAssociation(JSONObject optionObj, JSCallback successCallBack, JSCallback errorCallBack){
        ModuleAdapterCallBack moduleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack);
        String _CoolerSN = optionObj.getString("CoolerSN");
        String _deviceMacAddress = optionObj.getString("deviceMacAddress");
        if((_CoolerSN!=null&&_CoolerSN.isEmpty()) || (_deviceMacAddress!=null && _deviceMacAddress.isEmpty())){
            moduleAdapterCallBack.error("资产编号或智能标签Mac地址不能为空,请检查后再试");
            return;
        }
        if(smartTagFactory!=null){
            smartTagFactory.doAssociation(_CoolerSN,_deviceMacAddress, new WSAssociationCallback() {
                @Override
                public void onSuccess(AssociationModel associationModel) {
                    Map m = new HashMap();
                    m.put("message", associationModel.getMessage());
                    m.put("success", associationModel.isSuccess());
                    moduleAdapterCallBack.success(m);
                }

                @Override
                public void onFailure(String s, int i, Exception e) {
                    Map m = new HashMap();
                    m.put("message", s);
                    m.put("code", i);
                    m.put("exception", e!=null?e.getMessage():"");
                    moduleAdapterCallBack.error(m);
                }
            });
        }else{
            moduleAdapterCallBack.error("没有正确的初始化，请先初始化配置");
        }
    }

    @JSMethod(uiThread = false)
    public void removeAssociation(JSONObject optionObj, JSCallback successCallBack, JSCallback errorCallBack){
        ModuleAdapterCallBack moduleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack);
        String _CoolerSN = optionObj.getString("CoolerSN");
        String _deviceMacAddress = optionObj.getString("deviceMacAddress");
        if((_CoolerSN!=null && _CoolerSN.isEmpty()) || (_deviceMacAddress!=null && _deviceMacAddress.isEmpty())){
            moduleAdapterCallBack.error("资产编号或智能标签Mac地址不能为空,请检查后再试");
            return;
        }
        if(smartTagFactory!=null){
            smartTagFactory.removeAssociation(_CoolerSN,_deviceMacAddress, new WSRemoveAssociationCallback() {
                @Override
                public void onSuccess(RemoveAssociationModel removeAssociationModel) {
                    Map m = new HashMap();
                    m.put("message", removeAssociationModel.getMessage());
                    m.put("success", removeAssociationModel.isSuccess());
                    moduleAdapterCallBack.success(m);
                }

                @Override
                public void onFailure(String s, int i, Exception e) {
                    Map m = new HashMap();
                    m.put("message", s);
                    m.put("code", i);
                    m.put("exception", e!=null?e.getMessage():"");
                    moduleAdapterCallBack.error(m);
                }
            });
        }else{
            moduleAdapterCallBack.error("没有正确的初始化，请先初始化配置");
        }
    }

    @JSMethod(uiThread = false)
    public void showProgress(JSONObject optionObj){
        smartTagFactory.showProgress(optionObj.getString("message"));
    }
    @JSMethod(uiThread = false)
    public void dismissProgress(){
        smartTagFactory.dismissProgress();
    }

    @JSMethod(uiThread = false)
    public void launchValidationApp(JSONObject optionObj, JSCallback successCallBack, JSCallback errorCallBack) {
        ModuleAdapterCallBack moduleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack);
        String _uname = optionObj.getString("uname");
        String _password = optionObj.getString("pwd");
        String _uid = optionObj.getString("uid");
        if((_password==null || _password.isEmpty()) || (_uid==null || _uid.isEmpty())){
            moduleAdapterCallBack.error("唤起智能检查标签帐号密码错误");
            return;
        }
        if(smartTagFactory!=null){
            smartTagFactory.launchValidationApp(_uname,_password,_uid,moduleAdapterCallBack);
        }else{
            moduleAdapterCallBack.error("没有正确的初始化，请先初始化配置");
        }

    }

//            if(smartTagFactory.smartServerAPI.isDataAvailableForUpload()){
//    smartTagFactory.smartServerAPI.uploadDataUploadDownloadLog(smartTagFactory._userName, new WSStringProgressCallback() {
//        long total = 0;
//        @Override
//        public void onProgress(long l, String s) {
//            // 上传进度条
//            Map m = new HashMap();
//            JSONObject jdata = new JSONObject();
//            if(total==0) total = l;
//            m.put("status", "onUpProgress");
//            try {
//                jdata.put("total",total);
//                jdata.put("current",total-l);
//                jdata.put("message","上传中...");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            m.put("progress", jdata);
//            mAdaptercb.successKeepAlive(m);
//        }
//
//        @Override
//        public void onSuccess(HttpModel httpModel) {
//            total = 0;
//            Map m = new HashMap();
//            JSONObject jdata = new JSONObject();
//            m.put("status", "onDataDownloaded");
//            jdata.put("message","上传完成");
//            m.put("progress", jdata);
//            mAdaptercb.successKeepAlive(m);
//        }
//
//        @Override
//        public void onFailure(String s, int i, Exception e) {
//            total = 0;
//            Map m = new HashMap();
//            m.put("message", s);
//            m.put("code", i);
//            m.put("exception", e!=null?e.getMessage():"");
//            mAdaptercb.errorKeepAlive(m);
//            //上传失败
//        }
//    });
//}else{
//    mAdaptercb.successKeepAlive("无数据可以上传");
//}
    private void uploadData(ModuleAdapterCallBack mAdaptercb){
        smartTagFactory.smartServerAPI.uploadData(smartTagFactory._userName, new WSUploadCallback() {
            long total = 0;
            @Override
            public void onFailure(UploadStatusModel uploadStatusModel, String s, int i, Exception e) {
                total = 0;
                Map m = new HashMap();
                m.put("message", s);
                m.put("code", i);
                m.put("exception", e!=null?e.getMessage():"");
                mAdaptercb.errorKeepAlive(m);
                //上传失败
            }

            @Override
            public void onSuccess(final UploadStatusModel uploadStatusModel, HttpModel result) {

            }

            @Override
            public void onProgress(final long l,final String MACAddress,final String Message) {
                // 上传进度条
                Map m = new HashMap();
                JSONObject jdata = new JSONObject();
                if(total==0) total = l;
                m.put("status", "onUpProgress");
                try {
                    jdata.put("total",total);
                    jdata.put("current",total-l);
                    jdata.put("message","上传中...");
                } catch (JSONException e) {
                        e.printStackTrace();
                }
                m.put("progress", jdata);
                mAdaptercb.successKeepAlive(m);
            }

            @Override
            public void onAllDataUploaded() {
                total = 0;
                Map m = new HashMap();
                JSONObject jdata = new JSONObject();
                m.put("status", "onDataDownloaded");
                jdata.put("message","上传完成");
                m.put("progress", jdata);
                mAdaptercb.successKeepAlive(m);
            }
        });
    }

    @Override
    public boolean onActivityBack() {
        return super.onActivityBack();
    }

    @Override
    public void onActivityDestroy() {
        smartTagFactory.destroy();
        super.onActivityDestroy();
        UnRegisterBoradCastReceiver();
    }

    @Override
    public Map<String, PermissionModel> getPermissionModelList() {
        Map<String, PermissionModel> permissionModelList = new HashMap<>();
        permissionModelList.put(Permission.CAMERA, new PermissionModel("Camera", PackageManager.PERMISSION_GRANTED));
        permissionModelList.put(Permission.WRITE_EXTERNAL_STORAGE, new PermissionModel("Storage", PackageManager.PERMISSION_GRANTED));
        permissionModelList.put(Permission.READ_EXTERNAL_STORAGE, new PermissionModel("", PackageManager.PERMISSION_GRANTED));
        permissionModelList.put(Permission.ACCESS_FINE_LOCATION, new PermissionModel("Location", PackageManager.PERMISSION_GRANTED));
        permissionModelList.put(Permission.ACCESS_COARSE_LOCATION, new PermissionModel("", PackageManager.PERMISSION_GRANTED));
        return permissionModelList;
    }

    @Override
    public void isGranted() {

    }

    @Override
    public void onStopApp() {
    }

    private void receiver(){
        this.bleStateBroadCastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context c, Intent intent) {
                if (intent != null && "android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction())) {
                    int state = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -2147483648);
                    JSONObject jdata = new JSONObject();
                    switch(state) {
                        case 10:
                            break;
                        case 11:
                            //Turning Bluetooth on...
                            break;
                        case 12://Bluetooth on...
                        {
                            if(receiverAdapterCallBack!=null){
                                jdata.clear();
                                jdata.put("status","on");
                                jdata.put("message","蓝牙已打开,请等待");
                                receiverAdapterCallBack.successKeepAlive(jdata);
                            }
                        }
                        break;
                        case 13: //"Turning Bluetooth off..."
                        {
                            if(receiverAdapterCallBack!=null){
                                jdata.clear();
                                jdata.put("status","off");
                                jdata.put("message","蓝牙已关闭,请先打开蓝牙");
                                receiverAdapterCallBack.successKeepAlive(jdata);
                            }
                        }
                        break;
                        default:
                            //
                    }
                }
            }
        };
    }

    private void RegisterBoradCastReceiver(ModuleAdapterCallBack receiverAdapterCallBack) {
        if (mWXSDKInstance.getContext() != null && this.receiverAdapterCallBack==null) {
            try {
                this.receiverAdapterCallBack = receiverAdapterCallBack;
                mWXSDKInstance.getContext().registerReceiver(this.bleStateBroadCastReceiver, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
            } catch (Exception var2) {

            }
        }
    }

    private void UnRegisterBoradCastReceiver() {
        if (mWXSDKInstance.getContext() != null && this.receiverAdapterCallBack!=null) {
            try {
                this.receiverAdapterCallBack = null;
                mWXSDKInstance.getContext().unregisterReceiver(this.bleStateBroadCastReceiver);
            } catch (Exception var2) {

            }
        }

    }



    /*#################################################################################################*/
    static class SmartTagFactory implements ScannerCallback
    {
        private static String _userName = null;
        private static SmartServerAPI smartServerAPI = null;
        private static InsigmaBluetoothManager insigmaBluetoothManager = null;
        private static int _scanState = 0;
        private Context context = null;
        private static SmartTagCallback mSmartInterface;
        SmartTagFactory(String userName,String apiKey,Integer server_Index,Context _context){
            context = _context;
            mSmartInterface = null;
            smartServerAPI = new SmartServerAPI(context.getApplicationContext());
            smartServerAPI.setAPIKey(apiKey);
            smartServerAPI.setAccessURL(server_Index);
            smartServerAPI.setServerTimeoutInterval(180);
            _userName = userName;

            insigmaBluetoothManager = new InsigmaBluetoothManager(context,this);
            insigmaBluetoothManager.setTimeoutInterval(30);
            insigmaBluetoothManager.setCalibratedTXPower(-59);
        }

//        private SmartServerAPI getInstanceSmartAPI(Context context){
//            if(smartServerAPI == null){
//                smartServerAPI = new SmartServerAPI(context.getApplicationContext());
//            }
//            return smartServerAPI;
//        }

        private InsigmaBluetoothManager getBluetoothManager(){
            return insigmaBluetoothManager;
        }

        private void startScan(Context context,SmartTagCallback _mSmartInterface){
            try {
                _scanState = 1;
                mSmartInterface = _mSmartInterface;
                final boolean mIsBluetoothOn = getBluetoothManager().isBluetoothON();
                final boolean mIsBluetoothLePresent = getBluetoothManager().isBluetoothLeSupported();
                getBluetoothManager().askUserToEnableBluetoothIfNeeded((Activity) context);
                if (!mIsBluetoothOn || !mIsBluetoothLePresent) {
                    String msg = !mIsBluetoothOn?"请检查蓝牙是否开启":"";
                    if(mSmartInterface!=null)mSmartInterface.onError(msg.concat(!mIsBluetoothLePresent?"请检查蓝牙是否支持":""));
                    return;
                }
                getBluetoothManager().stopScan();
                getBluetoothManager().startScan();
            }catch (Exception e){
                stopScan();
                mSmartInterface.onError(e.getMessage());
            }
        }

        private void stopScan(){
            if(insigmaBluetoothManager!=null){
                insigmaBluetoothManager.stopScan();
            }
            _scanState = 0;
        }

        private InsigmaSmartDevice insigmaSmartDevice = null;
        private void connect(SmartDevice smartDevice, SmartConnectCallback sccallback) {
            if(insigmaSmartDevice==null || !insigmaSmartDevice.getSmartDevice().getSerialNumber().equals(smartDevice.getSerialNumber())) {
                insigmaSmartDevice = new InsigmaSmartDevice(context, smartDevice, new SmartCallback() {
                    @Override
                    public void onDeviceConnected(SmartDevice smartDevice) {
                        if(sccallback!=null){sccallback.onDeviceConnected();}
                    }

                    @Override
                    public void onDeviceDisconnected(SmartDevice smartDevice) {
                        if(sccallback!=null){sccallback.onDeviceDisconnected();}
                    }

                    @Override
                    public void onImageSequenceTableDownloaded(SmartDevice smartDevice, boolean b, org.json.JSONArray jsonArray) {
                    }

                    @Override
                    public void onImageDeleted(SmartDevice smartDevice, boolean b) {
                    }

                    @Override
                    public void onImageDownloadProgress(SmartDevice smartDevice, int i, int i1, int i2) {
                    }

                    @Override
                    public void onImageDownloadCompleted(SmartDevice smartDevice, boolean b, ByteArrayOutputStream byteArrayOutputStream) {
                    }

                    @Override
                    public void onDataDownloaded(SmartDevice smartDevice, boolean b, ArrayList<BLETagModel> arrayList) {
                        if(sccallback!=null){sccallback.onDataDownloaded(b,arrayList);}
                    }

                    @Override
                    public void onDataProgress(SmartDevice device, int currentIndex, int totalCount) {
                        if(sccallback!=null){sccallback.onDataProgress(currentIndex,totalCount);}
                        //showProgress(currentIndex + "/" + totalCount + " Data Downloading...");
                    }

                    @Override
                    public void onEraseAllEvents(SmartDevice smartDevice, boolean b) {
                    }

                    @Override
                    public void onRemoteCommandsExecutionProcess(SmartDevice smartDevice, org.json.JSONObject jsonObject, int i, int i1) {
                    }

                    @Override
                    public void onRemoteCommandsExecutionFinished(SmartDevice smartDevice, int i, String s) {
                    }

                    @Override
                    public void onUpdate(SmartDevice smartDevice, String s) {
                    }

                    @Override
                    public void onLogUpdate(SmartDevice smartDevice, String s) {
                    }

                    @Override
                    public void onUpdateFirmwareNumber(SmartDevice smartDevice, String s) {
                        //Firmware Version
                    }

                    @Override
                    public void onUpdateRssi(SmartDevice smartDevice, int i, int i1, double v, String s) {
                    }

                    @Override
                    public void onDFUProgress(SmartDevice smartDevice, int i, int i1, float v, float v1) {
                    }

                    @Override
                    public void onDFUSuccess(SmartDevice smartDevice) {
                    }

                    @Override
                    public void onDFUFailed(SmartDevice smartDevice, String s) {
                    }
                });
            }
            if (insigmaSmartDevice.isDisconnected()) {
                if(!smartDevice.isDeviceInWhiteList(context)){
                    smartServerAPI.getDeviceWhiteListData(_userName, smartDevice.getSerialNumber(), new WSStringCallback() {
                        @Override
                        public void onSuccess(HttpModel httpModel) {
                            if(httpModel.isSuccess()){
                                insigmaSmartDevice.connectDevice();
                            }else{
                                //白名单失败
                                //TODO
                            }
                        }

                        @Override
                        public void onFailure(String s, int i, Exception e) {
                            //TODO 连接失败
                        }
                    });
                }
                else{ /*与设备已经断开重新连接*/
                    insigmaSmartDevice.connectDevice();
                }
            } else {
                //已连接直接返回
                if(sccallback!=null){sccallback.onDeviceConnected();}
            }
        }

        private void checkDeviceAssociation(String deviceSerial, WSDeviceCallback cb){
            smartServerAPI.checkDeviceAssociation(_userName,deviceSerial,cb);
        }

        private void checkCoolerAssociation(String coolerSerial,WSCoolerCallback cb){
            smartServerAPI.checkCoolerAssociation(_userName,coolerSerial,cb);
        }

        private void launchValidationApp (String userName,String password,String uid,ModuleAdapterCallBack cb){
            boolean b = ValidationUtils.launchValidationApp(context,userName!=null?userName:_userName,password,uid);
            if(b){
                cb.success("");
            }else{
                cb.error("请先安装智能标签检查App");
            }
        }
        private void deviceDisconnect() {
            if (insigmaSmartDevice != null) {
                if (!insigmaSmartDevice.isDisconnected()) {
                    insigmaSmartDevice.disconnectDevice();
                }
            }
        }

        private int getScanState(){
            return  _scanState;
        }

        private ArrayList<JSONObject> _devices = new ArrayList<JSONObject>();
        private ArrayList<SmartDevice> _smartdevices = new ArrayList<SmartDevice>();
        private JSONObject _deviceSN = new JSONObject();
        private ArrayList<JSONObject> updateDevice(SmartDevice device) {
            return updateDevice(device,null);
        }
        private ArrayList<JSONObject> updateDevice(SmartDevice device,String smartDeviceSN){
            String _smartDeviceSN  = smartDeviceSN!=null?lpad(smartDeviceSN.length(),device.getSerialNumber()):"";
            if(smartDeviceSN!=null && !_smartDeviceSN.equals(smartDeviceSN))
            {
                return new ArrayList<JSONObject>();
            }else{
                if(_deviceSN.containsKey(device.getSerialNumber())){
                    _smartdevices.set(_deviceSN.getInteger(device.getSerialNumber()),device);
                    _devices.set(_deviceSN.getInteger(device.getSerialNumber()),deviceConvertJSON(device));
                }else {
                    _deviceSN.put(device.getSerialNumber(),_devices.size());
                    _smartdevices.add(device);
                    _devices.add(deviceConvertJSON(device));
                }
            }
            return _devices;
        }
        private String lpad(int length, String number) {
            return String.format("%"+length+"s", number).replace(' ', '0');
        }

        private JSONObject getJSONDeviceBySN(String smartDeviceSN){
            if(smartDeviceSN.isEmpty() || _devices.size() < _deviceSN.getInteger(smartDeviceSN)) {
                return null;
            }else{
                return _devices.get(_deviceSN.getInteger(smartDeviceSN));
            }
        }
        private SmartDevice getDeviceBySN(String smartDeviceSN){
            if(smartDeviceSN.isEmpty() || _devices.size() < _deviceSN.getInteger(smartDeviceSN)) {
                return null;
            }else{
                return _smartdevices.get(_deviceSN.getInteger(smartDeviceSN));
            }
        }
        private JSONObject deviceConvertJSON(SmartDevice smartDevice){
            JSONObject map = new JSONObject();
            map.put("Name",smartDevice.getDevice().getName());
            map.put("uuid",smartDevice.getIbeaconUUID());
            map.put("BatteryLevel",smartDevice.getBatteryLevel());
            map.put("CoolerID",smartDevice.getCoolerId());
            map.put("SN",smartDevice.getSerialNumber());
            map.put("Distance",smartDevice.getDistanceInMeter(context));
            map.put("DistanceInMM",smartDevice.getSmartShelfDistanceInMM());
            map.put("DistanceRange",smartDevice.getRSSIRange(smartDevice.getDistanceInMeter(context)));
            map.put("RunningAverageRssiAccurate",smartDevice.getRunningAverageRssiAccurate());
            map.put("macAddress",smartDevice.getAddress());
            map.put("Rssi",smartDevice.getRssi());
            map.put("DeviceType",smartDevice.getDeviceTypeName());
            map.put("DeviceTypeId",smartDevice.getDeviceTypeId());
            map.put("isDoorOpen",smartDevice.isDoorOpen());
            map.put("isMultiDoorEnable",smartDevice.isMultiDoorEnable());
            map.put("isDoorTimeout",smartDevice.isDoorTimeout());
            map.put("SmartDoorCount",smartDevice.getSmartDoorCount());
            map.put("isIBeacon", smartDevice.isIBeacon());
            return map;
        }

        private void clearAll(){
            _devices.clear();
            _deviceSN.clear();
            _smartdevices.clear();
        }

        private void doAssociation(String coolerSN,String deviceMacAddress, WSAssociationCallback cb){
            smartServerAPI.doAssociation(_userName,coolerSN,deviceMacAddress,cb);
        }

        private void removeAssociation(String coolerSN,String deviceMacAddress, WSRemoveAssociationCallback cb) {
            smartServerAPI.removeAssociation(_userName, coolerSN, deviceMacAddress, cb);
        }

        @Override
        public synchronized void onDeviceFound(BluetoothLeScanner bluetoothLeScanner, BluetoothLeDeviceStore bluetoothLeDeviceStore, SmartDevice smartDevice, Context context, boolean b, SmartDeviceModel smartDeviceModel) {
            if(mSmartInterface!=null)mSmartInterface.onDeviceFound(smartDevice,smartDeviceModel);
        }

        @Override
        public synchronized void onScanFinished(BluetoothLeScanner bluetoothLeScanner, BluetoothLeDeviceStore bluetoothLeDeviceStore, Context context, boolean b) {
            if(mSmartInterface!=null)mSmartInterface.onScanFinished(bluetoothLeDeviceStore);
        }

        @Override
        public void onScanFailed(int i) {
            if(mSmartInterface!=null)mSmartInterface.onScanFailed(i);
        }

        private void destroy(){
            if(insigmaBluetoothManager!=null){
                insigmaBluetoothManager.stopScan();
                insigmaBluetoothManager.onDestroy();
            }
            if(smartServerAPI!=null){
                smartServerAPI.onDestroy();
                smartServerAPI.onDestroy();
            }
        }

        private ProgressDialog progressDialog;
        private void showProgress(final String message) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (progressDialog != null) {
                            if (!progressDialog.isShowing()) {
                                progressDialog = new ProgressDialog(context);
                                if (TextUtils.isEmpty(message)) {
                                    progressDialog.setMessage("上传中...");
                                } else {
                                    progressDialog.setMessage(message);
                                }
                                progressDialog.setCancelable(false);
                                progressDialog.setCanceledOnTouchOutside(false);
                                progressDialog.show();
                            } else {
                                if (TextUtils.isEmpty(message)) {
                                    progressDialog.setMessage("请稍等...");
                                } else {
                                    progressDialog.setMessage(message);
                                }
                            }
                        } else {
                            progressDialog = new ProgressDialog(context);
                            if (TextUtils.isEmpty(message)) {
                                progressDialog.setMessage("加载中...");
                            } else {
                                progressDialog.setMessage(message);
                            }
                            progressDialog.setCancelable(false);
                            progressDialog.setCanceledOnTouchOutside(false);
                            progressDialog.show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        private void dismissProgress() {
            try {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (progressDialog != null) {
                            progressDialog.dismiss();
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public interface SmartTagCallback {
        void onDeviceFound(SmartDevice var3, SmartDeviceModel var6);

        void onScanFinished(BluetoothLeDeviceStore var2);

        void onScanFailed(int var1);

        void onError(String var1);
    }

    public  interface SmartConnectCallback{
        void onDeviceConnected();

        void onDeviceDisconnected();

        void onDataProgress(int currentIndex, int totalCount);

        void onDataDownloaded(boolean b, ArrayList<BLETagModel> arrayList);
    }
}
