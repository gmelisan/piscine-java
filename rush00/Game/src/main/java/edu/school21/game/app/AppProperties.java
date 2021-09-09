package edu.school21.game.app;

import java.util.ArrayList;

public class AppProperties {
    public static final String ENEMY_CHAR = "enemy.char";
    public static final String PLAYER_CHAR = "player.char";
    public static final String WALL_CHAR = "wall.char";
    public static final String GOAL_CHAR = "goal.char";
    public static final String EMPTY_CHAR = "empty.char";
    public static final String ENEMY_COLOR = "enemy.color";
    public static final String PLAYER_COLOR = "player.color";
    public static final String WALL_COLOR = "wall.color";
    public static final String GOAL_COLOR = "goal.color";
    public static final String EMPTY_COLOR = "empty.color";

    private Character enemy;
    private Character player;
    private Character wall;
    private Character goal;
    private Character empty;
    private String enemyColor;
    private String playerColor;
    private String wallColor;
    private String goalColor;
    private String emptyColor;

    public AppProperties() {
        this.empty = ' ';
    }

    public Character getEnemy() {
        return enemy;
    }

    public void setEnemy(Character enemy) {
        this.enemy = enemy;
    }

    public Character getPlayer() {
        return player;
    }

    public void setPlayer(Character player) {
        this.player = player;
    }

    public Character getWall() {
        return wall;
    }

    public void setWall(Character wall) {
        this.wall = wall;
    }

    public Character getGoal() {
        return goal;
    }

    public void setGoal(Character goal) {
        this.goal = goal;
    }

    public Character getEmpty() {
        return empty;
    }

    public void setEmpty(Character empty) {
        this.empty = empty;
    }

    public String getEnemyColor() {
        return enemyColor;
    }

    public void setEnemyColor(String enemyColor) {
        this.enemyColor = enemyColor;
    }

    public String getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(String playerColor) {
        this.playerColor = playerColor;
    }

    public String getWallColor() {
        return wallColor;
    }

    public void setWallColor(String wallColor) {
        this.wallColor = wallColor;
    }

    public String getGoalColor() {
        return goalColor;
    }

    public void setGoalColor(String goalColor) {
        this.goalColor = goalColor;
    }

    public String getEmptyColor() {
        return emptyColor;
    }

    public void setEmptyColor(String emptyColor) {
        this.emptyColor = emptyColor;
    }

    private boolean isAnyPropertyNull(Object... properties) {
        for (Object property : properties) {
            if (property == null) {
                return true;
            }
        }
        return false;
    }

    public boolean isCheckNull() {
        return isAnyPropertyNull(enemy, player, wall, goal, empty, enemyColor, playerColor, wallColor, goalColor, emptyColor);
    }
}
