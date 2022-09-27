package com.ucmed.mrdc.tslimage;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;
import com.taobao.weex.ui.SimpleComponentHolder;
import com.ucmed.mrdc.tslimage.zoomImage.zoomImage;


/**
 * Created by WX-GXM-1326 on 2018/4/25.
 */

public class TeslaImageProvider extends ContentProvider {
    @Override
    public boolean onCreate() {
        try {
            WXSDKEngine.registerComponent(new SimpleComponentHolder(zoomImage.class,new zoomImage.Creator()),false,"zoom-img");
            WXSDKEngine.registerModule("imgUtil",ImageUtilModule.class);
        } catch (WXException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void attachInfo(Context context, ProviderInfo info) {
        super.attachInfo(context, info);
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
