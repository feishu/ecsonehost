package com.ucmed.tesla.weexchartlib;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.taobao.weex.utils.WXResourceUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WX-GXM-1326 on 2017/6/23.
 */

public class LineChartHelper {

    private xAxisConfig xAxiscfg;
    private List<String> color;

    public void DataSet(LineChart mChart, JSONObject data){
        if(data.containsKey("title")){
            titleConfig title = JSON.parseObject(data.getString("title"),titleConfig.class);
//            mChart.setCenterText(title.getText());
//            mChart.setCenterTextColor(WXResourceUtils.getColor(title.getTextStyle().getColor()));
//            mChart.setCenterTextSizePixels(WXViewUtils.getRealPxByWidth(title.getTextStyle().getFontSize()));
        }
        if(data.containsKey("legend")){
            setLegend(mChart.getLegend(),JSON.parseObject(data.getString("legend"),legendConfig.class));
        }else{
            mChart.getLegend().setCustom(new ArrayList<LegendEntry>());
        }
        if(data.containsKey("yAxis")){
            setYAxis(mChart,JSON.parseObject(data.getString("yAxis"),yAxisConfig.class));
        }else {
            YAxis leftAxis = mChart.getAxisLeft();
            leftAxis.setDrawGridLines(false);
            leftAxis.setEnabled(false);
        }
        if(data.containsKey("xAxis")){
            setXAxis(mChart,JSON.parseObject(data.getString("xAxis"),xAxisConfig.class));
        }
        if(data.containsKey("color")){
            color = JSON.parseArray(data.getString("color"),String.class);
        }
        if(data.containsKey("series")){
            setData(mChart, JSON.parseArray(data.getString("series") ,LineAxisseriesConfig.class));
        }
        mChart.animateX(1000, Easing.EaseInOutQuart);
        mChart.setDescription(null);
        mChart.invalidate();
    }
    private void setLegend(Legend l,legendConfig config){
        if(config.getOrient().equals("horizontal"))l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        else l.setOrientation(Legend.LegendOrientation.VERTICAL);

        if(config.getX().toLowerCase().equals("right")) l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        else l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);

        if(config.getY().toLowerCase().equals("bottom")) l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        else l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);

        l.setTextSize(config.getTextStyle().getFontSize());
        l.setTextColor(config.getTextStyle().getColor());
//        l.setDrawInside(false);
//        l.setXEntrySpace(7f);
//        l.setYEntrySpace(0f);
//        l.setYOffset(0f);
    }

    private void setYAxis(LineChart mChart, yAxisConfig yaxis){
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setAxisMinimum(yaxis.getMin());
        leftAxis.setEnabled(true);
    }


    private void setData(LineChart mChart, List<LineAxisseriesConfig> arrayList) {

        mChart.getAxisRight().setEnabled(false);

        XAxis xAxis = mChart.getXAxis();

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
//        IAxisValueFormatter xAxisFormatter = new DefaultValueFormatter(0);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        if(color!=null){
            for (int i = 0; i < color.size(); i++) {
                colors.add(i, WXResourceUtils.getColor(color.get(i)));
            }
        }

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();

        for (int i = 0; i < arrayList.size(); i++) {
            ArrayList<Entry> values = new ArrayList<Entry>();
//            values.add(new Entry(0,Float.NaN));
            for (int j = 0; j < arrayList.get(i).getData().size(); j++) {
                values.add(new Entry(j+1,arrayList.get(i).getData().get(j)));
            }
//            values.add(new Entry(arrayList.get(i).getData().size()+1,Float.NaN));
            LineDataSet set = new LineDataSet(values, arrayList.get(i).getName());
//            set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set.setColor(colors.get(i));
            set.setCircleColor(colors.get(i));
            set.setLineWidth(1.8f);
            set.setCircleRadius(3.6f);
            set.setValueTextSize(10);
            if(arrayList.get(i).getAreaStyle()!=null) {
                set.setDrawFilled(true);
                if(arrayList.get(i).getAreaStyle().getNormal()!=null){
                    if(arrayList.get(i).getAreaStyle().getNormal().getColor().size()<=1)set.setFillColor(set.getColor());
                    else{
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            int[] ints = new int[arrayList.get(i).getAreaStyle().getNormal().getColor().size()];
                            for (int k = 0; k < arrayList.get(i).getAreaStyle().getNormal().getColor().size(); k++) {
                                ints[k] = WXResourceUtils.getColor(arrayList.get(i).getAreaStyle().getNormal().getColor().get(k));
                            }
                            GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,ints);
                            set.setFillDrawable(drawable);
                        }else{
                            set.setFillColor(set.getColor());
                        }
                    }
//                    set.setFillColor(arrayList.get(i).getAreaStyle().getNormal().getColor().equals("")?set.getColor():WXResourceUtils.getColor(arrayList.get(i).getAreaStyle().getNormal().getColor()));
                }else{
                    set.setFillColor(set.getColor());
                }
            }else{
                set.setDrawFilled(false);
            }
            dataSets.add(set);
        }

        LineData data = new LineData(dataSets);


        mChart.setData(data);

        if(xAxiscfg!=null) {
            mChart.getXAxis().setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    return value<=0?"":value>=xAxiscfg.getData().size()+1?"":xAxiscfg.getData().get((int)value-1);
                }

//                @Override
//                public String getFormattedValue(float value, AxisBase axis) {
//                    return value<=0?"":value>=xAxiscfg.getData().size()+1?"":xAxiscfg.getData().get((int)value-1);
//                }
            });
        }
    }

    public void setXAxis(LineChart mChart , xAxisConfig xaxis) {
        XAxis xAxis = mChart.getXAxis();
        xAxis.setGranularity(1f);
//        xAxis.setCenterAxisLabels(true);
        xAxis.setDrawLabels(true); // 显示 或 隐藏
        xAxis.setTextColor(Color.BLACK);
        xAxis.setTextSize(12);
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(xaxis.getData().size()+1);
        xAxiscfg = xaxis;
    }
}
