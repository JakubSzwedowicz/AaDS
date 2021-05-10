package AVL;

/**
 * @author Jakub Szwedowicz
 * @version 1.0
 * date: 09.05.2021
 * email: kuba.szwedowicz@gmail.com
 */
public class Node<T> {
    private Node<T> left;
    private Node<T> right;
    private Node<T> parent;
    private T data;

    public Node(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public void swapData(Node<T> node){
        if(parent == null){
            T temp = data;
            data = node.data;
            node.data = temp;
        }

    }
    public void setLeft(Node<T> node) {
        this.left = node;
    }

    public void setRight(Node<T> node) {
        this.right = node;
    }

    public void setParent(Node<T> node) {
        this.parent = node;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getLeft() {
        return left;
    }

    public Node<T> getRight() {
        return right;
    }

    public Node<T> getParent() {
        return parent;
    }

    public T getData() {
        return data;
    }

    public String toString(){
        return data.toString();
    }
}
