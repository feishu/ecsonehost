package com.ucmed.tesla.weexchartlib;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WX-GXM-1326 on 2017/6/26.
 */

public class LineAxisseriesConfig {
    private String type = "";

    private String name = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private List<Float> data ;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Float> getData() {
        return data;
    }

    public void setData(List<Float> data) {
        this.data = data;
    }

    public areaStyle areaStyle;

    public LineAxisseriesConfig.areaStyle getAreaStyle() {
        return areaStyle;
    }

    public void setAreaStyle(LineAxisseriesConfig.areaStyle areaStyle) {
        this.areaStyle = areaStyle;
    }

    public class areaStyle{

        public LineAxisseriesConfig.areaStyle.normal getNormal() {
            return normal;
        }

        public void setNormal(LineAxisseriesConfig.areaStyle.normal normal) {
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
