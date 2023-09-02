package duke;

import duke.Storage;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {

    private Storage storage;

    @BeforeEach
    public void setUp() {
        // Create a temporary test directory
        String testDir = "test_data";
        File tempDir = new File(testDir);
        if (!tempDir.exists()) {
            tempDir.mkdir();
        }
        storage = new Storage(testDir + "/test.txt");
    }

    @Test
    public void testSaveToDo() throws IOException {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("Task 1", "0"));
        tasks.add(new ToDo("Task 2", "1"));

        storage.saveTasks(tasks);

        List<String> lines = Files.readAllLines(Paths.get("test_data/test.txt"));
        assertEquals("[T | 0 | Task 1, T | 1 | Task 2]", lines.toString()); // Check if the file contains the expected content
    }



}
