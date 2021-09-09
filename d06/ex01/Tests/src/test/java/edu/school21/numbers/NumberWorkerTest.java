package edu.school21.numbers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberWorkerTest {

    private final NumberWorker numberWorker = new NumberWorker();

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 2207, 5167, 5851, 7841})
    public void isPrimeForPrimes(int number) {
        assertTrue(numberWorker.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 8, 12, 14, 26, 99, 2206, 4213, 4313, 5621, 7777, 8888, 9999})
    public void isPrimeForNotPrimes(int number) {
        assertFalse(numberWorker.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {-3, -2, -1, 0, 1})
    public void isPrimeForIncorrectNumbers(int number) {
        assertThrows(IllegalNumberException.class, () -> numberWorker.isPrime(number));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    public void digitSumIsValid(int number, int sum) {
        assertEquals(numberWorker.digitSum(number), sum);
    }
}
