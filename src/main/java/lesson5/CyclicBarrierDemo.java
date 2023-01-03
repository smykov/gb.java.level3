package lesson5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierDemo {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        String[] places = new String[]{
                "�������� ����", "������ �������", "���� ����"
        };

        int count = 4;
        ExecutorService group = Executors.newFixedThreadPool(count);
        CyclicBarrier barrier = new CyclicBarrier(count + 1);

        for (String place : places) {
            System.out.println("����������� ������� \"" + place + "\" ...");
            for (int i = 0; i < count; i++) {
                group.submit(() -> {
                    ThreadUtils.log("���������� �������...");
                    ThreadUtils.sleep(2, 7); // ����� ������� �������
                    ThreadUtils.log("�������� ������");

                    try {
                        barrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        throw new RuntimeException(e);
                    }

                });
            }

            barrier.await();
            System.out.println("��������� � ��������� �������...");
        }

        System.out.println("����� ������ ��������");

        group.shutdown();
    }

}
