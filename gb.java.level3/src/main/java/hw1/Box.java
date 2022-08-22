package hw1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Box<T extends Fruit> {
    private List<T> fruits;

    public Box(T ... fruits) {
        this.fruits = new ArrayList<>(Arrays.asList(fruits));
    }

    double GetWeight() {
        return fruits.stream()
                .map(Fruit::getWeight)
                .reduce(Double::sum)
                .get();
    }

    void moveTo(Box<? super T> box) {
        box.addAll(fruits);
        fruits.clear();
    }

    void addAll(List<? extends T> fruits) {
        this.fruits.addAll(fruits);
    }
    void addFruit(T... fruits) {
        this.fruits.addAll(Arrays.asList(fruits));
    }
    public boolean compare(Box<? extends T> box) {
        return this.GetWeight() == box.GetWeight();
    }

}
