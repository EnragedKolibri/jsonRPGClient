package com.jsonRPGClientFX.entities.mapEntities;

import lombok.Data;

@Data
public class MapLayerEntity {

    private int[][] layerMatrix;

    public MapLayerEntity(int[][] layerMatrix)
    {
        this.layerMatrix = layerMatrix;
    }
}
