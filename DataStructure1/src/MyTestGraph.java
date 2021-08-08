import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Addition (Driver Program)
 *
 * For the demonstration and testing of the Social Networking Program.
 *
 * @author [Meraj Khan]
 */
public class MyTestGraph {
	/** Enum to simplify input case handling */
	private enum Selection {
		FILE, FRIENDS, ALLFRIENDS, COMMONFRIENDS, DELETE, POPULARITY, EXIT, UNKNOWN
	};

	// Standard input scanner
	private static Scanner input;

	/*
	 * Main method
	 *
	 * @param args expected to contain optional filename for parsing
	 */
	public static void main(String[] args) throws IOException {
		// Initialise standard input scanner and get file from args, if possible
		input = new Scanner(System.in);
		preloadFile(args);

		// Handle user input
		while (true) {
			switch (getSelection()) {
			case UNKNOWN:
				reprompt();
				break;
			case FILE:
				getFile();
				break;
			case ALLFRIENDS:
				getAllFriends();
				break;
			case FRIENDS:
				getFriends();
				break;
			case COMMONFRIENDS:
				getCommonFriend();
				break;
			case DELETE:
				deleteMember();
				break;
			case POPULARITY:
				getPopularity();
				break;
			case EXIT:
				exit();
			}
		}
	}

	/*
	 * @param args copy of command line arguments
	 */
	private static void preloadFile(String[] args) throws IOException {
		if (args.length == 2) {
			// Program only accepts one filename (should be first token)
			loadFile2(args[0]);
		} else if (args.length > 1) {
			System.err.println("\n(Skipping load stage, only one filename allowed at a time.)\n");
		} else {
			System.out.println();
		}
	}

	/*
	 * Display a simple menu via standard output
	 */
	private static void printMenu() {
		System.out.println("Enter: 1 to load files");
		System.out.println("       2 to enter a Member name and view the friends of friends list");
		System.out.println("       3 to enter a Member name and view friends list");
		System.out.println("       4 to enter two Member names and view their common friend(s)");
		System.out.println("       5 to delete a member");
		System.out.println("       6 to see most popular members");
		System.out.println("       7 to exit");
		System.out.print("\n>> ");
	}

	/*
	 * @return enum case indicating selection type
	 */
	private static Selection getSelection() {
		printMenu();

		// Take input as text to avoid int parsing errors
		switch (input.nextLine()) {
		case "1":
			return Selection.FILE;
		case "2":
			return Selection.ALLFRIENDS;
		case "3":
			return Selection.FRIENDS;
		case "4":
			return Selection.COMMONFRIENDS;
		case "5":
			return Selection.DELETE;
		case "6":
			return Selection.POPULARITY;
		case "7":
			return Selection.EXIT;
		default:
			return Selection.UNKNOWN;
		}
	}

	/*
	 * Use file contents, if any, to populate Social Media network using graph data structure
	 *
	 * @param filename specifies location of input
	 */
	private static void loadFile2(String filename) {

		try {
			// Need to get file handle for name
			File file = new File(filename);
			BufferedWriter bw = null;
			BufferedReader br = new BufferedReader(new FileReader(filename));
			FileWriter filewriter = new FileWriter("target.txt");
			bw = new BufferedWriter(filewriter);

			// File may be missing
			if (file.exists()) {
				System.out.println("\nLoading " + filename + "...");
				try (Scanner fileInput = new Scanner(file)) {

					try {

						String line1;

						while ((line1 = br.readLine()) != null) {
							System.out.println(line1);

							bw.write(line1);
							bw.newLine();
						}
						br.close();
						bw.close();

					} catch (Exception e) {
						// First input failed validation check
						System.err.println(" unsuccessful, first line invalid.\n");
						return;
					}

					System.out.println(" success!\n");
				} catch (Exception e) {
					// Triggered if either nextLine call failed to yield input
					System.err.print(" unsuccessful, data invalid or missing.\n\n");
				}
			} else {
				// Triggered if file doesn't exist
				System.err.print("\nLoad unsuccessful, file not found...\n\n");
			}
		} catch (Exception e) {	
			// Triggered if the file name is incorrect
			System.err.println("Unable to read file name. The file name is incorrect. Please try to load file again");
			return;
		}		
	}

