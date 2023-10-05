package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StorageTest {
    private static final String TEST_FILE_PATH = "./src/main/data/tasklist.txt";

    @BeforeEach
    public void setUp() throws IOException {
        // Delete the test file before each test to start with a clean slate
        Files.deleteIfExists(Paths.get(TEST_FILE_PATH));
    }

    @Test
    public void testLoadTasks() throws DukeException {
        // Create a Storage instance for testing
        Storage storage = new Storage(TEST_FILE_PATH);

        // Load tasks (this should create the test file if it doesn't exist)
        storage.loadTasks();

        // Check if the test file exists
        assertTrue(Files.exists(Paths.get(TEST_FILE_PATH)));
    }

    @Test
    public void testPrintFileContents() throws IOException {
        // Create a test file with some content
        FileWriter fw = new FileWriter(TEST_FILE_PATH);
        fw.write("Task 1\nTask 2\nTask 3\n");
        fw.close();

        // Create a Storage instance for testing
        Storage storage = new Storage(TEST_FILE_PATH);

        // Print the file contents
        String fileContents = storage.printFileContents();

        // Check if the printed contents match what's in the file
        assertEquals("Task 1\nTask 2\nTask 3\n", fileContents);
    }
}

