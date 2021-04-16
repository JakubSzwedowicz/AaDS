
package Algorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author Jakub Szwedowicz
 * @version 1.0
 * date: 14.04.2021
 * email: kuba.szwedowicz@gmail.com
 */


// -Djava.compiler=NONE
class SortingAlgorithmsTest {


    @Nested
    class DefaultQuickSortTest {
        @ParameterizedTest(name = "Test DefaultQuickSort for Large Set of Uniformly Distributed Integers in reverse order. Run: {index}")
        @MethodSource("Algorithms.SortingAlgorithmsTest_Provider#testSortForLargeSetOfIntegersReverseOrderUniformDistribution_Provider")
        <T extends Comparable<? super T>> void testDefaultQuickSortForLargeSetOfIntegersReverseOrder(List<T> array) {
            SortingAlgorithms.DefaultQuickSort.sort(array);
            Assertions.assertTrue(isSorted(array));
        }

        @ParameterizedTest(name = "Test DefaultQuickSort for Large Set of Uniformly Distributed Doubles in reverse order. Run: {index}")
        @MethodSource("Algorithms.SortingAlgorithmsTest_Provider#testSortForLargeSetOfDoublesReverseOrderUniformDistribution_Provider")
        <T extends Comparable<? super T>> void testDefaultQuickSortForLargeSetOfDoublesReverseOrder(List<T> array) {
            SortingAlgorithms.DefaultQuickSort.sort(array);
            Assertions.assertTrue(isSorted(array));
        }

        @ParameterizedTest(name = "Test DefaultQuickSort for Large Set of huge amplitudes of Integers in random order. Run: {index}")
        @MethodSource("Algorithms.SortingAlgorithmsTest_Provider#testSortForLargeSetOfIntegersRandomOrderWithHugeGaps_Provider")
        <T extends Comparable<? super T>> void testDefaultQuickSortForLargeSetOfIntegersRandomOrder(List<T> array) {
            SortingAlgorithms.DefaultQuickSort.sort(array);
            Assertions.assertTrue(isSorted(array));
        }

        @ParameterizedTest(name = "Test DefaultQuickSort for Large Set of huge amplitudes of Doubles in random order. Run: {index}")
        @MethodSource("Algorithms.SortingAlgorithmsTest_Provider#testSortForLargeSetOfDoublesRandomOrderWithHugeGaps_Provider")
        <T extends Comparable<? super T>> void testDefaultQuickSortForLargeSetOfDoublesRandomOrder(List<T> array) {
            SortingAlgorithms.DefaultQuickSort.sort(array);
            Assertions.assertTrue(isSorted(array));
        }


        @ParameterizedTest(name = "Test DefaultQuickSort for Large Set of uniformly distributed Integers in natural order. Run: {index}")
        @MethodSource("Algorithms.SortingAlgorithmsTest_Provider#testSortForLargeSetOfIntegersNaturalOrderUniformDistribution_Provider")
        <T extends Comparable<? super T>> void testDefaultQuickSortForLargeSetOfIntegersNaturalOrder(List<T> array) {
            SortingAlgorithms.DefaultQuickSort.sort(array);
            Assertions.assertTrue(isSorted(array));
        }

        @ParameterizedTest(name = "Test DefaultQuickSort for Large Set of uniformly distributed Doubles in natural order. Run: {index}")
        @MethodSource("Algorithms.SortingAlgorithmsTest_Provider#testSortForLargeSetOfDoublesNaturalOrderUniformDistribution_Provider")
        <T extends Comparable<? super T>> void testDefaultQuickSortForLargeSetOfDoublesNaturalOrder(List<T> array) {
            SortingAlgorithms.DefaultQuickSort.sort(array);
            Assertions.assertTrue(isSorted(array));
        }
    }

    @Nested
    class DefaultQuickSortWithSeparateComparatorTest {
        @ParameterizedTest(name = "Test DefaultQuickSortWithSeparateComparator for Large Set of Uniformly Distributed Integers in reverse order. Run: {index}")
        @MethodSource("Algorithms.SortingAlgorithmsTest_Provider#testSortForLargeSetOfIntegersReverseOrderUniformDistribution_Provider")
        <T extends Comparable<? super T>> void testDefaultQuickSortWithSeparateComparatorForLargeSetOfIntegersReverseOrder(List<T> array) {
            SortingAlgorithms.DefaultQuickSortWithSeparateComparator.sort(array);
            Assertions.assertTrue(isSorted(array));
        }

