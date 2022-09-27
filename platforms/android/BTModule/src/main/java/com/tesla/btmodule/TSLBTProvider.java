package com.tesla.btmodule;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;


/**
 * Created by WX-GXM-1326 on 2018/4/16.
 */

public class TSLBTProvider extends ContentProvider {
    @Override
    public boolean onCreate() {
        try {
            WXSDKEngine.registerModule("bt", BlueToothModule.class);
            WXSDKEngine.registerModule("bluetooth", BlueToothModule.class);
            WXSDKEngine.registerModule("ble", FastBleModule.class);
        } catch (WXException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
