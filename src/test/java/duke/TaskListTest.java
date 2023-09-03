package duke;

import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


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
}
