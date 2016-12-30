import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.Scanner;

public class GraphStreamTest {

    public static void main(String[] args) {
        Graph graph = new SingleGraph("Tutorial 1");



        graph.addNode("A" );
        graph.addNode("B" );
        graph.addNode("C" );
        graph.addEdge("AB", "A", "B", true);
        graph.addEdge("BC", "B", "C", true);
        graph.addEdge("CA", "C", "A", true);


        graph.getEdge("AB").setAttribute("ui.style", "text-size: 16;");
        graph.getEdge("AB").setAttribute("ui.label", 5);

        graph.display();

        Scanner s = new Scanner(System.in);
        s.next();

        graph.getEdge("AB").setAttribute("ui.label", 25);


    }
}
