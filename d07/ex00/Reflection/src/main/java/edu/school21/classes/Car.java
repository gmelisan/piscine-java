package edu.school21.classes;

import java.util.StringJoiner;

public class Car {
    private Long id;
    private Boolean broken;
    private String comment;

    public Car() {
        this.id = 0L;
        this.broken = false;
        this.comment = "";
    }

    public Car(Long id, Boolean broken, String comment) {
        this.id = id;
        this.broken = broken;
        this.comment = comment;
    }

    public void crash() {
        broken = true;
    }
    public void crash(String comment) {
        broken = true;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Car.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("broken=" + broken)
                .add("comment='" + comment + "'")
                .toString();
    }
}
