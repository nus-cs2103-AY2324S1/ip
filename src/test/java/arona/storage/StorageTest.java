package arona.storage;

import arona.storage.Storage;
import arona.task.DeadlineTask;
import arona.task.EventTask;
import arona.task.Task;
import arona.task.ToDoTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class StorageTest {
    private static final String TEST_FILE_PATH = "test_tasks.txt";

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

    @Test
    public void loadTasks_ValidInput_LoadsTasks() {
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

    @Test
    public void updateTaskStatusAsMarked_ValidIndex_TaskStatusUpdated() {
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

    @Test
    public void updateTaskStatusAsUnmarked_ValidIndex_TaskStatusUpdated() {
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
    @Test
    public void deleteTask_ValidIndex_TaskDeleted() {
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
