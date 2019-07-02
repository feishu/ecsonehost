package com.ucmed.mrdc.tslqrcode;

import android.annotation.TargetApi;
import android.net.Uri;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.weex.weexextra.ModuleAdapterCallBack;


import static com.taobao.weex.adapter.URIAdapter.BUNDLE;


/**
 * Created by WX-GXM-1326 on 2018/4/27.
 */

public class QRModule extends WXModule {

    //扫码
    @JSMethod(uiThread = true)
    public void scanCode(JSONObject object, JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack) {
        new QRAdapterIml().scanCode(mWXSDKInstance.getContext(),
                new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack));
    }

    //解码
    @JSMethod(uiThread = true)
    public void decScan(JSONObject object, JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack) {
        new QRAdapterIml().decScan(mWXSDKInstance.getContext(), object.getString("url"),
                new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack));
    }

    //生成二维码
    @JSMethod(uiThread = true)
    public void createCode(JSONObject object, JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack) {
        if(TextUtils.isEmpty(object.getString("qrCodeString"))){
            new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack).error("src is null");
            return;
        }
        int width;
        int height;
        if(object.getInteger("width") == null || object.getInteger("height") == null){
            width=600;height=600;
        }else{
            width = object.getIntValue("width");
            height = object.getIntValue("height");
        }
        if(!TextUtils.isEmpty(object.getString("logo"))){
            String logoPath = mWXSDKInstance.rewriteUri(Uri.parse(object.getString("logo")), BUNDLE).getEncodedPath();
            object.put("logo",logoPath);
        }
        new QRAdapterIml().generateQR(mWXSDKInstance.getContext(), object.getString("qrCodeString"),object.getString("logo")
                ,object.getString("scanType"),width,height,
                new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack));
    }


}
