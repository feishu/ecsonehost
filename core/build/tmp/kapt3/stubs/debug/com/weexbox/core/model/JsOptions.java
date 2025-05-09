package com.weexbox.core.model;

import java.lang.System;

/**
 * Author: Mario
 * Time: 2018/9/11 下午7:45
 * Description: This is JsOptions
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b:\b\u0016\u0018\u00002\u00020\u0001:\u0001aB\u0005\u00a2\u0006\u0002\u0010\u0002R$\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001e\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010 \u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010!\u001a\u00020\"X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R(\u0010\'\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f\u0018\u00010(X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001a\u0010-\u001a\u00020\u0015X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u0017\"\u0004\b/\u0010\u0019R\u001a\u00100\u001a\u00020\"X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b0\u0010$\"\u0004\b1\u0010&R\u001e\u00102\u001a\u0004\u0018\u00010\"X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u00106\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u001c\u00107\u001a\u0004\u0018\u00010\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b8\u0010\u000e\"\u0004\b9\u0010\u0010R\u001c\u0010:\u001a\u0004\u0018\u00010\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b;\u0010\u000e\"\u0004\b<\u0010\u0010R\u001e\u0010=\u001a\u0004\u0018\u00010\u0015X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010B\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\u001c\u0010C\u001a\u0004\u0018\u00010\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bD\u0010\u000e\"\u0004\bE\u0010\u0010R\u001c\u0010F\u001a\u0004\u0018\u00010\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bG\u0010\u000e\"\u0004\bH\u0010\u0010R(\u0010I\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u0001\u0018\u00010(X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010*\"\u0004\bK\u0010,R\u001c\u0010L\u001a\u0004\u0018\u00010\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bM\u0010\u000e\"\u0004\bN\u0010\u0010R\u001e\u0010O\u001a\u0004\u0018\u00010\u0015X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010B\u001a\u0004\bP\u0010?\"\u0004\bQ\u0010AR\u001c\u0010R\u001a\u0004\u0018\u00010\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bS\u0010\u000e\"\u0004\bT\u0010\u0010R\u001c\u0010U\u001a\u0004\u0018\u00010\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bV\u0010\u000e\"\u0004\bW\u0010\u0010R\u001c\u0010X\u001a\u0004\u0018\u00010\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bY\u0010\u000e\"\u0004\bZ\u0010\u0010R\u001c\u0010[\u001a\u0004\u0018\u00010\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\\\u0010\u000e\"\u0004\b]\u0010\u0010R\u001a\u0010^\u001a\u00020\u0015X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b_\u0010\u0017\"\u0004\b`\u0010\u0019\u00a8\u0006b"}, d2 = {"Lcom/weexbox/core/model/JsOptions;", "", "()V", "actions", "", "Lcom/weexbox/core/model/JsOptions$ActionSheet;", "getActions", "()[Lcom/weexbox/core/model/JsOptions$ActionSheet;", "setActions", "([Lcom/weexbox/core/model/JsOptions$ActionSheet;)V", "[Lcom/weexbox/core/model/JsOptions$ActionSheet;", "cancelTitle", "", "getCancelTitle", "()Ljava/lang/String;", "setCancelTitle", "(Ljava/lang/String;)V", "color", "getColor", "setColor", "count", "", "getCount", "()I", "setCount", "(I)V", "duration", "", "getDuration", "()Ljava/lang/Double;", "setDuration", "(Ljava/lang/Double;)V", "Ljava/lang/Double;", "enableCrop", "", "getEnableCrop", "()Z", "setEnableCrop", "(Z)V", "headers", "", "getHeaders", "()Ljava/util/Map;", "setHeaders", "(Ljava/util/Map;)V", "height", "getHeight", "setHeight", "isCircle", "setCircle", "isSecure", "()Ljava/lang/Boolean;", "setSecure", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "message", "getMessage", "setMessage", "method", "getMethod", "setMethod", "millisecond", "getMillisecond", "()Ljava/lang/Integer;", "setMillisecond", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "name", "getName", "setName", "okTitle", "getOkTitle", "setOkTitle", "params", "getParams", "setParams", "placeholder", "getPlaceholder", "setPlaceholder", "progress", "getProgress", "setProgress", "responseType", "getResponseType", "setResponseType", "text", "getText", "setText", "title", "getTitle", "setTitle", "url", "getUrl", "setUrl", "width", "getWidth", "setWidth", "ActionSheet", "core_debug"})
public class JsOptions {
    @org.jetbrains.annotations.Nullable()
    private java.lang.String url;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String method;
    @org.jetbrains.annotations.Nullable()
    private java.util.Map<java.lang.String, java.lang.String> headers;
    @org.jetbrains.annotations.Nullable()
    private java.util.Map<java.lang.String, ? extends java.lang.Object> params;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String responseType;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String text;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Integer progress;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String title;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String message;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String okTitle;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String cancelTitle;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String placeholder;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Boolean isSecure;
    @org.jetbrains.annotations.Nullable()
    private com.weexbox.core.model.JsOptions.ActionSheet[] actions;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Double duration;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Integer millisecond;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String name;
    private boolean enableCrop;
    private boolean isCircle;
    private int width;
    private int height;
    private int count;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String color;
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getUrl() {
        return null;
    }
    
    public final void setUrl(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getMethod() {
        return null;
    }
    
    public final void setMethod(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.Map<java.lang.String, java.lang.String> getHeaders() {
        return null;
    }
    
    public final void setHeaders(@org.jetbrains.annotations.Nullable()
    java.util.Map<java.lang.String, java.lang.String> p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.Map<java.lang.String, java.lang.Object> getParams() {
        return null;
    }
    
    public final void setParams(@org.jetbrains.annotations.Nullable()
    java.util.Map<java.lang.String, ? extends java.lang.Object> p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getResponseType() {
        return null;
    }
    
    public final void setResponseType(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getText() {
        return null;
    }
    
    public final void setText(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getProgress() {
        return null;
    }
    
    public final void setProgress(@org.jetbrains.annotations.Nullable()
    java.lang.Integer p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getTitle() {
        return null;
    }
    
    public final void setTitle(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getMessage() {
        return null;
    }
    
    public final void setMessage(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getOkTitle() {
        return null;
    }
    
    public final void setOkTitle(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCancelTitle() {
        return null;
    }
    
    public final void setCancelTitle(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getPlaceholder() {
        return null;
    }
    
    public final void setPlaceholder(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean isSecure() {
        return null;
    }
    
    public final void setSecure(@org.jetbrains.annotations.Nullable()
    java.lang.Boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.weexbox.core.model.JsOptions.ActionSheet[] getActions() {
        return null;
    }
    
    public final void setActions(@org.jetbrains.annotations.Nullable()
    com.weexbox.core.model.JsOptions.ActionSheet[] p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getDuration() {
        return null;
    }
    
    public final void setDuration(@org.jetbrains.annotations.Nullable()
    java.lang.Double p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getMillisecond() {
        return null;
    }
    
    public final void setMillisecond(@org.jetbrains.annotations.Nullable()
    java.lang.Integer p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getName() {
        return null;
    }
    
    public final void setName(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    public final boolean getEnableCrop() {
        return false;
    }
    
    public final void setEnableCrop(boolean p0) {
    }
    
    public final boolean isCircle() {
        return false;
    }
    
    public final void setCircle(boolean p0) {
    }
    
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
    
    public final int getCount() {
        return 0;
    }
    
    public final void setCount(int p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getColor() {
        return null;
    }
    
    public final void setColor(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    public JsOptions() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\b\u00a8\u0006\f"}, d2 = {"Lcom/weexbox/core/model/JsOptions$ActionSheet;", "", "()V", "title", "", "getTitle", "()Ljava/lang/String;", "setTitle", "(Ljava/lang/String;)V", "type", "getType", "setType", "core_debug"})
    public static final class ActionSheet {
        @org.jetbrains.annotations.Nullable()
        private java.lang.String title;
        @org.jetbrains.annotations.Nullable()
        private java.lang.String type;
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getTitle() {
            return null;
        }
        
        public final void setTitle(@org.jetbrains.annotations.Nullable()
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getType() {
            return null;
        }
        
        public final void setType(@org.jetbrains.annotations.Nullable()
        java.lang.String p0) {
        }
        
        public ActionSheet() {
            super();
        }
    }
}