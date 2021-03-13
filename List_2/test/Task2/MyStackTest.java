package Task2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;


/**
 * @Author Jakub Szwedowicz
 * @Create 13.03.2021
 * @Version 1.0
 */

class MyStackTest {

    @AfterEach
    public void finishAfterTest(TestInfo testInfo) {
        System.out.println("Test info: " + testInfo.getDisplayName());
    }

    @ParameterizedTest(name = "testIsEmpty (Run: {index})")
    @MethodSource("Task2.MyStackTestProvider#testIsEmpty")
    <T> void testIsEmpty(boolean expected, MyStack<T> myStack) {
        Assertions.assertEquals(expected, myStack.isEmpty());
    }

    @ParameterizedTest(name = "testIsFull (Run: {index})")
    @MethodSource("Task2.MyStackTestProvider#testIsFull")
    <T> void testIsFull(boolean expected, MyStack<T> myStack) {
        Assertions.assertEquals(expected, myStack.isFull());
    }

    @ParameterizedTest(name = "testPop (Run: {index})")
    @MethodSource("Task2.MyStackTestProvider#testPop")
    <T> void testPop(T expected, MyStack<T> myStack) {
        T poped = myStack.pop();
        Assertions.assertEquals(expected, poped);
    }

    @ParameterizedTest(name = "testPush (Run: {index})")
    @MethodSource("Task2.MyStackTestProvider#testPush")
    <T> void testPush(T expected, MyStack<T> myStack) {
        myStack.push(expected);
        // Is use of MyStack<T>::top() illegal here and should be avoided?
        Assertions.assertEquals(expected, myStack.top());
    }

    @ParameterizedTest(name = "testSize (Run: {index})")
    @MethodSource("Task2.MyStackTestProvider#testSize")
    <T> void testSize(int expected, MyStack<T> myStack) {
        Assertions.assertEquals(expected, myStack.size());
    }

    @ParameterizedTest(name = "testTop (Run: {index})")
    @MethodSource("Task2.MyStackTestProvider#testTop")
    <T> void testTop(T expected, MyStack<T> myStack) {
        Assertions.assertEquals(expected, myStack.top());
    }
}