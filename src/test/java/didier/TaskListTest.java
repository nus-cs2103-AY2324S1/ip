package didier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import didier.exception.TaskNumberException;

public class TaskListTest {

    @Test
    public void remove_validIndex_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(null);
        try {
            assertEquals(taskList.removeTask(1), null);
        } catch (TaskNumberException e) {
            fail();
        }
    }

    @Test
    public void remove_invalidIndex_exceptionThrown() {
        TaskList taskList = new TaskList();
        taskList.addTask(null);
        try {
            assertEquals(taskList.removeTask(2), null);
            fail();
        } catch (TaskNumberException e) {
            assertEquals(e.getMessage(), "2 is an invalid task number. ");
        }
    }

}
