package com.ucmed.leancloud;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.util.concurrent.ExecutionException;

import cn.leancloud.AVFile;
import cn.leancloud.callback.ProgressCallback;
import cn.leancloud.utils.FileUtil;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class upLoadC {
    private AVFile mAvFile;
    private Callback mCallBack;
    private int i = 0;
    private Context mContext;
    public upLoadC(Context context, AVFile avFile,Callback callBack) {
        mAvFile = avFile;
        mContext = context;
        mCallBack = callBack;
        startUpLoad();
    }
//    public void setCallBack(Callback callBack){
//        mCallBack = callBack;
//    }

    public void failed(String message){
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("err", message);
        jsonObject1.put("status", "onError");
        if(mCallBack!=null){
            mCallBack.onError(jsonObject1);
        }
    }

    public void startUpLoad(){
        mAvFile.saveInBackground(new ProgressCallback() {
            @Override
            public void done(Integer percentDone) {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("progress", percentDone);
                if (mCallBack!=null){
                    mCallBack.onprogress(jsonObject1);
                }
            }
        });

        mAvFile.saveInBackground().subscribe(new Observer<AVFile>() {
            //            public void onSubscribe(Disposable disposable) {
//                JSONObject jsonObject1 = new JSONObject();
//                jsonObject1.put("status", "onSubscribe");
//                done.invoke(jsonObject1);
//            }
            public void onSubscribe(Disposable disposable) {
            }

            public void onNext(AVFile file) {
                System.out.println("文件保存完成。objectId：" + file.getObjectId());
                File resource = null;
                try {
                    resource = Glide.with(mContext).load(file.getUrl()).downloadOnly(Target.SIZE_ORIGINAL,Target.SIZE_ORIGINAL).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
//                    if(i == 2){
//                        failed(e.getMessage());
//                        return;
//                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
//                    if(i == 2){
//                        failed(e.getMessage());
//                        return;
//                    }
                }
                if(resource == null||!resource.exists()||resource.length()==0){
                    if(i==2){
                        failed("上传失败");
                        return;
                    }else{
                        i+=1;
                        startUpLoad();
                        return;
                    }
                }
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("url", file.getUrl());
                jsonObject1.put("ObjectId", file.getObjectId());
                jsonObject1.put("status", "onNext");
                if(mCallBack!=null){
                    mCallBack.onFinished(jsonObject1);
                }
            }

            public void onError(Throwable throwable) {
                // 保存失败，可能是文件无法被读取，或者上传过程中出现问题
                failed(throwable.getMessage());
            }

            public void onComplete() {
//                JSONObject jsonObject1 = new JSONObject();
//                jsonObject1.put("status", "onComplete");
            }
        });
    }

    public interface Callback {
        void onprogress(JSONObject object);

        void onFinished(JSONObject object);

        void onError(JSONObject object);
    }
}
