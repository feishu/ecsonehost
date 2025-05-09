package com.weexbox.core.router;

import java.lang.System;

/**
 * Author: Mario
 * Time: 2018/8/14 下午6:44
 * Description: This is Router
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 :2\u00020\u0001:\u0001:B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001f\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002022\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0002\u00104J\u000e\u00105\u001a\u0002002\u0006\u00101\u001a\u000202J\u0014\u00106\u001a\u0002002\f\u00107\u001a\b\u0012\u0004\u0012\u00020908R\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\n\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u000b\u0010\u0006\"\u0004\b\f\u0010\bR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0010\"\u0004\b\u001e\u0010\u0012R(\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020!\u0018\u00010 X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001c\u0010&\u001a\u0004\u0018\u00010\u0017X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\'\u0010\u0019\"\u0004\b(\u0010\u001bR\u001a\u0010)\u001a\u00020\u0017X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u0019\"\u0004\b+\u0010\u001bR\u001c\u0010,\u001a\u0004\u0018\u00010\u0017X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u0019\"\u0004\b.\u0010\u001b\u00a8\u0006;"}, d2 = {"Lcom/weexbox/core/router/Router;", "Ljava/io/Serializable;", "()V", "closeCount", "", "getCloseCount", "()Ljava/lang/Integer;", "setCloseCount", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "closeFrom", "getCloseFrom", "setCloseFrom", "closeFromBottomToTop", "", "getCloseFromBottomToTop", "()Z", "setCloseFromBottomToTop", "(Z)V", "disableGestureBack", "getDisableGestureBack", "setDisableGestureBack", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "navBarHidden", "getNavBarHidden", "setNavBarHidden", "params", "", "", "getParams", "()Ljava/util/Map;", "setParams", "(Ljava/util/Map;)V", "title", "getTitle", "setTitle", "type", "getType", "setType", "url", "getUrl", "setUrl", "close", "", "from", "Lcom/weexbox/core/controller/WBBaseActivity;", "count", "(Lcom/weexbox/core/controller/WBBaseActivity;Ljava/lang/Integer;)V", "open", "removeActivitys", "activities", "", "Landroid/app/Activity;", "Companion", "core_debug"})
public final class Router implements java.io.Serializable {
    @org.jetbrains.annotations.Nullable()
    private java.lang.String name;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String url;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String type;
    private boolean navBarHidden;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String title;
    private boolean disableGestureBack;
    @org.jetbrains.annotations.Nullable()
    private java.util.Map<java.lang.String, ? extends java.lang.Object> params;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Integer closeFrom;
    private boolean closeFromBottomToTop;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Integer closeCount;
    @org.jetbrains.annotations.NotNull()
    private static java.util.TreeMap<java.lang.String, java.lang.Class<? extends com.weexbox.core.controller.WBBaseActivity>> routes;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_NAME = "WeexBoxRouter";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TYPE_PUSH = "push";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TYPE_PRESENT = "present";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TYPE_MODALMASK = "modalMask";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String NAME_FLUTTER = "flutter";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String NAME_WEEX = "weex";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String NAME_WEB = "web";
    public static final com.weexbox.core.router.Router.Companion Companion = null;
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getName() {
        return null;
    }
    
    public final void setName(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getUrl() {
        return null;
    }
    
    public final void setUrl(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getType() {
        return null;
    }
    
    public final void setType(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final boolean getNavBarHidden() {
        return false;
    }
    
    public final void setNavBarHidden(boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getTitle() {
        return null;
    }
    
    public final void setTitle(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    public final boolean getDisableGestureBack() {
        return false;
    }
    
    public final void setDisableGestureBack(boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.Map<java.lang.String, java.lang.Object> getParams() {
        return null;
    }
    
    public final void setParams(@org.jetbrains.annotations.Nullable()
    java.util.Map<java.lang.String, ? extends java.lang.Object> p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getCloseFrom() {
        return null;
    }
    
    public final void setCloseFrom(@org.jetbrains.annotations.Nullable()
    java.lang.Integer p0) {
    }
    
    public final boolean getCloseFromBottomToTop() {
        return false;
    }
    
    public final void setCloseFromBottomToTop(boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getCloseCount() {
        return null;
    }
    
    public final void setCloseCount(@org.jetbrains.annotations.Nullable()
    java.lang.Integer p0) {
    }
    
    public final void open(@org.jetbrains.annotations.NotNull()
    com.weexbox.core.controller.WBBaseActivity from) {
    }
    
    public final void removeActivitys(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends android.app.Activity> activities) {
    }
    
    public final void close(@org.jetbrains.annotations.NotNull()
    com.weexbox.core.controller.WBBaseActivity from, @org.jetbrains.annotations.Nullable()
    java.lang.Integer count) {
    }
    
    public Router() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00042\u000e\u0010\u0016\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000e0\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R.\u0010\u000b\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u000e0\r0\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012\u00a8\u0006\u0017"}, d2 = {"Lcom/weexbox/core/router/Router$Companion;", "", "()V", "EXTRA_NAME", "", "NAME_FLUTTER", "NAME_WEB", "NAME_WEEX", "TYPE_MODALMASK", "TYPE_PRESENT", "TYPE_PUSH", "routes", "Ljava/util/TreeMap;", "Ljava/lang/Class;", "Lcom/weexbox/core/controller/WBBaseActivity;", "getRoutes", "()Ljava/util/TreeMap;", "setRoutes", "(Ljava/util/TreeMap;)V", "register", "", "name", "controller", "core_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.TreeMap<java.lang.String, java.lang.Class<? extends com.weexbox.core.controller.WBBaseActivity>> getRoutes() {
            return null;
        }
        
        public final void setRoutes(@org.jetbrains.annotations.NotNull()
        java.util.TreeMap<java.lang.String, java.lang.Class<? extends com.weexbox.core.controller.WBBaseActivity>> p0) {
        }
        
        public final void register(@org.jetbrains.annotations.NotNull()
        java.lang.String name, @org.jetbrains.annotations.NotNull()
        java.lang.Class<? extends com.weexbox.core.controller.WBBaseActivity> controller) {
        }
        
        private Companion() {
            super();
        }
    }
}