package hw4;

import java.util.List;
import java.util.stream.IntStream;

class LetterContainer {
    private static final char FIRST = 'A';
    private static final char LAST = 'C';
    private char letter = 'A';

    public synchronized void tryPrint(char target) {
        while (letter != target) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.print(target);
        next();
        notifyAll();
    }

    private void next() {
        if (letter == LAST) {
            letter = FIRST;
        } else {
            letter++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final int repeat = 5;

        LetterContainer container = new LetterContainer();

        List<Thread> threads = IntStream.rangeClosed(FIRST, LAST)
                .mapToObj(it -> (char) it)
                .map(it -> new Thread(() -> {
                    for (int j = 0; j < repeat; j++) {
                        container.tryPrint(it);
                    }
                }))
                .peek(Thread::start)
                .toList();

        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println();
    }

}

