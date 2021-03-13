package Task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

/**
 * @Author Jakub Szwedowicz
 * @Create 13.03.2021
 * @Version 1.0
 */
public class MyStackTestProvider {
    static Stream<Arguments> testIsEmpty() {
        return Stream.of(
                Arguments.of(
                        true,
                        new MyStack<Integer>(10)
                ),
                Arguments.of(
                        true,
                        new MyStack<Double>(Collections.emptyList())
                ),
                Arguments.of(
                        false,
                        new MyStack<Integer>(Arrays.asList(1, 2))
                ),
                Arguments.of(
                        false,
                        new MyStack<Double>(Arrays.asList(3.0, 2.0))
                )
        );
    }

    static Stream<Arguments> testIsFull() {
        return Stream.of(
                Arguments.of(
                        false,
                        new MyStack<Integer>(10)
                ),
                Arguments.of(
                        false,
                        new MyStack<Double>(Collections.emptyList())
                ),
                Arguments.of(
                        true,
                        new MyStack<Integer>(Arrays.asList(1, 2))
                ),
                Arguments.of(
                        true,
                        new MyStack<Double>(Arrays.asList(3.0, 2.0))
                )
        );
    }

    static Stream<Arguments> testPop() {
        return Stream.of(
                Arguments.of(
                        3,
                        new MyStack<Integer>(Arrays.asList(1, 2, 3))
                ),
                Arguments.of(
                        5.5,
                        new MyStack<Double>(Arrays.asList(1.2, 4.3, 5.5))
                )
        );
    }

    static Stream<Arguments> testPush() {
        return Stream.of(
                Arguments.of(
                        5,
                        new MyStack<Integer>(Arrays.asList(1, 2, 3))
                ),
                Arguments.of(
                        7.0,
                        new MyStack<Double>(Arrays.asList(1.2, 4.3, 5.5))
                )
        );
    }

    static Stream<Arguments> testSize() {
        return Stream.of(
                Arguments.of(
                        0,
                        new MyStack<Integer>(10)
                ),
                Arguments.of(
                        0,
                        new MyStack<Double>(Collections.emptyList())
                ),
                Arguments.of(
                        2,
                        new MyStack<Integer>(Arrays.asList(1, 2))
                ),
                Arguments.of(
                        3,
                        new MyStack<Double>(Arrays.asList(3.0, 2.0, 6.0))
                )
        );
    }

    static Stream<Arguments> testTop() {
        return Stream.of(
                Arguments.of(
                        3,
                        new MyStack<Integer>(Arrays.asList(1, 2, 3))
                ),
                Arguments.of(
                        5.5,
                        new MyStack<Double>(Arrays.asList(1.2, 4.3, 5.5))
                )
        );
    }

}
