package ex00;

public class Program {

	private static int count = 50;

	public static void main(String[] args) throws InterruptedException {
		String countArgName = "--count=";
		if (args.length > 0 && args[0].startsWith(countArgName))
			count = Integer.parseInt(args[0].substring(countArgName.length()));
		Thread t1 = new Thread(new Worker("Egg", count));
		Thread t2 = new Thread(new Worker("Hen", count));
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		for (int i = 0; i < count; ++i) {
			System.out.println("Human");
		}
	}
}
