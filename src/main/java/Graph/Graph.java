package Graph;

import java.util.*;

public class Graph implements IGraph {

    public Map<Integer,Node> nodes;
    public Map<Integer,Path> paths;
    //public Map<Integer,Boolean> isNodeVisited ;



    public Graph(){
        InitTree();
    }

    private void InitTree(){

        this.nodes = new HashMap<Integer, Node>();
        this.nodes.put(1, new Node(1));
        this.nodes.put(2, new Node(2));
        this.nodes.put(3, new Node(3));
        this.nodes.put(4, new Node(4));
        this.nodes.put(5, new Node(5));

        this.paths = new HashMap<Integer, Path>();
        this.paths.put(1, new Path(1, this.nodes.get(1), this.nodes.get(2)));
        this.paths.put(2, new Path(2, this.nodes.get(1), this.nodes.get(3)));
        this.paths.put(3, new Path(3, this.nodes.get(2), this.nodes.get(4)));
        this.paths.put(4, new Path(4, this.nodes.get(2), this.nodes.get(5)));

    }

    public void DFSTravers( Node startNode) {
        //suedou code
        System.out.print( startNode.getNodeId().toString() + "->");
        startNode.setVisited(true);
        Set<Map.Entry<Integer, Path>> pathEntries = paths.entrySet();
        for( Map.Entry<Integer, Path> pathEntry : pathEntries) {
            if ( pathEntry.getValue().startNode == startNode
                   && pathEntry.getValue().endNode.getVisited() == false) {
                DFSTravers( pathEntry.getValue().endNode);
            }
        }
    }

    /***
     * start from all the nodes and travers the graph.
     */
    public void DFSTravers(){
        Set<Map.Entry<Integer, Node>> entries = nodes.entrySet();
        for( Map.Entry<Integer, Node> entry : entries) {
            DFSTravers(entry.getValue());
            System.out.println();
        }
    }

    /***
     * find a shortest path from a startnode to endnode
     * @param startNode
     * @param endNode
     */
    public void DFSTravers(Node startNode, Node endNode){
        System.out.print( startNode.getNodeId() + "->");
        if ( startNode == endNode ){
            return;
        }

        startNode.setVisited(true);
        Set<Map.Entry<Integer, Path>> pathEntries = paths.entrySet();
        for( Map.Entry<Integer, Path> pathEntry : pathEntries) {
            if ( pathEntry.getValue().startNode == startNode
                    && pathEntry.getValue().endNode.getVisited() == false) {
                DFSTravers( pathEntry.getValue().endNode, endNode);
            }
        }
    }

    /***
     *
     * @param startNodeId
     */
    public void BFSTravers(Integer startNodeId){
        //suedou code

    }

    /***
     * use stack to travese graph, instead of recursive calls
     * In fact, the iterate mathod is BFS
     * https://www.hackerearth.com/zh/practice/algorithms/graphs/depth-first-search/tutorial/
     *
     * @param graph
     * @param startNodeid
     */
    public void DFSIterate(Graph graph, Integer startNodeid){
        Stack<Node> nodeStack = new Stack<Node>();
    }

    public static void main(String[] args) {
        System.out.printf("Test");
        Graph graph = new Graph();
        //graph.DFSTravers(graph.nodes.get(1));
        graph.DFSTravers(graph.nodes.get(1), graph.nodes.get(5));
    }
}
