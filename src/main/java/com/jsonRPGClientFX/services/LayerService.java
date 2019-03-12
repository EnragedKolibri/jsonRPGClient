package com.jsonRPGClientFX.services;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.*;

public class LayerService {

    private Map<String, Canvas> canvasMap = new LinkedHashMap<>();

    public void registerNewGraphicContext(String canvasName, int width, int height) {
        canvasMap.put(canvasName, new Canvas(width, height));
    }

    public Canvas getRegisteredCanvas(String canvasName) {
        return canvasMap.get(canvasName);
    }

    public GraphicsContext getRegisteredGraphicContext(String canvasName){
        Canvas canvas = getRegisteredCanvas(canvasName);
        return canvas.getGraphicsContext2D();
    }

    public Collection<Canvas> getAllRegisteredCanvas() {

        return canvasMap.values();
    }

}
