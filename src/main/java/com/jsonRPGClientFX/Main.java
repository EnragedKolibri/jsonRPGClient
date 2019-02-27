package com.jsonRPGClientFX;

import com.jsonRPGClientFX.entities.DecorationEntity;
import com.jsonRPGClientFX.entities.DrawableEntity;
import com.jsonRPGClientFX.entities.TerrainEntity;
import com.jsonRPGClientFX.entities.mapEnteties.Layer;
import com.jsonRPGClientFX.entities.mapEnteties.MapEntity;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class Main extends Application{


    static int suparPiecOfShittConstant = 32;
    static ArrayList<File> files = new ArrayList<>();
    Map<String, Layer> map;
    MapEntity mapEntity = new MapEntity("testMapa",map);


    static {
        files.add(new File("assets/terrain/1470105_3.png"));
        files.add(new File("assets/terrain/2473250_1.png"));
    }

    private static int[][] mapa =
            {
                    {0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0}


            };

    private static int[][] mapa1 =
            {
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}

            };



    private static DrawableEntity[][] drawableEntities;

    int i = 0;
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{

//        int ultraShittedWidth = zachemYaJivy(50);

        int ultraShittedWidth = mapa[0].length * suparPiecOfShittConstant;
        int ultraShittedHeight = mapa.length * suparPiecOfShittConstant;
//        int ultraShittedHeight = zachemYaJivy(20);

        Pane root = new StackPane();
        root.setPrefSize(ultraShittedWidth,ultraShittedHeight);

        Scene s = new Scene(root, ultraShittedWidth, ultraShittedHeight, Color.BLACK);


        Canvas mainLayer = new Canvas(ultraShittedWidth, ultraShittedHeight);
        Canvas testLayer = new Canvas(200,200);
        Canvas newLayer = new Canvas(ultraShittedWidth,ultraShittedHeight);
        GraphicsContext mainLayerGraphicCtx = mainLayer.getGraphicsContext2D();
        GraphicsContext testLayerGc = testLayer.getGraphicsContext2D();
        GraphicsContext newLayerGCtx = newLayer.getGraphicsContext2D();
        root.getChildren().addAll(mainLayer,testLayer, newLayer);


        drawableEntities = new DrawableEntity[mapa[0].length][mapa.length];
        for (int y = 0; y <  mapa.length; y++) {
            for (int x = 0; x < mapa[0].length ; x ++) {
                double yC = suparPiecOfShittConstant * y;
                double xC = suparPiecOfShittConstant * x;
                switch (mapa[y][x]) {
                    case 0: {
                        TerrainEntity terrainEntity = new TerrainEntity("name"+y+x, xC, yC);
                        terrainEntity.setFile(fileProvider(terrainEntity.getType()));
                        drawableEntities[x][y] = terrainEntity;
                        break;
                    }
                    case 1: {
                        DecorationEntity decorationEntity = new DecorationEntity("name"+y+x, xC, yC);
                        decorationEntity.setFile(fileProvider(decorationEntity.getType()));
                        drawableEntities[x][y] = decorationEntity;
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        }


        //Layer filler algorithm proto
//        int x = 0;
//        int y = 0;
//        for (int i = 0; i != ultraShittedWidth; i++) {
//            if (x == ultraShittedWidth) {
//                y+=suparPiecOfShittConstant;
//                x=0;
//            }
//            gc.drawImage(imageProcessor(files), x , y,suparPiecOfShittConstant,suparPiecOfShittConstant);
//            x+=suparPiecOfShittConstant;
//        }

//        for (int y = 0; y <  mapa.length; y++) {
//            int yC = suparPiecOfShittConstant * y;
//
//            System.out.println("y " + yC);
//            for (int x = 0; x < mapa[0].length ; x ++) {
//                int xC = suparPiecOfShittConstant * x;
//
//                mainLayerGraphicCtx.drawImage(imageProcessor(mapa[y][x]), xC , yC,suparPiecOfShittConstant,suparPiecOfShittConstant);
//            }
//        }


        for (int y = 0; y <  drawableEntities.length; y++) {
//            int yC = suparPiecOfShittConstant * y;

//            System.out.println("y " + yC);
            for (int x = 0; x < drawableEntities[0].length ; x ++) {
//                int xC = suparPiecOfShittConstant * x;

                DrawableEntity drawableEntity = drawableEntities[y][x];
                newLayerGCtx.drawImage(drawableEntity.getImage(), drawableEntity.getX() , drawableEntity.getY(), suparPiecOfShittConstant,suparPiecOfShittConstant);
            }
        }


        //Creating the mouse event handler
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {


                testLayerGc.drawImage(new Image(new File("assets/terrain/blood_fountain.png").toURI().toString()),i,0,32,32);
                i+=32;


            }
        };
        //Registering the event filter
        testLayer.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);





        stage.setScene(s);
        stage.setHeight(200);
        stage.setWidth(200);
        //stage.setResizable(false);
        stage.show();
    }
    //Game files processor proto

    public int zachemYaJivy(int pzdc)
    {
        return pzdc*suparPiecOfShittConstant;
    }

//    public Image imageProcessor(List<File> file){
//
//        return new Image(file.get(new Random().nextInt(file.size())).toURI().toString());
//    }
//
    public Image imageProcessor(int i){
        return new Image(files.get(i).toURI().toString());
    }

    public File fileProvider(DrawableEntity.Type type){
        switch (type) {
            case TERRAIN: {
                return files.get(0);
            }
            case ITEM: {
                return files.get(1);
            }
            default: {
                return null;
            }
        }
    }
}
