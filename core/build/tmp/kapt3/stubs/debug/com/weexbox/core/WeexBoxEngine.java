package com.weexbox.core;

import java.lang.System;

/**
 * Author: Mario
 * Time: 2018/8/15 下午3:37
 * Description: This is WeexBoxEngine
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0010H\u0016J\u0012\u0010\u0018\u001a\u00020\u00162\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u0016H\u0002J\b\u0010\u001c\u001a\u00020\u0016H\u0002J\b\u0010\u001d\u001a\u00020\u0016H\u0002J\u001a\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001aR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014\u00a8\u0006 "}, d2 = {"Lcom/weexbox/core/WeexBoxEngine;", "", "()V", "application", "Landroid/app/Application;", "getApplication", "()Landroid/app/Application;", "setApplication", "(Landroid/app/Application;)V", "value", "", "isDebug", "()Z", "setDebug", "(Z)V", "loadingIconRes", "", "getLoadingIconRes", "()Ljava/lang/String;", "setLoadingIconRes", "(Ljava/lang/String;)V", "disposeLoadingIcon", "", "res", "initWeex", "config", "Lcom/taobao/weex/InitConfig;", "registerComponent", "registerModule", "registerRouter", "setup", "weexConfig", "core_debug"})
public final class WeexBoxEngine {
    @org.jetbrains.annotations.NotNull()
    public static android.app.Application application;
    @org.jetbrains.annotations.NotNull()
    private static java.lang.String loadingIconRes;
    private static boolean isDebug;
    public static final com.weexbox.core.WeexBoxEngine INSTANCE = null;
    
    @org.jetbrains.annotations.NotNull()
    public final android.app.Application getApplication() {
        return null;
    }
    
    public final void setApplication(@org.jetbrains.annotations.NotNull()
    android.app.Application p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLoadingIconRes() {
        return null;
    }
    
    public final void setLoadingIconRes(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final boolean isDebug() {
        return false;
    }
    
    public final void setDebug(boolean value) {
    }
    
    public final void setup(@org.jetbrains.annotations.NotNull()
    android.app.Application application, @org.jetbrains.annotations.Nullable()
    com.taobao.weex.InitConfig weexConfig) {
    }
    
    private final void initWeex(com.taobao.weex.InitConfig config) {
    }
    
    private final void registerModule() {
    }
    
    private final void registerRouter() {
    }
    
    private final void registerComponent() {
    }
    
    public void disposeLoadingIcon(@org.jetbrains.annotations.NotNull()
    java.lang.String res) {
    }
    
    private WeexBoxEngine() {
        super();
    }
}