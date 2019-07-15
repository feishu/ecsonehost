package com.weexbox.example

import android.Manifest.permission.READ_PHONE_STATE
import android.os.Bundle
import com.taobao.weex.utils.WXLogUtils
import com.weexbox.core.controller.WBBaseActivity
import com.weexbox.core.util.ToastUtil
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.PermissionListener
import com.yanzhenjie.permission.Rationale
import com.yanzhenjie.permission.RationaleListener


/**
 * Author: Mario
 * Time: 2018/12/11 11:22 AM
 * Description: This is LaunchActivity
 */

class LaunchActivity : WBBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        router.navBarHidden = true
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        val launchFragment = LaunchFragment()


        AndPermission.with(this).requestCode(100).permission(READ_PHONE_STATE).rationale { requestCode, rationale ->
            AndPermission.rationaleDialog(this, rationale).show()
        }.callback(object : PermissionListener {
            override fun onSucceed(requestCode: Int, grantPermissions: List<String>) {
                supportFragmentManager.beginTransaction().replace(R.id.launchFragment, launchFragment).commit()
            }

            override fun onFailed(requestCode: Int, deniedPermissions: List<String>) {
                WXLogUtils.w("AndPermission,onFailed")
                ToastUtil.showLongToast(this@LaunchActivity,"Permission request Failed")
            }
        }).start()

    }
}
