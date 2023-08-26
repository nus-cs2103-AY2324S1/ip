package duke;

import duke.TaskList;
import duke.Ui;
import duke.task.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.ArrayList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StorageTest {
    private static final String TEST_FOLDER = "test_folder";
    private static final String TEST_FILE = "test_file.txt";

    private Storage storage;

    @BeforeEach
    public void setUp() {
        storage = new Storage(TEST_FOLDER, TEST_FILE);
    }

    @Test
    public void testLoadFile() {
        try {
            ArrayList<String> testData = new ArrayList<>();
            testData.add("Test line 1");
            testData.add("Test line 2");

            // Create a test file with some data
            File directory = new File(TEST_FOLDER);
            directory.mkdirs();
            File file = new File(directory, TEST_FILE);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (String line : testData) {
                    writer.write(line);
                    writer.newLine();
                }
            }

            ArrayList<String> loadedData = storage.loadFile();

            assertIterableEquals(testData, loadedData);
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void testSaveFile() {
        try {
            TaskList taskList = new TaskList();
            Ui ui = new Ui();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime time = LocalDateTime.parse("2023-08-25 12:30", formatter);

            ArrayList<Task> tasks = new ArrayList<>();
            tasks.add(new ToDo("read book"));
            tasks.add(new Deadline("return book", time));
            tasks.add(new Event("book event", time, time));

            for (Task task : tasks) {
                taskList.addTask(task, ui);
            }

            storage.saveFile(taskList);

            // Check if the file was saved correctly
            File directory = new File(TEST_FOLDER);
            File file = new File(directory, TEST_FILE);

            assertTrue(file.exists());

            ArrayList<String> loadedData = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    loadedData.add(line);
                }
            }

            ArrayList<String> savedData = new ArrayList<>();
            for (Task task : tasks) {
                savedData.add(task.toSaveLine());
            }

            assertIterableEquals(savedData, loadedData);
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }
}
