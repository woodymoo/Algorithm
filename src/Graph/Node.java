package Graph;

class Node {
    private Integer nodeId;
    private Boolean isVisited;

    public Node(Integer nodeId){
        this.setNodeId(nodeId);
        this.setVisited(false);
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public Boolean getVisited() {
        return isVisited;
    }

    public void setVisited(Boolean visited) {
        isVisited = visited;
    }
}
