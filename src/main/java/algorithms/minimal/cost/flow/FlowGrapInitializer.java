package algorithms.minimal.cost.flow;

import graph.Arc;
import graph.DuplicateNodeIdentifierException;
import graph.Graph;
import graph.Node;

import static algorithms.AlgUtils.getFromId;
import static algorithms.AlgUtils.getToId;

public class FlowGrapInitializer {
    public static Graph execute(Graph initialGraph){
        Graph flowGraph = new Graph();

        try {
            for (Node node : initialGraph.getNodes()) flowGraph.createNode(node.getIdentifier());
        } catch (DuplicateNodeIdentifierException e) {
            e.printStackTrace();
        }
        for (Arc arc : initialGraph.getArcs()) flowGraph.connect(getFromId(arc), getToId(arc), 0, 0);

        return flowGraph;
    }
}
