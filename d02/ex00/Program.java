package ex00;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Program {

	private static final String SIG_FILENAME = "signatures.txt";
	private static final String RESULT_FILENAME = "result.txt";
	private static final int EOF = -1;
	private static final int MAX_BYTES = 30;

	public static void main(String[] args) {
		Signatures sigs;
		try {
			sigs = new Signatures(SIG_FILENAME);
		} catch (SignatureFormatException | NumberFormatException e) {
			System.err.println(String.format("Wrong format of file '%s'", SIG_FILENAME));
			System.exit(-1);
			return ;
		} catch (FileNotFoundException e) {
			System.err.println(String.format("File '%s' not found", SIG_FILENAME));
			System.exit(-1);
			return ;
		}

		Scanner scanner = new Scanner(System.in);
		String s;
		while (scanner.hasNextLine()) {
			s = scanner.nextLine();
			if (s.equals("42"))
				break ;
			if (s.isEmpty())
				continue ;
			try {
				int c;
				List<Integer> bytes = new LinkedList<>();
				FileInputStream is = new FileInputStream(s);
				int i = 0;
				while ((c = is.read()) != EOF && i < MAX_BYTES) {
					bytes.add(c);
					++i;
				}
				String format = sigs.getFormat(bytes);
				if (format.isEmpty())
					System.out.println("UNDEFINED");
				else {
					FileWriter fw = new FileWriter(RESULT_FILENAME, true);
					fw.append(format + "\n");
					fw.close();
					System.out.println("PROCESSED");
				}
			} catch (FileNotFoundException e) {
				System.err.println("File not found");
			} catch (IOException e) {
				System.out.println(e + ": " + e.getMessage());
			}
		}
	}
}
