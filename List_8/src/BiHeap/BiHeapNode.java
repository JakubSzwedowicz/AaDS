package BiHeap;

/**
 * @author Jakub Szwedowicz
 * @version 1.0
 * date: 16.05.2021
 * email: kuba.szwedowicz@gmail.com
 */

// package private class
class BiHeapNode<T extends Comparable<? super T>> {
    T key;
    int degree;
    BiHeapNode<T> parent;
    BiHeapNode<T> sibling;
    BiHeapNode<T> child;

    BiHeapNode(T key){
        parent = null;
        this.key = key;
        degree = 0;
        sibling = null;
        child = null;
    }

    BiHeapNode(){
        this(null);
    }


    void swapKey(BiHeapNode<T> node){
        T temp = node.key;
        node.key = key;
        key = temp;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BiHeapNode)) {
            return false;
        }
        return key.equals(((BiHeapNode<T>)(obj)).key);
    }
}
