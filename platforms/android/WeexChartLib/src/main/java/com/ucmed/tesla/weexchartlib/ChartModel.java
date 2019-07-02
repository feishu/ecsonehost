package com.ucmed.tesla.weexchartlib;

/**
 * Created by hkq325800 on 2017/8/16.
 */

public class ChartModel {

    private String gridColor = "";
    private boolean isBarShadowShow = false;

    public String getGridColor() {
        return gridColor;
    }

    public void setGridColor(String gridColor) {
        this.gridColor = gridColor;
    }

    public boolean isBarShadowShow() {
        return isBarShadowShow;
    }

    public void setBarShadowShow(boolean barShadowShow) {
        isBarShadowShow = barShadowShow;
    }

    public float getGridLineWidth() {
        return gridLineWidth;
    }

    public void setGridLineWidth(float gridLineWidth) {
        this.gridLineWidth = gridLineWidth;
    }

    private float gridLineWidth = 1;
}
