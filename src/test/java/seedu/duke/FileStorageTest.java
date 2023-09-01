package seedu.duke;

import DukeException.DukeException;
import FileStorage.FileStorage;


import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class FileStorageTest {

    @Test
    public void testingFileSuccess() {
        FileStorage storage = new FileStorage("testing.txt");
        try {
            assertEquals(storage.read().size(), 5);
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
