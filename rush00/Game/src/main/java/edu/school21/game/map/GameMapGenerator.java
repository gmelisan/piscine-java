package edu.school21.game.map;

import edu.school21.game.app.AppProperties;

import java.util.Arrays;
import java.util.Random;

public class GameMapGenerator {
    private final int enemiesCount;
    private final int wallsCount;
    private final int sizeMap;
    private final char enemyChar;
    private final char playerChar;
    private final char wallChar;
    private final char goalChar;
    private final char emptyChar;
    private final GameMap gameMap;

    public GameMapGenerator(int enemiesCount, int wallsCount, int sizeMap, AppProperties appProperties) {
        this.enemiesCount = enemiesCount;
        this.wallsCount = wallsCount;
        this.sizeMap = sizeMap;
        this.enemyChar = appProperties.getEnemy();
        this.playerChar = appProperties.getPlayer();
        this.wallChar = appProperties.getWall();
        this.goalChar = appProperties.getGoal();
        this.emptyChar = appProperties.getEmpty();
        this.gameMap = new GameMap(new char[sizeMap][sizeMap]);
    }

    public GameMap generate() {
        char[][] map = gameMap.getMap();

        fillMap(map, wallChar, wallsCount);

        fillMap(map, enemyChar, enemiesCount);

        fillMap(map, playerChar, 1);

        fillMap(map, goalChar, 1);

        fillEmpty(map);

        if (checkValidityMap(map)) {
            return gameMap;
        } else {
            clearMap(map);
            return generate();
        }
    }

    private Position getFreePosition(char[][] map) {
        Random random = new Random();
        Position position = new Position(random.nextInt(sizeMap), random.nextInt(sizeMap));

        while (map[position.getY()][position.getX()] != '\0') {
            position.setX(random.nextInt(sizeMap));
            position.setY(random.nextInt(sizeMap));
        }
        return position;
    }

    private void fillMap(char[][] map, char symbol, int count) {
        for (int i = 0; i < count; i++) {
            Position position = getFreePosition(map);
            map[position.getY()][position.getX()] = symbol;
            if (symbol == enemyChar) {
                gameMap.getEnemies().put(i, position);
            }
            if (symbol == playerChar) {
                gameMap.setPlayer(position);
            }
            if (symbol == goalChar) {
                gameMap.setGoal(position);
            }
        }
    }

    private void fillEmpty(char[][] map) {
        for (int y = 0; y < sizeMap; y++) {
            for (int x = 0; x < sizeMap; x++) {
                if (map[y][x] == 0) {
                    map[y][x] = emptyChar;
                }
            }
        }
    }

    private boolean checkValidityMap(char[][] map) {
        Position player = gameMap.getPlayer();
        int x = player.getX();
        int y = player.getY();

        boolean canGoLeft = x != 0 && map[y][x - 1] == emptyChar;
        boolean canGoRight = (x != sizeMap - 1) && map[y][x + 1] == emptyChar;
        boolean canGoUp = y != 0 && map[y - 1][x] == emptyChar;
        boolean canGoDown = (y != sizeMap - 1) && map[y + 1][x] == emptyChar;

        return canGoRight || canGoLeft || canGoUp || canGoDown;
    }

    private void clearMap(char[][] map) {
        for (int y = 0; y < sizeMap; y++) {
            for (int x = 0; x < sizeMap; x++) {
                map[y][x] = emptyChar;
            }
        }
    }
}
