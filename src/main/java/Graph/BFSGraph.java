package Graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/***
 * https://blog.csdn.net/raphealguo/article/details/7523411
 *
 */
public class BFSGraph extends Graph {

    public void ShowPath(){

    }
    public void BFSTravers(Node startNode){
        // all nodes as not-visited
        this.ResetNodesVisited();
        // create a queue for unvisited, gray nodes
        // Create a queue for BFS,
        Queue<Node> unvisitedNodes = new LinkedList<Node>();

        // Mark the current node as visited and enqueue it
        startNode.setVisited(false);
        unvisitedNodes.add(startNode);

        while(unvisitedNodes.size() != 0){
            //dequeue the node from top...
            Node currentNode = unvisitedNodes.poll();

            System.out.print(currentNode.getNodeId() + " "); // print the node

            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            for (Map.Entry<Integer,Path> pathEntry : paths.entrySet() ) {
                if ( pathEntry.getValue().startNode == currentNode ) {
                    if ( pathEntry.getValue().endNode.getVisited() == false){
                        pathEntry.getValue().endNode.setVisited(true);
                        unvisitedNodes.add(pathEntry.getValue().endNode);
                    }

                }
            }

        }
    }

    public static void main(String[] args) {
        System.out.println("BFS travers demo.\n");
        BFSGraph graph = new BFSGraph();

        graph.BFSTravers(graph.nodes.get(1));
    }
}
