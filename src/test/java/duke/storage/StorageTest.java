package duke.storage;

import org.junit.jupiter.api.Test;

import java.io.File;

import duke.tasks.*;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    @Test
    public void testFileDoesntExistCreation() {
        String path = "data/test.txt";
        File file = new File(path);
        assertFalse(file.exists());
        Storage storage = new Storage(path);
        assertTrue(file.exists());
    }

    @Test
    public void testDirectoryDoesntExistCreation() {
        String path = "testdata/test.txt";
        File folder = new File("testdata");
        assertFalse(folder.isDirectory());
        Storage storage = new Storage(path);
        assertTrue(folder.exists());
    }

    @Test
    public void testReadFileTodo() {
        String path = "testdata/tasks.txt";
        Storage storage = new Storage(path);
        Task t = storage.stringToTask("T | O | do unit test");
        assertTrue(t instanceof Todo);
    }

    @Test
    public void testReadFileDeadline() {
        String path = "testdata/tasks.txt";
        Storage storage = new Storage(path);
        Task t = storage.stringToTask("D | O | study unit test | Aug 15 2023 02:30 PM");
        assertTrue(t instanceof Deadline);
    }

    @Test
    public void testReadFileEvent() {
        String path = "testdata/tasks.txt";
        Storage storage = new Storage(path);
        Task t = storage.stringToTask("E | O | give CS2103T test | Aug 27 2023 07:00 PM - Aug 27 2023 08:00 PM");
        assertTrue(t instanceof Event);
    }

}
