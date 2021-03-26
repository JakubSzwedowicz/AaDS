import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

/**
 * @Author Jakub Szwedowicz
 * @Create 23.03.2021
 * @Version 1.0
 */
public class AlgorithmsTest_Provider {

    public static Stream<Arguments> testKthSmallest_AllKth_Provider(){
        return Stream.of(
                Arguments.of(new int[]{5, 2, 4, 6, 8, 6, 3}, 1, 2),
                Arguments.of(new int[]{5, 2, 4, 6, 8, 6, 3}, 2, 3),
                Arguments.of(new int[]{5, 2, 4, 6, 8, 6, 3}, 3, 4),
                Arguments.of(new int[]{5, 2, 4, 6, 8, 6, 3}, 4, 5),
                Arguments.of(new int[]{5, 2, 4, 6, 8, 6, 3}, 5, 6),
                Arguments.of(new int[]{5, 2, 4, 6, 8, 6, 3}, 6, 6),
                Arguments.of(new int[]{5, 2, 4, 6, 8, 6, 3}, 7, 8)
        );
    }

    public static Stream<Arguments> testKthSmallest_SpecialCases_Provider(){
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 4, 5}, 1, 1),
                Arguments.of(new int[]{5, 4, 3, 2, 1}, 1, 1),
                Arguments.of(new int[]{1, 2}, 1, 1),
                Arguments.of(new int[]{1, 2}, 2, 2),
                Arguments.of(new int[]{1, 1, 1, 1}, 1, 1),
                Arguments.of(new int[]{1, 1, 2, 2, 3, 3}, 1, 1),
                Arguments.of(new int[]{1, 1, 2, 2, 3, 3}, 2, 1),
                Arguments.of(new int[]{1, 1, 2, 2, 3, 3}, 3, 2),
                Arguments.of(new int[]{1, 1, 2, 2, 3, 3}, 4, 2),
                Arguments.of(new int[]{1, 1, 2, 2, 3, 3}, 5, 3),
                Arguments.of(new int[]{1, 1, 2, 2, 3, 3}, 6, 3)
        );
    }
    public static Stream<Arguments> testKthSmallest_IllegalArgumentsException_Provider(){
        return Stream.of(
                Arguments.of(new int[]{}, 1),
                Arguments.of(null, 1),
                Arguments.of(new int[]{1, 2}, 3),
                Arguments.of(new int[]{1, 2}, 5),
                Arguments.of(new int[]{1, 2}, -1),
                Arguments.of(new int[]{1, 2}, 0)
        );
    }

}
