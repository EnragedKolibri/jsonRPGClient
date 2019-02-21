import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Main extends Application{




    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{

        int ultraShittedWidth = zachemYaJivy(50);
        int ultraShittedHeight = zachemYaJivy(20);
        int suparPiecOfShittConstant = 32;

        Pane root = new StackPane();
        root.setPrefSize(ultraShittedWidth,ultraShittedHeight);

        Scene s = new Scene(root, ultraShittedWidth, ultraShittedHeight, Color.BLACK);

        ArrayList<File> files = new ArrayList<>();
        files.add(new File("assets/1470105_3.png"));
        files.add(new File("assets/2473250_1.png"));

        Random random = new Random();

        Canvas canvas = new Canvas(ultraShittedWidth, ultraShittedHeight);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().addAll(canvas);

        //Layer filler algorithm proto
        int x = 0;
        int y = 0;
        int counter = 0;
        for (int i = 0; i != ultraShittedWidth; i++)
        {
            if (x == ultraShittedWidth)
            {
                y+=suparPiecOfShittConstant;
                x=0;
            }
            if (random.nextInt(10)>5)
            {
                counter ++;
            }else
            {
                if (counter>0)
                {
                    counter--;
                }

            }
            gc.drawImage(imageProcessor(files,counter), x , y,suparPiecOfShittConstant,suparPiecOfShittConstant);
            x+=suparPiecOfShittConstant;
        }
        stage.setScene(s);
        stage.show();
    }
    //Game files processor proto

    public int zachemYaJivy(int pzdc)
    {
        return pzdc*32;
    }

    public Image imageProcessor(ArrayList<File> file, int counter){

        if (counter == 1)
        {

            return new Image(file.get(1).toURI().toString());
        }
        return new Image(file.get(0).toURI().toString());
    }
}
