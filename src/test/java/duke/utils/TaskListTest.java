package duke.utils;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void markTaskDone_success() throws DukeException {
        TaskList task = new TaskList();
        task.addTodoTask("test");
        task.markTaskDone(0);
        assertEquals(task.getTasks().get(0).getIsDone(), true);
    }
    @Test
    public void markTaskDone_invalidTaskNumber() {
        try {
            TaskList task = new TaskList();
            task.addTodoTask("test");
            task.markTaskDone(4);
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "invalid task number");
        }
    }
}
