package com.jsonRPGClientFX;

import com.jsonRPGClientFX.entities.DecorationEntity;
import com.jsonRPGClientFX.entities.DrawableEntity;
import com.jsonRPGClientFX.entities.mapEntities.Map;
import com.jsonRPGClientFX.entities.TerrainEntity;
import com.jsonRPGClientFX.entities.mapEntities.MapLayer;
import com.jsonRPGClientFX.services.CanvasLayeringService;
import com.jsonRPGClientFX.services.MapService;
import com.jsonRPGClientFX.utils.Constants;
import com.jsonRPGClientFX.utils.UtilsLogger;
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
import java.util.Arrays;

public class Main extends Application {

    private Image boom = new Image(new File("assets/QMqbQ.png").toURI().toString());
    private Group root;

    private static ArrayList<File> files = new ArrayList<>();
    static {
        files.add(new File("assets/terrain/1470105_3.png"));
        files.add(new File("assets/terrain/2473250_1.png"));
    }

    private void drawImage(GraphicsContext gc, Image image, double angle, double x, double y, double width, double height) {
        Rotate r = new Rotate(angle, x + image.getWidth() / 2, y + image.getHeight() / 2);
        gc.save(); // saves the current state on stack, including the current transform
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
        gc.drawImage(image, x, y, width, height);
        gc.restore(); // back to original state (before rotation)
    }




    private int i = 0;
    private int j = 0;
    private int velociped = 5;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        String mainLayer = "mainLayer";
        String newLayer = "newLayer";

        int width = Constants.testMap[0].length * Constants.TILE_SIZE; //Нужно пересчитывать
        int height = Constants.testMap.length * Constants.TILE_SIZE;  //
        root = new Group();
        Scene scene = new Scene(root, width, height, Color.BLACK);
        //for each layer in testMap create layer through layer service and add
        CanvasLayeringService canvasLayeringService = new CanvasLayeringService();

        MapLayer woodLayer = new MapLayer(Constants.woodLayer);
        Map map = new Map();
        MapService mapService = new MapService();

        woodLayer.setDebugMode(true);
        map.addLayer("mainLayer",woodLayer);
        mapService.addMapToList(map);
        mapService.getMap(1).getRegisteredLayerNames().forEach(s -> canvasLayeringService.registerNewGraphicContext(s,width,height));

        canvasLayeringService.registerNewGraphicContext(newLayer, width, height);
        canvasLayeringService.getAllRegisteredCanvas().forEach(canvas -> root.getChildren().add(canvas));

        //Решить вопрос с просисовкой карты и автоматическим взаимодействием сервисов
        //Решить вопрос с ЖСОНОМ посмотреть как выглядят мои обьекты сейчас
        //


        int[][] testMap = mapService.getMap(1).getLayer(mainLayer).getLayerMatrix();

