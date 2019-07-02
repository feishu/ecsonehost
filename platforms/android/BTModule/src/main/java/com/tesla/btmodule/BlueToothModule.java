package com.tesla.btmodule;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.utils.WXLogUtils;
import com.weex.weexextra.ModuleAdapterCallBack;
import com.weex.weexextra.WEObserver;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by WX-GXM-1326 on 2017/5/31.
 */

public class BlueToothModule extends WXModule {

    public static ArrayList<DeviceInfo> pairedDevices;
    public static JSCallback status_change;
    /**
     * **********************************************************************************
     */
    public static String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";
    static Boolean bConnect = true;
    static String strAddress = null;
    static Boolean isConnecting = false;
    static ModuleAdapterCallBack creatBTconnectionCallBack;
    static Boolean isReading = false;
    static JSCallback stopReadCB;
    static int randomNumber = 0;
    private static ArrayList<DeviceInfo> mDeviceList;
    private static BluetoothAdapter mBtAdapter;
    private static InputStream mmInStream;
    private static OutputStream mmOutStream;
    private static BluetoothSocket btSocket = null;
    public String sDeviceID = "";
    public JSCallback scan_found_device;
    public JSCallback scan_complete;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent
                        .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String name = device.getName();
                if (!TextUtils.isEmpty(name)) {
                    if (!TextUtils.isEmpty(sDeviceID) && !name.contains(sDeviceID)) {
                        return;
                    }
                    if (!IsDeviceAlreadyAdd(device.getAddress())) {
                        DeviceInfo bt = new DeviceInfo();
                        bt.name = name;
                        bt.address = device.getAddress();
                        mDeviceList.add(bt);
                    }
                }

