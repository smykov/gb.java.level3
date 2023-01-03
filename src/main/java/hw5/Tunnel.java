package hw5;

public class Tunnel extends Stage {
    public Tunnel() {
        this.length = 80;
        this.description = "������� " + length + " ������";
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " ��������� � �����(����): " +
                        description);
                System.out.println(c.getName() + " ����� ����: " + description);
                Thread.sleep(length / c.getSpeed() * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " �������� ����: " +
                        description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
