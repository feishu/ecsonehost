package com.ucmed.mrdc.tslimage;

//import android.support.annotation.Nullable;
import androidx.annotation.Nullable;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.utils.WXUtils;
import com.weex.weexextra.ModuleAdapterCallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mdp on 2018/3/20.
 * Version:1.0.0
 */

public class ImageUtilModule extends WXModule {

    @JSMethod(uiThread = true)
    public void saveImageToPhotosAlbum(JSONObject object,
                                       @Nullable JSCallback successCallBack,
                                       @Nullable JSCallback errorCallBack,
                                       @Nullable JSCallback completeCallBack) {
        ModuleAdapterCallBack ModuleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);

        if (TextUtils.isEmpty(object.getString("filePath"))) {
            ModuleAdapterCallBack.error("saveImageToPhotosAlbum:fail filePath is empty");
            return;
        }
        new DefaultImageAdapterIml().saveImageToPhotosAlbum(mWXSDKInstance.getContext(), object.getString("filePath"),
                ModuleAdapterCallBack);
    }


    //选择图片或者拍照
    @JSMethod(uiThread = true)
    public void chooseImage(JSONObject object,
                            @Nullable JSCallback successCallBack,
                            @Nullable JSCallback errorCallBack,
                            @Nullable JSCallback completeCallBack) {
        ModuleAdapterCallBack ModuleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);
        int count = WXUtils.getNumberInt(object.get("count"), 9);
        List<String> sizeType = new ArrayList<>();
        if (object.containsKey("sizeType"))
            sizeType = JSONArray.parseArray(object.getString("sizeType"), String.class);
        List<String> sourceType = new ArrayList<>();
        if (object.containsKey("sourceType"))
            sourceType = JSON.parseArray(object.getString("sourceType"), String.class);
        else
            sourceType.add("camera");
        List<String> filePaths = new ArrayList<>();
        if (object.containsKey("filePaths"))
            filePaths = JSON.parseArray(object.getString("filePaths"), String.class);
        float compress = 1;
        if (object.containsKey("compress"))
            compress = object.getFloatValue("compress");
        new DefaultImageAdapterIml().chooseImage(mWXSDKInstance.getContext(), count, filePaths, sizeType, sourceType,compress, ModuleAdapterCallBack);
    }

    //预览
    @JSMethod(uiThread = true)
    public void previewImage(JSONObject object,
                             @Nullable JSCallback successCallBack,
                             @Nullable JSCallback errorCallBack,
                             @Nullable JSCallback completeCallBack) {
        final ModuleAdapterCallBack ModuleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);
        String current = object.containsKey("current") ? object.getString("current") : "";
        List<String> urls;
        if (object.containsKey("urls")) {
            urls = JSONArray.parseArray(object.getString("urls"), String.class);
            if (urls == null || urls.size() == 0) {
                ModuleAdapterCallBack.error("previewImage:fail urls is empty");
                return;
            }
        } else {
            ModuleAdapterCallBack.error("previewImage:fail urls is not exist");
            return;
        }
        new DefaultImageAdapterIml().previewImage(mWXSDKInstance.getContext(), current, urls);
    }

    //获取图片信息
    @JSMethod(uiThread = false)
    public void getImageInfo(JSONObject object,
                             @Nullable JSCallback successCallBack,
                             @Nullable JSCallback errorCallBack,
                             @Nullable JSCallback completeCallBack) {
        ModuleAdapterCallBack ModuleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);
        if (TextUtils.isEmpty(object.getString("src"))) {
            ModuleAdapterCallBack.error("getImageInfo:fail src is empty");
            return;
        }
        new DefaultImageAdapterIml().getImageInfo(mWXSDKInstance.getContext(), object.getString("src"), ModuleAdapterCallBack);
    }

}
