package com.jsonRPGClientFX;

import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


public class SpriteAnimation extends Transition {


    private Image image;
    private ImageView imageView;
    private int count;
    private int colums;
    private int offsetX;
    private int offsetY;
    private int width;
    private int height;

    public SpriteAnimation(Image image, Duration duration, int count, int colums, int offsetX, int offsetY, int width, int height, int cycleCount){
        this.image = image;
        this.imageView = new ImageView(image);
        this.imageView.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
        this.count = count;
        this.colums = colums;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
        setCycleDuration(duration);
        this.setCycleCount(cycleCount);

    }

    public ImageView getImageView() {
        return imageView;
    }


    // доделать выбор колонки и строки независимо
    @Override
    protected void interpolate(double frac) {
        final int index = Math.min((int) Math.floor(frac*count),count-1);
        final int x = (index % colums) * width+offsetX;
        final int y = (index / colums) * height+offsetY;
        imageView.setViewport(new Rectangle2D(x,y,width,height));
    }
}
