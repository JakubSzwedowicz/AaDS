package Benchmarking;

import Algorithms.SortingAlgorithms;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Jakub Szwedowicz
 * @version 1.0
 * date: 18.04.2021
 * email: kuba.szwedowicz@gmail.com
 */
public class SortingAlgorithmsBenchmark {
    private static Timer s_timer = new Timer();

    public static boolean runBenchmark(int printInfoLevel, int repetitions) {
        s_timer.reset();
        boolean correct = true;
        if (printInfoLevel > 0) {
            System.out.println("===================================== Start DefaultQuickSort Benchmark ================================================\n");
//            System.out.println("DefaultQuickSort Benchmark");
        }
        if (!DefaultQuickSortBenchmark.benchmarkDefaultQuickSort(printInfoLevel, repetitions)) {
            correct = false;
        }
        if (printInfoLevel > 0) {
            System.out.println("\n======================================= End DefaultQuickSort Benchmark =================================================");
            System.out.println("=============================== Start DefaultQuickSortWithSeparateComparator Benchmark ==================================\n");
        }
        if (!DefaultQuickSortWithSeparateComparatorBenchmark.benchmarkDefaultQuickSortWithSeparateComparator(printInfoLevel, repetitions)) {
            correct = false;
        }
        if (printInfoLevel > 0) {
            System.out.println("\n================================ end DefaultQuickSortWithSeparateComparator Benchmark ==================================");
            System.out.println("======================================== Start QuickSort Benchmark ====================================================\n");
        }
        if (!QuickSortBenchmark.benchmarkQuickSort(printInfoLevel, repetitions)) {
            correct = false;
        }
        if (printInfoLevel > 0) {
            System.out.println("\n========================================= end QuickSort Benchmark =====================================================");
            System.out.println("======================================== start BucketSort Benchmark ===================================================\n");
        }
        if (!BucketSortBenchmark.benchmarkBucketSort(printInfoLevel, repetitions)) {
            correct = false;
        }
        if (printInfoLevel > 0) {
            System.out.println("\n========================================= end BucketSort Benchmark ====================================================");
            System.out.println("===================================== start Collections::sort Benchmark ===============================================\n");
        }
        if (!CollectionsSortBenchmark.benchmarkCollectionsSort(printInfoLevel, repetitions)) {
            correct = false;
        }
        if (printInfoLevel > 0) {
            System.out.println("\n===================================== end Collections::sort Benchmark ================================================");
            System.out.println("======================================== start Arrays::sort Benchmark ================================================\n");
        }
        if (!ArraysSortBenchmark.benchmarkArraysSort(printInfoLevel, repetitions)) {
            correct = false;
        }
        if (printInfoLevel > 0) {
            System.out.println("\n========================================= end Arrays::sort Benchmark =================================================");
        }
        if (printInfoLevel > -1) {
            System.out.println("\n***************************** Overall Benchmarking Info *******************************");
            System.out.println("Overall benchmarking took "
                    + s_timer.get_overallDurationInMilis() + " [ms]");
            System.out.println("Average benchmarking took "
                    + s_timer.get_overallDurationInMilis() / repetitions + " [ms]");
            System.out.println("***************************** Overall Benchmarking Info *******************************\n");
        }
        return correct;
    }

    public static class DefaultQuickSortBenchmark {

