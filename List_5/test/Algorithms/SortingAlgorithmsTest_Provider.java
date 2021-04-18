package Algorithms;

import org.junit.jupiter.params.provider.Arguments;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Jakub Szwedowicz
 * @version 1.0
 * date: 14.04.2021
 * email: kuba.szwedowicz@gmail.com
 */
public class SortingAlgorithmsTest_Provider {

    private static final long s_seed = 0L;
    // with -Djava.compiler=NONE set s_largeSize = 2001. Tests take ~1.5 minute
//    private static final int s_largeSize = 2001;
    // without -Djava.compiler=NONE set s_largeSize = 20001. Tests take ~1 minute
//    private static final int s_largeSize = 20001;
//     without -Djava.compiler=NONE set s_largeSize = 10001. Tests take ~15 seconds
    private static final int s_largeSize = 2001;

    public static Stream<Arguments> testSortForLargeSetOfIntegersReverseOrderUniformDistribution_Provider() {
        Stream.Builder<Arguments> res = Stream.builder();
        AtomicInteger begin = new AtomicInteger();

        begin.set(0);
        List<Integer> bigNegative = Stream.generate(begin::getAndDecrement).limit(s_largeSize).collect(Collectors.toList());
        res.add(Arguments.of(bigNegative));

        begin.set(s_largeSize);
        List<Integer> bigPositive = Stream.generate(begin::getAndDecrement).limit(s_largeSize).collect(Collectors.toList());
        res.add(Arguments.of(bigPositive));

        begin.set(s_largeSize / 2);
        List<Integer> bigMixed = Stream.generate(begin::getAndDecrement).limit(s_largeSize).collect(Collectors.toList());
        res.add(Arguments.of(bigMixed));
        return res.build();
    }

    public static Stream<Arguments> testSortForLargeSetOfDoublesReverseOrderUniformDistribution_Provider() {
        Stream.Builder<Arguments> res = Stream.builder();
        AtomicInteger begin = new AtomicInteger();

        begin.set(0);
        List<Double> bigNegative = Stream.generate(() -> (double) begin.getAndDecrement() / 100).limit(s_largeSize).collect(Collectors.toList());
        res.add(Arguments.of(bigNegative));

        begin.set(s_largeSize);
        List<Double> bigPositive = Stream.generate(() -> (double) begin.getAndDecrement() / 100).limit(s_largeSize).collect(Collectors.toList());
        res.add(Arguments.of(bigPositive));

        begin.set(s_largeSize / 2);
        List<Double> bigMixed = Stream.generate(() -> (double) begin.getAndDecrement() / 100).limit(s_largeSize).collect(Collectors.toList());
        res.add(Arguments.of(bigMixed));
        return res.build();
    }

    public static Stream<Arguments> testSortForLargeSetOfIntegersRandomOrderWithHugeGaps_Provider() {
        Stream.Builder<Arguments> res = Stream.builder();

        int number = 1;
        List<Integer> bigPositive = new ArrayList<>(s_largeSize);
        for(int i = 0; i < s_largeSize/4; i++){
            bigPositive.add(number);
            number++;
        }
        for(int i = 0; i < s_largeSize /2; i++){
            bigPositive.add(number);
            number+= 5;
        }
        for(int i = 0; i < s_largeSize /4; i++){
            bigPositive.add(number);
            number+= 1000;
        }
        Collections.shuffle(bigPositive, new Random(s_seed));
        res.add(Arguments.of(bigPositive));
        return res.build();
    }

    public static Stream<Arguments> testSortForLargeSetOfDoublesRandomOrderWithHugeGaps_Provider() {
        Stream.Builder<Arguments> res = Stream.builder();
        double number = 1.0;

        List<Double> bigPositive = new ArrayList<>(s_largeSize);
        for(int i = 0; i < s_largeSize/4; i++){
            bigPositive.add(number);
            number+= 0.1;
        }
        for(int i = 0; i < s_largeSize /2; i++){
            bigPositive.add(number);
            number+= 0.5;   // 5 times bigger
        }
        for(int i = 0; i < s_largeSize /4; i++){
            bigPositive.add(number);
            number+= 100.3;   // 1003 times bigger
        }
        Collections.shuffle(bigPositive, new Random(s_seed));
        res.add(Arguments.of(bigPositive));
        return res.build();
    }

