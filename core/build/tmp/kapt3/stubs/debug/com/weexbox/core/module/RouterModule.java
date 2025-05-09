package com.weexbox.core.module;

import java.lang.System;

/**
 * Author: Mario
 * Time: 2018/8/16 下午5:05
 * Description: This is RouterModule
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0017J\u0016\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0006\u0018\u00010\bH\u0017J\u001a\u0010\n\u001a\u00020\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00060\bJ\u001c\u0010\r\u001a\u00020\u00042\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00060\bH\u0017J\b\u0010\u000e\u001a\u00020\u0004H\u0017\u00a8\u0006\u000f"}, d2 = {"Lcom/weexbox/core/module/RouterModule;", "Lcom/weexbox/core/module/BaseModule;", "()V", "close", "", "levels", "", "getParams", "", "", "getRouter", "Lcom/weexbox/core/router/Router;", "options", "open", "refresh", "core_debug"})
public class RouterModule extends com.weexbox.core.module.BaseModule {
    
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    public void open(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, ? extends java.lang.Object> options) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @com.taobao.weex.annotation.JSMethod(uiThread = false)
    public java.util.Map<java.lang.String, java.lang.Object> getParams() {
        return null;
    }
    
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    public void close(@org.jetbrains.annotations.Nullable()
    java.lang.Object levels) {
    }
    
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    public void refresh() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.weexbox.core.router.Router getRouter(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, ? extends java.lang.Object> options) {
        return null;
    }
    
    public RouterModule() {
        super();
    }
}