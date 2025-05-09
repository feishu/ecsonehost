package com.weex.weexextra;

import com.taobao.weex.utils.WXLogUtils;

import io.reactivex.disposables.Disposable;

/**
 * Created by liaochengzong on 2018/4/11.
 */

public abstract class WEObserver<T> implements io.reactivex.Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onError(Throwable e) {
        WXLogUtils.w(WEObserver.class.getName(), e.toString());
    }

    @Override
    public void onComplete() {

    }
}