        public static boolean benchmarkDefaultQuickSort(int printInfoLevel, int repetitions) {
            boolean correct = true;
            Timer timer = new Timer();
            for (int i = 0; i < repetitions; i++) {
                if (printInfoLevel > 2) {
                    System.out.println(
                            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Start DefaultQuickSort Benchmark repetition " + (i + 1)
                                    + " ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                }
                timer.start();
                if (!benchmarkDefaultQuickSortForLargeSetOfIntegersReverseOrder(printInfoLevel)) {
                    correct = false;
                }
                if (!benchmarkDefaultQuickSortForLargeSetOfDoublesReverseOrder(printInfoLevel)) {
                    correct = false;
                }
                if (!benchmarkDefaultQuickSortForLargeSetOfIntegersRandomOrder(printInfoLevel)) {
                    correct = false;
                }
                if (!benchmarkDefaultQuickSortForLargeSetOfDoublesRandomOrder(printInfoLevel)) {
                    correct = false;
                }
                if (!benchmarkDefaultQuickSortForLargeSetOfIntegersNaturalOrder(printInfoLevel)) {
                    correct = false;
                }
                if (!benchmarkDefaultQuickSortForLargeSetOfDoublesNaturalOrder(printInfoLevel)) {
                    correct = false;
                }
                timer.stop();
                if (printInfoLevel > 1) {
                    if (printInfoLevel > 2) {
                        System.out.println(
                                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ end DefaultQuickSort Benchmark repetition " + (i + 1)
                                        + " ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    }
                    System.out.println(
                            "Benchmarking DefaultQuickSort (rep: " + (i + 1) + "/" + repetitions + ") took "
                                    + timer.get_durationInMilis() + " [ms]");
                }
            }
            if (printInfoLevel > 0) {
                if (printInfoLevel > 1) {
                    System.out.println("===========================================================");
                }
                System.out.println(
                        "Overall benchmarking " + repetitions + " repetitions of DefaultQuickSort took "
                                + timer.get_overallDurationInMilis()
                                + " [ms]");
                System.out.println(
                        "Average from benchmarking " + repetitions + " repetitions of DefaultQuickSort took "
                                + timer.get_overallDurationInMilis() / repetitions
                                + " [ms]");
                if (printInfoLevel > 1) {
                    System.out.println("===========================================================");
                }
            }

            return correct;
        }

        private static boolean benchmarkDefaultQuickSortForLargeSetOfIntegersReverseOrder(int printInfoLevel) {
            List<List<Integer>> array = SortingAlgorithmsBenchmark_Provider.benchmarkSortForLargeSetOfIntegersReverseOrderUniformDistribution_Provider();
            return sort(array, SortingAlgorithms.DefaultQuickSort::sort
                    , "Benchmark DefaultQuickSort for Large Set of Uniformly Distributed Integers in reverse order. Run:"
                    , "BenchmarkDefaultQuickSortForLargeSetOfIntegersReverseOrder", printInfoLevel);
        }


        private static boolean benchmarkDefaultQuickSortForLargeSetOfDoublesReverseOrder(int printInfoLevel) {
            List<List<Double>> array = SortingAlgorithmsBenchmark_Provider.benchmarkSortForLargeSetOfDoublesReverseOrderUniformDistribution_Provider();
            return sort(array, SortingAlgorithms.DefaultQuickSort::sort
                    , "Benchmark DefaultQuickSort for Large Set of Uniformly Distributed Doubles in reverse order. Run:"
                    , "BenchmarkDefaultQuickSortForLargeSetOfDoublesReverseOrder", printInfoLevel);
        }

        private static boolean benchmarkDefaultQuickSortForLargeSetOfIntegersRandomOrder(int printInfoLevel) {
            List<List<Integer>> array = SortingAlgorithmsBenchmark_Provider.benchmarkSortForLargeSetOfIntegersRandomOrderWithHugeGaps_Provider();
            return sort(array, SortingAlgorithms.DefaultQuickSort::sort
                    , "Benchmark DefaultQuickSort for Large Set of huge amplitudes of Integers in random order. Run:"
                    , "benchmarkDefaultQuickSortForLargeSetOfIntegersRandomOrder", printInfoLevel);

        }

        private static boolean benchmarkDefaultQuickSortForLargeSetOfDoublesRandomOrder(int printInfoLevel) {
            List<List<Double>> array = SortingAlgorithmsBenchmark_Provider.benchmarkSortForLargeSetOfDoublesRandomOrderWithHugeGaps_Provider();
            return sort(array, SortingAlgorithms.DefaultQuickSort::sort
                    , "Benchmark DefaultQuickSort for Large Set of huge amplitudes of Doubles in random order. Run:"
                    , "benchmarkDefaultQuickSortForLargeSetOfDoublesRandomOrder", printInfoLevel);
        }


        private static boolean benchmarkDefaultQuickSortForLargeSetOfIntegersNaturalOrder(int printInfoLevel) {
            List<List<Integer>> array = SortingAlgorithmsBenchmark_Provider.benchmarkSortForLargeSetOfIntegersNaturalOrderUniformDistribution_Provider();
            return sort(array, SortingAlgorithms.DefaultQuickSort::sort
                    , "Benchmark DefaultQuickSort for Large Set of uniformly distributed Integers in natural order. Run:"
                    , "benchmarkDefaultQuickSortForLargeSetOfIntegersNaturalOrder", printInfoLevel);
        }


        private static boolean benchmarkDefaultQuickSortForLargeSetOfDoublesNaturalOrder(int printInfoLevel) {
            List<List<Double>> array = SortingAlgorithmsBenchmark_Provider.benchmarkSortForLargeSetOfDoublesNaturalOrderUniformDistribution_Provider();
            return sort(array, SortingAlgorithms.DefaultQuickSort::sort
                    , "Benchmark DefaultQuickSort for Large Set of uniformly distributed Doubles in natural order. Run:"
                    , "benchmarkDefaultQuickSortForLargeSetOfDoublesNaturalOrder", printInfoLevel);
        }
    }

    public static class DefaultQuickSortWithSeparateComparatorBenchmark {
        public static boolean benchmarkDefaultQuickSortWithSeparateComparator(int printInfoLevel, int repetitions) {
            boolean correct = true;
            Timer timer = new Timer();
            for (int i = 0; i < repetitions; i++) {
                if (printInfoLevel > 2) {
                    System.out.println(
                            "~~~~~~~~~~~~~~~~~~~~~~~~~~ Start DefaultQuickSortWithSeparator Benchmark repetition " + (i
                                    + 1) + " ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                }
                timer.start();
                if (!benchmarkDefaultQuickSortWithSeparateComparatorForLargeSetOfIntegersReverseOrder(printInfoLevel)) {
                    correct = false;
                }
                if (!benchmarkDefaultQuickSortWithSeparateComparatorForLargeSetOfDoublesReverseOrder(printInfoLevel)) {
                    correct = false;
                }
                if (!benchmarkDefaultQuickSortWithSeparateComparatorForLargeSetOfIntegersRandomOrder(printInfoLevel)) {
                    correct = false;
                }
                if (!benchmarkDefaultQuickSortWithSeparateComparatorForLargeSetOfDoublesRandomOrder(printInfoLevel)) {
                    correct = false;
                }
                if (!benchmarkDefaultQuickSortWithSeparateComparatorForLargeSetOfIntegersNaturalOrder(printInfoLevel)) {
                    correct = false;
                }
                if (!benchmarkDefaultQuickSortWithSeparateComparatorForLargeSetOfDoublesNaturalOrder(printInfoLevel)) {
                    correct = false;
                }
                timer.stop();
                if (printInfoLevel > 1) {
                    if (printInfoLevel > 2) {
                        System.out.println(
                                "~~~~~~~~~~~~~~~~~~~~~~~~~~~ end DefaultQuickSortWithSeparator Benchmark repetition "
                                        + (i + 1) + " ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    }
                    System.out.println(
                            "Benchmarking DefaultQuickSortWithSeparateComparator (rep: " + (i + 1) + "/" + repetitions
                                    + ") took " + timer.get_durationInMilis()
                                    + " [ms]");
                }
            }
            if (printInfoLevel > 0) {
                if (printInfoLevel > 1) {
                    System.out.println("===========================================================");
                }
                System.out.println(
                        "Benchmarking " + repetitions + " repetitions of DefaultQuickSortWithSeparateComparator took "
                                + timer.get_overallDurationInMilis()
                                + " [ms]");
                System.out.println(
                        "Average from benchmarking " + repetitions
                                + " repetitions of DefaultQuickSortWithSeparateComparator took "
                                + timer.get_overallDurationInMilis() / repetitions
                                + " [ms]");
                if (printInfoLevel > 1) {
                    System.out.println("===========================================================");
                }
            }
            return correct;
        }

        private static boolean benchmarkDefaultQuickSortWithSeparateComparatorForLargeSetOfIntegersReverseOrder(int printInfoLevel) {
            List<List<Double>> array = SortingAlgorithmsBenchmark_Provider.benchmarkSortForLargeSetOfDoublesNaturalOrderUniformDistribution_Provider();
            return sort(array, SortingAlgorithms.DefaultQuickSortWithSeparateComparator::sort
                    , "Benchmark DefaultQuickSortWithSeparateComparator for Large Set of Uniformly Distributed Integers in reverse order. Run:"
                    , "benchmarkDefaultQuickSortWithSeparateComparatorForLargeSetOfIntegersReverseOrder", printInfoLevel);
        }


        private static boolean benchmarkDefaultQuickSortWithSeparateComparatorForLargeSetOfDoublesReverseOrder(int printInfoLevel) {
            List<List<Double>> array = SortingAlgorithmsBenchmark_Provider.benchmarkSortForLargeSetOfDoublesNaturalOrderUniformDistribution_Provider();
            return sort(array, SortingAlgorithms.DefaultQuickSortWithSeparateComparator::sort
                    , "Benchmark DefaultQuickSortWithSeparateComparator for Large Set of Uniformly Distributed Doubles in reverse order. Run:"
                    , "benchmarkDefaultQuickSortWithSeparateComparatorForLargeSetOfDoublesReverseOrder", printInfoLevel);
        }


        private static boolean benchmarkDefaultQuickSortWithSeparateComparatorForLargeSetOfIntegersRandomOrder(int printInfoLevel) {
            List<List<Double>> array = SortingAlgorithmsBenchmark_Provider.benchmarkSortForLargeSetOfDoublesNaturalOrderUniformDistribution_Provider();
            return sort(array, SortingAlgorithms.DefaultQuickSortWithSeparateComparator::sort
                    , "\"Benchmark DefaultQuickSortWithSeparateComparator for Large Set of huge amplitudes of Integers in random order. Run:\""
                    , "benchmarkDefaultQuickSortWithSeparateComparatorForLargeSetOfIntegersRandomOrder", printInfoLevel);

        }

        private static boolean benchmarkDefaultQuickSortWithSeparateComparatorForLargeSetOfDoublesRandomOrder(int printInfoLevel) {
            List<List<Double>> array = SortingAlgorithmsBenchmark_Provider.benchmarkSortForLargeSetOfDoublesNaturalOrderUniformDistribution_Provider();
            return sort(array, SortingAlgorithms.DefaultQuickSortWithSeparateComparator::sort
                    , "Benchmark DefaultQuickSortWithSeparateComparator for Large Set of huge amplitudes of Doubles in random order. Run:"
                    , "benchmarkDefaultQuickSortWithSeparateComparatorForLargeSetOfDoublesRandomOrder", printInfoLevel);
        }


        private static boolean benchmarkDefaultQuickSortWithSeparateComparatorForLargeSetOfIntegersNaturalOrder(int printInfoLevel) {
            List<List<Double>> array = SortingAlgorithmsBenchmark_Provider.benchmarkSortForLargeSetOfDoublesNaturalOrderUniformDistribution_Provider();
            return sort(array, SortingAlgorithms.DefaultQuickSortWithSeparateComparator::sort
                    , "Benchmark DefaultQuickSortWithSeparateComparator for Large Set of uniformly distributed Integers in natural order. Run:"
                    , "benchmarkDefaultQuickSortWithSeparateComparatorForLargeSetOfIntegersNaturalOrder", printInfoLevel);
        }


        private static boolean benchmarkDefaultQuickSortWithSeparateComparatorForLargeSetOfDoublesNaturalOrder(int printInfoLevel) {
            List<List<Double>> array = SortingAlgorithmsBenchmark_Provider.benchmarkSortForLargeSetOfDoublesNaturalOrderUniformDistribution_Provider();
            return sort(array, SortingAlgorithms.DefaultQuickSortWithSeparateComparator::sort
                    , "Benchmark DefaultQuickSortWithSeparateComparator for Large Set of uniformly distributed Doubles in natural order. Run:"
                    , "benchmarkDefaultQuickSortWithSeparateComparatorForLargeSetOfDoublesNaturalOrder", printInfoLevel);
        }
    }

    public static class QuickSortBenchmark {

        public static boolean benchmarkQuickSort(int printInfoLevel, int repetitions) {
            boolean correct = true;
            Timer timer = new Timer();
            for (int i = 0; i < repetitions; i++) {
                if (printInfoLevel > 2) {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Start QuickSort Benchmark repetition " + (i + 1)
                            + " ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                }
                timer.start();
                if (!benchmarkQuickSortForLargeSetOfIntegersReverseOrder(printInfoLevel)) {
                    correct = false;
                }
                if (!benchmarkQuickSortForLargeSetOfDoublesReverseOrder(printInfoLevel)) {
                    correct = false;
                }
                if (!benchmarkQuickSortForLargeSetOfIntegersRandomOrder(printInfoLevel)) {
                    correct = false;
                }
                if (!benchmarkQuickSortForLargeSetOfDoublesRandomOrder(printInfoLevel)) {
                    correct = false;
                }
                if (!benchmarkQuickSortForLargeSetOfIntegersNaturalOrder(printInfoLevel)) {
                    correct = false;
                }
                if (!benchmarkQuickSortForLargeSetOfDoublesNaturalOrder(printInfoLevel)) {
                    correct = false;
                }
                timer.stop();
                if (printInfoLevel > 1) {
                    if (printInfoLevel > 2) {
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ end QuickSort Benchmark repetition " + (i + 1)
                                + " ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    }
                    System.out.println("Benchmarking QuickSort (rep: " + (i + 1) + "/" + repetitions + ") took "
                            + timer.get_durationInMilis() + " [ms]");
                }
            }
            if (printInfoLevel > 0) {
                if (printInfoLevel > 1) {
                    System.out.println("===========================================================");
                }
                System.out.println(
                        "Benchmarking " + repetitions + " repetitions of QuickSort took "
                                + timer.get_overallDurationInMilis()
                                + " [ms]");
                System.out.println(
                        "Average from benchmarking " + repetitions + " repetitions of QuickSort took "
                                + timer.get_overallDurationInMilis() / repetitions
                                + " [ms]");
                if (printInfoLevel > 1) {
                    System.out.println("===========================================================");
                }
            }
            return correct;
        }

        private static boolean benchmarkQuickSortForLargeSetOfIntegersReverseOrder(int printInfoLevel) {
            List<List<Integer>> array = SortingAlgorithmsBenchmark_Provider.benchmarkSortForLargeSetOfIntegersNaturalOrderUniformDistribution_Provider();
            return sort(array, SortingAlgorithms.QuickSort::sort
                    , "Benchmark QuickSort for Large Set of Uniformly Distributed Integers in reverse order. Run:"
                    , "benchmarkQuickSortForLargeSetOfIntegersReverseOrder", printInfoLevel);
        }


        private static boolean benchmarkQuickSortForLargeSetOfDoublesReverseOrder(int printInfoLevel) {
            List<List<Double>> array = SortingAlgorithmsBenchmark_Provider.benchmarkSortForLargeSetOfDoublesNaturalOrderUniformDistribution_Provider();
            return sort(array, SortingAlgorithms.QuickSort::sort
                    , "Benchmark QuickSort for Large Set of Uniformly Distributed Doubles in reverse order. Run:"
                    , "benchmarkQuickSortForLargeSetOfDoublesReverseOrder", printInfoLevel);
        }


        private static boolean benchmarkQuickSortForLargeSetOfIntegersRandomOrder(int printInfoLevel) {
            List<List<Double>> array = SortingAlgorithmsBenchmark_Provider.benchmarkSortForLargeSetOfDoublesNaturalOrderUniformDistribution_Provider();
            return sort(array, SortingAlgorithms.QuickSort::sort
                    , "Benchmark QuickSort for Large Set of huge amplitudes of Integers in random order. Run:"
                    , "benchmarkQuickSortForLargeSetOfIntegersRandomOrder", printInfoLevel);
        }


        private static boolean benchmarkQuickSortForLargeSetOfDoublesRandomOrder(int printInfoLevel) {
            List<List<Double>> array = SortingAlgorithmsBenchmark_Provider.benchmarkSortForLargeSetOfDoublesNaturalOrderUniformDistribution_Provider();
            return sort(array, SortingAlgorithms.QuickSort::sort
                    , "Benchmark QuickSort for Large Set of huge amplitudes of Doubles in random order. Run:"
                    , "benchmarkQuickSortForLargeSetOfDoublesRandomOrder", printInfoLevel);
        }

        private static boolean benchmarkQuickSortForLargeSetOfIntegersNaturalOrder(int printInfoLevel) {
            List<List<Double>> array = SortingAlgorithmsBenchmark_Provider.benchmarkSortForLargeSetOfDoublesNaturalOrderUniformDistribution_Provider();
            return sort(array, SortingAlgorithms.QuickSort::sort
                    , "Benchmark QuickSort for Large Set of uniformly distributed Integers in natural order. Run:"
                    , "benchmarkQuickSortForLargeSetOfIntegersNaturalOrder", printInfoLevel);
        }

        private static boolean benchmarkQuickSortForLargeSetOfDoublesNaturalOrder(int printInfoLevel) {
            List<List<Double>> array = SortingAlgorithmsBenchmark_Provider.benchmarkSortForLargeSetOfDoublesNaturalOrderUniformDistribution_Provider();
            return sort(array, SortingAlgorithms.QuickSort::sort
                    , "Benchmark QuickSort for Large Set of uniformly distributed Doubles in natural order. Run:"
                    , "benchmarkQuickSortForLargeSetOfDoublesNaturalOrder", printInfoLevel);
        }
    }

    public static class BucketSortBenchmark {

        public static boolean benchmarkBucketSort(int printInfoLevel, int repetitions) {
            boolean correct = true;
            Timer timer = new Timer();
            for (int i = 0; i < repetitions; i++) {
                if (printInfoLevel > 2) {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Start BucketSort Benchmark repetition " + (i + 1)
                            + " ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                }
                timer.start();
                if (!benchmarkBucketSortForLargeSetOfIntegersReverseOrder(printInfoLevel)) {
                    correct = false;
                }
                if (!benchmarkBucketSortForLargeSetOfDoublesReverseOrder(printInfoLevel)) {
                    correct = false;
                }
                if (!benchmarkBucketSortForLargeSetOfIntegersRandomOrder(printInfoLevel)) {
                    correct = false;
                }
                if (!benchmarkBucketSortForLargeSetOfDoublesRandomOrder(printInfoLevel)) {
                    correct = false;
                }
                if (!benchmarkBucketSortForLargeSetOfIntegersNaturalOrder(printInfoLevel)) {
                    correct = false;
                }
                if (!benchmarkBucketSortForLargeSetOfDoublesNaturalOrder(printInfoLevel)) {
                    correct = false;
                }
                timer.stop();
                if (printInfoLevel > 1) {
                    if (printInfoLevel > 2) {
                        System.out.println(
                                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ end BucketSort Benchmark repetition " + (i + 1)
                                        + " ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    }
                    System.out.println("Benchmarking BucketSort (rep: " + (i + 1) + "/" + repetitions + ") took "
                            + timer.get_durationInMilis() + " [ms]");
                }
            }
            if (printInfoLevel > 0) {
                if (printInfoLevel > 1) {
                    System.out.println("===========================================================");
                }
                System.out.println(
                        "Benchmarking " + repetitions + " repetitions of BucketSort took "
                                + timer.get_overallDurationInMilis()
                                + " [ms]");
                System.out.println(
                        "Average from benchmarking " + repetitions + " repetitions of BucketSort took "
                                + timer.get_overallDurationInMilis() / repetitions
                                + " [ms]");
                if (printInfoLevel > 1) {
                    System.out.println("===========================================================");
                }
            }
            return correct;
        }

        private static boolean benchmarkBucketSortForLargeSetOfIntegersReverseOrder(int printInfoLevel) {
            List<List<Integer>> array = SortingAlgorithmsBenchmark_Provider.benchmarkSortForLargeSetOfIntegersNaturalOrderUniformDistribution_Provider();
            return sort(array, SortingAlgorithms.BucketSort::sort
                    , "Benchmark BucketSort for Large Set of Uniformly Distributed Integers in reverse order. Run:"
                    , "benchmarkBucketSortForLargeSetOfIntegersReverseOrder", printInfoLevel);
        }


        private static boolean benchmarkBucketSortForLargeSetOfDoublesReverseOrder(int printInfoLevel) {
            List<List<Double>> array = SortingAlgorithmsBenchmark_Provider.benchmarkSortForLargeSetOfDoublesNaturalOrderUniformDistribution_Provider();
            return sort(array, SortingAlgorithms.BucketSort::sort
                    , "Benchmark BucketSort for Large Set of Uniformly Distributed Doubles in reverse order. Run:"
                    , "benchmarkBucketSortForLargeSetOfDoublesReverseOrder", printInfoLevel);
        }


        private static boolean benchmarkBucketSortForLargeSetOfIntegersRandomOrder(int printInfoLevel) {
            List<List<Double>> array = SortingAlgorithmsBenchmark_Provider.benchmarkSortForLargeSetOfDoublesNaturalOrderUniformDistribution_Provider();
            return sort(array, SortingAlgorithms.BucketSort::sort
                    , "Benchmark BucketSort for Large Set of huge amplitudes of Integers in random order. Run:"
                    , "benchmarkBucketSortForLargeSetOfIntegersRandomOrder", printInfoLevel);
        }


        private static boolean benchmarkBucketSortForLargeSetOfDoublesRandomOrder(int printInfoLevel) {
            List<List<Double>> array = SortingAlgorithmsBenchmark_Provider.benchmarkSortForLargeSetOfDoublesNaturalOrderUniformDistribution_Provider();
            return sort(array, SortingAlgorithms.BucketSort::sort
                    , "Benchmark BucketSort for Large Set of huge amplitudes of Doubles in random order. Run:"
                    , "benchmarkBucketSortForLargeSetOfDoublesRandomOrder", printInfoLevel);
        }


        private static boolean benchmarkBucketSortForLargeSetOfIntegersNaturalOrder(int printInfoLevel) {
            List<List<Double>> array = SortingAlgorithmsBenchmark_Provider.benchmarkSortForLargeSetOfDoublesNaturalOrderUniformDistribution_Provider();
            return sort(array, SortingAlgorithms.BucketSort::sort
                    , "Benchmark BucketSort for Large Set of uniformly distributed Integers in natural order. Run:"
                    , "benchmarkBucketSortForLargeSetOfDoublesNaturalOrder", printInfoLevel);
        }


        private static boolean benchmarkBucketSortForLargeSetOfDoublesNaturalOrder(int printInfoLevel) {
            List<List<Double>> array = SortingAlgorithmsBenchmark_Provider.benchmarkSortForLargeSetOfDoublesNaturalOrderUniformDistribution_Provider();
            return sort(array, SortingAlgorithms.BucketSort::sort
                    , "Benchmark BucketSort for Large Set of uniformly distributed Doubles in natural order. Run:"
                    , "benchmarkBucketSortForLargeSetOfDoublesNaturalOrder", printInfoLevel);
        }
    }

    public static class CollectionsSortBenchmark {
        public static boolean benchmarkCollectionsSort(int printInfoLevel, int repetitions) {
            boolean correct = true;
            Timer timer = new Timer();
            for (int i = 0; i < repetitions; i++) {
                if (printInfoLevel > 2) {
                    System.out.println(
                            "~~~~~~~~~~~~~~~~~~~~~~~~~~ Start Collections::sort Benchmark repetition " + (i + 1)
                                    + " ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                }
                timer.start();
                if (!benchmarkCollectionsSortForLargeSetOfIntegersReverseOrder(printInfoLevel)) {
                    correct = false;
                }
                if (!benchmarkCollectionsSortForLargeSetOfDoublesReverseOrder(printInfoLevel)) {
                    correct = false;
                }
                if (!benchmarkCollectionsSortForLargeSetOfIntegersRandomOrder(printInfoLevel)) {
                    correct = false;
                }
                if (!benchmarkCollectionsSortForLargeSetOfDoublesRandomOrder(printInfoLevel)) {
                    correct = false;
                }
                if (!benchmarkCollectionsSortForLargeSetOfIntegersNaturalOrder(printInfoLevel)) {
                    correct = false;
                }
                if (!benchmarkCollectionsSortForLargeSetOfDoublesNaturalOrder(printInfoLevel)) {
                    correct = false;
                }
                timer.stop();
                if (printInfoLevel > 1) {
                    if (printInfoLevel > 2) {
                        System.out.println(
                                "~~~~~~~~~~~~~~~~~~~~~~~~~~~ End Collections::sort Benchmark repetition " + (i + 1)
                                        + " ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    }
                    System.out.println("Benchmarking Collections::sort (rep: " + (i + 1) + "/" + repetitions + ") took "
                            + timer.get_durationInMilis() + " [ms]");
                }
            }
            if (printInfoLevel > 0) {
                if (printInfoLevel > 1) {
                    System.out.println("===========================================================");
                }
                System.out.println(
                        "Benchmarking " + repetitions + " repetitions of Collections::sort took "
                                + timer.get_overallDurationInMilis()
                                + " [ms]");
                System.out.println(
                        "Average from benchmarking " + repetitions + " repetitions of Collections::sort took "
                                + timer.get_overallDurationInMilis() / repetitions
                                + " [ms]");
                if (printInfoLevel > 1) {
                    System.out.println("===========================================================");
                }
            }
            return correct;
        }

        private static boolean benchmarkCollectionsSortForLargeSetOfIntegersReverseOrder(int printInfoLevel) {
            List<List<Integer>> array = SortingAlgorithmsBenchmark_Provider.benchmarkSortForLargeSetOfIntegersReverseOrderUniformDistribution_Provider();
            return sort(array, Collections::sort
                    , "Benchmark CollectionsSort for Large Set of Uniformly Distributed Integers in reverse order. Run:"
                    , "benchmarkCollectionsSortForLargeSetOfIntegersReverseOrder", printInfoLevel);
        }

        private static boolean benchmarkCollectionsSortForLargeSetOfDoublesReverseOrder(int printInfoLevel) {
            List<List<Double>> array = SortingAlgorithmsBenchmark_Provider.benchmarkSortForLargeSetOfDoublesReverseOrderUniformDistribution_Provider();
            return sort(array, Collections::sort
                    , "Benchmark CollectionsSort for Large Set of Uniformly Distributed Doubles in reverse order. Run:"
                    , "benchmarkCollectionsSortForLargeSetOfDoublesReverseOrder", printInfoLevel);

        }


        private static boolean benchmarkCollectionsSortForLargeSetOfIntegersRandomOrder(int printInfoLevel) {
            List<List<Integer>> array = SortingAlgorithmsBenchmark_Provider.benchmarkSortForLargeSetOfIntegersRandomOrderWithHugeGaps_Provider();
            return sort(array, Collections::sort
                    , "Benchmark CollectionsSort for Large Set of huge amplitudes of Integers in random order. Run:"
                    , "benchmarkCollectionsSortForLargeSetOfIntegersRandomOrder", printInfoLevel);

        }


        private static boolean benchmarkCollectionsSortForLargeSetOfDoublesRandomOrder(int printInfoLevel) {
            List<List<Double>> array = SortingAlgorithmsBenchmark_Provider.benchmarkSortForLargeSetOfDoublesRandomOrderWithHugeGaps_Provider();
            return sort(array, Collections::sort
                    , "Benchmark CollectionsSort for Large Set of huge amplitudes of Doubles in random order. Run:"
                    , "benchmarkCollectionsSortForLargeSetOfDoublesRandomOrder", printInfoLevel);

        }


        private static boolean benchmarkCollectionsSortForLargeSetOfIntegersNaturalOrder(int printInfoLevel) {
            List<List<Integer>> array = SortingAlgorithmsBenchmark_Provider.benchmarkSortForLargeSetOfIntegersNaturalOrderUniformDistribution_Provider();
            return sort(array, Collections::sort
                    , "Benchmark CollectionsSort for Large Set of uniformly distributed Integers in natural order. Run:"
                    , "benchmarkCollectionsSortForLargeSetOfIntegersNaturalOrder", printInfoLevel);

        }


        private static boolean benchmarkCollectionsSortForLargeSetOfDoublesNaturalOrder(int printInfoLevel) {
            List<List<Double>> array = SortingAlgorithmsBenchmark_Provider.benchmarkSortForLargeSetOfDoublesNaturalOrderUniformDistribution_Provider();
            return sort(array, Collections::sort
                    , "Benchmark CollectionsSort for Large Set of uniformly distributed Doubles in natural order. Run:"
                    , "benchmarkCollectionsSortForLargeSetOfDoublesNaturalOrder", printInfoLevel);

        }
    }

    public static class ArraysSortBenchmark {

        public static boolean benchmarkArraysSort(int printInfoLevel, int repetitions) {
            boolean correct = true;
            Timer timer = new Timer();
            for (int i = 0; i < repetitions; i++) {
                if (printInfoLevel > 2) {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~ Start Arrays::sort Benchmark repetition " + (i + 1)
                            + " ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                }
                timer.start();
                if (!benchmarkArraysSortForLargeSetOfIntegersReverseOrder(printInfoLevel)) {
                    correct = false;
                }
                if (!benchmarkArraysSortForLargeSetOfDoublesReverseOrder(printInfoLevel)) {
                    correct = false;
                }
                if (!benchmarkArraysSortForLargeSetOfIntegersRandomOrder(printInfoLevel)) {
                    correct = false;
                }
                if (!benchmarkArraysSortForLargeSetOfDoublesRandomOrder(printInfoLevel)) {
                    correct = false;
                }
                if (!benchmarkArraysSortForLargeSetOfIntegersNaturalOrder(printInfoLevel)) {
                    correct = false;
                }
                if (!benchmarkArraysSortForLargeSetOfDoublesNaturalOrder(printInfoLevel)) {
                    correct = false;
                }
                timer.stop();
                if (printInfoLevel > 1) {
                    if (printInfoLevel > 2) {
                        System.out.println(
                                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ End Arrays::sort Benchmark repetition " + (i + 1)
                                        + " ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    }
                    System.out.println("Benchmarking Arrays::sort (rep: " + (i + 1) + "/" + repetitions + ") took "
                            + timer.get_durationInMilis() + " [ms]");
                }
            }
            if (printInfoLevel > 0) {
                if (printInfoLevel > 1) {
                    System.out.println("===========================================================");
                }
                System.out.println(
                        "Benchmarking " + repetitions + " repetitions of Arrays::sort took "
                                + timer.get_overallDurationInMilis()
                                + " [ms]");
                System.out.println(
                        "Average from benchmarking " + repetitions + " repetitions of Arrays::sort took "
                                + timer.get_overallDurationInMilis() / repetitions
                                + " [ms]");
                if (printInfoLevel > 1) {
                    System.out.println("===========================================================");
                }
            }
            return correct;
        }

        private static boolean benchmarkArraysSortForLargeSetOfIntegersReverseOrder(int printInfoLevel) {
            List<int[]> array = SortingAlgorithmsBenchmark_Provider.benchmarkSortForLargeSetOfIntegersReverseOrderUniformDistribution_ArrayWrapperProvider();
            return sort(array, Arrays::sort
                    , "Benchmark ArraysSort for Large Set of Uniformly Distributed Integers in reverse order. Run:"
                    , "benchmarkArraysSortForLargeSetOfIntegersReverseOrder", printInfoLevel);

        }

        private static boolean benchmarkArraysSortForLargeSetOfDoublesReverseOrder(int printInfoLevel) {
            List<double[]> array = SortingAlgorithmsBenchmark_Provider.benchmarkSortForLargeSetOfDoublesReverseOrderUniformDistribution_ArrayWrapperProvider();
            return sort(array, Arrays::sort
                    , "Benchmark ArraysSort for Large Set of Uniformly Distributed Doubles in reverse order. Run:"
                    , "benchmarkArraysSortForLargeSetOfDoublesReverseOrder", printInfoLevel);

        }

        private static boolean benchmarkArraysSortForLargeSetOfIntegersRandomOrder(int printInfoLevel) {
            List<int[]> array = SortingAlgorithmsBenchmark_Provider.benchmarkSortForLargeSetOfIntegersRandomOrderWithHugeGaps_ArrayWrapperProvider();
            return sort(array, Arrays::sort
                    , "Benchmark ArraysSort for Large Set of huge amplitudes of Integers in random order. Run:"
                    , "benchmarkArraysSortForLargeSetOfIntegersRandomOrder", printInfoLevel);

        }

        private static boolean benchmarkArraysSortForLargeSetOfDoublesRandomOrder(int printInfoLevel) {
            List<double[]> array = SortingAlgorithmsBenchmark_Provider.benchmarkSortForLargeSetOfDoublesRandomOrderWithHugeGaps_ArrayWrapperProvider();
            return sort(array, Arrays::sort
                    , "Benchmark ArraysSort for Large Set of huge amplitudes of Doubles in random order. Run:"
                    , "benchmarkArraysSortForLargeSetOfDoublesRandomOrder", printInfoLevel);

        }

        private static boolean benchmarkArraysSortForLargeSetOfIntegersNaturalOrder(int printInfoLevel) {
            List<int[]> array = SortingAlgorithmsBenchmark_Provider.benchmarkSortForLargeSetOfIntegersNaturalOrderUniformDistribution_ArrayWrapperProvider();
            return sort(array, Arrays::sort
                    , "Benchmark ArraysSort for Large Set of uniformly distributed Integers in natural order. Run:"
                    , "benchmarkArraysSortForLargeSetOfIntegersNaturalOrder", printInfoLevel);

        }

        private static boolean benchmarkArraysSortForLargeSetOfDoublesNaturalOrder(int printInfoLevel) {
            List<double[]> array = SortingAlgorithmsBenchmark_Provider.benchmarkSortForLargeSetOfDoublesNaturalOrderUniformDistribution_ArrayWrapperProvider();
            return sort(array, Arrays::sort
                    , "Benchmark ArraysSort for Large Set of uniformly distributed Doubles in natural order. Run:"
                    , "benchmarkArraysSortForLargeSetOfDoublesNaturalOrder", printInfoLevel);

        }
    }

    private interface SortingListAlgorithm {
        <T extends Number & Comparable<? super T>> void sort(List<T> array);
    }

    private interface SortingIntArrayAlgorithm {
        void sort(int[] array);
    }

    private interface SortingDoubleArrayAlgorithm {
        void sort(double[] array);
    }

    private static <T extends Number & Comparable<? super T>> boolean sort(List<List<T>> array, SortingListAlgorithm sortingAlgorithm, String message, String callerName, int printInfoLevel) {
        boolean correct = true;
        for (int i = 0; i < array.size(); i++) {
            if (printInfoLevel > 2) {
                System.out.println(
                        message + " "
                                + i);
            }
            List<T> data = array.get(i);
            s_timer.start();
            sortingAlgorithm.sort(data);
            s_timer.stop();
            if (isSorted(data)) {
                if (printInfoLevel > 2) {
                    System.out.println("\tTime: " + s_timer.get_durationInMilis() + " [ms]");
                }
            } else {
                System.out.println(callerName + " failed to sort " + i
                        + " set of data");
                correct = false;
            }
        }
        return correct;
    }

    private static boolean sort(List<int[]> array, SortingIntArrayAlgorithm sortingAlgorithm, String message, String callerName, int printInfoLevel) {
        boolean correct = true;
        for (int i = 0; i < array.size(); i++) {
            if (printInfoLevel > 2) {
                System.out.println(
                        message + " "
                                + i);
            }
            int[] data = array.get(i);
            s_timer.start();
            sortingAlgorithm.sort(data);
            s_timer.stop();
            if (isSorted(data)) {
                if (printInfoLevel > 2) {
                    System.out.println("\tTime: " + s_timer.get_durationInMilis() + " [ms]");
                }
            } else {
                System.out.println(callerName + " failed to sort " + i
                        + " set of data");
                correct = false;
            }
        }
        return correct;
    }

    private static boolean sort(List<double[]> array, SortingDoubleArrayAlgorithm sortingAlgorithm, String message, String callerName, int printInfoLevel) {
        boolean correct = true;
        for (int i = 0; i < array.size(); i++) {
            if (printInfoLevel > 2) {
                System.out.println(
                        message + " "
                                + i);
            }
            double[] data = array.get(i);
            s_timer.start();
            sortingAlgorithm.sort(data);
            s_timer.stop();
            if (isSorted(data)) {
                if (printInfoLevel > 2) {
                    System.out.println("\tTime: " + s_timer.get_durationInMilis() + " [ms]");
                }
            } else {
                System.out.println(callerName + " failed to sort " + i
                        + " set of data");
                correct = false;
            }
        }
        return correct;
    }

    private static <T extends Comparable<? super T>> boolean isSorted(List<T> array) {
        for (int i = 0; i < array.size() - 1; i++) {
            if (array.get(i).compareTo(array.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }

    private static <T extends Comparable<? super T>> boolean isSorted(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i].compareTo(array[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] - array[i + 1] > 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean isSorted(double[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] - array[i + 1] > 0) {
                return false;
            }
        }
        return true;
    }
}
