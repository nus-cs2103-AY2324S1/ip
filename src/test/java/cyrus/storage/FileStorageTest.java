package cyrus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.google.gson.GsonBuilder;

import cyrus.adapters.LocalDateAdapter;
import cyrus.tasks.Deadline;
import cyrus.tasks.Event;
import cyrus.tasks.Task;
import cyrus.tasks.ToDo;

public class FileStorageTest {
    @Test
    public void testEmptyFilePath() {
        Assertions.assertThrows(AssertionError.class, () -> new FileStorage("   "));
        Assertions.assertThrows(AssertionError.class, () -> new FileStorage(""));
    }

    @Test
    public void testLoadFileCreation() {
        FileStorage storage = new FileStorage("test_data/test.json");
        storage.load();
        File testFile = new File("test_data/test.json");
        assertTrue(testFile.exists());
        testFile.delete();
    }

    @Test
    public void testLoadFileCreationNested() {
        FileStorage storage = new FileStorage("test_data/nested/test.json");
        storage.load();
        File testFile = new File("test_data/nested/test.json");
        assertTrue(testFile.exists());
        testFile.delete();
    }

    @Test
    public void testLoadEmptyFile() throws IOException {
        File testFile = new File("test_data/test.json");
        testFile.getParentFile().mkdirs();
        testFile.createNewFile();
        FileStorage storage = new FileStorage("test_data/test.json");
        var tasks = storage.load();
        assertEquals(0, tasks.size());
        testFile.delete();
    }

    @Test
    public void testLoadWithValidData() throws IOException {
        List<Task> tasks = new ArrayList<>();
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        tasks.add(new ToDo("todo task"));
        tasks.add(new Deadline("deadline task", LocalDate.parse("16/08/2023", formatter)));
        tasks.add(
                new Event(
                        "event task",
                        LocalDate.parse("16/08/2023", formatter),
                        LocalDate.parse("19/09/2023", formatter)
                )
        );

        var gson = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.TRANSIENT)
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        File testFile = new File("test_data/test.json");
        testFile.getParentFile().mkdirs();
        testFile.createNewFile();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("test_data/test.json"))) {
            gson.toJson(tasks, bw);
        } catch (IOException e) {
            return;
        }

        FileStorage storage = new FileStorage("test_data/test.json");
        List<Task> retrievedTasks = storage.load();
        for (int i = 0; i < retrievedTasks.size(); i++) {
            assertEquals(tasks.get(i).toString(), retrievedTasks.get(i).toString());
        }

        testFile.delete();
    }

    @Test
    public void testLoadWithInvalidData() throws IOException {
        var gson = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.TRANSIENT)
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        File testFile = new File("test_data/test.json");
        testFile.getParentFile().mkdirs();
        testFile.createNewFile();
        List<MalformedData> malformed = Arrays.asList(
                new MalformedData("Peter"),
                new MalformedData("Bad")
        );

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("test_data/test.json"))) {
            gson.toJson(malformed, bw);
        } catch (IOException e) {
            return;
        }

        FileStorage fs = new FileStorage("test_data/test.json");

        assertThrows(IllegalStateException.class, fs::load);

        testFile.delete();
    }

    private static class MalformedData {
        private final String name;

        public MalformedData(String name) {
            this.name = name;
        }
    }
}
