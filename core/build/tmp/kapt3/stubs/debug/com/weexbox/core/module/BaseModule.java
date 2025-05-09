package com.weexbox.core.module;

import java.lang.System;

/**
 * Author: Mario
 * Time: 2018/9/12 下午7:45
 * Description: This is BaseModule
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0002\u00a8\u0006\u000b"}, d2 = {"Lcom/weexbox/core/module/BaseModule;", "Lcom/taobao/weex/common/WXModule;", "()V", "getActivity", "Lcom/weexbox/core/controller/WBBaseActivity;", "getFragment", "Lcom/weexbox/core/controller/WBWeexFragment;", "getRecursionFragment", "fragments", "", "Landroidx/fragment/app/Fragment;", "core_debug"})
public class BaseModule extends com.taobao.weex.common.WXModule {
    
    @org.jetbrains.annotations.NotNull()
    public final com.weexbox.core.controller.WBBaseActivity getActivity() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.weexbox.core.controller.WBWeexFragment getFragment() {
        return null;
    }
    
    private final com.weexbox.core.controller.WBWeexFragment getRecursionFragment(java.util.List<? extends androidx.fragment.app.Fragment> fragments) {
        return null;
    }
    
    public BaseModule() {
        super();
    }
}