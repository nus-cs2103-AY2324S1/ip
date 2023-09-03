package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommandTest {
    @Test
    public void negativeArgument() {
        assertThrows(DukeException.class, () -> new DeleteCommand("-1").execute(new TaskList(), null, null));
    }

    @Test
    public void testTaskDeletion() throws DukeException {
        TaskList tasks = new TaskList();
        DeadlineCommand addTaskCommand = new DeadlineCommand("borrow book /by 2000-10-10 1800");
        addTaskCommand.loadTask(tasks);
        new DeleteCommand("1").execute(tasks, new Ui(), new Storage("data/duke.txt"));
        assertEquals(0, tasks.size());
    }
}
