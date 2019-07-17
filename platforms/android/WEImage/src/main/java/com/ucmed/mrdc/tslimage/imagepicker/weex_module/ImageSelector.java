package com.ucmed.mrdc.tslimage.imagepicker.weex_module;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;


import com.ucmed.mrdc.tslimage.imagepicker.ui.ImageGridActivity;

import java.util.ArrayList;
import java.util.List;

import static com.ucmed.mrdc.tslimage.imagepicker.util.CommonUtils.checkNotNull;


/**
 * Created by lijunguan on 2016/4/21.
 * emial: lijunguan199210@gmail.com
 * blog: https://lijunguan.github.io
 */
public class ImageSelector {


    public static final String SELECTED_RESULT = "selected_result";

    public static final int REQUEST_SELECT_IMAGE = 0x1024;

    public static final int REQUEST_OPEN_CAMERA = 0x2048;

    public static final int REQUEST_CROP_IMAGE = 0x4096;

    public static final int REQUEST_DELETE_IMAGE = 0x3072;

    public static final String ARG_ALBUM_CONFIG = "albumConfig";
    /**
     * 单选模式
     */
    @Deprecated
    public static final int SINGLE_MODE = 0x0;

    /**
     * 头像选择模式 得到裁剪后的正方形图片
     */
    public static final int AVATOR_MODE = 0x0;
    /**
     * 多选模式
     */
    public static final int MULTI_MODE = 0x1;
    /**
     * 已选择的图片路径, 本地路径,
     */
    public ArrayList<String> checkedPaths;

    public String[] paths;

    public void setCurrentPos(int currentPos) {
        this.currentPos = currentPos;
    }

    public int currentPos = 0;

    public boolean isEmpty_checkedPaths() {
        if (checkedPaths != null && checkedPaths.size() > 0)
            return false;
        else
            return true;

    }

    private AlbumConfig mConfig;

    private ImagePickerListener imagePickerListener;

    public void setImagePickerListener(ImagePickerListener imagePickerListener){
        this.imagePickerListener=imagePickerListener;
    }

    private static ImageSelector ourInstance = new ImageSelector();

    public static ImageSelector getInstance() {
        return ourInstance;
    }

    public AlbumConfig getConfig() {
        return mConfig;
    }
    public void setConfig(AlbumConfig mConfig) {
        this.mConfig = mConfig;
    }
    private ImageSelector() {
        mConfig = new AlbumConfig();
    }

    public ImageSelector setMaxCount(int maxCount) {
        checkNotNull(maxCount);
        mConfig.setMaxCount(maxCount);
        return this;
    }

    public ImageSelector setpaths(String[] paths){
        this.paths = paths;
        return this;
    }

    public ImageSelector setSelectModel(int model) {
        checkNotNull(model);
        mConfig.setSelectModel(model);
        return this;
    }

    public ImageSelector setShowCamera(boolean shown) {
        checkNotNull(shown);
        mConfig.setShownCamera(shown);
        return this;
    }

    public ImageSelector setGridColumns(int columns) {
        checkNotNull(columns);
        mConfig.setGridColumns(columns);
        return this;
    }

    public ImageSelector setToolbarColor(@ColorInt int toolbarColor) {
        checkNotNull(toolbarColor);
        mConfig.setToolbarColor(toolbarColor);
        return this;
    }

    public void startSelect(@NonNull Activity context) {
        Intent intent = new Intent(context, ImageGridActivity.class);
//        intent.putExtra(ImageSelector.ARG_ALBUM_CONFIG, mConfig);
//        intent.putExtra(AlbumActivity.OPERATION, AlbumActivity.CHECK_IMAGE);
        context.startActivityForResult(intent, REQUEST_SELECT_IMAGE);
    }


    public void startSelect(@NonNull Context context) {
        if (context instanceof Activity) {
            startSelect((Activity) context);
        } else {
            throw new IllegalArgumentException("Require a Activity.class,but find a Context.class");
        }
    }

    public void startDeleteSelect(@NonNull Activity context) {
        Intent intent = new Intent(context, ImageGridActivity.class);
//        intent.putExtra(AlbumActivity.PATHS, paths);
//        intent.putExtra(ImageSelector.ARG_ALBUM_CONFIG, mConfig);
//        intent.putExtra(AlbumActivity.CURRENT_POSITION, currentPos);
//        intent.putExtra(AlbumActivity.OPERATION, AlbumActivity.DELETE_IMAGE);
        context.startActivityForResult(intent, REQUEST_DELETE_IMAGE);
    }

    public void selectComplete(List<String> imagePaths) {
        if (imagePickerListener!=null){
            imagePickerListener.pickerFinish(imagePaths);
            //图片选择回调完成后需要置空, 防止内存泄漏
            imagePickerListener=null;
        }
    }

    public void deleteImage(int position){
        if (imagePickerListener!=null&&position!=-1){
            imagePickerListener.DeleteImage(position);
        }
    }
}
