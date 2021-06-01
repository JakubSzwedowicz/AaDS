package Graphs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import java.awt.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Jakub Szwedowicz
 * @version 1.0
 * date: 31.05.2021
 * email: kuba.szwedowicz@gmail.com
 */
class GraphTest {
    private Graph<Integer> graph;
    private List<List<Integer>> params;
    @BeforeEach
    void setUp() {
        graph = new Graph<>();
        params = List.of(List.of(5, 7, 2), List.of(5, 3, 6), List.of(9, 5, 5));
        graph.addNode(5);
        graph.addNode(7);
        graph.addNode(3);
        graph.addNode(8);
        graph.addNode(9);

        graph.addEdge(3, 7, 2);
        graph.addEdge(8, 7, 9);
        for(List<Integer> l : params){
            graph.addEdge(l.get(0), l.get(1), l.get(2));
        }
        graph.addEdge(8, 3, 4);
        graph.addEdge(9, 8, 3);
    }

    @Test
    void testAddNode() {
        int size = graph.getNumberOfNodes();
        assertFalse(graph.addNode(5));
        Assertions.assertEquals(size, graph.getNumberOfNodes());
        assertFalse(graph.addNode(7));
        Assertions.assertEquals(size, graph.getNumberOfNodes());
        assertTrue(graph.addNode(12));
        Assertions.assertEquals(size + 1, graph.getNumberOfNodes());
    }

    @Test
    void testAddEdge() {
        int size = graph.getNumberOfEdges();
        assertFalse(graph.addEdge(9, 8, 3));
        Assertions.assertEquals(size, graph.getNumberOfEdges());
        assertTrue(graph.addEdge(5, 8, 2));
        Assertions.assertEquals(size + 1, graph.getNumberOfEdges());
    }

    @Test
    void testGetEdges() {
        for(Edge<Integer> e : graph.getEdges(params.get(0).get(0))){
            boolean res = (params.contains(List.of(e.begin.key, e.end.key, e.weight)));
            if(!res){
                assertTrue(params.contains(List.of(e.end.key, e.begin.key, e.weight)));
            }
        }
    }

    @Test
    void hasEdge() {
        for(List<Integer> l : params){
            assertTrue(graph.hasEdge(l.get(0), l.get(1)));
            assertTrue(graph.hasEdge(l.get(1), l.get(0)));
        }
        assertFalse(graph.hasEdge(10, 5));
        assertFalse(graph.hasEdge(7, 9));
    }

    @Test
    void hasNode() {
        for(List<Integer> l : params){
            assertTrue(graph.hasNode(l.get(0)));
            assertTrue(graph.hasNode(l.get(1)));
        }
        assertFalse(graph.hasNode(10));
        assertTrue(graph.hasNode(8));
    }

    @Test
    void getMST() {
        IGraph<Integer> MSTGraph = graph.getMST();
        assertTrue(graph.contains(MSTGraph));
        assertFalse(MSTGraph.contains(graph));
        Assertions.assertEquals(11, MSTGraph.getSumOfWeights());
    }

    @Test
    void getSPW() {
        IGraph<Integer> SPWGraph = graph.getSPW(5, 9);
        Assertions.assertEquals(5, SPWGraph.getSumOfWeights());
        SPWGraph = graph.getSPW(7, 9);
        Assertions.assertEquals(7, SPWGraph.getSumOfWeights());
    }
}