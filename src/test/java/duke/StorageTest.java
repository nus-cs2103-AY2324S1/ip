package duke;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;

import org.junit.jupiter.api.Test;
public class StorageTest {
    private Storage storage = new Storage();
    @Test
    public void load_nil_exceptionThrown() {
        try {
            storage.load();
        } catch (IOException | ClassNotFoundException e) {
            assertNull(e.getMessage());
        }
    }
}
