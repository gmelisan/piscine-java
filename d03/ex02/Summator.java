package ex02;

import java.util.*;

public class Summator extends Thread {

    private final int id;
    private final List<Integer> array;
    private final int fromIndex;
    private final int toIndex;
    private int sum = 0;


    public Summator(int id, List<Integer> array, int fromIndex, int toIndex) {
        this.id = id;
        this.array = array;
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
    }

    @Override
    public void run() {
        for (Integer number : array)
            sum += number;
        System.out.printf("Thread %d: from %d to %d sum is %d\n", id, fromIndex, toIndex, sum);
    }

    public int getSum() {
        return sum;
    }
}
