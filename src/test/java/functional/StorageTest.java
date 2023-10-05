package functional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import tasks.Task;

//Solution below adapted from https://github.com/woojiahao/ip
class StorageTest {

    @Test
    public void testLoadFileCreation() throws DukeException {
        File testFile = new File("test_data/test.json");
        Storage storage = new Storage("test_data/test.json");
        storage.load();
        assertTrue(testFile.exists());
        testFile.delete();
    }


    @Test
    public void testLoadFileCreationNested() throws DukeException {
        File testFile = new File("test_data/nested/test.json");
        Storage storage = new Storage("test_data/nested/test.json");
        storage.load();
        assertTrue(testFile.exists());
        testFile.delete();
    }

    @Test
    public void load_emptyFile_pass() throws DukeException, IOException {
        File testFile = new File("test_data/test.json");
        testFile.getParentFile().mkdirs();
        testFile.createNewFile();
        Storage storage = new Storage("test_data/test.json");
        List<Task> tasks = storage.load();
        assertEquals(0, tasks.size());
        testFile.delete();
    }
}
