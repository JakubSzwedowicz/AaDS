package AVL;

import Visitor.Visitor;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @author Jakub Szwedowicz
 * @version 1.0
 * date: 09.05.2021
 * email: kuba.szwedowicz@gmail.com
 */
public interface Tree<T> extends Iterable<T> {
    enum IterationMethod {
        PreOrder,
        InOrder,
        PostOrder
    }

    boolean insert(T e);

    boolean delete(T e);

    T upper(T e);

    T lower(T e);

    Node<T> getMinNode();

    Node<T> getMinNodeDownRoot(Node<T> root);

    Node<T> getMaxNode();

    Node<T> getMaxNodeDownRoot(Node<T> root);

    Node<T> getRoot();

    Comparator<? super T> getComparator();

    default void iteratePreOrder(Node<T> node, Consumer<? super T> cons) {
        if (node != null) {
            cons.accept(node.getData());
            iteratePreOrder(node.getLeft(), cons);
            iteratePreOrder(node.getRight(), cons);
        }
    }

    default void iterateInOrder(Node<T> node, Consumer<? super T> cons) {
        if (node != null) {
            iterateInOrder(node.getLeft(), cons);
            cons.accept(node.getData());
            iterateInOrder(node.getRight(), cons);
        }
    }

    default void iteratePostOrder(Node<T> node, Consumer<? super T> cons) {
        if (node == null) {
            return;
        }
        iteratePostOrder(node.getLeft(), cons);
        iteratePostOrder(node.getRight(), cons);
        cons.accept(node.getData());
    }

    default void iterateOver(IterationMethod method, Consumer<? super T> cons) {
        switch (method) {
            case InOrder -> iterateInOrder(getRoot(), cons);
            case PostOrder -> iteratePostOrder(getRoot(), cons);
            case PreOrder -> iteratePreOrder(getRoot(), cons);
        }
    }

    default void iterateOver(IterationMethod method, Node<T> node, Consumer<? super T> cons) {
        switch (method) {
            case InOrder -> iterateInOrder(node, cons);
            case PostOrder -> iteratePostOrder(node, cons);
            case PreOrder -> iteratePreOrder(node, cons);
        }
    }


}
