package Task2;

import java.util.EmptyStackException;
/**
 * @Author Jakub Szwedowicz
 * @Create 13.03.2021
 * @Version 1.0
 */


public interface IStack<T> {
    boolean isEmpty();
    boolean isFull();
    T pop() throws EmptyStackException;
    void push(T elem);// throws Task2.FullStackException;   sinking stack doesn't throw this exception
    int size();
    T top() throws EmptyStackException;
}
