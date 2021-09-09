package edu.school21.game.map;

import java.util.HashMap;
import java.util.Map;

public class GameMap {
    private final char[][] map;
    private final Map<Integer, Position> enemies;
    private Position player;
    private Position goal;

    public GameMap(char[][] map) {
        this.map = map;
        this.enemies = new HashMap<>();
    }

    public char[][] getMap() {
        return map;
    }

    public Map<Integer, Position> getEnemies() {
        return enemies;
    }

    public char getByPosition(Position position) {
        return map[position.getX()][position.getY()];
    }

    public Position getPlayer() {
        return player;
    }

    public void setPlayer(Position position) {
        this.player = position;
    }

    public Position getGoal() {
        return goal;
    }

    public void setGoal(Position position) {
        this.goal = position;
    }

    public boolean isFinish() {
        return player.equals(goal);
    }

    public boolean isCatchByEnemy(Position enemy) {
        return player.equals(enemy);
    }
}
