package com.ucmed.tesla.weexchartlib;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WX-GXM-1326 on 2017/6/26.
 */

public class AxisseriesConfig {
    private String type = "";

    private String name = "";

    private List<String> color = new ArrayList<>();

    private float barWidth = Float.NaN;
    private float barGap = Float.NaN;
    private float barCategoryGap = Float.NaN;

    private String format = "#";

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getHighLightAlpha() {
        return highLightAlpha;
    }

    public void setHighLightAlpha(int highLightAlpha) {
        this.highLightAlpha = highLightAlpha;
    }

    private int highLightAlpha = 120;

    public float getBarWidth() {
        return barWidth;
    }

    public void setBarWidth(float barWidth) {
        this.barWidth = barWidth;
    }

    public float getBarGap() {
        return barGap;
    }

    public void setBarGap(float barGap) {
        this.barGap = barGap;
    }

    public float getBarCategoryGap() {
        return barCategoryGap;
    }

    public void setBarCategoryGap(float barCategoryGap) {
        this.barCategoryGap = barCategoryGap;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }

    private boolean drawvalue = true;

    public boolean isDrawvalue() {
        return drawvalue;
    }

    public void setDrawvalue(boolean drawvalue) {
        this.drawvalue = drawvalue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private List<List<Integer>> data ;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<List<Integer>> getData() {
        return data;
    }

    public void setData(List<List<Integer>> data) {
        this.data = data;
    }

    public areaStyle areaStyle;

    public AxisseriesConfig.areaStyle getAreaStyle() {
        return areaStyle;
    }

    public void setAreaStyle(AxisseriesConfig.areaStyle areaStyle) {
        this.areaStyle = areaStyle;
    }

    public class areaStyle{

        public AxisseriesConfig.areaStyle.normal getNormal() {
            return normal;
        }

        public void setNormal(AxisseriesConfig.areaStyle.normal normal) {
            this.normal = normal;
        }

        public normal normal;

        public class normal{

            public ArrayList<String> color = new ArrayList<>();
            public int shadowBlur;
            public String shadowColor;
            public int shadowOffsetX;
            public int shadowOffsetY;
            public int opacity;

            public ArrayList<String> getColor() {
                return color;
            }

            public void setColor(ArrayList<String> color) {
                this.color = color;
            }

            public int getShadowBlur() {
                return shadowBlur;
            }

            public void setShadowBlur(int shadowBlur) {
                this.shadowBlur = shadowBlur;
            }

            public String getShadowColor() {
                return shadowColor;
            }

            public void setShadowColor(String shadowColor) {
                this.shadowColor = shadowColor;
            }

            public int getShadowOffsetX() {
                return shadowOffsetX;
            }

            public void setShadowOffsetX(int shadowOffsetX) {
                this.shadowOffsetX = shadowOffsetX;
            }

            public int getShadowOffsetY() {
                return shadowOffsetY;
            }

            public void setShadowOffsetY(int shadowOffsetY) {
                this.shadowOffsetY = shadowOffsetY;
            }

            public int getOpacity() {
                return opacity;
            }

            public void setOpacity(int opacity) {
                this.opacity = opacity;
            }
        }
    }
//    public static class Data {
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public float getValue() {
//            return value;
//        }
//
//        public void setValue(float value) {
//            this.value = value;
//        }
//
//        private String name = "";
//        private float value = 0;
//
//    }
}
