package duke.management;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.task.Todo;

public class TaskListTest {
    @Test
    public void getTask_exceptionThrown() throws Exception {
        try {
            assertEquals(new Todo("test"), new TaskList().getTask(0));
            fail();
        } catch (DukeException e) {
            assertEquals("Oopssss! This task does not exist!", e.getMessage());
        }
    }

    @Test
    public void deleteTask_exceptionThrown() throws Exception {
        try {
            assertEquals(new Todo("test"), new TaskList().deleteTask(0));
            fail();
        } catch (DukeException e) {
            assertEquals("Oopssss! This task does not exist!", e.getMessage());
        }
    }
}
