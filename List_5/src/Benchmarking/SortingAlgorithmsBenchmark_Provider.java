package Benchmarking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Jakub Szwedowicz
 * @version 1.0
 * date: 18.04.2021
 * email: kuba.szwedowicz@gmail.com
 */
public class SortingAlgorithmsBenchmark_Provider {
    private static final long s_seed = 0;
    private static final int s_largeSize = 201;

    public static List<List<Integer>> benchmarkSortForLargeSetOfIntegersReverseOrderUniformDistribution_Provider() {
        List<List<Integer>> res = new ArrayList<>(3);
        AtomicInteger begin = new AtomicInteger();

        begin.set(0);
        List<Integer> bigNegative = Stream.generate(begin::getAndDecrement).limit(s_largeSize).collect(Collectors.toList());
        res.add(bigNegative);

        begin.set(s_largeSize);
        List<Integer> bigPositive = Stream.generate(begin::getAndDecrement).limit(s_largeSize).collect(Collectors.toList());
        res.add(bigPositive);

        begin.set(s_largeSize / 2);
        List<Integer> bigMixed = Stream.generate(begin::getAndDecrement).limit(s_largeSize).collect(Collectors.toList());
        res.add(bigMixed);
        return res;
    }

    public static List<List<Double>> benchmarkSortForLargeSetOfDoublesReverseOrderUniformDistribution_Provider() {
        List<List<Double>> res = new ArrayList<>(3);
        AtomicInteger begin = new AtomicInteger();

        begin.set(0);
        List<Double> bigNegative = Stream.generate(() -> (double) begin.getAndDecrement()
                / 100).limit(s_largeSize).collect(Collectors.toList());
        res.add(bigNegative);

        begin.set(s_largeSize);
        List<Double> bigPositive = Stream.generate(() -> (double) begin.getAndDecrement()
                / 100).limit(s_largeSize).collect(Collectors.toList());
        res.add(bigPositive);

        begin.set(s_largeSize / 2);
        List<Double> bigMixed = Stream.generate(() -> (double) begin.getAndDecrement()
                / 100).limit(s_largeSize).collect(Collectors.toList());
        res.add(bigMixed);
        return res;
    }

    public static List<List<Integer>> benchmarkSortForLargeSetOfIntegersRandomOrderWithHugeGaps_Provider() {
        List<List<Integer>> res = new ArrayList<>(3);

        int number = 1;
        List<Integer> bigPositive = new ArrayList<>(s_largeSize);
        for (int i = 0; i < s_largeSize / 4; i++) {
            bigPositive.add(number);
            number++;
        }
        for (int i = 0; i < s_largeSize / 2; i++) {
            bigPositive.add(number);
            number += 5;
        }
        for (int i = 0; i < s_largeSize / 4; i++) {
            bigPositive.add(number);
            number += 1000;
        }
        Collections.shuffle(bigPositive, new Random(s_seed));
        res.add(bigPositive);
        return res;
    }

    public static List<List<Double>> benchmarkSortForLargeSetOfDoublesRandomOrderWithHugeGaps_Provider() {
        List<List<Double>> res = new ArrayList<>(3);
        double number = 1.0;

        List<Double> bigPositive = new ArrayList<>(s_largeSize);
        for (int i = 0; i < s_largeSize / 4; i++) {
            bigPositive.add(number);
            number += 0.1;
        }
        for (int i = 0; i < s_largeSize / 2; i++) {
            bigPositive.add(number);
            number += 0.5;   // 5 times bigger
        }
        for (int i = 0; i < s_largeSize / 4; i++) {
            bigPositive.add(number);
            number += 100.3;   // 1003 times bigger
        }
        Collections.shuffle(bigPositive, new Random(s_seed));
        res.add(bigPositive);
        return res;
    }

