package Task2;

import java.util.Collection;
import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * @Author Jakub Szwedowicz
 * @Create 13.03.2021
 * @Version 1.0
 */
public class MyStack<T> implements IStack<T> {
    // MEMBERS
    private Object[] stack;
    private int size;
    private final int capacity;
    private int peak;

    // PUBLIC

    // constructors

    public MyStack(Collection<? super T> collection){
        size = collection.size();
        capacity = size;
        peak = capacity;
        stack = collection.toArray();
    }
    public MyStack(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Given capacity cannot be negative! \n\t capacity = " + capacity);
        }
        this.capacity = capacity;
        stack = new Object[capacity];
        size = 0;
        peak = 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isFull(){
        return false;
    }

    @Override
    public T pop() throws EmptyStackException {
        return null;
    }

    @Override
    public void push(T elem){
    }

    @Override
    public int size() {
        return -1;
    }

    @Override
    public T top() throws EmptyStackException {
        return null;
    }

    // PRIVATE
    private void incrDecrPeakAndSize(int step) {
    }
}
