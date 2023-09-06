package duke.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

public class StorageTest {
    @Test
    public void load_emptyFile_emptyList() {
        try {
            Storage storage = new Storage("empty.txt");
            List<String> lines = storage.load();
            assertEquals(0, lines.size());
        } catch (Exception e) {
            // the test should not reach this line
            fail();
        }
    }

    @Test
    public void save_lines_success() {
        try {
            Storage storage = new Storage("lines.txt");
            storage.load(); // to create the file
            List<String> lines = List.of("first line", "second line", "third line");
            storage.save(lines);
        } catch (Exception e) {
            // the test should not reach this line
            fail();
        }
    }
}
