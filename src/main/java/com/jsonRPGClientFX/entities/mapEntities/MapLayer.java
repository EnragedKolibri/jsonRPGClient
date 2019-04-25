package com.jsonRPGClientFX.entities.mapEntities;
import com.jsonRPGClientFX.utils.Constants;
import lombok.Data;
import java.util.LinkedHashMap;

@Data
public class MapLayer {

    private boolean debugMode;
    private int[][] layerMatrix;
    private LinkedHashMap <Integer,MapLayerDictionaryEntity> mapLayerDictionary = new LinkedHashMap<>();

    public MapLayer(int[][] layerMatrix)
    {
        MapLayerDictionaryEntity defaultEntity = new MapLayerDictionaryEntity();
        if (isDebugMode())
        {
            defaultEntity.setImagePath("default/debug.png");
        }
            defaultEntity.setImagePath("default/void.png");

        defaultEntity.setRotation(0);
        this.layerMatrix = layerMatrix;
        mapLayerDictionary.put(0,defaultEntity);
    }

    public void setDebugMode(boolean debugMode)
    {
        this.debugMode = debugMode;
    }

    private boolean isDebugMode()
    {
        return debugMode;
    }


    private class MapLayerDictionaryEntity {
        private int rotation;
        private String imagePath;

        void setImagePath(String imagePath)
        {
            this.imagePath = Constants.DEFAULT_IMAGE_ROOT_FOLDER+imagePath;
        }

        String getImagePath()
        {
            return imagePath;
        }

        void setRotation(int rotation)
        {
            this.rotation = rotation;
        }

        int getRotation()
        {
            return rotation;
        }


    }

}

