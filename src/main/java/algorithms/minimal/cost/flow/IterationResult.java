package algorithms.minimal.cost.flow;

import graph.Graph;

public class IterationResult {
    private Graph incrementalGraph;
    private Graph flowGraph;

    public IterationResult(Graph incrementalGraph, Graph flowGraph) {
        this.incrementalGraph = incrementalGraph;
        this.flowGraph = flowGraph;
    }

    public Graph getIncrementalGraph() {
        return incrementalGraph;
    }

    public void setIncrementalGraph(Graph incrementalGraph) {
        this.incrementalGraph = incrementalGraph;
    }

    public Graph getFlowGraph() {
        return flowGraph;
    }

    public void setFlowGraph(Graph flowGraph) {
        this.flowGraph = flowGraph;
    }
}

