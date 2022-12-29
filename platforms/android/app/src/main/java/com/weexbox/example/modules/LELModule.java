package com.weexbox.example.modules;

import android.app.Activity;
import android.content.Context;
//import android.support.annotation.NonNull;
import androidx.annotation.NonNull;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.bugfender.sdk.MyBugfender;
import com.lelibrary.androidlelibrary.ble.BluetoothLeDeviceStore;
import com.lelibrary.androidlelibrary.ble.BluetoothLeScanner;
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
import com.taobao.weex.utils.WXLogUtils;
import com.weex.weexextra.ModuleAdapterCallBack;
import com.weexbox.core.util.ToastUtil;
import com.weexbox.example.LaunchActivity;
import com.weexbox.example.R;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class LELModule extends WXModule {
    public void isGranted(Context context,doSomething dd){
        AndPermission.with(context).requestCode(111).permission(CAMERA,READ_PHONE_STATE,WRITE_EXTERNAL_STORAGE,ACCESS_FINE_LOCATION,ACCESS_COARSE_LOCATION).rationale(new RationaleListener() {
            @Override
            public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                AndPermission.rationaleDialog(context,rationale).show();
            }
        }).callback(new PermissionListener() {
            @Override
            public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                if(dd!=null)dd.doST();
            }

            @Override
            public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                WXLogUtils.w("AndPermission,onFailed");
                ToastUtil.showLongToast(context, "Permission request Failed");
            }
        }).start();
    }

    interface doSomething{
        void doST();
    }

    private static SmartServerAPI smartServerAPI = null;
    private SmartServerAPI getAPI(Context context){
        if(smartServerAPI == null){
            smartServerAPI = new SmartServerAPI(context.getApplicationContext());
        }
        return smartServerAPI;
    }
    private static InsigmaBluetoothManager insigmaBluetoothManager = null;
