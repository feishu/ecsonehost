package com.ucmed.mrdc.tslnet;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ucmed.mrdc.tslnet.net.DownloadCallbackInterface;
import com.ucmed.mrdc.tslnet.net.NetParams;
import com.ucmed.mrdc.tslnet.net.RequestCallbackInterface;
import com.ucmed.mrdc.tslnet.net.TSLNetAdapterInterface;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by WX-GXM-1326 on 2018/3/28.
 */

public class TSLNetAdapterIml implements TSLNetAdapterInterface {
    String TAG = "net";
    HashMap<String, Call> callMap = new HashMap();
    private ConcurrentHashMap<String, ArrayList<Cookie>> cookieStore = new ConcurrentHashMap<>();

    public OkHttpClient getClient(NetParams netparams) {
        return new OkHttpClient.Builder()
                .cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        //可以做保存cookies操作
                        if (!cookieStore.containsKey(url.host() + url.port())) {
                            ArrayList<Cookie> arrayList = new ArrayList<>();
                            arrayList.addAll(cookies);
                            cookieStore.put(url.host() + url.port(), arrayList);
                        } else {
                            for (Cookie listCookie : cookies) {
                                for (int i = 0; i < cookieStore.get(url.host() + url.port()).size(); i++) {
                                    if (cookieStore.get(url.host() + url.port()).get(i).name().equals(listCookie.name()))
                                        break;
                                    if (i == cookieStore.get(url.host() + url.port()).size() - 1)
                                        cookieStore.get(url.host() + url.port()).add(listCookie);
                                }
                            }
                        }
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        //加载新的cookies
                        List<Cookie> cookies = cookieStore.get(url.host() + url.port());
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                })
                .connectTimeout(netparams.getTimeout(), TimeUnit.SECONDS)
                .readTimeout(netparams.getTimeout(), TimeUnit.SECONDS)
                .writeTimeout(netparams.getTimeout(), TimeUnit.SECONDS)
                .build();
    }

    @Override
    public String get(NetParams params, final RequestCallbackInterface callbackInterface) {
        StringBuilder tempParams = new StringBuilder();

        //处理参数
        int pos = 0;
        Map<String, Object> data = TextUtils.isEmpty(params.getData()) ? new HashMap<String, Object>() : (Map<String, Object>) JSON.parse(params.getData());
        for (String key : data.keySet()) {
            //对参数进行URLEncoder
            tempParams.append((pos == 0 ? "" : "&") + String.format("%s=%s", key, URLEncoder.encode(String.valueOf(data.get(key))), "utf-8"));
            pos++;
        }

        //补全请求地址
        String requestUrl = data.size() < 1 ? params.getUrl()
                : params.getUrl().contains("?") ? String.format("%s&%s", params.getUrl(), tempParams.toString())
                : String.format("%s?%s", params.getUrl(), tempParams.toString());

        final Request request = addHeaders(params.getHeaders()).url(requestUrl).get().build();

        Call call = getClient(params).newCall(request);

        callMap.put(call.toString(), call);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callMap.remove(call.toString());
                if (callbackInterface != null) {
                    if (e.toString().contains("response's code is")) {
                        String[] s = e.toString().split(":");
                        callbackInterface.onFail(e.getMessage(), Integer.parseInt(s[s.length - 1]), new Headers.Builder().build());
                    } else {
                        callbackInterface.onFail(e.getMessage(), -1, new Headers.Builder().build());
                    }
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callMap.remove(call.toString());
                if (callbackInterface != null) {
                    doResponse(response, callbackInterface);
                }
            }
        });

        return call.toString();
    }

    public void doResponse(Response response, RequestCallbackInterface callbackInterface) {
        try {
            if (response.isSuccessful()) {
                String data = response.body().string();
                callbackInterface.onSuccess(data, response.code(), response.headers());
            } else {
                callbackInterface.onFail(response.message(), response.code(), response.headers());
            }
        } catch (IOException e) {
            e.printStackTrace();
            callbackInterface.onFail(e.getMessage(), response.code(), response.headers());
        }
    }

    @Override
    public String postJson(NetParams params, final RequestCallbackInterface callbackInterface) {
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

        RequestBody body = RequestBody.create(mediaType, params.getData()/*JSON.toJSONString(params.getData())*/);

        Request request = addHeaders(params.getHeaders()).url(params.getUrl()).post(body).build();

        Call call = getClient(params).newCall(request);

        callMap.put(call.toString(), call);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callMap.remove(call.toString());
                if (callbackInterface != null) {
                    if (e.toString().contains("response's code is")) {
                        String[] s = e.toString().split(":");
                        callbackInterface.onFail(e.getMessage(), Integer.parseInt(s[s.length - 1]), new Headers.Builder().build());
                    } else {
                        callbackInterface.onFail(e.getMessage(), -1, new Headers.Builder().build());
                    }
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callMap.remove(call.toString());
                if (callbackInterface != null) {
                    doResponse(response, callbackInterface);
//                    if (response.isSuccessful())
//                        callbackInterface.onSuccess(response.body().string(), response.code(), response.headers());
//                    else
//                        callbackInterface.onFail(response.body().string(), response.code(), response.headers());
                }
            }
        });

        return call.toString();
    }

    @Override
    public String postForm(NetParams params, final RequestCallbackInterface callbackInterface) {
        FormBody.Builder builder = new FormBody.Builder();
        Map<String, Object> data = TextUtils.isEmpty(params.getData()) ? new HashMap<String, Object>() : (Map<String, Object>) JSON.parse(params.getData());
        for (String key : data.keySet()) {
            builder.add(key, String.valueOf(data.get(key)));
        }
        FormBody formBody = builder.build();

        Request request = addHeaders(params.getHeaders()).url(params.getUrl()).post(formBody).build();

        Call call = getClient(params).newCall(request);

        callMap.put(call.toString(), call);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callMap.remove(call.toString());
                if (callbackInterface != null) {
                    if (e.toString().contains("response's code is")) {
                        String[] s = e.toString().split(":");
                        callbackInterface.onFail(e.getMessage(), Integer.parseInt(s[s.length - 1]), new Headers.Builder().build());
                    } else {
                        callbackInterface.onFail(e.getMessage(), -1, new Headers.Builder().build());
                    }
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callMap.remove(call.toString());
                if (callbackInterface != null) {
                    doResponse(response, callbackInterface);
//                    if (response.isSuccessful())
//                        callbackInterface.onSuccess(response.body().string(), response.code(), response.headers());
//                    else
//                        callbackInterface.onFail(response.body().string(), response.code(), response.headers());
                }
            }
        });

        return call.toString();
    }

    @Override
    public String upload(NetParams netparams, final DownloadCallbackInterface callbackInterface) {
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        /**
         * 多文件
         */
        if (netparams.getFilePath().startsWith("[") && netparams.getFilePath().endsWith("]")) {
            JSONArray filearray = JSON.parseArray(netparams.getFilePath());
            JSONArray namearray = JSON.parseArray(netparams.getName());
            for (int i = 0; i < filearray.size(); i++) {
                File file = new File(Uri.parse(filearray.getString(i)).getEncodedPath());
                if (file != null) {
                    RequestBody body = RequestBody.create(MediaType.parse("*/*"), file);
                    requestBody.addFormDataPart(namearray.getString(i), file.getName(), body);
                }
            }
        } else {
            /**
             * 单文件
             */
            File file = new File(Uri.parse(netparams.getFilePath()).getEncodedPath());
            if (file.exists()) {
                RequestBody body = RequestBody.create(MediaType.parse("*/*"), file);
                requestBody.addFormDataPart(netparams.getName(), file.getName(), body);
            }
        }

        for (String key : netparams.getFormData().keySet()) {
            requestBody.addFormDataPart(key, String.valueOf(netparams.getFormData().get(key)));
        }
        netparams.getHeaders().put("content-type", "multipart/form-data");

        Request request = addHeaders(netparams.getHeaders()).url(netparams.getUrl()).post(new ProgressRequestBody(requestBody.build(),
                new ProgressRequestListener() {
                    @Override
                    public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                        Log.d("upload", "bytesWritten=" + bytesWritten + "contentLength=" + contentLength + "done=" + done);
                        if (callbackInterface != null) {
                            callbackInterface.onProgress((int) (bytesWritten / contentLength * 100), (int) bytesWritten, (int) contentLength);
                        }
                    }
                })).build();

        Call call = getClient(netparams).newCall(request);

        callMap.put(call.toString(), call);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callMap.remove(call.toString());
                if (callbackInterface != null) {
                    if (e.toString().contains("response's code is")) {
                        String[] s = e.toString().split(":");
                        callbackInterface.onFail(e.getMessage(), Integer.parseInt(s[s.length - 1]), new Headers.Builder().build());
                    } else {
                        callbackInterface.onFail(e.getMessage(), -1, new Headers.Builder().build());
                    }
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callMap.remove(call.toString());
                if (callbackInterface != null) {
                    doResponse(response, callbackInterface);
//                    if (response.isSuccessful())
//                        callbackInterface.onSuccess(response.body().string(), response.code(), response.headers());
//                    else
//                        callbackInterface.onFail(response.body().string(), response.code(), response.headers());
                }
            }
        });

        return call.toString();
    }

    @Override
    public String download(final NetParams netparams, final DownloadCallbackInterface callbackInterface) {
        OkHttpClient.Builder mOkHttpClientBuilder = new OkHttpClient.Builder();
        Request request = addHeaders(netparams.getHeaders()).url(netparams.getUrl()).build();
//        mOkHttpClientBuilder.addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                //拦截
//                Response originalResponse = chain.proceed(chain.request());
//                //包装响应体并返回
//                return originalResponse.newBuilder().body(new ProgressResponseBody(originalResponse.body(), new ProgressResponseListener() {
//                    @Override
//                    public void onResponseProgress(long bytesRead, long contentLength, boolean done) {
//                        if(callbackInterface!=null){
//                            callbackInterface.onProgress((int)(bytesRead/contentLength*100),(int)bytesRead,(int)contentLength);
//                        }
//                    }
//                })).build();
//            }
//        }).build();

        Call call = mOkHttpClientBuilder.build().newCall(request);

        callMap.put(call.toString(), call);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callMap.remove(call.toString());
                if (callbackInterface != null) {
                    if (e.toString().contains("response's code is")) {
                        String[] s = e.toString().split(":");
                        callbackInterface.onFail(e.getMessage(), Integer.parseInt(s[s.length - 1]), new Headers.Builder().build());
                    } else {
                        callbackInterface.onFail(e.getMessage(), -1, new Headers.Builder().build());
                    }
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (callbackInterface != null) {
//                    callMap.remove(call.toString());
                    if (response.isSuccessful()) {
                        InputStream is = null;
                        FileOutputStream fos = null;
                        byte[] buf = new byte[2048];
                        int len = 0;
                        try {
                            long total = response.body().contentLength();
                            Log.e(TAG, "total------>" + total);
                            long current = 0;
                            is = response.body().byteStream();
                            File file = new File(netparams.getFilePath());
                            File dir = file.getParentFile();
                            if (!dir.exists()) dir.mkdirs();
                            fos = new FileOutputStream(file);
                            while ((len = is.read(buf)) != -1) {
                                current += len;
                                fos.write(buf, 0, len);
                                Log.e(TAG, "current------>" + current);
                                callbackInterface.onProgress((int) (current * 100 / total), (int) current, (int) total);
                            }
                            fos.flush();
                            callbackInterface.onSuccess(file.getAbsolutePath(), response.code(), response.headers());
                        } catch (IOException e) {
                            Log.e(TAG, e.toString());
                            callbackInterface.onFail(e.getMessage(), -1, response.headers());
                            return;
                        } finally {
                            try {
                                if (is != null) {
                                    is.close();
                                }
                                if (fos != null) {
                                    fos.close();
                                }
                            } catch (IOException e) {
                                Log.e(TAG, e.toString());
                            }
                        }
                    } else
                        callbackInterface.onFail(response.message(), response.code(), response.headers());
                }
                callMap.remove(call.toString());
            }
        });

        return call.toString();
    }

    @Override
    public void abort(String TAG) {
        if (callMap.get(TAG) != null)
            callMap.get(TAG).cancel();
    }

    @Override
    public String networkType(Context context) {
        return NetUtil.getNetworkType(context);
    }

//    Headers SetHeaders(Map<String, Object> headersParams) {
//        Headers headers = null;
//        okhttp3.Headers.Builder headersbuilder = new okhttp3.Headers.Builder();
//        if (headersParams != null) {
//            Iterator<String> iterator = headersParams.keySet().iterator();
//            while (iterator.hasNext()) {
//                String key = iterator.next();
//                headersbuilder.add(key, String.valueOf(headersParams.get(key)));
//            }
//        }
//        headers = headersbuilder.build();
//        return headers;
//    }

    Request.Builder addHeaders(JSONObject header) {
        Request.Builder builder = new Request.Builder()
                .addHeader("phoneModel", Build.MODEL)
                .addHeader("systemVersion", Build.VERSION.RELEASE);
        if (header != null) {
            Iterator<String> iterator = header.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                builder.addHeader(key, String.valueOf(header.get(key)));
            }
        }
        return builder;
    }


}
