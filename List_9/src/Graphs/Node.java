package Graphs;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Jakub Szwedowicz
 * @version 1.0
 * date: 23.05.2021
 * email: kuba.szwedowicz@gmail.com
 */

class Node<T> implements Comparable<Node<T>> {
    double f;    // cost function
    double g;    // move function
    double h;   // heurestic
    private static int idCounter = 0;
    int id;
    T key;
    Set<Edge<T>> edges;
    Node<T> parent;

    Node(double h, T data) {
        f = Double.MAX_VALUE;
        g = Double.MAX_VALUE;
        this.h = h;
        this.id = idCounter++;
        this.key = data;
        this.edges = new HashSet<>();
        parent = null;
    }

    @Override
    public int compareTo(Node<T> node) {
        return Double.compare(f, node.f);
    }

    double computeHeuristic(Node<T> end) {
        return h;
    }

    void addBranch(int weight, Node<T> node) {
        Edge<T> newEdge = new Edge<>(this, node, weight);
        edges.add(newEdge);
    }

    Node<T> connectsWithNode(Node<T> node) {
        for (Edge<T> e : edges) {
            if (e.end == node) {
                return node;
            }
        }
        return null;
    }
}