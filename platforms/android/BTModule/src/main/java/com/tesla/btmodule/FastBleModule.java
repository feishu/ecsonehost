package com.tesla.btmodule;

import android.Manifest;
import android.bluetooth.BluetoothGatt;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleGattCallback;
import com.clj.fastble.callback.BleIndicateCallback;
import com.clj.fastble.callback.BleScanCallback;
import com.clj.fastble.callback.BleWriteCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.clj.fastble.scan.BleScanRuleConfig;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.weex.weexextra.ModuleAdapterCallBack;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;



/**
 * Created by WX-GXM-1326 on 2017/5/31.
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class FastBleModule extends WXModule {
    public final static UUID UUID_NOTIFY = UUID.fromString("0000ffe1-0000-1000-8000-00805f9b34fb");
    public final static UUID UUID_SERVICE = UUID.fromString("0000ffe0-0000-1000-8000-00805f9b34fb");
    public static ArrayList<DeviceInfo> pairedDevices;
    public static JSCallback status_change;
    /**
     * **********************************************************************************
     */
    public static String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";
    static Boolean bConnect = true;
    static String strAddress = null;
    static Boolean isReading = false;
    static JSCallback stopReadCB;
    private static ArrayList<DeviceInfo> mDeviceList;
    private static BleDevice bleDevice;
    public String sDeviceID = "";
    public JSCallback scan_found_device;
    public JSCallback scan_complete;
    String strName = null;

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
                if (!BleManager.getInstance().isSupportBle()) {
                    failure.invoke(false);
                }

                if (!BleManager.getInstance().isBlueEnable()) {
                    BleManager.getInstance().enableBluetooth();
                }

                while (!BleManager.getInstance().isBlueEnable()) {
                    success.invokeAndKeepAlive(false);
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                success.invoke(true);
            }
        }).start();

    }

    @JSMethod(uiThread = false)
    public void closeBluetoothAdapter(JSCallback success, JSCallback fail) {
        BleManager.getInstance().cancelScan();
        BleManager.getInstance().disconnectAllDevice();
        BleManager.getInstance().disableBluetooth();

        if (mDeviceList != null)
            mDeviceList.clear();

        if (success != null) {
            success.invoke(null);
        }
    }

    @JSMethod(uiThread = false)
    public void onBluetoothAdapterStateChange(JSCallback status) {
        status_change = status;
    }

    @JSMethod(uiThread = false)
    public void stopBluetoothDevicesDiscovery(JSCallback success, JSCallback fail) {
        BleManager.getInstance().cancelScan();
        success.invoke(true);
    }

    @JSMethod(uiThread = false)
    public void startBluetoothDevicesDiscovery(Map params, JSCallback found_device, JSCallback complete) {
        if (params.containsKey("name")) {
            sDeviceID = (String) params.get("name");
        }
        scan_found_device = found_device;
        scan_complete = complete;
        mDeviceList = new ArrayList<DeviceInfo>();
        BleScanRuleConfig scanRuleConfig = new BleScanRuleConfig.Builder()
                .setAutoConnect(false)
                .setScanTimeOut(15000)
                .build();
        BleManager.getInstance().initScanRule(scanRuleConfig);
        BleManager.getInstance().scan(new BleScanCallback() {
            @Override
            public void onScanFinished(List<BleDevice> scanResultList) {
                if (scan_complete != null) scan_complete.invoke(mDeviceList);
            }

            @Override
            public void onScanStarted(boolean success) {

            }

            @Override
            public void onScanning(BleDevice bleDevice) {
                if (!IsDeviceAlreadyAdd(bleDevice.getMac())) {
                    DeviceInfo bt = new DeviceInfo();
                    bt.name = bleDevice.getName();
                    bt.address = bleDevice.getMac();
                    mDeviceList.add(bt);
                }
                if (scan_found_device != null)
                    scan_found_device.invokeAndKeepAlive(mDeviceList);
            }
        });
    }

    /**
     * @param object  address ios以rssi作为参数 Android以mac地址作为参数
     * @param success
     * @param failure
     */
    @JSMethod(uiThread = false)
    public void createBluetoothConnection(final JSONObject object, final JSCallback success, final JSCallback failure, JSCallback complete) {
        final ModuleAdapterCallBack creatBTconnectionCallBack = new ModuleAdapterCallBack(success, failure, complete) ;

        if (object.containsKey("address") && !TextUtils.isEmpty(object.getString("address"))) {
            String mstrAddress = object.getString("address");

            if (strAddress != null && mstrAddress.equals(strAddress) && bConnect) {
                creatBTconnectionCallBack.success("true");
                return;
            }

            BleManager.getInstance().connect(mstrAddress, new BleGattCallback() {
                @Override
                public void onStartConnect() {

                }

                @Override
                public void onConnectFail(BleDevice bleDevice, BleException exception) {
                    creatBTconnectionCallBack.error(exception.toString());
                }

                @Override
                public void onConnectSuccess(BleDevice bleDevice1, BluetoothGatt gatt, int status) {
                    bConnect = true;
                    strAddress = bleDevice.getMac();
                    bleDevice = bleDevice1;
                    creatBTconnectionCallBack.success(gatt.toString());
                }

                @Override
                public void onDisConnected(boolean isActiveDisConnected, BleDevice device, BluetoothGatt gatt, int status) {

                }
            });
        } else {
            creatBTconnectionCallBack.error("address should not be null");
        }
    }

    @JSMethod(uiThread = false)
    public void writeBluetoothValue(final JSONObject object, final JSCallback success, final JSCallback failure, JSCallback complete) {
        final ModuleAdapterCallBack ModuleAdapterCallBack = new ModuleAdapterCallBack(success, failure, complete);

        if (!bConnect) {
            ModuleAdapterCallBack.error("未连接设备");
            return;
        }

        int selectDirection = 0;
        if (object.containsKey("selectDirection")) {
            selectDirection = object.getIntValue("selectDirection");
        }

        BleManager.getInstance().write(bleDevice, UUID_SERVICE.toString(), UUID_NOTIFY.toString(), object.getString("order").getBytes(), new BleWriteCallback() {
            @Override
            public void onWriteSuccess(int current, int total, byte[] justWrite) {
                ModuleAdapterCallBack.success(new String(justWrite));
            }

            @Override
            public void onWriteFailure(BleException exception) {
                ModuleAdapterCallBack.error("send fail" + exception.toString());
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
        BleManager.getInstance().stopIndicate(bleDevice, UUID_SERVICE.toString(), UUID_NOTIFY.toString());
        isReading = false;
        if (success != null)
            success.invoke(true);
    }

    @JSMethod(uiThread = false)
    public void readBluetoothValue(final JSCallback success, final JSCallback failure) {
        BleManager.getInstance().indicate(
                bleDevice,
                UUID_SERVICE.toString(),
                UUID_NOTIFY.toString(),
                new BleIndicateCallback() {
                    @Override
                    public void onIndicateSuccess() {
                        // 打开通知操作成功
                    }

                    @Override
                    public void onIndicateFailure(BleException exception) {
                        // 打开通知操作失败
                        success.invokeAndKeepAlive(exception.toString());
                    }

                    @Override
                    public void onCharacteristicChanged(byte[] data) {
                        // 打开通知后，设备发过来的数据将在这里出现
                        success.invokeAndKeepAlive(new String(data));
                    }
                });
    }

    @JSMethod(uiThread = false)
    public void closeBluetoothConnection(JSCallback success, JSCallback failure) {
        if (bConnect) {
            BleManager.getInstance().disconnectAllDevice();
            bConnect = false;
            isReading = false;
        }
        if (success != null)
            success.invoke(true);
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


    public String bytesToString(byte[] b, int length) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < length; i++) {
            result.append((char) (b[i]));
        }

        return result.toString();
    }
}
