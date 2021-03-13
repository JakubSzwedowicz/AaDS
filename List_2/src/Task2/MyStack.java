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
        if(size == 0){
            peak = 0;
        } else {
            peak = capacity - 1;
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
        peak = 0;
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
        T res = (T) stack[peak];
        incrDecrPeakAndSize(-1);
        return res;
    }

    @Override
    public void push(T elem){
        incrDecrPeakAndSize(1);
        stack[peak] = elem;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T top() throws EmptyStackException {
        if(isEmpty()){
            throw new EmptyStackException();
        }
        @SuppressWarnings("unchecked")
        T res = (T) stack[peak];
        return res;
    }

    public String toString(){
        StringBuilder output = new StringBuilder("Stack: size = " + size + ", capacity = " + capacity + ", peak = " + peak);
        int k = peak;
        for(int i = size; i != -1; i--){
            output.append("\n").append(i).append(": ").append(stack[k--]);
            if(k == -1){
                k = size -1;
            }
        }
        return output.toString();
    }

    // PRIVATE
    private void incrDecrPeakAndSize(int step) {
        incrDecrPeak(step);
        incrDecrSize(step);
    }
    private void incrDecrPeak(int step){
        peak += step;
        if (peak == capacity) {
            peak = 0;
        } else if (peak == -1) {
            peak = capacity - 1;
        }
    }
    private void incrDecrSize(int step){
        size += step;
        // Size will never drop below 0
        if (size > capacity) {
            size = capacity;
        }
    }
}
