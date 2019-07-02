package com.ucmed.tesla.weexchartlib;

/**
 * Created by WX-GXM-1326 on 2017/6/23.
 */

public class titleConfig {
    public String text = "";
    public textStyle textStyle = new textStyle();

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public titleConfig.textStyle getTextStyle() {
        return textStyle;
    }

    public void setTextStyle(titleConfig.textStyle textStyle) {
        this.textStyle = textStyle;
    }

    public class textStyle{
        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public int getFontSize() {
            return fontSize;
        }

        public void setFontSize(int fontSize) {
            this.fontSize = fontSize;
        }

        public String color = "black";
        public int fontSize = 24;
    }
}
