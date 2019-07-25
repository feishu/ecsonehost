package com.ucmed.mrdc.tslnet;

import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.ucmed.mrdc.tslnet.net.DownloadCallbackInterface;
import com.ucmed.mrdc.tslnet.net.DownloadModuleAdapterCallBack;
import com.ucmed.mrdc.tslnet.net.NetAdapterManager;
import com.ucmed.mrdc.tslnet.net.NetParams;
import com.ucmed.mrdc.tslnet.net.RequestCallbackInterface;
import com.ucmed.mrdc.tslnet.net.RequestModuleAdapterCallBack;
import com.ucmed.mrdc.tslnet.net.TSLNetStatusListener;
import com.weex.weexextra.FileUtil;
import com.weex.weexextra.ModuleAdapterCallBack;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;

/**
 * Created by WX-GXM-1326 on 2018/3/28.
 */

public class TSLNetModule extends WXModule {

    public static HashMap<String,JSCallback> NetStatusList = new HashMap<>();

    @JSMethod(uiThread = false)
    public String request(JSONObject params, final JSCallback success, final JSCallback fail, final JSCallback complete) {

        final RequestModuleAdapterCallBack tslAdapterCallBack = new RequestModuleAdapterCallBack(success, fail, complete);

        NetParams netparams = tslAdapterCallBack.getNetParams(params, RequestModuleAdapterCallBack.Type.request);

        if (netparams == null) return "";

        if (netparams.getMethod().toUpperCase().equals("GET")) {
            return NetAdapterManager.getInstance().getTslNetAdapterInterface().get(netparams, new RequestCallbackInterface() {

                @Override
                public void onSuccess(String data, int statusCode, Headers header) {
                    tslAdapterCallBack.requestSuccess(data, statusCode, header);
                }

                @Override
                public void onFail(String data, int statusCode, Headers header) {
                    tslAdapterCallBack.requestError(data, statusCode, header);
                }
            });
        }
        if (netparams.getMethod().toUpperCase().equals("POST")) {
            if (netparams.getHeaders() != null) {
                if (netparams.getHeaders().getString("content-type") != null && netparams.getHeaders().getString("content-type").contains("application/json")) {
                    return NetAdapterManager.getInstance().getTslNetAdapterInterface().postJson(netparams, new RequestCallbackInterface() {
                        @Override
                        public void onSuccess(String data, int statusCode, Headers header) {
                            tslAdapterCallBack.requestSuccess(data, statusCode, header);
                        }

                        @Override
                        public void onFail(String data, int statusCode, Headers header) {
                            tslAdapterCallBack.requestError(data, statusCode, header);
                        }
                    });
                }
                if (netparams.getHeaders().getString("content-type") != null && netparams.getHeaders().getString("content-type").contains("application/x-www-form-urlencoded")) {
                    return NetAdapterManager.getInstance().getTslNetAdapterInterface().postForm(netparams, new RequestCallbackInterface() {
                        @Override
                        public void onSuccess(String data, int statusCode, Headers header) {
                            tslAdapterCallBack.requestSuccess(data, statusCode, header);
                        }

                        @Override
                        public void onFail(String data, int statusCode, Headers header) {
                            tslAdapterCallBack.requestError(data, statusCode, header);
                        }
                    });
                }
            }
        }
        return "";

    }

    @JSMethod(uiThread = false)
    public String upload(JSONObject params, JSCallback success, JSCallback fail, JSCallback complete, final JSCallback progressjs) {
        final DownloadModuleAdapterCallBack tslAdapterCallBack = new DownloadModuleAdapterCallBack(success, fail, complete);

        NetParams netparams = tslAdapterCallBack.getNetParams(params, RequestModuleAdapterCallBack.Type.upload);

        if (netparams == null) return "";

        return NetAdapterManager.getInstance().getTslNetAdapterInterface().upload(netparams, new DownloadCallbackInterface() {
            @Override
            public void onProgress(int progress, int totalBytesWritten, int totalBytesExpectedToWrite) {
                if (progressjs != null) {
                    progressjs.invokeAndKeepAlive(tslAdapterCallBack.upload(progress, totalBytesWritten, totalBytesExpectedToWrite));
                }
            }

            @Override
            public void onSuccess(String data, int statusCode, Headers header) {
                tslAdapterCallBack.requestSuccess(data, statusCode, header);
            }

            @Override
            public void onFail(String data, int statusCode, Headers header) {
                tslAdapterCallBack.requestError(data, statusCode, header);
            }

        });
    }

    @JSMethod(uiThread = false)
    public String download(JSONObject params, final JSCallback success, JSCallback fail, JSCallback complete, final JSCallback progressjs) {
        final DownloadModuleAdapterCallBack tslAdapterCallBack = new DownloadModuleAdapterCallBack(success, fail, complete);

        NetParams netparams = tslAdapterCallBack.getNetParams(params, RequestModuleAdapterCallBack.Type.download);

        if (netparams == null) return "";
        String url = netparams.getUrl();
        String filename = url.substring(url.lastIndexOf("/") + 1);
        netparams.setFilePath(new File(FileUtil.getCacheRootPath(), filename).getAbsolutePath());
        return NetAdapterManager.getInstance().getTslNetAdapterInterface().download(netparams, new DownloadCallbackInterface() {
            @Override
            public void onProgress(int progress, int totalBytesWritten, int totalBytesExpectedToWrite) {
                if (progressjs != null) {
                    progressjs.invokeAndKeepAlive(tslAdapterCallBack.download(progress, totalBytesWritten, totalBytesExpectedToWrite));
                }
            }

            @Override
            public void onSuccess(String data, int statusCode, Headers header) {
                tslAdapterCallBack.requestSuccess(data, statusCode, header);
            }

            @Override
            public void onFail(String data, int statusCode, Headers header) {
                tslAdapterCallBack.requestError(data, statusCode, header);
            }

        });
    }

    @JSMethod(uiThread = false)
    public void abort(String TAG) {
        NetAdapterManager.getInstance().getTslNetAdapterInterface().abort(TAG);
    }

    @JSMethod(uiThread = false)
    public void onNetworkStatusChange(final JSCallback callback) {
        if( NetAdapterManager.getInstance().getTslNetStatusListener()==null){
            NetAdapterManager.getInstance().setTslNetStatusListener(new TSLNetStatusListener() {
                @Override
                public void onStatusChange(boolean isConnected,String networkType) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("isConnected",isConnected);
                    map.put("networkType",networkType);
                    for (JSCallback jsCallback : NetStatusList.values()) {
                        jsCallback.invokeAndKeepAlive(map);
                    }
                }
            });
        }
        NetStatusList.put(mWXSDKInstance.getInstanceId(),callback);
    }

    @Override
    public void onActivityDestroy() {
        super.onActivityDestroy();
        NetStatusList.remove(mWXSDKInstance.getInstanceId());
    }

    @JSMethod(uiThread = false)
    public void getNetworkType(JSCallback success, JSCallback fail, JSCallback complete) {
        ModuleAdapterCallBack callBack=new ModuleAdapterCallBack(success,fail,complete);
        HashMap<String, String> map = new HashMap<>();
        map.put("networkType", NetAdapterManager.getInstance().getTslNetAdapterInterface().networkType(mWXSDKInstance.getContext()));
        callBack.success(map);
    }
}
