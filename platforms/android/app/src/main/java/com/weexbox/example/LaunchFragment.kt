//package com.weexbox.example
//
//import android.os.Bundle
//import com.weexbox.core.controller.WBBaseFragment
//import com.weexbox.core.router.Router
//import com.weexbox.core.update.UpdateManager
//import kotlinx.android.synthetic.main.fragment_lauch.*
//
///**
// * Author: Mario
// * Time: 2018/12/11 11:22 AM
// * Description: This is LaunchFragment
// */
//
//class LaunchFragment : WBBaseFragment() {
//
//    override fun getLayoutId(): Int {
//        return R.layout.fragment_lauch
//    }
//
//
//    fun updateUI(text: String, progress: Int) {
//        activity?.runOnUiThread {
//            progressTxt?.text = text + "" + progress + "%"
//            progressView?.setProgress(progress)
//        }
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//        // 配置热更新地址
//        val hotDeployUrl = "http://ecoupdate.frp.apcan.cn" //"http://weex.frp.apcan.cn"  //
//        UpdateManager.serverUrl = hotDeployUrl
//
//        // 是否需要强制更新
//        UpdateManager.forceUpdate = false
////        updateUI("检测更新...", 1)
//        // 执行热更新
//        UpdateManager.update { state, progress, error, url ->
//            when (state) {
//                UpdateManager.UpdateState.Unzip -> {
//                    // 解压
////                    updateUI("更新中...", progress)
//                    (activity as LaunchActivity).loadDialogHelper.showProgressWithText(activity, "更新中", progress)
//                }
//                UpdateManager.UpdateState.DownloadFile -> {
//                    // 下载
//                    if (UpdateManager.forceUpdate) {
////                        updateUI("下载中...", progress)
//                        (activity as LaunchActivity).loadDialogHelper.showProgressWithText(activity, "下载中", progress)
//                    }
//                }
//                UpdateManager.UpdateState.UpdateSuccess -> {
//                    // 更新成功，可以进入APP
//                    // 如果开启了强制更新，会等到下载完成才会进入这里。否则就是静默更新，解压成功就会进入
//                    val router = Router()
//                    router.name = Router.NAME_WEEX
//                    router.url = "page/home.js"
//                    router.closeFrom = 0
//                    router.open(activity as LaunchActivity)
//                }
//            }
//        }
//    }
//
//}
