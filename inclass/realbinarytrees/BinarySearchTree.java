import java.util.Currency;

public class BinarySearchTree {
	public Node root;
	
	/**
	 * Constructs an empty binary search tree.
	 */
	public BinarySearchTree() {
		//TODO: Initialize the root
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
			//TODO: Implement this helper method
			//return add(temp, root);
			return addHelper(temp, root);
		}
	}
	private boolean addHelper(Node toBeInserted, Node currentRoot) {
		//is the value at toBeInserted < > or = than the value at currentRoot?
		if (toBeInserted.value < currentRoot.value) {
			if (currentRoot.left != null) {
				return addHelper(toBeInserted, currentRoot.left);
			}
			currentRoot.left = toBeInserted;
			return true;
		}
		else if (toBeInserted.value > currentRoot.value) {
			if (currentRoot.right != null) {
				return addHelper(toBeInserted, currentRoot.right);
			}
			currentRoot.right = toBeInserted;
			return true;
		}
		else {
			return false;
		}

	}

	/**
	 * Returns true if the value is contained in the tree.
	 * 
	 * @param value Value to be searched in the tree.
	 * 
	 * @return True if the value is contained in the tree.
	 */
	public boolean contains(int value) {
		//TODO: Implement this helper method
		//return contains(value, root);
		return helpContains(value, root);
	}

	private boolean helpContains(int searchingFor, Node currentRoot) {
		//is the value at toBeInserted < > or = than the value at currentRoot?
		if (currentRoot == null) {
			return false;
		}
		if (searchingFor < currentRoot.value) {
			currentRoot = currentRoot.left;
			return helpContains(searchingFor, currentRoot);
		}
		else if (searchingFor > currentRoot.value) {
			currentRoot = currentRoot.right;
			return helpContains(searchingFor, currentRoot);
		}
		else {
			return true;
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
		//TODO: Find the minimum element recursively in the left tree
		//      and then return its value
		return minHelper(root).value;
	}
	private Node minHelper(Node currentRoot) {
	
		if (currentRoot.left != null) {
			currentRoot = currentRoot.left;
			return minHelper(currentRoot);
		}
		else {
			return currentRoot;
		}
	
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

		//TODO: Find the maximum element recursively in the right tree
		//      and then return its value
		return maxHelper(root).value;
	}
	private Node maxHelper(Node currentRoot) {
	
		if (currentRoot.right != null) {
			currentRoot = currentRoot.left;
			return minHelper(currentRoot);
		}
		else {
			return currentRoot;
		}
	
	}

	/**
	 * Removes an element from the tree.
	 * 
	 * @param value Value to be removed from the tree.
	 * 
	 * @return True if the value was removed; false if the value was not found.
	 */
	public boolean remove(int value) {
		//TODO: Implement this helper method
		//return remove(value, root, null);
		return removeHelper(value, root, null);

	}
	private boolean removeHelper(int searchingFor, Node currentRoot, Node parentRoot) {
		if (currentRoot == null) {
			return false;
		}
		if (searchingFor < currentRoot.value) {
			return removeHelper(searchingFor, currentRoot.left, currentRoot);
		}
		else if (searchingFor > currentRoot.value) {
			return removeHelper(searchingFor, currentRoot.right, currentRoot);
		}
		else {

			//if i have two children
			if (currentRoot.left != null && currentRoot.right != null) {
				int minVal = minHelper(currentRoot.right).value;
				removeHelper(minVal, currentRoot.right, currentRoot);
				currentRoot.value = minVal;
				return true;
			}

			Node child = null;
			
			//find a child
			if (currentRoot.left != null) {
				child = currentRoot.right;
			}
			else {
				child = currentRoot.left;
			}

			if (parentRoot == null) {
				root = child;
			}

			//relink parent to child
			if (parentRoot == null) {
				root = child;
			}
			else {
				//if it has two children, enter this part
				if (parentRoot.left == currentRoot) {
					parentRoot.left = child;
				}
				else {
				//else (AKA if it only has one or zero children)
				parentRoot.right = child;
				}
			}

			return true;
		}

		public int heightFromLeaf(Node currentRoot) {
			int leftVal = heightFromLeaf(currentRoot.left);
			int rightVal = heightFromLeaf(currentRoot.right);
			if (leftVal > rightVal)
				return 1 + heightFromLeaf(currentRoot.left);
			return 1 + heightFromLeaf(currentRoot.right);
		}
	}



	public static void main(String args[]) {
		
		BinarySearchTree tree = new BinarySearchTree();
		
		System.out.println("Minimum: " + tree.minimumValue());
		System.out.println("Maximum: " + tree.maximumValue());
		
		System.out.println("Contains 5: " + tree.contains(5));
		
		System.out.println("Adding 5: " + tree.add(5));
		
		System.out.println("Contains 5:" + tree.contains(5));

		System.out.println("Adding 3: " + tree.add(3));
		System.out.println("Adding 7: " + tree.add(7));
		
		System.out.println("Minimum: " + tree.minimumValue());
		System.out.println("Maximum: " + tree.maximumValue());
		
		System.out.println("Adding 1: " + tree.add(1));
		System.out.println("Adding 4: " + tree.add(4));

		System.out.println("Removing 9:" + tree.remove(9));

		System.out.println("Removing 3:" + tree.remove(3));
		System.out.println("Removing 4:" + tree.remove(4));
		System.out.println("Removing 1:" + tree.remove(1));
		
		
	}
}
