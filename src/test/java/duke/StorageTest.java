package duke;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {
    File tempFile;

    @BeforeEach
    public void setUp() throws IOException {
        // Create a temporary file for testing purposes
        tempFile = File.createTempFile("test_data", ".txt");
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Clean up after test
        Files.deleteIfExists(tempFile.toPath());
    }

    @Test
    public void fileCreation_fileDoesNotExist_fileIsCreated() throws IOException {
        Storage storage = new Storage(tempFile);
        assertTrue(storage.file.exists());
    }

    @Test
    public void readFromFile_prePopulatedFile_taskListPopulated() throws IOException {
        // Populate the test file with sample data
        Files.write(tempFile.toPath(), List.of("T|1|Sample Task"));
        Storage storage = new Storage(tempFile);
        TaskList tl = new TaskList();
        storage.fileToTaskList(tl);

        // Check if the TaskList has been populated
        assertTrue(!tl.taskList.isEmpty());
        assertEquals("Sample Task", tl.taskList.get(0).name);
    }

    @Test
    public void writeToFile_taskList_tasksWrittenToFile() throws IOException {
        Storage storage = new Storage(tempFile);
        TaskList tl = new TaskList();
        tl.add(new ToDo(true, "Sample Task"));
        storage.taskListToFile(tl);

        // Read from file to verify
        List<String> lines = Files.readAllLines(tempFile.toPath());
        assertTrue(!lines.isEmpty());
        assertEquals("T|1|Sample Task", lines.get(0));
    }
}
