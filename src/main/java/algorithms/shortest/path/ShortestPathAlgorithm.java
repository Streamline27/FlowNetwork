package algorithms.shortest.path;

import graph.Arc;
import graph.Graph;
import graph.Node;

import java.util.*;
import java.util.stream.Collectors;

public class ShortestPathAlgorithm {

    private Graph graph;
    private String fromId, toId;
    private List<List<Arc>> paths;

    public ShortestPathAlgorithm(Graph graph, String fromId, String toId)
    {
        this.graph = graph;
        this.fromId = fromId;
        this.toId = toId;
        this.paths = new ArrayList<>();
    }

    public Path execute(){
        Node start = graph.getNode(fromId);

        Stack<Arc> tracedArcs   = new Stack<>();
        Stack<Node> tracedNodes = new Stack<>();
        findPaths(start, tracedArcs, tracedNodes);

        if (paths.isEmpty()) return new Path();

        List<Path> formattedPaths = getFormattedPaths(paths);
        return getShortestPath(formattedPaths);
    }


    public void findPaths(Node currentNode, Stack<Arc> tracedArcs, Stack<Node> tracedNodes){
        if (currentNode.getIdentifier().equals(toId)) savePath(tracedArcs);
        else {

            List<Arc> adjustedArcs = currentNode.getOutArcs();
            for (Arc arc : adjustedArcs){
                if (tracedNodes.contains(currentNode)) continue;
                if (arc.getFlow().equals(0))           continue;

                tracedNodes.push(currentNode);
                tracedArcs.push(arc);

                findPaths(arc.getTo(), tracedArcs, tracedNodes);

                tracedNodes.pop();
                tracedArcs.pop();
            }
        }
    }

    private void savePath(Stack<Arc> tracedPath) {
        List<Arc> path = new ArrayList<>(tracedPath);
        paths.add(path);
    }

    private Path getShortestPath(List<Path> formattedPaths) {
        return formattedPaths.stream().min((o1, o2) -> o1.getCapacity() - o2.getCapacity()).get();
    }


    private List<Path> getFormattedPaths(List<List<Arc>> p) {
        return p.stream().map(Path::new).collect(Collectors.toList());
    }


}
