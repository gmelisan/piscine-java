package edu.school21.game.engine;

public enum Action {
    LEFT('A'),
    UPWARD('W'),
    RIGHT('D'),
    DOWNWARD('S'),
    LOSE('9');

    private final Character code;

    Action(char code) {
        this.code = code;
    }

    private static final Action[] ALL_VALUES = values();

    public static Action getByCode(int code) {
        for (Action action: ALL_VALUES) {
            if (action.code.equals(Character.toUpperCase((char)code))) {
                return action;
            }
        }
        return null;
    }

    public static Action getByCode(String code) {
        for (Action action: ALL_VALUES) {
            if (action.code.equals(code.toCharArray()[0])) {
                return action;
            }
        }
        return null;
    }

    public Character getCode() {
        return code;
    }
}
