package Task3;

import org.junit.jupiter.params.provider.Arguments;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @Author Jakub Szwedowicz
 * @Create 13.03.2021
 * @Version 1.0
 */
public class VTStackTestProvider {
    static Stream<Arguments> testPop() {
        return Stream.of(
                Arguments.of(
                        3,
                        new VTStack<Integer>(Arrays.asList(1, 2, 3))
                ),
                Arguments.of(
                        5.5,
                        new VTStack<Double>(Arrays.asList(1.2, 4.3, 5.5))
                )
        );
    }
    static Stream<Arguments> testPush() {
        return Stream.of(
                Arguments.of(
                        5,
                        new VTStack<Integer>(Arrays.asList(1, 2, 3))
                ),
                Arguments.of(
                        7.0,
                        new VTStack<Double>(Arrays.asList(1.2, 4.3, 5.5))
                )
        );
    }

    static Stream<Arguments> testPeekAndTop() {
        return Stream.of(
                Arguments.of(
                        3,
                        new VTStack<Integer>(Arrays.asList(1, 2, 3))
                ),
                Arguments.of(
                        5.5,
                        new VTStack<Double>(Arrays.asList(1.2, 4.3, 5.5))
                )
        );
    }
    static Stream<Arguments> testDown() {
        return Stream.of(
                Arguments.of(
                        3,
                        new VTStack<Integer>(Arrays.asList(1, 2, 3)),
                        0
                ),
                Arguments.of(
                        2,
                        new VTStack<Integer>(Arrays.asList(1, 2, 3)),
                        1
                ),
                Arguments.of(
                        1,
                        new VTStack<Integer>(Arrays.asList(1, 2, 3)),
                        2
                ),
                Arguments.of(
                        4.3,
                        new VTStack<Double>(Arrays.asList(1.2, 4.3, 5.5, 10.0)),
                        2
                )
        );
    }
}
