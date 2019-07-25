package com.ucmed.mrdc.tslnet.net;

import com.taobao.weex.bridge.JSCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WX-GXM-1326 on 2018/3/28.
 */

public class DownloadModuleAdapterCallBack extends RequestModuleAdapterCallBack {

    public DownloadModuleAdapterCallBack(JSCallback success, JSCallback fail, JSCallback complete) {
        super(success, fail, complete);
    }

    public Map upload(int progress, int totalBytesWritten, int totalBytesExpectedToWrite) {
        HashMap<String, Integer> res = new HashMap();
        res.put("progress", progress);
        res.put("totalBytesSent", totalBytesWritten);
        res.put("totalBytesExpectedToSend", totalBytesExpectedToWrite);
        return res;

    }

    public Map download(int progress, int totalBytesWritten, int totalBytesExpectedToWrite) {
        HashMap<String, Integer> res = new HashMap();
        res.put("progress", progress);
        res.put("totalBytesWritten", totalBytesWritten);
        res.put("totalBytesExpectedToWrite", totalBytesExpectedToWrite);
        return res;
    }
}
