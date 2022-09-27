package com.weexbox.example
import android.app.Activity
import android.graphics.Color
import android.util.Log
import com.azhon.appupdate.listener.OnDownloadListenerAdapter
import com.azhon.appupdate.manager.DownloadManager
import com.azhon.appupdate.util.ApkUtil
import com.beust.klaxon.Json
import com.weex.weexextra.ModuleAdapterCallBack
import java.io.File

class CheckUpdate(private val activity: Activity) {
    private var manager: DownloadManager? = null
    private var adaptrCallback : ModuleAdapterCallBack? = null
    fun download(data:UpdateBean){
        download(data,null)
    }
    fun download(data:UpdateBean,callback: ModuleAdapterCallBack?){
        if(data==null) return
        adaptrCallback = callback
        manager = DownloadManager.Builder(activity).run {
            apkUrl(data.downloadUrl)
            apkName(data.apkName)
            smallIcon(R.mipmap.ic_launcher)
            showNewerToast(data.showToast)
            apkVersionCode(data.versionCode)
            apkVersionName(data.versionName)
            apkSize(data.apkSize)
            apkDescription(data.updateDescription)
            apkMD5(data.apkMd5)
            dialogButtonTextColor(Color.WHITE)
            showNotification(true)
            showBgdToast(true)
            forcedUpgrade(data?.forcedUpgrade)
            onDownloadListener(listenerAdapter)
            build()
        }
        manager?.download()
        val b = ApkUtil.deleteOldApk(
            activity,
            activity.externalCacheDir?.path.toString() + "/${data.apkName}"
        )
        Log.e("deleteOldApk", "onButtonClick: $b")
    }

    private val listenerAdapter: OnDownloadListenerAdapter = object : OnDownloadListenerAdapter() {
        override fun downloading(max: Int, progress: Int) {
            val curr = (progress / max.toDouble() * 100.0).toInt()
            adaptrCallback?.progress(curr)
            Log.e("progress", "onButtonClick: $curr")
        }

        override fun done(apk: File) {
            Log.e("done", "下载完成")
            super.done(apk)
            adaptrCallback?.success(apk.path)
        }
    }
}

data class UpdateBean(
    @Json(name = "apkName")
    var apkName: String,
    @Json(name = "downloadUrl")
    var downloadUrl: String,
    @Json(name = "versionCode")
    var versionCode: Int,
    @Json(name = "updateDescription")
    var updateDescription: String,
    @Json(name = "versionName")
    var versionName: String,
    @Json(name = "apkSize")
    var apkSize: String,
    @Json(name = "apkMd5")
    var apkMd5: String,
    @Json(name = "forcedUpgrade")
    var forcedUpgrade : Boolean = true,
    @Json(name = "showToast")
    var showToast: Boolean = false
)