	/*
	 * Use file contents, if any, to populate Social Media network using graph data structure
	 *
	 * @param filename specifies location of input
	 */
	private static void loadFile1(String filename1) {
		// Need to get file handle for name
		try {
			File file = new File(filename1);
			BufferedReader br;
			br = new BufferedReader(new FileReader(filename1));
			BufferedWriter bw = null;
			FileWriter filewriter = new FileWriter("target1.txt");
			bw = new BufferedWriter(filewriter);

			// File may be missing
			if (file.exists()) {
				System.out.println("\nLoading " + filename1 + "...");
				try (Scanner fileInput = new Scanner(file)) {
					try {
						String line1;
						while ((line1 = br.readLine()) != null) {							
							System.out.println(line1);
							bw.write(line1);
							bw.newLine();
						}
						br.close();
						bw.close();
				} catch (Exception e) {		
					// First input failed validation check
					System.err.println(" unsuccessful, first line invalid.\n");
					return;
				}
				System.out.println(" success!\n");
			} catch (Exception e) {
				// Triggered if either nextLine call failed to yield input
				System.err.print(" unsuccessful, data invalid or missing.\n\n");
			}
		} else {
			// Triggered if file doesn't exist
			System.err.print("\nLoad unsuccessful, file not found...\n\n");
		}		
	}catch (Exception e) {	
		// Triggered if the file name is incorrect
		System.err.println("Unable to read file name. The file name is incorrect. Please try to load file again");
		return;
	}
}

	/*
	 * Displays Friends of friends list of a Member
	 *
	 */
	private static void getAllFriends() throws IOException {
		// Need to get file handle for name
		File file2 = new File("target.txt");
		File file1 = new File("target1.txt");

		//Scanner to read first file
		Scanner fileInput2 = new Scanner(file2);
		int counter1 = fileInput2.nextInt();

		//Initializing a graph
		Graph graph = new Graph(counter1);
		int counter2 = 0;
		int index = 0;
		String label = null;

		while (fileInput2.hasNext() && counter2<counter1-1){
			index= fileInput2.nextInt();
			label= fileInput2.next();
			graph.setLabel(index, label); 
		}

		//Scanner to read Second file
		Scanner fileInput1 = new Scanner(file1);
		int counter3 = fileInput1.nextInt();
		int counter4 = 0;
		int source = 0;
		int target = 0;

		while (fileInput1.hasNext() && counter4<counter3){
			source= fileInput1.nextInt();
			target= fileInput1.nextInt();
			graph.addEdge(source, target);
		}
		Scanner scan = new Scanner (System.in);
		System.out.println("Please enter a Member name:");
		String Member = scan.nextLine();
		BuildGraph.displayAllFriends(graph, Member);
	}

	/*
	 * Displays Friend list of a Member
	 *
	 */
	private static void getFriends() throws IOException {
		// Need to get file handle for name
		File file2 = new File("target.txt");
		File file1 = new File("target1.txt");

		//Scanner to read first file
		Scanner fileInput2 = new Scanner(file2);
		int counter1 = fileInput2.nextInt();

		//Initializing a graph
		Graph graph = new Graph(counter1);
		int counter2 = 0;
		int index = 0;
		String label = null;

		while (fileInput2.hasNext() && counter2<counter1-1){
			index= fileInput2.nextInt();
			label= fileInput2.next();
			graph.setLabel(index, label); 
		}

		//Scanner to read Second file
		Scanner fileInput1 = new Scanner(file1);
		int counter3 = fileInput1.nextInt();
		int counter4 = 0;
		int source = 0;
		int target = 0;

		while (fileInput1.hasNext() && counter4<counter3){
			source= fileInput1.nextInt();
			target= fileInput1.nextInt();
			graph.addEdge(source, target);
		}		
		Scanner scan = new Scanner (System.in);
		System.out.println("Please enter a Member name:");
		String Member = scan.nextLine();	
		BuildGraph.displayFriends(graph, Member);
	}

