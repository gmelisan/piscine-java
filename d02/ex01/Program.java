package ex01;

import java.io.*;
import java.util.ArrayList;
import java.util.*;

public class Program {
	private static final int BUFSIZE = 50_000;

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("Expected 2 args");
			System.exit(-1);
		}
		ArrayList<String> a = getFileWords(args[0]);
		ArrayList<String> b = getFileWords(args[1]);
		HashSet<String> dict = new HashSet<>(a);
		dict.addAll(b);

		ArrayList<Integer> aFreq = new ArrayList<>();
		for (String s : dict)
			aFreq.add(0);
		ArrayList<Integer> bFreq = new ArrayList<>();
		for (String s : dict)
			bFreq.add(0);
		for (String s : a) {
			if (dict.contains(s)) {
				int index = getSetIndex(dict, s);
				aFreq.set(index, aFreq.get(index) + 1);
			}
		}
		for (String s : b) {
			if (dict.contains(s)) {
				int index = getSetIndex(dict, s);
				bFreq.set(index, bFreq.get(index) + 1);
			}
		}

		System.out.printf("similarity = %.3f%n", similarity(aFreq, bFreq));

		try {
			FileWriter writer = new FileWriter("dictionary.txt");
			writer.write(dict.toString());
			writer.close();
		} catch (IOException e) {
			System.err.println(e);
			System.exit(-1);
		}
	}

	private static int getSetIndex(HashSet<String> set, String str) {
		int i = 0;
		for (String s : set) {
			if (s.equals(str))
				return i;
			++i;
		}
		return -1;
	}

	private static ArrayList<String> getFileWords(String filename) {
		ArrayList<String> res = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename), BUFSIZE);
			char[] buf = new char[BUFSIZE];
			int len;
			StringBuilder word = new StringBuilder();
			while ((len = reader.read(buf)) != -1) {
				for (int i = 0; i < len; ++i) {
					if (Character.isWhitespace(buf[i])) {
						if (!word.toString().isEmpty()) {
							res.add(word.toString());
							word = new StringBuilder();
						}
					} else {
						word.append(buf[i]);
					}
				}
			}
			if (!word.toString().isEmpty()) {
				res.add(word.toString());
			}

		} catch (IOException e) {
			System.err.println(e);
			System.exit(-1);
		}
		return res;
	}

	private static double similarity(ArrayList<Integer> a, ArrayList<Integer> b) {
		int numerator = 0;
		for (int i = 0; i < a.size(); ++i)
			numerator += a.get(i) * b.get(i);
		int left = 0;
		for (Integer ai : a)
			left += ai * ai;
		int right = 0;
		for (Integer bi : b)
			right += bi * bi;
		double denominator = Math.sqrt(left) * Math.sqrt(right);
		if (denominator == 0)
			return 0;
		return numerator / denominator;
	}
}
