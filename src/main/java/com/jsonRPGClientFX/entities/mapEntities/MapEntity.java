package com.jsonRPGClientFX.entities.mapEntities;

import javafx.scene.image.Image;
import lombok.Data;

import java.util.LinkedHashMap;

@Data
public class MapEntity {

            private String title;
            private Image mapPreview;

            private LinkedHashMap<String,MapLayerEntity> LayeredMap = new LinkedHashMap<>();
            private int[][] collisionLayer;

            public void addLayer(String layerName, MapLayerEntity mapLayerEntity)
            {
                LayeredMap.put(layerName,mapLayerEntity);
            }

            public int[][] getLayer(String layerName)
            {
                return LayeredMap.get(layerName).getLayerMatrix();
            }

}