	/*
	 * Displays common friends of two Members
	 *
	 */
	private static void getCommonFriend() throws FileNotFoundException {
		// Need to get file handle for name
		File file2 = new File("target.txt");
		File file1 = new File("target1.txt");

		//Scanner to read first file
		Scanner fileInput2 = new Scanner(file2);
		int counter1 = fileInput2.nextInt();

		//Initializing a graph
		Graph graph = new Graph(counter1);
		int counter2 = 0;
		int index = 0;
		String label = null;

		while (fileInput2.hasNext() && counter2<counter1-1){
			index= fileInput2.nextInt();
			label= fileInput2.next();
			graph.setLabel(index, label); 
		}

		//Scanner to read Second file
		Scanner fileInput1 = new Scanner(file1);
		int counter3 = fileInput1.nextInt();
		int counter4 = 0;
		int source = 0;
		int target = 0;

		while (fileInput1.hasNext() && counter4<counter3){
			source= fileInput1.nextInt();
			target= fileInput1.nextInt();
			graph.addEdge(source, target);
		}
		Scanner scan = new Scanner (System.in);
		System.out.println("Please enter 1st Member name:");
		String Member1 = scan.nextLine();
		System.out.println("Please enter 2nd Member name:");
		String Member2 = scan.nextLine();		

		BuildGraph.commonFriends(graph, Member1, Member2);
	}

	/*
	 * Deletes a specific member from a network
	 *
	 */
	private static void deleteMember() {
		System.err.println("This function is not available");
	}

	/*
	 * Shows the popularity of all members
	 *
	 */
	private static void getPopularity() throws IOException {
		// Need to get file handle for name
		File file2 = new File("target.txt");
		File file1 = new File("target1.txt");

		//Scanner to read first file
		Scanner fileInput2 = new Scanner(file2);
		int counter1 = fileInput2.nextInt();

		//Initializing a graph
		Graph graph = new Graph(counter1);
		int counter2 = 0;
		int index = 0;
		String label = null;

		while (fileInput2.hasNext() && counter2<counter1-1){
			index= fileInput2.nextInt();
			label= fileInput2.next();
			graph.setLabel(index, label); 
		}

		//Scanner to read Second file
		Scanner fileInput1 = new Scanner(file1);
		int counter3 = fileInput1.nextInt();
		int counter4 = 0;
		int source = 0;
		int target = 0;

		while (fileInput1.hasNext() && counter4<counter3){
			source= fileInput1.nextInt();
			target= fileInput1.nextInt();
			graph.addEdge(source, target);
		}

		BuildGraph bh = new BuildGraph();
		System.out.println("Name:" + "        || " + "Number of Friends");
		System.out.println("========================================");
		for (int i = 0; i < graph.size(); i++)
			BuildGraph.displayPopularity(graph, graph.getLabel(i));
	}

	/*
	 * Handle cases of unknown input
	 */
	private static void reprompt() {
		System.err.println("\nInvalid selection, please retry.\n");
	}

	/*
	 * Interactively load new input file
	 */
	private static void getFile() throws IOException {
		System.out.print("\nPlease enter the file name for Network List (Eg. friend.txt).\n>> ");
		String filename2 = input.nextLine();
		System.out.print("\nPlease enter the file name for index (Eg. index.txt).\n>> ");
		String filename1 = input.nextLine();
		loadFile1(filename2);
		loadFile2(filename1);
	}

	/*
	 * Performs cleanup and exits the program. 
	 *
	 */
	private static void exit() {
		File file1 = new File("target.txt");
		file1.delete();
		File file2 = new File("target1.txt");
		file2.delete();
		System.out.println("Performing cleanup.");
		System.out.println("Exiting, goodbye...");
		System.exit(0);		
	}
}

