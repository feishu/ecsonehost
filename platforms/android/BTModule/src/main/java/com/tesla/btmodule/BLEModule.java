//package com.tesla.btmodule;
//
//import android.Manifest;
//import android.bluetooth.BluetoothAdapter;
//import android.bluetooth.BluetoothDevice;
//import android.bluetooth.BluetoothGatt;
//import android.bluetooth.BluetoothGattCallback;
//import android.bluetooth.BluetoothGattCharacteristic;
//import android.bluetooth.BluetoothManager;
//import android.bluetooth.BluetoothProfile;
//import android.bluetooth.BluetoothSocket;
//import android.bluetooth.le.BluetoothLeScanner;
//import android.bluetooth.le.ScanCallback;
//import android.bluetooth.le.ScanResult;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.os.Build;
//import android.support.annotation.NonNull;
//import android.support.annotation.RequiresApi;
//import android.text.TextUtils;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.alibaba.fastjson.JSONObject;
//import com.taobao.weex.annotation.JSMethod;
//import com.taobao.weex.bridge.JSCallback;
//import com.taobao.weex.common.WXModule;
//import com.taobao.weex.utils.WXLogUtils;
//import com.ucmed.mrdc.teslacore.module.TSLModuleAdapterCallBack;
//import com.ucmed.mrdc.teslacore.util.TSLObserver;
//import com.yanzhenjie.permission.AndPermission;
//import com.yanzhenjie.permission.PermissionListener;
//
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.UUID;
//
//import io.reactivex.Observable;
//import io.reactivex.ObservableSource;
//import io.reactivex.WEObserver;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.functions.Function;
//import io.reactivex.schedulers.Schedulers;
//
//import static com.tesla.btmodule.BlueToothModule.sendOrder;
//
//
///**
// * Created by WX-GXM-1326 on 2017/5/31.
// */
//@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//public class BLEModule extends WXModule {
//    public static JSCallback getdataback;
//    public static ArrayList<DeviceInfo> pairedDevices;
//    public static JSCallback status_change;
//    public static JSCallback connect_suc;
//    public static JSCallback connect_fail;
//    public static JSCallback recv_data;
//    public static JSCallback recv_fail;
//    /**
//     * **********************************************************************************
//     */
//    public static String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";
//    public final static UUID UUID_NOTIFY = UUID.fromString("0000ffe1-0000-1000-8000-00805f9b34fb");
//    public final static UUID UUID_SERVICE = UUID.fromString("0000ffe0-0000-1000-8000-00805f9b34fb");
//    private static ArrayList<DeviceInfo> mDeviceList;
//    private static ArrayList<BluetoothDevice> realDevices;
//    private static BluetoothAdapter mBtAdapter;
//    public String sDeviceID = "";
//    public JSCallback scan_found_device;
//    public JSCallback scan_complete;
//
//    static Boolean bConnect = true;
//    String strName = null;
//    static String strAddress = null;
//    byte[] bRecv = new byte[1024];
//    int nRecved = 0;
//    int nNeed = 0;
//    private static InputStream mmInStream;
//    private static OutputStream mmOutStream;
//    private static BluetoothSocket btSocket = null;
//
//    @JSMethod(uiThread = false)
//    public void openBluetoothAdapter(final JSCallback success, final JSCallback failure) {
//        if (pairedDevices == null) pairedDevices = new ArrayList<>();
//
//        AndPermission.with(mWXSDKInstance.getContext()).requestCode(100).permission(Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN/*,Manifest.permission.BLUETOOTH_PRIVILEGED*/
//                , Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
//                .callback(new PermissionListener() {
//                    @Override
//                    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
//
//                        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//                            mBtAdapter = BluetoothAdapter.getDefaultAdapter();
//                        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
//                            final BluetoothManager bluetoothManager = (BluetoothManager) mWXSDKInstance.getContext().getSystemService(Context.BLUETOOTH_SERVICE);
//                            mBtAdapter = bluetoothManager.getAdapter();
//                        }
//
//                        if (mBtAdapter != null) {
//                            if (!mBtAdapter.isEnabled()) {
//                                mBtAdapter.enable();
//                            }
//                        }
//
//                        setDiscoverableTimeout(3000);
//
//                        success.invoke(mBtAdapter.isEnabled());
//                    }
//
//                    @Override
//                    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
//                        failure.invoke(deniedPermissions);
//                    }
//                }).start();
//
//    }
//
//    @JSMethod(uiThread = false)
//    public void closeBluetoothAdapter(JSCallback success, JSCallback fail) {
//        try {
//            bConnect = false;
//            Thread.sleep(100);
//            if (mmInStream != null)
//                mmInStream.close();
//            if (mmOutStream != null)
//                mmOutStream.close();
//            if (btSocket != null)
//                btSocket.close();
//        } catch (Exception e) {
//            Log.e(Common.TAG, "Close error...");
//            e.printStackTrace();
//            if (fail != null) {
//                fail.invoke(e.toString());
//            }
//        }
////        }
//        if (mBtAdapter != null) {
//            mBtAdapter.cancelDiscovery();
//            mBtAdapter.disable();
//            mBtAdapter = null;
//        }
//        try {
//            mDeviceList.clear();
//        } catch (Exception e) {
//            if (fail != null) {
//                fail.invoke(e.toString());
//            }
//        }
//        if (success != null) {
//            success.invoke(null);
//        }
//    }
//
//    @JSMethod(uiThread = false)
//    public void getBluetoothAdapterState(JSCallback success) {
//        if (mBtAdapter != null) {
//            success.invoke(mBtAdapter.getState());
//        } else {
//            success.invoke(BluetoothAdapter.STATE_OFF);
//        }
//    }
//
//    @JSMethod(uiThread = false)
//    public void onBluetoothAdapterStateChange(JSCallback status) {
//        status_change = status;
//    }
//
//    @JSMethod(uiThread = false)
//    public void stopBluetoothDevicesDiscovery(JSCallback success, JSCallback fail) {
//        if (mBtAdapter != null&&mBtAdapter.isEnabled()/*&&mBtAdapter.isDiscovering()*/) {
//            mBtAdapter.getBluetoothLeScanner().stopScan(scanCallback);
//        }
//        success.invoke(true);
//
//    }
//
//    private ScanCallback scanCallback = new ScanCallback() {
//        @Override
//        public void onScanResult(int callbackType, ScanResult result) {
//            BluetoothDevice device = result.getDevice();
//            String name = device.getName();
//            if (!TextUtils.isEmpty(name)) {
//                if (!TextUtils.isEmpty(sDeviceID) && !name.contains(sDeviceID)) {
//                    return;
//                }
//                if (!IsDeviceAlreadyAdd(device.getAddress())) {
//                    DeviceInfo bt = new DeviceInfo();
//                    bt.name = name;
//                    bt.address = device.getAddress();
//                    mDeviceList.add(bt);
//                    realDevices.add(device);
//                }
//            }
//
//            if (scan_found_device != null) scan_found_device.invokeAndKeepAlive(mDeviceList);
//        }
//
//        @Override
//        public void onBatchScanResults(List<ScanResult> results) {
//            super.onBatchScanResults(results);
//        }
//
//        @Override
//        public void onScanFailed(int errorCode) {
//            if (scan_complete != null) scan_complete.invoke(mDeviceList);
//        }
//    };
//
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    @JSMethod(uiThread = false)
//    public void startBluetoothDevicesDiscovery(Map params, JSCallback found_device, JSCallback complete) {
//        if (params.containsKey("name")) {
//            sDeviceID = (String) params.get("name");
//        }
//        scan_found_device = found_device;
//        scan_complete = complete;
//        if (mBtAdapter.isDiscovering()) {
//            mBtAdapter.cancelDiscovery();
//        }
//        mDeviceList = new ArrayList<DeviceInfo>();
//        realDevices = new ArrayList<>();
//        mBtAdapter.getBluetoothLeScanner().startScan(scanCallback);
//    }
//
//    public void setDiscoverableTimeout(int timeout) {
//        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
//        try {
//            Method setDiscoverableTimeout = BluetoothAdapter.class.getMethod("setDiscoverableTimeout", int.class);
//            setDiscoverableTimeout.setAccessible(true);
//            Method setScanMode = BluetoothAdapter.class.getMethod("setScanMode", int.class, int.class);
//            setScanMode.setAccessible(true);
//
//            setDiscoverableTimeout.invoke(adapter, timeout);
//            setScanMode.invoke(adapter, BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE, timeout);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    static Boolean isConnecting = false;
//    static TSLModuleAdapterCallBack creatBTconnectionCallBack;
//
//    /**
//     * @param object  address ios以rssi作为参数 Android以mac地址作为参数
//     * @param success
//     * @param failure
//     */
//    @JSMethod(uiThread = false)
//    public void createBluetoothConnection(final JSONObject object, JSCallback success, JSCallback failure, JSCallback complete) {
//        creatBTconnectionCallBack = new TSLModuleAdapterCallBack(success, failure, complete) {
//            @Override
//            protected void invokeCallBack(Map map, JSCallback... callBacks) {
//                WXLogUtils.w(getClass().getSimpleName(), map.toString());
//                for (JSCallback callBack : callBacks) {
//                    if (callBack != null) {
//                        callBack.invokeAndKeepAlive(map);
//                    }
//                }
//            }
//        };
//
//        if (object.containsKey("address") && !TextUtils.isEmpty(object.getString("address"))) {
//            String mstrAddress = object.getString("address");
//
//            if (strAddress != null && mstrAddress.equals(strAddress) && bConnect) {
//                creatBTconnectionCallBack.success("true");
//                return;
//            }
//
//            if (isConnecting) {
//                creatBTconnectionCallBack.success("connecting");
//                return;
//            }
//
//            BluetoothDevice device= getBTDevice(mstrAddress);
//            if(device!=null){
//                device.connectGatt(mWXSDKInstance.getContext(), false, new BluetoothGattCallback() {
//
//                    @Override
//                    public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
//                        super.onConnectionStateChange(gatt, status, newState);
//                    }
//
//                    @Override
//                    public void onServicesDiscovered(BluetoothGatt gatt, int status) {
//                        super.onServicesDiscovered(gatt, status);
//                    }
//
//                    @Override
//                    public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
//                        super.onCharacteristicRead(gatt, characteristic, status);
//                    }
//
//                    @Override
//                    public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
//                        super.onCharacteristicWrite(gatt, characteristic, status);
//                    }
//
//                    @Override
//                    public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
//                        super.onCharacteristicChanged(gatt, characteristic);
//                    }
//                });
//            }else {
//                creatBTconnectionCallBack.error("wrong device");
//            }
//        }else {
//            creatBTconnectionCallBack.error("address should not be null");
//        }
//    }
//
//    private BluetoothDevice getBTDevice(String address){
//        for (BluetoothDevice device:realDevices) {
//            if(device.getAddress().equals(address)){
//                return device;
//            }
//        }
//        return null;
//    }
//
//    @JSMethod(uiThread = false)
//    public void writeBluetoothValue(final JSONObject object, final JSCallback success, final JSCallback failure, JSCallback complete) {
//        final TSLModuleAdapterCallBack tslModuleAdapterCallBack = new TSLModuleAdapterCallBack(success, failure, complete);
//
//        if (!bConnect) {
//            tslModuleAdapterCallBack.error("未连接设备");
//            return;
//        }
//
//        Observable.just(12).subscribeOn(Schedulers.io())
//                .flatMap(new Function<Integer, ObservableSource<String>>() {
//                    @Override
//                    public ObservableSource<String> apply(Integer integer) throws Exception {
//                        return new Observable<String>() {
//                            @Override
//                            protected void subscribeActual(WEObserver<? super String> observer) {
//                                try {
//                                    if (mmOutStream == null) {
//                                        observer.onError(new Throwable("未开启读写流"));
//                                        return;
//                                    }
//
//                                    int selectDirection = 0;
//                                    if (object.containsKey("selectDirection")) {
//                                        selectDirection = object.getIntValue("selectDirection");
//                                    }
//                                    String retStr = send(sendOrder(object.getString("order"), selectDirection));
//                                    Thread.sleep(500);
//                                    observer.onNext(retStr);
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                    observer.onError(e);
////                                    return;
//                                }
//                            }
//                        };
//                    }
//                }).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new TSLObserver<String>() {
//                    @Override
//                    public void onNext(String o) {
//                        if (o.equals("false")) {
//                            tslModuleAdapterCallBack.error("send fail");
//                        } else {
//                            tslModuleAdapterCallBack.success(o);
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        super.onError(e);
//                        tslModuleAdapterCallBack.error("send fail" + e.toString());
//                    }
//                });
//    }
//
//    static Boolean isReading = false;
//
//    @JSMethod(uiThread = false)
//    public void stopReadBluetoothValue(final JSCallback success) {
//        if (!isReading || !bConnect) {
//            if (success != null)
//                success.invoke(true);
//            return;
//        }
//        stopReadCB = success;
//        isReading = false;
//    }
//
//    static JSCallback stopReadCB;
//
//    @JSMethod(uiThread = false)
//    public void readBluetoothValue(final JSCallback success, final JSCallback failure) {
//
//        Observable.just(11).subscribeOn(Schedulers.io())
//                .flatMap(new Function<Integer, ObservableSource<String>>() {
//                    @Override
//                    public ObservableSource<String> apply(Integer integer) throws Exception {
//                        return new ObservableSource<String>() {
//                            @Override
//                            public void subscribe(WEObserver<? super String> observer) {
//                                isReading = true;
//                                byte[] bufRecv = new byte[1024];
//                                int nRecv = 0;
//                                while (bConnect && isReading) {
//                                    try {
//                                        Log.e(Common.TAG, "Start Recv" + String.valueOf(mmInStream.available()));
//                                        nRecv = mmInStream.read(bufRecv);
//                                        if (nRecv < 1) {
//                                            Log.e(Common.TAG, "Recving Short");
//                                            Thread.sleep(200);
//                                            continue;
//                                        }
//                                        byte[] bRecv = new byte[1024];
//                                        int nRecved = 0;
//                                        System.arraycopy(bufRecv, 0, bRecv, nRecved, nRecv);
//                                        Log.e(Common.TAG, "Recv:" + String.valueOf(nRecv));
//                                        nRecved += nRecv;
//                                        if (nRecved < nNeed) {
//                                            Thread.sleep(200);
//                                            continue;
//                                        }
//                                        String strRecv = bytesToString(bRecv, nRecved).trim();
//                                        Log.e(Common.TAG, "接收数据: " + strRecv);
////                                        if (strRecv.endsWith("\r\n") || strRecv.endsWith("E"))
//                                        observer.onNext(strRecv);
//                                        Thread.sleep(200);
//                                    } catch (Exception e) {
//                                        Log.e(Common.TAG, "Recv thread:" + e.getMessage());
//                                        observer.onError(e);
//                                        break;
//                                    }
//                                }
//                                if (stopReadCB != null)
//                                    stopReadCB.invoke(true);
//                            }
//                        };
//                    }
//                }).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new TSLObserver<String>() {
//                    @Override
//                    public void onNext(String o) {
//                        if (success != null) {
//                            success.invokeAndKeepAlive(o);
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        super.onError(e);
//                        if (failure != null) {
//                            failure.invokeAndKeepAlive("Recv thread:" + e.getMessage());
//                        }
//                    }
//                });
//
//    }
//
//    @JSMethod(uiThread = false)
//    public void closeBluetoothConnection(JSCallback success, JSCallback failure) {
//        if (bConnect) {
//            bConnect = false;
//            isReading = false;
//            try {
//                Thread.sleep(500);
//                if (mmInStream != null)
//                    mmInStream.close();
//                if (mmOutStream != null)
//                    mmOutStream.close();
//                if (btSocket != null)
//                    btSocket.close();
//            } catch (Exception e) {
//                Log.e(Common.TAG, "Close error...");
//                e.printStackTrace();
//            }
//        }
//        if (success != null)
//            success.invoke(true);
//    }
//
//    public String send(byte[] strValue) {
////        toast("指令为："+strValue,true);
//        if (!bConnect) {
//            return "false";
//        }
//        try {
//            if (mmOutStream == null)
//                return "false";
////            toast("发送指令："+strValue,true);
//            mmOutStream.write(strValue);
//            mmOutStream.flush();
//            if (mmInStream != null) {
//                Thread.sleep(100);
//                byte[] bufRecv = new byte[1024];
//                int nRecv = 0;
//                nRecv = mmInStream.read(bufRecv);
//                if (nRecv < 1) {
//                    Thread.sleep(100);
//                    mmOutStream.write(strValue);
//                    return "";
//                }
//                return new String(bufRecv);
//            }
//            return "";
//        } catch (final Exception e) {
//            e.printStackTrace();
////            toast("发送指令出错" + e.toString(), true);
//            return "false";
//        }
//    }
//
//    // 随机选取1-10
//    public int random() {
//        int number = (int) (1 + Math.random() * 10);
//        if (number == randomNumber) {
//            random();
//        } else {
//            randomNumber = number;
//        }
//        return randomNumber;
//    }
//
//    static int randomNumber = 0;
//
//    private Boolean IsDeviceAlreadyAdd(String btAddr) {
//        Boolean bAdded = false;
//        for (DeviceInfo bt : mDeviceList) {
//            if (btAddr.equals(bt.address)) {
//                bAdded = true;
//                break;
//            }
//        }
//        return bAdded;
//    }
//
//    public void addpaidedDevides() {
//        if (mBtAdapter != null) {
//            Set<BluetoothDevice> bondeddevices = mBtAdapter.getBondedDevices();
//            for (BluetoothDevice device : bondeddevices) {
//                if (!IsDeviceAlreadyAdd(device.getAddress())) {
//                    DeviceInfo bt = new DeviceInfo();
//                    bt.name = device.getName();
//                    bt.address = device.getAddress();
//                    mDeviceList.add(bt);
//                }
//            }
//        }
//    }
//
//    public void toast(String s) {
//        toast(s, false);
//    }
//
//    public void toast(final String s, boolean b) {
//        if (b)
//            mWXSDKInstance.runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    Toast.makeText(mWXSDKInstance.getContext(), s, Toast.LENGTH_SHORT).show();
//                }
//            });
//    }
//
//    public String bytesToString(byte[] b, int length) {
//        StringBuffer result = new StringBuffer("");
//        for (int i = 0; i < length; i++) {
//            result.append((char) (b[i]));
//        }
//
//        return result.toString();
//    }
//}
