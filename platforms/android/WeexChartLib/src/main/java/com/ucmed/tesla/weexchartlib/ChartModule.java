package com.ucmed.tesla.weexchartlib;

import com.taobao.weex.WXSDKManager;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.ui.component.WXComponent;

/**
 * Created by WX-GXM-1326 on 2017/6/23.
 */

public class ChartModule extends WXModule {

    public ChartComponent Chart(String ref){
        WXComponent Component =
                WXSDKManager.getInstance()
                        .getWXRenderManager()
                        .getWXComponent(mWXSDKInstance.getInstanceId(), ref);
        if(Component instanceof  ChartComponent){
            return (ChartComponent) Component;
        }else {
            return null;
        }
    }
}
