package com.ucmed.mrdc.tslimage.imagepicker.imageloader;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.ucmed.mrdc.tslimage.R;
import com.ucmed.mrdc.tslimage.imagepicker.loader.ImageLoader;


/**
 * ================================================
 * 作    者：jeasonlzy（廖子尧 Github地址：https://github.com/jeasonlzy0216
 * 版    本：1.0
 * 创建日期：2016/5/19
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class GlideImageLoader implements ImageLoader {

    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {

//        Glide.with(activity)                             //配置上下文
//                .load(getFilePath(path))      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
//                .error(R.mipmap.default_image)           //设置错误图片
//                .placeholder(R.mipmap.default_image)     //设置占位图片
//                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
//                .into(imageView);

        Glide.with(activity)
                .load(getFilePath(path))
                .apply(new RequestOptions()
                        .placeholder(R.mipmap.default_image)
                        .error(R.mipmap.default_image)
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(imageView);
    }

    private String getFilePath(String path) {
        if (path.startsWith("http") || path.startsWith("ftp")) {
            return path;
        } else {
            return path/*Uri.parse(path).getEncodedPath()*/;
        }
    }

    @Override
    public void clearMemoryCache() {
    }
}
