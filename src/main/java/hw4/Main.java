package hw4;

class PrintChar {
    private char ch = 'A';

    public synchronized void print(char c) {
        while (ch != c) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.print(c);
        if (ch == 'C') {
            ch = 'A';
        } else {
            ch++;
        }
        notifyAll();
    }

    public static void main(String[] args) throws InterruptedException {
        PrintChar printChar = new PrintChar();
        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                printChar.print('A');
            }
        });
        Thread threadB = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                printChar.print('B');
            }
        });
        Thread threadC = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                printChar.print('C');
            }
        });
        threadA.start();
        threadB.start();
        threadC.start();
        threadA.join();
        threadB.join();
        threadC.join();
    }

}

