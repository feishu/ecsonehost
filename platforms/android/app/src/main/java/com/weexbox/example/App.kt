package com.weexbox.example

import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import com.lelibrary.androidlelibrary.init.SDKInsigma
import com.taobao.weex.WXSDKEngine
import com.weex.weexextra.Global
import com.weexbox.core.WeexBoxEngine
import com.weexbox.example.modules.AppUpdaterModule
import com.weexbox.example.modules.FileModule
import com.weexbox.example.modules.SmartTagToolsModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        // 初始化 WeexBox
        WeexBoxEngine.setup(this, null)

        // 开启调试
        WeexBoxEngine.isDebug = isApkInDebug(this)
        SDKInsigma.init(this,true)
        Global.init(this)
        WXSDKEngine.registerModule("SmartTag", SmartTagToolsModule::class.java)
        WXSDKEngine.registerModule("event", EventModule::class.java)
        WXSDKEngine.registerModule("FileUtil", FileModule::class.java)
        WXSDKEngine.registerModule("AppUpdater",AppUpdaterModule::class.java)
        Global.init(this)
    }

    fun isApkInDebug(context: Context): Boolean {
        return try {
            val info: ApplicationInfo = context.getApplicationInfo()
            info.flags and ApplicationInfo.FLAG_DEBUGGABLE !== 0
        } catch (e: Exception) {
            false
        }
    }

}
