package lesson3;

import java.io.Serial;
import java.io.Serializable;

public class Employee implements Serializable {
    @Serial
    private static final long serialVersionUID = -8198090651370403615L;
    private static int counter = 1;
    private long id;
    private String name;
//     для исключения из сериализации
//    private transient Department department;

    private Department department;

    public Employee(String name, Department department) {
        id = counter++;
        this.name = name;
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department=" + department +
                '}';
    }
}
