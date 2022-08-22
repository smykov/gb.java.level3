package hw1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer[] result = swap(array, 4, 8);
        System.out.println(Arrays.toString(result));

        List resultList = new ArrayList<>();
        resultList = getArrayList(array);


    }

    private static List getArrayList(Integer[] array) {
        List<Integer> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, array);

        return arrayList;
    }

    private static Integer[] swap(Integer[] array, int firstIndex, int secondIndex) {
        int firstIndexValue = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = firstIndexValue;

        return array;
    }
}
