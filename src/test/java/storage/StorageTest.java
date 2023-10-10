package storage;

import exception.DukeException;
import org.junit.jupiter.api.Test;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void testLoadAndSave() throws DukeException {
        // Define a test file path (change as needed)
        String filePath = "./data/dukeTest.txt";
        Storage storage = new Storage(filePath);

        // Create a list of sample tasks
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Test Task 1"));
        tasks.add(new Deadline("Test Deadline", LocalDateTime.of(2023, 12, 31, 23, 59)));
        tasks.add(new Event("Test Event", LocalDateTime.of(2023, 1, 1, 0, 0),LocalDateTime.of(2023, 12, 31, 23, 59)));

        // Save the tasks to the file
        storage.save(tasks);

        // Load the tasks from the file
        ArrayList<Task> loadedTasks = storage.load();

        // Check if loaded tasks match the original tasks
        assertEquals(tasks.size(), loadedTasks.size());
        for (int i = 0; i < tasks.size(); i++) {
            assertEquals(tasks.get(i).getDescription(), loadedTasks.get(i).getDescription());
        }
    }
}
