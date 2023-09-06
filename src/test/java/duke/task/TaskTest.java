package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskTest {
    @Test
    public void task_emptyDescription_exceptionThrown() {
        try {
            new Todo("");
            fail(); // Test should not reach this line
        } catch (DukeException e) {
            assertEquals("The description cannot be empty.", e.getMessage());
        }
    }
}