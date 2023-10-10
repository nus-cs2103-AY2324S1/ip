package duke;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class StorageTest {

    @Test
    void testLoadTasks() {
        // Test loading of tasks from file
        try {
            Path tempFilePath = Files.createTempFile(null, ".txt");
            Storage storage = new Storage(tempFilePath.toString());

            ArrayList<Task> expectedTasks = new ArrayList<>();
            expectedTasks.add(new ToDo("Task 1", true));
            expectedTasks.add(new Deadline("Deadline 1", false, "2023-08-29 1800"));
            expectedTasks.add(new Event("Event 1", true, "2023-08-30 0900", "2023-08-30 1200"));

            storage.saveTasks(expectedTasks);

            ArrayList<Task> loadedTasks = storage.loadTasks();

            assertEquals(expectedTasks.size(), loadedTasks.size());
            for (int i = 0; i < expectedTasks.size(); i++) {
                Task expectedTask = expectedTasks.get(i);
                Task loadedTask = loadedTasks.get(i);
                assertEquals(expectedTask.toSaveString(), loadedTask.toSaveString());
            }

            Files.deleteIfExists(tempFilePath);
        } catch (IOException e) {
            fail("Exception occurred during testing: " + e.getMessage());
        }
    }

    @Test
    void testSaveTasks() {
        try {
            // Test saving of todolist to file
            Path tempFilePath = Files.createTempFile(null, ".txt");
            Storage storage = new Storage(tempFilePath.toString());

            ArrayList<Task> tasksToSave = new ArrayList<>();
            tasksToSave.add(new ToDo("Task 1", true));
            tasksToSave.add(new Deadline("Deadline 1", false, "2023-08-29 1800"));
            tasksToSave.add(new Event("Event 1", true, "2023-08-30 0900", "2023-08-30 1200"));

            storage.saveTasks(tasksToSave);

            ArrayList<Task> loadedTasks = storage.loadTasks();

            assertEquals(tasksToSave.size(), loadedTasks.size());
            for (int i = 0; i < tasksToSave.size(); i++) {
                Task savedTask = tasksToSave.get(i);
                Task loadedTask = loadedTasks.get(i);
                assertEquals(savedTask.toSaveString(), loadedTask.toSaveString());
            }

            Files.deleteIfExists(tempFilePath);
        } catch (IOException e) {
            fail("Exception occurred during testing: " + e.getMessage());
        }
    }
}
