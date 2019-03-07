package com.jsonRPGClientFX.services;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.*;

public class GameService {

    private Map<String, Canvas> canvasMap = new HashMap<>();
    private List<String> order = new ArrayList<>();

    public void registerNewGraphicContext(String canvasName, int width, int height) {
        order.add(canvasName);
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
        List<Canvas> canvasList = new ArrayList<>();
        order.forEach(name -> {
            System.out.println("Canvas " + name);
            canvasList.add(getRegisteredCanvas(name));
        });
        return canvasList;
//        return canvasMap.values();
    }

}
