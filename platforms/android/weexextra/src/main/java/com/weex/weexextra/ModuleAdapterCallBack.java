package com.weex.weexextra;

import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.utils.WXLogUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * @author LCZ
 *         weex module方法处理回调类
 */
public class ModuleAdapterCallBack{
    JSCallback successCallBack;
    JSCallback errorCallBack;
    JSCallback completeCallBack;

    public ModuleAdapterCallBack(JSCallback... callbacks) {
        if (callbacks.length == 1) {
            successCallBack = callbacks[0];
        } else if (callbacks.length == 2) {
            successCallBack = callbacks[0];
            errorCallBack = callbacks[1];
        } else if (callbacks.length == 3) {
            successCallBack = callbacks[0];
            errorCallBack = callbacks[1];
            completeCallBack = callbacks[2];
        }

    }

    public void success(Map map) {
        invokeCallBack(map, successCallBack, completeCallBack);
    }

    public void successKeepAlive(Map map){
        invokeAndKeepAliveCallBack(map, successCallBack, completeCallBack);
    }

    public void success(String msg) {
        HashMap map = new HashMap();
        map.put("code",200);
        map.put("message", msg);
        invokeCallBack(map, successCallBack, completeCallBack);
    }

    public void successKeepAlive(String msg){
        HashMap map = new HashMap();
        map.put("code",200);
        map.put("message", msg);
        invokeAndKeepAliveCallBack(map, successCallBack, completeCallBack);
    }

    public void error(Map map) {
        invokeCallBack(map, errorCallBack, completeCallBack);
    }

    public void errorKeepAlive(Map map) {
        invokeAndKeepAliveCallBack(map, errorCallBack, completeCallBack);
    }
    public void error(String errMsg) {
        this.error(errMsg,202);
    }
    public void error(String errMsg,int code) {
        HashMap map = new HashMap();
        map.put("code",code);
        map.put("message", errMsg);
        invokeCallBack(map, errorCallBack, completeCallBack);
    }
    public void errorKeepAlive(String errMsg){
        this.errorKeepAlive(errMsg,202);
    }
    public void errorKeepAlive(String errMsg,int code) {
        HashMap map = new HashMap();
        map.put("code",code);
        map.put("message", errMsg);
        invokeAndKeepAliveCallBack(map, errorCallBack, completeCallBack);
    }

    /**
     * @param map
     * @param callBacks
     */
    protected void invokeCallBack(Map map, JSCallback... callBacks) {
        WXLogUtils.w(getClass().getSimpleName(), map.toString());
        for (JSCallback callBack : callBacks) {
            if (callBack != null) {
                callBack.invoke(map);
            }
        }
    }

    protected void invokeAndKeepAliveCallBack(Map map, JSCallback... callBacks) {
        WXLogUtils.w(getClass().getSimpleName(), map.toString());
        for (JSCallback callBack : callBacks) {
            if (callBack != null) {
                callBack.invokeAndKeepAlive(map);
            }
        }
    }


}
