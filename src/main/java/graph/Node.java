package graph;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private String identifier;
    private List<Arc> in;
    private List<Arc> out;

    public Node(String identifier) {
        in = new ArrayList<Arc>();
        out = new ArrayList<Arc>();

        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void addInArc(Arc arc){
        arc.setTo(this);
        in.add(arc);
    }

    public void addOutArc(Arc arc){
        arc.setFrom(this);
        out.add(arc);
    }

    public List<Arc> getOutArcs(){
        return out;
    }

    public List<Arc> getInArcs(){
        return in;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Node){
            Node anotherNode = (Node) obj;
            if (anotherNode.getIdentifier().equals(this.identifier)) return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.identifier.hashCode();
    }

    @Override
    public String toString() {
        return identifier;
    }
}
