package com.ucmed.mrdc.tslnet.net;

import okhttp3.Headers;

/**
 * Created by WX-GXM-1326 on 2018/3/28.
 */

public interface RequestCallbackInterface {

    void onSuccess(String data, int statusCode, Headers header);

    void onFail(String data, int statusCode, Headers header);

}
