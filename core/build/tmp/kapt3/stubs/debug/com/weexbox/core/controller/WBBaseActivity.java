package com.weexbox.core.controller;

import java.lang.System;

/**
 * Author: Mario
 * Time: 2018/8/14 下午6:39
 * Description: This is WBBaseActivity
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0016\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010-\u001a\u00020(H\u0016J\b\u0010.\u001a\u0004\u0018\u00010/J\u0018\u00100\u001a\u0004\u0018\u00010/2\f\u00101\u001a\b\u0012\u0004\u0012\u00020302H\u0002J\b\u00104\u001a\u00020\tH\u0016J\u0006\u00105\u001a\u00020\tJ\"\u00106\u001a\u00020\t2\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u0002082\b\u0010:\u001a\u0004\u0018\u00010;H\u0014J\b\u0010<\u001a\u00020\tH\u0016J\u0012\u0010=\u001a\u00020\t2\b\u0010>\u001a\u0004\u0018\u00010?H\u0014J\b\u0010@\u001a\u00020\tH\u0014J\u0010\u0010A\u001a\u00020\t2\u0006\u0010B\u001a\u00020CH\u0007J\u0010\u0010D\u001a\u00020\t2\u0006\u0010E\u001a\u00020\u0005H\u0002J\b\u0010F\u001a\u00020\tH\u0002J\u0010\u0010G\u001a\u00020\t2\u0006\u0010E\u001a\u00020\u0005H\u0002J\u0010\u0010H\u001a\u00020\t2\u0006\u0010I\u001a\u000208H\u0016J\b\u0010J\u001a\u00020\tH\u0016RD\u0010\u0003\u001a,\u0012\u0004\u0012\u00020\u0005\u0012\"\u0012 \u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\u0004\u0012\u00020\t0\u0006j\u0002`\n0\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u001cX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001c\u0010!\u001a\u0004\u0018\u00010\"X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001a\u0010\'\u001a\u00020(X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,\u00a8\u0006K"}, d2 = {"Lcom/weexbox/core/controller/WBBaseActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "events", "", "", "Lkotlin/Function1;", "", "", "", "Lcom/weexbox/core/event/EventCallback;", "getEvents", "()Ljava/util/Map;", "setEvents", "(Ljava/util/Map;)V", "floatingDraftButton", "Lcom/weexbox/core/widget/FloatingDraftButton;", "getFloatingDraftButton", "()Lcom/weexbox/core/widget/FloatingDraftButton;", "setFloatingDraftButton", "(Lcom/weexbox/core/widget/FloatingDraftButton;)V", "loadDialogHelper", "Lcom/weexbox/core/util/LoadDialogHelper;", "getLoadDialogHelper", "()Lcom/weexbox/core/util/LoadDialogHelper;", "setLoadDialogHelper", "(Lcom/weexbox/core/util/LoadDialogHelper;)V", "router", "Lcom/weexbox/core/router/Router;", "getRouter", "()Lcom/weexbox/core/router/Router;", "setRouter", "(Lcom/weexbox/core/router/Router;)V", "statusbar_layout", "Landroid/view/View;", "getStatusbar_layout", "()Landroid/view/View;", "setStatusbar_layout", "(Landroid/view/View;)V", "toolbar", "Lcom/weexbox/core/widget/SimpleToolbar;", "getToolbar", "()Lcom/weexbox/core/widget/SimpleToolbar;", "setToolbar", "(Lcom/weexbox/core/widget/SimpleToolbar;)V", "getActionbar", "getFragment", "Lcom/weexbox/core/controller/WBBaseFragment;", "getRecursionFragment", "fragments", "", "Landroidx/fragment/app/Fragment;", "hideStatusbarLayoutBackground", "initFloating", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onEvent", "event", "Lcom/weexbox/core/event/Event;", "openDebugWeex", "url", "openDialog", "openWeex", "setContentView", "layoutResID", "showStatusbarLayoutBackground", "core_debug"})
public class WBBaseActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    private com.weexbox.core.router.Router router;
    @org.jetbrains.annotations.NotNull()
    private java.util.Map<java.lang.String, kotlin.jvm.functions.Function1<java.util.Map<java.lang.String, ? extends java.lang.Object>, kotlin.Unit>> events;
    @org.jetbrains.annotations.NotNull()
    public com.weexbox.core.widget.SimpleToolbar toolbar;
    @org.jetbrains.annotations.Nullable()
    private android.view.View statusbar_layout;
    @org.jetbrains.annotations.NotNull()
    private com.weexbox.core.util.LoadDialogHelper loadDialogHelper;
    @org.jetbrains.annotations.NotNull()
    public com.weexbox.core.widget.FloatingDraftButton floatingDraftButton;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final com.weexbox.core.router.Router getRouter() {
        return null;
    }
    
    public final void setRouter(@org.jetbrains.annotations.NotNull()
    com.weexbox.core.router.Router p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, kotlin.jvm.functions.Function1<java.util.Map<java.lang.String, ? extends java.lang.Object>, kotlin.Unit>> getEvents() {
        return null;
    }
    
    public final void setEvents(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, kotlin.jvm.functions.Function1<java.util.Map<java.lang.String, ? extends java.lang.Object>, kotlin.Unit>> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.weexbox.core.widget.SimpleToolbar getToolbar() {
        return null;
    }
    
    public final void setToolbar(@org.jetbrains.annotations.NotNull()
    com.weexbox.core.widget.SimpleToolbar p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.view.View getStatusbar_layout() {
        return null;
    }
    
    public final void setStatusbar_layout(@org.jetbrains.annotations.Nullable()
    android.view.View p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.weexbox.core.util.LoadDialogHelper getLoadDialogHelper() {
        return null;
    }
    
    public final void setLoadDialogHelper(@org.jetbrains.annotations.NotNull()
    com.weexbox.core.util.LoadDialogHelper p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.weexbox.core.widget.FloatingDraftButton getFloatingDraftButton() {
        return null;
    }
    
    public final void setFloatingDraftButton(@org.jetbrains.annotations.NotNull()
    com.weexbox.core.widget.FloatingDraftButton p0) {
    }
    
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onEvent(@org.jetbrains.annotations.NotNull()
    com.weexbox.core.event.Event event) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.weexbox.core.controller.WBBaseFragment getFragment() {
        return null;
    }
    
    private final com.weexbox.core.controller.WBBaseFragment getRecursionFragment(java.util.List<? extends androidx.fragment.app.Fragment> fragments) {
        return null;
    }
    
    @java.lang.Override()
    public void setContentView(int layoutResID) {
    }
    
    public void showStatusbarLayoutBackground() {
    }
    
    public void hideStatusbarLayoutBackground() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.weexbox.core.widget.SimpleToolbar getActionbar() {
        return null;
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    /**
     * 处理devtool返回的DebugProxyUrl,WX启动devtool模式
     * @param code
     */
    private final void openWeex(java.lang.String url) {
    }
    
    private final void openDebugWeex(java.lang.String url) {
    }
    
    public final void initFloating() {
    }
    
    private final void openDialog() {
    }
    
    public WBBaseActivity() {
        super();
    }
}