package ex01;

import java.util.*;

public class Program {

	private static int count = 50;
	private static final List<String> buffer = new ArrayList<>();

	public static void main(String[] args) {
		String countArgName = "--count=";
		if (args.length > 0 && args[0].startsWith(countArgName))
			count = Integer.parseInt(args[0].substring(countArgName.length()));
		Consumer c1 = new Consumer(buffer, "Egg");
		Consumer c2 = new Consumer(buffer, "Hen");

		c1.start();
		c2.start();

		count *= 2;
		for (int i = 0; i < count; ++i) {
				synchronized (buffer) {
					buffer.add(i % 2 == 0 ? "Egg" : "Hen");
				}
				while (!buffer.isEmpty())
					Thread.yield();
		}

		c1.setDone();
		c2.setDone();
	}
}
