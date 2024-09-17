package duke.task;

import duke.storage.Storage;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {

    @Test
    void file_absentFile_createsNewFile() {
        String path = "data/test.txt";
        File file = new File(path);
        Storage storage = new Storage(path);
        assertTrue(file.exists());
    }

}