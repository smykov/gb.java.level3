package lesson5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class ReentrantReadWriteLockDemo {

    public static void main(String[] args) throws IOException, InterruptedException {
        Path path = Path.of("demo.txt");
        Files.writeString(path, "Blank file");

        FileAccessor fileAccessor = new FileAccessor(path);

        Runnable reader = () -> {
            ThreadUtils.log("Начинаю чтение из файла");
            // alt + enter
            fileAccessor.readFromFile(bytes -> {
                ThreadUtils.log("Прочитал " + bytes.length + " байт. Содержимое: [" + new String(bytes) + "]");
            });

            ThreadUtils.log("Закончил чтение из файла");
        };

        Runnable writer = () -> {
            long threadId = Thread.currentThread().getId();
            final String text = "Поток #" + threadId + " был здесь";
            ThreadUtils.log("Начинаю запись в файл");
            // alt + enter
            fileAccessor.writeToFile(text::getBytes);
            ThreadUtils.log("Записал данные в файл: [" + text + "]");
        };

        List<Thread> threads = Stream.of(writer, reader, reader, reader, reader, writer, reader)
                .map(Thread::new)
                .peek(Thread::start)
                .toList();

        for (Thread thread : threads) {
            thread.join();
        }
    }

    static class FileAccessor {

        private final Path path;
        private final ReentrantReadWriteLock lock;

        public FileAccessor(Path path) {
            this.path = path;
            lock = new ReentrantReadWriteLock();
        }

        public void readFromFile(Consumer<byte[]> reader) {
            Objects.requireNonNull(reader);
            try {
                lock.readLock().lock();
                ThreadUtils.sleep(5); // имитируем долгое чтение
                byte[] bytes = Files.readAllBytes(path);
                reader.accept(bytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                lock.readLock().unlock();
            }
        }

        public void writeToFile(Supplier<byte[]> writer) {
            Objects.requireNonNull(writer);
            try {
                lock.writeLock().lock();
                ThreadUtils.sleep(5); // имитируем долгую запись
                Files.write(path, writer.get());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                lock.writeLock().unlock();
            }
        }

    }


}
