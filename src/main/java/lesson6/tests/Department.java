package lesson6.tests;

public class Department {

    private String name;

    public Department(String name) {
        this.name = name;
        try {
            Thread.sleep(15 * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getName() {
        return name;
    }
}
