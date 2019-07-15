package com.weexextra.nfcmodule;

import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.weex.weexextra.ModuleAdapterCallBack;

import java.util.List;

public class NFCModule extends WXModule {

    @JSMethod(uiThread = false)
    public void init() {
        NFCAdapter.getInstance().init(mWXSDKInstance.getContext(), new NFCAdapter.doCallback() {
            @Override
            public void onSuccess(String s) {

            }

            @Override
            public void onfail(String s) {

            }
        });
        NFCAdapter.getInstance().setGetTag(new NFCAdapter.getTag() {
            @Override
            public void onGetTag(List<String> list) {

            }
        });
    }

    @JSMethod(uiThread = false)
    public void resume() {
        NFCAdapter.getInstance().resume(mWXSDKInstance.getContext());
    }

    @JSMethod(uiThread = false)
    public void pause() {
        NFCAdapter.getInstance().pause(mWXSDKInstance.getContext());
    }

    //往Ndef标签中写数据
    @JSMethod(uiThread = false)
    private void writeNdef(JSONObject jsonObject, JSCallback success, JSCallback failure) {
        final ModuleAdapterCallBack m = new ModuleAdapterCallBack(success, failure);
        NFCAdapter.getInstance().writeNdef(mWXSDKInstance.getContext(), jsonObject.getString("text"), new NFCAdapter.doCallback() {
            @Override
            public void onSuccess(String s) {
                m.success(s);
            }

            @Override
            public void onfail(String s) {
                m.error(s);
            }
        });
    }

    //读取Ndef标签中数据
    @JSMethod(uiThread = false)
    private void readNdef(JSCallback success, JSCallback failure) {
        final ModuleAdapterCallBack m = new ModuleAdapterCallBack(success, failure);
        NFCAdapter.getInstance().readNdef(mWXSDKInstance.getContext(), new NFCAdapter.doCallback() {
            @Override
            public void onSuccess(String s) {
                m.success(s);
            }

            @Override
            public void onfail(String s) {
                m.error(s);
            }
        });
    }


    /**
     * 写块
     *
     * @param sector 写入的扇区
     * @param block  写入的块区
     */
    @JSMethod(uiThread = false)
    private void writeBlock(JSONObject jsonObject,JSCallback success, JSCallback failure) {
        final ModuleAdapterCallBack m = new ModuleAdapterCallBack(success, failure);
        NFCAdapter.getInstance().writeBlock(mWXSDKInstance.getContext(), jsonObject.getIntValue("sector"), jsonObject.getIntValue("block"), jsonObject.getString("text")
                , new NFCAdapter.doCallback() {
                    @Override
                    public void onSuccess(String s) {
                        m.success(s);
                    }

                    @Override
                    public void onfail(String s) {
                        m.error(s);
                    }
                });
    }

    /**
     * 读取块
     *
     * @param context
     * @param sector  写入的扇区
     * @param block   写入的块区
     */
    @JSMethod(uiThread = false)
    private void readBlock(JSONObject jsonObject,JSCallback success, JSCallback failure) {
        final ModuleAdapterCallBack m = new ModuleAdapterCallBack(success, failure);
        NFCAdapter.getInstance().readBlock(mWXSDKInstance.getContext(), jsonObject.getIntValue("sector"), jsonObject.getIntValue("block")
                , new NFCAdapter.doCallback() {
                    @Override
                    public void onSuccess(String s) {
                        m.success(s);
                    }

                    @Override
                    public void onfail(String s) {
                        m.error(s);
                    }
                });
    }
}
