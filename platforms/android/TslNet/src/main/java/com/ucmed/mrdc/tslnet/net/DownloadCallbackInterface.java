package com.ucmed.mrdc.tslnet.net;

/**
 * Created by WX-GXM-1326 on 2018/3/28.
 */

public interface DownloadCallbackInterface extends RequestCallbackInterface {

    void onProgress(int progress, int totalBytesWritten, int totalBytesExpectedToWrite);

}
