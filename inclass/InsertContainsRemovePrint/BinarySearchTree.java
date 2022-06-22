public class BinarySearchTree {
	public Node root;

	/**
	 * Constructs an empty binary search tree.
	 */
	public BinarySearchTree() {
		root = null;
	}

	/**
	 * Adds an element into the tree.
	 * 
	 * @param value The element to be inserted in the tree.
	 * 
	 * @return True if the element was inserted; false if the value was already present.
	 */
	public boolean add(int value) {
		Node temp = new Node(value);

		if(root == null) {
			root = temp;

			return true;
		}
		else {
			return add(temp, root);
		}
	}

	public boolean add(Node node, Node currentRoot) {
		if(node.value < currentRoot.value) {
			if(currentRoot.left != null) {
				return add(node, currentRoot.left);
			}
			else {
				currentRoot.left = node;
				return true;
			}
		}
		else if(node.value > currentRoot.value) {
			if(currentRoot.right != null) {
				return add(node, currentRoot.right);
			}
			else {
				currentRoot.right = node;
				return true;
			}
		}

		return false;
	}

	/**
	 * Returns true if the value is contained in the tree.
	 * 
	 * @param value Value to be searched in the tree.
	 * 
	 * @return True if the value is contained in the tree.
	 */
	public boolean contains(int value) {
		return contains(value, root);
	}

	public boolean contains(int value, Node currentRoot) {
		if(currentRoot == null) {
			return false;
		}

		if(value == currentRoot.value) {
			return true;
		}
		else if(value < currentRoot.value) {
			return contains(value, currentRoot.left);
		}
		else {
			return contains(value, currentRoot.right);
		}
	}

	/**
	 * Returns the minimum value of the tree.
	 * 
	 * @return The minimum value of the tree, or -1 if the tree is empty.
	 */
	public int minimumValue() {
		if(root == null) {
			return -1;
		}

		Node minimumNode = minimumNode(root);

		return minimumNode.value;
	}

	public Node minimumNode(Node currentRoot) {
		if(currentRoot.left != null) {
			return minimumNode(currentRoot.left);
		}

		return currentRoot;
	}

	/**
	 * Returns the maximum value of the tree.
	 * 
	 * @return The maximum value of the tree, or -1 if the tree is empty.
	 */
	public int maximumValue() {
		if(root == null) {
			return -1;
		}

		Node maximumNode = maximumNode(root);

		return maximumNode.value;
	}

	public Node maximumNode(Node currentRoot) {
		if(currentRoot.right != null) {
			return maximumNode(currentRoot.right);
		}

		return currentRoot;
	}

	/**
	 * Removes an element from the tree.
	 * 
	 * @param value Value to be removed from the tree.
	 * 
	 * @return True if the value was removed; false if the value was not found.
	 */
	public boolean remove(int value) {
		return remove(value, root, null);
	}

	private boolean remove(int value, Node currentRoot, Node currentParent) {
		if(currentRoot == null) {
			return false;
		}
		else if(value < currentRoot.value) {
			return remove(value, currentRoot.left, currentRoot);
		}
		else if(value > currentRoot.value) {
			return remove(value, currentRoot.right, currentRoot);
		}

		// When value == currentRoot.value...

		if(currentRoot.left != null && currentRoot.right != null) {
			int min = minimumNode(currentRoot.right).value;

			// This will always succeed because you know
			// that the element to be removed is present
			remove(min, currentRoot.right, currentRoot);

			currentRoot.value = min;

			return true;
		}

		Node child;

		if(currentRoot.left != null) {
			child = currentRoot.left;
		}
		else {
			child = currentRoot.right;
		}

		if(currentParent == null) {
			root = child;
		}
		else {
			if(currentParent.left == currentRoot) {
				currentParent.left = child;
			}
			else {
				currentParent.right = child;
			}
		}
		
		// Note that if both children were null,
		// the part above works too!

		return true;
	}

	public void printAll() {
		printNice(root, 0);
		
		// System.out.println("Pre-order:");
		// printPreOrder(root);
		// System.out.println();

		// System.out.println("In-order:");
		// printInOrder(root);
		// System.out.println();

		// System.out.println("Post-order:");
		// printPostOrder(root);
		// System.out.println();
		
	}

	private void printInOrder(Node currentRoot) {
		if(currentRoot == null) {
			return;
		}

		printInOrder(currentRoot.left);
		System.out.print(" ");
		System.out.print(currentRoot.value);
		System.out.print(" ");
		printInOrder(currentRoot.right);
	}

	private void printPreOrder(Node currentRoot) {
		if(currentRoot == null) {
			return;
		}

		System.out.print(" ");
		System.out.print(currentRoot.value);
		System.out.print(" ");
		printPreOrder(currentRoot.left);
		printPreOrder(currentRoot.right);
	}

	private void printPostOrder(Node currentRoot) {
		if(currentRoot == null) {
			return;
		}

		printPostOrder(currentRoot.left);
		printPostOrder(currentRoot.right);
		System.out.print(" ");
		System.out.print(currentRoot.value);
		System.out.print(" ");
	}

	/**
	 * Prints the tree like this:

   [5]
[4]
      [3]
   [2]
      [1]

	 * This denotes a binary search tree with root [4] and two children:
	 *  2, and 5. Children 2 has two children: 1 and 3.
	 *  
	 * @param currentRoot The place where we start printing
	 */
	private void printNice(Node currentRoot, int howMuchToIndent) {
		if(currentRoot == null) {
			return;
		}

		printNice(currentRoot.left, howMuchToIndent + 1);
		for(int i = 0; i < howMuchToIndent; i++) {
			System.out.print("   ");
		}
		System.out.println(currentRoot.value);
		printNice(currentRoot.right, howMuchToIndent + 1);
	}

	/**
	 * Returns the height of the tree. An empty tree has height -1,
	 * a leaf node has height 0, otherwise the tree has as height
	 * the maximum of the height among its child subtrees plus one.
	 * 
	 * @return The height of the subtree, as defined above.
	 */
	public int height() {
		return height(root);
	}

	private int height(Node currentRoot) {
		if(currentRoot == null) {
			return - 1;
		}

		return Math.max(height(currentRoot.left), height(currentRoot.right)) + 1;
	}
	
	public static void main(String args[]) {
		BinarySearchTree tree = new BinarySearchTree();

		System.out.println("Minimum: " + tree.minimumValue());
		System.out.println("Maximum: " + tree.maximumValue());

		// System.out.println("Contains 5: " + tree.contains(5));

		// System.out.println("Adding 5: " + tree.add(5));

		// System.out.println("Contains 5:" + tree.contains(5));

		// System.out.println("Adding 3: " + tree.add(3));


		System.out.println("Minimum: " + tree.minimumValue());
		System.out.println("Maximum: " + tree.maximumValue());

		System.out.println("Adding 1: " + tree.add(5));
		System.out.println("Adding 4: " + tree.add(7));
		System.out.println("Adding 1: " + tree.add(99));
		System.out.println("Adding 4: " + tree.add(98));
		System.out.println("Adding 1: " + tree.add(8));
		System.out.println("Adding 4: " + tree.add(97));
		System.out.println("Adding 4: " + tree.add(9));
		System.out.println("Adding 4: " + tree.add(3));
		System.out.println("Adding 4: " + tree.add(1));
		System.out.println("Adding 4: " + tree.add(4));

		// System.out.println("Adding 4: " + tree.add(7));
		// System.out.println("Adding 4: " + tree.add(10));
		// System.out.println("Adding 4: " + tree.add(8));
		// System.out.println("Adding 4: " + tree.add(1));
		// System.out.println("Adding 4: " + tree.add(6));




		tree.printAll();


	}
}
