package com.weexbox.example.modules;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothClass;
import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
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
import com.lelibrary.androidlelibrary.sdk.callback.WSStringCallback;
import com.lelibrary.androidlelibrary.sdk.callback.WSStringProgressCallback;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.weex.weexextra.ModuleAdapterCallBack;
import com.weexbox.core.util.ToastUtil;

import java.io.ByteArrayOutputStream;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import static com.litesuits.common.utils.HandlerUtil.runOnUiThread;

public class SmartTagModule extends WXModule
{
    private SmartTagFactory smartTagFactory = null;
    @JSMethod(uiThread = false)
    public void init(JSONObject optionObj,JSCallback successCallBack, JSCallback errorCallBack){
        ModuleAdapterCallBack moduleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack);
        if(optionObj==null) moduleAdapterCallBack.error("初始化参数不能为空");
        else {
            String _apIkey = optionObj.getString("APIkey");
            String _userName = optionObj.getString("UserName");
            Integer _server_Index = optionObj.getInteger("Server_Index");
            if (_userName != null && _userName.length() > 0 && _apIkey != null && _apIkey.length() > 0 && _server_Index != null) {
                smartTagFactory = new SmartTagFactory(_userName, _apIkey, _server_Index, mWXSDKInstance.getContext());
                moduleAdapterCallBack.success("");
            } else {
                moduleAdapterCallBack.error("初始化参数填写错误，请重新填写");
            }
        }
    }

    @JSMethod(uiThread = false)
    public void doAssociationFactory(JSONObject optionsObj, JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack) {
        ModuleAdapterCallBack moduleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);
        String _CoolerSN = optionsObj.getString("CoolerSN");
        String _smartDeviceSN = optionsObj.getString("smartDeviceSN");
        if(_CoolerSN.isEmpty() || _smartDeviceSN.isEmpty()){
            moduleAdapterCallBack.error("资产编号或SmartTag编号不能为空,请检查后再试");
            return;
        }
        if(smartTagFactory!=null){
            smartTagFactory.startScan(mWXSDKInstance.getContext(),new SmartTagCallback(){

                @Override
                public void onDeviceFound(SmartDevice var3, SmartDeviceModel var6) {
                    smartTagFactory.updateDevice(var3,_smartDeviceSN);

                }

                @Override
                public void onScanFinished(BluetoothLeDeviceStore var2) {

                }

                @Override
                public void onScanFailed(int var1) {

                }

                @Override
                public void onError(String var1) {
                    moduleAdapterCallBack.error(var1);
                }
            });
        }else{
            moduleAdapterCallBack.error("没有正确的初始化，请先初始化配置");
        }
    }

    @JSMethod(uiThread = false)
    public void removeAssociationFactory(JSONObject optionsObj, JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack) {
        ModuleAdapterCallBack moduleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);
        String _CoolerSN = optionsObj.getString("CoolerSN");
        String _smartDeviceSN = optionsObj.getString("smartDeviceSN");
        if(_CoolerSN.isEmpty() || _smartDeviceSN.isEmpty()){
            moduleAdapterCallBack.error("资产编号或SmartTag编号不能为空,请检查后再试");
            return;
        }
        if(smartTagFactory!=null){
            if(smartTagFactory.insigmaSmartDevice!=null) {
                smartTagFactory.insigmaSmartDevice.disconnectDevice();
            }
            smartTagFactory.startScan(mWXSDKInstance.getContext(),new SmartTagCallback(){
                @Override
                public void onDeviceFound(SmartDevice var3, SmartDeviceModel var6) {
                    ArrayList<SmartDevice> devices = smartTagFactory.updateDevice(var3,_smartDeviceSN);
                    if(devices.size()>0 && smartTagFactory.getScanState()==1){
                        smartTagFactory.stopScan();
                        smartTagFactory.connect(devices.get(0), new SmartConnectCallback() {
                            @Override
                            public void onDeviceConnected() {
                                Map m = new HashMap();
                                JSONObject jdata = new JSONObject();
                                m.put("status", "onProgress");
                                try {

                                    jdata.put("message","已连接...");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                m.put("progress", jdata);
                                moduleAdapterCallBack.successKeepAlive(m);
                                download(moduleAdapterCallBack);
                            }

                            @Override
                            public void onDeviceDisconnected() {
                                Map m = new HashMap();
                                JSONObject jdata = new JSONObject();
                                m.put("status", "onProgress");
                                try {

                                    jdata.put("message","已断开...");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                m.put("progress", jdata);
                                moduleAdapterCallBack.successKeepAlive(m);
                            }

                            @Override
                            public void onDataProgress(int currentIndex, int totalCount) {
                                // 上传进度条
                                Map m = new HashMap();
                                JSONObject jdata = new JSONObject();
                                m.put("status", "onProgress");
                                try {
                                    jdata.put("total",totalCount);
                                    jdata.put("current",currentIndex);
                                    jdata.put("message","下载数据...");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                m.put("progress", jdata);
                                moduleAdapterCallBack.successKeepAlive(m);
                            }

                            @Override
                            public void onDataDownloaded(boolean isSuccess, ArrayList<BLETagModel> dataList) {
                                if(isSuccess && dataList != null){
                                    uploadData(moduleAdapterCallBack);
                                }else{
                                    Map m = new HashMap();
                                    JSONObject jdata = new JSONObject();
                                    m.put("status", "onProgress");
                                    try {
                                        jdata.put("message","Data Download Fail OR Data Not Available...");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    m.put("progress", jdata);
                                    moduleAdapterCallBack.successKeepAlive(m);
                                }
                            }
                        });
                    }
                }

                @Override
                public void onScanFinished(BluetoothLeDeviceStore var2) {

                }

                @Override
                public void onScanFailed(int var1) {

                }

                @Override
                public void onError(String var1) {
                    moduleAdapterCallBack.error(var1);
                }
            });
        }else{
            moduleAdapterCallBack.error("没有正确的初始化，请先初始化配置");
        }
    }

    private void download(ModuleAdapterCallBack mAdaptercb)
    {
        if (smartTagFactory.insigmaSmartDevice != null && smartTagFactory.insigmaSmartDevice.isDisconnected()) {
            mAdaptercb.error("Device Disconnected, Please Reconnect");
            return;
        }else{
            smartTagFactory.insigmaSmartDevice.downloadData();
        }
    }

    private void uploadData(ModuleAdapterCallBack mAdaptercb){
        if(smartTagFactory.smartServerAPI.isDataAvailableForUpload()){
            smartTagFactory.smartServerAPI.uploadDataUploadDownloadLog(smartTagFactory._userName, new WSStringProgressCallback() {
                long total = 0;
                @Override
                public void onProgress(long l, String s) {
                    // 上传进度条
                    Map m = new HashMap();
                    JSONObject jdata = new JSONObject();
                    if(total==0) total = l;
                    m.put("status", "onProgress");
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
                public void onSuccess(HttpModel httpModel) {
                    total = 0;
                    //上传完成
                }

                @Override
                public void onFailure(String s, int i, Exception e) {
                    total = 0;
                    mAdaptercb.error(s);
                    //上传失败
                }
            });
        }
    }


    static class SmartTagFactory implements ScannerCallback
    {
        private static String _userName = null;
        private static SmartServerAPI smartServerAPI = null;
        private static InsigmaBluetoothManager insigmaBluetoothManager = null;
        private static int _scanState = 0;
        private Context context = null;
        private SmartTagCallback mSmartInterface;
        SmartTagFactory(String userName,String apiKey,Integer server_Index,Context _context){
            context = _context;
            smartServerAPI = getInstanceSmartAPI(context);
            smartServerAPI.setAPIKey(apiKey);
            smartServerAPI.setAccessURL(server_Index);
            smartServerAPI.setServerTimeoutInterval(180);
            _userName = userName;

            insigmaBluetoothManager = getBluetoothManager(context);
            insigmaBluetoothManager.setTimeoutInterval(30);
            insigmaBluetoothManager.setCalibratedTXPower(-59);
        }

        private SmartServerAPI getInstanceSmartAPI(Context context){
            if(smartServerAPI == null){
                smartServerAPI = new SmartServerAPI(context.getApplicationContext());
            }
            return smartServerAPI;
        }

        private InsigmaBluetoothManager getBluetoothManager(Context context){
            if(insigmaBluetoothManager == null){
                insigmaBluetoothManager = new InsigmaBluetoothManager(context,this);
            }
            return insigmaBluetoothManager;
        }

        private void startScan(Context context,SmartTagCallback mSmartInterface){
            try {
                _scanState = 1;
                this.mSmartInterface = mSmartInterface;
                final boolean mIsBluetoothOn = insigmaBluetoothManager.isBluetoothON();
                final boolean mIsBluetoothLePresent = insigmaBluetoothManager.isBluetoothLeSupported();
                insigmaBluetoothManager.askUserToEnableBluetoothIfNeeded((Activity) context);
                if (!mIsBluetoothOn || !mIsBluetoothLePresent) {
                    if(mSmartInterface!=null)mSmartInterface.onError("IsBluetoothOn or IsBluetoothLePresent?");
                    return;
                }
                insigmaBluetoothManager.startScan();
            }catch (Exception e){
                stopScan();
                mSmartInterface.onError(e.getMessage());
            }
        }

        private void stopScan(){
            _scanState = 0;
            if(insigmaBluetoothManager!=null){
                insigmaBluetoothManager.stopScan();
            }
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
                        //下载下来的数据
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
                            insigmaSmartDevice.connectDevice();
                        }

                        @Override
                        public void onFailure(String s, int i, Exception e) {

                        }
                    });
                }
                else{
                    insigmaSmartDevice.connectDevice();
                }
            } else {
                //CommonUtils.showAlertDialog(this, "Device Already Connected", null, false);
            }
        }

        private int getScanState(){
            return  _scanState;
        }

        private ArrayList<SmartDevice> _devices = new ArrayList<SmartDevice>();
        private JSONObject _deviceSN = new JSONObject();
        private ArrayList<SmartDevice> updateDevice(SmartDevice device) {
            return  updateDevice(device,null);
        }
        private ArrayList<SmartDevice> updateDevice(SmartDevice device,String smartDeviceSN){
            if(smartDeviceSN!=null && !device.getSerialNumber().equals(smartDeviceSN))
            {
                return _devices;
            }else{
                if(_deviceSN.containsKey(device.getSerialNumber())){
                    _devices.set(_deviceSN.getInteger(device.getSerialNumber()),device);
                }else {
                    _deviceSN.put(device.getSerialNumber(),_devices.size());
                    _devices.add(device);
                }
            }
            return _devices;
        }

        private void clearAll(){
            _devices.clear();
            _deviceSN.clear();
        }

        @Override
        public void onDeviceFound(BluetoothLeScanner bluetoothLeScanner, BluetoothLeDeviceStore bluetoothLeDeviceStore, SmartDevice smartDevice, Context context, boolean b, SmartDeviceModel smartDeviceModel) {
            if(mSmartInterface!=null)mSmartInterface.onDeviceFound(smartDevice,smartDeviceModel);
        }

        @Override
        public void onScanFinished(BluetoothLeScanner bluetoothLeScanner, BluetoothLeDeviceStore bluetoothLeDeviceStore, Context context, boolean b) {
            if(mSmartInterface!=null)mSmartInterface.onScanFinished(bluetoothLeDeviceStore);
        }

        @Override
        public void onScanFailed(int i) {
            if(mSmartInterface!=null)mSmartInterface.onScanFailed(i);
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
                                    progressDialog.setMessage("上传中");
                                } else {
                                    progressDialog.setMessage(message);
                                }
                                progressDialog.setCancelable(false);
                                progressDialog.setCanceledOnTouchOutside(false);
                                progressDialog.show();
                            } else {
                                if (TextUtils.isEmpty(message)) {
                                    progressDialog.setMessage("请等。。");
                                } else {
                                    progressDialog.setMessage(message);
                                }
                            }
                        } else {
                            progressDialog = new ProgressDialog(context);
                            if (TextUtils.isEmpty(message)) {
                                progressDialog.setMessage("121212");
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
