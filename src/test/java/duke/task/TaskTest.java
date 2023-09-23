package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

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
