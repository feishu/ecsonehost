package com.weexbox.example

import android.Manifest.permission.READ_PHONE_STATE
import android.os.Bundle
import com.weexbox.core.controller.WBBaseActivity
import com.yanzhenjie.permission.AndPermission


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
        supportFragmentManager.beginTransaction().replace(R.id.launchFragment, launchFragment).commit()

        AndPermission.with(this).runtime().permission(READ_PHONE_STATE).onDenied({ permissions -> }).onGranted({ permissions -> }).start()

    }
}
