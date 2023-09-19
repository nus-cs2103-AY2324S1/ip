package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.task.TaskList;

public class EventCommandTest {
    @Test
    public void missingArgument() {
        assertThrows(DukeException.class, () -> new EventCommand("").execute(null, null, null));
    }

    @Test
    public void testEventLoad() {
        TaskList tasks = new TaskList();
        EventCommand testCommand = new EventCommand("read book /from 2000-10-10 1800 /to 2000-10-11 1800");
        testCommand.loadTask(tasks);
        assertEquals(1, tasks.size());
    }
}
