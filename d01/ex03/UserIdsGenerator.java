package ex03;

public class UserIdsGenerator {

    private static UserIdsGenerator instance;
    private Integer lastId = 0;

    private UserIdsGenerator(){}

    public static UserIdsGenerator getInstance() {
        if (instance == null) {
            instance = new UserIdsGenerator();
        }
        return instance;
    }

    public int generateId() {
        ++lastId;
        return lastId;
    }
}