    public static Stream<Arguments> testSortForLargeSetOfIntegersNaturalOrderUniformDistribution_Provider() {
        Stream.Builder<Arguments> res = Stream.builder();
        int begin = 0;

        begin = -(s_largeSize - 1);
        List<Integer> bigNegative = IntStream.rangeClosed(begin, 0).boxed().collect(Collectors.toList());
        res.add(Arguments.of(bigNegative));

        begin = 0;
        List<Integer> bigPositive = IntStream.rangeClosed(begin, s_largeSize - 1).boxed().collect(Collectors.toList());
        res.add(Arguments.of(bigPositive));

        begin = -(s_largeSize / 2);
        List<Integer> bigMixed = IntStream.rangeClosed(begin, -begin).boxed().collect(Collectors.toList());
        res.add(Arguments.of(bigMixed));
        return res.build();
    }

    public static Stream<Arguments> testSortForLargeSetOfDoublesNaturalOrderUniformDistribution_Provider() {
        Stream.Builder<Arguments> res = Stream.builder();
        AtomicInteger begin = new AtomicInteger();

        begin.set(-(s_largeSize - 1));
        List<Double> bigNegative = Stream.generate(() -> (double) begin.getAndIncrement() / 100).limit(s_largeSize).collect(Collectors.toList());
        res.add(Arguments.of(bigNegative));

        begin.set(0);
        List<Double> bigPositive = Stream.generate(() -> (double) begin.getAndIncrement() / 100).limit(s_largeSize).collect(Collectors.toList());
        res.add(Arguments.of(bigPositive));

        begin.set(-(s_largeSize / 2));
        List<Double> bigMixed = Stream.generate(() -> (double) begin.getAndIncrement() / 100).limit(s_largeSize).collect(Collectors.toList());
        res.add(Arguments.of(bigMixed));
        return res.build();
    }



    // Arrays
    public static Stream<Arguments> testSortForLargeSetOfIntegersReverseOrderUniformDistribution_ArrayWrapperProvider() {
        Stream.Builder<Arguments> res = Stream.builder();
        AtomicInteger begin = new AtomicInteger();

        begin.set(0);
        int[] bigNegative = IntStream.generate(begin::getAndDecrement).limit(s_largeSize).toArray();
        res.add(Arguments.of(bigNegative));

        begin.set(s_largeSize);
        int[] bigPositive = IntStream.generate(begin::getAndDecrement).limit(s_largeSize).toArray();
        res.add(Arguments.of(bigPositive));

        begin.set(s_largeSize / 2);
        int[] bigMixed = IntStream.generate(begin::getAndDecrement).limit(s_largeSize).toArray();
        res.add(Arguments.of(bigMixed));
        return res.build();
    }

    public static Stream<Arguments> testSortForLargeSetOfDoublesReverseOrderUniformDistribution_ArrayWrapperProvider() {
        Stream.Builder<Arguments> res = Stream.builder();
        AtomicInteger begin = new AtomicInteger();

        begin.set(0);
        double[] bigNegative = DoubleStream.generate(() -> begin.getAndDecrement() / 100.0).limit(s_largeSize).toArray();
        res.add(Arguments.of(bigNegative));

        begin.set(s_largeSize);
        double[] bigPositive = DoubleStream.generate(() -> (double) begin.getAndDecrement() / 100).limit(s_largeSize).toArray();
        res.add(Arguments.of(bigPositive));

        begin.set(s_largeSize / 2);
        double[] bigMixed = DoubleStream.generate(() -> (double) begin.getAndDecrement() / 100).limit(s_largeSize).toArray();
        res.add(Arguments.of(bigMixed));
        return res.build();
    }

