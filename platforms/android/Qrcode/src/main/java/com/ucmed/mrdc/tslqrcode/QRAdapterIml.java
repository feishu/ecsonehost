package com.ucmed.mrdc.tslqrcode;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.mylhyl.zxing.scanner.OnScannerCompletionListener;
import com.mylhyl.zxing.scanner.decode.QRDecode;
import com.weex.weexextra.ModuleAdapterCallBack;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by WX-GXM-1326 on 2018/4/27.
 */

public class QRAdapterIml {

    private ModuleAdapterCallBack callbackInterface;

    private static Bitmap addLogo(Bitmap src, Bitmap logo) {
        if (src == null) {
            return null;
        }

        if (logo == null) {
            return src;
        }

        //获取图片的宽高
        int srcWidth = src.getWidth();
        int srcHeight = src.getHeight();
        int logoWidth = logo.getWidth();
        int logoHeight = logo.getHeight();

        if (srcWidth == 0 || srcHeight == 0) {
            return null;
        }

        if (logoWidth == 0 || logoHeight == 0) {
            return src;
        }

        //logo大小为二维码整体大小的1/5
        float scaleFactor = srcWidth * 1.0f / 5 / logoWidth;
        Bitmap bitmap = Bitmap.createBitmap(srcWidth, srcHeight, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(bitmap);
            canvas.drawBitmap(src, 0, 0, null);
            canvas.scale(scaleFactor, scaleFactor, srcWidth / 2, srcHeight / 2);
            canvas.drawBitmap(logo, (srcWidth - logoWidth) / 2, (srcHeight - logoHeight) / 2, null);

            canvas.save(Canvas.ALL_SAVE_FLAG);
            canvas.restore();
        } catch (Exception e) {
            bitmap = null;
            e.getStackTrace();
        }

        return bitmap;
    }

