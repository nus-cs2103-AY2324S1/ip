package duke.command;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class UnmarkCommandTest {
    @Test
    public void execute_unmarkOutOfRangeIndex_exceptionThrown() {
        TaskList testList = new TaskList();
        Storage storage = new Storage("data/tasks.txt");
        Ui ui = new Ui();
        assertThrows(DukeException.class, () ->
                new UnmarkCommand(20).execute(testList, ui, storage));
    }

    @Test
    public void execute_invalidUnmarkIndex_exceptionThrown() {
        TaskList testList = new TaskList();
        Storage storage = new Storage("data/tasks.txt");
        Ui ui = new Ui();
        assertThrows(DukeException.class, () ->
                new UnmarkCommand(1).execute(testList, ui, storage));
    }
}
