package taskstuff;

import duke.DukeException;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;


class TaskListTest {
    @Test
    public void markTask_invalidIndex_exceptionThrown() {
        Task task = new Todo("read book");
        TaskList taskList = new TaskList();
        taskList.addTask(task);
        try {
            taskList.markTask(2);
            fail();
        } catch (DukeException e) {
            assertEquals("The index is not a valid index. Try again.", e.getMessage());
        }
    }

    @Test
    public void deleteTask_validIndex_success() {

        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("read book"));
        taskList.addTask(new Todo("cook"));
        try {
            taskList.deleteTask(1);
            assertEquals(1, taskList.getSize());
        } catch (DukeException e) {
            fail();
        }
    }
}