package com.weexbox.example;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import static android.Manifest.permission.READ_PHONE_STATE;

public class LaunchActivity extends WBBaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getRouter().setNavBarHidden(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        LaunchFragment launchFragment = new LaunchFragment();
        getActionbar().setVisibility(View.GONE);
        hideStatusbarLayoutBackground();

        AndPermission.with(this).requestCode(100).permission(READ_PHONE_STATE).rationale(new RationaleListener() {
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

/**
 * Author: Mario
 * Time: 2018/12/11 11:22 AM
 * Description: This is LaunchActivity
 */

//class LaunchActivity : WBBaseActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        router.navBarHidden = true
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_launch)
//        val launchFragment = LaunchFragment()
//        getActionbar().visibility = View.GONE
//        hideStatusbarLayoutBackground()
//
//        AndPermission.with(this).requestCode(100).permission(READ_PHONE_STATE).rationale { requestCode, rationale ->
//            AndPermission.rationaleDialog(this, rationale).show()
//        }.callback(object : PermissionListener {
//            override fun onSucceed(requestCode: Int, grantPermissions: List<String>) {
//                supportFragmentManager.beginTransaction().replace(R.id.launchFragment, launchFragment).commitAllowingStateLoss()
//            }
//
//            override fun onFailed(requestCode: Int, deniedPermissions: List<String>) {
//                WXLogUtils.w("AndPermission,onFailed")
//                ToastUtil.showLongToast(this@LaunchActivity, "Permission request Failed")
//            }
//        }).start()
//
//    }
//}
