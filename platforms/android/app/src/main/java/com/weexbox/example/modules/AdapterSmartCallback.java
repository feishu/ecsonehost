package com.weexbox.example.modules;

import com.lelibrary.androidlelibrary.ble.SmartDevice;
import com.lelibrary.androidlelibrary.model.BLETagModel;
import com.lelibrary.androidlelibrary.sdk.callback.SmartCallback;
import com.taobao.weex.bridge.JSCallback;
import com.weex.weexextra.ModuleAdapterCallBack;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class AdapterSmartCallback implements SmartCallback {
    ModuleAdapterCallBack moduleAdapterCallBack;
    public AdapterSmartCallback(ModuleAdapterCallBack  moduleAdapterCallBack) {
        moduleAdapterCallBack = moduleAdapterCallBack;
    }

    @Override
    public void onDeviceConnected(SmartDevice smartDevice) {

    }

    @Override
    public void onDeviceDisconnected(SmartDevice smartDevice) {

    }

    @Override
    public void onReadCalibrateGyro(SmartDevice smartDevice, boolean b) {

    }

    @Override
    public void onDisableDeepSleep(SmartDevice smartDevice, boolean b) {

    }

    @Override
    public void onImageCapture(SmartDevice smartDevice, boolean b) {

    }

    @Override
    public void onImageSequenceTableDownloaded(SmartDevice smartDevice, boolean b, JSONArray jsonArray) {

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

    }

    @Override
    public void onDataProgress(SmartDevice smartDevice, int i, int i1) {

    }

    @Override
    public void onEraseAllEvents(SmartDevice smartDevice, boolean b) {

    }

    @Override
    public void onRemoteCommandsExecutionProcess(SmartDevice smartDevice, JSONObject jsonObject, int i, int i1) {

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
    public void onUpdateFirmwareNumber(SmartDevice smartDevice, String s, String s1) {

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

    @Override
    public void onSTMProgress(SmartDevice smartDevice, int i, int i1, int i2) {

    }

    @Override
    public void onSTMSuccess(SmartDevice smartDevice) {

    }

    @Override
    public void onSTMFailed(SmartDevice smartDevice, String s) {

    }
}
