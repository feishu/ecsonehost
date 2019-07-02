package com.ucmed.tesla.weexchartlib;

import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;

/**
 * Created by WX-GXM-1326 on 2017/6/23.
 */

public class WXChartConfig {
    public static void init(){
        try {
            WXSDKEngine.registerComponent("chart",ChartComponent.class);
        } catch (WXException e) {
            e.printStackTrace();
        }
    }
}