//    private InsigmaBluetoothManager getBluetoothManager(Context context){
//        if(insigmaBluetoothManager == null){
//            insigmaBluetoothManager = new InsigmaBluetoothManager(context,this);
//        }
//        return insigmaBluetoothManager;
//    }
    @JSMethod
    public void setAPIKey(JSONObject object, JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack) {
        ModuleAdapterCallBack moduleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);
        if(!object.containsKey("APIkey")){
            moduleAdapterCallBack.error("params error");
            return;
        }
        getAPI(mWXSDKInstance.getContext()).setAPIKey(object.getString("APIkey"));
        moduleAdapterCallBack.success("");
    }

    @JSMethod
    public void setAccessURL(JSONObject object, JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack) {
        ModuleAdapterCallBack moduleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);
        if (!object.containsKey("Server_Index")) {
            moduleAdapterCallBack.error("params error");
            return;
        }
        getAPI(mWXSDKInstance.getContext()).setAccessURL(object.getIntValue("Server_Index"));
        moduleAdapterCallBack.success("");
    }

    @JSMethod
    public void setAPIKeyAndAccessURL(JSONObject object, JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack) {
        ModuleAdapterCallBack moduleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);
        if(!object.containsKey("APIkey")||!object.containsKey("Server_Index")){
            moduleAdapterCallBack.error("params error");
            return;
        }
        getAPI(mWXSDKInstance.getContext()).setAPIKey(object.getString("APIkey"));
        getAPI(mWXSDKInstance.getContext()).setAccessURL(object.getIntValue("Server_Index"));
        moduleAdapterCallBack.success("");
    }

    private String UserName = "";
    @JSMethod
    public void setAPIKeyAndAccessURLAndUserName(JSONObject object, JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack) {
        ModuleAdapterCallBack moduleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);
        if(!object.containsKey("APIkey")||!object.containsKey("Server_Index")||!object.containsKey("UserName")){
            moduleAdapterCallBack.error("params error");
            return;
        }
        getAPI(mWXSDKInstance.getContext()).setAPIKey(object.getString("APIkey"));
        getAPI(mWXSDKInstance.getContext()).setAccessURL(object.getIntValue("Server_Index"));
        UserName = object.getString("UserName");
        moduleAdapterCallBack.success("");
    }

    @JSMethod
    public void setServerTimeoutInterval(JSONObject object, JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack) {
        ModuleAdapterCallBack moduleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);
        if (!object.containsKey("ServerConnectionTimeout")) {
            moduleAdapterCallBack.error("params error");
            return;
        }
        getAPI(mWXSDKInstance.getContext()).setServerTimeoutInterval(object.getIntValue("ServerConnectionTimeout"));
        moduleAdapterCallBack.success("");
    }

    @JSMethod
    public void setBleTimeoutInterval(JSONObject object, JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack) {
        ModuleAdapterCallBack moduleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);
        if (!object.containsKey("ServerConnectionTimeout")) {
            moduleAdapterCallBack.error("params error");
            return;
        }
        getAPI(mWXSDKInstance.getContext()).setServerTimeoutInterval(object.getIntValue("ServerConnectionTimeout"));
        moduleAdapterCallBack.success("");
    }

    static SmartDevice mSmartDevice;

    private List<SmartDevice> smartDevices = new ArrayList<>();
    @JSMethod
    public void startScan(JSONObject object, JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack) {
        ModuleAdapterCallBack moduleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);
        try {
            insigmaBluetoothManager = new InsigmaBluetoothManager(mWXSDKInstance.getContext().getApplicationContext(),
                    new ScannerCallback() {
                        @Override
                        public void onDeviceFound(BluetoothLeScanner bluetoothLeScanner, BluetoothLeDeviceStore bluetoothLeDeviceStore, SmartDevice smartDevice, Context context, boolean b, SmartDeviceModel smartDeviceModel) {
                            Map m = new HashMap();
                            m.put("type", "onDeviceFound");
//                            m.put("device", JSON.toJSON(smartDeviceModel));
//                            m.put("device", JSON.toJSON(smartDevice));


                            JSONObject map = new JSONObject();
                            try {
                                map.put("IBeaconUUID",smartDevice.getIbeaconUUID());
                                map.put("BatteryLevel",smartDevice.getBatteryLevel());
                                map.put("CoolerID",smartDevice.getCoolerId());
                                map.put("SerialNumber",smartDevice.getSerialNumber());
                                map.put("Distance",smartDevice.getDistanceInMeter(mWXSDKInstance.getContext()));
                                map.put("DistanceInMM",smartDevice.getSmartShelfDistanceInMM());
                                map.put("DistanceRange",smartDevice.getRSSIRange(smartDevice.getDistanceInMeter(mWXSDKInstance.getContext())));
                                map.put("RunningAverageRssiAccurate",smartDevice.getRunningAverageRssiAccurate());
                                map.put("macAddress",smartDevice.getAddress());
                                map.put("Rssi",smartDevice.getRssi());
                                map.put("Name",smartDevice.getDevice().getName());
                                map.put("DeviceType",smartDevice.getDeviceTypeName());
                                map.put("DeviceTypeId",smartDevice.getDeviceTypeId());
                                map.put("isDoorOpen",smartDevice.isDoorOpen());
                                map.put("isMultiDoorEnable",smartDevice.isMultiDoorEnable());
                                map.put("isDoorTimeout",smartDevice.isDoorTimeout());
                                map.put("SmartDoorCount",smartDevice.getSmartDoorCount());
//                                map.put("isDoor2Open",smartDevice.isDoor2Open());
//                                map.put("isDoor2Timeout",smartDevice.isDoor2Timeout());
//                                map.put("isDoor3Open",smartDevice.isDoor3Open());
//                                map.put("isDoor3Timeout",smartDevice.isDoor3Timeout());
                                if(object.containsKey("smartDeviceSN")&&object.getString("smartDeviceSN").equals(smartDevice.getSerialNumber())){
                                    mSmartDevice = smartDevice;
                                    insigmaBluetoothManager.stopScan();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            m.put("device", map);
                            moduleAdapterCallBack.successKeepAlive(m);
                        }

                        @Override
                        public void onScanFinished(BluetoothLeScanner bluetoothLeScanner, BluetoothLeDeviceStore bluetoothLeDeviceStore, Context context, boolean b) {
                            Map m = new HashMap();
                            m.put("type", "onScanFinished");
//                            m.put("devices", JSONArray.parseArray(JSON.toJSONString(bluetoothLeDeviceStore.getDeviceList())));
                            m.put("devices",  new JSONArray(new ArrayList<Object>(bluetoothLeDeviceStore.getDeviceList())));
                            smartDevices = bluetoothLeDeviceStore.getDeviceList();
                            moduleAdapterCallBack.success(m);
                        }

                        @Override
                        public void onScanFailed(int i) {
                            Map m = new HashMap();
                            m.put("type", "onScanFailed");
                            m.put("code", i);
                            moduleAdapterCallBack.error(m);
                        }
                    });
            final boolean mIsBluetoothOn = insigmaBluetoothManager.isBluetoothON();
            final boolean mIsBluetoothLePresent = insigmaBluetoothManager.isBluetoothLeSupported();

            insigmaBluetoothManager.askUserToEnableBluetoothIfNeeded((Activity) mWXSDKInstance.getContext());
            if (!mIsBluetoothOn || !mIsBluetoothLePresent) {
                moduleAdapterCallBack.error("IsBluetoothOn or IsBluetoothLePresent?");
                return;
            }
            insigmaBluetoothManager.startScan();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @JSMethod
    public void stopScan(){
        if(insigmaBluetoothManager != null)insigmaBluetoothManager.stopScan();
    }

    @JSMethod
    public void getCoolerModel(JSONObject jsonObject, JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack) {
        ModuleAdapterCallBack moduleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);
        if(!jsonObject.containsKey("coolerSerial")){
            moduleAdapterCallBack.error("no coolerSerial");
            return;
        }

        getAPI(mWXSDKInstance.getContext()).checkCoolerAssociation(jsonObject.getString("username"), jsonObject.getString("coolerSerial"), new WSCoolerCallback() {
            @Override
            public void onSuccess(CoolerModel coolerModel) {
                JSONObject map = new JSONObject();
                try {
                    map.put("AssetSerialNumber",coolerModel.getAssetSerialNumber());
                    map.put("AssetType",coolerModel.getAssetType());
                    map.put("AssetTypeInstallationImages",coolerModel.getAssetTypeInstallationImages());
                    map.put("EquipmentNumber",coolerModel.getEquipmentNumber());
                    map.put("Message",coolerModel.getMessage());
                    map.put("OutletCode",coolerModel.getOutletCode());
                    map.put("OutletName",coolerModel.getOutletName());
                    map.put("SmartDeviceSerial",coolerModel.getSmartDeviceSerial());
                    map.put("isAssociated",coolerModel.isAssociated());
                    map.put("isSuccess",coolerModel.isSuccess());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                moduleAdapterCallBack.success(map);
            }

            @Override
            public void onFailure(String s, int i, Exception e) {
                Map m = new HashMap();
                m.put("error",s);
                m.put("code",i);
                m.put("Exception",e.getMessage());
                moduleAdapterCallBack.error(m);
            }
        });
    }

    @JSMethod
    public void launchValidationApp(JSONObject jsonObject, JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack){
        ModuleAdapterCallBack moduleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);
        boolean b = ValidationUtils.launchValidationApp(mWXSDKInstance.getContext(),UserName,jsonObject.getString("pwd"),jsonObject.getString("uid"));
        if(b){
            moduleAdapterCallBack.success("");
        }else{
            moduleAdapterCallBack.error("");
        }
    }

    @JSMethod
    public void isValidationAppExists(JSONObject jsonObject, JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack){
        ModuleAdapterCallBack moduleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);
        boolean b = ValidationUtils.isValidationAppExists(mWXSDKInstance.getContext());
        if(b){
            moduleAdapterCallBack.success("");
        }else{
            moduleAdapterCallBack.error("");
        }
    }

    @JSMethod
    public void doAssociation(JSONObject jsonObject, JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack){
        ModuleAdapterCallBack moduleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);
        getAPI(mWXSDKInstance.getContext()).doAssociation(UserName, jsonObject.getString("coolerSN"), jsonObject.getString("deviceMacAddress")
                , new WSAssociationCallback() {
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
                        m.put("exception", e.getMessage());
                        moduleAdapterCallBack.success(m);
                    }
                });
    }

    @JSMethod
    public void removeAssociation(JSONObject jsonObject, JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack){
        ModuleAdapterCallBack moduleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);
        getAPI(mWXSDKInstance.getContext()).removeAssociation(UserName, jsonObject.getString("coolerSN"), jsonObject.getString("deviceMacAddress")
                , new WSRemoveAssociationCallback() {
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
                        m.put("exception", e.getMessage());
                        moduleAdapterCallBack.success(m);
                    }
                });
    }

    @JSMethod
    public void checkDeviceAssociation(JSONObject jsonObject, JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack){
        ModuleAdapterCallBack moduleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);
        getAPI(mWXSDKInstance.getContext()).checkDeviceAssociation(UserName, jsonObject.getString("smartDeviceSN")
                , new WSDeviceCallback() {
                    @Override
                    public void onSuccess(DeviceModel deviceModel) {
                        JSONObject map = new JSONObject();
                        map.put("message",deviceModel.getMessage());
                        map.put("success",deviceModel.isSuccess());

                        JSONArray data = new JSONArray();
                        for (int i = 0; i < deviceModel.getData().size(); i++) {
                            JSONObject devicedata1 = new JSONObject();
                            devicedata1.put("AssociatedCoolerSerialNumber",deviceModel.getData().get(i).getAssociatedCoolerSerialNumber());
                            devicedata1.put("DeviceSerialNumber",deviceModel.getData().get(i).getDeviceSerialNumber());
                            devicedata1.put("ErrorType",deviceModel.getData().get(i).getErrorType());
                            devicedata1.put("isAvailableForAssociation",deviceModel.getData().get(i).isAvailableForAssociation());
                            data.add(devicedata1);
                        }
                        map.put("data",data);

                        JSONObject devicedata = new JSONObject();
                        devicedata.put("AssociatedCoolerSerialNumber",deviceModel.getDeviceData().getAssociatedCoolerSerialNumber());
                        devicedata.put("DeviceSerialNumber",deviceModel.getDeviceData().getDeviceSerialNumber());
                        devicedata.put("ErrorType",deviceModel.getDeviceData().getErrorType());
                        devicedata.put("isAvailableForAssociation",deviceModel.getDeviceData().isAvailableForAssociation());
                        map.put("devicedata",devicedata);
                        moduleAdapterCallBack.success(map);
                    }

                    @Override
                    public void onFailure(String s, int i, Exception e) {
                        Map m = new HashMap();
                        m.put("message", s);
                        m.put("code", i);
                        m.put("exception", e.getMessage());
                        moduleAdapterCallBack.success(m);
                    }
                });
    }

    @JSMethod
    public void checkCoolerAssociation(JSONObject jsonObject, JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack){
        ModuleAdapterCallBack moduleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);
        getAPI(mWXSDKInstance.getContext()).checkCoolerAssociation(UserName, jsonObject.getString("CoolerSN")
                , new WSCoolerCallback() {
                    @Override
                    public void onSuccess(CoolerModel coolerModel) {
                        JSONObject map = new JSONObject();
                        try {
                            map.put("AssetSerialNumber",coolerModel.getAssetSerialNumber());
                            map.put("AssetType",coolerModel.getAssetType());
                            map.put("AssetTypeInstallationImages",coolerModel.getAssetTypeInstallationImages());
                            map.put("EquipmentNumber",coolerModel.getEquipmentNumber());
                            map.put("Message",coolerModel.getMessage());
                            map.put("OutletCode",coolerModel.getOutletCode());
                            map.put("OutletName",coolerModel.getOutletName());
                            map.put("SmartDeviceSerial",coolerModel.getSmartDeviceSerial());
                            map.put("isAssociated",coolerModel.isAssociated());
                            map.put("isSuccess",coolerModel.isSuccess());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        moduleAdapterCallBack.success(map);
                    }

                    @Override
                    public void onFailure(String s, int i, Exception e) {
                        Map m = new HashMap();
                        m.put("message", s);
                        m.put("code", i);
                        m.put("exception", e.getMessage());
                        moduleAdapterCallBack.success(m);
                    }
                });
    }

    @JSMethod
    public void uploadData(JSONObject jsonObject, JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack){
        ModuleAdapterCallBack moduleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);
        getAPI(mWXSDKInstance.getContext()).uploadData(UserName
                , new WSUploadCallback() {

                    @Override
                    public void onFailure(UploadStatusModel uploadStatusModel, String Message, int StatusCode, Exception exception) {
                        Map m = new HashMap();
                        m.put("message", Message);
                        m.put("StatusCode", StatusCode);
                        m.put("exception", exception.getMessage());
                        m.put("uploadStatusModel",JSON.toJSON(uploadStatusModel));
                        moduleAdapterCallBack.error(m);
                    }

                    @Override
                    public void onSuccess(UploadStatusModel uploadStatusModel, HttpModel httpModel) {
                        Map m = new HashMap();
                        m.put("uploadStatusModel",JSON.toJSON(uploadStatusModel));
                        m.put("httpModel",JSON.toJSON(httpModel));
                        m.put("status","onSuccess");
                        moduleAdapterCallBack.successKeepAlive(m);
                    }

                    @Override
                    public void onProgress(long Left, String MACAddress, String Message) {
                        Map m = new HashMap();
                        m.put("left", Left);
                        m.put("status", "onProgress");
                        m.put("MACAddress", MACAddress);
                        m.put("Message", Message);
                        moduleAdapterCallBack.successKeepAlive(m);
                    }

                    @Override
                    public void onAllDataUploaded() {
                        Map m = new HashMap();
                        m.put("status", "onAllDataUploaded");
                        moduleAdapterCallBack.successKeepAlive(m);
                    }
                });
    }

    @JSMethod
    public void downloadData(JSONObject jsonObject, JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack){
        ModuleAdapterCallBack moduleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);
        InsigmaSmartDevice insigmaSmartDevice = new InsigmaSmartDevice(mWXSDKInstance.getContext(), smartDevices.get(jsonObject.getIntValue("index"))
                , new AdapterSmartCallback(moduleAdapterCallBack,
                new SmartTagToolsModule.SmartConnectCallback() {
                    @Override
                    public void onDeviceConnected() {

                    }

                    @Override
                    public void onDeviceDisconnected() {

                    }

                    @Override
                    public void onDataProgress(int currentIndex, int totalCount) {

                    }

                    @Override
                    public void onDataDownloaded(boolean b, ArrayList<BLETagModel> arrayList) {
                        uploadData(jsonObject,moduleAdapterCallBack);
                    }
                },
                mWXSDKInstance.getContext()));
        if(!insigmaSmartDevice.isDisconnected()){
            insigmaSmartDevice.connectDevice();
            return;
        }
        insigmaSmartDevice.downloadData();
    }

    @JSMethod
    public void connectDevice(JSONObject jsonObject, JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack) {
        ModuleAdapterCallBack moduleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);
        InsigmaSmartDevice insigmaSmartDevice = new InsigmaSmartDevice(mWXSDKInstance.getContext(), smartDevices.get(jsonObject.getIntValue("index"))
                , new AdapterSmartCallback(moduleAdapterCallBack, mWXSDKInstance.getContext()));
        insigmaSmartDevice.connectDevice();
    }

    @JSMethod
    public void doAssociationFactory(JSONObject jsonObject, JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack) {
        ModuleAdapterCallBack moduleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);

        Timer timer = new Timer();

        try {
            insigmaBluetoothManager = new InsigmaBluetoothManager(mWXSDKInstance.getContext().getApplicationContext(),
                    new ScannerCallback() {
                        @Override
                        public void onDeviceFound(BluetoothLeScanner bluetoothLeScanner, BluetoothLeDeviceStore bluetoothLeDeviceStore, SmartDevice smartDevice, Context context, boolean b, SmartDeviceModel smartDeviceModel) {
                            Map m = new HashMap();
                            m.put("type", "onDeviceFound");
//                            m.put("device", JSON.toJSON(smartDeviceModel));
//                            m.put("device", JSON.toJSON(smartDevice));
                            if(smartDevice.getSerialNumber().equals(jsonObject.getString("smartDeviceSN"))&&smartDevice.isDoorOpen()){
                                insigmaBluetoothManager.stopScan();
                                timer.cancel();
                                moduleAdapterCallBack.error("Door Opened");
                            }
//                            JSONObject map = new JSONObject();
//                            try {
//                                map.put("IBeaconUUID",smartDevice.getIbeaconUUID());
//                                map.put("BatteryLevel",smartDevice.getBatteryLevel());
//                                map.put("CoolerID",smartDevice.getCoolerId());
//                                map.put("SerialNumber",smartDevice.getSerialNumber());
//                                map.put("Distance",smartDevice.getDistanceInMeter(mWXSDKInstance.getContext()));
//                                map.put("DistanceInMM",smartDevice.getSmartShelfDistanceInMM());
//                                map.put("DistanceRange",smartDevice.getRSSIRange(smartDevice.getDistanceInMeter(mWXSDKInstance.getContext())));
//                                map.put("RunningAverageRssiAccurate",smartDevice.getRunningAverageRssiAccurate());
//                                map.put("macAddress",smartDevice.getAddress());
//                                map.put("Rssi",smartDevice.getRssi());
//                                map.put("Name",smartDevice.getDevice().getName());
//                                map.put("DeviceType",smartDevice.getDeviceTypeName());
//                                map.put("DeviceTypeId",smartDevice.getDeviceTypeId());
//                                map.put("isDoorOpen",smartDevice.isDoorOpen());
//                                map.put("isMultiDoorEnable",smartDevice.isMultiDoorEnable());
//                                map.put("isDoorTimeout",smartDevice.isDoorTimeout());
//                                map.put("SmartDoorCount",smartDevice.getSmartDoorCount());
//                                map.put("isDoor2Open",smartDevice.isDoor2Open());
//                                map.put("isDoor2Timeout",smartDevice.isDoor2Timeout());
//                                map.put("isDoor3Open",smartDevice.isDoor3Open());
//                                map.put("isDoor3Timeout",smartDevice.isDoor3Timeout());

//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                            m.put("device", map);
//                            moduleAdapterCallBack.successKeepAlive(m);
                        }

                        @Override
                        public void onScanFinished(BluetoothLeScanner bluetoothLeScanner, BluetoothLeDeviceStore bluetoothLeDeviceStore, Context context, boolean b) {
//                            Map m = new HashMap();
//                            m.put("type", "onScanFinished");
//                            m.put("devices", JSONArray.parseArray(JSON.toJSONString(bluetoothLeDeviceStore.getDeviceList())));
//                            m.put("devices",  new JSONArray(new ArrayList<Object>(bluetoothLeDeviceStore.getDeviceList())));
//                            smartDevices = bluetoothLeDeviceStore.getDeviceList();
//                            moduleAdapterCallBack.success(m);
                        }

                        @Override
                        public void onScanFailed(int i) {
//                            Map m = new HashMap();
//                            m.put("type", "onScanFailed");
//                            m.put("code", i);
//                            moduleAdapterCallBack.error(m);
                        }
                    });
            final boolean mIsBluetoothOn = insigmaBluetoothManager.isBluetoothON();
            final boolean mIsBluetoothLePresent = insigmaBluetoothManager.isBluetoothLeSupported();

            insigmaBluetoothManager.askUserToEnableBluetoothIfNeeded((Activity) mWXSDKInstance.getContext());
            if (!mIsBluetoothOn || !mIsBluetoothLePresent) {
                moduleAdapterCallBack.error("IsBluetoothOn or IsBluetoothLePresent?");
                return;
            }
            insigmaBluetoothManager.startScan();

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if(insigmaBluetoothManager !=null){
                        insigmaBluetoothManager.stopScan();
                        doAssociation(jsonObject,moduleAdapterCallBack);
                    }
                }
            },jsonObject.getLongValue("timeout"));

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void doAssociation(JSONObject jsonObject,ModuleAdapterCallBack moduleAdapterCallBack){
        getAPI(mWXSDKInstance.getContext()).doAssociation(UserName, jsonObject.getString("coolerSN"), jsonObject.getString("deviceMacAddress")
                , new WSAssociationCallback() {
                    @Override
                    public void onSuccess(AssociationModel associationModel) {
                        Map m = new HashMap();
                        m.put("message", associationModel.getMessage());
                        m.put("success", associationModel.isSuccess());
                        m.put("status", "onSucess");
                        moduleAdapterCallBack.success(m);
                    }

                    @Override
                    public void onFailure(String s, int i, Exception e) {
                        Map m = new HashMap();
                        m.put("message", s);
                        m.put("code", i);
                        m.put("exception", e.getMessage());
                        m.put("status", "onError");
                        moduleAdapterCallBack.success(m);
                    }
                });
    }

    @JSMethod
    public void removeAssociationFactory(JSONObject jsonObject, JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack) {
        ModuleAdapterCallBack moduleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);
        insigmaBluetoothManager = new InsigmaBluetoothManager(mWXSDKInstance.getContext().getApplicationContext(),
                new ScannerCallback() {
                    @Override
                    public void onDeviceFound(BluetoothLeScanner bluetoothLeScanner, BluetoothLeDeviceStore bluetoothLeDeviceStore, SmartDevice smartDevice, Context context, boolean b, SmartDeviceModel smartDeviceModel) {
                        Map m = new HashMap();
                        m.put("type", "onDeviceFound");
                        if(jsonObject.containsKey("smartDeviceSN")&&jsonObject.getString("smartDeviceSN").equals(smartDevice.getSerialNumber())){
                            insigmaBluetoothManager.stopScan();
                            // connectDevice(jsonObject,smartDevice,moduleAdapterCallBack);
                        }
                    }

                    @Override
                    public void onScanFinished(BluetoothLeScanner bluetoothLeScanner, BluetoothLeDeviceStore bluetoothLeDeviceStore, Context context, boolean b) {
                        Map m = new HashMap();
                        m.put("type", "onScanFinished");
//                            m.put("devices", JSONArray.parseArray(JSON.toJSONString(bluetoothLeDeviceStore.getDeviceList())));
                        m.put("devices",  new JSONArray(new ArrayList<Object>(bluetoothLeDeviceStore.getDeviceList())));
                        smartDevices = bluetoothLeDeviceStore.getDeviceList();
                        moduleAdapterCallBack.success(m);


                        getAPI(mWXSDKInstance.getContext()).getDeviceWhiteListData(UserName, smartDevices.get(0).getSerialNumber(), new WSStringCallback() {
                            @Override
                            public void onSuccess(HttpModel result) {
                                ToastUtil.showLongToast(mWXSDKInstance.getContext(), "onSuccess");
                                connectDevice(jsonObject,smartDevices.get(0),moduleAdapterCallBack);
                            }

                            @Override
                            public void onFailure(String Message, int StatusCode, Exception exception) {
                                ToastUtil.showLongToast(mWXSDKInstance.getContext(), "onFailure");
                            }
                        });
                    }

                    @Override
                    public void onScanFailed(int i) {
                        Map m = new HashMap();
                        m.put("type", "onScanFailed");
                        m.put("code", i);
                        moduleAdapterCallBack.error(m);
                        insigmaBluetoothManager.stopScan();
                    }
                });
        final boolean mIsBluetoothOn = insigmaBluetoothManager.isBluetoothON();
        final boolean mIsBluetoothLePresent = insigmaBluetoothManager.isBluetoothLeSupported();

        insigmaBluetoothManager.askUserToEnableBluetoothIfNeeded((Activity) mWXSDKInstance.getContext());
        if (!mIsBluetoothOn || !mIsBluetoothLePresent) {
            moduleAdapterCallBack.error("IsBluetoothOn or IsBluetoothLePresent?");
            return;
        }
        insigmaBluetoothManager.startScan();
    }
    public void connectDevice(JSONObject jsonObject,SmartDevice smartDevice,ModuleAdapterCallBack moduleAdapterCallBack){
        InsigmaSmartDevice insigmaSmartDevice = new InsigmaSmartDevice(mWXSDKInstance.getContext(), smartDevice, new AdapterSmartCallback(
                new SmartTagToolsModule.SmartConnectCallback() {
                    @Override
                    public void onDeviceConnected() {
                        downLoadData(jsonObject,smartDevice,moduleAdapterCallBack);
                    }

                    @Override
                    public void onDeviceDisconnected() {

                    }

                    @Override
                    public void onDataProgress(int currentIndex, int totalCount) {

                    }

                    @Override
                    public void onDataDownloaded(boolean b, ArrayList<BLETagModel> arrayList) {

                    }
                }
        , mWXSDKInstance.getContext()));
        if (insigmaSmartDevice.isDisconnected()) {
            insigmaSmartDevice.connectDevice();
        }
    }
    public void downLoadData(JSONObject jsonObject,SmartDevice smartDevice,ModuleAdapterCallBack moduleAdapterCallBack){
        InsigmaSmartDevice insigmaSmartDevice = new InsigmaSmartDevice(mWXSDKInstance.getContext(), smartDevice, new AdapterSmartCallback(new SmartTagToolsModule.SmartConnectCallback() {
            @Override
            public void onDeviceConnected() {

            }

            @Override
            public void onDeviceDisconnected() {

            }

            @Override
            public void onDataProgress(int currentIndex, int totalCount) {
                Map m = new HashMap();
                m.put("status","onProgress");
                m.put("progress",currentIndex);
                m.put("total",totalCount);
                moduleAdapterCallBack.successKeepAlive(m);
            }

            @Override
            public void onDataDownloaded(boolean b, ArrayList<BLETagModel> arrayList) {
                uploadData(jsonObject,moduleAdapterCallBack);
            }
        }, mWXSDKInstance.getContext()));
        insigmaSmartDevice.downloadData();
    }
    public void uploadData(JSONObject jsonObject,ModuleAdapterCallBack moduleAdapterCallBack){
        getAPI(mWXSDKInstance.getContext()).uploadData(UserName
                , new WSUploadCallback() {

                    @Override
                    public void onFailure(UploadStatusModel uploadStatusModel, String Message, int StatusCode, Exception exception) {
                        Map m = new HashMap();
                        m.put("message", Message);
                        m.put("StatusCode", StatusCode);
                        m.put("exception", exception.getMessage());
                        m.put("uploadStatusModel",JSON.toJSON(uploadStatusModel));
                        m.put("statuc","onError");
                        moduleAdapterCallBack.error(m);
                    }

                    @Override
                    public void onSuccess(UploadStatusModel uploadStatusModel, HttpModel httpModel) {
                        Map m = new HashMap();
                        m.put("uploadStatusModel",JSON.toJSON(uploadStatusModel));
                        m.put("httpModel",JSON.toJSON(httpModel));
                        m.put("status","onSuccess");
//                        moduleAdapterCallBack.successKeepAlive(m);
                        removeAssociation(jsonObject,moduleAdapterCallBack);
                    }

                    @Override
                    public void onProgress(long Left, String MACAddress, String Message) {
                        Map m = new HashMap();
                        m.put("left", Left);
//                        m.put("status", "onProgress");
                        m.put("MACAddress", MACAddress);
                        m.put("Message", Message);
                        moduleAdapterCallBack.successKeepAlive(m);
                    }

                    @Override
                    public void onAllDataUploaded() {
                        Map m = new HashMap();
                        m.put("status", "onAllDataUploaded");
                        moduleAdapterCallBack.successKeepAlive(m);
                    }
                });
    }
    public void removeAssociation(JSONObject jsonObject,ModuleAdapterCallBack moduleAdapterCallBack){
        getAPI(mWXSDKInstance.getContext()).removeAssociation(UserName, jsonObject.getString("coolerSN"), mSmartDevice.getAddress()
                , new WSRemoveAssociationCallback() {
                    @Override
                    public void onSuccess(RemoveAssociationModel removeAssociationModel) {
                        Map m = new HashMap();
                        m.put("message", removeAssociationModel.getMessage());
                        m.put("success", removeAssociationModel.isSuccess());
                        m.put("status", "onSuccess");
                        moduleAdapterCallBack.success(m);
                    }

                    @Override
                    public void onFailure(String s, int i, Exception e) {
                        Map m = new HashMap();
                        m.put("message", s);
                        m.put("code", i);
                        m.put("exception", e.getMessage());
                        m.put("status", "onError");
                        moduleAdapterCallBack.error(m);
                    }
                });
    }
}
