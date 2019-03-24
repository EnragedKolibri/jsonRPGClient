package com.jsonRPGClientFX;

import com.jsonRPGClientFX.entities.DecorationEntity;
import com.jsonRPGClientFX.entities.DrawableEntity;
import com.jsonRPGClientFX.entities.TerrainEntity;
import com.jsonRPGClientFX.services.LayerService;
import com.jsonRPGClientFX.utils.Constants;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Main extends Application {
    Image boom = new Image(new File("assets/QMqbQ.png").toURI().toString());
    int count = 7;
    int colums = 6;
    int offsetX = 0;
    int offsetY = 0;
    int animationWidth = 112;
    int animationHeight = 148;
    ImageView explosionImageView = new ImageView(boom);


    // доделать выбор колонки и строки независимо
    
    private static ArrayList<File> files = new ArrayList<>();
    private LayerService layerService = new LayerService();
    Random random = new Random();
    // Map<String, Layer> map;
    // MapEntity mapEntity = new MapEntity("testMapa",map);


    static {
        files.add(new File("assets/terrain/1470105_3.png"));
        files.add(new File("assets/terrain/2473250_1.png"));
    }

    //draw image rotate on 90 180 270 deg некоторые тайлы нужно поворачивать на разный угол (ровный)
    // сделать масив флоат и через точку указывать угол поворота
    // *.0 - * это ид картинки 0 это угол поворота , а значит читаем картинку как есть
    // *.9 - * это ид картинки 9 это угол поворота , а значит читаем картинку и поварачиваем на 90 градусов при отрисовке.
    // *.18 - * это ид картинки 18 это угол поворота , а значит читаем картинку и поварачиваем на 90 градусов при отрисовке
    // *.27 - * это ид картинки 270 это угол поворота , а значит читаем картинку и поварачиваем на 90 градусов при отрисовке
    // доступны будут только эти углы поворота и в случае если угол не равен вышеперечисленным отдаем эррор с координатами(длинна конкретного масива[0-х], длнна масива масивов) где в масиве ошибка
    private static int[][] mapa =
            {
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}


            };

    private static int[][] collisionMapa =
            {
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}


            };

    private void drawRotatedImage(GraphicsContext gc, Image image, double angle, double x, double y, double width, double height) {
        Rotate r = new Rotate(angle, x + image.getWidth() / 2, y + image.getHeight() / 2);
        gc.save(); // saves the current state on stack, including the current transform
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
        gc.drawImage(image, x, y, width, height);
        gc.restore(); // back to original state (before rotation)
    }


    private static DrawableEntity[][] drawableEntities;

    private int i = 0;
    private int j = 0;
    private int velociped = 5;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();

        int ultraShittedWidth = mapa[0].length * Constants.TILE_SIZE;
        int ultraShittedHeight = mapa.length * Constants.TILE_SIZE;
