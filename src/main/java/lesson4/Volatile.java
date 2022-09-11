package lesson4;

public class Volatile {
    private static volatile int myInt = 0;

    public static void main(String[] args) {
        new ChangeListener().start();
        new ChangeMaker().start();
    }

    static class ChangeListener extends Thread {
        @Override
        public void run() {
            int localInt = myInt;
            while (myInt < 5) {
                if (localInt != myInt) {
                    localInt = myInt;
                    System.out.println(localInt + " t1");
                }
            }
        }
    }

    static class ChangeMaker extends Thread {
        @Override
        public void run() {
            int localInt = myInt;
            while (myInt < 5) {
                myInt = ++localInt;
                System.out.println(localInt + " t2");
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
