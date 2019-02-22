package utils;

import lombok.Data;

import java.io.File;

@Data
public class AssetsTypeHolder {
    private final static String assetsPath = "assets/";

    private File[] terrain = new File(assetsPath+"terrain").listFiles();
    private File[] player = new File(assetsPath+"player").listFiles();

    static enum AssetType{
        TERRAIN,PLAYER
    }
}


