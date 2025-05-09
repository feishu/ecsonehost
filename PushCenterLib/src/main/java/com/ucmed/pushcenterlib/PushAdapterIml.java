package com.ucmed.pushcenterlib;

import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class PushAdapterIml {

    public static final String TAG = PushAdapterIml.class.getSimpleName();
    public String origin = "";

    public PushAdapterIml(String origin) {
        this.origin = origin;
    }

    /**
     * 对传入参数进行空值判断,
     *
     * @return
     */

    public boolean initParam(Map params, String paramName, PushResultCallBack callBack) {
        HashMap map = new HashMap();
        if (params == null) {
            map.put("errMsg", "fail params is null");
            map.put("origin", origin);
            invokeResult(false, map, callBack);
            return false;
        }
        Log.w(TAG, params.toString());
        Object paramValue = params.get(paramName);
        if (paramValue == null || TextUtils.isEmpty(paramValue.toString())) {
            map.put("errMsg", "fail missing parameter " + paramName);
            map.put("origin", origin);
            invokeResult(false, map, callBack);
            return false;
        }
        return true;
    }


    /**
     * 根据操作结果执行callBack
     */
    protected void invokeResult(boolean isSuccess, Map res, PushResultCallBack callBack) {
        if (callBack != null) {
            if (res == null) {
                res = new HashMap();
            }
            res.put("origin", origin);
            if (isSuccess)
                callBack.success(res);
            else
                callBack.failure(res);
        }
    }

//    protected void invokeResult(boolean isSuccess, String msg, PushResultCallBack callBack) {
//        Map map = new HashMap();
//        map.put("errMsg", "fail params is null");
//        invokeResult(isSuccess, map, callBack);
//    }
}
