import dukepackage.Storage;
import dukepackage.Task;
import dukepackage.TaskType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The StorageTest class is a JUnit test class for testing the Storage class.
 */
public class StorageTest {

    private Storage storage;

    /**
     * Sets up the test environment before each test case.
     */
    @BeforeEach
    public void setUp() {
        storage = new Storage();
    }

    /**
     * Tests the addList() method of the Storage class.
     */
    @Test
    public void testAddList() {
        Task task = new Task("Test task", TaskType.TODO, "", "");
        storage.addList(task);
        assertEquals(1, storage.getTaskList().size());
        assertEquals("Test task", storage.getTaskList().get(0).getDescription());
    }

    /**
     * Tests the delete() method of the Storage class.
     */
    @Test
    public void testDelete() {
        Task task1 = new Task("Task 1", TaskType.TODO, "", "");
        Task task2 = new Task("Task 2", TaskType.TODO, "", "");
        storage.addList(task1);
        storage.addList(task2);
        storage.deleteTask(0);
        assertEquals(1, storage.getTaskList().size());
        assertEquals("Task 2", storage.getTaskList().get(0).getDescription());
    }
}