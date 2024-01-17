package com.ucmed.aliyunoss;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.ucmed.aliyunoss.bridge.Promise;

import java.io.File;
import java.util.HashMap;

import io.reactivex.disposables.Disposable;


 class OSSFactory{
     private OSS mOSS;
     private AliyunBucketManager mBucketManager;
     private AliyunObjectManager mObjectManager;
     private AliyunUploadManager mUploadManager;
     private AliyunDownloadManager mDownloadManager;
     private AliyunAuthManager mAuth;
     private static OSSFactory instance;

     private OSSFactory(){
         mAuth = new AliyunAuthManager(null, new AliyunAuthManager.AuthListener() {
             @Override
             public void onAuthFinished(OSS oss) {
                 init(oss);
             }
         });
     }

     public static OSSFactory getInstance() {
         if (instance == null) {
             instance = new OSSFactory();
         }
         return instance;
     }

     public AliyunAuthManager getAuth(){
         return instance.mAuth;
     }

     public AliyunBucketManager getBucketManager(){
         return instance.mBucketManager;
     }

     public AliyunObjectManager getObjectManager(){
         return instance.mObjectManager;
     }

     public AliyunUploadManager getUploadManager(){
         return instance.mUploadManager;
     }

     public AliyunDownloadManager getDownloadManager(){
         return instance.mDownloadManager;
     }

     public OSS getOSS(){
         return instance.mOSS;
     }

     /**
     * init oss
     * @param oss
     */
    private void init(OSS oss) {
        mOSS = oss;
        mBucketManager = new AliyunBucketManager(mOSS);
        mObjectManager = new AliyunObjectManager(mOSS);
        mUploadManager = new AliyunUploadManager(mOSS);
        mDownloadManager = new AliyunDownloadManager(mOSS);
    }
}

public class OSSModule extends WXModule {
    private AliyunAuthManager mAuth;
    private OSSFactory oss;
    public OSSModule(){
        this.init();
    }

    private void init() {
        oss = OSSFactory.getInstance();
        mAuth = oss.getAuth();
    }


    /**
     * initWithSigner WEEXMethod
     * @param signature
     * @param accessKey
     * @param endPoint
     * @param configuration
     */
    @JSMethod(uiThread = false)
    public void initWithSigner(final String signature, final String accessKey, String endPoint, JSONObject configuration) {
        mAuth.setContext(mWXSDKInstance.getContext());
        mAuth.initWithSigner(signature, accessKey, endPoint, configuration);
    }

    /**
     * initWithPlainTextAccessKey WEEXMethod
     * @param accessKeyId
     * @param accessKeySecret
     * @param endPoint
     * @param configuration
     */
    @JSMethod(uiThread = false)
    public void initWithPlainTextAccessKey(String accessKeyId, String accessKeySecret, String endPoint, JSONObject configuration) {
        mAuth.setContext(mWXSDKInstance.getContext());
        mAuth.initWithPlainTextAccessKey(accessKeyId, accessKeySecret, endPoint, configuration);
    }

    /**
     * initWithSecurityToken WEEXMethod
     * @param securityToken
     * @param accessKeyId
     * @param accessKeySecret
     * @param endPoint
     * @param configuration
     */
    @JSMethod(uiThread = false)
    public void initWithSecurityToken(String securityToken, String accessKeyId, String accessKeySecret, String endPoint, JSONObject configuration) {
        mAuth.setContext(mWXSDKInstance.getContext());
        mAuth.initWithSecurityToken(securityToken, accessKeyId, accessKeySecret, endPoint, configuration);
    }

    /**
     * initWithServerSTS WEEXMethod
     * @param server
     * @param endPoint
     * @param configuration
     */
    @JSMethod(uiThread = false)
    public void initWithServerSTS(final String server, String endPoint, JSONObject configuration, JSONObject headers) {
        mAuth.setContext(mWXSDKInstance.getContext());
        mAuth.initWithServerSTS(server, endPoint, configuration, headers);
    }

    /**
     * async Upload WEEXMethod
     * @param bucketName
     * @param ossFile
     * @param sourceFile
     * @param cb
     */
    @JSMethod(uiThread = false)
    public void asyncUpload(String bucketName, String ossFile, String sourceFile,JSCallback progress, JSCallback cb, JSONObject options) {
        oss.getUploadManager().asyncUpload(mWXSDKInstance.getContext().getApplicationContext(), bucketName, ossFile, sourceFile, cb, progress, options);
    }

