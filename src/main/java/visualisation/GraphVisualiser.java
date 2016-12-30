package visualisation;

import graph.Arc;
import graph.Graph;
import graph.Node;
import org.graphstream.graph.Edge;
import org.graphstream.graph.implementations.SingleGraph;

import static algorithms.AlgUtils.getFromId;
import static algorithms.AlgUtils.getToId;

public class GraphVisualiser {
    private Graph graph;
    private SingleGraph uiGraph;


    public GraphVisualiser(Graph graph) {

        uiGraph = new SingleGraph("Flow network");
        this.graph = graph;

        for (Node node : graph.getNodes()){
            org.graphstream.graph.Node n = uiGraph.addNode(node.getIdentifier());
            n.setAttribute("ui.style", "text-color: red; text-size: 18;");
            n.setAttribute("ui.label", node.getIdentifier());
        }

        for (Arc arc : graph.getArcs()){
            Edge e = uiGraph.addEdge(getFromId(arc)+getToId(arc), getFromId(arc), getToId(arc), true);
            e.setAttribute("ui.style", "text-size: 16;");
            e.setAttribute("ui.label", arc.getFlow());
        }

        uiGraph.display();
    }

    public void refresh(){
        for (Arc arc : graph.getArcs()){
            Edge e = uiGraph.getEdge(getFromId(arc)+getToId(arc));
            e.setAttribute("ui.label", arc.getFlow());
        }
    }
}
