package List_1;

import List_1.MyArrayList;

import java.util.Random;

public class Functions {
    // members
    private static Random s_generator = new Random();

    public static <T> void fillRandom(Number[] a_array, Class<T> a_type, double a_lowerBound, double a_upperBound) {
        fillRandom(a_array, a_array.length, a_type, a_lowerBound, a_upperBound);
    }

    public static <T> void fillRandom(Number[] a_array, int a_size, Class<T> a_type, double a_lowerBound, double a_upperBound) {
        for (int i = 0; i < a_size; i++) {
            double randomDouble = s_generator.nextDouble();
        }
    }

    public static double getRandomDouble() {
        return s_generator.nextDouble();
    }

    public static int getRandomInt(int a_upperBound) {
        return s_generator.nextInt(a_upperBound);
    }

    public double getRandomInt() {
        return s_generator.nextInt();
    }

}
