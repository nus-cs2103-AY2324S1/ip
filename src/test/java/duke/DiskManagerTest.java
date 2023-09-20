package duke;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DiskManagerTest {
    @Test
    public void loadFromDisk_loadDummyFile_success() throws DukeException {
        String expected = "[T][ ] play[D][ ] play (by: Aug 9 2023)[E][X] play (from: Apr 4 2023 to: May 5 2023)";
        assertEquals(expected,
                new DiskManager("src/test/resources", "tasks.json")
                        .loadFromDisk().toString());
    }

    @Test
    public void loadFromDisk_loadCorruptedFile_exceptionThrown() {
        try {
            new DiskManager("src/test/resources", "tasks_corrupted.json").loadFromDisk();
            fail();
        } catch (DukeException e) {
            assertEquals("Error when deserializing file", e.getMessage());
        }
    }

    @Test
    public void loadFromDisk_loadEmptyFile_success() throws DukeException {
        assertEquals("", new DiskManager("src/test/resources", "tasks_empty.json")
                .loadFromDisk().toString());
    }

    @Test
    public void loadFromDisk_loadNonExistingFile_success() throws DukeException {
        String filePath = "src/test/resources/dummy.json";
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }

        assertEquals("", new DiskManager("src/test/resources", "dummy.json")
                .loadFromDisk().toString());
    }
}
