package com.ucmed.tesla.weexchartlib;

import android.graphics.Color;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.taobao.weex.utils.WXResourceUtils;
import com.taobao.weex.utils.WXViewUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WX-GXM-1326 on 2017/6/23.
 */

public class PieChartHelper {

    private List<String> color;
    private tooltip mtooltip;
    public void DataSet(PieChart mChart, JSONObject data){
        if(data.containsKey("title")){
            titleConfig title = JSON.parseObject(data.getString("title"),titleConfig.class);
            mChart.setCenterText(title.getText());
            mChart.setCenterTextColor(WXResourceUtils.getColor(title.getTextStyle().getColor()));
            mChart.setCenterTextSizePixels(WXViewUtils.getRealPxByWidth(title.getTextStyle().getFontSize()));
        }
        if(data.containsKey("tooltip")){
            mtooltip = JSON.parseObject(data.getString("tooltip"),tooltip.class);
            mChart.setUsePercentValues(mtooltip.formatter.contains("%"));
        }
        if(data.containsKey("legend")){
            setLegend(mChart,JSON.parseObject(data.getString("legend"),legendConfig.class));
        }else{
            mChart.getLegend().setCustom(new ArrayList<LegendEntry>());
        }
        if(data.containsKey("color")){
            color = JSON.parseArray(data.getString("color"),String.class);
        }
        if(data.containsKey("series")){
            setData(mChart, JSON.parseArray(data.getString("series") ,seriesConfig.class));
        }
        mChart.animateX(1000);
        mChart.setDescription(null);
        mChart.invalidate();
    }
    private void setLegend(PieChart mChart,legendConfig config){
        Legend l = mChart.getLegend();
        if(config.getOrient().equals("horizontal"))l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        else l.setOrientation(Legend.LegendOrientation.VERTICAL);

        if(config.getX().toLowerCase().equals("right")) l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        else l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);

        if(config.getY().toLowerCase().equals("bottom")) l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        else l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);

        l.setTextSize(config.getTextStyle().getFontSize());
        l.setTextColor(config.getTextStyle().getColor());

        if(config.getData().size()>0) {
            ArrayList<LegendEntry> legendEntries = new ArrayList<>();
            for (String s:config.getData()) {
                LegendEntry legend = new LegendEntry();
                legend.label = s;
                legendEntries.add(legend);
            }
            mChart.getLegend().setCustom(legendEntries);
        }

//        l.setDrawInside(false);
//        l.setXEntrySpace(7f);
//        l.setYEntrySpace(0f);
//        l.setYOffset(0f);
    }

    private void setData(PieChart mChart,List<seriesConfig> arrayList) {

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < arrayList.get(0).getData().size() ; i++) {
            entries.add(new PieEntry( arrayList.get(0).getData().get(i).getValue(),
                    arrayList.get(0).getData().get(i).getName()));
        }

        PieDataSet dataSet = new PieDataSet(entries," ");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(0f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

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



        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

//        dataSet.setValueLinePart1OffsetPercentage(80.f);
//        dataSet.setValueLinePart1Length(0.2f);
//        dataSet.setValueLinePart2Length(0.4f);
//        //dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
//        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        PieData piedata = new PieData(dataSet);
        piedata.setValueFormatter(mtooltip.formatter.contains("%")?new PercentFormatter(new DecimalFormat("########0")):new DefaultValueFormatter(0));
        piedata.setValueTextSize(11f);
        piedata.setValueTextColor(Color.WHITE);
        mChart.setData(piedata);


        mChart.setDrawHoleEnabled(true);
        mChart.setTransparentCircleAlpha(0);
        // undo all highlights
        mChart.highlightValues(null);

    }
}
