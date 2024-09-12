package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class StorageTest {

    private static final String TEST_FILE_PATH = "data/testDuke.txt";

    private Storage storage;

    @BeforeEach
    public void setUp() {
        storage = new Storage(TEST_FILE_PATH);
    }

    @AfterEach
    public void tearDown() throws Exception {
        Files.deleteIfExists(Paths.get(TEST_FILE_PATH));
    }

    @Test
    public void load_emptyFile_emptyList() throws FileNotFoundException {
        List<Task> tasks = storage.load();
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void saveAndLoad_singleTodoTask_loadedCorrectly() throws FileNotFoundException {
        TaskList taskList = new TaskList();
        Task task = new Todo("Test todo task");
        taskList.add(task);
        storage.save(taskList);

        List<Task> loadedTasks = storage.load();
        assertEquals(1, loadedTasks.size());
        assertEquals(task.toFileString(), loadedTasks.get(0).toFileString());
    }

    @Test
    public void saveAndLoad_singleDeadlineTask_loadedCorrectly() throws FileNotFoundException {
        TaskList taskList = new TaskList();
        Task task = new Deadline("Test deadline task", "2023-09-19 1200");
        taskList.add(task);
        storage.save(taskList);

        List<Task> loadedTasks = storage.load();
        assertEquals(1, loadedTasks.size());
        assertEquals(task.toFileString(), loadedTasks.get(0).toFileString());
    }

    @Test
    public void saveAndLoad_singleEventTask_loadedCorrectly() throws FileNotFoundException {
        TaskList taskList = new TaskList();
        Task task = new Event("Test event task", "2023-09-19 1200", "2023-09-19 1400");
        taskList.add(task);
        storage.save(taskList);

        List<Task> loadedTasks = storage.load();
        assertEquals(1, loadedTasks.size());
        assertEquals(task.toFileString(), loadedTasks.get(0).toFileString());
    }

    @Test
    public void saveAndLoad_markTaskAsDone_statusRetained() throws FileNotFoundException {
        TaskList taskList = new TaskList();
        Task task = new Todo("Test task");
        task.markDone();
        taskList.add(task);
        storage.save(taskList);

        List<Task> loadedTasks = storage.load();
        assertEquals(1, loadedTasks.size());
        assertTrue(loadedTasks.get(0).getStatus());
    }

    @Test
    public void saveAndLoad_markTaskAsNotDone_statusRetained() throws FileNotFoundException {
        TaskList taskList = new TaskList();
        Task task = new Todo("Test task");
        taskList.add(task);
        storage.save(taskList);

        List<Task> loadedTasks = storage.load();
        assertEquals(1, loadedTasks.size());
        assertFalse(loadedTasks.get(0).getStatus());
    }
}
