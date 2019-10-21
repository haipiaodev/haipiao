package com.haipiao.persist.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private int tagId;

    @Column(name = "img_id")
    private int imageId;

    @Column(name = "x_position")
    private int x;

    @Column(name = "y_position")
    private int y;

    private String text;

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return tagId == tag.tagId &&
                imageId == tag.imageId &&
                x == tag.x &&
                y == tag.y &&
                Objects.equals(text, tag.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagId, imageId, x, y, text);
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagId=" + tagId +
                ", imageId=" + imageId +
                ", x=" + x +
                ", y=" + y +
                ", text='" + text + '\'' +
                '}';
    }
}
