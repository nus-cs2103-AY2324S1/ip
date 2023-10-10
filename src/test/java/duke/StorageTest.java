package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class StorageTest {
    private static final String TEST_FILE_PATH = "./src/main/data/tasklist.txt";
    private Storage storage;

    @BeforeEach
    public void setUp() {
        storage = new Storage(TEST_FILE_PATH);
    }

    @AfterEach
    public void tearDown() {
        // Delete the test file after each test to ensure no leftover data
        File testFile = new File(TEST_FILE_PATH);
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    public void testPrintFileContents_fileNotFound() {
        String result = storage.printFileContents();
        assertEquals("Error: There are no items in the list!", result);
    }
    @Test
    public void testLoadTasks_fileDoesNotExist() throws DukeException {
        storage.loadTasks();

        File testFile = new File(TEST_FILE_PATH);
        assertTrue(testFile.exists());
    }

    @Test
    public void testLoadTasks_directoryDoesNotExist() throws DukeException {
        // Simulating directory not existing scenario by providing a path with a directory
        Storage directoryTestStorage = new Storage("testDir/" + TEST_FILE_PATH);
        directoryTestStorage.loadTasks();

        File testFile = new File("testDir/" + TEST_FILE_PATH);
        assertTrue(testFile.exists());
    }

    // Note: You may need to adjust the TaskList mock and its behavior based on your actual TaskList implementation.
}
