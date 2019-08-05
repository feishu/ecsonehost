package com.weexbox.example;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.taobao.weex.utils.WXLogUtils;
import com.weexbox.core.controller.WBBaseActivity;
import com.weexbox.core.util.ToastUtil;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import org.jetbrains.annotations.Nullable;

import java.util.List;

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
