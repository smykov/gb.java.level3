package lesson1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        container();
        pair();
        numbers();
        collections();
        comparator();

    }

    private static void comparator() {
        ArrayList<String> strings = new ArrayList<>();
        Collections.addAll(strings, "e", "", "sdfgrs", "ab", "cd", "a", "e", "ae", "abc", "eee");
        System.out.println(strings);

        strings.sort((o1, o2) -> {
            int diff = o2.length() - o1.length();
            if (diff != 0){
                return diff;
            }

            return o1.compareTo(o2);
        });
        System.out.println(strings);
    }

    private static void collections() {
        ArrayList<String> strings = new ArrayList<>();
        Collections.addAll(strings, "a", "b", "c", "d");
//        strings.add("a");
//        strings.add("b");
//        strings.add("c");
//        strings.add("d");

        System.out.println(strings.get(2));


    }

    private static void numbers() {
        NumberContainer<Integer> emptyNumberContainer = new NumberContainer<>();
        NumberContainer<Integer> nullNumberContainer = new NumberContainer<>(null);
//        NumberContainer<String> numbers3 = new NumberContainer<>("adsda", "asdaad");

        Integer[] numberArray = new Integer[]{1, 2, 3, 4, 5};
        NumberContainer<Integer> arrayBasedNumberContainer = new NumberContainer<>(numberArray);

        NumberContainer<Integer> varargsBasedNumberContainer = new NumberContainer<>(1, 2, 3, 4, 5);
        System.out.println(varargsBasedNumberContainer.sum());

    }

    private static void pair() {
        String str = "asdasda";
        Integer integer = 2;

        Pair<String, Integer> pair = new Pair<String, Integer>(str, integer);

        String first = pair.getFirst();

        Pair<?, ?> pairWithoutTypes = new Pair<>("qwasd", 4);
        Object first1 = pairWithoutTypes.getFirst();

    }

    private static void container() {
//        Integer integer = new Integer(2);
//        Container integerContainer = new Container(integer);
//
//        integerContainer.setItem(new String("sdfer"));
//
//        Object item = integerContainer.getItem();
//        System.out.println(((Integer) item).intValue());
//
//        Container<String> stringContainer = new Container<>("asdsaf");
//
//        stringContainer.setItem(new Integer(2));
//
//        String item = stringContainer.getItem();
    }
}
