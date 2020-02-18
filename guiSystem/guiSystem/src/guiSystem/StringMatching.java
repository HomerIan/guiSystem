package guiSystem;

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
}
