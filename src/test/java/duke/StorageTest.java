package duke;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
public class StorageTest {
    private Path tempFilePath = Paths.get("data/temp.txt");
    private Storage storage = new Storage(tempFilePath);

    /**
     * Tests the load method in Storage. Exception should always be thrown
     */
    @Test
    public void load_nil_exceptionThrown() {
        try {
            storage.load();
        } catch (IOException | ClassNotFoundException e) {
            assertNull(e.getMessage());
        }
    }
}
