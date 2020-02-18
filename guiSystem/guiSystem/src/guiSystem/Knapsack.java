package guiSystem;

import java.util.Scanner;

public class Knapsack {

	static String remarks = null;

	public static void Knapsack(int custNum[], int weight[], int value[]) {
		int totalweight = 0, totalcost = 0, capacity = 100;

		for (int i = 0; i < custNum.length; i++) {
			for (int j = i + 1; j < custNum.length + 1; j++) {
				if (i != 0) {
					totalweight = weight[i - 1] + weight[j - 1];
					totalcost = value[i - 1] + value[j - 1];
					if (totalweight <= capacity) {
						remarks = "Accepted";
					} else {
						remarks = "Rejected";
					}
					System.out.println(i + "," + j + "\t" + totalweight + "\t" + totalcost + "\t" + remarks);
				} else {
					totalweight = weight[j - 1];
					totalcost = value[j - 1];
					if (totalweight <= capacity) {
						remarks = "Accepted";
					} else {
						remarks = "Rejected";
					}
					System.out.println(j + "\t" + totalweight + "\t" + totalcost + "\t" + remarks);
				}
			}
		}
		int a = 0, b = 1, c = 1;
		for (int i = 0; i < custNum.length * 2; i++) {
			totalweight = weight[a] + weight[a + b] + weight[a + b + c];
			totalcost = value[a] + value[a + b] + value[a + b + c];
			if (totalweight <= capacity) {
				remarks = "Accepted";
			} else {
				remarks = "Rejected";
			}
			System.out.println(custNum[a] + "," + custNum[a + b] + "," + custNum[a + b + c] + "\t" + totalweight + "\t"
					+ totalcost + "\t" + remarks);
			++c;
			if (a + b + c == 5) {
				b++;
				c = 1;
			}
			if (a + b == 4) {
				a++;
				b = 1;
			}
		}
		int d = 0, e = 1, f = 1, g = 1;
		for (int i = 0; i < custNum.length; i++) {
			totalweight = weight[d] + weight[d + e] + weight[d + e + f] + weight[d + e + f + g];
			totalcost = value[d] + value[d + e] + value[d + e + f] + value[d + e + f + g];
			if (totalweight <= capacity) {
				remarks = "Accepted";
			} else {
				remarks = "Rejected";
			}
			System.out.println(custNum[d] + "," + custNum[d + e] + "," + custNum[d + e + f] + ","
					+ custNum[d + e + f + g] + "\t" + totalweight + "\t" + totalcost + "\t" + remarks);
			++g;
			if (d + e + f + g == 5) {
				f++;
				g = 1;
			}
			if (d + e + f == 4) {
				e++;
				f = 1;
			}
			if (d + e == 3) {
				d++;
				e = 1;
			}

		}
		for (int i = 0; i < 1; i++) {
			totalweight = weight[i] + weight[i + 1] + weight[i + 2] + weight[i + 3] + weight[i + 4];
			totalcost = value[i] + value[i + 1] + value[i + 2] + value[i + 3] + value[i + 4];
			if (totalweight <= capacity) {
				remarks = "Accepted";
			} else {
				remarks = "Rejected";
			}
			System.out.println(custNum[i] + "," + custNum[i + 1] + "," + custNum[i + 2] + "," + custNum[i + 3] + ","
					+ custNum[i + 4] + "\t" + totalweight + "\t" + totalcost + "\t" + remarks);
		}
	}

	public static void main(String[] args) {

		/*
		 * System.out.print("Enter weight threshold: "); knapsackcapacity =
		 * input.nextInt(); System.out.print("Enter number of objects: "); numObject =
		 * input.nextInt();
		 * 
		 * int weightObject[] = new int[numObject + 1]; int costObject[] = new
		 * int[numObject + 1];
		 * 
		 * System.out.println("Enter variables: "); for (int i = 0; i < numObject; i++)
		 * { customer[i] = input.next(); } for (int i = 0; i < numObject; i++) {
		 * System.out.print("Enter weight of object " + customer[i] + ": ");
		 * weightObject[i] = input.nextInt(); } for (int i = 0; i < numObject; i++) {
		 * System.out.print("Enter cost of object " + customer[i] + ": "); costObject[i]
		 * = input.nextInt(); }
		 * 
		 * possibleCombinations(customer, weightObject, costObject, knapsackcapacity,
		 * numObject); }
		 */
		int[] weight = { 10, 20, 30, 40, 50 };
		int[] value = { 50, 30, 40, 20, 10 };
		int[] custNum = { 1, 2, 3, 4, 5 };
		Knapsack(custNum, weight, value);

	}
}
