package lesson5;

import java.util.concurrent.*;

public class CountDownLatchDemo {

    public static void main(String[] args) {
        int count = 4;
        CountDownLatch cdl = new CountDownLatch(4);

        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            pool.submit(() -> {
                ThreadUtils.log("����� �� ����");
                ThreadUtils.sleep(2, 7);
                ThreadUtils.log("������� � ����� �����");

                cdl.countDown();
            });
        }

        try {
            cdl.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("��� � �����");

        pool.shutdown();
    }

}
