package com.weexbox.example.modules

import android.content.pm.PackageInfo
import android.os.Build
import com.alibaba.fastjson.JSON
import com.azhon.appupdate.config.Constant
import com.taobao.weex.annotation.JSMethod
import com.taobao.weex.bridge.JSCallback
import com.weex.weexextra.ModuleAdapterCallBack
import com.weexbox.Util.ApkUtil
import com.weexbox.core.WeexBoxEngine.application
import com.weexbox.core.module.BaseModule
import com.weexbox.example.CheckUpdate
import com.weexbox.example.UpdateBean
import org.json.JSONObject
import java.io.File
import java.util.HashMap

class AppUpdaterModule: BaseModule() {

    @JSMethod(uiThread = false)
    fun updateVersion(appUpdateData : String, progress :JSCallback, done : JSCallback) {
        val moduleAdapterCallBack = ModuleAdapterCallBack(null, null, done ,progress)
        val appCheckUpdate  = CheckUpdate(this.getActivity())
        var updateBean = JSON.parseObject(appUpdateData,UpdateBean::class.java)
        appCheckUpdate.download(updateBean,moduleAdapterCallBack)
    }

    @JSMethod(uiThread = false)
    fun getVersion(successCallBack : JSCallback){
        val moduleAdapterCallBack =
            ModuleAdapterCallBack(successCallBack)
        val versionCode = ApkUtil.getVersionCode(mWXSDKInstance.context)
        var packageName = mWXSDKInstance.context.packageName
        val map = HashMap<String, Any>()
        val versionData = com.alibaba.fastjson.JSONObject()
        versionData["packageName"] = packageName
        versionData["versionCode"] = versionCode
        map["code"] = 200
        map["versionData"] = versionData
        moduleAdapterCallBack.success(map)
    }

    @JSMethod(uiThread = false)
    fun installApp(path:String,done:JSCallback){
        val map = HashMap<String, Any>()
        if (path.isEmpty()){
            map["code"] = 202
            map["message"] = "App的文件目录不能为空"
            done.invoke(map)
            return
        }
        val file = File(path)
        if(file.exists())
            ApkUtil.installApk(mWXSDKInstance.context,"${application.packageName}.fileProvider",file)
        else{
            map["code"] = 202
            map["message"] = "App的文件目录不能为空"
            done.invoke(map)
        }
    }

    @JSMethod(uiThread = false)
    fun deleteOldApk(path: String){
        val file = File(path)
        if(file.exists())
            ApkUtil.deleteOldApk(mWXSDKInstance.context,path)
    }
}