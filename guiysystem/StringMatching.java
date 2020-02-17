package guiysystem;

import java.util.Scanner;

public class StringMatching {

	public static int StringMatching(String[] names, String pattern) {
		int patternLength = pattern.length();

		for (int a = 0; a < names.length; a++) {
			String text = names[a];
			int textLength = text.length();

			for (int i = 0; i < textLength; i++) {
				int j = 0;
				while ((j < patternLength) && (text.charAt(i + j) == pattern.charAt(j))) {
					j++;
				}
				if (j == patternLength) {
					return i;// pattern is found
				}
			}
		}
		return -1;// pattern not found
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String[] names = {"Homer Reyes","Bon Tura","Troy Gallardo"};
		String pattern = "Troy";

		int pos = StringMatching(names, pattern);

		if (pos != -1) {
			System.out.println("FOUND");
		} else {
			System.out.println("NOT FOUND");
		}
	}
}
