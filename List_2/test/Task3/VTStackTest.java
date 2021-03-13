package Task3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Jakub Szwedowicz
 * @Create 13.03.2021
 * @Version 1.0
 */
class VTStackTest {

    @AfterEach
    void tearDown(TestInfo testInfo) {
        System.out.println("Test info: " + testInfo.getDisplayName());
    }

    @ParameterizedTest(name = "testPop (Run: {index})")
    @MethodSource("Task3.VTStackTestProvider#testPop")
    <T> void testPop(T expected, VTStack<T> vtStack) {
        assertEquals(expected, vtStack.pop());
    }

    @ParameterizedTest(name = "testPush (Run: {index})")
    @MethodSource("Task3.VTStackTestProvider#testPush")
    <T> void testPush(T expected, VTStack<T> vtStack) {
        vtStack.push(expected);
        assertEquals(expected, vtStack.top());
    }

    @ParameterizedTest(name = "testPeek (Run: {index})")
    @MethodSource("Task3.VTStackTestProvider#testPeekAndTop")
    <T> void testPeek(T expected, VTStack<T> vtStack) {
        assertEquals(expected, vtStack.peek());
    }

    @ParameterizedTest(name = "testTop (Run: {index})")
    @MethodSource("Task3.VTStackTestProvider#testPeekAndTop")
    <T> void testTop(T expected, VTStack<T> vtStack) {
        assertEquals(expected, vtStack.top());
    }

    @ParameterizedTest(name = "testDown (Run: {index})")
    @MethodSource("Task3.VTStackTestProvider#testDown")
    <T> void testDown(T expected, VTStack<T> vtStack, int down) {
        for(int i = 0; i < down; i++){
            vtStack.down();
        }
        assertEquals(expected, vtStack.peek());
    }
}