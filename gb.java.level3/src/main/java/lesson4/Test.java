package lesson4;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            //task
        });

        thread.start(); // старт потока

        thread.interrupt();

        thread.join(); // поток будет ждать пока запущенный поток не завершится или его не прервут thread.interrupt();


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
