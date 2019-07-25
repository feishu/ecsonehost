package com.ucmed.mrdc.tslnet.net;

import android.content.Context;


/**
 * Created by WX-GXM-1326 on 2018/3/28.
 */

public interface TSLNetAdapterInterface {

    String get(NetParams params, RequestCallbackInterface callbackInterface);

    String postJson(NetParams params, RequestCallbackInterface callbackInterface);

    String postForm(NetParams params, RequestCallbackInterface callbackInterface);

    String upload(NetParams params, DownloadCallbackInterface callbackInterface);

    String download(NetParams params, DownloadCallbackInterface callbackInterface);

    void abort(String TAG);

    String networkType(Context context);

}
