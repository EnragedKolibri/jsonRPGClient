package com.jsonRPGClientFX;

import com.jsonRPGClientFX.entities.DecorationEntity;
import com.jsonRPGClientFX.entities.DrawableEntity;
import com.jsonRPGClientFX.entities.TerrainEntity;
import com.jsonRPGClientFX.services.LayerService;
import javafx.animation.Animation;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;

public class Main extends Application{

    Image boom = new Image(new File("assets/QMqbQ.png").toURI().toString());
    Image tileset = new Image(new File("assets/hyptosis_tile-art-batch-1.png").toURI().toString());
    private int count = 7;
    private int colums = 6;
    private int offsetX = 0;
    private int offsetY = 0;
    private int width = 112;
    private int height = 148;
    // доделать выбор колонки и строки независимо


    static int suparPiecOfShittConstant = 32;
    static ArrayList<File> files = new ArrayList<>();
    private LayerService gameService = new LayerService();
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
                    {0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0},
                    {0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
                    {0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
                    {0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
                    {0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
                    {0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
                    {0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
                    {0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0}


            };

    private static DrawableEntity[][] drawableEntities;

    int i = 0;
    int j = 0;
    private int velociped = 5;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{
        ImageView testimageView = new ImageView(tileset);
        testimageView.setViewport(new Rectangle2D(0,0,32,32));


        ImageView imageView = new ImageView(boom);
        imageView.setViewport(new Rectangle2D(offsetX,offsetY,width,height));

        SpriteAnimation spriteAnimation = new SpriteAnimation(imageView, Duration.millis(700),count,colums,offsetX,offsetY,width,height);
        spriteAnimation.setCycleCount(Animation.INDEFINITE);
        spriteAnimation.play();


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
        root.getChildren().add(imageView);

        TerrainEntity.TerrainType groundType = TerrainEntity.TerrainType.GROUND;
        TerrainEntity.TerrainType voidType = TerrainEntity.TerrainType.VOID;


        drawableEntities = new DrawableEntity[mapa[0].length][mapa.length];
        for (int y = 0; y <  mapa.length; y++) {
            for (int x = 0; x < mapa[0].length ; x ++) {
                double yC = suparPiecOfShittConstant * y;
                double xC = suparPiecOfShittConstant * x;
                switch (mapa[y][x]) {
                    case 0: {
                        TerrainEntity terrainEntity = new TerrainEntity("name"+y+x, voidType, xC, yC);;
                        terrainEntity.setFile(files.get(0));
                        drawableEntities[x][y] = terrainEntity;
                        break;
                    }
                    case 1: {
                        DecorationEntity decorationEntity = new DecorationEntity("name"+y+x, xC, yC);
                        decorationEntity.setFile(files.get(1));
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




        reRender(gameService.getRegisteredGraphicContext(mainLayer));
        Image testMoving = new Image(new File("assets/terrain/blood_fountain.png").toURI().toString());

        //Creating the mouse event handler
        EventHandler<MouseEvent> eventHandler = e -> {
            System.out.println("Mouse Event handled");
//            gameService.getRegisteredGraphicContext(newLayer).clearRect(i,0,32,32);
            gameService.getRegisteredGraphicContext(newLayer).drawImage(testMoving,i,j,32,32);
            i+=32;
        };
        //Registering the event filter


        EventHandler<KeyEvent> keyEventEventHandler = e -> {



            reRender(gameService.getRegisteredGraphicContext(mainLayer));
            System.out.println("Key pressed "+e.getCode());
            gameService.getRegisteredGraphicContext(newLayer).clearRect(i,j,33,33);
           switch (e.getCode().toString()) {
               case "W":
                   System.out.println("W");
                   j -= velociped;
                   gameService.getRegisteredGraphicContext(newLayer).drawImage(testMoving,i,j,32,32);
                   break;
               case "A":
                   System.out.println("A");
                   i -= velociped;
                   gameService.getRegisteredGraphicContext(newLayer).drawImage(testMoving,i,j,32,32);
                   break;
               case "S":
                   System.out.println("S");
                   j += velociped;
                   gameService.getRegisteredGraphicContext(newLayer).drawImage(testMoving,i,j,32,32);
                   break;
               case "D":
                   System.out.println("D");
                   i += velociped;
                   gameService.getRegisteredGraphicContext(newLayer).drawImage(testMoving, i,j,32,32);
                   break;
           }

        };

        s.addEventFilter(KeyEvent.KEY_PRESSED, keyEventEventHandler);
        gameService.getRegisteredCanvas(testLayer).addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);







        stage.setScene(s);
        stage.setHeight(200);
        stage.setWidth(200);
        //stage.setResizable(false);
        stage.show();
    }
    //Game files processor proto

    public void reRender(GraphicsContext graphicsContext)
    {
        for (int y = 0; y <  drawableEntities.length; y++) {
//            int yC = suparPiecOfShittConstant * y;

//            System.out.println("y " + yC);
            for (int x = 0; x < drawableEntities[0].length ; x ++) {
//                int xC = suparPiecOfShittConstant * x;

                DrawableEntity drawableEntity = drawableEntities[y][x];
                graphicsContext.drawImage(drawableEntity.getImage(), drawableEntity.getX() , drawableEntity.getY(), suparPiecOfShittConstant,suparPiecOfShittConstant);
            }
        }
    }
}
