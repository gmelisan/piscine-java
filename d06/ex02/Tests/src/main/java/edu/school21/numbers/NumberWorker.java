package edu.school21.numbers;

class IllegalNumberException extends RuntimeException {}

public class NumberWorker {

    public boolean isPrime(int number) {
        if (number <= 1)
            throw new IllegalNumberException();
        for (int i = 2; i * i <= number; ++i) {
            if (number % i == 0)
                return false;
        }
        return true;
    }

    public int digitSum(int number) {
        int result = 0;
        do {
            result += number % 10;
        } while ((number /= 10) != 0);
        return result;
    }
}
