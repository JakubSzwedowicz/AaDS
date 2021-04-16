package Algorithms;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Jakub Szwedowicz
 * @version 1.0
 * date: 14.04.2021
 * email: kuba.szwedowicz@gmail.com
 */


public class SortingAlgorithms {

    public static class BucketSort {
        // In fact it is possible to sort for example Strings with BucketSort.
        // It all comes down to HOW they are supposed to be sorted. i.e by string length.
        public static <T extends Number & Comparable<? super T>> void sort(List<T> array) {
            final int size = (int) Math.sqrt(array.size());
            final List<List<T>> buckets = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                buckets.add(new ArrayList<>(buckets.size()));
            }
            divideToBuckets(array, buckets);
            for (List<T> l : buckets) {
                insertionSort(l);
            }
            takeFromBuckets(array, buckets);
        }

        private static int hashFunction(double value, double max, int bucketsNumber) {
            return Math.max(0, (int) ((value / max) * (bucketsNumber - 1)));
        }

        private static <T extends Number & Comparable<? super T>> void divideToBuckets(List<T> array, List<List<T>> buckets) {
            final double max = Collections.max(array).doubleValue();
            for (T e : array) {
                int index = hashFunction(e.doubleValue(), max, buckets.size());
                buckets.get(index).add(e);
            }
        }

        private static <T extends Number & Comparable<? super T>> void takeFromBuckets(List<T> array, List<List<T>> buckets) {
            int index = 0;
            for (List<T> l : buckets) {
                for (T e : l) {
                    array.set(index++, e);
                }
            }
        }

        private static <T extends Comparable<? super T>> void insertionSort(List<T> array) {
            for (int i = 1; i < array.size(); i++) {
                T e = array.get(i);
                int j = 0;
                for (j = i - 1; j >= 0 && array.get(j).compareTo(e) > 0; j--) {
                    Collections.swap(array, j, j + 1);
                }
                array.set(j + 1, e);
            }
        }

    }

    public static class QuickSort {
        // The algorithm is in place!
        public static <T extends Comparable<? super T>> void sort(List<T> array) {
            sortHelper(array, 0, array.size());
        }

        private static <T extends Comparable<? super T>> void sortHelper(List<T> array, int begin, int end) {
            if (begin < end - 1) {
                int pivot = partition(array, begin, end);
                sortHelper(array, begin, pivot);
                sortHelper(array, pivot + 1, end);
            }
        }

        private static <T extends Comparable<? super T>> int partition(List<T> array, int begin, int end) {
            // Parametered magic five algorithm with argument 'width' which doesn't have to be 5.
            int pivot_index = Pivot.getPivot(array, begin, end, 3);
            T pivot = array.get(pivot_index);
            int j = begin;
            for (int i = begin; i < end; i++) {
                if (array.get(i).compareTo(pivot) < 0) {
                    if(pivot_index == j){
                        pivot_index = i;
                    }
                    Collections.swap(array, i, j);
                    j++;
                }
            }
            Collections.swap(array, j, pivot_index);
            return j;
        }

        private static class Pivot {
            public static <T extends Comparable<? super T>> int getPivot(List<T> array, int begin, int end, int width) {
                // method based on magic five algorithm, in place
                if(end - begin <= width + (width >> 1)){
                    return begin + (end - begin) / 2;
                }
                int last = 0;
                for (int i = begin; i <= end - width; i += width) {
                    last = i + width;
                    insertionSort(array, i, last, 1);
                }
                if((end - begin) % width != 0) {
                    insertionSort(array, last, end, 1);
                }
                insertionSort(array, begin + width / 2, end, width);
                return getMedianFromSorted(array, begin, end, width);
            }


            private static <T extends Comparable<? super T>> int getMedianFromSorted(List<T> array, int begin, int end, int offset) {
                // for begin = 0, end = 38, width = 5, so size = 38
                // Assuming [x0, x1, ... , xn-1] of size = 38 WAS sorted on each range equal offset
                // so that ranges [x0, x1, ... x4], [x5, x6, ... , x9], ... , [x35, x36, x37] WERE sorted separately.
                // The function looks for the median within NOW sorted:
                // [x2, x7, x12, x17, x22, x27, x32, x37], medianSize = 8
                // So here, because medianSize is even, from [x17, x22], INDEX of x22 is returned
                // so returned integer equals 22

                // Slower equation
//                int medianSize = (int) Math.ceil((double) (end - begin) / offset);
                int diff = end - begin;
                // Faster equation
                int medianSize = (diff) / offset + ((diff % offset == 0) ? 0 : 1);
                return begin + (offset / 2) + offset * (medianSize / 2);
            }

            // bc it uses offset then depending whether offset == 1 or offset != 1 it behaves somewhat to shellSort
            private static <T extends Comparable<? super T>> void insertionSort(List<T> array, int begin, int end, int offset) {
                for (int i = begin + offset; i < end; i += offset) {
                    T e = array.get(i);
                    int j = 0;
                    for (j = i - offset; j >= 0 && array.get(j).compareTo(e) > 0; j -= offset) {
                        Collections.swap(array, j, j + offset);
                    }
                    array.set(j + offset, e);
                }
            }
        }
    }

    public static class DefaultQuickSortWithSeparateComparator{
        public static <T> void sort(List<T> array, Comparator<? super T> comparator) {
            sortHelper(array, comparator, 0, array.size() - 1);
        }

        public static <T extends Comparable<? super T>> void sort(List<T> array) {
            // Different things to achieve the same
//            Comparator<? super T> comp = (T f, T s) -> {return f.compareTo(s);};
            Comparator<? super T> comp = T::compareTo;
//            Comparator<? super T> comp = (e1, e2) -> ((Comparable<T>)e1).compareTo(e2);
            sort(array, comp);
        }

        private static <T> void sortHelper(List<T> array, Comparator<? super T> comparator, int begin, int end) {
            if (begin < end) {
                int pivot = partition(array, comparator, begin, end);
                sortHelper(array, comparator, begin, pivot - 1);
                sortHelper(array, comparator, pivot + 1, end);
            }
        }

        private static <T> int partition(List<T> array, Comparator<? super T> comparator, int begin, int end) {
            T pivot = array.get(end);
            int j = 0;
            for(int i = 0; i < end; i++){
                if(comparator.compare(array.get(i), pivot) < 0){
                    Collections.swap(array, j, i);
                    j++;
                }
            }
            Collections.swap(array, j, end);
            return j;
        }

    }

    public static class DefaultQuickSort{
        public static <T extends Comparable<? super T>> void sort(List<T> array) {
            sortHelper(array, 0, array.size() - 1);
        }

        private static <T extends Comparable<? super T>> void sortHelper(List<T> array, int begin, int end) {
            if (begin < end) {
                int pivot = partition(array, begin, end);
                sortHelper(array, begin, pivot - 1);
                sortHelper(array, pivot + 1, end);
            }
        }

        private static <T extends Comparable<? super T>> int partition(List<T> array, int begin, int end) {
            T pivot = array.get(end);
            int j = 0;
            for(int i = 0; i < end; i++){
                if(array.get(i).compareTo(pivot) < 0){
                    Collections.swap(array, j, i);
                    j++;
                }
            }
            Collections.swap(array, j, end);
            return j;
        }

    }

    public static void testClass() {
        // Just run tests
    }
}
