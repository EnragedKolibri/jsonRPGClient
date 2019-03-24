package com.jsonRPGClientFX.entities;

import javafx.scene.image.Image;

import java.io.File;

public class TerrainEntity extends DrawableEntity {

    public enum TerrainType{
        VOID, GROUND;

    }

    private TerrainType terrainType;

    public TerrainEntity(String name, TerrainType terrainType ,Double x, Double y) {

        super(Type.TERRAIN, name, x, y);
        this.terrainType = terrainType;
    }


    public void setImage(Image image)
    {
        super.setImage(image);
    }

    public void setFile(File file) {
        super.setImage(new Image(file.toURI().toString()));
    }

    public TerrainType getTerrainType()
    {
        return terrainType;
    }

}
