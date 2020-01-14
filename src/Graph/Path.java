package Graph;

public class Path {
    Integer pathId;
    Node startNode;
    Node endNode;

    public Path(Integer pathId, Node startNode, Node endNode) {
        this.pathId = pathId;
        this.startNode = startNode;
        this.endNode = endNode;
    }
}
