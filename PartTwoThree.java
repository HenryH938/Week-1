package bankAccountInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * @author Yifu Hou
 * 
 *         Code for Java Review 1 - part 2 and part 3;
 *
 */
public class PartTwoThree {
	private static int row = 50;
	private static int column = 13;
	private static String pathCustomer = "src\\bankAccountInfo\\resources\\accountInfo.txt";
	private static String pathPayment = "src\\bankAccountInfo\\resources\\payment.txt";
	private static String[] customerList = customerName(pathCustomer);
	private static double[][] paymentRecord = paymentInfo(pathPayment, row, column);
	private static String[] Standing = standingIndicator(paymentRecord);

	public static void main(String[] args) {
		mainMenu();
	}

	private static void mainMenu() {
		int entry = 0;
		while (entry != 3) {
			Scanner entranceScanner = new Scanner(System.in);
			System.out.println("\n\n         ****** Main Menu ******\n");
			System.out.println("          Press 1 to Show Report");
			System.out.println("          Press 2 to Query Records");
			System.out.println("          Press 3 to Leave App\n");
			System.out.print("          Your Entry: ");
			entry = entranceScanner.nextInt();
			System.out.println("\n");
		
			if (entry == 1) {
				printReport();
			} else if (entry == 2) {
				customerMenu();
			} else if (entry == 3) {
				System.out.println("\n\n");
				System.out.print("********* Have a Nice Day! Bye! *********");
			}

			else {
				System.out.println("\n\n");
				System.out.print("\n*** Please Follow Instruction ***\n\n");
				mainMenu();
			}
		}
		System.out.println("\n\n");
	}

	private static String[] customerName(String path) {
		String[] Customers = null;
		try (Scanner myScanner = new Scanner(new File(path))) {
			String stringContent;
			String delemiter = ",";

			while (myScanner.hasNextLine()) {
				stringContent = myScanner.nextLine();
				Customers = stringContent.split(delemiter);
				System.out.println();

				for (int i = 0; i < 50; i++) {
					String str_01 = Customers[i].replace('\"', ' ');
					Customers[i] = str_01.stripLeading().stripTrailing();
				}

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return Customers;
	}

	private static void printHeader(String label) {
		int baseLength = 85;
		printLine(baseLength);
		System.out.println("\n" + label);
		printLine(baseLength);
		System.out.print("\nName ");
		System.out.print("            Account  ");

		for (int i = 1; i <= 12; i++) {
			System.out.printf("%02d  ", i);
		}
		System.out.print("Standing\n");
		printLine(baseLength);
		System.out.println();
	}

	private static void printLine(int baseLength) {
		String lineType = "-";
		for (int i = 0; i < baseLength; i++) {
			System.out.print(lineType);
		}
	}

	private static void printReport() {
		printHeader("Customer Payment History");
		for (int i = 0; i < row; i++) {
			// print names
			System.out.printf("%-19s ", customerList[i]);
			// print paymentInfo
			for (Double d : paymentRecord[i]) {
				System.out.printf("%3.0f ", d);
			}
			// print standing
			System.out.printf("   %6s", Standing[i]);
			System.out.println("\n");
		}
		printLine(85);
	}

	private static void userQuery(int index) {
		// print names
		System.out.printf("%-19s ", customerList[index]);
		// print paymentInfo
		for (Double d : paymentRecord[index]) {
			System.out.printf("%3.0f ", d);
		}
		// print standing
		System.out.printf("   %6s", Standing[index]);
		System.out.println();
	}

	private static double[][] paymentInfo(String path, int rows, int columns) {
		int accountNoLines = rows;
		int accountInfoColumn = columns;
		String pathPayment = path;
		double[][] payments = new double[accountNoLines][accountInfoColumn];
		try (Scanner paymentScanner = new Scanner(new File(pathPayment))) {
			while (paymentScanner.hasNextLine()) {
				for (int i = 0; i < 50; i++) {
					String[] temp = paymentScanner.nextLine().split(",");
					for (int j = 0; j < 13; j++) {
						payments[i][j] = Double.parseDouble(temp[j]);
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return payments;
	}

	private static String[] standingIndicator(double[][] paymentTable) {
		String standing = null;
		String[] standingArray = new String[paymentTable.length];

		for (int i = 0; i < paymentTable.length; i++) {
			int zeroCounter = 0;
			for (double d : paymentTable[i]) {

				if (d == 0)
					zeroCounter++;

				switch (zeroCounter) {
				case 0:
					standing = "Good";
					break;
				case 1:
					standing = "Fair";
					break;
				case 2:
					standing = "Poor";
					break;
				default:
					standing = "Closed";
				}

				standingArray[i] = standing;
			}

		}
		return standingArray;
	}

	private static void customerMenu() {
		int selection = 0;
		while (selection != 4) {
			Scanner optionScanner = new Scanner(System.in);
			printLine(50);
			System.out.println("\nCustomer Menu");
			printLine(50);
			System.out.println("\n1. Find customer by account number.");
			System.out.println("\n2. Report customers with any missed payments.");
			System.out.println("\n3. Report customers with \"Closed\" status.");
			System.out.println("\n4. Exit");
			printLine(50);
			System.out.print("\nEnter 1,2,3,or 4 here: ");
			selection = optionScanner.nextInt();
			// optionScanner.close();
			switch (selection) {
			case 1:
				Scanner accountNum = new Scanner(System.in);
				System.out.print("\n\nPlease enter an Account Number: ");
				int accountNumber = accountNum.nextInt();
				printHeader("Customer Query by Accont No.");
				for (int i = 0; i < 50; i++) {
					if (paymentRecord[i][0] == accountNumber) {
						userQuery(i);
						printLine(85);
						System.out.println("\n\n\n\n");
					}

				}

				break;
			case 2:
				System.out.println();
				printHeader("Accounts with Skipped Payment");
				for (int i = 0; i < Standing.length; i++) {
					if (!Standing[i].equals("Good")) {
						userQuery(i);
					}
				}
				printLine(85);
				System.out.println("\n\n\n\n");
				break;
			case 3:
				System.out.println();
				printHeader("Accounts with \"Closed\" Standing");
				for (int i = 0; i < Standing.length; i++) {
					if (Standing[i].equals("Closed")) {
						userQuery(i);
					}
				}
				printLine(85);
				System.out.println("\n\n\n\n");
				break;
			case 4:
				System.out.println("\n\n");
				mainMenu();
				break;
			default:
				System.out.println("\n\n");
				System.out.print("\n*** Please Follow Instruction ***\n\n");
				break;
			}

		}
	}
}
