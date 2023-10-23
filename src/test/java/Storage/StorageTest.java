package Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import URBOI_PACKIN.Storage.Storage;
import URBOI_PACKIN.Task;
import URBOI_PACKIN.TaskTypes.Deadline;
import URBOI_PACKIN.TaskTypes.Todo;
import URBOI_PACKIN.TaskTypes.Event;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {

    private static final String TEST_FILE_PATH = "test_tasks.txt";

    @BeforeEach
    public void setUp() {
        // Delete the test file if it exists before each test
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testSaveAndLoadTasks() {
        // Create some test tasks
        ArrayList<Task> tasksToSave = new ArrayList<>();
        tasksToSave.add(new Todo("Test Todo Task"));
        tasksToSave.add(new Deadline("Test Deadline Task", LocalDateTime.now()));
        tasksToSave.add(new Event("Test Event Task", "2023-10-23 10:00", "2023-10-23 12:00"));

        // Save tasks to the test file
        Storage.saveTasksToFile(tasksToSave);

        // Load tasks from the test file
        ArrayList<Task> loadedTasks = new ArrayList<>();
        Storage.loadTasksFromFile(loadedTasks);

        // Assert that the loaded tasks match the saved tasks
        assertEquals(tasksToSave.size(), loadedTasks.size());
        for (int i = 0; i < tasksToSave.size(); i++) {
            assertEquals(tasksToSave.get(i).toFileString(), loadedTasks.get(i).toFileString());
        }
    }


    @Test
    public void testCreateTaskFromLine() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String line = "T | 1 | Test Todo Task";

        // Use reflection to access the private method
        Method createTaskFromLine = Storage.class.getDeclaredMethod("createTaskFromLine", String.class);
        createTaskFromLine.setAccessible(true);

        // Call the private method to create a task
        Task task = (Task) createTaskFromLine.invoke(null, line);

        assertTrue(task instanceof Todo);
        assertEquals("Test Todo Task", task.getDescription());
        assertEquals(true, task.isDone());
    }
}
