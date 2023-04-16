package lesson6.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class TestPerson {

    Department department;
    Person person;

    @BeforeEach
    void setup() {
        Department department = Mockito.mock(Department.class);
        person = new Person("name", department);
    }

    @Test
    void TestGetDepartmentGood() {
        Mockito.when(department.getName()).thenReturn("department-name");
        assertEquals("department-name", person.getDepartmentName());
    }

//    @Test
//    void TestGetDepartmentBad() {
//        Person p = new Person("name", new Department("department-name"));
//        assertEquals("department-name", p.getDepartmentName());
//    }

}