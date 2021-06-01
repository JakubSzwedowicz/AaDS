import Graphs.Graph;
import Graphs.IGraph;

import java.util.PriorityQueue;

/**
 * @author Jakub Szwedowicz
 * @version 1.0
 * date: 31.05.2021
 * email: kuba.szwedowicz@gmail.com
 */
public class Main {
    public static void main(String[] args){

        IGraph<Integer> graph = new Graph<>();
        graph.addNode(5);
        graph.addNode(7);
        graph.addNode(3);
        graph.addNode(8);
        graph.addNode(9);

        graph.addEdge(5, 7, 2);
        graph.addEdge(5, 3, 6);
        graph.addEdge(3, 7, 2);
        graph.addEdge(8, 7, 9);
        graph.addEdge(8, 3, 5);
        graph.addEdge(9, 5, 5);
        graph.addEdge(9, 8, 3);

        IGraph<Integer> graph1 = graph.getMST();
        IGraph<Integer> graph2 = graph.getSPW(9, 7);
        System.out.println("Derp");
    }
}
