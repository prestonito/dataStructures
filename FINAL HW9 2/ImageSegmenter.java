import java.util.*;
import java.awt.Color;

/** Implementation of ImageSegmenter class
 * 
 * @author - Luca Voichick
 * @author - Preston Ito
 * 
 * Time Spent: 6 hrs
 * 
 */
public class ImageSegmenter {


    /** Segment method that finds and merges segments
     * 
     * Uses helper methods to find the segments to merge and uses
     * a formula to determine if merging is necessary.
     * 
     * @param rgbArray - 2-dimensional Color array
     * @param granularity - Used to determine criteria for merging
     * @return - 2-dimensional Color array with all segments colored
     */
    public static Color[][] segment(Color[][] rgbArray, double granularity) {
        
        Pixel[][] weightedGraph = new Pixel[rgbArray.length][rgbArray[0].length];
        DisjointSetForest forest = new DisjointSetForest(weightedGraph);
        PriorityQueue<Edge> edgeQueue = createEdgeQ(weightedGraph, rgbArray);
        unionIfNecessary(edgeQueue, forest, granularity);
        
        return colorAssign(rgbArray, weightedGraph, forest);
    }

    /** Creates PriorityQueue of Edges
     * 
     * Helper method to segment; adds edges to our PriorityQueue
     * 
     * @param weightedGraph - 2-dimensional Pixel array
     * @param rgbArray - 2-dimensional Color array
     * @return - PriorityQueue of Edge's that is in order by weight
     */
    private static PriorityQueue<Edge> createEdgeQ(Pixel[][] weightedGraph, Color[][] rgbArray) {
        PriorityQueue<Edge> edgeQueue = new PriorityQueue<Edge>();    

        for(int row = 0; row < rgbArray.length; row++){
            for(int col = 0; col < rgbArray[0].length; col++){
                Pixel pixel = new Pixel(row, col, rgbArray[row][col]);
                weightedGraph[row][col] = pixel;

                int Nrow = row -1;
                int Ncol = col-1;
                if (Nrow >= 0 && Nrow <  rgbArray.length && Ncol >= 0 && Ncol < rgbArray[0].length){
                    Pixel neighbor = new Pixel(Nrow, Ncol, rgbArray[Nrow][Ncol]);
                    Edge edge = new Edge(pixel, neighbor);
                    edgeQueue.add(edge);
                }
                Nrow = row ;
                Ncol = col+1;
                if (Nrow >= 0 && Nrow <  rgbArray.length && Ncol >= 0 && Ncol < rgbArray[0].length){
                    Pixel neighbor = new Pixel(Nrow, Ncol, rgbArray[Nrow][Ncol]);
                    Edge edge = new Edge(pixel, neighbor);
                    edgeQueue.add(edge);
                }
                Nrow = row -1;
                Ncol = col;
                if (Nrow >= 0 && Nrow <  rgbArray.length && Ncol >= 0 && Ncol < rgbArray[0].length){
                    Pixel neighbor = new Pixel(Nrow, Ncol, rgbArray[Nrow][Ncol]);
                    Edge edge = new Edge(pixel, neighbor);
                    edgeQueue.add(edge);
                }
                Nrow = row -1;
                Ncol = col+1;
                if (Nrow >= 0 && Nrow <  rgbArray.length && Ncol >= 0 && Ncol < rgbArray[0].length){
                    Pixel neighbor = new Pixel(Nrow, Ncol, rgbArray[Nrow][Ncol]);
                    Edge edge = new Edge(pixel, neighbor);
                    edgeQueue.add(edge);
                }
            }
        }
        return edgeQueue;
    }

    /** Calls union method if necessary
     * 
     * Helper method to segment; uses granularity, segment size,
     * and weight to determine if union() is necessary. 
     * 
     * @param edgeQueue - PriorityQueue that was created in createEdgeQ method
     * @param forest - DisjoinSetForest object
     * @param gran - Granularity 
     */
    private static void unionIfNecessary(PriorityQueue<Edge> edgeQueue, DisjointSetForest forest, double gran) {

        while (!edgeQueue.isEmpty()) {
            Edge edge = edgeQueue.poll();
            Pixel start = edge.getFirstPixel();
            Pixel end = edge.getSecondPixel();
            Node startNodeRep = forest.find(start);
            Node endNodeRep = forest.find(end);
            if (startNodeRep != endNodeRep) {
                int startSegSize = startNodeRep.segmentSize;
                int endSegSize = endNodeRep.segmentSize;
                if (edge.getWeight() < Math.min((startNodeRep.internalDistance + (double)(gran/startSegSize)), 
                    endNodeRep.internalDistance + (double)(gran/endSegSize))) {
                        forest.union(startNodeRep, endNodeRep, edge); 
                }
            }
        }          
    }

    /** Assigns colors to segments
     * 
     * Helper method to segment; uses a map to keep track of head nodes
     * and its associated children. Assigns the same random color all nodes in 
     * the segment.
     * 
     * @param rgbArray - 2-dimensional Color array
     * @param pixArray - 2-dimensional Pixel array
     * @param forest - DisjoinSetForest object
     * @return - Final 2-dimensional Color array with all segments colored 
     */
    private static Color[][] colorAssign(Color[][] rgbArray, Pixel[][] pixArray, DisjointSetForest forest) {

        Map<Node, ArrayList<Node>> nodeFam = new HashMap<>();

        for (int row = 0; row < rgbArray.length; row ++) {
            for (int col = 0; col < rgbArray[0].length; col ++) {
                Node repNode = forest.find(pixArray[row][col]);
                repNode.pixel = pixArray[row][col];
                if (!nodeFam.containsKey(repNode)) {
                    ArrayList<Node> hashtValue = new ArrayList<>();
                    hashtValue.add(repNode);
                    nodeFam.put(repNode, hashtValue);
                }
                else {
                    nodeFam.get(repNode).add(forest.nodeForest[row][col]);
                }
            }
        }

        ColorPicker colorGen = new ColorPicker();
        for (Node nodeHead : nodeFam.keySet()) {
            Color randoColor = colorGen.nextColor();
            nodeHead.color = randoColor;
            rgbArray[nodeHead.pixel.getRow()][nodeHead.pixel.getCol()] = randoColor;
            for (Node childNode : nodeFam.get(nodeHead)) {
                childNode.color = randoColor;
                int colorRow = childNode.pixel.getRow();
                int colorCol = childNode.pixel.getCol();
                rgbArray[colorRow][colorCol] = randoColor;
            }
        }

        return rgbArray;
    }
}

