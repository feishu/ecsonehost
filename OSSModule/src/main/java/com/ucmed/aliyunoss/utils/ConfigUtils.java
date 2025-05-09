package com.ucmed.aliyunoss.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.sdk.android.oss.ClientConfiguration;

public class ConfigUtils {

    /**
     * Auth initAuthConfig
     * @param configuration
     * @return
     */
    public static ClientConfiguration initAuthConfig(JSONObject configuration) {
       ClientConfiguration conf = new ClientConfiguration();
       conf.setConnectionTimeout(configuration.getIntValue("timeoutIntervalForRequest") * 1000);
       conf.setSocketTimeout(configuration.getIntValue("timeoutIntervalForRequest") * 1000);
       conf.setMaxConcurrentRequest(configuration.getIntValue("maxRetryCount"));
       conf.setMaxErrorRetry(configuration.getIntValue("maxRetryCount"));
       return conf;
   }
}
