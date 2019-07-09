package com.jsonRPGClientFX.utils;

import java.io.IOException;
import java.util.function.Consumer;

public class UtilsLogger {

    private enum Level
    {
        ALL,MAP_SER,FILE_SERV,CANV_LAYERING_SERV,
    }

    public UtilsLogger (Level level)
    {

      /*  Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("path/to/other/view.fxml"), resources);
            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root, 450, 450));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }*/

    }


    public static void log( String logMessage)
    {
        String msg = "[INFO]: ";
        System.out.println( msg+logMessage);
    }
}
