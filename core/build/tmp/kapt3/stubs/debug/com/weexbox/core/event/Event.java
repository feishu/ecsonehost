package com.weexbox.core.event;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u000b\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005\u00a2\u0006\u0002\u0010\u0002R(\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\u00a8\u0006\u0010"}, d2 = {"Lcom/weexbox/core/event/Event;", "", "()V", "info", "", "", "getInfo", "()Ljava/util/Map;", "setInfo", "(Ljava/util/Map;)V", "name", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "Companion", "core_debug"})
public final class Event {
    @org.jetbrains.annotations.NotNull()
    public java.lang.String name;
    @org.jetbrains.annotations.Nullable()
    private java.util.Map<java.lang.String, ? extends java.lang.Object> info;
    public static final com.weexbox.core.event.Event.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getName() {
        return null;
    }
    
    public final void setName(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.Map<java.lang.String, java.lang.Object> getInfo() {
        return null;
    }
    
    public final void setInfo(@org.jetbrains.annotations.Nullable()
    java.util.Map<java.lang.String, ? extends java.lang.Object> p0) {
    }
    
    public Event() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0001\u0018\u00010\bJ<\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00062$\u0010\f\u001a \u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0001\u0018\u00010\b\u0012\u0004\u0012\u00020\u00040\rj\u0002`\u000eJ<\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u00062$\u0010\f\u001a \u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0001\u0018\u00010\b\u0012\u0004\u0012\u00020\u00040\rj\u0002`\u000eJ\u0016\u0010\u0010\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010\u0010\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0011\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\u0011\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000f\u00a8\u0006\u0012"}, d2 = {"Lcom/weexbox/core/event/Event$Companion;", "", "()V", "emit", "", "name", "", "info", "", "register", "target", "Lcom/weexbox/core/controller/WBBaseActivity;", "callback", "Lkotlin/Function1;", "Lcom/weexbox/core/event/EventCallback;", "Lcom/weexbox/core/controller/WBBaseFragment;", "unregister", "unregisterAll", "core_debug"})
    public static final class Companion {
        
        public final void register(@org.jetbrains.annotations.NotNull()
        com.weexbox.core.controller.WBBaseFragment target, @org.jetbrains.annotations.NotNull()
        java.lang.String name, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super java.util.Map<java.lang.String, ? extends java.lang.Object>, kotlin.Unit> callback) {
        }
        
        public final void unregister(@org.jetbrains.annotations.NotNull()
        com.weexbox.core.controller.WBBaseFragment target, @org.jetbrains.annotations.NotNull()
        java.lang.String name) {
        }
        
        public final void unregisterAll(@org.jetbrains.annotations.NotNull()
        com.weexbox.core.controller.WBBaseFragment target) {
        }
        
        public final void emit(@org.jetbrains.annotations.NotNull()
        java.lang.String name, @org.jetbrains.annotations.Nullable()
        java.util.Map<java.lang.String, ? extends java.lang.Object> info) {
        }
        
        public final void register(@org.jetbrains.annotations.NotNull()
        com.weexbox.core.controller.WBBaseActivity target, @org.jetbrains.annotations.NotNull()
        java.lang.String name, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super java.util.Map<java.lang.String, ? extends java.lang.Object>, kotlin.Unit> callback) {
        }
        
        public final void unregister(@org.jetbrains.annotations.NotNull()
        com.weexbox.core.controller.WBBaseActivity target, @org.jetbrains.annotations.NotNull()
        java.lang.String name) {
        }
        
        public final void unregisterAll(@org.jetbrains.annotations.NotNull()
        com.weexbox.core.controller.WBBaseActivity target) {
        }
        
        private Companion() {
            super();
        }
    }
}