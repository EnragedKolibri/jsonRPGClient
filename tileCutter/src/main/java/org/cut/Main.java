package org.cut;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {




        tileSetsCutter("hyptosis_tile-art-batch-1");


    }

    static void tileSetsCutter(String imageName) throws IOException {
        File file = new File("assets\\" + imageName);
        file.mkdir();
        final BufferedImage source = ImageIO.read(new File("assets/" + imageName + ".png"));
        int idx = 0;
        int y = 0;
        int lastYPoint = source.getHeight() - 32;
        int lastXPoint = source.getWidth() - 32;
        for (int x = 0; x != source.getWidth(); x += 32) {
            if (x == lastXPoint) {
                ImageIO.write(source.getSubimage(x, y, 32, 32), "png", new File("assets/" + imageName + "/" + idx++ + ".png"));
                x = 0;
                y += 32;
            }
            if (y == lastYPoint&&x==lastXPoint)
            {
                ImageIO.write(source.getSubimage(x, y, 32, 32), "png", new File("assets/" + imageName + "/" + idx++ + ".png"));
                break;
            }
            ImageIO.write(source.getSubimage(x, y, 32, 32), "png", new File("assets/" + imageName + "/" + idx++ + ".png"));

        }
    }
}
