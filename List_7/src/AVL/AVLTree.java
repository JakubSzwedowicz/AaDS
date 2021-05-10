package AVL;

import AVL.Iterators.ITwoWayIterator;
import AVL.Iterators.TwoWayInOrderIterator;
import Visitor.InOrderVisitor;

import java.util.*;
import java.util.function.Consumer;

/**
 * @author Jakub Szwedowicz
 * @version 1.0
 * date: 09.05.2021
 * email: kuba.szwedowicz@gmail.com
 */
public class AVLTree<T> implements Tree<T> {

    // MEMBERS
    private Node<T> root;
    private final Comparator<? super T> comp;

    // PUBLIC
    // Constructors
    public AVLTree(Comparator<? super T> comp) {
        this.comp = comp;
        root = null;
    }

    public AVLTree(List<? extends T> list) {
        if (list.size() > 0 && !(list.get(0) instanceof Comparable)) {
            throw new ClassCastException("Objects given in List are not comparable!");
        }
        comp = null;
        root = null;
        addAll(list);
    }


    @Override
    @SuppressWarnings("unchecked")
    public boolean insert(T e) {
        if (root == null) {
            addToEmpty(e);
            return true;
        }
        Node<T> parent = getParentForLeaf(e);
        if (parent == null) {
            return false;
        }
        Comparable<? super T> c = getComparableTo(e);
        Node<T> newNode = new Node<>(e);
        if (c.compareTo(parent.getData()) == 1) {
            parent.setRight(newNode);
        } else {
            parent.setLeft(newNode);
        }
        newNode.setParent(parent);
        return true;
    }

    @Override
    public boolean delete(T e) {
        Node<T> node = findNode(e);
        return delete(node);
    }

    private boolean delete(Node<T> node) {
        if (node == null) {
            return false;
        }
        Node<T> parent = node.getParent();
        Node<T> left = node.getLeft();
        Node<T> right = node.getRight();
        Node<T> theOnlyChild = null;
        if (left == null) {
            theOnlyChild = node.getRight();
        } else if (right == null) {
            theOnlyChild = node.getLeft();
        }

        // Case if node has at most 1 child, might have none then theOnlyChild = null
        if (left == null || right == null) {
            if (parent.getLeft() == node) {
                parent.setLeft(theOnlyChild);
            } else {
                parent.setRight(theOnlyChild);
            }
            if (theOnlyChild != null) {
                theOnlyChild.setParent(parent);
            }
            node.setRight(null);
            node.setParent(null);
            return true;
        }
        Node<T> succ = getMinNodeDownRoot(node.getRight());
        node.swapData(succ);
        delete(succ);
        return true;
//        if (parent.getLeft() == node) {
//            if (left == null) {
//                parent.setLeft(right);
//                right.setParent(parent);
//                return true;
//            } else if (right == null) {
//                parent.setLeft(left);
//                left.setParent(parent);
//                return true;
//            }
//            Node<T> succ = getMinNodeDownRoot(node.getRight());
//            node.swapData(succ);
//            delete(succ);
//        } else {
//
//        }
    }

    @Override
    public T upper(T e) {
        ITwoWayIterator<T> it = iterator();
        T value = it.next();
        Comparable<? super T> c = getComparableTo(e);
        int rel = c.compareTo(value);
        if (rel == 0) {
            return value;
        } else if (rel == 1) {
            value = findGreaterOrEqualThan(it, c);
            return value;
        } else {
            value = findSmallerOrEqualThan(it, c);
            rel = c.compareTo(value);
            if (rel == 1) {
                it.next();
                return it.next();
            }
            return value;
        }
    }

    @Override
    public T lower(T e) {
        ITwoWayIterator<T> it = iterator();
        T value = it.next();
        Comparable<? super T> c = getComparableTo(e);
        int rel = c.compareTo(value);
        if (rel == 0) {
            return value;
        } else if (rel == 1) {
            value = findGreaterOrEqualThan(it, c);
            rel = c.compareTo(value);
            if (rel == -1) {
                it.prev();
                value = it.prev();
            }
            return value;
        } else {
            value = findSmallerOrEqualThan(it, c);
            return value;
        }
    }

    @Override
    public Node<T> getMinNode() {
        return getMinNodeDownRoot(root);
    }

