package duke.command;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class MarkCommandTest {
    @Test
    public void negativeArgument() {
        assertThrows(DukeException.class, () -> new MarkCommand("-1").execute(new TaskList(), null, null));
    }

    @Test
    public void invalidArgument() {
        assertThrows(DukeException.class, () -> new MarkCommand("2").execute(new TaskList(), null, null));
    }

    @Test
    public void markTaskTest() throws DukeException {
        TaskList tasks = new TaskList();
        TodoCommand testCommand = new TodoCommand("buy book");
        testCommand.loadTask(tasks);
        new MarkCommand("1").execute(tasks, new Ui(), new Storage("data/duke.txt"));
        assertTrue(tasks.get(0).getComplete());
        new DeleteCommand("1").execute(tasks, new Ui(), new Storage("data/duke.txt"));
    }
}
