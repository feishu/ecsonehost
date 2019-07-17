package com.ucmed.mrdc.tslimage.util;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;

import com.ucmed.mrdc.tslimage.imagepicker.ImagePicker;
import com.ucmed.mrdc.tslimage.imagepicker.view.CropImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by mdp on 2018/3/8.
 * Version:1.0.0
 */

public class ImageUtil {

    //通知图库更新
    public static void saveBitmapToGallery(Context context, File imgFile) throws FileNotFoundException {

        MediaStore.Images.Media.insertImage(context.getContentResolver(),
                imgFile.getAbsolutePath(), imgFile.getName(), null);

        MediaScannerConnection.scanFile(context, new String[]{imgFile.getAbsolutePath()}, null, null);

    }

    //解析图片信息
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static HashMap parseImageInfo(String filePath) throws IOException {
        HashMap<String, String> map = new HashMap<>();
        ExifInterface exifInterface = null;
        if (TextUtils.isEmpty(filePath))
            throw new IllegalArgumentException("filePath cannot be null");

        exifInterface = new ExifInterface(Uri.parse(filePath).getEncodedPath());
        switch (exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)) {
            case ExifInterface.ORIENTATION_NORMAL:
                map.put("orientation", "up");
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                map.put("orientation", "right");
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                map.put("orientation", "down");
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                map.put("orientation", "left");
                break;
        }


        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(Uri.parse(filePath).getEncodedPath(), options);
        String type = options.outMimeType;
        long size = new File(filePath).length();
        map.put("size", String.valueOf(size));
        map.put("width", options.outWidth+"");
        map.put("height", options.outHeight+"");
        map.put("type", TextUtils.isEmpty(type) ? "未能识别的图片" : type.split("/").length > 1 ? type.split("/")[1] : type.split("/")[0]);
        map.put("path", filePath);
        return map;
    }

    public static File compressImage(File folder, File imageFile, ContentResolver contentResolver,int screenWidth,int screenHeight){
        String extName=imageFile.getName().substring(imageFile.getName().lastIndexOf(".")+1);
        Bitmap.CompressFormat outputFormat;
        if(TextUtils.equals(extName.toLowerCase(),"png"))
            outputFormat = Bitmap.CompressFormat.PNG;
        else if(TextUtils.equals(extName.toLowerCase(),"webp"))
            outputFormat = Bitmap.CompressFormat.WEBP;
        else
            outputFormat = Bitmap.CompressFormat.JPEG;

        File saveFile = createFile(folder, /*imageFile.getName().substring(0,imageFile.getName().lastIndexOf(".")) ,*/"IMG_", "."+extName);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imageFile.getAbsolutePath(), options);


        // 获取到这个图片的原始宽度和高度
        int picWidth = options.outWidth;
        int picHeight = options.outHeight;

        // isSampleSize是表示对图片的缩放程度，比如值为2图片的宽度和高度都变为以前的1/2
        options.inSampleSize = 1;
        // 根据屏的大小和图片大小计算出缩放比例
        if (picWidth > picHeight) {
            if (picWidth > screenWidth)
                options.inSampleSize = picWidth / screenWidth;
        } else {
            if (picHeight > screenHeight)

                options.inSampleSize = picHeight / screenHeight;
        }

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        //避免出现内存溢出的情况，进行相应的属性设置。
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inDither = true;

        Bitmap OriginBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath(),options);

        return saveOutput(OriginBitmap, outputFormat, saveFile, contentResolver);
    }

    /** 根据系统时间、前缀、后缀产生一个文件 */
    private static File createFile(File folder,/*String originName ,*/String prefix, String suffix) {
        if (!folder.exists() || !folder.isDirectory()) folder.mkdirs();
        try {
            File nomedia = new File(folder, ".nomedia");  //在当前文件夹底下创建一个 .nomedia 文件
            if (!nomedia.exists()) nomedia.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA);
        String filename = prefix + /*originName +*/ /*dateFormat.format(new Date(*/System.currentTimeMillis()/*))*/ + suffix;
        return new File(folder, filename);
    }

    /** 将图片保存在本地 */
    private static File saveOutput(Bitmap croppedImage, Bitmap.CompressFormat outputFormat, File saveFile, ContentResolver contentResolver) {
        OutputStream outputStream = null;
        try {
            outputStream = contentResolver.openOutputStream(Uri.fromFile(saveFile));
            int compress = (int)(ImagePicker.getInstance().getCompress()*100);
            if (outputStream != null) croppedImage.compress(outputFormat, compress, outputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        croppedImage.recycle();
        return saveFile;
    }
}
