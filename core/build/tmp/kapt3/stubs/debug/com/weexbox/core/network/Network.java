package com.weexbox.core.network;

import java.lang.System;

/**
 * Author: Mario
 * Time: 2018/9/11 下午4:46
 * Description: This is Network
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0002 !B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002JH\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u000e2\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\u001a2\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0\u001aH\u0002JR\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0016\u001a\u00020\u000e2\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u0014\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u001a2\u0014\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u001a2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00150\u001fR\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012\u00a8\u0006\""}, d2 = {"Lcom/weexbox/core/network/Network;", "", "()V", "client", "Lokhttp3/OkHttpClient;", "getClient", "()Lokhttp3/OkHttpClient;", "mediaTypeJSON", "Lokhttp3/MediaType;", "getMediaTypeJSON", "()Lokhttp3/MediaType;", "setMediaTypeJSON", "(Lokhttp3/MediaType;)V", "server", "", "getServer", "()Ljava/lang/String;", "setServer", "(Ljava/lang/String;)V", "call", "Lretrofit2/Call;", "Lokhttp3/ResponseBody;", "url", "method", "Lcom/weexbox/core/network/Network$HTTPMethod;", "parameters", "", "headers", "request", "", "callback", "Lretrofit2/Callback;", "HTTPMethod", "Service", "core_debug"})
public final class Network {
    @org.jetbrains.annotations.Nullable()
    private static java.lang.String server;
    @org.jetbrains.annotations.NotNull()
    private static final okhttp3.OkHttpClient client = null;
    @org.jetbrains.annotations.Nullable()
    private static okhttp3.MediaType mediaTypeJSON;
    public static final com.weexbox.core.network.Network INSTANCE = null;
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getServer() {
        return null;
    }
    
    public final void setServer(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final okhttp3.OkHttpClient getClient() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final okhttp3.MediaType getMediaTypeJSON() {
        return null;
    }
    
    public final void setMediaTypeJSON(@org.jetbrains.annotations.Nullable()
    okhttp3.MediaType p0) {
    }
    
    private final retrofit2.Call<okhttp3.ResponseBody> call(java.lang.String url, com.weexbox.core.network.Network.HTTPMethod method, java.util.Map<java.lang.String, ? extends java.lang.Object> parameters, java.util.Map<java.lang.String, java.lang.String> headers) {
        return null;
    }
    
    public final void request(@org.jetbrains.annotations.NotNull()
    java.lang.String url, @org.jetbrains.annotations.NotNull()
    com.weexbox.core.network.Network.HTTPMethod method, @org.jetbrains.annotations.Nullable()
    java.util.Map<java.lang.String, ? extends java.lang.Object> parameters, @org.jetbrains.annotations.Nullable()
    java.util.Map<java.lang.String, java.lang.String> headers, @org.jetbrains.annotations.NotNull()
    retrofit2.Callback<okhttp3.ResponseBody> callback) {
    }
    
    private Network() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/weexbox/core/network/Network$HTTPMethod;", "", "(Ljava/lang/String;I)V", "GET", "POST", "core_debug"})
    public static enum HTTPMethod {
        /*public static final*/ GET /* = new GET() */ /*enum*/ ,
        /*public static final*/ POST /* = new POST() */ /*enum*/ ;
        
        HTTPMethod() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001JD\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u0014\b\u0001\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\b2\u0014\b\u0001\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\bH\'J8\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u000b\u001a\u00020\f2\u0014\b\u0001\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\bH\'\u00a8\u0006\r"}, d2 = {"Lcom/weexbox/core/network/Network$Service;", "", "methodGet", "Lretrofit2/Call;", "Lokhttp3/ResponseBody;", "url", "", "parameters", "", "headers", "methodJson", "body", "Lokhttp3/RequestBody;", "core_debug"})
    public static abstract interface Service {
        
        @org.jetbrains.annotations.NotNull()
        @retrofit2.http.GET()
        public abstract retrofit2.Call<okhttp3.ResponseBody> methodGet(@org.jetbrains.annotations.NotNull()
        @retrofit2.http.Url()
        java.lang.String url, @org.jetbrains.annotations.NotNull()
        @retrofit2.http.QueryMap()
        java.util.Map<java.lang.String, java.lang.Object> parameters, @org.jetbrains.annotations.NotNull()
        @retrofit2.http.HeaderMap()
        java.util.Map<java.lang.String, java.lang.String> headers);
        
        @org.jetbrains.annotations.NotNull()
        @retrofit2.http.POST()
        public abstract retrofit2.Call<okhttp3.ResponseBody> methodJson(@org.jetbrains.annotations.NotNull()
        @retrofit2.http.Url()
        java.lang.String url, @org.jetbrains.annotations.NotNull()
        @retrofit2.http.Body()
        okhttp3.RequestBody body, @org.jetbrains.annotations.NotNull()
        @retrofit2.http.HeaderMap()
        java.util.Map<java.lang.String, java.lang.String> headers);
    }
}