        UtilsLogger.log("testMap" + Arrays.deepToString(testMap));
       DrawableEntity[][] drawableEntities = new DrawableEntity[testMap[0].length][testMap.length];
        for (int y = 0; y < testMap.length; y++) {
                double yC = Constants.TILE_SIZE * y;
            for (int x = 0; x < testMap[0].length; x++) {
                double xC = Constants.TILE_SIZE * x;
                switch (testMap[y][x]) {
                    case 0: {
                        TerrainEntity terrainEntity = new TerrainEntity("name" + y + x, xC, yC);
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

        mapRender(canvasLayeringService.getRegisteredGraphicContext(mainLayer), drawableEntities);

        //Creating the mouse event handler
        EventHandler<MouseEvent> eventHandler = e -> {
            float animationWidth = 112;
            float animationHeight = 148;
            ImageView explosionImageView = new ImageView(boom);
            explosionImageView.setViewport(new Rectangle2D(0, 0, 112, 148));
            SpriteAnimation spriteAnimation = new SpriteAnimation(explosionImageView, Duration.millis(500), 6, 6, 0, 0, 112, 148) ;
            spriteAnimation.setCycleCount(1);
            root.getChildren().add(explosionImageView);
            explosionImageView.setX(e.getX()-112/2);
            explosionImageView.setY(e.getY()-148+20);
            spriteAnimation.play();
            spriteAnimation.setOnFinished(t -> root.getChildren().remove(explosionImageView));
            UtilsLogger.log(root.getChildren().toString());
        };

        //Registering the event filter
        //добавить слежение за мышью
        Image testMoving = new Image(new File("assets/terrain/blood_fountain.png").toURI().toString());
        EventHandler<KeyEvent> keyEventEventHandler = e -> {
            UtilsLogger.log("Key pressed " + e.getCode());
            clearArea(canvasLayeringService.getRegisteredGraphicContext(newLayer), i, j, 32, 32);
            switch (e.getCode().toString()) {
                case "W":
                    UtilsLogger.log("W");
                    j -= velociped;
                    drawImage(canvasLayeringService.getRegisteredGraphicContext(newLayer), testMoving, 0, i, j, 32, 32);
                    //canvasLayeringService.getRegisteredGraphicContext(newLayer).drawImage(testMoving,i,j,32,32);

                    break;
                case "A":
                    UtilsLogger.log("A");
                    i -= velociped;
                    drawImage(canvasLayeringService.getRegisteredGraphicContext(newLayer), testMoving, 270, i, j, 32, 32);
                    //canvasLayeringService.getRegisteredGraphicContext(newLayer).drawImage(testMoving,i,j,32,32);
                    break;
                case "S":
                    UtilsLogger.log("S");
                    j += velociped;
                    drawImage(canvasLayeringService.getRegisteredGraphicContext(newLayer), testMoving, 180, i, j, 32, 32);
                    //canvasLayeringService.getRegisteredGraphicContext(newLayer).drawImage(testMoving,i,j,32,32);
                    break;
                case "D":
                    UtilsLogger.log("D");
                    i += velociped;
                    drawImage(canvasLayeringService.getRegisteredGraphicContext(newLayer), testMoving, 90, i, j, 32, 32);
                    //canvasLayeringService.getRegisteredGraphicContext(newLayer).drawImage(testMoving, i,j,32,32);
                    break;
            }

        };

        scene.addEventHandler(KeyEvent.KEY_PRESSED, keyEventEventHandler);
        scene.addEventHandler(MouseEvent.MOUSE_CLICKED,eventHandler);

        stage.setTitle("Vision");
        stage.setScene(scene);
        stage.setHeight(1000);
        stage.setWidth(1000);
        stage.show();

        UtilsLogger.log(root.getClass().getName()+root.getChildren().toString());


    }
    //Game files processor proto

    private void clearArea(GraphicsContext gc, double x, double y, double width, double height) {
        gc.clearRect(x, y, width, height);
    }

    private void mapRender(GraphicsContext gc, DrawableEntity[][] drawableEntities) {
        for (int y = 0; y < drawableEntities.length; y++) {
//            int yC = suparPiecOfShittConstant * y;

//            System.out.println("y " + yC);
            for (int x = 0; x < drawableEntities[0].length; x++) {
//                int xC = suparPiecOfShittConstant * x;

                DrawableEntity drawableEntity = drawableEntities[y][x];
                drawImage(gc,drawableEntity.getImage(),0,drawableEntity.getX(),drawableEntity.getY(),Constants.TILE_SIZE,Constants.TILE_SIZE);
            }
        }
    }


    //не забывать что анимация играет в image view который нужно добавлять в рут(группу для отображения в окне) а она пока существует только в методе окна
    //нужно вынести группу в лист в отдельном класе (но в группе и так все узлы в листе)
    //нужно хранить анимации в отдельном листе (но лучше привязывать к отдельным обьектам)(подумать о фабрике обьектов)(сложные обьекты с анимацией через нее , а не сложные тайлы отдельно
    // или все через нее хз)
    //Последовательная загрузка изображений в память ... как оптимизация
    // коллизия: радиус попарных точек (есть вероятность что их прийдется строить дохера и столько же считать)



}
