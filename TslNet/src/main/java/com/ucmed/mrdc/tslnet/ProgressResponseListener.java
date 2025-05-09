package com.ucmed.mrdc.tslnet;

/**
 * Created by WX-GXM-1326 on 2018/3/19.
 */

/**
 * 响应体进度回调接口，用于文件下载进度回调
 */
public interface ProgressResponseListener {
    void onResponseProgress(long bytesRead, long contentLength, boolean done);
}
