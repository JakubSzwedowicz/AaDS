package Task3;

import Task2.MyStack;

import java.util.Collection;
import java.util.EmptyStackException;

/**
 * @Author Jakub Szwedowicz
 * @Create 13.03.2021
 * @Version 1.0
 */
public class VTStack<T> extends MyStack<T> {
    // MEMBER
    private int peek;
    private int peekCounter;
    // PUBLIC
    public VTStack(int capacity){
        super(capacity);
        peek = top;
        peekCounter = 0;
    }

    public VTStack(){
        super();
        peek = top;
        peekCounter = 0;
    }

    public VTStack(Collection<? super T> collection){
        super(collection);
        peek = top;
        peekCounter = 0;
    }

    @Override
    public T pop() throws EmptyStackException {
        T res = super.pop();
        resetPeekAndCounter();
        return res;
    }

    @Override
    public void push(T elem) {
        super.push(elem);
        resetPeekAndCounter();
    }

    public T peek(){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        @SuppressWarnings("unchecked")
        T res = (T) stack[peek];
        return res;
    }

    public void toTop(){
        resetPeekAndCounter();
    }

    public void down() throws BottomOfStackException{
        if(peekCounter == size){
            throw new BottomOfStackException();
        }
        peekCounter++;
        decreasePeek();
    }

    // PRIVATE
    private void decreasePeek(){
        peek = incrDecrTop(-1, peek);
    }

    private void resetPeekAndCounter(){
        peek = top;
        peekCounter = 0;
    }
}
