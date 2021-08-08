public class IntLinkedList {

	public IntLinkedListNode head;
	public IntLinkedListNode tail;

	public IntLinkedList() {
		this.head = null;
		this.tail = null;
	}

	/**This method adds a node at the end of Linked List
	 * @param nodeData the data to place in the new node
	 */
	public void insertNode(int nodeData) {
		IntLinkedListNode node = new IntLinkedListNode(nodeData);

		if (this.head == null) {
			this.head = node;
		} else {
			this.tail.next = node;
		}

		this.tail = node;
	}

	/**Compute the number of nodes in a linked list.
	 * @return the number of nodes in the linked list
	 */
	public int getCount() {
		IntLinkedListNode temp = head;
		int count = 0;
		while (temp != null) {
			count++;
			temp = temp.next;
		}
		return count;
	}

	/**prints the linked list
	 * @param head of the node of a linked list that will be printed
	 */
	public static void printLinkedList(IntLinkedListNode head) {
		while (head != null) {
			System.out.print(head.data);
			head = head.next;
		}
		System.out.println("");

	}

	/**method to add a new node at the end of the linked list 
	 * @param node the data to place in the new node
	 */
	public void addToTheLast(IntLinkedListNode node) {

		if (head == null) {
			head = node;
		} else {
			IntLinkedListNode temp = head;
			while (temp.next != null)
				temp = temp.next;

			temp.next = node;
		}
	}

	/** prints the linked list
	 * @param printNode 
	 */
	public void printList(IntLinkedListNode printNode) {
		IntLinkedListNode temp = printNode;
		while (temp != null) {
			System.out.print(temp.data);
			temp = temp.next;
		}
		
		System.out.println();
	}


	/**method to reverse a linked List
	 * @param node accepts the node of the linked list
	 * @return the reversed Linked List
	 */
	public static IntLinkedListNode reverseLinkedList(IntLinkedListNode node) {
		if (node == null || node.next == null) {
			return node;
		}

		IntLinkedListNode remaining = reverseLinkedList(node.next);
		node.next.next = node;
		node.next = null;
		return remaining;
	}

	
	/**This function will do sum of numbers represented by linked list
	 * @param l1 is the first SILL 
	 * @param l2 is the Second SILL
	 * @return the sum of both SILLs
	 */
	public IntLinkedListNode findSumOfNumbers(IntLinkedListNode l1, IntLinkedListNode l2) {
		int carry = 0;

		IntLinkedListNode newHead = null;
		IntLinkedListNode tempNodeForIteration = null;
		int sum = 0;

		int firstIter = 0;
		while (l1 != null || l2 != null) {
			firstIter++;
			sum = carry;
			if (l1 != null) {
				sum = sum + l1.data;
				l1 = l1.next;
			}

			if (l2 != null) {
				sum = sum + l2.data;
				l2 = l2.next;
			}

			carry = sum / 10;
			sum = sum % 10;
			// Check if it first node for the result
			if (firstIter == 1) {
				tempNodeForIteration = new IntLinkedListNode(sum);
				newHead = tempNodeForIteration;
			} else {
				IntLinkedListNode tempSumNode = new IntLinkedListNode(sum);
				tempNodeForIteration.next = tempSumNode;
				tempNodeForIteration = tempNodeForIteration.next;
			}

		}
		if (carry != 0) {
			IntLinkedListNode tempNode = new IntLinkedListNode(carry);
			tempNodeForIteration.next = tempNode;
		}

		return newHead;
	}

}
