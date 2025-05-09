package com.weexbox.core.module;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0017J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0017J&\u0010\u000b\u001a\u00020\b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0017J,\u0010\u0010\u001a\u00020\b2\u0018\u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r0\u00122\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0017J\u0010\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u000fH\u0017J,\u0010\u0015\u001a\u00020\b2\u0018\u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r0\u00122\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0017\u00a8\u0006\u0016"}, d2 = {"Lcom/weexbox/core/module/NavigatorModule;", "Lcom/weexbox/core/module/BaseModule;", "()V", "getActionbar", "Lcom/weexbox/core/widget/SimpleToolbar;", "getHeight", "", "onBackPressed", "", "callback", "Lcom/taobao/weex/bridge/JSCallback;", "setCenterItem", "options", "", "", "", "setLeftItems", "items", "", "setNavColor", "color", "setRightItems", "core_debug"})
public class NavigatorModule extends com.weexbox.core.module.BaseModule {
    
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    public void setCenterItem(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, ? extends java.lang.Object> options, @org.jetbrains.annotations.Nullable()
    com.taobao.weex.bridge.JSCallback callback) {
    }
    
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    public void setLeftItems(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends java.util.Map<java.lang.String, ? extends java.lang.Object>> items, @org.jetbrains.annotations.Nullable()
    com.taobao.weex.bridge.JSCallback callback) {
    }
    
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    public void setRightItems(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends java.util.Map<java.lang.String, ? extends java.lang.Object>> items, @org.jetbrains.annotations.Nullable()
    com.taobao.weex.bridge.JSCallback callback) {
    }
    
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    public void setNavColor(@org.jetbrains.annotations.NotNull()
    java.lang.Object color) {
    }
    
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    public void onBackPressed(@org.jetbrains.annotations.NotNull()
    com.taobao.weex.bridge.JSCallback callback) {
    }
    
    @com.taobao.weex.annotation.JSMethod(uiThread = false)
    public int getHeight() {
        return 0;
    }
    
    private final com.weexbox.core.widget.SimpleToolbar getActionbar() {
        return null;
    }
    
    public NavigatorModule() {
        super();
    }
}