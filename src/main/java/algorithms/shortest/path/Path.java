package algorithms.shortest.path;

import graph.Arc;
import graph.Node;

import java.util.ArrayList;
import java.util.List;

import static algorithms.AlgUtils.getFromId;
import static algorithms.AlgUtils.getToId;

public class Path {

    private List<Arc> arcs;
    private Integer capacity;
    private Integer minFlow = Integer.MAX_VALUE;

    /*
        Constructor
        */

    public Path() {
        arcs = new ArrayList<Arc>();
        capacity = 0;
    }

    public Path(List<Arc> pathArcs) {
        this.arcs = new ArrayList<Arc>();
        capacity = 0;
        for (Arc arc : pathArcs) addArc(arc);
    }



    /*
        Implemented functionality
        */

    public void addArc(Arc arc) {
        if (!arcs.isEmpty()) validateArc(arc);

        arcs.add(arc);
        capacity+=arc.getCapacity();

        if (Math.abs(arc.getFlow())<minFlow) minFlow = Math.abs(arc.getFlow());
    }


    public Node from(){
        if (arcs.isEmpty()) throw new IllegalStateException("Can not get FROM from empty path");
        return getFirstArc().getFrom();
    }


    public Node to(){
        if (arcs.isEmpty()) throw new IllegalStateException("Can not get TO from empty path");
        return getLastArc().getTo();
    }

    /*
        Getters and setters
        */

    public List<Arc> getArcs(){
        return new ArrayList<Arc>(arcs);
    }

    public Integer getCapacity() {
        return capacity;
    }

    /*
        Private helper methods
        */

    private Arc getFirstArc() {
        return arcs.get(0);
    }

    private void validateArc(Arc arc){
        Arc lastArc = getLastArc();
        if (areNotAdjusted(arc, lastArc)) throw new IllegalArgumentException();
    }

    private Arc getLastArc() {
        return arcs.get(arcs.size()-1);
    }

    private boolean areNotAdjusted(Arc arc, Arc lastArc) {
        return !getToId(lastArc).equals(getFromId(arc));
    }

    public Integer getMinFlow() {
        return minFlow;
    }
}
