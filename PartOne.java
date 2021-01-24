package bankAccountInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PartOne {
	public static void main(String[] args) {
// part1
//		int[] numbers = new int[] { 39, 46, 10, 37, 33,  4, 30, 26, 14, 19, 
//		                            29,  6, 43,  8, 35, 50, 13, 25, 17, 48, 
//		                            28,  3, 41, 34, 36, 38, 49, 16, 45,  2, 
//		                            40, 15, 24,  7,  5,  9, 20,  1, 42, 44, 
//		                            21, 47, 12, 22, 18, 31, 11, 32, 27, 23 };

		String path = "src\\bankAccountInfo\\resources\\numberArray.txt";
		int[] numbers = new int[50];
		try (Scanner fileScanner = new Scanner(new File(path))) {
			while (fileScanner.hasNext()) {

				String delimeter = ",";
				// System.out.println(fileScanner.nextLine());
				String line = fileScanner.nextLine();
				String[] tempString = line.split(delimeter);
				for (int i = 0; i < numbers.length; i++) {
					numbers[i] = Integer.parseInt(tempString[i]);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int input;
		Scanner scanner = new Scanner(System.in);
		System.out.print("\nPlease enter a number between 1 and 50, inclusive: ");
		input = scanner.nextInt();

		while (input < 1 || input > 50) {
			System.out.print("\nPlease enter a number between 1 and 50, inclusive: ");
			input = scanner.nextInt();
		}
		scanner.close();
		
		printLine(53);
		
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] == input) {
				String ending = null;
				if (i == 10)
					ending = "th";
				else if (i == 11)
					ending = "th";
				else if (i == 12)
					ending = "th";
				else if (i % 10 == 0)
					ending = "st";
				else if (i % 10 == 1)
					ending = "nd";
				else if (i % 10 == 2)
					ending = "rd";
				else
					ending = "th";
				System.out.printf("%nThanks! You entered %d. It is the %d%s number.%n", input, i + 1,
						ending);
			}
		}

	}

	private static void printLine(int length) {
		for (int i = 0; i < length; i++)
			System.out.print("-");
	}
}
