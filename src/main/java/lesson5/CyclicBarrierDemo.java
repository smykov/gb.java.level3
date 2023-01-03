package lesson5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierDemo {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        String[] places = new String[]{
                "Звездная ночь", "Черный квадрат", "Мона Лиза"
        };

        int count = 4;
        ExecutorService group = Executors.newFixedThreadPool(count);
        CyclicBarrier barrier = new CyclicBarrier(count + 1);

        for (String place : places) {
            System.out.println("Осматриваем картину \"" + place + "\" ...");
            for (int i = 0; i < count; i++) {
                group.submit(() -> {
                    ThreadUtils.log("Осматриваю картину...");
                    ThreadUtils.sleep(2, 7); // время осмотра картины
                    ThreadUtils.log("Закончил осмотр");

                    try {
                        barrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        throw new RuntimeException(e);
                    }

                });
            }

            barrier.await();
            System.out.println("Переходим к следующей картине...");
        }

        System.out.println("Общий осмотр закончен");

        group.shutdown();
    }

}
