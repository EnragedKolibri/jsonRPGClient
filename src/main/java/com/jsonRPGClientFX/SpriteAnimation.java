package com.jsonRPGClientFX;

import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


public class SpriteAnimation extends Transition {

    private ImageView imageView;
    private int count;
    private int colums;
    private int offsetX;
    private int offsetY;
    private int width;
    private int height;

    public SpriteAnimation(ImageView imageView, Duration duration, int count, int colums, int offsetX, int offsetY, int width, int height){
        this.imageView=imageView;
        this.count = count;
        this.colums = colums;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
        setCycleDuration(duration);

    }

    @Override
    protected void interpolate(double frac) {
        final int index = Math.min((int) Math.floor(frac*count),count-1);
        final int x = (index % colums) * width+offsetX;
        final int y = (index / colums) * height+offsetY;
        imageView.setViewport(new Rectangle2D(x,y,width,height));
    }
}
