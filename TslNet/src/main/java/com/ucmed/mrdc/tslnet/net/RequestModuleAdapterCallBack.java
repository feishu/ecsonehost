package com.ucmed.mrdc.tslnet.net;

import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.bridge.JSCallback;
import com.weex.weexextra.ModuleAdapterCallBack;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import okhttp3.Headers;

/**
 * Created by WX-GXM-1326 on 2018/3/28.
 */

public class RequestModuleAdapterCallBack extends ModuleAdapterCallBack {
    public RequestModuleAdapterCallBack(JSCallback success, JSCallback fail, JSCallback complete) {
        super(success,fail,complete);
    }

    public void requestSuccess(String data, int statusCode, Headers header) {
        HashMap<String,Object> res = new HashMap();
        res.put("data",data);
        res.put("statusCode",statusCode);
        res.put("header", getHeadersInfo(header));
        success(res);
    }

    private Map<String, String> getHeadersInfo(Headers headers) {
        Map<String, String> map = new HashMap<String, String>();
        Set<String> headerNames = headers.names();
        Iterator<String> it = headerNames.iterator();
        while (it.hasNext()) {
            String key = it.next();
            String value = headers.get(key);
            map.put(key, value);
        }
        return map;
    }

    public void requestError(String data, int statusCode, Headers header) {
        HashMap<String,Object> res = new HashMap();
        res.put("data",data);
        res.put("statusCode",statusCode);
        res.put("header",header ==null ? "" : getHeadersInfo(header));
        error(res);
    }

    public NetParams getNetParams(JSONObject params, Type type){
        boolean invaildOption = params == null ? true : params.getString("url") == null;
        if (invaildOption) {
            HashMap resp = new HashMap<String,Object>();
            resp.put("statusCode", -1);
            resp.put("data", "url is null");
            error(resp);
            return null;
        } else {
            if(type== Type.request) {
                return getRequestParams(params);
            }
            if(type== Type.upload){
                return getUploadParams(params);
            }
            if(type== Type.download){
                return getDownloadParams(params);
            }
        }
        return null;
    }

    public NetParams getRequestParams(JSONObject params){
        NetParams netparams = new NetParams();
        netparams.setUrl(params.getString("url"));
        netparams.setHeaders(params.getJSONObject("header")==null?new JSONObject():params.getJSONObject("header"));
        String content_type = getContentType(netparams.getHeaders());
        netparams.getHeaders().put("content-type",content_type);
//        netparams.setData(params.get("data")==null?new HashMap<String, Object>():TextUtils.isEmpty(String.valueOf(params.get("data")))?new HashMap<String, Object>():(Map<String, Object>) params.get("data"));
//        netparams.setData(TextUtils.isEmpty (params.getString("data"))?new HashMap<String, Object>():(Map<String, ? extends Object>) JSON.parse(params.getString("data")));
        netparams.setData(TextUtils.isEmpty (params.getString("data"))?"":params.getString("data"));
        netparams.setDataType(params.getString("dataType")==null?"json":params.getString("dataType").toLowerCase());
        netparams.setResponseType(params.getString("responseType")==null?"":params.getString("responseType"));
        netparams.setTimeout(params.getLongValue("timeout") > 0 ? params.getLongValue("timeout") : 30000);
        String method = params.getString("method");
        if (method != null) {
            method = method.toUpperCase();
            if (!method.equals("GET") && !method.equals("POST") && !method.equals("OPTIONS") && !method.equals("HEAD") && !method.equals("PUT") && !method.equals("DELETE") && !method.equals("TRACE") && !method.equals("CONNECT")) {
                method = "GET";
            }
        }
        netparams.setMethod(method==null?"GET":method);
        return netparams;
    }

    public NetParams getUploadParams(JSONObject params){
        NetParams netparams =new NetParams();
        netparams.setUrl(params.getString("url"));
        netparams.setFilePath(params.getString("filePath"));
        netparams.setName(params.getString("name"));
        netparams.setHeaders(params.getJSONObject("header")==null?new JSONObject():params.getJSONObject("header"));
        netparams.setFormData(params.getJSONObject("formData"));
        return netparams;
    }

    public NetParams getDownloadParams(JSONObject params){
        NetParams netparams =new NetParams();
        netparams.setUrl(params.getString("url"));
        netparams.setHeaders(params.getJSONObject("header")==null?new JSONObject():params.getJSONObject("header"));
        return netparams;
    }

    public String getContentType(JSONObject jsonObject){
        String content_type = !TextUtils.isEmpty(jsonObject.getString("content-type")) ? jsonObject.getString("content-type"):
                !TextUtils.isEmpty(jsonObject.getString("Content-Type")) ? jsonObject.getString("Content-Type"): "application/x-www-form-urlencoded";
        Iterator iterator = jsonObject.keySet().iterator();
        while(iterator.hasNext()){
            Object o = iterator.next();
            if(o.toString().toLowerCase().equals("content-type"))
                iterator.remove();
        }
        return content_type;
    }

    public enum Type{
        request,
        upload,
        download
    }
}
