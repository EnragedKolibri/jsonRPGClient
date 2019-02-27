package com.jsonRPGClientFX.entities;

import javafx.scene.image.Image;

import java.io.File;

public class DecorationEntity extends DrawableEntity {

    public DecorationEntity(String name, Double x, Double y) {
        super(Type.ITEM, name, x, y);
    }

    public void setFile(File file) {
        super.setImage(new Image(file.toURI().toString()));
    }

}
