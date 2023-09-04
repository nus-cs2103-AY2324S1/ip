package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void toString_validDate_success() {
        try {
            Task deadline = new Deadline("read", "2024-04-04");
            assertEquals("[D][ ] read (by: Apr. 4 2024)", deadline.toString());
        } catch (DateTimeParseException e) {
            fail();
        }
    }

    @Test
    public void toString_invalidDate_success() {
        try {
            Task deadline = new Deadline("read", "2024-40-40");
            fail();
        } catch (DateTimeParseException e) {
            assertTrue(e instanceof DateTimeParseException);
        }
    }
}
