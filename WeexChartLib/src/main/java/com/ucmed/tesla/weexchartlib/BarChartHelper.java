package com.ucmed.tesla.weexchartlib;

import android.graphics.Color;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.taobao.weex.utils.WXResourceUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by WX-GXM-1326 on 2017/6/23.
 */

public class BarChartHelper {

    private xAxisConfig xAxiscfg;
    private static List<String> color;
    private static List<AxisseriesConfig> seriess = new ArrayList<>();
    private static ChartModel chart;

    public void DataSet(BarChart mChart, JSONObject data){
        if(!mChart.isEmpty()) mChart.clear();
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
        }
        if(data.containsKey("color")){
            color = JSON.parseArray(data.getString("color"),String.class);
        }
        if(data.containsKey("chart")){
            chart = JSON.parseObject(data.getString("chart") ,ChartModel.class);
        }
        if(data.containsKey("series")){
            seriess = JSON.parseArray(data.getString("series") ,AxisseriesConfig.class);
        }
        if(data.containsKey("xAxis")){
            setXAxis(mChart,JSON.parseObject(data.getString("xAxis"),xAxisConfig.class));
        }
        if(data.containsKey("series")){
            setData(mChart, seriess/*JSON.parseArray(data.getString("series") ,AxisseriesConfig.class)*/);
        }

        if(chart!=null){
            mChart.getAxisLeft().setGridColor(Color.parseColor(chart.getGridColor()));
            mChart.setDrawBarShadow(chart.isBarShadowShow());
            mChart.getAxisLeft().setGridLineWidth(chart.getGridLineWidth());
        }
        mChart.animateY(1000);
        mChart.setDescription(null);
        mChart.invalidate();
    }
    private static void setLegend(Legend l,legendConfig config){
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

    private static void setYAxis(BarChart mChart, yAxisConfig yaxis){
        YAxis leftAxis = mChart.getAxisLeft();
        if(!String.valueOf(yaxis.getMin()).equals("NaN"))
        leftAxis.setAxisMinimum(yaxis.getMin());
        if(!String.valueOf(yaxis.getMax()).equals("NaN"))
        leftAxis.setAxisMaximum(yaxis.getMax());
    }

    private void setData(BarChart mChart, final List<AxisseriesConfig> arrayList) {
        mChart.setHighlightFullBarEnabled(true);
        float groupSpace = 0.05f;
        float barSpace = (1f-0.05f)/arrayList.size()/10;
        float barWidth = barSpace*9;

        XYMarkerView mv = new XYMarkerView(mChart.getContext());
        mv.setChartView(mChart); // For bounds control
        mChart.setMarker(mv); // Set the marker to the chart

        mChart.getAxisRight().setEnabled(false);

        XAxis xAxis = mChart.getXAxis();

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
//        IAxisValueFormatter xAxisFormatter = new DefaultValueFormatter(0);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();
        if(color!=null){
            for (int i = 0; i < color.size(); i++) {
                colors.add(WXResourceUtils.getColor(color.get(i)));
            }
        }
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



        BarData data = new BarData();
        ArrayList<String> xVals = new ArrayList<>();//X轴数据

        for (int i = 0; i < arrayList.size(); i++) {
            ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();
            for (int j = 0; j < arrayList.get(i).getData().size(); j++) {
                float[] vals = new float[arrayList.get(i).getData().get(j).size()];
                for (int k = 0; k < arrayList.get(i).getData().get(j).size(); k++) {
                    vals[k] = arrayList.get(i).getData().get(j).get(k);
                }
                yVals.add(new BarEntry(j,vals/*arrayList.get(i).getData().get(j)*/));
            }
            BarDataSet set = new BarDataSet(yVals, arrayList.get(i).getName());
//            set.setHighLightColor(Color.parseColor("#696969"));//颜色叠加
            final int finalI = i;
            set.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    return new DecimalFormat(arrayList.get(finalI).getFormat()).format(value);
                }
            });
//            set.setValueFormatter(new IValueFormatter() {
//                @Override
//                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
//                    return new DecimalFormat(arrayList.get(finalI).getFormat()).format(value);
//                }
//            });
            set.setHighLightAlpha(arrayList.get(i).getHighLightAlpha());
            if(arrayList.get(i).getColor().size()>0){
                int[] colori = new int[arrayList.get(i).getColor().size()];
                for (int l = 0; l < arrayList.get(i).getColor().size(); l++) {
                    colori[l] = WXResourceUtils.getColor(arrayList.get(i).getColor().get(l));
                }
                set.setColors(colori);
            }else {
                set.setColor(colors.get(i));
            }
            data.addDataSet(set);
            data.setDrawValues(arrayList.get(i).isDrawvalue());
//            if(arrayList.size()==1){
//                data = new BarData(set);
//                data.setDrawValues(arrayList.get(i).isDrawvalue());
//            }
        }




//        dataSet.setColors(colors);
//        dataSet.setSelectionShift(0f);

//        dataSet.setValueLinePart1OffsetPercentage(80.f);
//        dataSet.setValueLinePart1Length(0.2f);
//        dataSet.setValueLinePart2Length(0.4f);
//        //dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
//        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);


        mChart.setData(data);


        if(xAxiscfg!=null) {
            int xx = mChart.getXAxis().getLabelCount();
            String s = mChart.getXAxis().getFormattedLabel(0);
            mChart.getXAxis().setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    return value<0?"":value>=xAxiscfg.getData().size()?"":xAxiscfg.getData().get((int)value);
                }
            });
//            mChart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
//                @Override
//                public String getFormattedValue(float value, AxisBase axis) {
//                    return value<0?"":value>=xAxiscfg.getData().size()?"":xAxiscfg.getData().get((int)value);
//                }
//            });
//            mChart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
//                @Override
//                public String getFormattedValue(float value, AxisBase axis) {
//                    return xAxiscfg.getData().get((int) value);
//                }
//            });
        }

        if(!String.valueOf(arrayList.get(arrayList.size()-1).getBarWidth()).equals("NaN"))
            mChart.getBarData().setBarWidth(arrayList.get(arrayList.size()-1).getBarWidth());
        else
            mChart.getBarData().setBarWidth(barWidth);

        if(arrayList.size()>1) {
            if(!String.valueOf(arrayList.get(arrayList.size()-1).getBarGap()).equals("NaN")&&!String.valueOf(arrayList.get(arrayList.size()-1).getBarCategoryGap()).equals("NaN"))
                mChart.groupBars(0,arrayList.get(arrayList.size()-1).getBarGap(),arrayList.get(arrayList.size()-1).getBarCategoryGap());
            else
                mChart.groupBars(0, groupSpace, barSpace);
        }
    }

    public void setXAxis(BarChart mChart , xAxisConfig xaxis) {
        XAxis xAxis = mChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(seriess.size()>1);
        xAxis.setDrawLabels(true); // 显示 或 隐藏
        xAxis.setTextColor(Color.BLACK);
        xAxis.setTextSize(12);
        if(seriess.size()>1) {
            xAxis.setAxisMinimum(0);
            xAxis.setAxisMaximum(xaxis.getData().size());
        }
        xAxiscfg = xaxis;
    }
}
