package ex01;

import java.util.*;

public class Consumer extends Thread {

    private final List<String> buffer;
    private final String name;
    private boolean done = false;

    public Consumer(List<String> buffer, String name) {
        this.buffer = buffer;
        this.name = name;
    }

    @Override
    public void run() {
        while (!done) {
            synchronized (buffer) {
                if (!buffer.isEmpty() && buffer.get(0).equals(name))
                    System.out.println(buffer.remove(0));
            }
        }
    }

    public void setDone() {
        done = true;
    }
}
