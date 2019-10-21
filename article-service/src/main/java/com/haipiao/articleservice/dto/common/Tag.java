package com.haipiao.articleservice.dto.common;

import com.google.gson.annotations.SerializedName;

public class Tag {
    @SerializedName("text")
    private String text;

    @SerializedName("x")
    private int x;

    @SerializedName("y")
    private int y;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
