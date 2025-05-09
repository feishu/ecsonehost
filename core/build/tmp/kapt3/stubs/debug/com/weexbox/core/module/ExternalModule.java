package com.weexbox.core.module;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001:\u0001\u0016B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0017J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0006H\u0017J$\u0010\u0010\u001a\u00020\u00042\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00060\u00122\u0006\u0010\u0007\u001a\u00020\bH\u0017J\u0018\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u001c\u0010\u0015\u001a\u00020\u00042\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00060\u0012H\u0017\u00a8\u0006\u0017"}, d2 = {"Lcom/weexbox/core/module/ExternalModule;", "Lcom/weexbox/core/module/BaseModule;", "()V", "callPhone", "", "phone", "", "callback", "Lcom/taobao/weex/bridge/JSCallback;", "getBitmapHeight", "", "path", "", "getBitmapWidth", "openBrowser", "url", "openCamera", "options", "", "openPhoto", "Lcom/alibaba/fastjson/JSONObject;", "vibration", "BitmapAttribute", "core_debug"})
public class ExternalModule extends com.weexbox.core.module.BaseModule {
    
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    public void openCamera(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, ? extends java.lang.Object> options, @org.jetbrains.annotations.NotNull()
    com.taobao.weex.bridge.JSCallback callback) {
    }
    
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    public final void openPhoto(@org.jetbrains.annotations.NotNull()
    com.alibaba.fastjson.JSONObject options, @org.jetbrains.annotations.NotNull()
    com.taobao.weex.bridge.JSCallback callback) {
    }
    
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    public void openBrowser(@org.jetbrains.annotations.NotNull()
    java.lang.Object url) {
    }
    
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    public void callPhone(@org.jetbrains.annotations.NotNull()
    java.lang.Object phone, @org.jetbrains.annotations.Nullable()
    com.taobao.weex.bridge.JSCallback callback) {
    }
    
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    public void vibration(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, ? extends java.lang.Object> options) {
    }
    
    public final int getBitmapWidth(@org.jetbrains.annotations.NotNull()
    java.lang.String path) {
        return 0;
    }
    
    public final int getBitmapHeight(@org.jetbrains.annotations.NotNull()
    java.lang.String path) {
        return 0;
    }
    
    public ExternalModule() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\b\u00a8\u0006\u0012"}, d2 = {"Lcom/weexbox/core/module/ExternalModule$BitmapAttribute;", "Lcom/weexbox/core/net/entity/HttpBaseEntity;", "()V", "height", "", "getHeight", "()I", "setHeight", "(I)V", "url", "", "getUrl", "()Ljava/lang/String;", "setUrl", "(Ljava/lang/String;)V", "width", "getWidth", "setWidth", "core_debug"})
    public static final class BitmapAttribute extends com.weexbox.core.net.entity.HttpBaseEntity {
        private int width;
        private int height;
        @org.jetbrains.annotations.NotNull()
        public java.lang.String url;
        
        public final int getWidth() {
            return 0;
        }
        
        public final void setWidth(int p0) {
        }
        
        public final int getHeight() {
            return 0;
        }
        
        public final void setHeight(int p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getUrl() {
            return null;
        }
        
        public final void setUrl(@org.jetbrains.annotations.NotNull()
        java.lang.String p0) {
        }
        
        public BitmapAttribute() {
            super();
        }
    }
}