package com.weex.weexextra;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;

import com.taobao.weex.WXEnvironment;
import com.taobao.weex.utils.WXLogUtils;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ucmed on 2016/12/8.
 */

public class FileUtil {
    private static String TAG = "TSL-FileUtil";

    public static void copyFromAssetsDataToSD(Context context, String filename, String strOutFileName) throws IOException {
        InputStream myInput;
        OutputStream myOutput = new FileOutputStream(strOutFileName);
        myInput = context.getAssets().open(filename);
        byte[] buffer = new byte[1024];
        int length = myInput.read(buffer);
        while (length > 0) {
            myOutput.write(buffer, 0, length);
            length = myInput.read(buffer);
        }
        myOutput.flush();
        myInput.close();
        myOutput.close();
    }

    public static void copyFromAssetsDataToContext(Context context, String filename) throws IOException {
        InputStream myInput;
        OutputStream myOutput = new FileOutputStream(context.getFilesDir().getAbsolutePath() + "/" /*+ Bundle_confing.getZip()*/);
        myInput = context.getAssets().open(filename);
        byte[] buffer = new byte[1024];
        int length = myInput.read(buffer);
        while (length > 0) {
            myOutput.write(buffer, 0, length);
            length = myInput.read(buffer);
        }

        myOutput.flush();
        myInput.close();
        myOutput.close();
    }

    public static String copyZipFileFromAssetsDataToContext(Context context,  String packageName,String filename) throws IOException {
        InputStream myInput;
        File outFileDir = new File(FileUtil.getPackageDir(packageName));
        if (!outFileDir.exists()) {
            outFileDir.mkdirs();
        }
        String outFilePath = FileUtil.getPackageZipFielPath(packageName);

        OutputStream myOutput = new FileOutputStream(outFilePath);
        myInput = context.getAssets().open(filename);
        byte[] buffer = new byte[1024];
        int length = myInput.read(buffer);
        while (length > 0) {
            myOutput.write(buffer, 0, length);
            length = myInput.read(buffer);
        }
        myOutput.flush();
        myInput.close();
        myOutput.close();
        return outFilePath;
    }


