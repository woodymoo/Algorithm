package Graph;



public class Path {
    Integer pathId;
    Node startNode;
    Node endNode;
    Double length;

    public Path(Integer pathId, Node startNode, Node endNode, Double length ) {
        this.pathId = pathId;
        this.startNode = startNode;
        this.endNode = endNode;
        this.length = length;

    }

    public Path(Integer pathId, Node startNode, Node endNode) {
        this.pathId = pathId;
        this.startNode = startNode;
        this.endNode = endNode;
    }
}
