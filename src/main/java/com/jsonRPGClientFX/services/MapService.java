package com.jsonRPGClientFX.services;

import com.jsonRPGClientFX.entities.mapEntities.Map;
import com.jsonRPGClientFX.utils.UtilsLogger;

import java.util.LinkedHashMap;

public class MapService {
    private Integer mapsInLisCounter = 0;//это щит и нужно сделать как то не так
    private Integer currentMapId; //задел на будующще здесь будет храниться id арты на которой играют
    private LinkedHashMap<Integer, Map> mapsList = new LinkedHashMap<>();

    public Map getMap(Integer id)
    {
        if (id > mapsInLisCounter||id < 0)
        {
            UtilsLogger.log("Wrong map ID");
            return null;
        }
        return mapsList.get(id);
    }

    public void addMapToList(Map mapEntity)
    {
        mapsInLisCounter++;
        mapsList.put(mapsInLisCounter,mapEntity);
    }



}
