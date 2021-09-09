package edu.school21.chaseLogic;

import java.util.ArrayList;

public class Cerebra {
    private final char emptyChar;
    private char[][] map;
    private Position player;
    private Position currentBot;

    public Cerebra(char[][] map, Integer playerX,Integer playerY, char emptyChar) {
        this.map = map;
        this.player = new Position(playerX,playerY);
        this.emptyChar = emptyChar;
    }

    public void setMap(char[][] map) {
        this.map = map;
    }

    public void setPlayer(Integer x, Integer y) {
        this.player =  new Position(x,y);
    }

    public void setCurrentBot(Integer x, Integer y) {
        this.currentBot = new Position(x,y);
    }

    public Integer[] getMove() {
        ArrayList<Position> priorityMoves = getMovePriority();

        Position smartMove = new Position(currentBot.getX(),currentBot.getY());

        for (Position movePosition : priorityMoves) {
            if (canGoToDirection(movePosition.getX(), movePosition.getY())) {
                smartMove = movePosition;
                break;
            }
        }

        Integer[] position = new Integer[2];

        position[0] = smartMove.getX();
        position[1] = smartMove.getY();

        return position;
    }

    private ArrayList<Position> getMovePriority() {
        ArrayList<Position> arrayList = new ArrayList<>();

        Position up = new Position(currentBot.getX(), currentBot.getY() - 1);
        Position down = new Position(currentBot.getX(), currentBot.getY() + 1);
        Position right = new Position(currentBot.getX() + 1, currentBot.getY());
        Position left = new Position(currentBot.getX() - 1, currentBot.getY());

        int difY = currentBot.getY() - player.getY();
        int difX = currentBot.getX() - player.getX();

        if (difX == 0 || (Math.abs(difY) < Math.abs(difX) && difY != 0)) {
            if (difY < 0 && difX <= 0) {
                arrayList.add(down);
                arrayList.add(right);
                arrayList.add(up);
                arrayList.add(left);
            } else if (difY < 0) {
                arrayList.add(down);
                arrayList.add(left);
                arrayList.add(up);
                arrayList.add(right);
            } else if (difY > 0 && difX < 0) {
                arrayList.add(up);
                arrayList.add(right);
                arrayList.add(down);
                arrayList.add(left);
            } else if (difY > 0) {
                arrayList.add(up);
                arrayList.add(left);
                arrayList.add(down);
                arrayList.add(right);
            }
        } else {
            if (difY <= 0 && difX < 0) {
                arrayList.add(right);
                arrayList.add(down);
                arrayList.add(left);
                arrayList.add(up);
            } else if (difY <= 0) {
                arrayList.add(left);
                arrayList.add(down);
                arrayList.add(right);
                arrayList.add(up);
            } else if (difX < 0) {
                arrayList.add(right);
                arrayList.add(up);
                arrayList.add(left);
                arrayList.add(down);
            } else {
                arrayList.add(left);
                arrayList.add(up);
                arrayList.add(right);
                arrayList.add(down);
            }
        }

        return arrayList;
    }

    private boolean canGoToDirection(int x, int y) {
        boolean isEdgeOfMap =
                x >= 0 && x < map.length &&
                        y >= 0 && y < map.length;

        return isEdgeOfMap && (map[y][x] == emptyChar || (x == player.getX() && y == player.getY()));
    }
}
