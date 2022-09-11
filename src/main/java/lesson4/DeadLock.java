package lesson4;

public class DeadLock {

    static Object lock1 = new Object();
    static Object lock2 = new Object();

    public static void main(String[] args) {
        DeadThreadOne one = new DeadThreadOne();
        one.start();
        DeadThreadTwo two = new DeadThreadTwo();
        two.start();

    }

    public static class DeadThreadOne extends Thread {
        @Override
        public void run() {

            synchronized (lock1) {
                System.out.println("One держит lock1...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("One ждет lock2...");
                synchronized (lock2) {
                    System.out.println("One держит lock1 и lock2...");
                }
            }
        }
    }

    public static class DeadThreadTwo extends Thread {
        @Override
        public void run() {

            synchronized (lock2) {
                System.out.println("Two держит lock2...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Two ждет lock1...");
                synchronized (lock1) {
                    System.out.println("One держит lock1 и lock2...");
                }
            }
        }
    }
}