    /**
     * asyncAppendObject WEEXMethod
     * @param bucketName
     * @param objectKey
     * @param uploadFilePath
     * @param options
     * @param promise
     */
    @JSMethod(uiThread = false)
    public void asyncAppendObject(String bucketName,String objectKey,String uploadFilePath,JSONObject options,final Promise promise) {
        oss.getUploadManager().asyncAppendObject(mWXSDKInstance.getContext().getApplicationContext(),bucketName, objectKey, uploadFilePath, options, promise);
    }

    /**
     * asyncResumableUpload WEEXMethod
     * @param bucketName
     * @param objectKey
     * @param uploadFilePath
     * @param options
     * @param promise
     */
    @JSMethod(uiThread = false)
    public void asyncResumableUpload(String bucketName,String objectKey,String uploadFilePath,JSONObject options,final Promise promise) {
        oss.getUploadManager().asyncResumableUpload(mWXSDKInstance.getContext().getApplicationContext(), bucketName, objectKey, uploadFilePath, options, promise);
    }

    /**
     * initMultipartUpload WEEXMethod
     * @param bucketName
     * @param objectKey
     * @param promise
     */
    @JSMethod(uiThread = false)
    public void initMultipartUpload(String bucketName,String objectKey,final Promise promise) {
        oss.getUploadManager().initMultipartUpload(bucketName, objectKey, promise);
    }

    /**
     * multipartUpload WEEXMethod
     * @param bucketName
     * @param objectKey
     * @param filepath
     * @param options
     * @param promise
     */
//    @SuppressLint("LongLogTag")
    @JSMethod(uiThread = false)
    public void multipartUpload(String bucketName, String objectKey, String uploadId, String filepath, JSONObject options,final Promise promise) {
        oss.getUploadManager().multipartUpload(mWXSDKInstance.getContext().getApplicationContext(), bucketName, objectKey, uploadId, filepath, options, promise);
    }

    /**
     * AbortMultipartUploadRequest WEEXMethod
     * @param bucketName
     * @param objectKey
     * @param uploadId
     */
    @JSMethod(uiThread = false)
    public void abortMultipartUpload(String bucketName,String objectKey,String uploadId,final Promise promise) {
        oss.getUploadManager().abortMultipartUpload(bucketName, objectKey, uploadId, promise);
    }

    /**
     * listParts WEEXMethod
     * @param bucketName
     * @param objectKey
     * @param uploadId
     * @param promise
     */
    @JSMethod(uiThread = false)
    public void listParts (String bucketName,String objectKey,String uploadId,final Promise promise) {
        oss.getUploadManager().listParts(bucketName, objectKey, uploadId, promise);
    }

    /**
     * generatePresignedUrl WEEXMethod
     * @param bucketName
     * @param objectKey
     * @return
     * @throws ClientException
     */
    @JSMethod(uiThread = false)
    public String generatePresignedUrl(String bucketName,String objectKey,String x_oss_process ) throws ClientException {
        return oss.getUploadManager().generatePresignedUrl(bucketName, objectKey, x_oss_process,60);
    }

    /**
     * asyncDownload and image process WEEXMethod
     * @param bucketName
     * @param ossFile
     * @param updateDate
     * @param options
     * @param promise
     */
    @JSMethod(uiThread = false)
    public void asyncDownload(String bucketName, String ossFile, String updateDate,JSONObject options, final Promise promise) {
        oss.getDownloadManager().asyncDownload(mWXSDKInstance.getContext().getApplicationContext(), bucketName, ossFile, updateDate, options, promise);
    }
    /**
     * createBucket WEEXMethod
     * @param bucketName
     * @param acl
     * @param region
     * @param promise
     */
    @JSMethod(uiThread = false)
    public void asyncCreateBucket (String bucketName,String acl,String region,final Promise promise) {
        oss.getBucketManager().asyncCreateBucket(bucketName, acl, region, promise);
    }

    /**
     * async getBucketName WEEXMethod
     * @param bucketName
     * @param promise
     */
    @JSMethod(uiThread = false)
    public void asyncGetBucketACL (String bucketName,final Promise promise) {
        oss.getBucketManager().asyncGetBucketACL(bucketName,promise);
    }

