package com.weexbox.core.component;

import java.lang.System;

/**
 * Author: Mario
 * Time: 2019/1/16 6:13 PM
 * Description: This is LottieComponent
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B+\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0002\b\u0003\u0018\u00010\b\u00a2\u0006\u0002\u0010\tJ\u001a\u0010\u0010\u001a\u00020\u00112\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013J\u000e\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0017J\u0010\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u001aH\u0014J\b\u0010\u001b\u001a\u00020\u0017H\u0007J\u001a\u0010\u001c\u001a\u00020\u00172\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013J\u0012\u0010\u001d\u001a\u00020\u00112\b\u0010\u001e\u001a\u0004\u0018\u00010\u0002H\u0014J\b\u0010\u001f\u001a\u00020\u0011H\u0007J\u0012\u0010 \u001a\u00020\u00112\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0007J\"\u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020\u00152\u0006\u0010#\u001a\u00020\u00152\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0007J\"\u0010$\u001a\u00020\u00112\u0006\u0010%\u001a\u00020\u00152\u0006\u0010&\u001a\u00020\u00152\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0007J\b\u0010\'\u001a\u00020\u0011H\u0007J\u001e\u0010(\u001a\u00020\u00112\u0014\u0010)\u001a\u0010\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0013H\u0016R\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f\u00a8\u0006*"}, d2 = {"Lcom/weexbox/core/component/LottieComponent;", "Lcom/weexbox/core/component/BaseComponent;", "Lcom/airbnb/lottie/LottieAnimationView;", "instance", "Lcom/taobao/weex/WXSDKInstance;", "parent", "Lcom/taobao/weex/ui/component/WXVContainer;", "basicComponentData", "Lcom/taobao/weex/ui/action/BasicComponentData;", "(Lcom/taobao/weex/WXSDKInstance;Lcom/taobao/weex/ui/component/WXVContainer;Lcom/taobao/weex/ui/action/BasicComponentData;)V", "callback", "Lcom/taobao/weex/bridge/JSCallback;", "getCallback", "()Lcom/taobao/weex/bridge/JSCallback;", "setCallback", "(Lcom/taobao/weex/bridge/JSCallback;)V", "applyProperties", "", "attributes", "", "", "", "complete", "", "initComponentHostView", "context", "Landroid/content/Context;", "isAnimationPlaying", "loadSource", "onHostViewInitialized", "host", "pause", "play", "playFromFrame", "fromFrame", "toFrame", "playFromProgress", "fromProgress", "toProgress", "stop", "updateAttrs", "attrs", "core_debug"})
public final class LottieComponent extends com.weexbox.core.component.BaseComponent<com.airbnb.lottie.LottieAnimationView> {
    @org.jetbrains.annotations.Nullable()
    private com.taobao.weex.bridge.JSCallback callback;
    
    @org.jetbrains.annotations.Nullable()
    public final com.taobao.weex.bridge.JSCallback getCallback() {
        return null;
    }
    
    public final void setCallback(@org.jetbrains.annotations.Nullable()
    com.taobao.weex.bridge.JSCallback p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    protected com.airbnb.lottie.LottieAnimationView initComponentHostView(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @java.lang.Override()
    protected void onHostViewInitialized(@org.jetbrains.annotations.Nullable()
    com.airbnb.lottie.LottieAnimationView host) {
    }
    
    @java.lang.Override()
    public void updateAttrs(@org.jetbrains.annotations.Nullable()
    java.util.Map<java.lang.String, java.lang.Object> attrs) {
    }
    
    public final boolean loadSource(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.Object> attributes) {
        return false;
    }
    
    public final void applyProperties(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.Object> attributes) {
    }
    
    public final void complete(boolean complete) {
    }
    
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    public final boolean isAnimationPlaying() {
        return false;
    }
    
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    public final void playFromProgress(@org.jetbrains.annotations.NotNull()
    java.lang.Object fromProgress, @org.jetbrains.annotations.NotNull()
    java.lang.Object toProgress, @org.jetbrains.annotations.Nullable()
    com.taobao.weex.bridge.JSCallback callback) {
    }
    
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    public final void playFromFrame(@org.jetbrains.annotations.NotNull()
    java.lang.Object fromFrame, @org.jetbrains.annotations.NotNull()
    java.lang.Object toFrame, @org.jetbrains.annotations.Nullable()
    com.taobao.weex.bridge.JSCallback callback) {
    }
    
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    public final void play(@org.jetbrains.annotations.Nullable()
    com.taobao.weex.bridge.JSCallback callback) {
    }
    
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    public final void pause() {
    }
    
    @com.taobao.weex.annotation.JSMethod(uiThread = true)
    public final void stop() {
    }
    
    public LottieComponent(@org.jetbrains.annotations.Nullable()
    com.taobao.weex.WXSDKInstance instance, @org.jetbrains.annotations.Nullable()
    com.taobao.weex.ui.component.WXVContainer<?> parent, @org.jetbrains.annotations.Nullable()
    com.taobao.weex.ui.action.BasicComponentData<?> basicComponentData) {
        super(null, null, null);
    }
}