//        int ultraShittedHeight = zachemYaJivy(20);
        explosionImageView.setViewport(new Rectangle2D(offsetX, offsetY, animationWidth, animationHeight));
        SpriteAnimation spriteAnimation = new SpriteAnimation(explosionImageView, Duration.millis(700), count, colums, offsetX, offsetY, animationWidth, animationHeight);
        spriteAnimation.setCycleCount(1);



        //root.setPrefSize(ultraShittedWidth, ultraShittedHeight);

        Scene s = new Scene(root, ultraShittedWidth, ultraShittedHeight, Color.BLACK);

        String mainLayer = "mainLayer";
        String testLayer = "testLayer";
        String newLayer = "newLayer";
        layerService.registerNewGraphicContext(mainLayer, ultraShittedWidth, ultraShittedHeight);
        layerService.registerNewGraphicContext(newLayer, ultraShittedWidth, ultraShittedHeight);
        layerService.registerNewGraphicContext(testLayer, 200, 200);

        layerService.getAllRegisteredCanvas().forEach(canvas -> root.getChildren().add(canvas));
        root.getChildren().add(explosionImageView);
        explosionImageView.setX(100);

        //TerrainEntity.TerrainType groundType = TerrainEntity.TerrainType.GROUND;
        TerrainEntity.TerrainType voidType = TerrainEntity.TerrainType.VOID;


        drawableEntities = new DrawableEntity[mapa[0].length][mapa.length];
        for (int y = 0; y < mapa.length; y++) {
            for (int x = 0; x < mapa[0].length; x++) {
                double yC = Constants.TILE_SIZE * y;
                double xC = Constants.TILE_SIZE * x;
                switch (mapa[y][x]) {
                    case 0: {
                        TerrainEntity terrainEntity = new TerrainEntity("name" + y + x, voidType, xC, yC);
                        terrainEntity.setFile(files.get(0));
                        drawableEntities[x][y] = terrainEntity;
                        break;
                    }
                    case 1: {
                        DecorationEntity decorationEntity = new DecorationEntity("name" + y + x, xC, yC);
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


        mapRender(layerService.getRegisteredGraphicContext(mainLayer), drawableEntities);
        Image testMoving = new Image(new File("assets/terrain/blood_fountain.png").toURI().toString());

        //Creating the mouse event handler
        EventHandler<MouseEvent> eventHandler = e -> {
            log("Mouse Event handled");
            //w112
            //h148
            explosionImageView.setX(e.getX()-animationWidth/2);
            explosionImageView.setY(e.getY()-animationHeight+20);
            spriteAnimation.play();
//            layerService.getRegisteredGraphicContext(newLayer).clearRect(i,0,32,32);
            //drawRotatedImage(layerService.getRegisteredGraphicContext(newLayer),testMoving,90,10,10);
            //layerService.getRegisteredGraphicContext(newLayer).drawImage(testMoving,i,j,32,32);
            //i+=32;
        };
        //Registering the event filter


        EventHandler<KeyEvent> keyEventEventHandler = e -> {
            log("Key pressed " + e.getCode());
            clearArea(layerService.getRegisteredGraphicContext(newLayer), i, j, 32, 32);
            switch (e.getCode().toString()) {
                case "W":
                    log("W");
                    j -= velociped;
                    drawRotatedImage(layerService.getRegisteredGraphicContext(newLayer), testMoving, 0, i, j, 32, 32);
                    //layerService.getRegisteredGraphicContext(newLayer).drawImage(testMoving,i,j,32,32);

                    break;
                case "A":
                    log("A");
                    i -= velociped;
                    drawRotatedImage(layerService.getRegisteredGraphicContext(newLayer), testMoving, 270, i, j, 32, 32);
                    //layerService.getRegisteredGraphicContext(newLayer).drawImage(testMoving,i,j,32,32);
                    break;
                case "S":
                    log("S");
                    j += velociped;
                    drawRotatedImage(layerService.getRegisteredGraphicContext(newLayer), testMoving, 180, i, j, 32, 32);
                    //layerService.getRegisteredGraphicContext(newLayer).drawImage(testMoving,i,j,32,32);
                    break;
                case "D":
                    log("D");
                    i += velociped;
                    drawRotatedImage(layerService.getRegisteredGraphicContext(newLayer), testMoving, 90, i, j, 32, 32);
                    //layerService.getRegisteredGraphicContext(newLayer).drawImage(testMoving, i,j,32,32);
                    break;
            }

        };

        s.addEventHandler(KeyEvent.KEY_PRESSED, keyEventEventHandler);
        s.addEventHandler(MouseEvent.MOUSE_CLICKED,eventHandler);
        layerService.getRegisteredCanvas(testLayer).addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);


        stage.setTitle("Vision");
        stage.setScene(s);
        stage.setHeight(200);
        stage.setWidth(200);
        //stage.setResizable(false);
        stage.show();
    }
    //Game files processor proto

    private void clearArea(GraphicsContext gc, double x, double y, double width, double height) {
        gc.clearRect(x, y, width, height);
    }

    private void log(String str) {
        System.out.println("[LOG-INFO]: " + str);
    }

    public void mapRender(GraphicsContext gc, DrawableEntity[][] drawableEntities) {
        for (int y = 0; y < drawableEntities.length; y++) {
//            int yC = suparPiecOfShittConstant * y;

//            System.out.println("y " + yC);
            for (int x = 0; x < drawableEntities[0].length; x++) {
//                int xC = suparPiecOfShittConstant * x;

                DrawableEntity drawableEntity = drawableEntities[y][x];
                gc.drawImage(drawableEntity.getImage(), drawableEntity.getX(), drawableEntity.getY(), Constants.TILE_SIZE, Constants.TILE_SIZE);
            }
        }
    }

    //не забывать что анимация играет в image view который нужно добавлять в рут(группу для отображения в окне) а она пока существует только в методе окна
    //нужно вынести группу в лист в отдельном класе (но в группе и так все узлы в листе)
    //нужно хранить анимации в отдельном листе (но лучше привязывать к отдельным обьектам)(подумать о фабрике обьектов)(сложные обьекты с анимацией через нее , а не сложные тайлы отдельно
    // или все через нее хз)



}
