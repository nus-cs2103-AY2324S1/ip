package arona.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import arona.task.DeadlineTask;
import arona.task.EventTask;
import arona.task.Task;




/**
 * Unit tests for the Storage class.
 */
public class StorageTest {
    private static final String TEST_FILE_PATH = "test_tasks.txt";

    /**
     * Sets up a test file before each test.
     */
    @BeforeEach
    public void setUp() {
        // Create a test file for each test
        File testFile = new File(TEST_FILE_PATH);
        try {
            if (!testFile.exists()) {
                testFile.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests loading tasks from a valid input file.
     */
    @Test
    public void loadTasksTest() {
        Storage storage = new Storage(TEST_FILE_PATH);
        ArrayList<Task> tasks = new ArrayList<>();

        // Add some test data to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_FILE_PATH))) {
            writer.write("T|0|Task 1");
            writer.newLine();
            writer.write("D|0|Task 2|2023-09-15");
            writer.newLine();
            writer.write("E|0|Task 3|from: 2023-09-15|to: 2023-09-16");
        } catch (IOException e) {
            e.getMessage();
        }

        storage.loadTasks(tasks);

        // Verify that the tasks were loaded correctly
        assertEquals(3, tasks.size());
        assertEquals("Task 1", tasks.get(0).getDescription());
        assertEquals("Task 2", tasks.get(1).getDescription());
        assertEquals(LocalDate.of(2023, 9, 15), ((DeadlineTask) tasks.get(1)).getDate());
        assertEquals("Task 3", ((EventTask) tasks.get(2)).getDescription());
        assertEquals("from: 2023-09-15", ((EventTask) tasks.get(2)).getFrom());
        assertEquals("to: 2023-09-16", ((EventTask) tasks.get(2)).getTo());
    }

    /**
     * Tests updating the status of a task to marked.
     */
    @Test
    public void updateTaskStatusAsMarkedTest() {
        Storage storage = new Storage(TEST_FILE_PATH);

        // Add some test data to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_FILE_PATH))) {
            writer.write("T|0|Task 1");
            writer.newLine();
            writer.write("D|0|Task 2|2023-09-15");
            writer.newLine();
            writer.write("E|0|Task 3|2023-09-15|2023-09-16");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Mark the second task as done (index 1)
        storage.updateTaskStatusAsMarked(1);

        // Verify that the task status was updated
        ArrayList<Task> tasks = new ArrayList<>();
        storage.loadTasks(tasks);
        assertEquals("[X] ", tasks.get(1).getStatusIcon());
    }

    /**
     * Tests updating the status of a task to unmarked.
     */
    @Test
    public void updateTaskStatusAsUnmarkedTest() {
        Storage storage = new Storage(TEST_FILE_PATH);

        // Add some test data to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_FILE_PATH))) {
            writer.write("T|1|Task 1"); // Task 1 is already marked as done
            writer.newLine();
            writer.write("D|0|Task 2|2023-09-15");
            writer.newLine();
            writer.write("E|1|Task 3|2023-09-15|2023-09-16"); // Task 3 is already marked as done
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Unmark the first task (index 0)
        storage.updateTaskStatusAsUnmarked(0);

        // Verify that the task status was updated
        ArrayList<Task> tasks = new ArrayList<>();
        storage.loadTasks(tasks);
        assertEquals("[ ] ", tasks.get(0).getStatusIcon());
    }

    /**
     * Tests deleting a task from the storage.
     */
    @Test
    public void deleteTaskTest() {
        Storage storage = new Storage(TEST_FILE_PATH);

        // Add some test data to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_FILE_PATH))) {
            writer.write("T|0|Task 1");
            writer.newLine();
            writer.write("D|0|Task 2|2023-09-15");
            writer.newLine();
            writer.write("E|0|Task 3|2023-09-15|2023-09-16");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Delete the second task (index 1)
        storage.deleteTask(1);

        // Verify that the task was deleted
        ArrayList<Task> tasks = new ArrayList<>();
        storage.loadTasks(tasks);
        assertEquals(2, tasks.size());
        assertEquals("Task 1", tasks.get(0).getDescription());
        assertEquals("Task 3", tasks.get(1).getDescription());
    }
}
