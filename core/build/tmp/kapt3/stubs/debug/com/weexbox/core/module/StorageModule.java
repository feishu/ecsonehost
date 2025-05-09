package com.weexbox.core.module;

import java.lang.System;

/**
 * Author: Mario
 * Time: 2018/8/17 下午5:24
 * Description: This is StorageModule
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0017J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0017J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007H\u0017J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0007H\u0017\u00a8\u0006\u000b"}, d2 = {"Lcom/weexbox/core/module/StorageModule;", "Lcom/weexbox/core/module/BaseModule;", "()V", "deleteAll", "", "deleteData", "key", "", "getData", "setData", "value", "core_debug"})
public class StorageModule extends com.weexbox.core.module.BaseModule {
    
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    public void setData(@org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    public java.lang.String getData(@org.jetbrains.annotations.NotNull()
    java.lang.String key) {
        return null;
    }
    
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    public void deleteData(@org.jetbrains.annotations.NotNull()
    java.lang.String key) {
    }
    
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    public void deleteAll() {
    }
    
    public StorageModule() {
        super();
    }
}