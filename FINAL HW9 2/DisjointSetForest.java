import java.util.*;

/** Implementation of DisjointSetForest, a data structure that is used for image compression
 * 
 * @author Luca Voichick
 * @author Preston Ito
 * 
 * Time Spent: 4 hrs
 * 
 */
public class DisjointSetForest {

    //The node forest as a 2-dimensional Node array
    public Node[][] nodeForest;


    /** DisjointSetForest constructor
     * 
     * Constructs the nodeForest array given a 2-dimensional pixel Array
     * 
     * @param pixelArray - Array of pixels
     */
    public DisjointSetForest(Pixel[][] pixelArray) {
        nodeForest = new Node[pixelArray.length][pixelArray[0].length];
        
        for (int row = 0; row < pixelArray.length; row++) {
            for(int col = 0; col < pixelArray[0].length ; col++){ 
                Node node = new Node();
                node.pixel = pixelArray[row][col];
                node.segmentSize = 1;
                nodeForest[row][col] = node;  
            } 
        }
    }

    /** Method that finds node head given a pixel
     * 
     * Finds the parent node using a while loop and
     * sets each of the nodes above it to the same parent
     * 
     * @param Pixel - a pixel object
     * @return - The representative (parent) node 
     */
    public Node find(Pixel pixel) {

        //points pixel to the one representative 
        Set<Node> visitedNodes = new HashSet<Node>();
        Node representative = nodeForest[pixel.getRow()][pixel.getCol()];
        representative.pixel = pixel;

        //collects all of the nodes that need to be pointed to the one representative
        while (representative.parent != null) {
            visitedNodes.add(representative);
            representative = representative.parent;
        }

        //goes through the set and makes each of their parent = the oen representative 
        for (Node child : visitedNodes) {
            child.parent = representative;
        }

        return representative;
    }


    /** Unions two nodes by updating parent/child relationship
     * 
     * Updates the parent relationship of two give nodes, and also
     * updates node's segment size, internal distance, and rank (if necessary)
     * 
     * @param Pixel1 - Node 1
     * @param Pixel2 - Node 2
     * @param edge - Edge that connects nodes
     */
    public void union(Node Pixel1, Node Pixel2, Edge edge) {
        
        if(Pixel1.rank > Pixel2.rank){
            Pixel1.segmentSize = Pixel1.segmentSize + Pixel2.segmentSize;
            Pixel2.parent = Pixel1;
            Pixel1.internalDistance = edge.getWeight();
        }
        else if(Pixel2.rank > Pixel1.rank){
            Pixel2.segmentSize = Pixel1.segmentSize + Pixel2.segmentSize;
            Pixel1.parent = Pixel2;
            Pixel2.internalDistance = edge.getWeight();
        }
        else {
            Pixel2.segmentSize = Pixel1.segmentSize + Pixel2.segmentSize;
            Pixel1.parent = Pixel2;
            Pixel2.internalDistance = edge.getWeight();
            Pixel2.rank = Pixel2.rank+1;   
        }  
    }  
}
