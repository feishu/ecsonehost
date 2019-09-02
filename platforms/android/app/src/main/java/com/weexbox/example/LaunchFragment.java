package com.weexbox.example;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.taobao.weex.utils.WXLogUtils;
import com.ucmed.pushcenterlib.PushCenterManager;
import com.ucmed.pushcenterlib.PushCenterUtil;
import com.ucmed.pushcenterlib.PushResultCallBack;
import com.weexbox.core.controller.WBBaseFragment;
import com.weexbox.core.router.Router;
import com.weexbox.core.update.UpdateManager;

import java.io.File;
import java.util.Map;

import kotlin.Unit;
import kotlin.jvm.functions.Function4;

/**
 * Author: Mario
 * Time: 2018/12/11 11:22 AM
 * Description: This is LaunchFragment
 */

public class LaunchFragment extends WBBaseFragment {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_lauch;
    }

    public void updateUI(String text, int progress) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressTxt.setText(text + "" + progress + "%");
                progressView.setProgress(progress);
            }
        });
    }

    TextView progressTxt;
    ProgressBar progressView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // 配置热更新地址
        String hotDeployUrl =   "http://weex.frp.apcan.cn"; // "https://ecsoneqnr.app.swiretest.com/hotecsone"; //
        UpdateManager.INSTANCE.setServerUrl(hotDeployUrl);
//        UpdateManager.serverUrl = hotDeployUrl

        // 是否需要强制更新
        UpdateManager.INSTANCE.setForceUpdate(false);
//        UpdateManager.forceUpdate = false

        progressTxt = (TextView) rootView.findViewById(R.id.progressTxt);
        progressView = (ProgressBar) rootView.findViewById(R.id.progressView);

        // 执行热更新
        UpdateManager.INSTANCE.update(new Function4<UpdateManager.UpdateState, Integer, Throwable, File, Unit>() {
            @Override
            public Unit invoke(UpdateManager.UpdateState updateState, Integer integer, Throwable throwable, File file) {
                if (updateState.equals(UpdateManager.UpdateState.Unzip)) {
                    updateUI("更新中", integer);
                }
                if (updateState == UpdateManager.UpdateState.DownloadFile) {
                    updateUI("下载中", integer);
                }
                if (updateState == UpdateManager.UpdateState.UpdateSuccess) {
                    Router router = new Router();
                    router.setName(Router.NAME_WEEX);
                    router.setUrl("page/home.js");
                    router.setCloseFrom(0);
                    router.open((LaunchActivity) getActivity());
                }
                return null;
            }
        });

//        PushCenterManager.getInstance().getPushCenterInterface().register(getContext(), null, new PushResultCallBack() {
//            @Override
//            public void success(Map map) {
//                WXLogUtils.i(map.toString());
//            }
//
//            @Override
//            public void failure(Map map) {
//                WXLogUtils.i(map.toString());
//            }
//        });

    }
}

