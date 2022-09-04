package lesson4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsTest {
    public static void main(String[] args) {

        ExecutorService treadManager = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10; i++) {
            int k = i;
            treadManager.submit(() -> {
                //task
                System.out.println(Thread.currentThread().getName() + ": " + k);
            });
        }
    }
}
