package com.ucmed.pushcenterlib.XgPush;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;


/**
 * Created by WX-GXM-1326 on 2018/4/16.
 */

public class TSLXgPushProvider extends ContentProvider {
    @Override
    public boolean onCreate() {
        XGPushUtil.init(getContext(), getContext().getResources().getIdentifier("ic_launcher", "mipmap", getContext().getPackageName()), new XGIOperateCallback() {
            @Override
            public void onSuccess(Object o, int i) {
                Log.e("WXApplication",
                        "信鸽推送注册成功,token=" + XGPushConfig.getToken(getContext()));
            }

            @Override
            public void onFail(Object o, int i, String s) {
                Log.e("WXApplication",
                        "信鸽推送注册失败,i=" + i + ",s=" + s);
            }
        });
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
