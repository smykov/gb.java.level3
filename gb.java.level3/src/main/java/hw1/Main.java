package hw1;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] result = swap(array, 4, 8);
        System.out.println(Arrays.toString(result));
    }

    private static int[] swap(int[] array, int firstIndex, int secondIndex) {
        int firstIndexValue = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = firstIndexValue;

        return array;
    }
}
