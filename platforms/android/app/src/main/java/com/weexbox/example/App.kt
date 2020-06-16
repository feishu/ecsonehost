package com.weexbox.example

import android.app.Application
import com.lelibrary.androidlelibrary.init.SDKInsigma
import com.taobao.weex.WXSDKEngine
import com.weex.weexextra.Global
import com.weexbox.core.WeexBoxEngine
import com.weexbox.example.modules.LELModule
import com.weexbox.example.modules.SmartTagModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        // 初始化 WeexBox
        WeexBoxEngine.setup(this, null)

        // 开启调试
        WeexBoxEngine.isDebug = true
        SDKInsigma.init(this,true)
        WXSDKEngine.registerModule("SmartTag", SmartTagModule::class.java)
        WXSDKEngine.registerModule("event", EventModule::class.java)
        Global.init(this)
    }

}
