import java.util.Random;

/**
 * @Author Jakub Szwedowicz
 * @Create 23.03.2021
 * @Version 1.0
 */
public class Algorithms {
    private static Random generator = new Random();
    public static int kthSmallest(int[] tab, int kthSmallest) throws IllegalArgumentException{
        if(tab == null || tab.length == 0  || kthSmallest <= 0 || kthSmallest > tab.length){
            throw new IllegalArgumentException("Illegal parameters were passed!");
        }
        return kthSmallestHelper(tab, kthSmallest, 0, tab.length);
    }

    private static int kthSmallestHelper(int[] tab, int kthSmallest, int begin, int end){
        int smaller = begin;
        int equal = generator.nextInt(end - begin) + begin;
        int greater = end;
        int pivot = tab[equal];
        for(int i = begin; i != greater; i++){
            int number = tab[i];
            if(number < pivot){
                swap(tab, i, smaller++);
            } else if(number > pivot){
                swap(tab, i--, --greater);
            }
        }
        int smallerLength = smaller - begin;
        int equalLength = greater - smaller;
//        System.out.println("Begin = " + begin + "\nequal = " + equal + "\nend = " + end + "\npivot = " + pivot + "\nkth = " + kthSmallest);
        if(kthSmallest <= smallerLength){
            return kthSmallestHelper(tab, kthSmallest, begin, smaller);
        } else if(kthSmallest <= smallerLength + equalLength){
            return tab[smaller];
        } else {
            return kthSmallestHelper(tab, kthSmallest - (smallerLength + equalLength), greater, end);
        }
    }

    private static void swap(int[] tab, int a, int b){
        int k = tab[a];
        tab[a] = tab[b];
        tab[b] = k;
    }


}
