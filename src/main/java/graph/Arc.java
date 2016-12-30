package graph;

public class Arc {

    private Node from;
    private Node to;

    private Integer capacity;
    private Integer flow;

    public Arc(Integer flow) {
        this.flow = flow;
        this.capacity = 0;
    }

    public Arc(Integer capacity, Integer flow) {
        this.capacity = capacity;
        this.flow = flow;
    }

    public Node getFrom() {
        return from;
    }

    public Node getTo() {
        return to;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Integer getFlow() {
        return flow;
    }

    public void setFlow(Integer flow) {
        this.flow = flow;
    }

    public void setFrom(Node from) {
        this.from = from;
    }

    public void setTo(Node to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return from.getIdentifier() + " - "+to.getIdentifier();
    }
}
