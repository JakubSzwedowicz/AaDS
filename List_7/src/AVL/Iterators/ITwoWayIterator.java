package AVL.Iterators;

import AVL.Node;

import java.util.Iterator;

/**
 * @author Jakub Szwedowicz
 * @version 1.0
 * date: 09.05.2021
 * email: kuba.szwedowicz@gmail.com
 */
public interface ITwoWayIterator<T> extends Iterator<T> {

    T prev();
    boolean hasPrev();
    Node<T> prevNode();
    Node<T> nextNode();
}
