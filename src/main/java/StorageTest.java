import DukePackage.Storage;
import DukePackage.Task;
import DukePackage.TaskType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    private Storage storage;

    @BeforeEach
    public void setUp() {
        storage = new Storage();
    }

    @Test
    public void testAddList() {
        Task task = new Task("Test task", TaskType.TODO, "", "");
        storage.addList(task);

        assertEquals(1, storage.getTaskList().size());
        assertEquals("Test task", storage.getTaskList().get(0).getDescription());
    }

    @Test
    public void testDelete() {
        Task task1 = new Task("Task 1", TaskType.TODO, "", "");
        Task task2 = new Task("Task 2", TaskType.TODO, "", "");
        storage.addList(task1);
        storage.addList(task2);

        storage.delete(0);

        assertEquals(1, storage.getTaskList().size());
        assertEquals("Task 2", storage.getTaskList().get(0).getDescription());
    }
}