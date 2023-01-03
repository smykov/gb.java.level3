package hw5;

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
        this.name = "�������� #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " ���������");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " �����");
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
    }
}
