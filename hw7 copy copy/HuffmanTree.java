import java.io.PrintStream;
import java.util.*;

/** Implementation of a Huffman Tree, which encodes and decodes txt files
 *
 * @author Preston Ito
 * @author Liz Austell
 *
 * Time Spent: 4 hours
 */
public class HuffmanTree {

    //The top of the tree, from which the entire tree stems from
    private HuffmanNode root;
    
    /** HuffmanTree constructor
     * 
     * This constructor constructs a Huffman coding tree using the
     * given array of frequencies
     *
     * @param count - the number of occurrences of the character
     *                with the ASCII value i
     */
    public HuffmanTree(int[] count) {
        //puts each ascii value and corresponding frequency into a node
        //add each node to a List
        Queue<HuffmanNode> listOfNodes = new PriorityQueue<HuffmanNode>();
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0)
                listOfNodes.add(new HuffmanNode(count[i], i));
        }
        //EOF character
        listOfNodes.add(new HuffmanNode(1, count.length));
        add(listOfNodes);
        root = listOfNodes.remove();
    }
    
    /** Helper method for HuffmanTree Constructor
     * 
     * This method recursively adds each node of the HuffmanTree from the
     * values with the lowest frequency until the entire tree is constructed
     * 
     * @param listOfNodes - Queue of nodes in order from smallest to greatest frequency
     */
    private void add(Queue<HuffmanNode> listOfNodes) {

        //the tree is done, so we set the root to the top of the tree
        if (listOfNodes.size() == 1)
            return;
        
        //Combines the first two elements together because they have the smallest freq
        HuffmanNode node1 = listOfNodes.remove();
        HuffmanNode node2 = listOfNodes.remove();

        //creates a new node and links the first two list elements as its children
        //removes the first two list elements and adds the new node into the list
        //call recursively and repeat until the tree is done
        HuffmanNode combinedNode = new HuffmanNode(node1.frequency + node2.frequency);
        combinedNode.left = node1;
        combinedNode.right = node2;
        listOfNodes.add(combinedNode);
        add(listOfNodes);
        
    }

    // Implementation of each node in a Huffman Tree
    private class HuffmanNode implements Comparable<HuffmanNode>
    {
        public int frequency;
        public int AsciiValue;
	
	    public HuffmanNode left;
	    public HuffmanNode right;
	
	    /** HuffmanNode constructor
         * 
         * Has a value for frequency and ASCII in each node
         * 
         * @param frequency
         * @param AsciiValue
         */
        public HuffmanNode(int frequency, int AsciiValue) {
		    this.frequency = frequency;
            this.AsciiValue = AsciiValue;
		
		    left = null;
		    right = null;
	    }

         /** HuffmanNode constructor
         * 
         * Has a value for frequency in each node
         * 
         * @param frequency
         * @param AsciiValue
         */
        public HuffmanNode(int frequency) {
		    this.frequency = frequency;
		
		    left = null;
		    right = null;
	    }

         /** HuffmanNode constructor
         * 
         * Empty nodes for the purpose of reconstructing a tree
         * where we don't necessarily know the values of the internal nodes
         * 
         * @param frequency
         * @param AsciiValue
         */
        public HuffmanNode()
        {
            left = null;
		    right = null;   
        }

        /** Orders HuffmanNodes from least to greatest frequencies
         *  for the purpose of the priority queue
         */
        public int compareTo(HuffmanNode other)
        {
            return this.frequency - other.frequency;
        }
    }

    /** Writes the Huffman Tree to the supplied output stream
     *
     * @param output - a file that contains ASCII and its corresponding Huffman code 
     *                 (consisting of 0's and 1's that represent left and right nodes in the tree)
     */
    public void write(PrintStream output, int[] count) {

        //prints the number of elements in the output in the beginning of the .huff file
        int counter = 1;
        for (int i = 0; i < count.length; i ++) {
            if (count[i] != 0)
                counter++;
        }
        int countKey = counter * 2;
        output.println(countKey);

        String leftRight = "";
        writeHelper(root, output, leftRight);

    }

    /** Recursive write helper method
     * 
     * This method recursively traverses the tree and logs the path 
     * taken to each leaf node and writes it to output
     * 
     * @param currentRoot - the current node in the traversal of tree
     * @param output - a file that contains ASCII and its corresponding Huffman code 
     *                 (consisting of 0's and 1's that represent left and right nodes 
     *                 in the tree)
     * @param leftRight - String that keeps track of the direction of traversal in 
     *                    order to assign each value a HuffmanCode
     */
    private void writeHelper(HuffmanNode currentRoot, PrintStream output, 
                            String leftRight) {
        
        // if currentRoot has no children, it's a leaf node, so we write 
        // the ASCII value and corresponding Huffman code to the output.
        if (currentRoot.left == null && currentRoot.right == null)
        {
            output.println(currentRoot.AsciiValue);
            output.println(leftRight);
            leftRight = leftRight.substring(0,leftRight.length()-1);
            return;
        }
       
        //keeps track of going left and right, and calls recursively to the
        //left and right in order to construct the Huffman code
        leftRight = leftRight + "0";
        writeHelper(currentRoot.left, output, leftRight);
        leftRight = leftRight.substring(0,leftRight.length()-1);

        leftRight = leftRight + "1";
        writeHelper(currentRoot.right, output, leftRight);
        leftRight = leftRight.substring(0,leftRight.length()-1);

    }

    /** second constructor for decoding (unhuffing)
     * 
     * @param input a file that contains ASCII and its corresponding Huffman code 
     *              (consisting of 0's and 1's that represent left and right nodes 
     *              in the tree)
     */
    public HuffmanTree(Scanner input) {
        int n; 
        String code;

        root = new HuffmanNode();
        HuffmanNode currentNode = root;

        //doesn't work, but supposed to read the beginning of the .huff file
        int countKey = Integer.parseInt(input.nextLine());
        System.out.println(countKey);
        for (int i = 0; i < countKey; i++) {
            n = Integer.parseInt(input.nextLine()); // reads the character, ASCII
            code = input.nextLine(); // reads the corresponding Huffman code
            createNodes(n, code, currentNode);
        }
    }

    /** Recursive helper for second HuffmanTree constructor
     * 
     * This method creates Huffman nodes to reconstruct a Huffman Tree by 
     * traversing the Huffman Code
     * 
     * @param n - each ASCII value
     * @param code - String of each Huffman Code
     * @param currentNode - the current node in the reconstruction of tree
     */
    private void createNodes(int n, String code, HuffmanNode currentNode)
    {
        //Iterates through each index of code to construct nodes to the
        //left and right as dictated by the Huffman code
        for (int i = 0; i<code.length(); i++)
            {
                if (code.substring(i, i+1).equals("0"))
                {
                   if (currentNode.left == null)
                        currentNode.left = new HuffmanNode();
                   currentNode = currentNode.left;
                }
                else
                {
                    if (currentNode.right == null)
                        currentNode.right = new HuffmanNode();
                    currentNode = currentNode.right;
                }
            }
            currentNode.AsciiValue = n;
    }

    /** Decodes the huffman code into its original characters 
     * 
     * This method reads the individual bits from the input stream and writes 
     * the corresponding characters to the supplied output stream. It stops 
     * reading when a character with a value equal to the eof parameter is encountered. 
     * 
     * @param input - A list of 0s and 1s representing the Huffman Code 
     * @param output - The original words that were encoded 
     * @param eof - pseudo- End-of-File marker to know when to stop decoding
     */
    public void decode(BitInputStream input, PrintStream output, int eof) {

        //Reads bit by bit and figures out the corresponding ascii value
        //Writes the ASCII character to the output
        if (root == null)
            return;
        HuffmanNode currentNode = root;
        boolean flag = true;
        //Iterates through the entire input
        while (flag)
        {
            
            //Iterates until a leaf node is found in the tree by using
            //the huffman code to traverse the tree
            while (currentNode.right != null || currentNode.left != null)
            {
                int currentBit = input.readBit();
                if (currentBit == -1)
                {
                    flag = false;
                    break;
                }
                else if (currentBit == 0) 
                {
                    currentNode = currentNode.left;
                }
                else
                    currentNode = currentNode.right;               
            }
            //Once we have found a leaf node, we write the corresponding 
            //ascii value to the output
            if (currentNode.AsciiValue == eof)
                return;
            output.write(currentNode.AsciiValue);
            currentNode = root;
        } 
    }
}