    /**
     * Load file in asset directory.
     *
     * @param path    FilePath
     * @param context Weex Context
     * @return the Content of the file
     */
    public static String loadAsset(String path, Context context) {
        if (path == null || context == null) {
            return null;
        }
        StringBuilder builder;
        try {
            InputStream in = context.getAssets().open(path);

            builder = new StringBuilder(in.available() + 10);

            BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(in));
            char[] data = new char[2048];
            int len = -1;
            while ((len = localBufferedReader.read(data)) > 0) {
                builder.append(data, 0, len);
            }
            localBufferedReader.close();
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    WXLogUtils.w("WXFileUtils loadAsset: ", e);
                }
            }
            return builder.toString();

        } catch (IOException e) {
            e.printStackTrace();
            WXLogUtils.w("", e);
        }

        return "";
    }

    /**
     * Load file in app directory.
     *
     * @param path    FilePath
     * @param context Weex Context
     * @return the Content of the file
     */
    public static String loadFile(String path, Context context) {
        if (path == null || context == null) {
            return null;
        }
        StringBuilder builder;
        try {
            InputStream in = new FileInputStream(path);

            builder = new StringBuilder(in.available() + 10);

            BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(in));
            char[] data = new char[2048];
            int len = -1;
            while ((len = localBufferedReader.read(data)) > 0) {
                builder.append(data, 0, len);
            }
            localBufferedReader.close();
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    WXLogUtils.w("WXFileUtils loadAsset: ", e);
                }
            }
            return builder.toString();

        } catch (IOException e) {
            e.printStackTrace();
            WXLogUtils.w("", e);
        }

        return "";
    }


    /**
     * 保存临时文件到缓存目录中
     *
     * @param file      待保存的文件
     * @param savedPath 需要保存到的位置
     * @return null 表示保存失败
     */
    public static String saveFile(File file, String savedPath, String fileName) {
        FileInputStream is = null;
        FileOutputStream os = null;
        try {
            WXLogUtils.d(TAG, "fileName=" + fileName);
            WXLogUtils.d(TAG, "savedPath=" + savedPath);

            File savedFilePath = new File(savedPath);
            if (!savedFilePath.exists()) {
                savedFilePath.mkdirs();
            }

            File savedFile = new File(savedFilePath, fileName);

            is = new FileInputStream(file);
            byte temp[] = new byte[4096];
            os = new FileOutputStream(savedFile);
            int len = 0;
            while ((len = is.read(temp)) > 0) {
                os.write(temp, 0, len);
            }
            os.flush();
            return savedFile.getAbsolutePath();

        } catch (Exception e) {
            e.printStackTrace();
            WXLogUtils.d(TAG, e.getMessage());
            return null;
        } finally {
            try {
                if (is != null)
                    is.close();
                if (os != null)
                    os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取缓存文件的根目录
     *
     * @return
     */
    public static String getCacheRootPath() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return Global.TSL_EXTERNAL_CACHE_ROOTPATH;
        } else {
            return Global.TSL_CACHE_ROOTPATH;
        }
    }


    /**
     * 获取文件存储的根目录
     *
     * @return
     */
    public static String getFileRootPath() {
        return Global.TSL_FILE_ROOTPATH;
    }


    /**
     * 获取默认的代码存储根目录
     *
     * @return
     */
    public static String getTSLCodeRootPath() {

        return Global.TSL_CODE_ROOTPATH;
    }

    /**
     * 获取文件摘要
     *
     * @param file
     * @param digestAlgorithm
     * @return
     */
    public static String getFileDigestStr(File file, String digestAlgorithm) throws NoSuchAlgorithmException, IOException {
        String digestStr = null;
        FileInputStream is = null;
        byte buffer[] = new byte[4096];
        int len;
        final MessageDigest mDigest = MessageDigest.getInstance(digestAlgorithm.toUpperCase());
        is = new FileInputStream(file);
        while ((len = is.read(buffer, 0, 4096)) != -1) {
            mDigest.update(buffer, 0, len);
        }
        BigInteger bigInt = new BigInteger(1, mDigest.digest());
        digestStr = bigInt.toString(16);
        return digestStr;

    }

    /**
     * 删除该文件/文件夹下的所有文件
     *
     * @param file
     */
    public static void deleteFile(File file) {
        deleteAllFile(file.listFiles());
        file.delete();
    }

    /**
     * 递归删除文件目录下的所有文件
     *
     * @param fileList
     */
    public static void deleteAllFile(File[] fileList) {
        for (int i = 0; i < fileList.length; i++) {
            File file = fileList[i];
            if (file.isDirectory()) {
                deleteAllFile(file.listFiles());
                file.delete();
            } else {
                file.delete();
            }
        }
    }

    /**
     * 根据路径获取文件名
     *
     * @param filePath
     * @return
     */
    public static String getFileNameByPath(String filePath) {
        return filePath.substring(filePath.lastIndexOf("/") + 1);
    }


    /**
     * 读取配置文件中的内容
     *
     * @param filePath
     * @return
     */
    public static String getConfigFileContent(String filePath) {
        File file = new File(filePath);
        if (file.exists() && file.length() > 0) {
            StringBuffer sb = new StringBuffer();
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(file));
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();
                return sb.toString();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "{}";
    }


    /**
     * 根据分包名获取压缩文件的存储路径
     *
     * @param packageName
     * @return
     */
    public static String getPackageZipFielPath(String packageName) {
        return FileUtil.getTSLCodeRootPath() + packageName + File.separator + Global.TSL_CODE_ZIP;
    }

    /**
     * 根据分包名获取分包代码路径
     *
     * @param packageName
     * @return
     */
    public static String getPackageDir(String packageName) {
        return FileUtil.getTSLCodeRootPath() + packageName + File.separator;
    }


    /**
     * 根据分包名获取配置文件路径
     *
     * @param packageName
     * @return
     */
    public static String getPackageConfigFilePath(String packageName) {
        return FileUtil.getTSLCodeRootPath() + packageName + File.separator + Global.TSL_CODE_CONFIG;
    }

    /**
     * 根据分包名差量更新包的路径
     *
     * @param packageName
     * @return
     */
    public static String getPackagePatchFilePath(String packageName) {
        return FileUtil.getTSLCodeRootPath() + packageName + File.separator + Global.TSL_CODE_PATCH;
    }

    /**
     * 判断是否本地路径
     * @param url
     * @return
     */
    public static boolean isLocalFile(String url){
        String SDpath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String Filepath = WXEnvironment.getApplication().getFilesDir().getAbsolutePath();
        String FileExpath = WXEnvironment.getApplication().getExternalFilesDir("").getAbsolutePath();
        String Cachepath = WXEnvironment.getApplication().getCacheDir().getAbsolutePath();
        String CacheExpath = WXEnvironment.getApplication().getExternalCacheDir().getAbsolutePath();

        return  (url.startsWith(SDpath) ||
                url.startsWith(Filepath) ||
                url.startsWith(FileExpath) ||
                url.startsWith(Cachepath) ||
                url.startsWith(CacheExpath));
    }


    public static String saveBitmapToCache(Bitmap bitmap,String fileName){
        if(bitmap==null){
            return "";
        }
        if(TextUtils.isEmpty(fileName)){
            fileName=System.currentTimeMillis()+"";
        }
        String cacheDir=FileUtil.getCacheRootPath();
        File dir=new File(cacheDir);
        if(!dir.exists()){
            dir.mkdirs();
        }
        File file=new File(dir,fileName);
        try {
            FileOutputStream out=new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "";
        }catch (IOException e){
            e.printStackTrace();
            return "";
        }
        return file.getAbsolutePath();
    }


    //file文件读取成byte[]
    public static byte[] readFile(File file) {
        RandomAccessFile rf = null;
        byte[] data = null;
        try {
            rf = new RandomAccessFile(file, "r");
            data = new byte[(int) rf.length()];
            rf.readFully(data);
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            closeQuietly(rf);
        }
        return data;
    }

    //关闭读取file
    public static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
