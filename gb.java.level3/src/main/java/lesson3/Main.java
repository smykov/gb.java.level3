package lesson3;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        //input output
        //старая библиотека  java.io.File
//        File file = new File("files/file.txt");
//        File notExistFile = new File("files/not_exist_file.txt");

        //Новая библиотека
        //java.nio.*
        //windows \\
        //unix /
        // можно так "files" . File.separator . "file.txt"
        //либо перечеслением через ,
        Path file = Path.of("files", "file.txt");

        System.out.println(Files.exists(file));
        System.out.println(Files.exists(Path.of("files/not_exist_file.txt")));

        deleteFile(file);

        createFile(file);


    }

    private static String readDataFromFileAsReader(Path file) {
        File oldFile = file.toFile();
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(oldFile, StandardCharsets.UTF_8);
            String result = new String();
            char[] buffer = new char[8];
            while (fileReader.read(buffer) != -1) {
                String s = new String(buffer);
                result += s;
            }

            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeDataToFile(Path file, String data) {
        try {
            OutputStream outputStream = Files.newOutputStream(file);

            outputStream.write(data.getBytes());

            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static String readDataFromFile(Path file) {
        try {
            InputStream inputStream = Files.newInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

            byte[] buffer = new byte[48];
            String result = "";
            while (bufferedInputStream.read(buffer) != -1) {
                String s = new String(buffer);
                result += s;

            }
            inputStream.close();

            return result;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String readTextFromFile(Path file) {

        try {
            return Files.readString(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean writeTextToFile(Path file) {
        try {
            Files.writeString(file, "adasd/nssdfgsg/nafsdfs");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    private static boolean createDeleteFile(Path file) {
        Path parent = file.getParent();
        if (!Files.exists(parent)) {
            try {
//                Files.createDirectory(parent);
                Files.createDirectories(parent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (Files.exists(file)) {
            try {
                Files.delete(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                Files.delete(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }

    private static Path createFile(Path path) {
        try {
            return Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void deleteFile(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
