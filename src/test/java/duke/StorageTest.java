package duke;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    private final String testFilePath = "data/testTasks.txt";
    private Storage storage;

    @BeforeEach
    public void setUp() {
        storage = new Storage(testFilePath);
    }

    @AfterEach
    public void tearDown() {
        File file = new File(testFilePath);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testSaveTasks() {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.add(new Todo("read book"));

        assertDoesNotThrow(() -> storage.saveTasks(taskList));
    }

    @Test
    public void testLoadTasks() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Todo todo = new Todo("read book");
        taskList.add(todo);

        try {
            storage.saveTasks(taskList);

            ArrayList<Task> loadedTasks = storage.loadTasks();
            assertNotNull(loadedTasks);
            assertEquals(1, loadedTasks.size());
            assertEquals(todo.toString(), loadedTasks.get(0).toString());

        } catch (Exception e) {
            fail("Should not have thrown any exception.");
        }
    }
}
