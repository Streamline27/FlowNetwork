import algorithms.minimal.cost.flow.IterationResult;
import algorithms.minimal.cost.flow.MinimalCostFlowAlgorithm;
import algorithms.minimal.cost.flow.NoNeedToIterateException;
import graph.DuplicateNodeIdentifierException;
import graph.Graph;
import visualisation.GraphVisualiser;

import java.util.Scanner;

public class Console {

    public static void main(String[] args) throws DuplicateNodeIdentifierException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===================================================");
        System.out.println("===         Minimal cost flow algorithm         ===");
        System.out.println("===================================================");
        System.out.println("---------------------------------------------------");

        System.out.print("  1) Enter number of producers (a): ");
        int producerCount = scanner.nextInt();
        System.out.print("  2) Enter number of consumers (b): ");
        int consumerCount = scanner.nextInt();

        showSplittingLine();

        Graph g = new Graph();
        g.createNode("0");
        g.createNode("1");
        for (int a = 1; a <= producerCount; a++) g.createNode("a"+a);
        for (int b = 1; b <= consumerCount; b++) g.createNode("b"+b);

        showSplittingLine();
        System.out.println(" 3) Enter amount for every producer: ");
        for (int a = 1; a <= producerCount; a++) {
            System.out.print(" a"+a+": ");
            int amount = scanner.nextInt();
            g.connect("0", "a"+a, amount, 0);
        }

        showSplittingLine();
        System.out.println(" 4) Enter amount for every consumer: ");
        for (int b = 1; b <= consumerCount; b++) {
            System.out.print(" b"+b+": ");
            int amount = scanner.nextInt();
            g.connect("b"+b, "1", amount, 0);
        }

        showSplittingLine();
        System.out.println(" There will be a matrix "+producerCount+"x"+consumerCount);
        System.out.println(" 5) Fill in the costs in the cells: ");
        for (int a = 1; a <= producerCount; a++) {
            for (int b = 1; b <= consumerCount; b++) {

                System.out.print(" a"+a+"b"+b+": ");
                int cost = scanner.nextInt();
                g.connect("a"+a, "b"+b, Integer.MAX_VALUE, cost);

            }
        }

        MinimalCostFlowAlgorithm a = new MinimalCostFlowAlgorithm(g, "0", "1");
        GraphVisualiser visualiser = new GraphVisualiser(a.getFlowGraph());
        scanner.nextLine();

        try {
            int iterationNumber = 0;
            for (;;){
                visualiser.refresh();
                showSplittingLine();
                System.out.println(" Iteration no: "+iterationNumber);
                System.out.println(" Press enter to continue...");
                scanner.nextLine();
                a.iterate();
                iterationNumber ++;
            }
        } catch (NoNeedToIterateException e) {
            visualiser.refresh();
        }

        System.out.println("Minimal flow cost found: "+a.getCurrentFlow());
    }

    private static void showSplittingLine() {
        System.out.println();
        System.out.println("---------------------------------------------------");
        System.out.println();
    }
}
