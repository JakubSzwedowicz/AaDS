package Graphs;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Jakub Szwedowicz
 * @version 1.0
 * date: 23.05.2021
 * email: kuba.szwedowicz@gmail.com
 */
public class Graph<T> implements IGraph<T> {
    // MEMBERS
    private int noNodes = 0;
    private int noEdges = 0;
    private final Map<T, Node<T>> nodes;

    // PUBLIC
    public Graph() {
        nodes = new HashMap<>();
    }

    @Override
    public boolean addNode(T data) {
        Node<T> ret = nodes.put(data, new Node<>(5, data));
        if (ret == null) {
            noNodes++;
        }
        return ret == null;
    }

    @Override
    public boolean addEdge(T src, T dst) {
        return addEdge(src, dst, 0);
    }

    @Override
    public boolean addEdge(T src, T dst, int weight) {
        if (weight < 0) {
            return false;
        }
        Node<T> srcNode = nodes.get(src);
        Node<T> dstNode = nodes.get(dst);
        if ((srcNode != null) && (dstNode != null)) {
            Set<Edge<T>> srcEdges = srcNode.edges;
            Edge<T> newEdge = new Edge<>(srcNode, dstNode, weight);
            if (srcEdges.add(newEdge)) {
                Set<Edge<T>> dstEdges = dstNode.edges;
                dstEdges.add(new Edge<>(dstNode, srcNode, weight));
                noEdges++;
                return true;
            }
        }
        return false;
    }

    @Override
    public Collection<Edge<T>> getEdges(T key) {
        Node<T> node = nodes.get(key);
        if (node == null) {
            return null;
        }
        return node.edges;
    }

    @Override
    public int getNumberOfNodes() {
        return noNodes;
    }

    @Override
    public int getNumberOfEdges() {
        return noEdges;
    }

    @Override
    public int getSumOfWeights() {
        int res = 0;
        Map<Edge<T>, Boolean> visited = new HashMap<>(noEdges);
        for (Map.Entry<T, Node<T>> pair : nodes.entrySet()) {
                    res += DFSHelper(visited, pair.getValue().edges);
            }
        // Because each edge is doubled - the graph is somewhat ready be directed one. MST algorithm would have to change
        return res / 2;
    }

    private int DFSHelper(Map<Edge<T>, Boolean> visited, Set<Edge<T>> edges) {
        int res = 0;
        for (Edge<T> n : edges) {
            if (visited.get(n) == null || !visited.get(n)) {
                visited.put(n, true);
                res += n.weight;
            }
        }
        return res;
    }

    @Override
    public boolean hasEdge(T src, T dst) {
        Node<T> node = nodes.get(src);
        if (node == null) {
            return false;
        }
        return node.connectsWithNode(nodes.get(dst)) != null;
    }

    @Override
    public boolean hasNode(T node) {
        return nodes.containsKey(node);
    }

    @Override
    // Returns minimum weight spanning tree
    // internally uses Prim's algorithm
    public IGraph<T> getMST() {
        Graph<T> graph = new Graph<>();
        if (noNodes == 0) {
            return graph;
        }
        int stillToAdd = noNodes;
        PriorityQueue<Edge<T>> edges = new PriorityQueue<>();

        // Just to obtain the first element
        Map.Entry<T, Node<T>> first = nodes.entrySet().iterator().next();
        graph.addNode(first.getKey());
        edges.addAll(first.getValue().edges);
        stillToAdd--;
        while (stillToAdd != 0) {
            Edge<T> shortest = null;
            T endPoint = null;
            while ((shortest == null) && !(edges.isEmpty())) {
                shortest = edges.poll();
                endPoint = shortest.end.key;
                if (graph.hasNode(endPoint)) {
                    shortest = null;
                }
            }
            if (shortest == null) {
                // If are still nodes to be added (stillToAdd != 0) and
                // the next node couldn't be found then it must be disconnected
                // Disconnected graph!
                return null;
            }
            edges.addAll(nodes.get(endPoint).edges);
            graph.addNode(endPoint);
            graph.addEdge(shortest.begin.key, endPoint, shortest.weight);
            stillToAdd--;
        }
        return graph;
    }

    @Override
    public boolean contains(Object o) {
        if (!(o instanceof Graph)) {
            return false;
        }
        Graph<T> g = (Graph<T>) o;
        for (Map.Entry<T, Node<T>> pair : g.nodes.entrySet()) {
            Set<Edge<T>> edges = nodes.get(pair.getKey()).edges;
            if (edges == null || !edges.containsAll(pair.getValue().edges)) {
                return false;
            }
        }
        return true;
    }

    @Override
    // #Uses A*
    public IGraph<T> getSPW(T start, T end) {
        if (noNodes == 0) {
            return null;
        }
        Node<T> startNode = nodes.get(start);
        Node<T> endNode = nodes.get(end);
        // Fast solution but should be improved
        for(Node<T> n : nodes.values()){
            n.parent = null;
        }
        if ((startNode != null) && (endNode != null)) {
            Node<T> result = AStarAlgorithm(startNode, endNode);
            if (result == null) {
                return null;
            }
            Graph<T> graph = new Graph<>();
            graph.addNode(result.key);
            Node<T> parent = result.parent;
            while (parent != null) {
                for (Edge<T> edge : result.edges) {
                    if (edge.end == parent) {
                        graph.addNode(parent.key);
                        graph.addEdge(result.key, parent.key, edge.weight);
                        result.parent = null;
                        result = parent;
                        break;
                    }
                }
                parent = parent.parent;
            }
            return graph;
        }
        return null;
    }

    private Node<T> AStarAlgorithm(Node<T> start, Node<T> end) {
        Queue<Node<T>> open = new PriorityQueue<>();
        Queue<Node<T>> closed = new PriorityQueue<>();

        start.g = 0;
        start.f = start.g + start.computeHeuristic(end);
        open.add(start);

        while (!open.isEmpty()) {
            Node<T> node = open.peek();
            if (node == end) {
                return node;
            }

            for (Edge<T> edge : node.edges) {
                Node<T> nextNode = edge.end;
                double cost = node.g + edge.weight;

                if (!open.contains(nextNode) && !closed.contains(nextNode)) {
                    nextNode.parent = node;
                    nextNode.g = cost;
                    nextNode.f = nextNode.g + nextNode.computeHeuristic(end);
                    open.add(nextNode);
                } else {
                    if (cost < nextNode.g) {
                        nextNode.parent = node;
                        nextNode.g = cost;
                        nextNode.f = nextNode.g + nextNode.computeHeuristic(end);

                        if (closed.contains(nextNode)) {
                            closed.remove(nextNode);
                            open.add(nextNode);
                        }
                    }
                }
            }
            open.remove(node);
            closed.add(node);
        }
        return null;
    }


    // PRIVATE
    private Edge<T> findEdge(Set<Edge<T>> edges, Edge<T> edge) {
        for (Edge<T> e : edges) {
            if (e.equals(edge)) {
                return e;
            }
        }
        return null;
    }

}
