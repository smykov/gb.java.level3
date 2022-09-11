package lesson5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {
    public static void main(String[] args) {
        int count = 4;
        CountDownLatch countDownLatch = new CountDownLatch(count);

        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            pool.submit(() -> {
                ThreadUtils.log("Вышел из дома");
                ThreadUtils.sleep(2, 7);
                ThreadUtils.log("Подошел к месту сбора");

                countDownLatch.countDown();
            });
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Все в сборе");

        pool.shutdown();
    }
}
