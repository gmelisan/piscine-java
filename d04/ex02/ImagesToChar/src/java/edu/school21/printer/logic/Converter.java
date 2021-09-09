package edu.school21.printer.logic;

import com.diogonunes.jcolor.*;
import com.sun.prism.paint.Color;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Converter {

    public Attribute[][] convert(String path, String white, String black, String def) {
        BufferedImage image;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResource(path)));
        } catch (IOException | NullPointerException e) {
            System.err.println("Error while reading from file: " + e.getMessage());
            e.printStackTrace();
            System.exit(-1);
            return null;
        }
        Attribute[][] map = new Attribute[image.getHeight()][image.getWidth()];
        for (int i = 0; i < map.length; ++i) {
            for (int j = 0; j < map[i].length; ++j) {
                int pixel = image.getRGB(j, i);
                if (pixel == Color.WHITE.getIntArgbPre())
                    map[i][j] = getColorAttribute(white);
                else if (pixel == Color.BLACK.getIntArgbPre())
                    map[i][j] = getColorAttribute(black);
                else
                    map[i][j] = getColorAttribute(def);
            }
        }
        return map;
    }

    private Attribute getColorAttribute(String name) {
        switch (name) {
            case "BLACK":
                return Attribute.BLACK_BACK();
            case "WHITE":
                return Attribute.WHITE_BACK();
            case "MAGENTA":
                return Attribute.MAGENTA_BACK();
            case "YELLOW":
                return Attribute.YELLOW_BACK();
            case "GRAY":
                return Attribute.BACK_COLOR(77, 77, 77);
            case "BLUE":
                return Attribute.BLUE_BACK();
            case "GREEN":
                return Attribute.GREEN_BACK();
            case "CYAN":
                return Attribute.CYAN_BACK();
            case "RED":
                return Attribute.RED_BACK();
            default:
                return Attribute.CLEAR();
        }
    }
}
