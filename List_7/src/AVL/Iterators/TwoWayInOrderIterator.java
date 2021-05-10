package AVL.Iterators;

import AVL.Node;
import AVL.Tree;

import java.util.function.Consumer;

/**
 * @author Jakub Szwedowicz
 * @version 1.0
 * date: 09.05.2021
 * email: kuba.szwedowicz@gmail.com
 */
public class TwoWayInOrderIterator<T> implements ITwoWayIterator<T> {
    private Node<T> currNode;
    private Node<T> nextNode;
    private Node<T> prevNode;
    private final Tree<T> tree;

    public TwoWayInOrderIterator(Tree<T> tree, Node<T> node) {
        this.tree = tree;
        currNode = node;
        nextNode = nextNode();
        prevNode = prevNode();
    }

    public TwoWayInOrderIterator(Tree<T> tree) {
        this.tree = tree;
        Node<T> min = tree.getMinNode();
        currNode = min;
        nextNode = nextNode();
        prevNode = prevNode();
    }

    @Override
    public boolean hasNext() {
        return currNode != null;
    }

    @Override
    public boolean hasPrev() {
        return prevNode != null;
    }

    @Override
    public T next() {
        T res = currNode.getData();
        prevNode = currNode;
        currNode = nextNode;
        nextNode = nextNode();
        return res;
    }

    @Override
    public T prev() {
        T res = prevNode.getData();
        nextNode = currNode;
        currNode = prevNode;
        prevNode = prevNode();
        return res;
    }

    @Override
    public Node<T> prevNode() {
        if (currNode == null) {
            // Or maybe exception?
            return null;
        }
        Node<T> next = currNode.getParent();
        if (next == null) {
            // Case for the 'e' being root
            return tree.getMaxNodeDownRoot(currNode.getLeft());
        }
        if (currNode == next.getLeft()) {
            Node<T> maxRightSubTree = tree.getMaxNodeDownRoot(currNode.getLeft());
            if (maxRightSubTree != null) {
                return maxRightSubTree;
            }
            Node<T> parent = next.getParent();
            while (parent != null && next == parent.getLeft()) {
                next = parent;
                parent = parent.getParent();
            }
            return parent;

        } else {
            Node<T> maxRightSubTree = tree.getMaxNodeDownRoot(currNode.getLeft());
            if (maxRightSubTree != null) {
                next = maxRightSubTree;
            }
            return next;
        }
    }

    @Override
    public Node<T> nextNode() {
        if (currNode == null) {
            // Or maybe exception?
            return null;
        }
        Node<T> next = currNode.getParent();
        if (next == null) {
            // Case for the 'e' being root
            return tree.getMinNodeDownRoot(currNode.getRight());
        }
        if (currNode == next.getLeft()) {
            Node<T> minRightSubTree = tree.getMinNodeDownRoot(currNode.getRight());
            if (minRightSubTree != null) {
                next = minRightSubTree;
            }
            return next;
        } else {
            Node<T> minRightSubTree = tree.getMinNodeDownRoot(currNode.getRight());
            if (minRightSubTree != null) {
                return minRightSubTree;
            }
            Node<T> parent = next.getParent();
            while (parent != null && next == parent.getRight()) {
                next = parent;
                parent = parent.getParent();
            }
            return parent;
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void forEachRemaining(Consumer<? super T> action) {
        while(currNode != null){
            action.accept(next());
        }
    }
}
