package lesson4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsTest {
    public static void main(String[] args) {

        //фиксированое количество потоков, и обработка в с ожиданием освобождения потока
        fixedThreadPool(4);
        //потоки создаются новые когда все имеющиеся заняты
        cachedThreadPool();

    }

    private static void fixedThreadPool(int nThreads) {
        ExecutorService treadManager = Executors.newFixedThreadPool(nThreads);
        for (int i = 1; i <= 10; i++) {
            int k = i;
            treadManager.submit(() -> {
                //task
                System.out.println(Thread.currentThread().getName() + ": " + k);
            });
        }
    }

    private static void cachedThreadPool() {
        ExecutorService treadManager = Executors.newCachedThreadPool();
        for (int i = 1; i <= 10; i++) {
            int k = i;
            treadManager.submit(() -> {
                //task
                System.out.println(Thread.currentThread().getName() + ": " + k);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
