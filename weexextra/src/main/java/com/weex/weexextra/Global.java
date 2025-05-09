package com.weex.weexextra;

import android.app.Application;
import android.content.Context;

import com.taobao.weex.utils.WXLogUtils;


import java.io.File;

public class Global {


    private static Context mContext;

    /**
     * 加载tsl weex代码的地址,
     *  服务器地址:http://192.168.23.188:8098/
     *  本地地址:本地地址 同 TSL_CODE_ROOTPATH
     *
     */
    public static  String HOST;

    /**
     * tsl weex代码压缩包的名字
     */
    public static final String TSL_CODE_ZIP="index.zip";

    /**
     * 默认的入口文件名
     */
    public static final String TSL_CODE_INDEX="index.js";
    /**
     * 配置文件
     */
    public static final String TSL_CODE_CONFIG="app.json";

    public static final String TSL_CODE_PATCH="patch.zip";

    /**
     * 存储tsl weex代码的地方,放在内部存储中防止被别的app读取到
     */
    public static String TSL_CODE_ROOTPATH;


    /**
     * 内部存储中文件的存储目录
     *
     */
    public static String TSL_FILE_ROOTPATH;

    /**
     * 内部存储中的缓存路径,在内存不足时会被回收
     *
     */
    public static String TSL_CACHE_ROOTPATH;
    /**
     * 外部存储中的缓存路径,在外部存储可用时使用外部存储,否则使用内部存储缓存
     */
    public static String TSL_EXTERNAL_CACHE_ROOTPATH;


    public static boolean ScreenLockStatus=true;


    public static void init(Application context) {
        if (context == null) {
            WXLogUtils.w("TSLGlobal :context is null ");
            return;
        }
        mContext = context;
        TSL_FILE_ROOTPATH=context.getFilesDir().getAbsolutePath()+ File.separator ;

        TSL_CODE_ROOTPATH = context.getFilesDir().getAbsolutePath() + File.separator + "tsl2" + File.separator;
        WXLogUtils.w("TSLGlobal", TSL_CODE_ROOTPATH);
        TSL_CACHE_ROOTPATH = context.getCacheDir().getAbsolutePath() + File.separator;
        TSL_EXTERNAL_CACHE_ROOTPATH = context.getExternalCacheDir().getAbsolutePath() + File.separator;

    }


    /**
     * 请求运行时权限使用的requestCode
     */
    public static final int REQUEST_PERMISSION = 20000;

    public static final String ERRMSG = "errMsg";


}
