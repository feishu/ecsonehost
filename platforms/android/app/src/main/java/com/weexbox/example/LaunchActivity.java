package com.weexbox.example;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
//import android.support.annotation.NonNull;
//import android.support.v4.app.NotificationManagerCompat;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationManagerCompat;
import android.util.Log;
import android.view.View;

import com.taobao.weex.utils.WXLogUtils;
import com.ucmed.pushcenterlib.PushCenterManager;
import com.ucmed.pushcenterlib.PushResultCallBack;
import com.weexbox.core.controller.WBBaseActivity;
import com.weexbox.core.util.ToastUtil;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.provider.Settings.EXTRA_APP_PACKAGE;
import static android.provider.Settings.EXTRA_CHANNEL_ID;

public class LaunchActivity extends WBBaseActivity {
    LaunchFragment launchFragment ;
    boolean b = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getRouter().setNavBarHidden(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        launchFragment = new LaunchFragment();
        getActionbar().setVisibility(View.GONE);
        hideStatusbarLayoutBackground();

    }

    /**
     * 作者：CnPeng
     * 时间：2018/7/12 上午9:02
     * 功用：检查是否已经开启了通知权限
     * 说明：
     */
    private void checkNotifySetting() {
        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        // areNotificationsEnabled方法的有效性官方只最低支持到API 19，低于19的仍可调用此方法不过只会返回true，即默认为用户已经开启了通知。
        boolean isOpened = manager.areNotificationsEnabled();
        if (isOpened) {
//            mBinding.tvMsg.setText("通知权限已经被打开" +
//                    "\n手机型号:" + android.os.Build.MODEL +
//                    "\nSDK版本:" + android.os.Build.VERSION.SDK +
//                    "\n系统版本:" + android.os.Build.VERSION.RELEASE +
//                    "\n软件包名:" + getPackageName());
//            return true;
            gotoApp();
        } else {
//            mBinding.tvMsg.setText("还没有开启通知权限，点击去开启");
//            return false;
            ToastUtil.showShortToast(this,"请打开通知开关");
            gotoSetting();
        }
    }

    /**
     * 作者：CnPeng
     * 时间：2018/7/12 上午8:02
     * 功用：初始化点击事件
     * 说明：
     */
    private void gotoSetting() {
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= 26) {
            // android 8.0引导
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("android.provider.extra.APP_PACKAGE", getPackageName());
        } else if (Build.VERSION.SDK_INT >= 21) {
            // android 5.0-7.0
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("app_package", getPackageName());
            intent.putExtra("app_uid", getApplicationInfo().uid);
        } else {
            // 其他
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", getPackageName(), null));
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        checkAppVersion();
        if(b){
            gotoApp();
        }else {
            b = true;
            checkNotifySetting();
        }
    }

    private void gotoApp(){
        AndPermission.with(this).requestCode(100).permission(CAMERA,
                READ_PHONE_STATE,
                WRITE_EXTERNAL_STORAGE,
                ACCESS_FINE_LOCATION,
                ACCESS_COARSE_LOCATION).rationale(new RationaleListener() {
            @Override
            public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                AndPermission.rationaleDialog(LaunchActivity.this,rationale).show();
            }
        }).callback(new PermissionListener() {
            @Override
            public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                getSupportFragmentManager().beginTransaction().replace(R.id.launchFragment, launchFragment).commitAllowingStateLoss();
            }

            @Override
            public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                WXLogUtils.w("AndPermission,onFailed");
                ToastUtil.showLongToast(LaunchActivity.this, "Permission request Failed");
            }
        }).start();

        Map map = new HashMap();
        ApplicationInfo appInfo = null;
        try {
            appInfo = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            if (PushCenterManager.getInstance().getSDKtype().toLowerCase().equals("oppo")) {
                map.put("OPPO_appKey", appInfo.metaData.getString("OPPO_appKey"));
                map.put("OPPO_appSecret", appInfo.metaData.getString("OPPO_appSecret"));
            }
            if(PushCenterManager.getInstance().getSDKtype().toLowerCase().equals("vivo")){
                map.put("Mi_APP_ID", appInfo.metaData.getString("Mi_APP_ID"));
                map.put("Mi_APP_KEY", appInfo.metaData.getString("Mi_APP_KEY"));
            }
            if(PushCenterManager.getInstance().getManufacturer().toLowerCase().toLowerCase().equals("xiaomi")){
                map.put("MZ_APP_ID", appInfo.metaData.getString("MZ_APP_ID"));
                map.put("MZ_APP_KEY", appInfo.metaData.getString("MZ_APP_KEY"));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        PushCenterManager.getInstance().getPushCenterInterface().register(this, map, new PushResultCallBack() {
            @Override
            public void success(Map map) {
                WXLogUtils.i(map.toString());
            }

            @Override
            public void failure(Map map) {
                WXLogUtils.i(map.toString());
            }
        });
    }
}

