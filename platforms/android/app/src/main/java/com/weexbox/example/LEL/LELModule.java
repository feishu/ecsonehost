package com.weexbox.example.LEL;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bugfender.sdk.MyBugfender;
import com.lelibrary.androidlelibrary.ble.BluetoothLeDeviceStore;
import com.lelibrary.androidlelibrary.ble.BluetoothLeScanner;
import com.lelibrary.androidlelibrary.ble.ScannerCallback;
import com.lelibrary.androidlelibrary.ble.SmartDevice;
import com.lelibrary.androidlelibrary.ble.SmartDeviceModel;
import com.lelibrary.androidlelibrary.sdk.InsigmaBluetoothManager;
import com.lelibrary.androidlelibrary.sdk.SmartServerAPI;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
//    private static InsigmaBluetoothManager insigmaBluetoothManager = null;
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

    @JSMethod
    public void startScan(JSONObject object, JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack) {
        ModuleAdapterCallBack moduleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);
        try {
            InsigmaBluetoothManager insigmaBluetoothManager = new InsigmaBluetoothManager(mWXSDKInstance.getContext().getApplicationContext(),
                    new ScannerCallback() {
                        @Override
                        public void onDeviceFound(BluetoothLeScanner bluetoothLeScanner, BluetoothLeDeviceStore bluetoothLeDeviceStore, SmartDevice smartDevice, Context context, boolean b, SmartDeviceModel smartDeviceModel) {
                            Map m = new HashMap();
                            m.put("type", "found");
                            m.put("device", JSON.toJSON(smartDeviceModel));
                            moduleAdapterCallBack.success(m);
                        }

                        @Override
                        public void onScanFinished(BluetoothLeScanner bluetoothLeScanner, BluetoothLeDeviceStore bluetoothLeDeviceStore, Context context, boolean b) {
                            Map m = new HashMap();
                            m.put("type", "finish");
//                            m.put("devices", JSONArray.parseArray(JSON.toJSONString(bluetoothLeDeviceStore.getDeviceList())));
                            m.put("devices",  new JSONArray(new ArrayList<Object>(bluetoothLeDeviceStore.getDeviceList())));
                            moduleAdapterCallBack.success(m);
                        }

                        @Override
                        public void onScanFailed(int i) {
                            Map m = new HashMap();
                            m.put("type", "fail");
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
    public void startScan(){

    }


}
