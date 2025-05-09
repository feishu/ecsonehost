package com.weexbox.core.adapter;

import java.lang.System;

/**
 * Author: Mario
 * Time: 2019/2/25 4:03 PM
 * Description: This is WebSocketAdapter
 */
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J&\u0010\r\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010\u0011\u001a\u00020\bH\u0016J\u0012\u0010\u0012\u001a\u00020\b2\b\u0010\u0013\u001a\u0004\u0018\u00010\fH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/weexbox/core/adapter/WebSocketAdapter;", "Lcom/taobao/weex/appfram/websocket/IWebSocketAdapter;", "()V", "eventListener", "Lcom/taobao/weex/appfram/websocket/IWebSocketAdapter$EventListener;", "ws", "Lcom/squareup/okhttp/ws/WebSocket;", "close", "", "code", "", "reason", "", "connect", "url", "protocol", "listener", "destroy", "send", "data", "core_debug"})
public final class WebSocketAdapter implements com.taobao.weex.appfram.websocket.IWebSocketAdapter {
    private com.squareup.okhttp.ws.WebSocket ws;
    private com.taobao.weex.appfram.websocket.IWebSocketAdapter.EventListener eventListener;
    
    @java.lang.Override()
    public void connect(@org.jetbrains.annotations.Nullable()
    java.lang.String url, @org.jetbrains.annotations.Nullable()
    java.lang.String protocol, @org.jetbrains.annotations.Nullable()
    com.taobao.weex.appfram.websocket.IWebSocketAdapter.EventListener listener) {
    }
    
    @java.lang.Override()
    public void send(@org.jetbrains.annotations.Nullable()
    java.lang.String data) {
    }
    
    @java.lang.Override()
    public void close(int code, @org.jetbrains.annotations.Nullable()
    java.lang.String reason) {
    }
    
    @java.lang.Override()
    public void destroy() {
    }
    
    public WebSocketAdapter() {
        super();
    }
}