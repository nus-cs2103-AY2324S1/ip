package duke.command;

import duke.DukeException;
import duke.task.TaskList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TodoCommandTest {
    @Test
    public void missingArgument() {
        assertThrows(DukeException.class, () -> new TodoCommand("").execute(null, null, null));
    }

    @Test
    public void testTodoLoad() {
        TaskList tasks = new TaskList();
        TodoCommand testCommand = new TodoCommand("buy book");
        testCommand.loadTask(tasks);
        assertEquals(1, tasks.size());
    }
}
