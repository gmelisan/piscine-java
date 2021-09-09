package edu.school21.printer.app;

import edu.school21.printer.logic.*;

import java.util.List;

public class Program {

    private static final char defaultChar = ' ';
    private static char whiteChar = '.';
    private static char blackChar = 'O';
    private static String path = "";

    public static void main(String[] args) {
        parseArgs(args);
        Converter converter = new Converter();
        char[][] list = converter.convert(path, whiteChar, blackChar, defaultChar);
        for (char[] row : list) {
            for (char c : row)
                System.out.print(c);
            System.out.println();
        }
    }

    private static void parseArgs(String[] args) {
        if (args.length == 0) {
            System.err.println("Need path to *.bmp");
            System.exit(-1);
        }
        path = args[0];
        try {
            whiteChar = args[1].toCharArray()[0];
            blackChar = args[2].toCharArray()[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Cannot detect white/black char from parameters");
            System.out.printf("Using '%c' for white, '%c' for black\n", whiteChar, blackChar);
        }
    }
}
