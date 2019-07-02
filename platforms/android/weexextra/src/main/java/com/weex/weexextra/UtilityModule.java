package com.weex.weexextra;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.TextureView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.ui.component.WXComponent;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UtilityModule extends WXModule {


    @JSMethod(uiThread = false)
    public String md5StringSync(String targetString) {
        return getMD5String(targetString);

    }

    public String getMD5String(String targetString) {
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(targetString.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            re_md5 = buf.toString();
            return re_md5;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return re_md5;
        }
    }

    @JSMethod(uiThread = false)
    public void getCacheSize(JSCallback success, JSCallback fail, JSCallback complete) {
        ModuleAdapterCallBack ModuleAdapterCallBack = new ModuleAdapterCallBack(success, fail, complete);
        String string = GlideCatchUtil.getInstance().getCacheSize(mWXSDKInstance.getContext());
        ModuleAdapterCallBack.success(string);
    }

    @JSMethod(uiThread = false)
    public String getCacheSizeSync() {
        return GlideCatchUtil.getInstance().getCacheSize(mWXSDKInstance.getContext());
    }

    @JSMethod(uiThread = false)
    public void cleanCache(JSCallback success, JSCallback fail, JSCallback complete) {
        ModuleAdapterCallBack ModuleAdapterCallBack = new ModuleAdapterCallBack(success, fail, complete);
        Glide.get(mWXSDKInstance.getContext()).clearDiskCache();
        ModuleAdapterCallBack.success("clearCache:ok");
    }

    @JSMethod(uiThread = false)
    public boolean cleanCacheSync() {
        Glide.get(mWXSDKInstance.getContext()).clearDiskCache();
        return true;
    }

    @JSMethod(uiThread = false)
    public void fileToBase64(JSONObject object, JSCallback success, JSCallback fail, JSCallback complete) {
        final ModuleAdapterCallBack ModuleAdapterCallBack = new ModuleAdapterCallBack(success, fail, complete);
        if (!object.containsKey("file")) {
            ModuleAdapterCallBack.error("file should not be null");
            return;
        }
        Uri uri = Uri.parse(object.getString("file"));
        String s = uri.getEncodedPath();
        File file = new File(s);
        FileInputStream inputFile = null;
        try {
            inputFile = new FileInputStream(file);
            final byte[] buffer = new byte[(int) file.length()];
            inputFile.read(buffer);
            inputFile.close();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String result = Base64.encodeToString(buffer, Base64.NO_WRAP);
                    ModuleAdapterCallBack.success(result);
                }
            }).start();
        } catch (java.io.IOException e) {
            e.printStackTrace();
            ModuleAdapterCallBack.error(e.getMessage());
        }
    }

    @JSMethod(uiThread = false)
    public Object getTSLConfigPlistWithKey(String key) {
        String appConfigString = FileUtil.loadAsset("app.json", mWXSDKInstance.getContext());
        JSONObject jsonObject = null;
        try {
            jsonObject = JSON.parseObject(appConfigString);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return jsonObject == null ? "" : jsonObject.get(key);
    }

    /**
     * 跳转至应用宝市场
     *
     * @param packageName 应用包名
     * @param success
     * @param fail
     * @param complete
     */
    @JSMethod
    public void goToMarket(String packageName, JSCallback success, JSCallback fail, JSCallback complete) {
//        String url = "http://android.myapp.com/myapp/detail.htm?apkName="+packageName+"&ADTAG=mobile";
//        Intent intent = new Intent();
//        //Intent intent = new Intent(Intent.ACTION_VIEW,uri);
//        intent.setAction("android.intent.action.VIEW");
//        Uri content_url = Uri.parse(url);
//        intent.setData(content_url);
//        mWXSDKInstance.getContext().startActivity(intent);
//        Log.i("TSLUtility", "gotoMarket："+url);
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.APP_MARKET");
        PackageManager pm = mWXSDKInstance.getContext().getPackageManager();
        List<ResolveInfo> infos = pm.queryIntentActivities(intent, 0);
        if (infos.size() > 0) {
//            int size = infos.size();
//            for (int i = 0; i < size; i++) {
//                ActivityInfo activityInfo = infos.get(i).activityInfo;
            String marketName = infos.get(0).activityInfo.packageName;
//                //获取应用市场的包名
//            }

            intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.parse("market://details?id=" + packageName);//app包名
            intent.setData(uri);
            intent.setPackage(marketName);
//            intent.setPackage("com.tencent.android.qqdownloader");//应用市场包名
            mWXSDKInstance.getContext().startActivity(intent);

        } else {
            /**
             * 没有安装任何应用市场APP
             */
        }
    }

    @JSMethod
    public void openUrl(String url, JSCallback success, JSCallback fail, JSCallback complete) {

        if (TextUtils.isEmpty(url)) {
            new ModuleAdapterCallBack(success, fail, complete).error("openUrl:fail url is null");
            return;
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        mWXSDKInstance.getContext().startActivity(intent);
        Log.i("TSLUtility", "openUrl：" + url);
    }


    @JSMethod
    public void getDeviceInfo(JSCallback success, JSCallback fail, JSCallback complete) {
        ModuleAdapterCallBack adapter = new ModuleAdapterCallBack(success, fail, complete);
        Map map = DeviceUtil.getDevice(mWXSDKInstance.getContext());
        if (map == null) {
            adapter.error("getDeviceInfo:fail ");
        } else {
            JSONObject object = new JSONObject(map);
            object.put(Global.ERRMSG, "getDeviceInfo:ok");
            adapter.success(object);
        }
    }

    @JSMethod(uiThread = false)
    public Object  getDeviceInfoSync() {
        Map map = DeviceUtil.getDevice(mWXSDKInstance.getContext());
        return new JSONObject(map);
    }

    @JSMethod
    public void setClipBoard(JSONObject args, JSCallback success, JSCallback fail, JSCallback complete) {
        ModuleAdapterCallBack adapter = new ModuleAdapterCallBack(success, fail, complete);
        String text = args.getString("text");
        if (TextUtils.isEmpty(text)) {
            adapter.error("setClipBoard:fail text is null");
            return;
        }
        ClipboardUtil.setString(mWXSDKInstance.getContext(), "", text);
        adapter.success("setClipBoard:ok");
    }

    @JSMethod
    public void getClipBoard(JSCallback success, JSCallback fail, JSCallback complete) {
        ModuleAdapterCallBack adapter = new ModuleAdapterCallBack(success, fail, complete);
        Map map = ClipboardUtil.getString(mWXSDKInstance.getContext());
        JSONObject object = new JSONObject(map);
        object.put(Global.ERRMSG, "getClipBoard:ok");
        adapter.success(object);
    }

    /**
     * 获取View的截图,
     *
     * @param args
     * @param success
     * @param fail
     * @param complete
     */
    @JSMethod
    public void captureView(JSONObject args, JSCallback success, JSCallback fail, JSCallback complete) {
        ModuleAdapterCallBack adapter = new ModuleAdapterCallBack(success, fail, complete);
        String ref = args.getString("ref");
        if (TextUtils.isEmpty(ref)) {
            adapter.error("captureView:fail ref is null");
            return;
        }
        WXComponent component = WXSDKManager.getInstance().getWXRenderManager().getWXComponent(mWXSDKInstance.getInstanceId(), ref);
        if(component==null){
            adapter.error("captureView:not found component by ref="+ref);
            return;
        }
        View view = component.getHostView();
        Bitmap bitmap = null;
        if (view != null) {
            if (view instanceof TextureView) {
                bitmap = ((TextureView) view).getBitmap();
            } else {
                bitmap = loadBitmapFromView(component.getHostView());
            }
        }
        if(bitmap!=null){
            //保存图片,
           String cachePath=FileUtil.saveBitmapToCache(bitmap,null);
           if(!TextUtils.isEmpty(cachePath)){
               HashMap map=new HashMap();
               map.put(Global.ERRMSG,"captureView:ok");
               map.put("tempFilePath",cachePath);
               adapter.success(map);
           }else{
               adapter.error("captureView:fail save bitmap fail");
           }
        }else{
            //失败回调
            adapter.error("captureView:fail create bitmap fail");
        }
    }

    /**
     * 安装APK包
     * @param object
     * @param success
     * @param fail
     * @param complete
     */
    @JSMethod
    public void installAPK(JSONObject object, JSCallback success, JSCallback fail, JSCallback complete){
        ModuleAdapterCallBack adapter = new ModuleAdapterCallBack(success, fail, complete);
        if (!object.containsKey("file")) {
            adapter.error("installAPK:fail file should not be null");
            return;
        }
        Uri uri = Uri.parse(object.getString("file"));
        String s = uri.getEncodedPath();
        File apkFile = new File(s);
        if(!apkFile.exists()){
            adapter.error("installAPK:fail file is not exist");
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri apkUri = FileProvider.getUriForFile(mWXSDKInstance.getContext(), mWXSDKInstance.getContext().getPackageName() + ".FileProvider", apkFile);//在AndroidManifest中的android:authorities值
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);//添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        }

        mWXSDKInstance.getContext().startActivity(intent);
    }

    private Bitmap loadBitmapFromView(View view) {
        int w = view.getWidth();
        int h = view.getHeight();
        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);
        view.draw(c);
        return bmp;
    }


}
