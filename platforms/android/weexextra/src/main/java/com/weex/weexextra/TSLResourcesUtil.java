package com.weex.weexextra;

import android.content.Context;

public class TSLResourcesUtil {

    /**
     * 根据资源的名称获取资源id
     * @param context
     * @param name
     * @return
     */
    public static int getResourceInMipmap(Context context, String name) {
        int resId = context.getResources().getIdentifier(name, "mipmap", context.getPackageName());
        //如果没有在"mipmap"下找到imageName,将会返回0
        return resId;
    }


}