        @ParameterizedTest(name = "Test DefaultQuickSortWithSeparateComparator for Large Set of Uniformly Distributed Doubles in reverse order. Run: {index}")
        @MethodSource("Algorithms.SortingAlgorithmsTest_Provider#testSortForLargeSetOfDoublesReverseOrderUniformDistribution_Provider")
        <T extends Comparable<? super T>> void testDefaultQuickSortWithSeparateComparatorForLargeSetOfDoublesReverseOrder(List<T> array) {
            SortingAlgorithms.DefaultQuickSortWithSeparateComparator.sort(array);
            Assertions.assertTrue(isSorted(array));
        }

        @ParameterizedTest(name = "Test DefaultQuickSortWithSeparateComparator for Large Set of huge amplitudes of Integers in random order. Run: {index}")
        @MethodSource("Algorithms.SortingAlgorithmsTest_Provider#testSortForLargeSetOfIntegersRandomOrderWithHugeGaps_Provider")
        <T extends Comparable<? super T>> void testDefaultQuickSortWithSeparateComparatorForLargeSetOfIntegersRandomOrder(List<T> array) {
            SortingAlgorithms.DefaultQuickSortWithSeparateComparator.sort(array);
            Assertions.assertTrue(isSorted(array));
        }

        @ParameterizedTest(name = "Test DefaultQuickSortWithSeparateComparator for Large Set of huge amplitudes of Doubles in random order. Run: {index}")
        @MethodSource("Algorithms.SortingAlgorithmsTest_Provider#testSortForLargeSetOfDoublesRandomOrderWithHugeGaps_Provider")
        <T extends Comparable<? super T>> void testDefaultQuickSortWithSeparateComparatorForLargeSetOfDoublesRandomOrder(List<T> array) {
            SortingAlgorithms.DefaultQuickSortWithSeparateComparator.sort(array);
            Assertions.assertTrue(isSorted(array));
        }


        @ParameterizedTest(name = "Test DefaultQuickSortWithSeparateComparator for Large Set of uniformly distributed Integers in natural order. Run: {index}")
        @MethodSource("Algorithms.SortingAlgorithmsTest_Provider#testSortForLargeSetOfIntegersNaturalOrderUniformDistribution_Provider")
        <T extends Comparable<? super T>> void testDefaultQuickSortWithSeparateComparatorForLargeSetOfIntegersNaturalOrder(List<T> array) {
            SortingAlgorithms.DefaultQuickSortWithSeparateComparator.sort(array);
            Assertions.assertTrue(isSorted(array));
        }

        @ParameterizedTest(name = "Test DefaultQuickSortWithSeparateComparator for Large Set of uniformly distributed Doubles in natural order. Run: {index}")
        @MethodSource("Algorithms.SortingAlgorithmsTest_Provider#testSortForLargeSetOfDoublesNaturalOrderUniformDistribution_Provider")
        <T extends Comparable<? super T>> void testDefaultQuickSortWithSeparateComparatorForLargeSetOfDoublesNaturalOrder(List<T> array) {
            SortingAlgorithms.DefaultQuickSortWithSeparateComparator.sort(array);
            Assertions.assertTrue(isSorted(array));
        }
    }

    @Nested
    class QuickSortTest {
        @ParameterizedTest(name = "Test QuickSort for Large Set of Uniformly Distributed Integers in reverse order. Run: {index}")
        @MethodSource("Algorithms.SortingAlgorithmsTest_Provider#testSortForLargeSetOfIntegersReverseOrderUniformDistribution_Provider")
        <T extends Comparable<? super T>> void testQuickSortForLargeSetOfIntegersReverseOrder(List<T> array) {
            SortingAlgorithms.QuickSort.sort(array);
            Assertions.assertTrue(isSorted(array));
        }

