package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    @Test
    public void markTask_negativeIndex_exceptionThrown() {
        try {
            assertEquals("⚠ OOPS!!! The task index is out of range.", new TaskList().markTask(-1,true));
            fail();
        } catch (Exception e) {
            assertEquals("task not found", e.getMessage());
        }
    }
}
