import java.util.Scanner;

public class Program {

	public static int min(int a, int b, int c, int d, int e) {
		int ab = a < b ? a : b;
		int cd = c < d ? c : d;
		int abcd = ab < cd ? ab : cd;
		return abcd < e ? abcd : e;
	}

	public static int check(int n) {
		if (n < 1 || n > 9) {
			System.err.println("IllegalArgument");
			System.exit(-1);
		}
		return n;
	}

	public static int getWeekNumber(int data) {
		return data >> 8;
	}

	public static int getGrade(int data) {
		return data & 0xF;
	}

	public static int storeData(int weekNumber, int minGrade) {
		int data = 0;
		data = data | weekNumber;
		data = data << 8;
		data = data | minGrade;
		return data;
	}

	public static void printIfNeed(int data) {
		if (data == 0)
			return;
		System.out.print("Week ");
		System.out.print(getWeekNumber(data));
		System.out.print(" ");
		int m = getGrade(data);
		for (int i = 0; i < m; ++i)
			System.out.print("=");
		System.out.println(">");		
	}

    public static void main(String[] args) {
		int d1 = 0, d2 = 0, d3 = 0, d4 = 0;
		int d5 = 0, d6 = 0, d7 = 0, d8 = 0;
		int d9 = 0, d10 = 0, d11 = 0;
		int d12 = 0, d13 = 0, d14 = 0;
		int d15 = 0, d16 = 0, d17 = 0, d18 = 0;
		Scanner scanner = new Scanner(System.in);
		int prevWeekNumber = 0;
		int i = 1;

		while (true) {
			if (scanner.hasNext("42")) {
				break;
			}
			scanner.next("Week");
			int weekNumber = scanner.nextInt();
			if (weekNumber <= prevWeekNumber) {
				System.err.println("IllegalArgument");
				System.exit(-1);
			}
			int mon = check(scanner.nextInt());
			int tue = check(scanner.nextInt());
			int wed = check(scanner.nextInt());
			int thu = check(scanner.nextInt());
			int fri = check(scanner.nextInt());

			int m = min(mon, tue, wed, thu, fri);
			if (i == 1)
				d1 = storeData(weekNumber, m);
			else if (i == 2)
				d2 = storeData(weekNumber, m);
			else if (i == 3)
				d3 = storeData(weekNumber, m);
			else if (i == 4)
				d4 = storeData(weekNumber, m);
			else if (i == 5)
				d5 = storeData(weekNumber, m);
			else if (i == 6)
				d6 = storeData(weekNumber, m);
			else if (i == 7)
				d7 = storeData(weekNumber, m);
			else if (i == 8)
				d8 = storeData(weekNumber, m);
			else if (i == 9)
				d9 = storeData(weekNumber, m);
			else if (i == 10)
				d10 = storeData(weekNumber, m);
			else if (i == 11)
				d11 = storeData(weekNumber, m);
			else if (i == 12)
				d12 = storeData(weekNumber, m);
			else if (i == 13)
				d13 = storeData(weekNumber, m);
			else if (i == 14)
				d14 = storeData(weekNumber, m);
			else if (i == 15)
				d15 = storeData( weekNumber, m);
			else if (i == 16)
				d16 = storeData(weekNumber, m);
			else if (i == 17)
				d17 = storeData(weekNumber, m);
			else if (i == 18)
				d18 = storeData(weekNumber, m);
			prevWeekNumber = weekNumber;
			++i;
		}

		for (i = 1; i <= 18; ++i) {
			if (i == 1)
				printIfNeed(d1);
			else if (i == 2)
				printIfNeed(d2);
			else if (i == 3)
				printIfNeed(d3);
			else if (i == 4)
				printIfNeed(d4);
			else if (i == 5)
				printIfNeed(d5);
			else if (i == 6)
				printIfNeed(d6);
			else if (i == 7)
				printIfNeed(d7);
			else if (i == 8)
				printIfNeed(d8);
			else if (i == 9)
				printIfNeed(d9);
			else if (i == 10)
				printIfNeed(d10);
			else if (i == 11)
				printIfNeed(d11);
			else if (i == 12)
				printIfNeed(d12);
			else if (i == 13)
				printIfNeed(d13);
			else if (i == 14)
				printIfNeed(d14);
			else if (i == 15)
				printIfNeed(d15);
			else if (i == 16)
				printIfNeed(d16);
			else if (i == 17)
				printIfNeed(d17);
			else if (i == 18)
				printIfNeed(d18);
		}
		
	}
}
