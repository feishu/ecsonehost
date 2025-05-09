package com.weexbox.core.module;

import java.lang.System;

/**
 * Author: Mario
 * Time: 2018/9/12 下午6:33
 * Description: This is EventModule
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006H\u0017J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0017J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\bH\u0017J\b\u0010\u000e\u001a\u00020\u0004H\u0017\u00a8\u0006\u000f"}, d2 = {"Lcom/weexbox/core/module/EventModule;", "Lcom/weexbox/core/module/BaseModule;", "()V", "emit", "", "options", "", "", "", "register", "name", "callback", "Lcom/taobao/weex/bridge/JSCallback;", "unregister", "unregisterAll", "core_debug"})
public class EventModule extends com.weexbox.core.module.BaseModule {
    
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    public void register(@org.jetbrains.annotations.NotNull()
    java.lang.Object name, @org.jetbrains.annotations.NotNull()
    com.taobao.weex.bridge.JSCallback callback) {
    }
    
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    public void emit(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, ? extends java.lang.Object> options) {
    }
    
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    public void unregister(@org.jetbrains.annotations.NotNull()
    java.lang.Object name) {
    }
    
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    public void unregisterAll() {
    }
    
    public EventModule() {
        super();
    }
}