    public static List<List<Integer>> benchmarkSortForLargeSetOfIntegersNaturalOrderUniformDistribution_Provider() {
        List<List<Integer>> res = new ArrayList<>(3);
        int begin = 0;

        begin = -(s_largeSize - 1);
        List<Integer> bigNegative = IntStream.rangeClosed(begin, 0).boxed().collect(Collectors.toList());
        res.add(bigNegative);

        begin = 0;
        List<Integer> bigPositive = IntStream.rangeClosed(begin, s_largeSize - 1).boxed().collect(Collectors.toList());
        res.add(bigPositive);

        begin = -(s_largeSize / 2);
        List<Integer> bigMixed = IntStream.rangeClosed(begin, -begin).boxed().collect(Collectors.toList());
        res.add(bigMixed);
        return res;
    }

    public static List<List<Double>> benchmarkSortForLargeSetOfDoublesNaturalOrderUniformDistribution_Provider() {
        List<List<Double>> res = new ArrayList<>(3);
        AtomicInteger begin = new AtomicInteger();

        begin.set(-(s_largeSize - 1));
        List<Double> bigNegative = Stream.generate(() -> (double) begin.getAndIncrement()
                / 100).limit(s_largeSize).collect(Collectors.toList());
        res.add(bigNegative);

        begin.set(0);
        List<Double> bigPositive = Stream.generate(() -> (double) begin.getAndIncrement()
                / 100).limit(s_largeSize).collect(Collectors.toList());
        res.add(bigPositive);

        begin.set(-(s_largeSize / 2));
        List<Double> bigMixed = Stream.generate(() -> (double) begin.getAndIncrement()
                / 100).limit(s_largeSize).collect(Collectors.toList());
        res.add(bigMixed);
        return res;
}


    // Arrays
    public static List<int[]> benchmarkSortForLargeSetOfIntegersReverseOrderUniformDistribution_ArrayWrapperProvider() {
        List<int[]> res = new ArrayList<>(3);
        AtomicInteger begin = new AtomicInteger();

        begin.set(0);
        int[] bigNegative = IntStream.generate(begin::getAndDecrement).limit(s_largeSize).toArray();
        res.add(bigNegative);

        begin.set(s_largeSize);
        int[] bigPositive = IntStream.generate(begin::getAndDecrement).limit(s_largeSize).toArray();
        res.add(bigPositive);

        begin.set(s_largeSize / 2);
        int[] bigMixed = IntStream.generate(begin::getAndDecrement).limit(s_largeSize).toArray();
        res.add(bigMixed);
        return res;
    }

    public static List<double[]> benchmarkSortForLargeSetOfDoublesReverseOrderUniformDistribution_ArrayWrapperProvider() {
        List<double[]> res = new ArrayList<>(3);
        AtomicInteger begin = new AtomicInteger();

        begin.set(0);
        double[] bigNegative = DoubleStream.generate(() -> begin.getAndDecrement()
                / 100.0).limit(s_largeSize).toArray();
        res.add(bigNegative);

        begin.set(s_largeSize);
        double[] bigPositive = DoubleStream.generate(() -> (double) begin.getAndDecrement()
                / 100).limit(s_largeSize).toArray();
        res.add(bigPositive);

        begin.set(s_largeSize / 2);
        double[] bigMixed = DoubleStream.generate(() -> (double) begin.getAndDecrement()
                / 100).limit(s_largeSize).toArray();
        res.add(bigMixed);
        return res;
    }

    public static List<int[]> benchmarkSortForLargeSetOfIntegersRandomOrderWithHugeGaps_ArrayWrapperProvider() {
        List<int[]> res = new ArrayList<>(3);

        int number = 1;
        int[] bigPositive = new int[s_largeSize];
        int splitSmallNumbers = 4;
        int smallNumbersEnd = s_largeSize / splitSmallNumbers;
        for (int i = 0; i < smallNumbersEnd; i++) {
            bigPositive[i] = number;
            number++;
        }

        int splitMediumNumbers = 2;
        int mediumNumbersEnd = s_largeSize / splitMediumNumbers;
        for (int i = smallNumbersEnd; i < mediumNumbersEnd; i++) {
            bigPositive[i] = number;
            number += 5;
        }

        int splitBigNumbers = 4;
        int bigNumbersEnd = s_largeSize / splitMediumNumbers;
        for (int i = mediumNumbersEnd; i < bigNumbersEnd; i++) {
            bigPositive[i] = number;
            number += 1000;
        }

        shuffleArray(bigPositive);
        res.add(bigPositive);
        return res;
    }

