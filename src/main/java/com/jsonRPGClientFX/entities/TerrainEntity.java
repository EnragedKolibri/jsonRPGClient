package com.jsonRPGClientFX.entities;

import javafx.scene.image.Image;

import java.io.File;

public class TerrainEntity extends DrawableEntity {

    public TerrainEntity(String name, Double x, Double y) {
        super(Type.TERRAIN, name, x, y);
    }

    public void setFile(File file) {
        super.setImage(new Image(file.toURI().toString()));
    }

}
