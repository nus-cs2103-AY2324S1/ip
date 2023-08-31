package duke.task;

import duke.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeadlineTest {

    @Test
    public void testInvalidDateFormat() {
        assertThrows(DukeException.class, () -> {
            new Deadline("deadline project", "August 2023");
        });
    }

    @Test
    public void testValidDateFormat() {
        assertDoesNotThrow(() -> {
            new Deadline("deadline project", "2023-08-01");
        });
    }
}
