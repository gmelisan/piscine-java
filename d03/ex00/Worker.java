package ex00;

public class Worker implements Runnable {

    private String name;
    private int count;

    public Worker(String name, int count) {
        this.name = name;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; ++i) {
            System.out.println(name);
            Thread.yield();
        }
    }
}