        @ParameterizedTest(name = "Test QuickSort for Large Set of Uniformly Distributed Doubles in reverse order. Run: {index}")
        @MethodSource("Algorithms.SortingAlgorithmsTest_Provider#testSortForLargeSetOfDoublesReverseOrderUniformDistribution_Provider")
        <T extends Comparable<? super T>> void testQuickSortForLargeSetOfDoublesReverseOrder(List<T> array) {
            SortingAlgorithms.QuickSort.sort(array);
            Assertions.assertTrue(isSorted(array));
        }

        @ParameterizedTest(name = "Test QuickSort for Large Set of huge amplitudes of Integers in random order. Run: {index}")
        @MethodSource("Algorithms.SortingAlgorithmsTest_Provider#testSortForLargeSetOfIntegersRandomOrderWithHugeGaps_Provider")
        <T extends Comparable<? super T>> void testQuickSortForLargeSetOfIntegersRandomOrder(List<T> array) {
            SortingAlgorithms.QuickSort.sort(array);
            Assertions.assertTrue(isSorted(array));
        }

        @ParameterizedTest(name = "Test QuickSort for Large Set of huge amplitudes of Doubles in random order. Run: {index}")
        @MethodSource("Algorithms.SortingAlgorithmsTest_Provider#testSortForLargeSetOfDoublesRandomOrderWithHugeGaps_Provider")
        <T extends Comparable<? super T>> void testQuickSortForLargeSetOfDoublesRandomOrder(List<T> array) {
            SortingAlgorithms.QuickSort.sort(array);
            Assertions.assertTrue(isSorted(array));
        }

        @ParameterizedTest(name = "Test QuickSort for Large Set of uniformly distributed Integers in natural order. Run: {index}")
        @MethodSource("Algorithms.SortingAlgorithmsTest_Provider#testSortForLargeSetOfIntegersNaturalOrderUniformDistribution_Provider")
        <T extends Comparable<? super T>> void testQuickSortForLargeSetOfIntegersNaturalOrder(List<T> array) {
            SortingAlgorithms.QuickSort.sort(array);
            Assertions.assertTrue(isSorted(array));
        }

        @ParameterizedTest(name = "Test QuickSort for Large Set of uniformly distributed Doubles in natural order. Run: {index}")
        @MethodSource("Algorithms.SortingAlgorithmsTest_Provider#testSortForLargeSetOfDoublesNaturalOrderUniformDistribution_Provider")
        <T extends Comparable<? super T>> void testQuickSortForLargeSetOfDoublesNaturalOrder(List<T> array) {
            SortingAlgorithms.QuickSort.sort(array);
            Assertions.assertTrue(isSorted(array));
        }
    }

    @Nested
    class BucketSortTest {
        @ParameterizedTest(name = "Test BucketSort for Large Set of Uniformly Distributed Integers in reverse order. Run: {index}")
        @MethodSource("Algorithms.SortingAlgorithmsTest_Provider#testSortForLargeSetOfIntegersReverseOrderUniformDistribution_Provider")
        <T extends Number & Comparable<? super T>> void testBucketSortForLargeSetOfIntegersReverseOrder(List<T> array) {
            SortingAlgorithms.BucketSort.sort(array);
            Assertions.assertTrue(isSorted(array));
        }

        @ParameterizedTest(name = "Test BucketSort for Large Set of Uniformly Distributed Doubles in reverse order. Run: {index}")
        @MethodSource("Algorithms.SortingAlgorithmsTest_Provider#testSortForLargeSetOfDoublesReverseOrderUniformDistribution_Provider")
        <T extends Number & Comparable<? super T>> void testBucketSortForLargeSetOfDoublesReverseOrder(List<T> array) {
            SortingAlgorithms.BucketSort.sort(array);
            Assertions.assertTrue(isSorted(array));
        }

        @ParameterizedTest(name = "Test BucketSort for Large Set of huge amplitudes of Integers in random order. Run: {index}")
        @MethodSource("Algorithms.SortingAlgorithmsTest_Provider#testSortForLargeSetOfIntegersRandomOrderWithHugeGaps_Provider")
        <T extends Number & Comparable<? super T>> void testBucketSortForLargeSetOfIntegersRandomOrder(List<T> array) {
            SortingAlgorithms.BucketSort.sort(array);
            Assertions.assertTrue(isSorted(array));
        }

