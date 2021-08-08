import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

/**
 * BuildGraph Class holds the methods that are required to perform functionalities such 
 *Display friend list, finding common friends etc.
 *
 *
 * @author [MD Meraj Khan]
 */
public class BuildGraph {

	/**Constructor to display friends of a member
	 * @param g
	 * @param user
	 * @throws Exception is Member name doesn't match a name in the network.
	 */
	public static void displayFriends(Graph g, Object user) {
		try {
			checkName(user);
			int[] friends = g.neighbors(g.getVertexIndex(user));
			System.out.print("Friends of " + user + " are ");
			System.out.print("\n");

			for (int j = 0; j < friends.length; ++j)
				System.out.print(g.getLabel(friends[j]) + "\n");
			System.out.println();
		} catch (Exception e) {
			System.err.println("Please try again");
		}
	}

	/**Constructor to display Friends of friends of a member
	 * @param g
	 * @param user
	 * @throws Exception is Member name doesn't match a name in the network.
	 */
	public static void displayAllFriends(Graph g, Object user) {
		try {
			checkName(user);
			int[] friends2 = g.neighbors(g.getVertexIndex(user));
			int j;

			System.out.print("\n");
			System.out.println(user + " has " + friends2.length + " friend(s)");

			for (j = 0; j < friends2.length; ++j) {
				int x = j + 1;
				System.out.println("The no " + x + "  friend of " + user + " is ");
				System.out.println(g.getLabel(friends2[j]) + "\n");
				String test = String.valueOf(g.getLabel(friends2[j]));

				int[] friends1 = g.neighbors(g.getVertexIndex(test));
				System.out.println("Friends of " + test + " are ");
				for (int K = 0; K < friends1.length; K++) {
					System.out.print(g.getLabel(friends1[K]) + "\n");
				}
				System.out.println("------------------");
			}
		} catch (Exception e) {
			System.err.println("Please try again");
		}
	}

	/**Constructor to display friends of a member
	 * @param g
	 * @param user1
	 * @param user2
	 * @throws Exception is Members name do not match existing names in the network.
	 */
	public static void commonFriends(Graph g, Object user1, Object user2) {
		try {
			checkName(user1);
			checkName(user2);

			int[] friends1 = g.neighbors(g.getVertexIndex(user1));
			int[] friends2 = g.neighbors(g.getVertexIndex(user2));

			for (int i = 0; i < friends1.length; i++) {
				for (int j = 0; j < friends2.length; j++) {
					if (friends1[i] == friends2[j]) {
						System.out.println(
								"The Common friend(s) of " + user1 + " & " + user2 + " are " + g.getLabel(friends1[i]));
						break;
					}
				}
			}
		} catch (Exception e) {
			System.err.println("Please try again");
		}
	}

	/**Constructor to display popular members in the network
	 * @param g
	 * @param user
	 */
	public static void displayPopularity(Graph g, Object user) {
		int[] friends = g.neighbors(g.getVertexIndex(user));
		System.out.print("  " + user + "             ");
		for (int j = 0; j < friends.length; ++j)
			g.getLabel(friends[j]);
		int count = 0;
		while (count < friends.length) {
			count++;
		}
		System.out.println(count);
	}

	/**Checks if the name is valid
	 * @param user
	 * @throws FileNotFoundException
	 */
	public static void checkName(Object user) throws FileNotFoundException {
		File file = new File("target.txt");
		Scanner fileInput = new Scanner(file);
		int count = fileInput.nextInt();
		int index = 0;
		String[] names = new String[count];

		for (int i = 0; i < names.length; i++) {
			index = fileInput.nextInt();
			names[i] = fileInput.next();
		}

		List<String> list = Arrays.asList(names);
		if (list.contains(user)) {
			boolean b = true;
		} else {
			System.err.println("The name " + user + " does not exist in the network");
			return;
		}
	}	
}