    @Override
    public Node<T> getMinNodeDownRoot(Node<T> root) {
        if (root == null) {
            return null;
        }
        Node<T> n = root;
        Node<T> next = n.getLeft();
        while (next != null) {
            n = next;
            next = next.getLeft();
        }
        return n;
    }

    @Override
    public Node<T> getMaxNode() {
        return getMaxNodeDownRoot(root);
    }

    @Override
    public Node<T> getMaxNodeDownRoot(Node<T> root) {
        if (root == null) {
            return null;
        }
        Node<T> n = root;
        Node<T> next = n.getRight();
        while (next != null) {
            n = next;
            next = next.getRight();
        }
        return n;
    }

    @Override
    public Node<T> getRoot() {
        return root;
    }

    @Override
    public Comparator<? super T> getComparator() {
        return comp;
    }

    public void addAll(Collection<? extends T> coll) {
        for (T t : coll) {
            insert(t);
        }
    }

    public boolean contains(T e) {
        Node<T> node = findNode(e);
        return node != null;
    }

    public static void testClass() {
        AVLTree<Integer> t = new AVLTree<Integer>(Arrays.asList(25, 15, 10, 4, 12, 22, 18, 24, 50, 35, 31, 44, 70, 66, 90));
        Node<Integer> n = t.root.getLeft().getLeft().getLeft();
        ITwoWayIterator<Integer> it = t.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println("\n");
        while (it.hasPrev()) {
            System.out.println(it.prev());
        }
        System.out.println("\n");
        System.out.println(t.lower(33));
        System.out.println(t.upper(37));
        System.out.println("\n");
        System.out.println(t.findNode(4) != null);
        System.out.println(t.findNode(12) != null);
        System.out.println(t.findNode(22) != null);
        System.out.println(t.findNode(23) != null);

        System.out.println("\n");

        t.iterateOver(IterationMethod.InOrder, e -> System.out.print(e + " "));
        System.out.println("");
        InOrderVisitor in = new InOrderVisitor();
    }

    @Override
    public ITwoWayIterator<T> iterator() {
        return new TwoWayInOrderIterator<T>(this);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Tree.super.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return Tree.super.spliterator();
    }

    @SuppressWarnings("unchecked")
    public Comparable<? super T> getComparableTo(T e) {
        Comparable<? super T> c;
        if (comp == null) {
            c = ((Comparable<? super T>) e);
        } else {
            c = (T e1) -> comp.compare(e, e1);
        }
        return c;
    }

    // PRIVATE
    private void addToEmpty(T e) {
        root = new Node<>(e);
    }

    private Node<T> getParentForLeaf(T e) {
        Node<T> parent = root;
        Node<T> temp = root;
        Comparable<? super T> c = getComparableTo(e);
        while (temp != null) {
            parent = temp;
            if (c.compareTo(temp.getData()) == 0) {
                temp = null;
            } else if (c.compareTo(temp.getData()) == 1) {
                temp = temp.getRight();
            } else {
                temp = temp.getLeft();
            }
        }
        return parent;
    }

    private Node<T> findExactNodeOrParent(T e) {
        Node<T> parent = root;
        Node<T> temp = root;
        Comparable<? super T> c = getComparableTo(e);
        int res = c.compareTo(temp.getData());
        while (temp != null && res != 0) {
            parent = temp;
            if (res == 0) {
                temp = null;
            } else if (res == 1) {
                temp = temp.getRight();
            } else {
                temp = temp.getLeft();
            }
            res = c.compareTo(temp.getData());
        }
        return parent;
    }

    private Node<T> findNode(T e) {
        Node<T> node = root;
        Comparable<? super T> c = getComparableTo(e);
        while (node != null) {
            int res = c.compareTo(node.getData());
            if (res == 0) {
                return node;
            } else if (res == 1) {
                node = node.getRight();
            } else {
                node = node.getLeft();
            }
        }
        return null;
    }

    private T findGreaterOrEqualThan(ITwoWayIterator<T> it, Comparable<? super T> c) {
        T value = null;
        int res = 1;
        while (res == 1 && it.hasNext()) {
            value = it.next();
            res = c.compareTo(value);
        }
        return value;
    }

    private T findSmallerOrEqualThan(ITwoWayIterator<T> it, Comparable<? super T> c) {
        T value = null;
        int res = -1;
        while (res == -1 && it.hasPrev()) {
            value = it.prev();
            res = c.compareTo(value);
        }
        return value;
    }


}