    public void scanCode(final Context context, final ModuleAdapterCallBack callbackInterface) {
        this.callbackInterface = callbackInterface;
        AndPermission.with(context).runtime().permission(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> deniedPermissions) {
                        if (callbackInterface != null)
                            callbackInterface.error("scanCode:fail Permissions denied");
                    }
                }).onGranted(new Action<List<String>>() {
            @Override
            public void onAction(List<String> grantPermissions) {
                if (!EventBus.getDefault().isRegistered(QRAdapterIml.this)) {
                    EventBus.getDefault().register(QRAdapterIml.this);
                }
                Intent intent = new Intent(context, TSLScanViewActivity.class);
                context.startActivity(intent);
            }
        }).start();
    }

    @Subscribe
    public void onEvent(ScanQrCodeSuccessEvent event) {
        if (event == null)
            return;
        Map jsonObject = new HashMap();
        jsonObject.put("resultStr", event.resultStr);
        jsonObject.put("result", event.resultStr);
        jsonObject.put("scanType", event.scanType);
        jsonObject.put("charSet", event.charSet);
        jsonObject.put("path", "");
        if (callbackInterface != null)
            callbackInterface.success(jsonObject);
        EventBus.getDefault().unregister(this);
    }

    public void decScan(final Context context, String image, final ModuleAdapterCallBack callbackInterface) {
        if (image.startsWith("http") || image.startsWith("https")) {
            Glide.with(context).asBitmap().load(image).into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    QRDecode.decodeQR(resource, new OnScannerCompletionListener() {
                        @Override
                        public void onScannerCompletion(Result rawResult, ParsedResult parsedResult, Bitmap barcode) {
                            if (callbackInterface != null) {
                                if (rawResult == null)
                                    callbackInterface.error("decScan:fail " + context.getString(R.string.tsl_toast_decodescan_fail));
                                else {
                                    Map jsonObject = new HashMap();
                                    jsonObject.put("resultStr", rawResult.getText());
                                    jsonObject.put("result", rawResult.getText());
                                    jsonObject.put("scanType", rawResult.getBarcodeFormat().name());
                                    jsonObject.put("charSet", parsedResult.getDisplayResult());
                                    jsonObject.put("path", "");
                                    callbackInterface.success(jsonObject);
                                }
                            }
                        }
                    });
                }
            });
        } else {
            QRDecode.decodeQR(image, new OnScannerCompletionListener() {
                @Override
                public void onScannerCompletion(Result rawResult, ParsedResult parsedResult, Bitmap barcode) {
                    if (callbackInterface != null) {
                        if (rawResult == null)
                            callbackInterface.error("decScan:fail " + context.getString(R.string.tsl_toast_decodescan_fail));
                        else {
                            Map jsonObject = new HashMap();
                            jsonObject.put("resultStr", rawResult.getText());
                            jsonObject.put("result", rawResult.getText());
                            jsonObject.put("scanType", rawResult.getBarcodeFormat().name());
                            jsonObject.put("charSet", parsedResult.getDisplayResult());
                            jsonObject.put("path", "");
                            callbackInterface.success(jsonObject);
                        }
                    }
                }
            });
        }
    }

    public void generateQR(final Context context, String src, String logo, String format, int QR_WIDTH, int QR_HEIGHT, final ModuleAdapterCallBack callbackInterface) {
        if (TextUtils.isEmpty(src)) {
            callbackInterface.error("src is null");
        }
        BarcodeFormat barcodeFormat = BarcodeFormat.QR_CODE;
        if (!TextUtils.isEmpty(format)) {
            switch (format) {
                case "QR_CODE":
                    barcodeFormat = BarcodeFormat.QR_CODE;
                    break;
                case "AZTEC":
                    barcodeFormat = BarcodeFormat.AZTEC;
                    break;
                case "CODABAR":
                    barcodeFormat = BarcodeFormat.CODABAR;
                    break;
                case "CODE_39":
                    barcodeFormat = BarcodeFormat.CODE_39;
                    break;
                case "CODE_93":
                    barcodeFormat = BarcodeFormat.CODE_93;
                    break;
                case "CODE_128":
                    barcodeFormat = BarcodeFormat.CODE_128;
                    break;
                case "DATA_MATRIX":
                    barcodeFormat = BarcodeFormat.DATA_MATRIX;
                    break;
                case "EAN_8":
                    barcodeFormat = BarcodeFormat.EAN_8;
                    break;
                case "EAN_13":
                    barcodeFormat = BarcodeFormat.EAN_13;
                    break;
                case "ITF":
                    barcodeFormat = BarcodeFormat.ITF;
                    break;
                case "MAXICODE":
                    barcodeFormat = BarcodeFormat.MAXICODE;
                    break;
                case "PDF_417":
                    barcodeFormat = BarcodeFormat.PDF_417;
                    break;
                case "RSS_14":
                    barcodeFormat = BarcodeFormat.RSS_14;
                    break;
                case "RSS_EXPANDED":
                    barcodeFormat = BarcodeFormat.RSS_EXPANDED;
                    break;
                case "UPC_A":
                    barcodeFormat = BarcodeFormat.UPC_A;
                    break;
                case "UPC_E":
                    barcodeFormat = BarcodeFormat.UPC_E;
                    break;
                case "UPC_EAN_EXTENSION":
                    barcodeFormat = BarcodeFormat.UPC_EAN_EXTENSION;
                    break;
            }
        }

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix bitMatrix = null;
        OutputStream outputStream = null;
        try {
            bitMatrix = new QRCodeWriter().encode(src, barcodeFormat, QR_WIDTH, QR_HEIGHT, hints);
            int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
            //下面这里按照二维码的算法，逐个生成二维码的图片，
            //两个for循环是图片横列扫描的结果
            for (int y = 0; y < QR_HEIGHT; y++) {
                for (int x = 0; x < QR_WIDTH; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * QR_WIDTH + x] = 0xff000000;
                    } else {
                        pixels[y * QR_WIDTH + x] = 0xffffffff;
                    }
                }
            }
            //生成二维码图片的格式，使用ARGB_8888
            final Bitmap bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);

            if (!TextUtils.isEmpty(logo)) {
                Glide.with(context).asBitmap().load(logo).into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        Bitmap final_bitmap = addLogo(bitmap, resource);
                        File savef = saveImgFile(context, final_bitmap);
                        if (savef.exists()) {
                            Map map = new HashMap();
                            map.put("imageUrl", savef.getAbsolutePath());
                            callbackInterface.success(map);
                        } else {
                            callbackInterface.error(new FileNotFoundException().getMessage());
                        }
                    }
                });
                return;
            }

            File filesDir = new File(context.getFilesDir().getAbsoluteFile() + "/QRfile");
            if (!filesDir.exists()) filesDir.mkdirs();
            File saveFile = new File(filesDir.getAbsolutePath() + "/" + System.currentTimeMillis() + ".png");
            outputStream = context.getContentResolver().openOutputStream(Uri.fromFile(saveFile));
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);

            if (saveFile.exists()) {
                Map map = new HashMap();
                map.put("imageUrl", Uri.fromFile(saveFile).toString());
                callbackInterface.success(map);
            }
        } catch (WriterException e) {
            e.printStackTrace();
            callbackInterface.error(e.getMessage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            callbackInterface.error(e.getMessage());
        } finally {
            try {
                if (outputStream != null)
                    outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private File saveImgFile(Context context, Bitmap bitmap) {
        OutputStream outputStream = null;
        try {
            File filesDir = new File(context.getFilesDir().getAbsoluteFile() + "/QRfile");
            if (!filesDir.exists()) filesDir.mkdirs();
            File saveFile = new File(filesDir.getAbsolutePath() + "/" + System.currentTimeMillis() + ".png");
            outputStream = context.getContentResolver().openOutputStream(Uri.fromFile(saveFile));
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            return saveFile;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}













