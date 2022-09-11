package lesson5;

import java.util.concurrent.ThreadLocalRandom;

public class ThreadUtils {
    public ThreadUtils() {
    }

    public static void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sleep(int min, int max) {
        sleep(min + ThreadLocalRandom.current().nextInt(max - min));
    }

    public static void log(String text) {
        long threadId = Thread.currentThread().getId();
        System.out.println("#" + threadId + " " + text);
    }
}
