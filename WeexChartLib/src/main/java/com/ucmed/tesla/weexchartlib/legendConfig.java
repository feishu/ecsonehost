package com.ucmed.tesla.weexchartlib;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WX-GXM-1326 on 2017/6/26.
 */

public class legendConfig {
    private String orient = "";
    private String x = "";
    private String y = "";
    private textstyle textStyle = new textstyle();
    private List<String> data = new ArrayList<>();

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public textstyle getTextStyle() {
        return textStyle;
    }

    public void setTextStyle(textstyle textStyle) {
        this.textStyle = textStyle;
    }

    public String getOrient() {
        return orient;
    }

    public void setOrient(String orient) {
        this.orient = orient;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public class textstyle{
        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public String getFontStyle() {
            return fontStyle;
        }

        public void setFontStyle(String fontStyle) {
            this.fontStyle = fontStyle;
        }

        public int getFontSize() {
            return fontSize;
        }

        public void setFontSize(int fontSize) {
            this.fontSize = fontSize;
        }

        private int color = Color.BLACK;
        private String fontStyle = "normal";
        private int fontSize = 12;
    }
}
