package com.ucmed.mrdc.tslimage.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;

import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.ucmed.mrdc.tslimage.imagepicker.ImagePicker;
import com.ucmed.mrdc.tslimage.imagepicker.weex_module.ImageSelector;
import com.weex.weexextra.ScreenUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by WX-GXM-1326 on 2018/8/22.
 */

public class CompressTask /*extends AsyncTask<String,Integer,List<String>>*/ {

    Context context;
    ProgressDialog LoadingDialog;

    public CompressTask(Context context) {
        this.context = context;
    }

    private void prepareTask() {
        SpinKitView spinKitView = new SpinKitView(context);
        FadingCircle fadingCircle = new FadingCircle();
        fadingCircle.setColor(Color.WHITE);
        spinKitView.setIndeterminateDrawable(fadingCircle);
//        LoadingDialog = TSLModuleAdapterManager.getInstance().getTslModalAdapterInterface().makeDialog(context, "正在处理",
//                true,
//                spinKitView);
//        LoadingDialog.showAllowingStateLoss(((Activity) context).getFragmentManager(), "loading");
        LoadingDialog = new ProgressDialog(context);
        LoadingDialog.setMessage("正在处理...");
        LoadingDialog.setCanceledOnTouchOutside(false);
        LoadingDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        LoadingDialog.show();
    }

    public void execute(final String[] strings) {
        Observable.just(strings).subscribeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<String[], ObservableSource<String[]>>() {
                    @Override
                    public ObservableSource<String[]> apply(final String[] strings) throws Exception {
                        return new ObservableSource<String[]>() {
                            @Override
                            public void subscribe(Observer observer) {
                                prepareTask();
                                observer.onNext(strings);
                            }
                        };
//
                    }
                }).observeOn(Schedulers.io())
                .flatMap(new Function<String[], ObservableSource<List<String>>>() {

                    @Override
                    public ObservableSource<List<String>> apply(final String[] strings1) throws Exception {
                        return new ObservableSource<List<String>>() {
                            @Override
                            public void subscribe(Observer observer) {
                                observer.onNext(doTask(strings1));
                            }
                        };
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<String> strings) {
                        postTask(strings);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private List<String> doTask(String... strings) {
        List<String> stringList = new ArrayList<>();
        for (String s : strings) {
            stringList.add(ImageUtil.compressImage(ImagePicker.getInstance().getCropCacheFolder(context), new File(s), context.getContentResolver()
                    , ScreenUtil.getInstance((Activity) context).DisplayWidth
                    , ScreenUtil.getInstance((Activity) context).DisplayHeight).getAbsolutePath());
        }
        return stringList;
    }


    private void postTask(List<String> strings) {
        LoadingDialog.dismiss();
        ImageSelector.getInstance().selectComplete(strings);//传给weex-module
        ((Activity) context).finish();
    }
}
