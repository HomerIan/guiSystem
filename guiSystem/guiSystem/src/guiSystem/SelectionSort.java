package guiSystem;

import java.io.PrintStream;
import java.sql.*;

public class SelectionSort {
	// SELECTION SORT
	public static void selectionSort(Integer[] arr) {

		for (int i = 0; i < arr.length - 1; i++) {
			int minValuePosition = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[minValuePosition]) {
					minValuePosition = j;
				}
			}
			final int temp = arr[i];
			arr[i] = arr[minValuePosition];
			arr[minValuePosition] = temp;

			for (int k = 0; k < arr.length; k++) {
				// ITERTATION
				System.out.print(arr[k] + " ");
			}
			System.out.println();
		}
	}
}
