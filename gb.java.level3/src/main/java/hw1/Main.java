package hw1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        task1();
        task2();

        Box<Orange> orangeBox = new Box<>(
                new Orange(),
                new Orange(),
                new Orange()
        );
        Box<Apple> appleBox = new Box<>(
                new Apple(),
                new Apple()
        );
        Box<GoldenApple> goldenAppleBox = new Box<>(
                new GoldenApple(),
                new GoldenApple(),
                new GoldenApple()
        );

        System.out.println(appleBox.getWeight());

        orangeBox.addFruit(new Orange());
        appleBox.addFruit(new GoldenApple());
        appleBox.addFruit(new Apple());

        System.out.println(appleBox.getWeight());

        goldenAppleBox.moveTo(appleBox);

        if (appleBox.compare(goldenAppleBox)) {
            System.out.println("Коробки с яблоками и апельсинами равны!");
        } else {
            System.out.println("Коробки с яблоками и апельсинами не равны!");
        }


    }

    private static void task2() {
        Integer[] arrayInts = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String[] arrayStr = new String[]{"a", "b", "c", "d", "e", "f", "g", "h"};
        List resultList = new ArrayList<>();
        resultList = getArrayList(arrayInts);
        resultList = getArrayList(arrayStr);
    }

    private static void task1() {
        Integer[] arrayInts = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(Arrays.toString(arrayInts));
        swap(arrayInts, 4, 8);
        System.out.println(Arrays.toString(arrayInts));

        String[] arrayStr = new String[]{"a", "b", "c", "d", "e", "f", "g", "h"};
        System.out.println(Arrays.toString(arrayStr));
        swap(arrayStr, 2, 5);
        System.out.println(Arrays.toString(arrayStr));
    }

    private static <T> List getArrayList(T[] array) {
        return Arrays.asList(array);
    }

    private static <T> void swap(T[] array, int firstIndex, int secondIndex) {
        if (
                (array == null)
                        || (firstIndex < 0)
                        || (secondIndex < 0)
                        || (firstIndex >= array.length)
                        || (secondIndex >= array.length)
        ) {
            throw new IllegalArgumentException();
        }

        T firstIndexValue = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = firstIndexValue;
    }
}
