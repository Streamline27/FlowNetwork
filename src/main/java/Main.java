import algorithms.minimal.cost.flow.NoNeedToIterateException;
import algorithms.minimal.cost.flow.IterationResult;
import algorithms.minimal.cost.flow.MinimalCostFlowAlgorithm;
import algorithms.shortest.path.IllegalPathArcException;
import graph.DuplicateNodeIdentifierException;
import graph.Graph;
import visualisation.GraphVisualiser;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws DuplicateNodeIdentifierException, IllegalPathArcException {
        System.out.println("Hello World");

        Graph g = new Graph();

        for (Integer i = 0; i < 9; i++) {
            g.createNode(i.toString());
        }

        g.connect("0", "1", 4, 3);
        g.connect("0", "2", 8, 1);
        g.connect("2", "1", 3, 1);
        g.connect("1", "3", 2, 1);
        g.connect("2", "3", 10, 5);

        MinimalCostFlowAlgorithm a = new MinimalCostFlowAlgorithm(g, "0", "3");
        GraphVisualiser visualiser = new GraphVisualiser(a.getFlowGraph());

        Scanner s = new Scanner(System.in);
        IterationResult iterationResult = null;
        try {
            for (;;){
                iterationResult = a.iterate();
                visualiser.refresh();
                s.next();
            }
        } catch (NoNeedToIterateException e) {
            e.printStackTrace();
        }
        finally {
            visualiser.refresh();
        }


        System.out.println("Done");
    }
}
