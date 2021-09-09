package edu.school21.game.printer;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;
import edu.school21.game.app.AppProperties;

import java.util.HashMap;
import java.util.Map;

public class Printer {
    private final AppProperties appProperties;
    private final Map<Character, Ansi.BColor> characterBColorMap;
    private char[][] map;

    public Printer(AppProperties appProperties, char[][] map) {
        this.appProperties = appProperties;
        this.map = map;
        this.characterBColorMap = fillColorMap();
    }

    private Map<Character, Ansi.BColor> fillColorMap() {
        Map<Character, Ansi.BColor> characterBColorMap = new HashMap<>();

        characterBColorMap.put(appProperties.getEnemy(), ColorConverter.getBColor(appProperties.getEnemyColor()));
        characterBColorMap.put(appProperties.getPlayer(), ColorConverter.getBColor(appProperties.getPlayerColor()));
        characterBColorMap.put(appProperties.getWall(), ColorConverter.getBColor(appProperties.getWallColor()));
        characterBColorMap.put(appProperties.getGoal(), ColorConverter.getBColor(appProperties.getGoalColor()));
        characterBColorMap.put(appProperties.getEmpty(), ColorConverter.getBColor(appProperties.getEmptyColor()));

        return characterBColorMap;
    }

    public void setMap(char[][] map) {
        this.map = map;
    }

    public void printMap(String profile) {

        if (!profile.equals("dev")) {
            System.out.print("\033[H\033[2J");
        }

        ColoredPrinter coloredPrinter = new ColoredPrinter.Builder(1, false).build();

        for (char[] row : map) {
            for (char el : row) {
                printChar(coloredPrinter, el);
            }
            System.out.println();
        }
    }

    private void printChar(ColoredPrinter cp, char aChar) {
        Ansi.BColor color = characterBColorMap.get(aChar);
        String out = " " + aChar + " ";
        cp.print(out, Ansi.Attribute.NONE, Ansi.FColor.BLACK, color);
    }
}
