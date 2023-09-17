package seedu.duke;

import dukeexception.DukeException;
import filestorage.FileStorage;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class FileStorageTest {

    @Test
    public void testingFileSuccess() {
        FileStorage storage = new FileStorage("testing.txt");
        try {
            assertEquals(storage.read().size(), 6);
        } catch (DukeException e) {
            fail();
        }
    }
    @Test
    public void emptyFile_success() {
        FileStorage storage = new FileStorage("nothing.txt");
        try {
            assertEquals(storage.read().size(), 0);
        } catch (DukeException e) {
            fail();
        }
    }
}
