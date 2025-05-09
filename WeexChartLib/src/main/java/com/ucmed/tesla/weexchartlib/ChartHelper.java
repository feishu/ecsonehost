package com.ucmed.tesla.weexchartlib;

import com.alibaba.fastjson.JSONObject;

import static com.ucmed.tesla.weexchartlib.ChartComponent.bar_type;
import static com.ucmed.tesla.weexchartlib.ChartComponent.line_type;
import static com.ucmed.tesla.weexchartlib.ChartComponent.pie_type;

/**
 * Created by WX-GXM-1326 on 2017/6/23.
 */

public class ChartHelper {

    public static void render(String type , JSONObject jsonObject , ChartComponent component){
        if(component.Charttype == -1)
        component.setCharttype(type);
        switch (component.Charttype){
            case pie_type :
                PieChartHelper pieChartHelper = new PieChartHelper();
                pieChartHelper.DataSet(component.getHostView().getPieChart(),jsonObject);
                break;
            case bar_type :
                BarChartHelper barChartHelper = new BarChartHelper();
                barChartHelper.DataSet(component.getHostView().getBarChart(),jsonObject);
                break;
            case line_type :
                LineChartHelper lineChartHelper = new LineChartHelper();
                lineChartHelper.DataSet(component.getHostView().getLineChart(),jsonObject);
                break;
        }
    }
}
