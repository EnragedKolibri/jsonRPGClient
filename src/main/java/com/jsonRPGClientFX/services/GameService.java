package com.jsonRPGClientFX.services;

import com.jsonRPGClientFX.entities.mapEnteties.MapEntity;

public class GameService {


    public void star() {
        //
        MapService mapService = new MapService();
        mapService.loadMap(new MapEntity());
    }
}
