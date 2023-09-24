package carbonbot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class StorageTest {
    private static final String TEST_PATH = "./cs2103-tmp/test/tasks.txt";

    @Test
    public void testWriteLoad() throws IOException {
        try {
            Storage storage = new Storage(TEST_PATH);
            storage.write("T | 1 | eat\nT | 1 | work\nT | 0 | sleep");

            List<String> expected = new ArrayList<>();
            expected.add("T | 1 | eat");
            expected.add("T | 1 | work");
            expected.add("T | 0 | sleep");

            assertEquals(expected, storage.load());
        } catch (Exception e) {
            fail();
        } finally {
            Files.delete(Path.of(TEST_PATH));
        }
    }

    @Test
    public void testWriteNonExistingFile() throws IOException {
        Path testPath = Path.of(TEST_PATH);
        // Delete file if already exists
        if (Files.exists(testPath)) {
            Files.delete(testPath);
        }

        // Check if storage is able to create the file and write to it
        Storage storage = new Storage(TEST_PATH);
        storage.write("T | 1 | eat");

        List<String> expected = new ArrayList<>();
        expected.add("T | 1 | eat");
        assertEquals(expected, storage.load());

        Files.delete(testPath);
    }

}
