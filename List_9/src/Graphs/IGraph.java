package Graphs;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Jakub Szwedowicz
 * @version 1.0
 * date: 23.05.2021
 * email: kuba.szwedowicz@gmail.com
 */
public interface IGraph<T> {
    boolean addNode(T node);
    boolean addEdge(T src, T dst);
    boolean addEdge(T src, T dst, int weight);
    Collection<Edge<T>> getEdges(T v);
    int getNumberOfNodes();
    int getNumberOfEdges();
    int getSumOfWeights();
    boolean hasEdge(T src, T dst);
    boolean hasNode(T node);
    boolean contains(Object o);
    IGraph<T> getMST();
    IGraph<T> getSPW(T start, T end);
}
