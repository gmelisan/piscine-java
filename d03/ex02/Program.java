package ex02;

import java.util.*;

public class Program {

	private static int arraySize = 30;
	private static int threadsCount = 3;
	private static final int randomBound = 1000;

	public static void main(String[] args) throws InterruptedException {
		parseArgs(args);
		ArrayList<Integer> array = new ArrayList<>();
		Random random = new Random();
		int sum = 0;
		for (int i = 0; i < arraySize; ++i) {
			int n = random.nextInt(randomBound * 2 + 1) - randomBound;
			array.add(n);
			sum += n;
		}
		System.out.println("Sum: " + sum);
		ArrayList<Summator> pool = new ArrayList<>();
		int fromIndex = 0;
		int toIndex = arraySize / threadsCount;
		for (int i = 0; i < threadsCount; ++i) {
			if (i == threadsCount - 1)
				toIndex = array.size();
			List<Integer> sub = array.subList(fromIndex, toIndex);
			pool.add(new Summator(i + 1, sub, fromIndex, toIndex - 1));
			fromIndex = toIndex;
			toIndex = fromIndex + arraySize / threadsCount;
		}
		for (Summator s : pool) {
			s.start();
		}
		for (Summator s : pool) {
			s.join();
		}
		sum = 0;
		for (Summator s : pool) {
			sum += s.getSum();
		}
		System.out.println("Sum by threads: " + sum);
	}
	private static void parseArgs(String[] args) {
		final String argArraySize = "--arraySize=";
		final String argThreadsCount = "--threadsCount=";
		boolean userArraySize = false;
		boolean userThreadsCount = false;
		for (String arg : args) {
			if (arg.startsWith(argArraySize)) {
				arraySize = Integer.parseInt(arg.substring(argArraySize.length()));
				userArraySize = true;
			} else if (arg.startsWith(argThreadsCount)) {
				threadsCount = Integer.parseInt(arg.substring(argThreadsCount.length()));
				userThreadsCount = true;
			}
		}
		if (!userArraySize)
			System.out.println("Array size is not specified. Default to " + arraySize);
		if (!userThreadsCount)
			System.out.println("Threads count is not specified. Default to " + threadsCount);
		if (threadsCount <= 0 || arraySize <= 0) {
			System.err.println("Values should be positive");
			System.exit(-1);
		}
		if (threadsCount > arraySize) {
			System.err.println("Threads count must not be greater than array size");
			System.exit(-1);
		}
	}
}
