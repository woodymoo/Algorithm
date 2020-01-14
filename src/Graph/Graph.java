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
        this.paths.put(3, new Path(2, this.nodes.get(2), this.nodes.get(4)));
        this.paths.put(4, new Path(2, this.nodes.get(2), this.nodes.get(5)));

    }
    @Override
    public void DFSTravers(Graph graph, Node startNode) {
        //suedou code
        System.out.println( startNode.getNodeId().toString() + "\n");
        startNode.setVisited(true);
        Set<Map.Entry<Integer, Node>> entries = nodes.entrySet();
        for( Map.Entry<Integer, Node> entry : entries) {
            if (entry.getValue().getVisited() == false) {
                DFSTravers(graph, entry.getValue());
            }
        }
    }

    public void BFSTravers(Graph graph, Integer startNodeId){
        //suedou code

    }

    public static void main(String[] args) {
        System.out.printf("Test");
        Graph graph = new Graph();
        graph.DFSTravers(graph, graph.nodes.get(1));
    }
}