        @ParameterizedTest(name = "Test BucketSort for Large Set of huge amplitudes of Doubles in random order. Run: {index}")
        @MethodSource("Algorithms.SortingAlgorithmsTest_Provider#testSortForLargeSetOfDoublesRandomOrderWithHugeGaps_Provider")
        <T extends Number & Comparable<? super T>> void testBucketSortForLargeSetOfDoublesRandomOrder(List<T> array) {
            SortingAlgorithms.BucketSort.sort(array);
            Assertions.assertTrue(isSorted(array));
        }

        @ParameterizedTest(name = "Test BucketSort for Large Set of uniformly distributed Integers in natural order. Run: {index}")
        @MethodSource("Algorithms.SortingAlgorithmsTest_Provider#testSortForLargeSetOfIntegersNaturalOrderUniformDistribution_Provider")
        <T extends Number & Comparable<? super T>> void testBucketSortForLargeSetOfIntegersNaturalOrder(List<T> array) {
            SortingAlgorithms.BucketSort.sort(array);
            Assertions.assertTrue(isSorted(array));
        }

        @ParameterizedTest(name = "Test BucketSort for Large Set of uniformly distributed Doubles in natural order. Run: {index}")
        @MethodSource("Algorithms.SortingAlgorithmsTest_Provider#testSortForLargeSetOfDoublesNaturalOrderUniformDistribution_Provider")
        <T extends Number & Comparable<? super T>> void testBucketSortForLargeSetOfDoublesNaturalOrder(List<T> array) {
            SortingAlgorithms.BucketSort.sort(array);
            Assertions.assertTrue(isSorted(array));
        }
    }

    @Nested
    class CollectionsSortTest {
        @ParameterizedTest(name = "Test CollectionsSort for Large Set of Uniformly Distributed Integers in reverse order. Run: {index}")
        @MethodSource("Algorithms.SortingAlgorithmsTest_Provider#testSortForLargeSetOfIntegersReverseOrderUniformDistribution_Provider")
        <T extends Comparable<? super T>> void testCollectionsSortForLargeSetOfIntegersReverseOrder(List<T> array) {
            Collections.sort(array);
            Assertions.assertTrue(isSorted(array));
        }

        @ParameterizedTest(name = "Test CollectionsSort for Large Set of Uniformly Distributed Doubles in reverse order. Run: {index}")
        @MethodSource("Algorithms.SortingAlgorithmsTest_Provider#testSortForLargeSetOfDoublesReverseOrderUniformDistribution_Provider")
        <T extends Comparable<? super T>> void testCollectionsSortForLargeSetOfDoublesReverseOrder(List<T> array) {
            Collections.sort(array);
            Assertions.assertTrue(isSorted(array));
        }

        @ParameterizedTest(name = "Test CollectionsSort for Large Set of huge amplitudes of Integers in random order. Run: {index}")
        @MethodSource("Algorithms.SortingAlgorithmsTest_Provider#testSortForLargeSetOfIntegersRandomOrderWithHugeGaps_Provider")
        <T extends Comparable<? super T>> void testCollectionsSortForLargeSetOfIntegersRandomOrder(List<T> array) {
            Collections.sort(array);
            Assertions.assertTrue(isSorted(array));
        }

        @ParameterizedTest(name = "Test CollectionsSort for Large Set of huge amplitudes of Doubles in random order. Run: {index}")
        @MethodSource("Algorithms.SortingAlgorithmsTest_Provider#testSortForLargeSetOfDoublesRandomOrderWithHugeGaps_Provider")
        <T extends Comparable<? super T>> void testCollectionsSortForLargeSetOfDoublesRandomOrder(List<T> array) {
           Collections.sort(array);
            Assertions.assertTrue(isSorted(array));
        }

        @ParameterizedTest(name = "Test CollectionsSort for Large Set of uniformly distributed Integers in natural order. Run: {index}")
        @MethodSource("Algorithms.SortingAlgorithmsTest_Provider#testSortForLargeSetOfIntegersNaturalOrderUniformDistribution_Provider")
        <T extends Comparable<? super T>> void testCollectionsSortForLargeSetOfIntegersNaturalOrder(List<T> array) {
            Collections.sort(array);
            Assertions.assertTrue(isSorted(array));
        }

