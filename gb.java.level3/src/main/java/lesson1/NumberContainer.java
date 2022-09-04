package lesson1;

public class NumberContainer<T extends Integer> {
    private T[] numbers;

    public NumberContainer(T... numbers) { //varargs
        this.numbers = numbers;
    }

    public int sum() {
        int sum = 0;
        for (T number : numbers) {
            sum += number.intValue();
        }
        return sum;
    }
}
