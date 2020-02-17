package guiSystem;

import java.util.Scanner;

public class Knapsack {

	static int numObject;
	static int knapsackcapacity= 0;
	static String customer[] = new String[100];
	static double numCombination;
	static String remarks = null;
	static Scanner input = new Scanner(System.in);

	public static void possibleCombinations(String customer[], int weightObject[], int costObject[],
			int knapsackcapacity, int numObject) {
		int weight = 0;
		int cost = 0;
		String newVar = "";

		for (int i = 1; i < (1 << numObject); i++) {
			String newVariable = Integer.toBinaryString(i);
				
			for (int j = newVariable.length() - 1, k = numObject - 1; j >= 0; j--, k--) {
				if (newVariable.charAt(j) == '1') {
					newVar = customer[k];
					weight += weightObject[k];
					cost += costObject[k];
					System.out.print(newVar+" ");
				}
			}
			if(weight<=knapsackcapacity) {
				remarks = "Accepted";
			}else {
				remarks = "Rejected";
			}

			System.out.println("\t" + weight + "\t" + cost + "\t" + remarks);

			weight = 0;
			cost = 0;
		}//end of loop
	}

	public static void main(String[] args) {

		System.out.print("Enter weight threshold: ");
		knapsackcapacity = input.nextInt();
		System.out.print("Enter number of objects: ");
		numObject = input.nextInt();

		int weightObject[] = new int[numObject + 1];
		int costObject[] = new int[numObject + 1];

		System.out.println("Enter variables: ");
		for (int i = 0; i < numObject; i++) {
			customer[i] = input.next();
		}
		for (int i = 0; i < numObject; i++) {
			System.out.print("Enter weight of object " + customer[i] + ": ");
			weightObject[i] = input.nextInt();
		}
		for (int i = 0; i < numObject; i++) {
			System.out.print("Enter cost of object " + customer[i] + ": ");
			costObject[i] = input.nextInt();
		}

		possibleCombinations(customer, weightObject, costObject, knapsackcapacity, numObject);
	}
}
