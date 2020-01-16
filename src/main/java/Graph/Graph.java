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
        this.paths.put(1, new Path(1, this.nodes.get(1), this.nodes.get(2), 1.0));
        this.paths.put(2, new Path(2, this.nodes.get(1), this.nodes.get(3), 1.0));
        this.paths.put(3, new Path(3, this.nodes.get(2), this.nodes.get(4), 4.0));
        this.paths.put(4, new Path(4, this.nodes.get(2), this.nodes.get(5),4.0));
        this.paths.put(5, new Path(5, this.nodes.get(3), this.nodes.get(5),1.0));
    }

    public void DFSTravers( Node startNode) {
        ResetNodesVisited();
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
        ResetNodesVisited();
        Set<Map.Entry<Integer, Node>> entries = nodes.entrySet();
        for( Map.Entry<Integer, Node> entry : entries) {
            DFSTravers(entry.getValue());
            System.out.println();
        }
    }

    public void ResetNodesVisited(){
        Set<Map.Entry<Integer, Node>> entries = nodes.entrySet();
        for( Map.Entry<Integer, Node> entry : entries) {
            entry.getValue().setVisited(false);
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
     * 	DFS-iterative (G, s):                                   //Where G is graph and s is source vertex
     *       let S be stack
     *       S.push( s )            //Inserting s in stack
     *       mark s as visited.
     *       while ( S is not empty):
     *           //Pop a vertex from stack to visit next
     *           v  =  S.top( )
     *          S.pop( )
     *          //Push all the neighbours of v in stack that are not visited
     *         for all neighbours w of v in Graph G:
     *             if w is not visited :
     *                      S.push( w )
     *                     mark w as visited
     * @param startNode
     */
    public void DFSIterate(Node startNode){
//        Stack<Node> nodeStack = new Stack<Node>();
//
//        nodeStack.push(startNode);
//        startNode.setVisited(true);
//
//        while( nodeStack.isEmpty() == false){
//            //Pop a vertex from stack to visit next
//            Node node = nodeStack.pop();
//            VisitAndShow(node);
//
//            //Push all the neighbours of v in stack that are not visited
//            for( Map.Entry<Integer, Path> pathEntry : paths.entrySet()) {
//                Node neighborNode = pathEntry.getValue().endNode;
//                if ( pathEntry.getValue().startNode == node
//                    || neighborNode.getVisited() == false ){
//                    nodeStack.push(neighborNode);
//                    neighborNode.setVisited(true);
//                    System.out.print( neighborNode.getNodeId() + "->");
//                }
//
//            }
//        }
        Stack<Node> nodeStack = new Stack<Node>();
        ResetNodesVisited();

        IterateNeighbors(nodeStack, startNode);
    }

    private void IterateNeighbors(Stack<Node> nodeStack, Node startNode){
        nodeStack.add(startNode);
        do{
            // 出栈
            Node currentNode = nodeStack.pop();

            // 如果该节点还没有被遍历，则遍历该节点并将子节点入栈
            if( currentNode.getVisited() ==false){
                // 遍历并打印
                VisitAndShow(currentNode);
                currentNode.setVisited(true);

                // 没遍历的子节点入栈
                for( Map.Entry<Integer, Path> pathEntry : paths.entrySet() ){
                    if ( pathEntry.getValue().startNode == currentNode
                            || pathEntry.getValue().endNode.getVisited() == false ){
                        nodeStack.add(pathEntry.getValue().endNode);
                    }

                }

            }
        }while(!nodeStack.isEmpty());
    }

    public void DFSIterateTravers(){
        Stack<Node> nodeStack = new Stack<Node>();
        ResetNodesVisited();

        for( Map.Entry<Integer, Node> nodeEntry : nodes.entrySet()){
            if(nodeEntry.getValue().getVisited() == false ){
                IterateNeighbors(nodeStack, nodeEntry.getValue());
            }
        }
    }

    private void VisitAndShow(Node node){
        if ( node.getVisited() == false )
            System.out.print(node.getNodeId() + "->");
    }


    public void DFSRecursiveShortestPath(Node startNode, Node endNode){
        Stack<Node> nodeStack = new Stack<Node>();
        Double totalLength = 0.0;
        DFSRecursive(nodeStack, startNode, endNode, totalLength );
    }

    private void ShowPath( Stack<Node> nodeStack ){
        for( int i = 0; i< nodeStack.size(); i++){
            System.out.print( "->" + nodeStack.get(i).getNodeId() );
        }

    }

    private Double minTotalLength = 999999.0;

    private void DFSRecursive(Stack<Node> nodeStack, Node startNode, Node endNode, Double totalLength){
        nodeStack.push(startNode);
        if ( startNode == endNode ) {
                if ( minTotalLength > totalLength) {
                    minTotalLength = totalLength;
                }
                ShowPath(nodeStack);
                System.out.print(" Min Path = " + minTotalLength + "\n");
                return;
        }
        for ( Map.Entry<Integer, Path> pathEntry : paths.entrySet()  ){
            if ( pathEntry.getValue().startNode == startNode ){
                if (pathEntry.getValue().endNode.getVisited() == false){
                    pathEntry.getValue().endNode.setVisited(true);
                    DFSRecursive(nodeStack,
                            pathEntry.getValue().endNode,
                            endNode,
                            totalLength+ pathEntry.getValue().length);
                    nodeStack.pop();
                    pathEntry.getValue().endNode.setVisited(false);
                }
                else {
                    ShowPath(nodeStack);
                    System.out.print("  -----> node " + pathEntry.getValue().endNode.getNodeId() + " is not reachable.\n");
                }
            }

        }


    }
    public static void main(String[] args) {
        test1();
        test2();

    }

    public static void test1(){
        System.out.printf("Test1:\n");
        Graph graph = new Graph();
        //graph.DFSTravers(graph.nodes.get(1));
        //graph.DFSTravers(graph.nodes.get(1), graph.nodes.get(5));
    }

    public static void test2(){
        System.out.printf("\nTest2:\n");
        Graph graph = new Graph();
        //graph.DFSIterate(graph.nodes.get(1));
        //graph.DFSIterateTravers();
        graph.DFSRecursiveShortestPath( graph.nodes.get(1),graph.nodes.get(5));
        System.out.print("minTotalLength = " + graph.minTotalLength);
    }
}
