package lesson4;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            //task
        });

        thread.start(); // ����� ������

        thread.interrupt();

        thread.join(); // ����� ����� ����� ���� ���������� ����� �� ���������� ��� ��� �� ������� thread.interrupt();


    }

    public static class MyThread extends Thread {

        @Override
        public void run() {
            if (!isInterrupted()) {

            }
            //task
        }
    }
}
