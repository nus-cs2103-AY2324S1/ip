package bellcurvegod.tasklist;

import bellcurvegod.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void addTask_invalidCommand_exceptionThrown() {
        try {
            TaskList.addTask("hello");
            fail();
        } catch (Exception e) {
            assertEquals(Ui.getLine() + "\n" +
                    "You have entered an invalid bellcurvegod.command word!\n" +
                    "To add a new Task, use \"todo\", \" deadline\", or \"event\".\n" +
                    Ui.getLine(), e.getMessage());
        }
    }
}
