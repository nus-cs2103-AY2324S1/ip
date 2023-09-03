package storage;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.Storage;
import tasks.Deadline;
import tasks.Task;
import tasks.TaskList;
import tasks.ToDo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

public class StorageTest {

    private Storage storage;
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        // Set up a temporary test file
        String testFilePath = "testTasks.txt";
        storage = new Storage(testFilePath);
        taskList = new TaskList();
    }

    @Test
    public void testSaveTasksToFile() throws IOException {
        Task task1 = new ToDo("Task 1");
        taskList.add(task1);

        // Save the tasks to the test file
        storage.saveTasksToFile(taskList);

        List<String> lines = Files.readAllLines(Path.of("testTasks.txt"));
        assertEquals("T | 0 | Task 1", lines.get(0));
        Files.deleteIfExists(Path.of("testTasks.txt"));
    }

}
