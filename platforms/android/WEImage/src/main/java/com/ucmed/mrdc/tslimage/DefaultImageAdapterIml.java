package com.ucmed.mrdc.tslimage;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
//import android.support.annotation.NonNull;
import androidx.annotation.NonNull;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.ucmed.mrdc.tslimage.imagepicker.ImagePicker;
import com.ucmed.mrdc.tslimage.imagepicker.bean.ImageItem;
import com.ucmed.mrdc.tslimage.imagepicker.imageloader.GlideImageLoader;
import com.ucmed.mrdc.tslimage.imagepicker.ui.ImageGridActivity;
import com.ucmed.mrdc.tslimage.imagepicker.ui.ImagePreviewActivity;
import com.ucmed.mrdc.tslimage.imagepicker.weex_module.ImagePickerListener;
import com.ucmed.mrdc.tslimage.imagepicker.weex_module.ImageSelector;
import com.ucmed.mrdc.tslimage.util.ImageUtil;
import com.weex.weexextra.ModuleAdapterCallBack;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by mdp on 2018/4/2.
 * Version:1.0.0
 */

public class DefaultImageAdapterIml {

    private ImageSelector imageSelector;

    public void saveImageToPhotosAlbum(final Context context, String filePath,
                                       final ModuleAdapterCallBack imageSaveCallbackInterface) {

        Uri uri = Uri.parse(filePath);
        final File file = new File(uri.getEncodedPath());
        if (!file.exists())
            if (imageSaveCallbackInterface != null) {
                imageSaveCallbackInterface.error("saveImageToPhotosAlbum:fail file is no exist");
                return;
            }

        AndPermission.with(context).permission(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                        AndPermission.rationaleDialog(context, rationale).show();
                    }
                }).callback(new PermissionListener() {
            @Override
            public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                try {
                    ImageUtil.saveBitmapToGallery(context, file);
                    if (imageSaveCallbackInterface != null)
                        imageSaveCallbackInterface.success("");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    if (imageSaveCallbackInterface != null)
                        imageSaveCallbackInterface.error(e.getMessage());
                }
            }

            @Override
            public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                if (imageSaveCallbackInterface != null)
                    imageSaveCallbackInterface.error("Permissions denied");
            }
        }).start();

    }

    public void chooseImage(Context context, int count, List<String> filePath, List<String> sizeType, List<String> sourceType,
                            float compress, final ModuleAdapterCallBack chooseCallbackInterface) {
        imageSelector = ImageSelector.getInstance();
        imageSelector.setImagePickerListener(new ImagePickerListener() {
            @Override
            public void pickerFinish(List<String> paths) {
                if (chooseCallbackInterface != null) {
                    if (paths == null) {
                        chooseCallbackInterface.error("choose nothing");
                        return;
                    }
                    HashMap map = new HashMap<>();
                    List<String> stringList = new ArrayList<>();
                    List<HashMap> mtempFiles = new ArrayList<>();
                    if (paths == null) paths = new ArrayList<>();
                    for (String path : paths) {
                        File file = new File(path);
                        stringList.add(Uri.fromFile(file).toString());
                        HashMap hashMap = new HashMap<>();
                        hashMap.put("path", Uri.fromFile(file).toString());
                        hashMap.put("size", file.length());
                        mtempFiles.add(hashMap);

                    }
                    map.put("tempFilePaths", stringList);
                    map.put("tempFiles", mtempFiles);
                    chooseCallbackInterface.success(map);
                }
            }

            @Override
            public void DeleteImage(int index) {

            }
        });

        ImagePicker imagePicker = ImagePicker.getInstance();
        if (imagePicker.getImageLoader() == null) {
            imagePicker.setImageLoader(new GlideImageLoader());
        }
        imagePicker.setCrop(false);
        ArrayList<ImageItem> images = new ArrayList<>();

        if (filePath != null && filePath.size() > 0) {
            for (Object pathString : filePath) {
                if (pathString == null) continue;
                ImageItem item = new ImageItem();
                item.path = Uri.parse(String.valueOf(pathString)).getEncodedPath();
                images.add(item);
            }
        }
        if (count < 2) {
            imagePicker.setMultiMode(false);
        } else {
            imagePicker.setMultiMode(true);
            imagePicker.setSelectLimit(count);
        }

        imagePicker.setCompress(compress);

//        if(params.containsKey("cropwidth")){imagePicker.setFocusWidth((int)params.get("cropwidth"));imagePicker.setCrop(true);}else {imagePicker.setCrop(false);}
//        if(params.containsKey("cropheight")){imagePicker.setFocusHeight((int)params.get("cropheight"));imagePicker.setCrop(true);}else {imagePicker.setCrop(false);}
//        if(params.containsKey("savewidth")){imagePicker.setOutPutX((int)params.get("savewidth"));}
//        if(params.containsKey("saveheight")){imagePicker.setOutPutY((int)params.get("saveheight"));}

        if (sourceType.contains("album") && !sourceType.contains("camera")) {
            imagePicker.setShowCamera(false);
        } else if (!sourceType.contains("album") && sourceType.contains("camera")) {
            Intent intent = new Intent(context, ImageGridActivity.class);
            intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
            context.startActivity(intent);
            return;
        } else {
            imagePicker.setShowCamera(true);
        }

        Intent intent = new Intent(context, ImageGridActivity.class);
        intent.putExtra(ImageGridActivity.EXTRAS_IMAGES, images);
        context.startActivity(intent);
    }


    public void previewImage(Context context, String current, List<String> urls) {
        int currentPos = urls.indexOf(current) < 0 ? 0 : urls.indexOf(current);
        ArrayList<ImageItem> images = new ArrayList<>();
        if (urls.size() > 0) {
            for (String pathString : urls) {
                ImageItem item = new ImageItem();
                item.path = pathString;//Uri.parse(pathString).getEncodedPath();
                images.add(item);
            }
        }
        ImagePicker imagePicker = ImagePicker.getInstance();
        if (imagePicker.getImageLoader() == null) {
            imagePicker.setImageLoader(new GlideImageLoader());
        }
        imagePicker.setSelectLimit(images.size());
        Intent intentPreview = new Intent(context, ImagePreviewActivity.class);
        intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, images);
        intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, currentPos);
        intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
        intentPreview.putExtra(ImagePicker.ONLY_PERVIEW, true);
        context.startActivity(intentPreview);
    }

    @SuppressLint("NewApi")
    public void getImageInfo(Context context, String src, ModuleAdapterCallBack imageInfoCallbackInterface) {
        String filepath = Uri.parse(src).getEncodedPath();
        if (!new File(filepath).exists()) {
            if (src.startsWith("http")) {
                File resource = null;
                String exc = "";
                try {
                    resource = Glide.with(context).load(src).downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get();
                    imageInfoCallbackInterface.success(ImageUtil.parseImageInfo(resource.getAbsolutePath()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    exc = e.getLocalizedMessage();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                    exc = e.getLocalizedMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                    exc = e.getLocalizedMessage();
                }
                if (!TextUtils.isEmpty(exc))
                    imageInfoCallbackInterface.error("getImageInfo:fail " + exc);
                return;
            } else if (imageInfoCallbackInterface != null) {
                imageInfoCallbackInterface.error("getImageInfo:fail " + "file is not exist");
                return;
            }
        }
        try {
            if (imageInfoCallbackInterface != null) {
                HashMap map = ImageUtil.parseImageInfo(filepath);
                imageInfoCallbackInterface.success(map);
            }
        } catch (IOException e) {
            e.printStackTrace();
            imageInfoCallbackInterface.error("getImageInfo:fail " + e.getMessage());
        }
    }

}
