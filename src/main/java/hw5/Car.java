package hw5;

import static hw5.MainClass.*;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private final Race race;
    private final int speed;
    private final String name;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    public void finished() {
        lock.lock();
        try {
            countFinished++;
            if (countFinished == 1) {
                System.out.println(this.name + " Выйграл гонку!!!");
            } else {
                System.out.println(this.name + " финишировал " + countFinished);
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            barrier.await(); //ждем когда все будут готовы

            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }
            finished();
            barrier.await(); //ждем когда все финишируют

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
