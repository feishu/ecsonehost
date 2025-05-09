package com.weexbox.core.controller;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010/\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u0013H\u0002J\b\u00100\u001a\u000201H&J\b\u00102\u001a\u00020\fH\u0016J\u0012\u00103\u001a\u00020\f2\b\u00104\u001a\u0004\u0018\u000105H\u0016J&\u00106\u001a\u0004\u0018\u00010$2\u0006\u00107\u001a\u0002082\b\u00109\u001a\u0004\u0018\u00010:2\b\u00104\u001a\u0004\u0018\u000105H\u0016J\b\u0010;\u001a\u00020\fH\u0016J\u0010\u0010<\u001a\u00020\f2\u0006\u0010=\u001a\u00020>H\u0007J\u0010\u0010?\u001a\u00020\f2\u0006\u0010@\u001a\u00020\u0013H\u0016J\b\u0010A\u001a\u00020\fH\u0016J\b\u0010B\u001a\u00020\fH\u0016J\u0010\u0010C\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u0013H\u0016J\u0010\u0010D\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u0013H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006RD\u0010\u0007\u001a,\u0012\u0004\u0012\u00020\u0004\u0012\"\u0012 \u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n\u0012\u0004\u0012\u00020\f0\tj\u0002`\r0\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u00020\u0013X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\u00020\u0013X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0016\"\u0004\b\u001c\u0010\u0018R\u001a\u0010\u001d\u001a\u00020\u001eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020$X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b\'\u0010(R\u001a\u0010)\u001a\u00020*X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.\u00a8\u0006E"}, d2 = {"Lcom/weexbox/core/controller/WBBaseFragment;", "Landroidx/fragment/app/Fragment;", "()V", "backName", "", "getBackName", "()Ljava/lang/String;", "events", "", "Lkotlin/Function1;", "", "", "", "Lcom/weexbox/core/event/EventCallback;", "getEvents", "()Ljava/util/Map;", "setEvents", "(Ljava/util/Map;)V", "isFirstResume", "", "isHiddenChanged", "isListenBack", "()Z", "setListenBack", "(Z)V", "isOnCreateView", "isSetUserVisibleHint", "isVisibleToUser", "setVisibleToUser", "loadDialogHelper", "Lcom/weexbox/core/util/LoadDialogHelper;", "getLoadDialogHelper", "()Lcom/weexbox/core/util/LoadDialogHelper;", "setLoadDialogHelper", "(Lcom/weexbox/core/util/LoadDialogHelper;)V", "rootView", "Landroid/view/View;", "getRootView", "()Landroid/view/View;", "setRootView", "(Landroid/view/View;)V", "router", "Lcom/weexbox/core/router/Router;", "getRouter", "()Lcom/weexbox/core/router/Router;", "setRouter", "(Lcom/weexbox/core/router/Router;)V", "changeVisibleToUser", "getLayoutId", "", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroy", "onEvent", "event", "Lcom/weexbox/core/event/Event;", "onHiddenChanged", "hidden", "onPause", "onResume", "onVisibleToUserChanged", "setUserVisibleHint", "core_debug"})
public abstract class WBBaseFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.NotNull()
    private com.weexbox.core.router.Router router;
    @org.jetbrains.annotations.NotNull()
    private java.util.Map<java.lang.String, kotlin.jvm.functions.Function1<java.util.Map<java.lang.String, ? extends java.lang.Object>, kotlin.Unit>> events;
    private boolean isListenBack;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String backName = "WB-onBackPressed";
    @org.jetbrains.annotations.NotNull()
    private com.weexbox.core.util.LoadDialogHelper loadDialogHelper;
    private boolean isVisibleToUser;
    private boolean isOnCreateView;
    private boolean isSetUserVisibleHint;
    private boolean isHiddenChanged;
    private boolean isFirstResume;
    @org.jetbrains.annotations.NotNull()
    public android.view.View rootView;
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
    
    public final boolean isListenBack() {
        return false;
    }
    
    public final void setListenBack(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getBackName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.weexbox.core.util.LoadDialogHelper getLoadDialogHelper() {
        return null;
    }
    
    public final void setLoadDialogHelper(@org.jetbrains.annotations.NotNull()
    com.weexbox.core.util.LoadDialogHelper p0) {
    }
    
    public final boolean isVisibleToUser() {
        return false;
    }
    
    public final void setVisibleToUser(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.view.View getRootView() {
        return null;
    }
    
    public final void setRootView(@org.jetbrains.annotations.NotNull()
    android.view.View p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    public abstract int getLayoutId();
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onEvent(@org.jetbrains.annotations.NotNull()
    com.weexbox.core.event.Event event) {
    }
    
    public void onBackPressed() {
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    @java.lang.Override()
    public void onPause() {
    }
    
    @java.lang.Override()
    public void setUserVisibleHint(boolean isVisibleToUser) {
    }
    
    @java.lang.Override()
    public void onHiddenChanged(boolean hidden) {
    }
    
    private final void changeVisibleToUser(boolean isVisibleToUser) {
    }
    
    public void onVisibleToUserChanged(boolean isVisibleToUser) {
    }
    
    public WBBaseFragment() {
        super();
    }
}