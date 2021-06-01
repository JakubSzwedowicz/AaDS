package BiHeap;

import java.util.LinkedList;

/**
 * @author Jakub Szwedowicz
 * @version 1.0
 * date: 16.05.2021
 * email: kuba.szwedowicz@gmail.com
 */

public class BiHeap<T extends Comparable<? super T>> {
    private BiHeapNode<T> head;

    public BiHeap() {
        head = null;
    }

    public BiHeapNode<T> getHead() {
        return head;
    }

    public int size() {
        int res = 0;
        BiHeapNode<T> node = head;
        while (node != null) {
            res += Math.pow(2, node.degree);
            node = node.sibling;
        }
        return res;
    }

    public static <T extends Comparable<? super T>> BiHeap<T> makeHeap(Class<T> clazz) {
        return new BiHeap<T>();
    }

    public static <T extends Comparable<? super T>> BiHeap<T> insert(BiHeap<T> heap, BiHeapNode<T> node) {
        if (node == null) {
            return heap;
        }
        BiHeap<T> tempHeap = makeHeap(node.key.getClass());
        node.parent = null;
        node.sibling = null;
        node.child = null;
        node.degree = 0;
        tempHeap.head = node;
        heap = union(heap, tempHeap);
        return heap;
    }

    public static <T extends Comparable<? super T>> BiHeapNode<T> minimum(BiHeap<T> heap) {
        if (heap == null || heap.head == null) {
            return null;
        }
        BiHeapNode<T> min = heap.head;
        for (BiHeapNode<T> node = min.sibling; node != null; node = node.sibling) {
            if (node.key.compareTo(min.key) < 0) {
                min = node;
            }
        }
        return min;
    }

    public static <T extends Comparable<? super T>> BiHeapNode<T> extractMin(BiHeap<T> heap) {
        return extractMin(heap, null);
    }

    public static <T extends Comparable<? super T>> BiHeap<T> union(BiHeap<T> heap1, BiHeap<T> heap2) {
        if (heap2 == null || heap2.head == null || heap2.head.key == null) {
            return heap1;
        }
        if (heap1.head == null) {
            heap1.head = heap2.head;
            heap2.head = null;
            return heap1;
        }
        BiHeap<T> mergedHeap = binomialHeapMerge(heap1, heap2);
        BiHeapNode<T> prev = null;
        BiHeapNode<T> node = mergedHeap.head;
        if (node == null) {
            return mergedHeap;
        }
        BiHeapNode<T> next = node.sibling;
        while (next != null) {
            BiHeapNode<T> nextNext = next.sibling;
            if (node.degree != next.degree || (nextNext != null && nextNext.degree == node.degree)) { // case 1 & 2
                prev = node;
                node = next;
            } else {
                if (node.key.compareTo(next.key) <= 0) {    // case 3
                    node.sibling = next.sibling;
                    binomialLink(next, node);
                } else {    // case 4
                    if (prev == null) {
                        mergedHeap.head = next;
                    } else {
                        prev.sibling = next;
                    }
                    binomialLink(node, next);
                    node = next;
                }
            }
            next = nextNext;
        }
        return mergedHeap;
    }

    public static <T extends Comparable<? super T>> void delete(BiHeap<T> heap, BiHeapNode<T> node) {
        extractMin(heap, node);
    }

    public static <T extends Comparable<? super T>> boolean decreaseKey(BiHeap<T> heap, BiHeapNode<T> node, T key) {
        if (key.compareTo(node.key) > 0) {
            return false;
        }
        node.key = key;
        BiHeapNode<T> parent = node.parent;
        while (parent != null && (parent.key.compareTo(node.key) > 0)) {
            parent.swapKey(node);
            node = parent;
            parent = parent.parent;
        }
        return true;
    }

    private static <T extends Comparable<? super T>> BiHeapNode<T> extractMin(BiHeap<T> heap, BiHeapNode<T> optionalNodeToRemove) {
        if (heap == null || heap.head == null) {
            return null;
        }
        BiHeapNode<T> minNode;
        if (optionalNodeToRemove == null) {
            minNode = minimum(heap);
        } else {
            minNode = popToTheTop(heap, optionalNodeToRemove);
        }
        BiHeap<T> tempHeap = makeHeap(minNode.key.getClass());

        tempHeap.head = minNode.child;
        for (BiHeapNode<T> node = tempHeap.head; node != null; node = node.sibling) {
            tempHeap.head = node;
        }

        reverseNodeList(null, minNode.child);

        // Clear parents
        for (BiHeapNode<T> node = tempHeap.head; node != null; node = node.sibling) {
            node.parent = null;
        }

        // connect prev(minNode) with next(minNode)
        if (minNode == heap.head) {
            heap.head = minNode.sibling;
        } else {
            BiHeapNode<T> parentOfMin = heap.head;
            while (parentOfMin.sibling != minNode) {
                parentOfMin = parentOfMin.sibling;
            }
            parentOfMin.sibling = minNode.sibling;
        }
        minNode.sibling = null;

        heap.head = union(heap, tempHeap).head;
        return minNode;
    }

    private static <T extends Comparable<? super T>> BiHeapNode<T> popToTheTop(BiHeap<T> heap, BiHeapNode<T> node) {
        BiHeapNode<T> parent = node.parent;
        while (parent != null) {
            parent.swapKey(node);
            node = parent;
            parent = parent.parent;
        }
        return node;
    }

    private static <T extends Comparable<? super T>> BiHeapNode<T> binomialLink(BiHeapNode<T> heap1, BiHeapNode<T> heap2) {
        heap1.parent = heap2;
        heap1.sibling = heap2.child;
        heap2.child = heap1;
        heap2.degree++;
        return heap2;
    }

    private static <T extends Comparable<? super T>> BiHeap<T> binomialHeapMerge(BiHeap<T> heap1, BiHeap<T> heap2) {
        BiHeap<T> res = makeHeap(heap2.head.key.getClass());
        LinkedList<BiHeapNode<T>> mergedHeadsList = new LinkedList<>();
        while (heap1.head != null && heap2.head != null) {
            if (heap1.head.degree <= heap2.head.degree) {
                mergedHeadsList.addLast(heap1.head);
                heap1.head = heap1.head.sibling;
            } else {
                mergedHeadsList.addLast(heap2.head);
                heap2.head = heap2.head.sibling;
            }
        }
        for (; heap1.head != null; heap1.head = heap1.head.sibling) {
            mergedHeadsList.addLast(heap1.head);
        }
        for (; heap2.head != null; heap2.head = heap2.head.sibling) {
            mergedHeadsList.addLast(heap2.head);
        }
        res.head = mergedHeadsList.pop();
        BiHeapNode<T> head = res.head;
        while (!mergedHeadsList.isEmpty()) {
            head.sibling = mergedHeadsList.pop();
            head = head.sibling;
        }
        return res;
    }

    private static <T extends Comparable<? super T>> void reverseNodeList(BiHeapNode<T> olderSibling, BiHeapNode<T> node) {
        if (node == null) {
            return;
        }
        reverseNodeList(node, node.sibling);
        node.sibling = olderSibling;
        return;
    }
}
