package com.jsonRPGClientFX.entities;

import com.jsonRPGClientFX.components.IRenderable;
import javafx.scene.image.Image;

import java.io.File;

public abstract class DrawableEntity implements IRenderable {

    public enum Type {
        TERRAIN, PLAYER, ITEM
    }

    private String name;
    private Type type;
    private Image image;
    private Double x;
    private Double y;

    DrawableEntity(Type type, String name, Double x, Double y) {
        this.type = type;
        this.name = name;
        this.x = x;
        this.y = y;
    }

    protected void setImage(Image image) {
        this.image = image;
    }

    private String getName() {
        return this.name;
    }

    public Type getType() {
        return this.type;
    }

    public Image getImage() {
        return this.image;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public void renderIfInFrame(Double x, Double y) {
        if (this.x > x && this.y > y) return;
        this.render();
    }

}
