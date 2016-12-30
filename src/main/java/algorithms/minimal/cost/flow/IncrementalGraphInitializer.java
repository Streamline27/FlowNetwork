package algorithms.minimal.cost.flow;

import graph.Arc;
import graph.DuplicateNodeIdentifierException;
import graph.Graph;
import graph.Node;

import static algorithms.AlgUtils.getFromId;
import static algorithms.AlgUtils.getToId;

public class IncrementalGraphInitializer {
    public static Graph execute(Graph initialGraph){

        Graph incrementalGraph = new Graph();

        initializeIncrementalGraphNodes(initialGraph, incrementalGraph);
        initializeIncrementalGraphArcs(initialGraph, incrementalGraph);
        return incrementalGraph;
    }

    private static void initializeIncrementalGraphArcs(Graph graph, Graph incrementalGraph) {
        for (Arc arc : graph.getArcs()){
            incrementalGraph.connect(getFromId(arc), getToId(arc), arc.getFlow(), arc.getCapacity());
            incrementalGraph.connect(getToId(arc), getFromId(arc), 0, -arc.getCapacity());
        }
    }

    private static void initializeIncrementalGraphNodes(Graph graph, Graph incrementalGraph) {
        try {
            for (Node node : graph.getNodes()) incrementalGraph.createNode(node.getIdentifier());
        } catch (DuplicateNodeIdentifierException e) {
            e.printStackTrace();
        }
    }
}
