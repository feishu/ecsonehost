package com.ucmed.tesla.weexchartlib;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSONObject;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.Constants;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.utils.WXResourceUtils;

import java.util.Map;


/**
 * Created by WX-GXM-1326 on 2017/6/23.
 */

public class ChartComponent extends WXComponent<ChartLayout> {
    public int Charttype = -1;
    public static final int pie_type = 0;
    public static final int bar_type = 1;
    public static final int line_type = 2;

    public ChartComponent(WXSDKInstance instance, WXVContainer parent, BasicComponentData basicComponentData) {
        super(instance, parent, basicComponentData);
    }

    public ChartComponent(WXSDKInstance instance, WXVContainer parent, int type, BasicComponentData basicComponentData) {
        super(instance, parent, type, basicComponentData);
    }

//    public ChartComponent(WXSDKInstance instance, WXDomObject dom, WXVContainer parent) {
//        super(instance, dom, parent);
//    }



    @Override
    protected ChartLayout initComponentHostView(@NonNull Context context) {
        ChartLayout chartLayout = new ChartLayout(context);
        return chartLayout;
    }

    @Override
    public void updateProperties(Map<String, Object> props) {
        super.updateProperties(props);
    }

    @Override
    protected boolean setProperty(String key, Object param) {
        switch (key){
            case "option":
                setOption(param.toString());
                return true;
            default:
                return super.setProperty(key, param);
        }
    }

    public void setCharttype(@NonNull String type){
        getHostView().setCharttype(type);
        if(type.toLowerCase().contains("pie")){
            Charttype = pie_type;
        }
        if(type.toLowerCase().contains("bar")){
            Charttype = bar_type;
        }
        if(type.toLowerCase().contains("line")){
            Charttype = line_type;
        }
    }

    @WXComponentProp(name = "option ")
    public void setOption(@NonNull String option ){
            JSONObject jsonObject = JSONObject.parseObject(option);
            String type = jsonObject.getJSONArray("series").getJSONObject(0).getString("type");
            ChartHelper.render(type,jsonObject,this);
    }

}
