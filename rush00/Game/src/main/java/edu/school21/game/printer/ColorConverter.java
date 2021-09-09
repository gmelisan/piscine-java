package edu.school21.game.printer;

import com.diogonunes.jcdp.color.api.Ansi;

public class ColorConverter {

    public static Ansi.FColor getFColor(String color) {
        if (color == null) {
            return Ansi.FColor.NONE;
        }

        switch (color) {
            case "BLACK":
                return Ansi.FColor.BLACK;
            case "RED":
                return Ansi.FColor.RED;
            case "GREEN":
                return Ansi.FColor.GREEN;
            case "YELLOW":
                return Ansi.FColor.YELLOW;
            case "BLUE":
                return Ansi.FColor.BLUE;
            case "MAGENTA":
                return Ansi.FColor.MAGENTA;
            case "CYAN":
                return Ansi.FColor.CYAN;
            case "WHITE":
                return Ansi.FColor.WHITE;
            default:
                return Ansi.FColor.NONE;
        }
    }

    public static Ansi.BColor getBColor(String color) {
        if (color == null) {
            return Ansi.BColor.NONE;
        }

        switch (color) {
            case "BLACK":
                return Ansi.BColor.BLACK;
            case "RED":
                return Ansi.BColor.RED;
            case "GREEN":
                return Ansi.BColor.GREEN;
            case "YELLOW":
                return Ansi.BColor.YELLOW;
            case "BLUE":
                return Ansi.BColor.BLUE;
            case "MAGENTA":
                return Ansi.BColor.MAGENTA;
            case "CYAN":
                return Ansi.BColor.CYAN;
            case "WHITE":
                return Ansi.BColor.WHITE;
            default:
                return Ansi.BColor.NONE;
        }
    }
}
