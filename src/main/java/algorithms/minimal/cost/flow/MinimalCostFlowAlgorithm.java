package algorithms.minimal.cost.flow;

import algorithms.shortest.path.Path;
import algorithms.shortest.path.ShortestPathAlgorithm;
import graph.Arc;
import graph.Graph;

import java.util.Optional;

import static algorithms.AlgUtils.getFromId;
import static algorithms.AlgUtils.getToId;

public class MinimalCostFlowAlgorithm {

    private Graph flowGraph;
    private Graph incrementalGraph;

    private String fromId, toId;


    public MinimalCostFlowAlgorithm(Graph graph, String fromId, String toId) {

        incrementalGraph = IncrementalGraphInitializer.execute(graph);
        flowGraph        = FlowGrapInitializer.execute(graph);

        this.fromId = fromId;
        this.toId = toId;

    }

    public IterationResult iterate() throws NoNeedToIterateException {

        ShortestPathAlgorithm shortestPathAlgorithm = new ShortestPathAlgorithm(incrementalGraph, fromId, toId);
        Path path = shortestPathAlgorithm.execute();

        if (path.getArcs().isEmpty()) throw new NoNeedToIterateException("There is no more possible paths!");

        // Increment path in flowGraph
        Integer minFlowInPath = path.getMinFlow();

        for (Arc arc : path.getArcs()){

            if (arcIsStraight(arc)){
                Arc flowGraphArc = flowGraph.getArc(getFromId(arc), getToId(arc)).get();
                flowGraphArc.setFlow(flowGraphArc.getFlow() + minFlowInPath);
            }
            else { // If arc is reverse
                Arc flowGraphArc = flowGraph.getArc(getToId(arc), getFromId(arc)).get();
                flowGraphArc.setFlow(flowGraphArc.getFlow() - minFlowInPath);
            }
        }

        // Increment path in IncrementalGraph
        for (Arc arc : path.getArcs()){
            Arc incrementalGraphStraightArc = incrementalGraph.getArc(getFromId(arc), getToId(arc)).get();
            Arc incrementalGraphReverseArc  = incrementalGraph.getArc(getToId(arc), getFromId(arc)).get();

            incrementalGraphStraightArc.setFlow(incrementalGraphStraightArc.getFlow() - minFlowInPath);
            incrementalGraphReverseArc.setFlow(incrementalGraphReverseArc.getFlow()   + minFlowInPath);

        }

        return new IterationResult(incrementalGraph, flowGraph);
    }

    private boolean arcIsStraight(Arc arc) {
        Optional<Arc> flowGraphArcOptional = flowGraph.getArc(getFromId(arc), getToId(arc));
        return flowGraphArcOptional.isPresent();
    }

    public Graph getFlowGraph() {
        return flowGraph;
    }

    public Integer getCurrentFlow(){
        return flowGraph.getNode(toId).getInArcs().stream().mapToInt(arc -> arc.getFlow()).sum();
    }
}
