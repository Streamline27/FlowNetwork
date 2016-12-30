package graph;

import java.util.*;
import java.util.function.Predicate;

public class Graph {

    private List<Node> nodes;
    private List<Arc> arcs;
    private Map<String, Node> nodesByIds;

    /*
        Constructor
        */
    public Graph() {
        arcs = new ArrayList<Arc>();
        nodes = new ArrayList<Node>();
        nodesByIds = new HashMap<String, Node>();
    }

    /*
        Implemented functionality
        */
    public void createNode(String nodeIdentifier) throws DuplicateNodeIdentifierException {
        if (nodesByIds.containsKey(nodeIdentifier)) throw new DuplicateNodeIdentifierException();

        Node node = new Node(nodeIdentifier);
        nodesByIds.put(nodeIdentifier, node);
        nodes.add(node);
    }

    public Node getNode(String identifier){

        if (!nodesByIds.containsKey(identifier)) throw new IllegalArgumentException();
        return nodesByIds.get(identifier);
    }

    public List<Node> getNodes(){
        return nodes;
    }



    public void connect(String fromId, String toId, Integer capacity){
        connect(fromId, toId, 0, capacity);
    }

    public void connect(String fromId, String toId, Integer flow, Integer capacity){
        Node from = nodesByIds.get(fromId);
        Node to   = nodesByIds.get(toId);

        Arc arc = new Arc(capacity, flow);
        arc.setTo(to);
        arc.setFrom(from);

        from.addOutArc(arc);
        to.addInArc(arc);

        arcs.add(arc);
    }

    public Optional<Arc> getArc(String fromId, String toId){
        if (!(nodesByIds.containsKey(fromId)&&nodesByIds.containsKey(toId))) throw new IllegalArgumentException();

        Node from = nodesByIds.get(fromId);
        Node to   = nodesByIds.get(toId);
        Optional<Arc> fromTo  = from.getOutArcs().stream().filter(arcDestinationIs(to)).findFirst();;

        return fromTo;
    }

    /*
        Getters and Setters
        */
    public List<Arc> getArcs() {
        return arcs;
    }

    /*
        Private helper methods
        */
    private Predicate<Arc> arcDestinationIs(Node node){
        return arc -> arc.getTo().equals(node);
    }

}
