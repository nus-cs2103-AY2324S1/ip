package duke.storage;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {

    @Test
    void file_absentFile_createsNewFile() {
        String path = "data/test.txt";
        File file = new File(path);
        assertFalse(file.exists());
        Storage storage = new Storage(path);
        assertTrue(file.exists());
    }

    @Test
    void file_absentFolder_createNewFolder() {
        String path = "testdata/test.txt";
        File folder = new File("testdata");
        assertFalse(folder.isDirectory());
        Storage storage = new Storage(path);
        assertTrue(folder.exists());
    }

}
