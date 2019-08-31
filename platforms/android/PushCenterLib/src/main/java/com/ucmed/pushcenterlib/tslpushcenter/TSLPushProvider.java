package com.ucmed.pushcenterlib.tslpushcenter;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;
import com.ucmed.pushcenterlib.PushCenterManager;


/**
 * Created by WX-GXM-1326 on 2018/4/16.
 */

public class TSLPushProvider extends ContentProvider {
    @Override
    public boolean onCreate() {
        try {
            WXSDKEngine.registerModule("push", TSLPushCenterModule.class);
            ApplicationInfo appInfo = null;
            try {
                appInfo = getContext().getPackageManager().getApplicationInfo(getContext().getPackageName(), PackageManager.GET_META_DATA);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            PushCenterManager.getInstance().setHaveOPPO(!TextUtils.isEmpty(appInfo.metaData.getString("OPPO_appKey")) && !TextUtils.isEmpty(appInfo.metaData.getString("OPPO_appSecret")));
            PushCenterManager.getInstance().setHaveVIVO(!TextUtils.isEmpty(appInfo.metaData.getString("VIVO_APP_ID")) && !TextUtils.isEmpty(appInfo.metaData.getString("VIVO_APP_KEY")));
            PushCenterManager.getInstance().setHaveHW(!TextUtils.isEmpty(appInfo.metaData.getString("HW_APPID")));
            PushCenterManager.getInstance().setHaveMi(!TextUtils.isEmpty(appInfo.metaData.getString("Mi_APP_ID")) && !TextUtils.isEmpty(appInfo.metaData.getString("Mi_APP_KEY")));
            PushCenterManager.getInstance().setHaveMz(!TextUtils.isEmpty(appInfo.metaData.getString("MZ_APP_ID")) && !TextUtils.isEmpty(appInfo.metaData.getString("MZ_APP_KEY")));

            String msg = PushCenterManager.getInstance().isHaveOPPO() + "--" +
                    PushCenterManager.getInstance().isHaveVIVO() + "--" +
                    PushCenterManager.getInstance().isHaveHW() + "--" +
                    PushCenterManager.getInstance().isHaveMi() + "--" +
                    PushCenterManager.getInstance().isHaveMz() + "--";
            Log.i("push", msg);
            PushCenterManager.getInstance().defaultInit(getContext(), null);
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
