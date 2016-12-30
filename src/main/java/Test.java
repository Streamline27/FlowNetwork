import algorithms.minimal.cost.flow.IterationResult;
import algorithms.minimal.cost.flow.MinimalCostFlowAlgorithm;
import algorithms.minimal.cost.flow.NoNeedToIterateException;
import graph.DuplicateNodeIdentifierException;
import graph.Graph;

public class Test {

    public static void main(String[] args) throws DuplicateNodeIdentifierException {
        Graph g = new Graph();

        createNodes(g);

        g.connect("0", "a1", 60, 0);
        g.connect("0", "a2", 50, 0);
        g.connect("0", "a3", 70, 0);

        g.connect("b1", "1", 25, 0);
        g.connect("b2", "1", 33, 0);
        g.connect("b3", "1", 90, 0);
        g.connect("b4", "1", 10, 0);

        g.connect("a1", "b1", Integer.MAX_VALUE, 10);
        g.connect("a1", "b2", Integer.MAX_VALUE, 11);
        g.connect("a1", "b3", Integer.MAX_VALUE, 18);
        g.connect("a1", "b4", Integer.MAX_VALUE, 32);

        g.connect("a2", "b1", Integer.MAX_VALUE, 16);
        g.connect("a2", "b2", Integer.MAX_VALUE, 14);
        g.connect("a2", "b3", Integer.MAX_VALUE, 20);
        g.connect("a2", "b4", Integer.MAX_VALUE, 25);

        g.connect("a3", "b1", Integer.MAX_VALUE, 26);
        g.connect("a3", "b2", Integer.MAX_VALUE, 28);
        g.connect("a3", "b3", Integer.MAX_VALUE, 22);
        g.connect("a3", "b4", Integer.MAX_VALUE, 30);


        MinimalCostFlowAlgorithm a = new MinimalCostFlowAlgorithm(g, "0", "1");

        IterationResult iterationResult = null;
        try {
            for (;;) iterationResult = a.iterate();
        } catch (NoNeedToIterateException e) {
            e.printStackTrace();
        }


        System.out.println("Done");
    }

    private static void createNodes(Graph g) throws DuplicateNodeIdentifierException {
        g.createNode("0");
        g.createNode("1");

        g.createNode("a1");
        g.createNode("a2");
        g.createNode("a3");

        g.createNode("b1");
        g.createNode("b2");
        g.createNode("b3");
        g.createNode("b4");
    }
}
