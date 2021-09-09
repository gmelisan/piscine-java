package ex03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.*;

public class Program {

	private static int threadsCount = 3;
	private static final String SOURCE_FILENAME = "files_urls.txt";

	public static void main(String[] args) {
		parseArgs(args);
		ArrayList<NumberedUrl> list;
		try {
			 list = getUrls();
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
			System.exit(-1);
			return ;
		}
		ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(threadsCount);
		for (NumberedUrl nurl : list) {
			Downloader dl = new Downloader(nurl);
			executor.execute(dl);
		}
		executor.shutdown();
	}

	private static void parseArgs(String[] args) {
		final String argThreadsCount = "--threadsCount=";
		boolean userThreadsCount = false;
		for (String arg : args) {
			if (arg.startsWith(argThreadsCount)) {
				threadsCount = Integer.parseInt(arg.substring(argThreadsCount.length()));
				userThreadsCount = true;
			}
		}
		if (!userThreadsCount)
			System.out.println("Threads count is not specified. Default to " + threadsCount);
		if (threadsCount <= 0) {
			System.err.println("Values should be positive");
			System.exit(-1);
		}
	}

	private static ArrayList<NumberedUrl> getUrls() throws FileNotFoundException {
		ArrayList<NumberedUrl> list = new ArrayList<>();
		Scanner scanner = new Scanner(new FileInputStream(SOURCE_FILENAME));
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			try {
				list.add(new NumberedUrl(line));
			} catch (WrongFormatException e) {
				System.err.printf("Error while parsing '%s': %s", line, e.getMessage());
			}
		}
		return list;
	}
}
