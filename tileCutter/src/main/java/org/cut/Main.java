package org.cut;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {




        tileSetsCutter("hyptosis_tile-art-batch-1",32);


    }

    static void tileSetsCutter(String imageName, int step) throws IOException {
        File file = new File("assets\\" + imageName);
        file.mkdir();
        final BufferedImage source = ImageIO.read(new File("assets/" + imageName + ".png"));
        int idx = 0;
        int y = 0;
        int lastYPoint = source.getHeight() - step;
        int lastXPoint = source.getWidth() - step;
        for (int x = 0; x != source.getWidth(); x += step) {
            if (x == lastXPoint) {
                ImageIO.write(source.getSubimage(x, y, step, step), "png", new File("assets/" + imageName + "/" + idx++ + ".png"));
                x = 0;
                y += step;
            }
            if (y == lastYPoint&&x==lastXPoint)
            {
                ImageIO.write(source.getSubimage(x, y, step, step), "png", new File("assets/" + imageName + "/" + idx++ + ".png"));
                break;
            }
            ImageIO.write(source.getSubimage(x, y, step, step), "png", new File("assets/" + imageName + "/" + idx++ + ".png"));

        }
    }
}
