package bellcurvegod.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class StorageTest {
    @Test
    public void handleTaskData_wrongDataFormat_exceptionThrown() {
        try {
            Storage.handleTaskData("X|X|X");
            fail();
        } catch (Exception e) {
            assertEquals("The tasks stored in your local disk have wrong format!", e.getMessage());
        }
    }
}
