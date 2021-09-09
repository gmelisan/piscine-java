import java.util.Scanner;

public class Program {

	public static int sumOfDigits(int number) {

		int result = 0;
		do {
			result += number % 10;
		} while ((number /= 10) != 0);
		return result;
	}

	public static boolean isPrime(int number) {

		if (number <= 1) {
			System.err.println("IllegalArgument");
			System.exit(-1);
		}
		for (int i = 2; i * i <= number; ++i) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}
	
    public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		int result = 0;
		int number = scanner.nextInt();
		while (number != 42) {
			if (isPrime(sumOfDigits(number)))
				++result;
			number = scanner.nextInt();
		}
		System.out.println("Count of coffee-request - " + result);
	}
}
