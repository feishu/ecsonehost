package com.ucmed.leancloud;

import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

import java.io.File;

import cn.leancloud.AVFile;
import cn.leancloud.AVOSCloud;
import cn.leancloud.callback.ProgressCallback;
import cn.leancloud.core.AVOSService;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class LCModule extends WXModule {

    @JSMethod(uiThread = true)
    public void init(JSONObject jsonObject) {
        AVOSCloud.initialize(mWXSDKInstance.getContext(), jsonObject.getString("appId"), jsonObject.getString("appKey"));
        if (jsonObject.containsKey("serverURLs"))
            AVOSCloud.setServer(AVOSService.API, jsonObject.getString("serverURLs"));
    }

    @JSMethod(uiThread = false)
    public void upload(JSONObject jsonObject, final JSCallback progress, final JSCallback done) {
        if(!jsonObject.containsKey("file")){
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("status", "onError");
            jsonObject1.put("error", "file 参数有错误");
            done.invoke(jsonObject1);
            return;
        }
        String filestr = jsonObject.getString("file").replace("file://","");
        File file = new File(filestr);
        byte[] data = DemoUtils.readFile(file);
        AVFile avFile = new AVFile(file.getName(), data);
        avFile.saveInBackground(new ProgressCallback() {
            @Override
            public void done(Integer percentDone) {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("progress", percentDone);
                progress.invokeAndKeepAlive(jsonObject1);
            }
        });


        avFile.saveInBackground().subscribe(new Observer<AVFile>() {
            JSONObject successData = new JSONObject();
            public void onSubscribe(Disposable disposable) {}
            public void onNext(AVFile file) {
                successData.clear();
                successData.put("url", file.getUrl());
                successData.put("ObjectId", file.getObjectId());
                successData.put("size",file.getSize());
                successData.put("status", "onSuccess");
                successData.put("message", "文件上传成功");
               // done.invoke(successData);
            }

            public void onError(Throwable throwable) {
                // 保存失败，可能是文件无法被读取，或者上传过程中出现问题
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("status", "onError");
                jsonObject1.put("error",throwable.getMessage());
                done.invoke(jsonObject1);
            }

            public void onComplete() {
                successData.remove("status");
                successData.put("status", "onComplete");

                successData.remove("message");
                successData.put("message","完成");
                done.invoke(successData);
            }
        });

    }
}
