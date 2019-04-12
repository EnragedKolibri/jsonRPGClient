package com.jsonRPGClientFX.services;

import com.jsonRPGClientFX.entities.MapEntity;
import com.jsonRPGClientFX.utils.UtilsLogger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

public class MapService {
    private Integer mapsInLisCounter = 0;//это щит и нужно сделать как то не так
    private Integer currentMapId; //задел на будующще здесь будет храниться id арты на которой играют
    private LinkedHashMap<Integer, MapEntity> mapsList = new LinkedHashMap<>();

    public void getMap(Integer id)
    {
        if (id > mapsInLisCounter||id < 0)
        {
            UtilsLogger.log("Wrong map ID");
            return;
        }
        mapsList.get(id);
    }

    public void addMapToList(MapEntity mapEntity)
    {
        mapsInLisCounter++;
        mapsList.put(mapsInLisCounter,mapEntity);
    }



}
