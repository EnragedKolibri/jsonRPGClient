package com.jsonRPGClientFX;

import com.jsonRPGClientFX.entities.DecorationEntity;
import com.jsonRPGClientFX.entities.DrawableEntity;
import com.jsonRPGClientFX.entities.TerrainEntity;
import com.jsonRPGClientFX.services.GameService;
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
    private GameService gameService = new GameService();
   // Map<String, Layer> map;
   // MapEntity mapEntity = new MapEntity("testMapa",map);


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

        String mainLayer = "mainLayer";
        String testLayer = "testLayer";
        String newLayer = "newLayer";
        gameService.registerNewGraphicContext(mainLayer,ultraShittedWidth,ultraShittedHeight);
        gameService.registerNewGraphicContext(newLayer,ultraShittedWidth,ultraShittedHeight);
        gameService.registerNewGraphicContext(testLayer,200,200);
//        root.getChildren().addAll(gameService.getAllRegisteredCanvas());
        gameService.getAllRegisteredCanvas().forEach(canvas -> root.getChildren().add(canvas));

        TerrainEntity.TerrainType terrainType = TerrainEntity.TerrainType.GROUND;


        drawableEntities = new DrawableEntity[mapa[0].length][mapa.length];
        for (int y = 0; y <  mapa.length; y++) {
            for (int x = 0; x < mapa[0].length ; x ++) {
                double yC = suparPiecOfShittConstant * y;
                double xC = suparPiecOfShittConstant * x;
                switch (mapa[y][x]) {
                    case 0: {
                        TerrainEntity terrainEntity = new TerrainEntity("name"+y+x, terrainType, xC, yC);;
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
                gameService.getRegisteredGraphicContext(newLayer).drawImage(drawableEntity.getImage(), drawableEntity.getX() , drawableEntity.getY(), suparPiecOfShittConstant,suparPiecOfShittConstant);
            }
        }


        //Creating the mouse event handler
        EventHandler<MouseEvent> eventHandler = e -> {
            System.out.println("Event handeled");
//            gameService.getRegisteredGraphicContext(newLayer).clearRect(i,0,32,32);
            gameService.getRegisteredGraphicContext(newLayer).drawImage(new Image(new File("assets/terrain/blood_fountain.png").toURI().toString()),i,0,32,32);
            i+=32;
        };
        //Registering the event filter
        gameService.getRegisteredCanvas(testLayer).addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);







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
