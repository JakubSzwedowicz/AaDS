package BiHeap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Jakub Szwedowicz
 * @version 1.0
 * date: 17.05.2021
 * email: kuba.szwedowicz@gmail.com
 */
class BiHeapTest {
    private BiHeap<Integer> heap;

    @BeforeEach
    void setUp() {
        heap = new BiHeap<Integer>();
        heap = BiHeap.insert(heap, new BiHeapNode<Integer>(10));
        heap = BiHeap.insert(heap, new BiHeapNode<Integer>(1));
        heap = BiHeap.insert(heap, new BiHeapNode<Integer>(2));
        heap = BiHeap.insert(heap, new BiHeapNode<Integer>(5));
        heap = BiHeap.insert(heap, new BiHeapNode<Integer>(12));
        heap = BiHeap.insert(heap, new BiHeapNode<Integer>(3));
        heap = BiHeap.insert(heap, new BiHeapNode<Integer>(7));
        heap = BiHeap.insert(heap, new BiHeapNode<Integer>(23));
    }

    @Test
    void testSize() {
        Assertions.assertEquals(8, heap.size());
        BiHeap.delete(heap, heap.getHead());
        Assertions.assertEquals(7, heap.size());
    }

    @Test
    void testInsert() {
        BiHeapNode<Integer> node = new BiHeapNode<Integer>(0);
        heap = BiHeap.insert(heap, node);
        Assertions.assertEquals(node, BiHeap.minimum(heap));
        BiHeap.extractMin(heap);
        BiHeapNode<Integer> minNode = new BiHeapNode<Integer>(1);
        Assertions.assertEquals(minNode, BiHeap.minimum(heap));
    }

    @Test
    void testMinimum() {
        BiHeapNode<Integer> node = new BiHeapNode<Integer>(1);
         Assertions.assertEquals(node, BiHeap.minimum(heap));
        BiHeap.delete(heap, BiHeap.minimum(heap));
        node = new BiHeapNode<Integer>(2);
        Assertions.assertEquals(node, BiHeap.minimum(heap));
    }

    @Test
    void testExtractMin() {
        BiHeapNode<Integer> node = new BiHeapNode<Integer>(1);
        Assertions.assertEquals(node, BiHeap.extractMin(heap));
        node = new BiHeapNode<Integer>(2);
        Assertions.assertEquals(node, BiHeap.extractMin(heap));
        node = new BiHeapNode<Integer>(3);
        Assertions.assertEquals(node, BiHeap.extractMin(heap));
    }

    @Test
    void testDelete() {
        BiHeapNode<Integer> minNode = BiHeap.minimum(heap);
        BiHeap.delete(heap, minNode);
        Assertions.assertEquals(new BiHeapNode<Integer>(2), BiHeap.minimum(heap));

        minNode = new BiHeapNode<Integer>(-10);
        heap = BiHeap.insert(heap, minNode);
        Assertions.assertEquals(minNode, BiHeap.minimum(heap));
    }

    @Test
    void testDecreaseKey() {
        BiHeapNode<Integer> node = heap.getHead();
        node = node.child;
        node = node.child;
        node = node.sibling;
        BiHeap.decreaseKey(heap, node, -10);
        Assertions.assertEquals(new BiHeapNode<Integer>(-10), BiHeap.minimum(heap));
    }
}