    public static List<double[]> benchmarkSortForLargeSetOfDoublesRandomOrderWithHugeGaps_ArrayWrapperProvider() {
        List<double[]> res = new ArrayList<>(3);

        double number = 1.0;
        double[] bigPositive = new double[s_largeSize];
        int splitSmallNumbers = 4;
        int smallNumbersEnd = s_largeSize / splitSmallNumbers;
        for (int i = 0; i < smallNumbersEnd; i++) {
            bigPositive[i] = number;
            number += 0.1;
        }

        int splitMediumNumbers = 2;
        int mediumNumbersEnd = s_largeSize / splitMediumNumbers;
        for (int i = smallNumbersEnd; i < mediumNumbersEnd; i++) {
            bigPositive[i] = number;
            number += 0.5;
        }

        int splitBigNumbers = 4;
        int bigNumbersEnd = s_largeSize / splitMediumNumbers;
        for (int i = mediumNumbersEnd; i < bigNumbersEnd; i++) {
            bigPositive[i] = number;
            number += 100.3;
        }

        shuffleArray(bigPositive);
        res.add(bigPositive);
        return res;
    }

    public static List<int[]> benchmarkSortForLargeSetOfIntegersNaturalOrderUniformDistribution_ArrayWrapperProvider() {
        List<int[]> res = new ArrayList<>(3);
        int begin = 0;

        begin = -(s_largeSize - 1);
        int[] bigNegative = IntStream.rangeClosed(begin, 0).toArray();
        res.add(bigNegative);

        begin = 0;
        int[] bigPositive = IntStream.rangeClosed(begin, s_largeSize - 1).toArray();
        res.add(bigPositive);

        begin = -(s_largeSize / 2);
        int[] bigMixed = IntStream.rangeClosed(begin, -begin).toArray();
        res.add(bigMixed);
        return res;
    }

    public static List<double[]> benchmarkSortForLargeSetOfDoublesNaturalOrderUniformDistribution_ArrayWrapperProvider() {
        List<double[]> res = new ArrayList<>(3);
        AtomicInteger begin = new AtomicInteger();

        begin.set(-(s_largeSize - 1));
        double[] bigNegative = DoubleStream.generate(() -> (double) begin.getAndIncrement()
                / 100).limit(s_largeSize).toArray();
        res.add(bigNegative);

        begin.set(0);
        double[] bigPositive = DoubleStream.generate(() -> (double) begin.getAndIncrement()
                / 100).limit(s_largeSize).toArray();
        res.add(bigNegative);

        begin.set(-(s_largeSize / 2));
        double[] bigMixed = DoubleStream.generate(() -> (double) begin.getAndIncrement()
                / 100).limit(s_largeSize).toArray();
        res.add(bigNegative);
        return res;
    }

    // Fisher-Yates shuffling algorithm
    static <T extends Number & Comparable<? super T>> void shuffleArray(int[] array) {
        Random generator = new Random(s_seed);
        for (int i = array.length - 1; i > 0; i--) {
            int index = generator.nextInt(i + 1);
            int e = array[index];
            array[index] = array[i];
            array[i] = e;
        }
    }

    // Fisher-Yates shuffling algorithm
    static <T extends Number & Comparable<? super T>> void shuffleArray(double[] array) {
        Random generator = new Random(s_seed);
        for (int i = array.length - 1; i > 0; i--) {
            int index = generator.nextInt(i + 1);
            double e = array[index];
            array[index] = array[i];
            array[i] = e;
        }
    }
}
