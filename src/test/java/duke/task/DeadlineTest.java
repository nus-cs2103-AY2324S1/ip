package duke.task;

import duke.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeadlineTest {

    @Test
    public void testInvalidDateFormat() {
        // Invalid date format should throw an error of DukeException type.
        assertThrows(DukeException.class, () -> {
            new Deadline("deadline project", "August 2023");
        });
    }

    @Test
    public void testValidDateFormat() {
        // Correct date format should not throw an error.
        assertDoesNotThrow(() -> {
            new Deadline("deadline project", "2023-08-01");
        });
    }
}
