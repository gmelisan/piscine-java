import java.util.Scanner;

public class Program {
	
    public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		int number = scanner.nextInt();
		if (number <= 1) {
			System.err.println("IllegalArgument");
			System.exit(-1);
		}
		int steps = 1;
		String result = "true";
		for (int i = 2; i * i <= number; ++i) {
			if (number % i == 0) {
				result = "false";
				break;
			}
			++steps;
		}
		System.out.println(result + " " + steps);
	}
}
