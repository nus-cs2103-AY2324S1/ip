package bellcurvegod.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void addTask_invalidCommand_exceptionThrown() {
        try {
            TaskList.addTask("hello");
            fail();
        } catch (Exception e) {
            assertEquals("You have entered an invalid command word!\n"
                + "To add a new Task, use \"todo\", \"deadline\", or \"event\".\n", e.getMessage());
        }
    }
}
