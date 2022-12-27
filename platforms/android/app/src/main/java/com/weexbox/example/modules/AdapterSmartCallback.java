package com.weexbox.example.modules;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.lelibrary.androidlelibrary.ble.SmartDevice;
import com.lelibrary.androidlelibrary.model.BLETagModel;
import com.lelibrary.androidlelibrary.sdk.callback.SmartCallback;
import com.weex.weexextra.ModuleAdapterCallBack;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdapterSmartCallback implements SmartCallback {
    ModuleAdapterCallBack moduleAdapterCallBack;
    SmartTagToolsModule.SmartConnectCallback sccallback;
    Context _context;
    public AdapterSmartCallback(ModuleAdapterCallBack  adapterCallBack, SmartTagToolsModule.SmartConnectCallback conncallback, Context context) {
        moduleAdapterCallBack = adapterCallBack;
        sccallback = conncallback;
        _context = context;
    }
    public AdapterSmartCallback(ModuleAdapterCallBack  moduleAdapterCallBack,Context context){
        this(moduleAdapterCallBack,null,context);
    }

    public AdapterSmartCallback(SmartTagToolsModule.SmartConnectCallback conncallback,Context context){
        this(null,conncallback,context);
    }

    /**连接ST*/
    @Override
    public void onDeviceConnected(SmartDevice smartDevice) {
        if(moduleAdapterCallBack!=null){
            Map map = new HashMap();
            try {
                map.put("IBeaconUUID",smartDevice.getIbeaconUUID());
                map.put("BatteryLevel",smartDevice.getBatteryLevel());
                map.put("CoolerID",smartDevice.getCoolerId());
                map.put("SerialNumber",smartDevice.getSerialNumber());
                map.put("Distance",smartDevice.getDistanceInMeter(_context));
                map.put("DistanceInMM",smartDevice.getSmartShelfDistanceInMM());
                map.put("DistanceRange",smartDevice.getRSSIRange(smartDevice.getDistanceInMeter(_context)));
                map.put("RunningAverageRssiAccurate",smartDevice.getRunningAverageRssiAccurate());
                map.put("macAddress",smartDevice.getAddress());
                map.put("Rssi",smartDevice.getRssi());
                map.put("Name",smartDevice.getDevice().getName());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            map.put("status", "onDeviceConnected");
            moduleAdapterCallBack.successKeepAlive(map);
        }

        if(sccallback!=null){
            sccallback.onDeviceConnected();
        }
    }

    /**断开ST*/
    @Override
    public void onDeviceDisconnected(SmartDevice smartDevice) {
        if(moduleAdapterCallBack!=null){
            Map map = new HashMap();
            try {
                map.put("IBeaconUUID",smartDevice.getIbeaconUUID());
                map.put("BatteryLevel",smartDevice.getBatteryLevel());
                map.put("CoolerID",smartDevice.getCoolerId());
                map.put("SerialNumber",smartDevice.getSerialNumber());
                map.put("Distance",smartDevice.getDistanceInMeter(_context));
                map.put("DistanceInMM",smartDevice.getSmartShelfDistanceInMM());
                map.put("DistanceRange",smartDevice.getRSSIRange(smartDevice.getDistanceInMeter(_context)));
                map.put("RunningAverageRssiAccurate",smartDevice.getRunningAverageRssiAccurate());
                map.put("macAddress",smartDevice.getAddress());
                map.put("Rssi",smartDevice.getRssi());
                map.put("Name",smartDevice.getDevice().getName());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            map.put("status", "onDeviceDisconnected");
            moduleAdapterCallBack.successKeepAlive(map);
        }
        if(sccallback!=null){
            sccallback.onDeviceDisconnected();
        }
    }

    @Override
    public void onReadCalibrateGyro(SmartDevice smartDevice, boolean b) {
        Log.d("smcallback", "onReadCalibrateGyro: ");
    }

    @Override
    public void onDisableDeepSleep(SmartDevice smartDevice, boolean b) {
        Log.d("smcallback", "onDisableDeepSleep: ");
    }

    @Override
    public void onImageCapture(SmartDevice smartDevice, boolean b) {
        Log.d("smcallback", "onImageCapture: ");
    }

    @Override
    public void onImageSequenceTableDownloaded(SmartDevice smartDevice,  boolean isSuccess, org.json.JSONArray listData) {
        if(moduleAdapterCallBack!=null){
            Map map = new HashMap();
            try {
                map.put("IBeaconUUID",smartDevice.getIbeaconUUID());
                map.put("BatteryLevel",smartDevice.getBatteryLevel());
                map.put("CoolerID",smartDevice.getCoolerId());
                map.put("SerialNumber",smartDevice.getSerialNumber());
                map.put("Distance",smartDevice.getDistanceInMeter(_context));
                map.put("DistanceInMM",smartDevice.getSmartShelfDistanceInMM());
                map.put("DistanceRange",smartDevice.getRSSIRange(smartDevice.getDistanceInMeter(_context)));
                map.put("RunningAverageRssiAccurate",smartDevice.getRunningAverageRssiAccurate());
                map.put("macAddress",smartDevice.getAddress());
                map.put("Rssi",smartDevice.getRssi());
                map.put("Name",smartDevice.getDevice().getName());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            map.put("status", "onImageSequenceTableDownloaded");
            map.put("isSuccess", isSuccess);
            map.put("listData", JSON.parseArray(listData.toString()));
            moduleAdapterCallBack.successKeepAlive(map);
        }

    }

    @Override
    public void onImageDeleted(SmartDevice smartDevice, boolean isDeleted) {
        if(moduleAdapterCallBack!=null) {
            Map map = new HashMap();
            try {
                map.put("IBeaconUUID", smartDevice.getIbeaconUUID());
                map.put("BatteryLevel", smartDevice.getBatteryLevel());
                map.put("CoolerID", smartDevice.getCoolerId());
                map.put("SerialNumber", smartDevice.getSerialNumber());
                map.put("Distance", smartDevice.getDistanceInMeter(_context));
                map.put("DistanceInMM", smartDevice.getSmartShelfDistanceInMM());
                map.put("DistanceRange", smartDevice.getRSSIRange(smartDevice.getDistanceInMeter(_context)));
                map.put("RunningAverageRssiAccurate", smartDevice.getRunningAverageRssiAccurate());
                map.put("macAddress", smartDevice.getAddress());
                map.put("Rssi", smartDevice.getRssi());
                map.put("Name", smartDevice.getDevice().getName());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            map.put("status", "onImageDeleted");
            map.put("isDeleted", isDeleted);
            moduleAdapterCallBack.successKeepAlive(map);
        }
    }

    @Override
    public void onImageDownloadProgress(SmartDevice smartDevice, int packetId, int packetCount, int percentage) {
        if(moduleAdapterCallBack!=null) {
            Map map = new HashMap();
            try {
                map.put("IBeaconUUID", smartDevice.getIbeaconUUID());
                map.put("BatteryLevel", smartDevice.getBatteryLevel());
                map.put("CoolerID", smartDevice.getCoolerId());
                map.put("SerialNumber", smartDevice.getSerialNumber());
                map.put("Distance", smartDevice.getDistanceInMeter(_context));
                map.put("DistanceInMM", smartDevice.getSmartShelfDistanceInMM());
                map.put("DistanceRange", smartDevice.getRSSIRange(smartDevice.getDistanceInMeter(_context)));
                map.put("RunningAverageRssiAccurate", smartDevice.getRunningAverageRssiAccurate());
                map.put("macAddress", smartDevice.getAddress());
                map.put("Rssi", smartDevice.getRssi());
                map.put("Name", smartDevice.getDevice().getName());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            map.put("status", "onImageDownloadProgress");
            map.put("packetId", packetId);
            map.put("packetCount", packetCount);
            map.put("percentage", percentage);
            moduleAdapterCallBack.successKeepAlive(map);
        }
    }

    @Override
    public void onImageDownloadCompleted(SmartDevice smartDevice, boolean isSuccess, ByteArrayOutputStream byteArrayOutputStream) {
        if(moduleAdapterCallBack!=null) {
            Map map = new HashMap();
            try {
                map.put("IBeaconUUID", smartDevice.getIbeaconUUID());
                map.put("BatteryLevel", smartDevice.getBatteryLevel());
                map.put("CoolerID", smartDevice.getCoolerId());
                map.put("SerialNumber", smartDevice.getSerialNumber());
                map.put("Distance", smartDevice.getDistanceInMeter(_context));
                map.put("DistanceInMM", smartDevice.getSmartShelfDistanceInMM());
                map.put("DistanceRange", smartDevice.getRSSIRange(smartDevice.getDistanceInMeter(_context)));
                map.put("RunningAverageRssiAccurate", smartDevice.getRunningAverageRssiAccurate());
                map.put("macAddress", smartDevice.getAddress());
                map.put("Rssi", smartDevice.getRssi());
                map.put("Name", smartDevice.getDevice().getName());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            map.put("status", "onImageDownloadCompleted");
            map.put("isSuccess", isSuccess);
            //TODO
//                map.put("byteArrayOutputStream", byteArrayOutputStream.toString());
            moduleAdapterCallBack.successKeepAlive(map);
        }
    }

    /**下载ST数据*/
    @Override
    public void onDataDownloaded(SmartDevice smartDevice, boolean isSuccess, ArrayList<BLETagModel> arrayList) {
        if(sccallback!=null){
            sccallback.onDataDownloaded(isSuccess,arrayList);
        }
        if(moduleAdapterCallBack!=null) {
            Map map = new HashMap();
            try {
                map.put("IBeaconUUID", smartDevice.getIbeaconUUID());
                map.put("BatteryLevel", smartDevice.getBatteryLevel());
                map.put("CoolerID", smartDevice.getCoolerId());
                map.put("SerialNumber", smartDevice.getSerialNumber());
                map.put("Distance", smartDevice.getDistanceInMeter(_context));
                map.put("DistanceInMM", smartDevice.getSmartShelfDistanceInMM());
                map.put("DistanceRange", smartDevice.getRSSIRange(smartDevice.getDistanceInMeter(_context)));
                map.put("RunningAverageRssiAccurate", smartDevice.getRunningAverageRssiAccurate());
                map.put("macAddress", smartDevice.getAddress());
                map.put("Rssi", smartDevice.getRssi());
                map.put("Name", smartDevice.getDevice().getName());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            map.put("status", "onDataDownloaded");
            map.put("isSuccess", isSuccess);
            map.put("dataList", new JSONArray(new ArrayList<Object>(arrayList)));
            moduleAdapterCallBack.successKeepAlive(map);
        }
    }

    /**下载进度*/
    @Override
    public void onDataProgress(SmartDevice smartDevice, int currentIndex, int totalCount) {
        if(sccallback!=null){
            sccallback.onDataProgress(currentIndex,totalCount);
        }
        if(moduleAdapterCallBack!=null){
            Map map = new HashMap();
            try {
                map.put("IBeaconUUID",smartDevice.getIbeaconUUID());
                map.put("BatteryLevel",smartDevice.getBatteryLevel());
                map.put("CoolerID",smartDevice.getCoolerId());
                map.put("SerialNumber",smartDevice.getSerialNumber());
                map.put("Distance",smartDevice.getDistanceInMeter(_context));
                map.put("DistanceInMM",smartDevice.getSmartShelfDistanceInMM());
                map.put("DistanceRange",smartDevice.getRSSIRange(smartDevice.getDistanceInMeter(_context)));
                map.put("RunningAverageRssiAccurate",smartDevice.getRunningAverageRssiAccurate());
                map.put("macAddress",smartDevice.getAddress());
                map.put("Rssi",smartDevice.getRssi());
                map.put("Name",smartDevice.getDevice().getName());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            map.put("status", "onDataProgress");
            map.put("currentIndex", currentIndex);
            map.put("totalCount", totalCount);
            moduleAdapterCallBack.successKeepAlive(map);
        }
    }

    @Override
    public void onEraseAllEvents(SmartDevice smartDevice, boolean b) {
        if(moduleAdapterCallBack!=null){
            Map map = new HashMap();
            try {
                map.put("IBeaconUUID",smartDevice.getIbeaconUUID());
                map.put("BatteryLevel",smartDevice.getBatteryLevel());
                map.put("CoolerID",smartDevice.getCoolerId());
                map.put("SerialNumber",smartDevice.getSerialNumber());
                map.put("Distance",smartDevice.getDistanceInMeter(_context));
                map.put("DistanceInMM",smartDevice.getSmartShelfDistanceInMM());
                map.put("DistanceRange",smartDevice.getRSSIRange(smartDevice.getDistanceInMeter(_context)));
                map.put("RunningAverageRssiAccurate",smartDevice.getRunningAverageRssiAccurate());
                map.put("macAddress",smartDevice.getAddress());
                map.put("Rssi",smartDevice.getRssi());
                map.put("Name",smartDevice.getDevice().getName());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            map.put("status", "onEraseAllEvents");
            map.put("isSuccess", b);
            moduleAdapterCallBack.successKeepAlive(map);
        }
    }

    @Override
    public void onRemoteCommandsExecutionProcess(SmartDevice smartDevice, JSONObject jsonObject, int RCIndex, int RCCount) {
        if(moduleAdapterCallBack!=null){
            Map map = new HashMap();
            try {
                map.put("IBeaconUUID",smartDevice.getIbeaconUUID());
                map.put("BatteryLevel",smartDevice.getBatteryLevel());
                map.put("CoolerID",smartDevice.getCoolerId());
                map.put("SerialNumber",smartDevice.getSerialNumber());
                map.put("Distance",smartDevice.getDistanceInMeter(_context));
                map.put("DistanceInMM",smartDevice.getSmartShelfDistanceInMM());
                map.put("DistanceRange",smartDevice.getRSSIRange(smartDevice.getDistanceInMeter(_context)));
                map.put("RunningAverageRssiAccurate",smartDevice.getRunningAverageRssiAccurate());
                map.put("macAddress",smartDevice.getAddress());
                map.put("Rssi",smartDevice.getRssi());
                map.put("Name",smartDevice.getDevice().getName());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            map.put("status", "onRemoteCommandsExecutionProcess");
            map.put("RCIndex", RCIndex);
            map.put("RCCount", RCCount);
            map.put("JSONObject", JSON.parseObject(jsonObject.toString()));
            moduleAdapterCallBack.successKeepAlive(map);
        }
    }

    @Override
    public void onRemoteCommandsExecutionFinished(SmartDevice smartDevice, int statuscode, String message) {
        if(moduleAdapterCallBack!=null) {
            Map map = new HashMap();
            try {
                map.put("IBeaconUUID", smartDevice.getIbeaconUUID());
                map.put("BatteryLevel", smartDevice.getBatteryLevel());
                map.put("CoolerID", smartDevice.getCoolerId());
                map.put("SerialNumber", smartDevice.getSerialNumber());
                map.put("Distance", smartDevice.getDistanceInMeter(_context));
                map.put("DistanceInMM", smartDevice.getSmartShelfDistanceInMM());
                map.put("DistanceRange", smartDevice.getRSSIRange(smartDevice.getDistanceInMeter(_context)));
                map.put("RunningAverageRssiAccurate", smartDevice.getRunningAverageRssiAccurate());
                map.put("macAddress", smartDevice.getAddress());
                map.put("Rssi", smartDevice.getRssi());
                map.put("Name", smartDevice.getDevice().getName());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            map.put("status", "onRemoteCommandsExecutionFinished");
            map.put("statuscode", statuscode);
            map.put("message", message);
            moduleAdapterCallBack.successKeepAlive(map);
        }
    }

    @Override
    public void onUpdate(SmartDevice smartDevice, String message) {
        if(moduleAdapterCallBack!=null){
            Map map = new HashMap();
            try {
                map.put("IBeaconUUID",smartDevice.getIbeaconUUID());
                map.put("BatteryLevel",smartDevice.getBatteryLevel());
                map.put("CoolerID",smartDevice.getCoolerId());
                map.put("SerialNumber",smartDevice.getSerialNumber());
                map.put("Distance",smartDevice.getDistanceInMeter(_context));
                map.put("DistanceInMM",smartDevice.getSmartShelfDistanceInMM());
                map.put("DistanceRange",smartDevice.getRSSIRange(smartDevice.getDistanceInMeter(_context)));
                map.put("RunningAverageRssiAccurate",smartDevice.getRunningAverageRssiAccurate());
                map.put("macAddress",smartDevice.getAddress());
                map.put("Rssi",smartDevice.getRssi());
                map.put("Name",smartDevice.getDevice().getName());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            map.put("status", "onUpdate");
            map.put("message", message);
            moduleAdapterCallBack.successKeepAlive(map);
        }
    }

    @Override
    public void onLogUpdate(SmartDevice smartDevice, String message) {
        if(moduleAdapterCallBack!=null){
            Map map = new HashMap();
            try {
                map.put("IBeaconUUID",smartDevice.getIbeaconUUID());
                map.put("BatteryLevel",smartDevice.getBatteryLevel());
                map.put("CoolerID",smartDevice.getCoolerId());
                map.put("SerialNumber",smartDevice.getSerialNumber());
                map.put("Distance",smartDevice.getDistanceInMeter(_context));
                map.put("DistanceInMM",smartDevice.getSmartShelfDistanceInMM());
                map.put("DistanceRange",smartDevice.getRSSIRange(smartDevice.getDistanceInMeter(_context)));
                map.put("RunningAverageRssiAccurate",smartDevice.getRunningAverageRssiAccurate());
                map.put("macAddress",smartDevice.getAddress());
                map.put("Rssi",smartDevice.getRssi());
                map.put("Name",smartDevice.getDevice().getName());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            map.put("status", "onLogUpdate");
            map.put("message", message);
            moduleAdapterCallBack.successKeepAlive(map);
        }
    }

    @Override
    public void onUpdateFirmwareNumber(SmartDevice smartDevice, String s, String s1) {
        if(moduleAdapterCallBack!=null){
            Map map = new HashMap();
            try {
                map.put("IBeaconUUID",smartDevice.getIbeaconUUID());
                map.put("BatteryLevel",smartDevice.getBatteryLevel());
                map.put("CoolerID",smartDevice.getCoolerId());
                map.put("SerialNumber",smartDevice.getSerialNumber());
                map.put("Distance",smartDevice.getDistanceInMeter(_context));
                map.put("DistanceInMM",smartDevice.getSmartShelfDistanceInMM());
                map.put("DistanceRange",smartDevice.getRSSIRange(smartDevice.getDistanceInMeter(_context)));
                map.put("RunningAverageRssiAccurate",smartDevice.getRunningAverageRssiAccurate());
                map.put("macAddress",smartDevice.getAddress());
                map.put("Rssi",smartDevice.getRssi());
                map.put("Name",smartDevice.getDevice().getName());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            map.put("status", "onUpdateFirmwareNumber");
            map.put("FirmwareNumber", s);
            moduleAdapterCallBack.successKeepAlive(map);
        }
    }

    @Override
    public void onUpdateRssi(SmartDevice smartDevice, int rssi, int status,  double distance,  String Range) {
        if(moduleAdapterCallBack!=null){
            Map map = new HashMap();
            try {
                map.put("IBeaconUUID",smartDevice.getIbeaconUUID());
                map.put("BatteryLevel",smartDevice.getBatteryLevel());
                map.put("CoolerID",smartDevice.getCoolerId());
                map.put("SerialNumber",smartDevice.getSerialNumber());
                map.put("Distance",smartDevice.getDistanceInMeter(_context));
                map.put("DistanceInMM",smartDevice.getSmartShelfDistanceInMM());
                map.put("DistanceRange",smartDevice.getRSSIRange(smartDevice.getDistanceInMeter(_context)));
                map.put("RunningAverageRssiAccurate",smartDevice.getRunningAverageRssiAccurate());
                map.put("macAddress",smartDevice.getAddress());
                map.put("Rssi",smartDevice.getRssi());
                map.put("Name",smartDevice.getDevice().getName());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            map.put("status", "onUpdateRssi");
            map.put("rssi", rssi);
            map.put("status", status);
            map.put("distance", distance);
            map.put("Range", Range);
            moduleAdapterCallBack.successKeepAlive(map);
        }
    }

    @Override
    public void onDFUProgress(SmartDevice smartDevice, int dfuType, int percentage, float currentSpeed, float averageSpeed) {
        if(moduleAdapterCallBack!=null){
            Map map = new HashMap();
            try {
                map.put("IBeaconUUID",smartDevice.getIbeaconUUID());
                map.put("BatteryLevel",smartDevice.getBatteryLevel());
                map.put("CoolerID",smartDevice.getCoolerId());
                map.put("SerialNumber",smartDevice.getSerialNumber());
                map.put("Distance",smartDevice.getDistanceInMeter(_context));
                map.put("DistanceInMM",smartDevice.getSmartShelfDistanceInMM());
                map.put("DistanceRange",smartDevice.getRSSIRange(smartDevice.getDistanceInMeter(_context)));
                map.put("RunningAverageRssiAccurate",smartDevice.getRunningAverageRssiAccurate());
                map.put("macAddress",smartDevice.getAddress());
                map.put("Rssi",smartDevice.getRssi());
                map.put("Name",smartDevice.getDevice().getName());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            map.put("status", "onDFUProgress");
            map.put("dfuType", dfuType);
            map.put("percentage", percentage);
            map.put("currentSpeed", currentSpeed);
            map.put("averageSpeed", averageSpeed);
            moduleAdapterCallBack.successKeepAlive(map);
        }
    }

    @Override
    public void onDFUSuccess(SmartDevice smartDevice) {
        if (moduleAdapterCallBack!=null){
            Map map = new HashMap();
            try {
                map.put("IBeaconUUID",smartDevice.getIbeaconUUID());
                map.put("BatteryLevel",smartDevice.getBatteryLevel());
                map.put("CoolerID",smartDevice.getCoolerId());
                map.put("SerialNumber",smartDevice.getSerialNumber());
                map.put("Distance",smartDevice.getDistanceInMeter(_context));
                map.put("DistanceInMM",smartDevice.getSmartShelfDistanceInMM());
                map.put("DistanceRange",smartDevice.getRSSIRange(smartDevice.getDistanceInMeter(_context)));
                map.put("RunningAverageRssiAccurate",smartDevice.getRunningAverageRssiAccurate());
                map.put("macAddress",smartDevice.getAddress());
                map.put("Rssi",smartDevice.getRssi());
                map.put("Name",smartDevice.getDevice().getName());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            map.put("status", "onDFUSuccess");
            moduleAdapterCallBack.successKeepAlive(map);
        }
    }

    @Override
    public void onDFUFailed(SmartDevice smartDevice, String message) {
        if(moduleAdapterCallBack!=null){
            Map map = new HashMap();
            try {
                map.put("IBeaconUUID",smartDevice.getIbeaconUUID());
                map.put("BatteryLevel",smartDevice.getBatteryLevel());
                map.put("CoolerID",smartDevice.getCoolerId());
                map.put("SerialNumber",smartDevice.getSerialNumber());
                map.put("Distance",smartDevice.getDistanceInMeter(_context));
                map.put("DistanceInMM",smartDevice.getSmartShelfDistanceInMM());
                map.put("DistanceRange",smartDevice.getRSSIRange(smartDevice.getDistanceInMeter(_context)));
                map.put("RunningAverageRssiAccurate",smartDevice.getRunningAverageRssiAccurate());
                map.put("macAddress",smartDevice.getAddress());
                map.put("Rssi",smartDevice.getRssi());
                map.put("Name",smartDevice.getDevice().getName());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            map.put("status", "onDFUFailed");
            map.put("message", message);
            moduleAdapterCallBack.successKeepAlive(map);
        }
    }

    @Override
    public void onSTMProgress(SmartDevice smartDevice, int i, int i1, int i2) {
        if(moduleAdapterCallBack!=null){
            Map map = new HashMap();
            try {
                map.put("IBeaconUUID",smartDevice.getIbeaconUUID());
                map.put("BatteryLevel",smartDevice.getBatteryLevel());
                map.put("CoolerID",smartDevice.getCoolerId());
                map.put("SerialNumber",smartDevice.getSerialNumber());
                map.put("Distance",smartDevice.getDistanceInMeter(_context));
                map.put("DistanceInMM",smartDevice.getSmartShelfDistanceInMM());
                map.put("DistanceRange",smartDevice.getRSSIRange(smartDevice.getDistanceInMeter(_context)));
                map.put("RunningAverageRssiAccurate",smartDevice.getRunningAverageRssiAccurate());
                map.put("macAddress",smartDevice.getAddress());
                map.put("Rssi",smartDevice.getRssi());
                map.put("Name",smartDevice.getDevice().getName());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            map.put("status", "onSTMProgress");
            map.put("percentage", i);
            map.put("currentSpeed", i1);
            map.put("averageSpeed", i2);
            moduleAdapterCallBack.successKeepAlive(map);
        }
    }

    @Override
    public void onSTMSuccess(SmartDevice smartDevice) {
        if (moduleAdapterCallBack!=null){
            Map map = new HashMap();
            try {
                map.put("IBeaconUUID",smartDevice.getIbeaconUUID());
                map.put("BatteryLevel",smartDevice.getBatteryLevel());
                map.put("CoolerID",smartDevice.getCoolerId());
                map.put("SerialNumber",smartDevice.getSerialNumber());
                map.put("Distance",smartDevice.getDistanceInMeter(_context));
                map.put("DistanceInMM",smartDevice.getSmartShelfDistanceInMM());
                map.put("DistanceRange",smartDevice.getRSSIRange(smartDevice.getDistanceInMeter(_context)));
                map.put("RunningAverageRssiAccurate",smartDevice.getRunningAverageRssiAccurate());
                map.put("macAddress",smartDevice.getAddress());
                map.put("Rssi",smartDevice.getRssi());
                map.put("Name",smartDevice.getDevice().getName());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            map.put("status", "onSTMSuccess");
            moduleAdapterCallBack.successKeepAlive(map);
        }
    }

    @Override
    public void onSTMFailed(SmartDevice smartDevice, String message) {
        if(moduleAdapterCallBack!=null){
            Map map = new HashMap();
            try {
                map.put("IBeaconUUID",smartDevice.getIbeaconUUID());
                map.put("BatteryLevel",smartDevice.getBatteryLevel());
                map.put("CoolerID",smartDevice.getCoolerId());
                map.put("SerialNumber",smartDevice.getSerialNumber());
                map.put("Distance",smartDevice.getDistanceInMeter(_context));
                map.put("DistanceInMM",smartDevice.getSmartShelfDistanceInMM());
                map.put("DistanceRange",smartDevice.getRSSIRange(smartDevice.getDistanceInMeter(_context)));
                map.put("RunningAverageRssiAccurate",smartDevice.getRunningAverageRssiAccurate());
                map.put("macAddress",smartDevice.getAddress());
                map.put("Rssi",smartDevice.getRssi());
                map.put("Name",smartDevice.getDevice().getName());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            map.put("status", "onSTMFailed");
            map.put("message", message);
            moduleAdapterCallBack.successKeepAlive(map);
        }
    }
}
