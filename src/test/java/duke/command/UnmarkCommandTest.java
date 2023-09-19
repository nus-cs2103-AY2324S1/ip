package duke.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class UnmarkCommandTest {
    @Test
    public void negativeArgument() {
        assertThrows(DukeException.class, () -> new UnmarkCommand("-1").execute(new TaskList(), null, null));
    }

    @Test
    public void invalidArgument() {
        assertThrows(DukeException.class, () -> new UnmarkCommand("-1").execute(new TaskList(), null, null));
    }

    @Test
    public void markTaskTest() throws DukeException {
        TaskList tasks = new TaskList();
        TodoCommand testCommand = new TodoCommand("todo buy book");
        testCommand.loadTask(tasks);
        new MarkCommand("1").execute(tasks, new Ui(), new Storage("data/duke.txt"));
        new UnmarkCommand("1").execute(tasks, new Ui(), new Storage("data/duke.txt"));
        assertFalse(tasks.get(0).getComplete());
        new DeleteCommand("1").execute(tasks, new Ui(), new Storage("data/duke.txt"));
    }
}
