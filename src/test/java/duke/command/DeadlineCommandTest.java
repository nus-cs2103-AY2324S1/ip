package duke.command;

import duke.DukeException;
import duke.task.TaskList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeadlineCommandTest {
    @Test
    public void missingArgument() {
        assertThrows(DukeException.class, () -> new DeadlineCommand("").execute(null, null, null));
    }

    @Test
    public void testDeadlineLoad() {
        TaskList tasks = new TaskList();
        DeadlineCommand testCommand = new DeadlineCommand("borrow book /by 2000-10-10 1800");
        testCommand.loadTask(tasks);
        assertEquals(1, tasks.size());
    }
}
