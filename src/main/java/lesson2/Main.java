package lesson2;

import java.sql.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    private static Connection connection;

    public static void main(String[] args) {
        try {
            connection = getConnection();

            executeQueryExample();
            executeUpdateExample();
            executeDeleteExample();
            executeInsertExample();
            executeInsertBatchExample();

            if (connection != null) {
                connection.close();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:src/main/resources/database.db");
    }

    private static void executeInsertBatchExample() throws SQLException {
        System.out.println("Пакетное создание записей:");
        try (Connection connection = getConnection()) {
            String insertQueryTemplate = "insert into students(name, score) values(?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQueryTemplate);

            for (int i = 0; i < 3000; i++) {
                int score = ThreadLocalRandom.current().nextInt(50, 100);
                preparedStatement.setString(1, "User-" + (i + 1));
                preparedStatement.setLong(2, score);

                preparedStatement.addBatch();
            }

            long start = System.currentTimeMillis();
            int[] ints = preparedStatement.executeBatch();
            int insertRowsCount = ints.length;
            System.out.println("Добавлено строк: " + insertRowsCount);
            System.out.println("Потрачено времени: " + (System.currentTimeMillis() - start));
        }

    }

    private static void executeInsertExample() throws SQLException {
        System.out.println("Создание записей по одному:");
        Statement statement = connection.createStatement();

        String insertQueryTemplate = "insert into students(name, score) values('%s', %s)";

        long total = 0;
        int insertRowsCount = 0;
        for (int i = 0; i < 3000; i++) {
            int score = ThreadLocalRandom.current().nextInt(50, 100);
            String insertQuery = String.format(insertQueryTemplate, "User-" + (i + 1), score);

            long start = System.currentTimeMillis();
            insertRowsCount += statement.executeUpdate(insertQuery);
            total += System.currentTimeMillis() - start;
        }


        System.out.println("Добавлено строк: " + insertRowsCount);
        System.out.println("Потрачено времени: " + (total));
    }

    private static void executeDeleteExample() throws SQLException {
        Statement statement = connection.createStatement();
        int deleteRowsCount = statement.executeUpdate("delete from students where name = 'Петя' or name like 'User-%'");

        System.out.println("Удалено строк: " + deleteRowsCount);
    }

    private static void executeUpdateExample() throws SQLException {
        Statement statement = createStatement();
        int updateRowsCount = statement.executeUpdate("update students set score = 70 where name = 'Вася'");

        System.out.println("Изменено строк: " + updateRowsCount);

    }

    private static void executeQueryExample() throws SQLException {
        Statement statement = createStatement();
        ResultSet resultSet = statement.executeQuery("select * from students");

        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String score = resultSet.getString("score");

            System.out.println(String.format("[%s] %s - %s", id, name, score));

        }
    }

    private static Statement createStatement() throws SQLException {
        return connection.createStatement();
    }
}
