package com.weexbox.core.model;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u0010\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR \u0010\t\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\b\u00a8\u0006\f"}, d2 = {"Lcom/weexbox/core/model/Md5Realm;", "Lio/realm/RealmObject;", "()V", "md5", "", "getMd5", "()Ljava/lang/String;", "setMd5", "(Ljava/lang/String;)V", "path", "getPath", "setPath", "core_debug"})
public class Md5Realm extends io.realm.RealmObject {
    @org.jetbrains.annotations.Nullable()
    @io.realm.annotations.PrimaryKey()
    private java.lang.String path;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String md5;
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getPath() {
        return null;
    }
    
    public final void setPath(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getMd5() {
        return null;
    }
    
    public final void setMd5(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    public Md5Realm() {
        super();
    }
}