package com.weexbox.core.module;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00062\u0006\u0010\t\u001a\u00020\nH\u0017J$\u0010\u000b\u001a\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00062\u0006\u0010\f\u001a\u00020\nH\u0017J$\u0010\r\u001a\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00062\u0006\u0010\f\u001a\u00020\nH\u0017J\b\u0010\u000e\u001a\u00020\u0004H\u0017J\u0018\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\nH\u0017J$\u0010\u0011\u001a\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00062\u0006\u0010\f\u001a\u00020\nH\u0017J$\u0010\u0012\u001a\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00062\u0006\u0010\f\u001a\u00020\nH\u0017J\u0010\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\bH\u0017J\u001c\u0010\u0015\u001a\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006H\u0017J\u001c\u0010\u0016\u001a\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006H\u0017\u00a8\u0006\u0017"}, d2 = {"Lcom/weexbox/core/module/ModalModule;", "Lcom/weexbox/core/module/BaseModule;", "()V", "actionSheet", "", "options", "", "", "", "completionCallback", "Lcom/taobao/weex/bridge/JSCallback;", "alert", "callback", "confirm", "dismiss", "openOptionsPicker", "Lcom/alibaba/fastjson/JSONObject;", "openTimePicker", "prompt", "showLoading", "text", "showProgress", "showToast", "core_debug"})
public class ModalModule extends com.weexbox.core.module.BaseModule {
    
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    public void alert(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, ? extends java.lang.Object> options, @org.jetbrains.annotations.NotNull()
    com.taobao.weex.bridge.JSCallback callback) {
    }
    
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    public void confirm(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, ? extends java.lang.Object> options, @org.jetbrains.annotations.NotNull()
    com.taobao.weex.bridge.JSCallback callback) {
    }
    
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    public void prompt(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, ? extends java.lang.Object> options, @org.jetbrains.annotations.NotNull()
    com.taobao.weex.bridge.JSCallback callback) {
    }
    
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    public void actionSheet(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, ? extends java.lang.Object> options, @org.jetbrains.annotations.NotNull()
    com.taobao.weex.bridge.JSCallback completionCallback) {
    }
    
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    public void showToast(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, ? extends java.lang.Object> options) {
    }
    
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    public void showLoading(@org.jetbrains.annotations.NotNull()
    java.lang.Object text) {
    }
    
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    public void showProgress(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, ? extends java.lang.Object> options) {
    }
    
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    public void dismiss() {
    }
    
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    public void openTimePicker(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, ? extends java.lang.Object> options, @org.jetbrains.annotations.NotNull()
    com.taobao.weex.bridge.JSCallback callback) {
    }
    
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    public void openOptionsPicker(@org.jetbrains.annotations.NotNull()
    com.alibaba.fastjson.JSONObject options, @org.jetbrains.annotations.NotNull()
    com.taobao.weex.bridge.JSCallback callback) {
    }
    
    public ModalModule() {
        super();
    }
}