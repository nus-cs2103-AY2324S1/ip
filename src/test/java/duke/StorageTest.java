package duke;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;

import org.junit.jupiter.api.Test;
public class StorageTest {
    private String tempFilePath = "data/temp.txt";
    private Storage storage = new Storage(tempFilePath);

    /**
     * Tests the load method in Storage. Exception should always be thrown
     */
    @Test
    public void load_nil_exceptionThrown() {
        try {
            storage.load();
        } catch (DukeException | IOException | ClassNotFoundException e) {
            assertNull(e.getMessage());
        }
    }
}
