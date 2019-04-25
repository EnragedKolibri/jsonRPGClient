package com.jsonRPGClientFX.entities.mapEntities;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.LinkedHashMap;


public class Map {

            private String title;
            private Image mapPreview;

            private LinkedHashMap<String, MapLayer> LayeredMap = new LinkedHashMap<>();
            private ArrayList<String> registeredLayerNames = new ArrayList<>();
            private int[][] collisionLayer;

            public void addLayer(String layerName, MapLayer layer){

                registeredLayerNames.add(layerName);
                LayeredMap.put(layerName, layer);
            }

            public MapLayer getLayer(String layerName) {

                return LayeredMap.get(layerName);
            }

            public void setCollisionLayer(int[][] collisionLayer){

                this.collisionLayer=collisionLayer;
            }

            public int[][] getCollisionLayer() {
                return collisionLayer;
            }

            public void setMapPreview(Image mapPreview) {
                this.mapPreview = mapPreview;
            }

            public Image getMapPreview() {
                return mapPreview;
            }

            public void SetMapTitle(String title) {
                this.title=title;
            }

            public String getMapTitle() {
                return title;
            }

            public ArrayList<String> getRegisteredLayerNames()
            {
                return registeredLayerNames;
            }

}
