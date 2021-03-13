package Task2;

import java.util.Collection;
import java.util.EmptyStackException;

/**
 * @Author Jakub Szwedowicz
 * @Create 13.03.2021
 * @Version 1.0
 */
public class MyStack<T> implements IStack<T> {
    // MEMBERS
    protected Object[] stack;
    protected int size;
    protected final int capacity;
    protected int top;

    private static final int MINIMAL_CAPACITY = 10;
    // PUBLIC

    // constructors

    public MyStack() {
        this(MINIMAL_CAPACITY);
    }

    public MyStack(Collection<? super T> collection) {
        size = collection.size();
        capacity = size;
        if (size == 0) {
            top = 0;
        } else {
            top = capacity - 1;
        }
        stack = collection.toArray();
    }

    public MyStack(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Given capacity cannot be negative! \n\t capacity = " + capacity);
        }
        this.capacity = capacity;
        stack = new Object[capacity];
        size = 0;
        top = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public T pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        @SuppressWarnings("unchecked")
        T res = (T) stack[top];
        incrDecrTopAndSize(-1);
        return res;
    }

    @Override
    public void push(T elem) {
        incrDecrTopAndSize(1);
        stack[top] = elem;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T top() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        @SuppressWarnings("unchecked")
        T res = (T) stack[top];
        return res;
    }

    public String toString() {
        StringBuilder output = new StringBuilder(
                "Stack: size = " + size + ", capacity = " + capacity + ", peak = " + top);
        int k = top;
        for (int i = size; i != -1; i--) {
            output.append("\n").append(i).append(": ").append(stack[k--]);
            if (k == -1) {
                k = size - 1;
            }
        }
        return output.toString();
    }

    // PRIVATE
    private void incrDecrTopAndSize(int step) {
        incrDecrTop(step);
        incrDecrSize(step);
    }

    private void incrDecrTop(int step) {
        top = incrDecrTop(step, top);
    }

    private void incrDecrSize(int step) {
        size += step;
        // Size will never drop below 0
        if (size > capacity) {
            size = capacity;
        }
    }

    // PROTECTED
    protected int incrDecrTop(int step, int peek) {
        peek += step;
        if (peek == capacity) {
            peek = 0;
        } else if (peek == -1) {
            peek = capacity - 1;
        }
        return peek;
    }
}