    /**
     * list buckets WEEXMethod
     * @param promise
     */
    @JSMethod(uiThread = false)
    public void asyncListBuckets(final Promise promise) {
        oss.getBucketManager().asyncListBuckets(promise);
    }
    /**
     * async delet bucket WEEXMethod
     * @param bucketName
     * @param promise
     */
    @JSMethod(uiThread = false)
    public void asyncDeleteBucket(String bucketName,final Promise promise) {
        oss.getBucketManager().asyncDeleteBucket(bucketName,promise);
    }

    /**
     * asyncHeadObject WEEXMethod
     * @param bucketName
     * @param objectKey
     * @param promise
     */
    @JSMethod(uiThread = false)
    public void asyncHeadObject(String bucketName,String objectKey,final Promise promise) {
        oss.getObjectManager().asyncHeadObject(bucketName,objectKey,promise);
    }

    /**
     * asyncListObjects WEEXMethod
     * @param bucketName
     * @param options
     * @param promise
     */
    @JSMethod(uiThread = false)
    public void asyncListObjects(String bucketName,JSONObject options,final Promise promise) {
        oss.getObjectManager().asyncListObjects(bucketName, options, promise);
    }

    /**
     * copy objects WEEXMethod
     * @param srcBucketName
     * @param srcObjectKey
     * @param desBucketName
     * @param destObjectKey
     * @param options set object metadata
     * @param promise
     */

    @JSMethod(uiThread = false)
    public void asyncCopyObject (String srcBucketName,String srcObjectKey, String desBucketName, String destObjectKey,JSONObject options ,final Promise promise ) {
        oss.getObjectManager().asyncCopyObject(srcBucketName,srcObjectKey,desBucketName,destObjectKey,options,promise);
    }

    /**
     * does object exist WEEXMethod
     * @param bucketName
     * @param objectKey
     * @param promise
     */
    @JSMethod(uiThread = false)
    public void doesObjectExist(String bucketName,String objectKey,final Promise promise) {
        oss.getObjectManager().doesObjectExist(bucketName,objectKey,promise);
    }

    /**
     * asyncDeleteObject WEEXMethod
     * @param bucketName
     * @param objectKey
     * @param promise
     */
    @JSMethod(uiThread = false)
    public void asyncDeleteObject(String bucketName, String objectKey,final Promise promise) {
        oss.getObjectManager().asyncDeleteObject(bucketName, objectKey, promise);
    }

    @JSMethod(uiThread = false)
    public void upload(JSONObject jsonObject, final JSCallback progress, final JSCallback done) {
//        if(!jsonObject.containsKey("file")){
//            JSONObject jsonObject1 = new JSONObject();
//            jsonObject1.put("status", "onError");
//            jsonObject1.put("error", "file 参数有错误");
//            done.invoke(jsonObject1);
//            return;
//        }
//        String filestr = jsonObject.getString("file").replace("file://","");
//        File file = new File(filestr);
//        byte[] data = DemoUtils.readFile(file);
//        AVFile avFile = new AVFile(file.getName(), data);
//        avFile.saveInBackground(new ProgressCallback() {
//            @Override
//            public void done(Integer percentDone) {
//                JSONObject jsonObject1 = new JSONObject();
//                jsonObject1.put("progress", percentDone);
//                progress.invokeAndKeepAlive(jsonObject1);
//            }
//        });
//
//
//        avFile.saveInBackground().subscribe(new Observer<AVFile>() {
//            JSONObject successData = new JSONObject();
//            public void onSubscribe(Disposable disposable) {}
//            public void onNext(AVFile file) {
//                successData.clear();
//                successData.put("url", file.getUrl());
//                successData.put("ObjectId", file.getObjectId());
//                successData.put("size",file.getSize());
//                successData.put("status", "onSuccess");
//                successData.put("message", "文件上传成功");
//               // done.invoke(successData);
//            }
//
//            public void onError(Throwable throwable) {
//                // 保存失败，可能是文件无法被读取，或者上传过程中出现问题
//                JSONObject jsonObject1 = new JSONObject();
//                jsonObject1.put("status", "onError");
//                jsonObject1.put("error",throwable.getMessage());
//                done.invoke(jsonObject1);
//            }
//
//            public void onComplete() {
//                successData.remove("status");
//                successData.put("status", "onComplete");
//
//                successData.remove("message");
//                successData.put("message","完成");
//                done.invoke(successData);
//            }
//        });
    }
}
