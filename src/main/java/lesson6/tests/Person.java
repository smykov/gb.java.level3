package lesson6.tests;


public class Person {

    private final String name;
    private final Department department;

    public Person(String name, Department department) {
        this.name = name;
        this.department = department;
    }

    public String getDepartmentName() {
        return department.getName();
    }

}
