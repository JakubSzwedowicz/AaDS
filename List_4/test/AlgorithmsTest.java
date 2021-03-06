import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @Author Jakub Szwedowicz
 * @Create 23.03.2021
 * @Version 1.0
 */
class AlgorithmsTest {

    @ParameterizedTest(name = "kthSmallest run {index}")
    @MethodSource("AlgorithmsTest_Provider#testKthSmallest_AllKth_Provider")
    <T extends Comparable<? super T>> void testKthSmallest_AllKth(T[] tab_param, int kth_param, int answer) {
        Assertions.assertEquals(answer, Algorithms.kthSmallest(tab_param, kth_param));
    }

    @ParameterizedTest(name = "kthSmallest run {index}")
    @MethodSource("AlgorithmsTest_Provider#testKthSmallest_SpecialCases_Provider")
    <T extends Comparable<? super T>> void testKthSmallest_SpecialCases(T[] tab_param, int kth_param, int answer) {
        Assertions.assertEquals(answer, Algorithms.kthSmallest(tab_param, kth_param));
    }

    @ParameterizedTest(name = "kthSmallest run {index}")
    @MethodSource("AlgorithmsTest_Provider#testKthSmallest_IllegalArgumentsException_Provider")
    <T extends Comparable<? super T>> void testKthSmallest_IllegalArgumentsException(T[] tab_param, int kth_param) {
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () -> Algorithms.kthSmallest(tab_param, kth_param));
        Assertions.assertEquals("Illegal parameters were passed!", ex.getMessage());
    }

}