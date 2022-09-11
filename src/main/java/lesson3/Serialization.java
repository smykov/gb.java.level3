package lesson3;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Serialization {
    public static void main(String[] args) {
        Employee originalEmployee = new Employee("John", new Department("department"));
        System.out.println(originalEmployee);

        Path employeeStore = Path.of("files", "employee.txt");

//        try (OutputStream outputStream = Files.newOutputStream(employeeStore)) {
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
//            objectOutputStream.writeObject(originalEmployee);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        Object restoredEmployee;
        try (InputStream inputStream = Files.newInputStream(employeeStore)) {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            restoredEmployee = (Employee) objectInputStream.readObject();


        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println(restoredEmployee);

    }
}
