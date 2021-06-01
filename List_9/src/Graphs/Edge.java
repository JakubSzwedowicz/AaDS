package Graphs;

import java.util.LinkedList;

/**
 * @author Jakub Szwedowicz
 * @version 1.0
 * date: 23.05.2021
 * email: kuba.szwedowicz@gmail.com
 */


class Edge<T> implements Comparable<T> {
    final Node<T> end;
    final Node<T> begin;
    int weight;
    Edge(Node<T> begin, Node<T> end, int weight){
        this.begin = begin;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Edge)){
            return false;
        }
        Edge<T> e = (Edge<T>) obj;
        return (((e.end.key == end.key) && (e.begin.key == begin.key)));
    }

    @Override
    public int compareTo(T o) {
        if(! (o instanceof Edge)){
            return -1;
        }
        Edge<T> e = (Edge<T>)o;
        return weight - e.weight;
    }


    // Very much needed!
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + weight * prime + begin.key.hashCode() * prime + end.key.hashCode() * prime;
        long temp = Double.doubleToLongBits(result);
        result = prime * result + (int)(temp ^ (temp >>> 32));
        return result;
    }
}
