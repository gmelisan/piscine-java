package edu.school21.game.app;

import com.beust.jcommander.JCommander;
import edu.school21.game.engine.GameEngine;
import edu.school21.game.exception.IllegalParameterException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Program {

    public static void main(String[] args) throws IOException {
        ArgsProperties argsProperties = new ArgsProperties();

        JCommander.newBuilder()
                .addObject(argsProperties)
                .build()
                .parse(args);

        checkGameCondition(argsProperties);

        AppProperties appProperties = new AppProperties();

        checkApplicationProperties(argsProperties, appProperties);

        GameEngine gameEngine = new GameEngine(argsProperties, appProperties);

        gameEngine.launch();
    }

    private static void checkGameCondition(ArgsProperties argsProperties) {
        Integer enemiesCount = argsProperties.getEnemiesCount();
        Integer wallsCount = argsProperties.getWallsCount();
        Integer sizeMap = argsProperties.getSizeMap();

        if (enemiesCount + wallsCount + 2 >= sizeMap * sizeMap) {
            throw new IllegalParameterException("Incorrect parameters for create game");
        }
    }

    private static void checkApplicationProperties(ArgsProperties argsProperties, AppProperties appProperties) throws IOException {
        String profile = argsProperties.getProfile();

        InputStream inputStream = Program.class.getClassLoader().getResourceAsStream("application-" + profile + ".properties");

        if (inputStream == null) {
            throw new IllegalParameterException("Not found property file for this profile");
        }

        Properties properties = new Properties();

        properties.load(inputStream);

        inputStream.close();

        appProperties.setEnemy(parseChar(properties.getProperty(AppProperties.ENEMY_CHAR)));
        appProperties.setPlayer(parseChar(properties.getProperty(AppProperties.PLAYER_CHAR)));
        appProperties.setWall(parseChar(properties.getProperty(AppProperties.WALL_CHAR)));
        appProperties.setGoal(parseChar(properties.getProperty(AppProperties.GOAL_CHAR)));

        String emptyChar = properties.getProperty(AppProperties.EMPTY_CHAR);

        if (!emptyChar.isEmpty()) {
            appProperties.setEmpty(parseChar(emptyChar));
        }

        appProperties.setEnemyColor(properties.getProperty(AppProperties.ENEMY_COLOR));
        appProperties.setPlayerColor(properties.getProperty(AppProperties.PLAYER_COLOR));
        appProperties.setWallColor(properties.getProperty(AppProperties.WALL_COLOR));
        appProperties.setGoalColor(properties.getProperty(AppProperties.GOAL_COLOR));
        appProperties.setEmptyColor(properties.getProperty(AppProperties.EMPTY_COLOR));

        if (appProperties.isCheckNull()) {
            throw new IllegalParameterException("Incorrect properties");
        }
    }

    private static Character parseChar(String str) {
        if (isChar(str)) {
            return str.charAt(0);
        } else {
            return null;
        }
    }

    private static boolean isChar(String string) {
        return string != null && string.length() == 1;
    }
}