                if (scan_found_device != null) scan_found_device.invokeAndKeepAlive(mDeviceList);
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED
                    .equals(action)) {
                if (scan_complete != null) scan_complete.invoke(mDeviceList);
//                mWXSDKInstance.getContext().unregisterReceiver(mReceiver);
            } else if (action.equals(BluetoothDevice.ACTION_BOND_STATE_CHANGED)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String BondState = "";
                switch (device.getBondState()) {
                    case BluetoothDevice.BOND_NONE:
                        Log.e(mWXSDKInstance.getContext().getPackageName(), "取消配对");
                        BondState = "BOND_NONE";
                        break;
                    case BluetoothDevice.BOND_BONDING:
                        BondState = "BOND_BONDING";
                        Log.e(mWXSDKInstance.getContext().getPackageName(), "配对中");
                        break;
                    case BluetoothDevice.BOND_BONDED:
                        BondState = "BOND_BONDED";
                        Log.e(mWXSDKInstance.getContext().getPackageName(), "配对成功");
                        break;
                }
                if (status_change != null) {
                    Map map = new HashMap();
                    map.put("status", BondState);
                    map.put("name", device.getName());
                    map.put("address", device.getAddress());
                    map.put("uuids", device.getUuids());
                    status_change.invokeAndKeepAlive(map);
                }
            } else if (action.equals(BluetoothDevice.ACTION_ACL_CONNECTED)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (status_change != null) {
                    Map map = new HashMap();
                    map.put("status", "CONNECTED");
                    map.put("name", device.getName());
                    map.put("address", device.getAddress());
                    map.put("uuids", device.getUuids());
                    status_change.invokeAndKeepAlive(map);
                }
            } else if (action.equals(BluetoothDevice.ACTION_ACL_DISCONNECTED)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (status_change != null) {
                    Map map = new HashMap();
                    map.put("status", "DISCONNECTED");
                    map.put("name", device.getName());
                    map.put("address", device.getAddress());
                    map.put("uuids", device.getUuids());
                    status_change.invokeAndKeepAlive(map);
                }
            }
        }
    };
    int nNeed = 0;

    @JSMethod(uiThread = false)
    public void openBluetoothAdapter(final JSCallback success, final JSCallback failure) {
        if (pairedDevices == null) pairedDevices = new ArrayList<>();

        AndPermission.with(mWXSDKInstance.getContext()).runtime().permission(Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN/*,Manifest.permission.BLUETOOTH_PRIVILEGED*/
                , Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> deniedPermissions) {
                        failure.invoke(deniedPermissions);
                    }
                }).onGranted(new Action<List<String>>() {
            @Override
            public void onAction(List<String> grantPermissions) {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    mBtAdapter = BluetoothAdapter.getDefaultAdapter();
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    final BluetoothManager bluetoothManager = (BluetoothManager) mWXSDKInstance.getContext().getSystemService(Context.BLUETOOTH_SERVICE);
                    mBtAdapter = bluetoothManager.getAdapter();
                }

                if (mBtAdapter != null) {
                    if (!mBtAdapter.isEnabled()) {
                        mBtAdapter.enable();
                    }
                }

                setDiscoverableTimeout(3000);

                success.invoke(mBtAdapter.isEnabled());
            }
        }).start();
    }

    @JSMethod(uiThread = false)
    public void closeBluetoothAdapter(JSCallback success, JSCallback fail) {
//        if (bConnect) {
//            bConnect = false;

        try {
            bConnect = false;
            Thread.sleep(100);
            if (mmInStream != null)
                mmInStream.close();
            if (mmOutStream != null)
                mmOutStream.close();
            if (btSocket != null)
                btSocket.close();
        } catch (Exception e) {
            Log.e(Common.TAG, "Close error...");
            e.printStackTrace();
            if (fail != null) {
                fail.invoke(e.toString());
            }
        }
//        }
        if (mBtAdapter != null) {
            mBtAdapter.cancelDiscovery();
            mBtAdapter.disable();
            mBtAdapter = null;
        }
        try {
            mWXSDKInstance.getContext().unregisterReceiver(mReceiver);
            mDeviceList.clear();
        } catch (Exception e) {
            if (fail != null) {
                fail.invoke(e.toString());
            }
        }
        if (success != null) {
            success.invoke(null);
        }
    }

    @JSMethod(uiThread = false)
    public void getBluetoothAdapterState(JSCallback success) {
        if (mBtAdapter != null) {
            success.invoke(mBtAdapter.getState());
        } else {
            success.invoke(BluetoothAdapter.STATE_OFF);
        }
    }

    @JSMethod(uiThread = false)
    public void getConnectedDevice(final JSCallback success, JSCallback fail) {
        int a2dp = mBtAdapter.getProfileConnectionState(BluetoothProfile.A2DP);
        int headset = mBtAdapter.getProfileConnectionState(BluetoothProfile.HEADSET);
        int health = mBtAdapter.getProfileConnectionState(BluetoothProfile.HEALTH);
        int flag = -1;
        if (a2dp == BluetoothProfile.STATE_CONNECTED) {
            flag = a2dp;
        } else if (headset == BluetoothProfile.STATE_CONNECTED) {
            flag = headset;
        } else if (health == BluetoothProfile.STATE_CONNECTED) {
            flag = health;
        }

        if (flag != -1) {
            mBtAdapter.getProfileProxy(mWXSDKInstance.getContext(), new BluetoothProfile.ServiceListener() {
                @Override
                public void onServiceDisconnected(int profile) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onServiceConnected(int profile, BluetoothProfile proxy) {
                    // TODO Auto-generated method stub
                    List<BluetoothDevice> mDevices = proxy.getConnectedDevices();
                    List<Map> list = new ArrayList<>();
                    if (mDevices != null && mDevices.size() > 0) {
                        for (BluetoothDevice device : mDevices) {
                            Log.i("W", "device name: " + device.getName());
                            Map map = new HashMap();
                            map.put("name", device.getName());
                            map.put("address", device.getAddress());
                            list.add(map);
                        }
                        success.invoke(list);
                    } else {
                        Log.i("W", "mDevices is null");
                        success.invoke(list);
                    }
                }
            }, flag);
        }
    }

    @JSMethod(uiThread = false)
    public void onBluetoothAdapterStateChange(JSCallback status) {
        status_change = status;
    }

    @JSMethod(uiThread = false)
    public void stopBluetoothDevicesDiscovery(JSCallback success, JSCallback fail) {
        if (mBtAdapter != null) {
            success.invoke(mBtAdapter.cancelDiscovery());
        } else {
            success.invoke(true);
        }
    }

    @JSMethod(uiThread = false)
    public void startBluetoothDevicesDiscovery(Map params, JSCallback found_device, JSCallback complete) {
        if (params.containsKey("name")) {
            sDeviceID = (String) params.get("name");
        }
        scan_found_device = found_device;
        scan_complete = complete;

        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        mWXSDKInstance.getContext().registerReceiver(mReceiver, filter);

        if (mBtAdapter.isDiscovering()) {
            mBtAdapter.cancelDiscovery();
        }
        mDeviceList = new ArrayList<DeviceInfo>();

        mBtAdapter.startDiscovery();
    }

    public void setDiscoverableTimeout(int timeout) {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        try {
            Method setDiscoverableTimeout = BluetoothAdapter.class.getMethod("setDiscoverableTimeout", int.class);
            setDiscoverableTimeout.setAccessible(true);
            Method setScanMode = BluetoothAdapter.class.getMethod("setScanMode", int.class, int.class);
            setScanMode.setAccessible(true);

            setDiscoverableTimeout.invoke(adapter, timeout);
            setScanMode.invoke(adapter, BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE, timeout);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param object  address ios以rssi作为参数 Android以mac地址作为参数
     * @param success
     * @param failure
     */
    @JSMethod(uiThread = false)
    public void createBluetoothConnection(final JSONObject object, JSCallback success, JSCallback failure, JSCallback complete) {
        creatBTconnectionCallBack = new ModuleAdapterCallBack(success, failure, complete) {
            @Override
            protected void invokeCallBack(Map map, JSCallback... callBacks) {
                WXLogUtils.w(getClass().getSimpleName(), map.toString());
                for (JSCallback callBack : callBacks) {
                    if (callBack != null) {
                        callBack.invokeAndKeepAlive(map);
                    }
                }
            }
        };

        Observable.just(1).subscribeOn(Schedulers.io()).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                return new ObservableSource<String>() {
                    @Override
                    public void subscribe(Observer<? super String> observer) {
                        if (object.containsKey("address") && !TextUtils.isEmpty(object.getString("address"))) {
                            String mstrAddress = object.getString("address");

                            if (strAddress != null && mstrAddress.equals(strAddress) && bConnect) {
                                observer.onNext("true");
                                return;
                            }

                            if (isConnecting) {
                                observer.onNext("connecting");
                                return;
                            }

                            InputStream tmpIn;
                            OutputStream tmpOut;
                            try {
                                isConnecting = true;
                                UUID uuid = UUID.fromString(SPP_UUID);
                                BluetoothDevice btDev = mBtAdapter.getRemoteDevice(mstrAddress);
                                btSocket = btDev.createRfcommSocketToServiceRecord(uuid);
//                                btSocket =(BluetoothSocket) btDev.getClass()
//                                        .getDeclaredMethod("createRfcommSocket",new Class[]{int.class})
                                if (btSocket != null) {
                                    btSocket.connect();
                                }
                                tmpIn = btSocket.getInputStream();
                                tmpOut = btSocket.getOutputStream();
                            } catch (Exception e) {
                                Log.d(Common.TAG, "Error connected to: " + mstrAddress);
                                bConnect = false;
                                mmInStream = null;
                                mmOutStream = null;
                                btSocket = null;
                                e.printStackTrace();
                                observer.onError(e);
                                return;
                            }
                            bConnect = true;
                            strAddress = mstrAddress;
                            mmInStream = tmpIn;
                            mmOutStream = tmpOut;
                            observer.onNext("true");
                        } else {
                            observer.onError(new Throwable("address should not be null"));
                        }
                    }
                };
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new WEObserver<String>() {
                    @Override
                    public void onNext(String o) {
                        creatBTconnectionCallBack.success(o);
                        isConnecting = false;
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        creatBTconnectionCallBack.error(e.toString());
                        isConnecting = false;
                    }
                });
    }

    @JSMethod(uiThread = false)
    public void writeBluetoothValue(final JSONObject object, final JSCallback success, final JSCallback failure, JSCallback complete) {
        final ModuleAdapterCallBack ModuleAdapterCallBack = new ModuleAdapterCallBack(success, failure, complete);

        if (!bConnect) {
            ModuleAdapterCallBack.error("未连接设备");
            return;
        }

        Observable.just(12).subscribeOn(Schedulers.io())
                .flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        return new Observable<String>() {
                            @Override
                            protected void subscribeActual(Observer<? super String> observer) {
                                try {
                                    if (mmOutStream == null) {
                                        observer.onError(new Throwable("未开启读写流"));
                                        return;
                                    }
                                    String retStr = send(object.getString("order").getBytes());
                                    Thread.sleep(500);
                                    observer.onNext(retStr);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    observer.onError(e);
//                                    return;
                                }
                            }
                        };
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new WEObserver<String>() {
                    @Override
                    public void onNext(String o) {
                        if (o.equals("false")) {
                            ModuleAdapterCallBack.error("send fail");
                        } else {
                            ModuleAdapterCallBack.success(o);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        ModuleAdapterCallBack.error("send fail" + e.toString());
                    }
                });
    }

    @JSMethod(uiThread = false)
    public void stopReadBluetoothValue(final JSCallback success) {
        if (!isReading || !bConnect) {
            if (success != null)
                success.invoke(true);
            return;
        }
        stopReadCB = success;
        isReading = false;
    }

    @JSMethod(uiThread = false)
    public void readBluetoothValue(final JSCallback success, final JSCallback failure) {

        Observable.just(11).subscribeOn(Schedulers.io())
                .flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        return new ObservableSource<String>() {
                            @Override
                            public void subscribe(Observer<? super String> observer) {
                                isReading = true;
                                byte[] bufRecv = new byte[1024];
                                int nRecv = 0;
                                while (bConnect && isReading) {
                                    try {
                                        Log.e(Common.TAG, "Start Recv" + mmInStream.available());
                                        nRecv = mmInStream.read(bufRecv);
                                        if (nRecv < 1) {
                                            Log.e(Common.TAG, "Recving Short");
                                            Thread.sleep(200);
                                            continue;
                                        }
                                        byte[] bRecv = new byte[1024];
                                        int nRecved = 0;
                                        System.arraycopy(bufRecv, 0, bRecv, nRecved, nRecv);
                                        Log.e(Common.TAG, "Recv:" + nRecv);
                                        nRecved += nRecv;
                                        if (nRecved < nNeed) {
                                            Thread.sleep(200);
                                            continue;
                                        }
                                        String strRecv = bytesToString(bRecv, nRecved).trim();
                                        Log.e(Common.TAG, "接收数据: " + strRecv);
//                                        if (strRecv.endsWith("\r\n") || strRecv.endsWith("E"))
                                        observer.onNext(strRecv);
                                        Thread.sleep(200);
                                    } catch (Exception e) {
                                        Log.e(Common.TAG, "Recv thread:" + e.getMessage());
                                        observer.onError(e);
                                        break;
                                    }
                                }
                                if (stopReadCB != null)
                                    stopReadCB.invoke(true);
                            }
                        };
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new WEObserver<String>() {
                    @Override
                    public void onNext(String o) {
                        if (success != null) {
                            success.invokeAndKeepAlive(o);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (failure != null) {
                            failure.invokeAndKeepAlive("Recv thread:" + e.getMessage());
                        }
                    }
                });

    }

    @JSMethod(uiThread = false)
    public void closeBluetoothConnection(JSCallback success, JSCallback failure) {
        if (bConnect) {
            bConnect = false;
            isReading = false;
            try {
                Thread.sleep(500);
                if (mmInStream != null)
                    mmInStream.close();
                if (mmOutStream != null)
                    mmOutStream.close();
                if (btSocket != null)
                    btSocket.close();
            } catch (Exception e) {
                Log.e(Common.TAG, "Close error...");
                e.printStackTrace();
            }
        }
        if (success != null)
            success.invoke(true);
    }

    public String send(byte[] strValue) {
//        toast("指令为："+strValue,true);
        if (!bConnect) {
            return "false";
        }
        try {
            if (mmOutStream == null)
                return "false";
//            toast("发送指令："+strValue,true);
            mmOutStream.write(strValue);
            mmOutStream.flush();
            if (mmInStream != null) {
                Thread.sleep(100);
                byte[] bufRecv = new byte[1024];
                int nRecv = 0;
                nRecv = mmInStream.read(bufRecv);
                if (nRecv < 1) {
                    Thread.sleep(100);
                    mmOutStream.write(strValue);
                    return "";
                }
                return new String(bufRecv);
            }
            return "";
        } catch (final Exception e) {
            e.printStackTrace();
//            toast("发送指令出错" + e.toString(), true);
            return "false";
        }
    }

    // 随机选取1-10
    public int random() {
        int number = (int) (1 + Math.random() * 10);
        if (number == randomNumber) {
            random();
        } else {
            randomNumber = number;
        }
        return randomNumber;
    }

    private Boolean IsDeviceAlreadyAdd(String btAddr) {
        Boolean bAdded = false;
        for (DeviceInfo bt : mDeviceList) {
            if (btAddr.equals(bt.address)) {
                bAdded = true;
                break;
            }
        }
        return bAdded;
    }

    public void addpaidedDevides() {
        if (mBtAdapter != null) {
            Set<BluetoothDevice> bondeddevices = mBtAdapter.getBondedDevices();
            for (BluetoothDevice device : bondeddevices) {
                if (!IsDeviceAlreadyAdd(device.getAddress())) {
                    DeviceInfo bt = new DeviceInfo();
                    bt.name = device.getName();
                    bt.address = device.getAddress();
                    mDeviceList.add(bt);
                }
            }
        }
    }

    public void toast(String s) {
        toast(s, false);
    }

    public void toast(final String s, boolean b) {
        if (b)
            mWXSDKInstance.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mWXSDKInstance.getContext(), s, Toast.LENGTH_SHORT).show();
                }
            });
    }

    public String bytesToString(byte[] b, int length) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < length; i++) {
            result.append((char) (b[i]));
        }

        return result.toString();
    }
}
