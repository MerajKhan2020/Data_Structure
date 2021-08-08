import java.io.*;
import java.util.*;


/*Student Name: Md Meraj Khan
Email: Ashekeenkhan@gmail.com
Subject: ITC 322 - Data Structure
Assignment 1 */

public class Main {
	private static Scanner userInput;
	private static String menu;

	public static void main(String[] args) throws IOException {

		try {
			userInput = new Scanner(System.in);
			menu = getMenu();

			boolean userSelection = false;

			while (!userSelection) {
				String userEntry = input(menu);

				switch (userEntry) {
				case "1":
					readFile();
					break;

				case "2":
					displayIntLists();

					break;

				case "3":
					additionIntList();

					break;

				case "4":
					userSelection = true;
					break;

				default:
					printOutput("\nInvalid option\n");
					break;
				}

			}
		} catch (RuntimeException runtimeException) {
			printOutput(runtimeException);
		}

		printOutput("\nProgram Ended\n");
	}

	/** This is the menu
	 * @return tostring version of the menu
	 */
	private static String getMenu() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\"=========== Large integer addition ==========\" \n")
				.append("\"| Enter 1 to Read numbers from File         |\"\n")
				.append("\"| Enter 2 to Display integers stored in SILL|\"\n")
				.append("\"| Enter 3 to Display addition result        |\"\n")
				.append("\"| Enter 4 to Exit                           |\"\n")
				.append("\"=============================================\"\n");

		return stringBuilder.toString();
	}

	/**this method prompts user input
	 * @param prompt is string value entered by user.
	 * @returnuser input
	 */
	private static String input(String prompt) {
		System.out.print(prompt);
		return userInput.nextLine();
	}

	/**Prints String version of an object
	 * @param object can be passed on from other method
	 */
	private static void printOutput(Object object) {
		System.out.println(object);
	}

	/**This method reads the SILLs from the text file provided 
	 * @throws IOException indicates that an invalid input has been entered by the User. 
	 */
	private static void readFile() throws IOException {
		Scanner readInt;
		try {
			Scanner userInput = new Scanner(System.in);
			System.out.println("Please enter the file name:");
			String fileName = userInput.nextLine();
			File file = new File(fileName);


			readInt = new Scanner(file);

			while (readInt.hasNext()) {
				String str1 = readInt.next();
				String str2 = readInt.next();

				System.out.println(str1);
				System.out.println(str2);

				IntLinkedList linkedList = new IntLinkedList();

				for (int i = 0; i < str1.length(); i++) {

					str1 = str1.replaceAll("-", "");

					char c = str1.charAt(i);
					linkedList.insertNode(Character.getNumericValue(c));
				}

			}
			readInt.close();
			
		} catch (Exception e) {
			System.out.println("File read failed: Couldn’t find the file! Please check the file name and enter again");
		}

	}

	/** Print a linked List
	 * @param the head of a linked list that will be copied (which may be
	 * an empty list in where source is null)
	 */
	private static void printLinkedList(IntLinkedListNode head) {
		while (head != null) {
			System.out.print(head.data);
			head = head.next;
		}
		System.out.println("");

	}

	/**This method displays the SILL list from the text file. 
	 * @throws IOException indicates that an invalid input has been entered by the User.
	 */
	private static void displayIntLists() throws IOException {
		Scanner readInt;
		try {
			Scanner userInput = new Scanner(System.in);
			System.out.println("Please enter the file name:");
			String fileName = userInput.nextLine();
			File file = new File(fileName);
			readInt = new Scanner(file);

			while (readInt.hasNext()) {
				String str1 = readInt.next();
				String str2 = readInt.next();
				
				String Str3 = new String (str1);
				String Str4 = new String (str2);

				IntLinkedList linkedList = new IntLinkedList();

				for (int i = 0; i < str1.length(); i++) {

					str1 = str1.replaceAll("-", "");

					char c = str1.charAt(i);
					linkedList.insertNode(Character.getNumericValue(c));
				}

				IntLinkedList linkedList1 = new IntLinkedList();

				for (int i = 0; i < str2.length(); i++) {

					str2 = str2.replaceAll("-", "");
					char c = str2.charAt(i);
					linkedList1.insertNode(Character.getNumericValue(c));
				}

				System.out.println(Str3+" Count of nodes is " + linkedList.getCount());
				System.out.println(Str4+" Count of nodes is " + linkedList1.getCount());
			}
			readInt.close();
			
		} catch (Exception e) {
			System.out.println("File read failed: Couldn’t find the file! Please check the file name and enter again");
		}

	}

	/**This method perfomrs the Addition task and displays the sum of two SILLs.
	 * @throws IOException indicates that an invalid input has been entered by the User.
	 */
	private static void additionIntList() throws IOException {
		Scanner readInt;
		try {
			Scanner userInput = new Scanner(System.in);
			System.out.println("Please enter the file name:");
			String fileName = userInput.nextLine();
			File file = new File(fileName);


			readInt = new Scanner(file);

			while (readInt.hasNext()) {
				String str1 = readInt.next();
				String str2 = readInt.next();
			
				if (str1.contains("-") && str2.contains("-")) {
					IntLinkedList linkedList = new IntLinkedList();

					for (int i = 0; i < str1.length(); i++) {

						str1 = str1.replaceAll("-", "");

						char c = str1.charAt(i);
						linkedList.insertNode(Character.getNumericValue(c));
					}

					IntLinkedList linkedList1 = new IntLinkedList();

					for (int i = 0; i < str2.length(); i++) {

						str2 = str2.replaceAll("-", "");
						char c = str2.charAt(i);
						linkedList1.insertNode(Character.getNumericValue(c));
					}

					int countNode1 = linkedList.getCount();
					int countNode2 = linkedList1.getCount();

					IntLinkedList linkedList3 = new IntLinkedList();
					// Creating a linked list
					IntLinkedListNode head1 = new IntLinkedListNode(countNode1);
					head1 = linkedList.head;
					System.out.print("SILL 1:  "+"-");
					linkedList3.printList(head1);
					// head=null;

					IntLinkedListNode head2 = new IntLinkedListNode(countNode2);
					head2 = linkedList1.head;
					linkedList3.addToTheLast(head2);

					System.out.print("SILL 2:  "+"-");
					linkedList3.printList(head2);
					// Reversing first linkedList
					head1 = linkedList3.reverseLinkedList(head1);

					// Reversing second linkedList
					head2 = linkedList3.reverseLinkedList(head2);
					;

					// function to find sum of two linkedlist represent by number
					IntLinkedListNode result = linkedList3.findSumOfNumbers(head1, head2);
					// Reverse the above linkedlist to get actual sum
					result = linkedList3.reverseLinkedList(result);
					System.out.print("SILL 3:  "+"-");
					linkedList3.printList(result);
					
				}else {
					IntLinkedList linkedList = new IntLinkedList();

					for (int i = 0; i < str1.length(); i++) {
						char c = str1.charAt(i);
						linkedList.insertNode(Character.getNumericValue(c));
					}

					IntLinkedList linkedList1 = new IntLinkedList();

					for (int i = 0; i < str2.length(); i++) {
						char c = str2.charAt(i);
						linkedList1.insertNode(Character.getNumericValue(c));
					}

					int countNode1 = linkedList.getCount();
					int countNode2 = linkedList1.getCount();

					IntLinkedList linkedList3 = new IntLinkedList();
					
					// Creating a linked list
					IntLinkedListNode head1 = new IntLinkedListNode(countNode1);
					head1 = linkedList.head;
					System.out.print("SILL 1:  ");
					linkedList3.printList(head1);
					// head=null;

					IntLinkedListNode head2 = new IntLinkedListNode(countNode2);
					head2 = linkedList1.head;
					linkedList3.addToTheLast(head2);

					System.out.print("SILL 2:  ");
					linkedList3.printList(head2);
					// Reversing first linkedList
					head1 = linkedList3.reverseLinkedList(head1);

					// Reversing second linkedList
					head2 = linkedList3.reverseLinkedList(head2);
					;

					// function to find sum of two linkedlist represent by number
					IntLinkedListNode result = linkedList3.findSumOfNumbers(head1, head2);
					// Reverse the above linkedlist to get actual sum
					result = linkedList3.reverseLinkedList(result);
					System.out.print("SILL 3:  ");
					linkedList3.printList(result);
					
				}

			}
			readInt.close();
			
		} catch (Exception e) {
			System.out.println("File read failed: Couldn’t find the file! Please check the file name and enter again");
		}

	}

}
