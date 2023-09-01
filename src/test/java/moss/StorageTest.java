package moss;

import moss.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The StorageTest class contains unit tests for the Storage class.
 */

public class StorageTest {

    /**
     * Tests the loading and saving of tasks using the Storage class.
     *
     * @throws MossException If there's an issue with loading or saving tasks.
     */
    @Test
    public void testLoadAndSaveTasks() throws MossException {
        // Create sample tasks
        List<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("Buy groceries"));
        tasks.add(new Deadline("Finish homework", LocalDate.parse("2023-09-15")));
        tasks.add(new Event("Party", LocalDate.parse("2023-09-20"), LocalDate.parse("2023-09-22")));
        tasks.get(0).markDone();

        // Create a storage instance
        Storage storage = new Storage();

        // Save tasks
        storage.saveTasks(tasks);

        // Load tasks
        List<Task> loadedTasks = storage.loadTasks();

        // Check if tasks are loaded correctly
        assertEquals(3, loadedTasks.size());

        assertEquals("Buy groceries", loadedTasks.get(0).description);
        assertTrue(loadedTasks.get(0).isDone);

        assertEquals("Finish homework", loadedTasks.get(1).description);
        assertFalse(loadedTasks.get(1).isDone);
        assertEquals(LocalDate.parse("2023-09-15"), ((Deadline) loadedTasks.get(1)).date);

        assertEquals("Party", loadedTasks.get(2).description);
        assertFalse(loadedTasks.get(2).isDone);
        assertEquals(LocalDate.parse("2023-09-20"), ((Event) loadedTasks.get(2)).fromDate);
        assertEquals(LocalDate.parse("2023-09-22"), ((Event) loadedTasks.get(2)).toDate);
    }
}


