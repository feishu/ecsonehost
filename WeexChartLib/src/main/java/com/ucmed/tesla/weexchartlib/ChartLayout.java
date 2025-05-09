package com.ucmed.tesla.weexchartlib;

import android.content.Context;
import android.os.Build;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.annotation.RequiresApi;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.ViewStub;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;

/**
 * Created by WX-GXM-1326 on 2017/6/26.
 */

public class ChartLayout extends LinearLayout {
    public PieChart getPieChart() {
        return pieChart;
    }

    public LineChart getLineChart() {
        return lineChart;
    }

    public BarChart getBarChart() {
        return barChart;
    }

    private PieChart pieChart;
    private LineChart lineChart;
    private BarChart barChart;
    public ChartLayout(Context context) {
        this(context,null);
    }

    public ChartLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.chart, this);
    }

    public void setCharttype(@NonNull String type){
        if(type.toLowerCase().contains("pie")){
            ViewStub myViewStub = (ViewStub)findViewById(R.id.piechart);
            myViewStub.inflate();
            pieChart = (PieChart)findViewById(R.id.pie_chart);
        }
        if(type.toLowerCase().contains("bar")){
            ViewStub myViewStub = (ViewStub)findViewById(R.id.barchart);
            myViewStub.inflate();
            barChart = (BarChart)findViewById(R.id.bar_chart);
        }
        if(type.toLowerCase().contains("line")){
            ViewStub myViewStub = (ViewStub)findViewById(R.id.linechart);
            myViewStub.inflate();
            lineChart = (LineChart)findViewById(R.id.line_chart);
        }
    }
}
