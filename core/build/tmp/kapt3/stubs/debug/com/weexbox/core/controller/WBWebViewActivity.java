package com.weexbox.core.controller;

import java.lang.System;

/**
 * Author:leon.wen
 * Time:2018/10/20   19:00
 * Description:This is WBWebViewActivity
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0012\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014J\b\u0010\n\u001a\u00020\u0006H\u0014R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/weexbox/core/controller/WBWebViewActivity;", "Lcom/weexbox/core/controller/WBBaseActivity;", "()V", "sonicSession", "Lcom/tencent/sonic/sdk/SonicSession;", "onBackPressed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "Companion", "core_debug"})
public class WBWebViewActivity extends com.weexbox.core.controller.WBBaseActivity {
    private com.tencent.sonic.sdk.SonicSession sonicSession;
    @org.jetbrains.annotations.Nullable()
    private static com.weexbox.core.interfaces.WebViewSetInterface webViewSetInterface;
    public static final com.weexbox.core.controller.WBWebViewActivity.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    public WBWebViewActivity() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"Lcom/weexbox/core/controller/WBWebViewActivity$Companion;", "", "()V", "webViewSetInterface", "Lcom/weexbox/core/interfaces/WebViewSetInterface;", "getWebViewSetInterface", "()Lcom/weexbox/core/interfaces/WebViewSetInterface;", "setWebViewSetInterface", "(Lcom/weexbox/core/interfaces/WebViewSetInterface;)V", "core_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.Nullable()
        public final com.weexbox.core.interfaces.WebViewSetInterface getWebViewSetInterface() {
            return null;
        }
        
        public final void setWebViewSetInterface(@org.jetbrains.annotations.Nullable()
        com.weexbox.core.interfaces.WebViewSetInterface p0) {
        }
        
        private Companion() {
            super();
        }
    }
}