package edu.school21.printer.logic;

import com.sun.prism.paint.Color;
import com.sun.prism.paint.Paint;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Converter {

    public char[][] convert(String path, char whiteChar, char blackChar, char defaultChar) {
        BufferedImage image;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResource(path)));
        } catch (IOException | NullPointerException e) {
            System.err.println("Error while reading from file: " + e.getMessage());
            e.printStackTrace();
            System.exit(-1);
            return null;
        }
        char[][] map = new char[image.getHeight()][image.getWidth()];
        for (int i = 0; i < map.length; ++i) {
            for (int j = 0; j < map[i].length; ++j) {
                int pixel = image.getRGB(j, i);
                if (pixel == Color.WHITE.getIntArgbPre())
                    map[i][j] = whiteChar;
                else if (pixel == Color.BLACK.getIntArgbPre())
                    map[i][j] = blackChar;
                else
                    map[i][j] = defaultChar;
            }
        }
        return map;
    }
}