        @ParameterizedTest(name = "Test CollectionsSort for Large Set of uniformly distributed Doubles in natural order. Run: {index}")
        @MethodSource("Algorithms.SortingAlgorithmsTest_Provider#testSortForLargeSetOfDoublesNaturalOrderUniformDistribution_Provider")
        <T extends Comparable<? super T>> void testCollectionsSortForLargeSetOfDoublesNaturalOrder(List<T> array) {
            Collections.sort(array);
            Assertions.assertTrue(isSorted(array));
        }
    }

    @Nested
    class ArraysSortTest {

        @ParameterizedTest(name = "Test ArraysSort for Large Set of Uniformly Distributed Integers in reverse order. Run: {index}")
        @MethodSource("Algorithms.SortingAlgorithmsTest_Provider#testSortForLargeSetOfIntegersReverseOrderUniformDistribution_ArrayWrapperProvider")
        void testArraysSortForLargeSetOfIntegersReverseOrder(int[] array) {
            Arrays.sort(array);
            Assertions.assertTrue(isSorted(array));
        }

        @ParameterizedTest(name = "Test ArraysSort for Large Set of Uniformly Distributed Doubles in reverse order. Run: {index}")
        @MethodSource("Algorithms.SortingAlgorithmsTest_Provider#testSortForLargeSetOfDoublesReverseOrderUniformDistribution_ArrayWrapperProvider")
         void testArraysSortForLargeSetOfDoublesReverseOrder(double[] array) {
            Arrays.sort(array);
            Assertions.assertTrue(isSorted(array));
        }

        @ParameterizedTest(name = "Test ArraysSort for Large Set of huge amplitudes of Integers in random order. Run: {index}")
        @MethodSource("Algorithms.SortingAlgorithmsTest_Provider#testSortForLargeSetOfIntegersRandomOrderWithHugeGaps_ArrayWrapperProvider")
         void testArraysSortForLargeSetOfIntegersRandomOrder(int[] array) {
            Arrays.sort(array);
            Assertions.assertTrue(isSorted(array));
        }

        @ParameterizedTest(name = "Test ArraysSort for Large Set of huge amplitudes of Doubles in random order. Run: {index}")
        @MethodSource("Algorithms.SortingAlgorithmsTest_Provider#testSortForLargeSetOfDoublesRandomOrderWithHugeGaps_ArrayWrapperProvider")
        void testArraysSortForLargeSetOfDoublesRandomOrder(double[] array) {
            Arrays.sort(array);
            Assertions.assertTrue(isSorted(array));
        }

        @ParameterizedTest(name = "Test ArraysSort for Large Set of uniformly distributed Integers in natural order. Run: {index}")
        @MethodSource("Algorithms.SortingAlgorithmsTest_Provider#testSortForLargeSetOfIntegersNaturalOrderUniformDistribution_ArrayWrapperProvider")
         void testArraysSortForLargeSetOfIntegersNaturalOrder(int[] array) {
            Arrays.sort(array);
            Assertions.assertTrue(isSorted(array));
        }

        @ParameterizedTest(name = "Test ArraysSort for Large Set of uniformly distributed Doubles in natural order. Run: {index}")
        @MethodSource("Algorithms.SortingAlgorithmsTest_Provider#testSortForLargeSetOfDoublesNaturalOrderUniformDistribution_ArrayWrapperProvider")
         void testArraysSortForLargeSetOfDoublesNaturalOrder(double[] array) {
            Arrays.sort(array);
            Assertions.assertTrue(isSorted(array));
        }
    }

    private <T extends Comparable<? super T>> boolean isSorted(List<T> array) {
        for (int i = 0; i < array.size() - 1; i++) {
            if (array.get(i).compareTo(array.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }

    private <T extends Comparable<? super T>> boolean isSorted(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i].compareTo(array[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] - array[i + 1] > 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isSorted(double[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] - array[i + 1] > 0) {
                return false;
            }
        }
        return true;
    }
}
