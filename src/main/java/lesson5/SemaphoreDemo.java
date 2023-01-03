package lesson5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    public static void main(String[] args) {
        Shop shop = new Shop(6);

        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 1; i <= 7; i++) {
            pool.submit(() -> {
                long threadId = Thread.currentThread().getId();
                ThreadUtils.log("Пытаюсь войти");
                shop.enter(threadId);
                ThreadUtils.log("Вошел");

                ThreadUtils.sleep(2, 7);

                ThreadUtils.log("Вышел");
                shop.left(threadId);
            });
        }

        for (int i = 0; i < 15; i++) {
            ThreadUtils.sleep(1);
            shop.printVisitors();
        }

        pool.shutdown();
    }

    static class Shop {
        private final List<Long> visitors;
        private final Semaphore semaphore;

        public Shop(int capacity) {
            semaphore = new Semaphore(capacity, true);
            visitors = Collections.synchronizedList(new ArrayList<>());
        }

        public void enter(long id) {
            try {
                semaphore.acquire(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            visitors.add(id);
        }

        public void left(long id) {
            visitors.remove(id);
            semaphore.release(2);
        }

        public void printVisitors() {
            System.out.println("Текущие покупатели: " + visitors);
        }
    }

}
