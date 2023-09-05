package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

class StorageTest {

    @Test
    void testLoad() {
        Storage storage = new Storage("taskList.txt");
        File loadedFile = storage.load();
        assertNotNull(loadedFile);
        assertEquals("taskList.txt", loadedFile.getName());
    }
}

