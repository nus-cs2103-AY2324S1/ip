package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UnmarkCommandTest {
    @Test
    public void UnmarkOutOfRangeIndex() {
        TaskList testList = new TaskList();
        Storage storage = new Storage("data/tasks.txt");
        Ui ui = new Ui();
        assertThrows(DukeException.class,
                () -> new UnmarkCommand(20).execute(testList, ui, storage));
    }

    @Test
    public void InvalidUnmarkIndex() {
        TaskList testList = new TaskList();
        Storage storage = new Storage("data/tasks.txt");
        Ui ui = new Ui();
        assertThrows(DukeException.class,
                () -> new UnmarkCommand(1).execute(testList, ui, storage));
    }
}