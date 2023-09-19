package duke.storage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Tag;
import java.util.ArrayList;

import duke.ui.Ui;
import duke.task.TaskList;

public class StorageTest {
    @Test
    @Tag("Basic test")
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    @Tag("Basic test")
    public void storage_initialisation_success() {
        Storage storage = new Storage(new Ui());
        assertEquals(2, 2);
    }

    @Test
    @Tag("readFile test")
    public void readFile_nonEmptyFile_success() {
        Storage storage = new Storage(new Ui());
        try {
            assertTrue(storage.readFile() instanceof ArrayList<?>);
        } catch (Exception e) {
            assertTrue(false);
        }
    }
}
