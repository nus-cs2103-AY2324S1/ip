package jarvis.storage;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import jarvis.exceptions.DukeException;
import jarvis.tasks.Deadline;
import jarvis.tasks.Event;
import jarvis.tasks.Task;
import jarvis.tasks.Todo;

public class StorageTest {

    //    @Test
    //    public void file_fileDoesntExist_createNewFile() {
    //        String path = "data/test.txt";
    //        File file = new File(path);
    //        assertFalse(file.exists());
    //        Storage storage = new Storage(path);
    //        assertTrue(file.exists());
    //    }

    //    @Test
    //    public void file_folderDoesntExist_createNewFolder() {
    //        String path = "testdata/test.txt";
    //        File folder = new File("testdata");
    //        assertFalse(folder.isDirectory());
    //        Storage storage = new Storage(path);
    //        assertTrue(folder.exists());
    //    }

    @Test
    public void file_readFile_correctTodo() {
        String path = "testdata/tasks.txt";
        Storage storage = new Storage(path);
        try {
            Task t = storage.stringToTask("T | O | do unit test");
            assertTrue(t instanceof Todo);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void file_readFile_correctDeadline() {
        String path = "testdata/tasks.txt";
        Storage storage = new Storage(path);
        try {
            Task t = storage.stringToTask("D | O | study unit test | Aug 15 2023 02:30 PM");
            assertTrue(t instanceof Deadline);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void file_readFile_correctEvent() {
        String path = "testdata/tasks.txt";
        Storage storage = new Storage(path);
        try {
            Task t = storage.stringToTask("E | O | give CS2103T test | Aug 27 2023 07:00 PM - "
                    + "Aug 27 2023 08:00 PM");
            assertTrue(t instanceof Event);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void file_readFile_incorrectEvent() {
        String path = "testdata/tasks.txt";
        Storage storage = new Storage(path);
        assertThrows(DukeException.class, () -> storage.stringToTask("E | XCP | | Aug 27 2023 07:00 PM "
                + "- Aug 27 2023 08:00 PM"));
    }

    @Test
    public void file_readFile_incorrectTodo() {
        String path = "testdata/tasks.txt";
        Storage storage = new Storage(path);
        assertThrows(DukeException.class, () -> storage.stringToTask("T | XYZ |"));
    }

    @Test
    public void file_readFile_incorrectDeadline() {
        String path = "testdata/tasks.txt";
        Storage storage = new Storage(path);
        assertThrows(DukeException.class, () -> storage.stringToTask("D | 0 | XYZZ"));
    }

}