    public static Stream<Arguments> testSortForLargeSetOfIntegersRandomOrderWithHugeGaps_ArrayWrapperProvider() {
        Stream.Builder<Arguments> res = Stream.builder();

        int number = 1;
        int[] bigPositive = new int[s_largeSize];
        int splitSmallNumbers = 4;
        int smallNumbersEnd = s_largeSize/splitSmallNumbers;
        for(int i = 0; i < smallNumbersEnd; i++){
            bigPositive[i] = number;
            number++;
        }

        int splitMediumNumbers = 2;
        int mediumNumbersEnd = s_largeSize/splitMediumNumbers;
        for(int i = smallNumbersEnd; i < mediumNumbersEnd; i++){
            bigPositive[i] = number;
            number+= 5;
        }

        int splitBigNumbers = 4;
        int bigNumbersEnd = s_largeSize/splitMediumNumbers;
        for(int i = mediumNumbersEnd; i < bigNumbersEnd; i++){
            bigPositive[i] = number;
            number+= 1000;
        }

        shuffleArray(bigPositive);
        res.add(Arguments.of(bigPositive));
        return res.build();
    }

    public static Stream<Arguments> testSortForLargeSetOfDoublesRandomOrderWithHugeGaps_ArrayWrapperProvider() {
        Stream.Builder<Arguments> res = Stream.builder();

        double number = 1.0;
        double[] bigPositive = new double[s_largeSize];
        int splitSmallNumbers = 4;
        int smallNumbersEnd = s_largeSize/splitSmallNumbers;
        for(int i = 0; i < smallNumbersEnd; i++){
            bigPositive[i] = number;
            number+=0.1;
        }

        int splitMediumNumbers = 2;
        int mediumNumbersEnd = s_largeSize/splitMediumNumbers;
        for(int i = smallNumbersEnd; i < mediumNumbersEnd; i++){
            bigPositive[i] = number;
            number+=0.5;
        }

        int splitBigNumbers = 4;
        int bigNumbersEnd = s_largeSize/splitMediumNumbers;
        for(int i = mediumNumbersEnd; i < bigNumbersEnd; i++){
            bigPositive[i] = number;
            number+= 100.3;
        }

        shuffleArray(bigPositive);
        res.add(Arguments.of(bigPositive));
        return res.build();
    }

    public static Stream<Arguments> testSortForLargeSetOfIntegersNaturalOrderUniformDistribution_ArrayWrapperProvider() {
        Stream.Builder<Arguments> res = Stream.builder();
        int begin = 0;

        begin = -(s_largeSize - 1);
        int[] bigNegative = IntStream.rangeClosed(begin, 0).toArray();
        res.add(Arguments.of(bigNegative));

        begin = 0;
        int[] bigPositive = IntStream.rangeClosed(begin, s_largeSize - 1).toArray();
        res.add(Arguments.of(bigPositive));

        begin = -(s_largeSize / 2);
        int[] bigMixed = IntStream.rangeClosed(begin, -begin).toArray();
        res.add(Arguments.of(bigMixed));
        return res.build();
    }

    public static Stream<Arguments> testSortForLargeSetOfDoublesNaturalOrderUniformDistribution_ArrayWrapperProvider() {
        Stream.Builder<Arguments> res = Stream.builder();
        AtomicInteger begin = new AtomicInteger();

        begin.set(-(s_largeSize - 1));
        double[] bigNegative = DoubleStream.generate(() -> (double) begin.getAndIncrement() / 100).limit(s_largeSize).toArray();
        res.add(Arguments.of(bigNegative));

        begin.set(0);
        double[] bigPositive = DoubleStream.generate(() -> (double) begin.getAndIncrement() / 100).limit(s_largeSize).toArray();
        res.add(Arguments.of(bigNegative));

        begin.set(-(s_largeSize / 2));
        double[] bigMixed = DoubleStream.generate(() -> (double) begin.getAndIncrement() / 100).limit(s_largeSize).toArray();
        res.add(Arguments.of(bigNegative));
        return res.build();
    }

    // Fisher-Yates shuffling algorithm
    static void shuffleArray(int[] array)
    {
        Random generator = new Random(s_seed);
        for (int i = array.length - 1; i > 0; i--)
        {
            int index = generator.nextInt(i + 1);
            int e = array[index];
            array[index] = array[i];
            array[i] = e;
        }
    }

    // Fisher-Yates shuffling algorithm
    static void shuffleArray(double[] array)
    {
        Random generator = new Random(s_seed);
        for (int i = array.length - 1; i > 0; i--)
        {
            int index = generator.nextInt(i + 1);
            double e = array[index];
            array[index] = array[i];
            array[i] = e;
        }
    }
}
