package edu.school21.game.engine;

import com.sun.xml.internal.xsom.impl.Ref;
import edu.school21.game.app.AppProperties;
import edu.school21.game.app.ArgsProperties;
import edu.school21.game.map.GameMap;
import edu.school21.game.map.GameMapGenerator;
import edu.school21.game.map.Position;
import edu.school21.game.printer.Printer;

import edu.school21.chaseLogic.Cerebra;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GameEngine {
    private final AppProperties appProperties;
    private final String profile;
    private GameMap gameMap;
    private volatile boolean isGameOver;
    private String message = "";

    public GameEngine(ArgsProperties argsProperties, AppProperties appProperties) {
        init(argsProperties, appProperties);
        this.profile = argsProperties.getProfile();
        this.appProperties = appProperties;
        this.isGameOver = false;
    }

    private void init(ArgsProperties argsProperties, AppProperties appProperties) {
        int enemiesCount = argsProperties.getEnemiesCount();

        int wallsCount = argsProperties.getWallsCount();

        int sizeMap = argsProperties.getSizeMap();

        GameMapGenerator gameMapGenerator = new GameMapGenerator(enemiesCount, wallsCount, sizeMap, appProperties);

        gameMap = gameMapGenerator.generate();
    }

    public void launch() {
        Scanner scanner = new Scanner(System.in);

        Movement movement = new Movement(gameMap, appProperties);

        Printer printer = new Printer(appProperties, gameMap.getMap());

        Map<Integer, Position> bots = gameMap.getEnemies();

        Position player = gameMap.getPlayer();

        Cerebra cerebra = new Cerebra(gameMap.getMap(), player.getX(), player.getY(), appProperties.getEmpty());

        Terminal.init();

        while (!isGameOver) {
            printer.printMap(profile);
            if (!message.isEmpty()) {
                System.out.println(message);
                message = "";
            }

            if (!movement.isMovable(player.getX(), player.getY())) {
                gameOverAlert("U lose. Enemies surrounded you(");
            }

            if (handleAction(scanner, movement)) {
                isGameOver = gameMap.isFinish();

                if (isGameOver) {
                    renderMap(printer);
                    gameOverAlert("You win! Congratulations)");
                }

                renderMap(printer);

                cerebra.setPlayer(player.getX(), player.getY());

                for (int i = 0; i < bots.size(); i++) {
                    Position currentBot = bots.get(i);

                    cerebra.setMap(gameMap.getMap());

                    cerebra.setCurrentBot(currentBot.getX(), currentBot.getY());

                    Integer[] smartMove = cerebra.getMove();

                    if (profile.equals("dev")) {
                        System.out.printf("Enemy want go to (%d,%d). Approve it by press 8.%n", smartMove[1], smartMove[0]);
                    }

                    movement.goToDirection(currentBot, smartMove[0], smartMove[1], appProperties.getEnemy());

                    if (profile.equals("dev")) {
                        if (handleDevAction(scanner)) {
                            if (i != bots.size() - 1) {
                                renderMap(printer);
                            }
                        } else {
                            gameOverAlert("U lose by not approving enemy`s smart move:)");
                        }
                    }

                    currentBot.setX(smartMove[0]);
                    currentBot.setY(smartMove[1]);

                    if (gameMap.isCatchByEnemy(currentBot)) {
                        isGameOver = true;
                        renderMap(printer);
                        gameOverAlert("You lose(");
                    }
                }

                printer.setMap(gameMap.getMap());

                isGameOver = gameMap.isFinish();
            }
        }
    }

    private void renderMap(Printer printer) {
        printer.setMap(gameMap.getMap());
        printer.printMap(profile);
    }

    private void gameOverAlert(String message) {
        Terminal.restore();
        System.out.println(message);
        System.exit(0);
    }

    private boolean handleDevAction(Scanner scanner) {
        try {
            int input = System.in.read();
            if (input == '8')
                return true;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    private boolean handleAction(Scanner scanner, Movement movement) {
        int input = 0;
        try {
            input = System.in.read();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        Action action;
        if (input != -1) {
             action = Action.getByCode(input);
        } else
            action = Action.LOSE;

        if (action == null) {
            message = ("Invalid code of Action, usage: " + Arrays
                    .stream(Action.values())
                    .map(Action::getCode)
                    .collect(Collectors.toList()));
            return false;
        } else {
            return movement.handleAction(action);
        }
    }
}
