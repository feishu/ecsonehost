package com.weexbox.example.modules;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.utils.WXLogUtils;
import com.ucmed.mrdc.tslimage.imagepicker.util.Utils;
import com.weex.weexextra.ModuleAdapterCallBack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileModule extends WXModule {
    private String TAG = "FileModule";

    @JSMethod(uiThread = false)
    public void copy(JSONObject object, JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack) {
        ModuleAdapterCallBack moduleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);
        if (!object.containsKey("originFilePath") || !object.containsKey("targetDir")) {
            moduleAdapterCallBack.error("参数错误");
            return;
        }
        File newDir;
        if (Utils.existSDCard())
            newDir = new File(Environment.getExternalStorageDirectory(), object.getString("targetDir"));
        else
            newDir = new File(mWXSDKInstance.getContext().getFilesDir(), object.getString("targetDir"));

//        String newFilePath = saveFile(new File(object.getString("originFilePath")), newDir.getAbsolutePath(), getFileNameByPath(object.getString("originFilePath")));
        boolean b = copyFolder(object.getString("originFilePath"),newDir.getAbsolutePath());
        if (!b) {
            moduleAdapterCallBack.error("复制失败");
        } else {
            Map map = new HashMap();
            map.put("newFilePath", newDir.getAbsolutePath());
            moduleAdapterCallBack.success(map);
        }
    }

    @JSMethod
    public void delete(JSONObject object, JSCallback successCallBack, JSCallback errorCallBack, JSCallback completeCallBack) {
        ModuleAdapterCallBack moduleAdapterCallBack = new ModuleAdapterCallBack(successCallBack, errorCallBack, completeCallBack);
        if (!object.containsKey("filePath")) {
            moduleAdapterCallBack.error("参数错误");
            return;
        }
        if (!new File(object.getString("filePath")).exists()) {
            moduleAdapterCallBack.error("文件不存在");
            return;
        }
        deleteFile(new File(object.getString("filePath")));
        moduleAdapterCallBack.success("删除完成");
    }


    /**
     * 复制文件夹及其中的文件
     *
     * @param oldPath String 原文件夹路径 如：data/user/0/com.test/files
     * @param newPath String 复制后的路径 如：data/user/0/com.test/cache
     * @return <code>true</code> if and only if the directory and files were copied;
     * <code>false</code> otherwise
     */
    public boolean copyFolder(String oldPath, String newPath) {
        try {
            File newFile = new File(newPath);
            if (!newFile.exists()) {
                if (!newFile.mkdirs()) {
                    Log.e("--Method--", "copyFolder: cannot create directory.");
                    return false;
                }
            }
            File oldFile = new File(oldPath);
            String[] files = oldFile.list();
            File temp;
            for (String file : files) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file);
                } else {
                    temp = new File(oldPath + File.separator + file);
                }

                if (temp.isDirectory()) {   //如果是子文件夹
                    copyFolder(oldPath + "/" + file, newPath + "/" + file);
                } else if (!temp.exists()) {
                    Log.e("--Method--", "copyFolder:  oldFile not exist.");
                    return false;
                } else if (!temp.isFile()) {
                    Log.e("--Method--", "copyFolder:  oldFile not file.");
                    return false;
                } else if (!temp.canRead()) {
                    Log.e("--Method--", "copyFolder:  oldFile cannot read.");
                    return false;
                } else {
                    FileInputStream fileInputStream = new FileInputStream(temp);
                    FileOutputStream fileOutputStream = new FileOutputStream(newPath + "/" + temp.getName());
                    byte[] buffer = new byte[1024];
                    int byteRead;
                    while ((byteRead = fileInputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, byteRead);
                    }
                    fileInputStream.close();
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String saveFile(File file, String savedPath, String fileName) {
        FileInputStream is = null;
        FileOutputStream os = null;

        File savedFile;
        try {
            WXLogUtils.d(TAG, "fileName=" + fileName);
            WXLogUtils.d(TAG, "savedPath=" + savedPath);
            File savedFilePath = new File(savedPath);
            if (!savedFilePath.exists()) {
                savedFilePath.mkdirs();
            }

            savedFile = new File(savedFilePath, fileName);
            is = new FileInputStream(file);
            byte[] temp = new byte[4096];
            os = new FileOutputStream(savedFile);
            boolean var8 = false;

            int len;
            while ((len = is.read(temp)) > 0) {
                os.write(temp, 0, len);
            }

            os.flush();
            String var9 = savedFile.getAbsolutePath();
            return var9;
        } catch (Exception var19) {
            var19.printStackTrace();
            WXLogUtils.d(TAG, var19.getMessage());
            savedFile = null;
        } finally {
            try {
                if (is != null) {
                    is.close();
                }

                if (os != null) {
                    os.close();
                }
            } catch (IOException var18) {
                var18.printStackTrace();
            }

        }

        return "";
    }

    public String getFileNameByPath(String filePath) {
        return filePath.substring(filePath.lastIndexOf("/") + 1);
    }

    public void deleteFile(File file) {
        deleteAllFile(file.listFiles());
        file.delete();
    }

    public void deleteAllFile(File[] fileList) {
        for (int i = 0; i < fileList.length; ++i) {
            File file = fileList[i];
            if (file.isDirectory()) {
                deleteAllFile(file.listFiles());
                file.delete();
            } else {
                file.delete();
            }
        }

    }
}
