package edu.school21.game.engine;

import edu.school21.game.app.AppProperties;
import edu.school21.game.map.GameMap;
import edu.school21.game.map.Position;

public class Movement {
    private final AppProperties appProperties;
    private final GameMap gameMap;

    public Movement(GameMap gameMap, AppProperties appProperties) {
        this.gameMap = gameMap;
        this.appProperties = appProperties;
    }

    public boolean handleAction(Action action) {
        Position player = gameMap.getPlayer();

        int x = player.getX();
        int y = player.getY();

        switch (action) {
            case LEFT:
                return goToDirection(player, x - 1, y, appProperties.getPlayer());
            case RIGHT:
                return goToDirection(player, x + 1, y, appProperties.getPlayer());
            case UPWARD:
                return goToDirection(player, x, y - 1, appProperties.getPlayer());
            case DOWNWARD:
                return goToDirection(player, x, y + 1, appProperties.getPlayer());
            default:
                System.out.println("U give up(");
                System.exit(0);
                break;
        }

        return false;
    }

    public boolean isMovable(int x, int y) {
        boolean canGoLeft = canGoToDirection(x - 1, y);
        boolean canGoRight = canGoToDirection(x + 1, y);
        boolean canGoToUp = canGoToDirection(x, y - 1);
        boolean canGoDown = canGoToDirection(x, y + 1);

        return canGoToUp || canGoDown || canGoLeft || canGoRight;
    }

    private boolean canGoToDirection(int x, int y) {
        boolean isEdgeOfMap =
                x >= 0 && x < gameMap.getMap().length &&
                        y >= 0 && y < gameMap.getMap().length;

        return isEdgeOfMap &&
                (gameMap.getMap()[y][x] == appProperties.getEmpty() ||
                        gameMap.getMap()[y][x] == appProperties.getGoal());
    }

    public boolean goToDirection(Position player, int x, int y, char symbol) {
        if (!canGoToDirection(x, y) && symbol == appProperties.getPlayer()) {
            cantGoMsg();
            return false;
        }

        char[][] map = gameMap.getMap();

        map[player.getY()][player.getX()] = appProperties.getEmpty();

        map[y][x] = symbol;

        if (symbol == appProperties.getPlayer()) {
            gameMap.getPlayer().setY(y);
            gameMap.getPlayer().setX(x);
        }

        return true;
    }

    private void cantGoMsg() {
        System.out.println("You can't go there!");
    }
}
