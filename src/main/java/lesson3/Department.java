package lesson3;

import java.io.Serial;
import java.io.Serializable;

public class Department implements Serializable {
    @Serial
    private static final long serialVersionUID = -8198090651370403615L;
    private String name;

    public Department(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                '}';
    }
}
