package duke.command;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MarkCommandTest {
    @Test
    public void SingleMark() throws DukeException {
        TaskList testList = new TaskList();
        Storage storage = new Storage("data/tasks.txt");
        Ui ui = new Ui();
        new AddCommand("todo read book", 'T').execute(testList, ui, storage);
        new MarkCommand(0).execute(testList, ui, storage);
        assertEquals("[T][X] read book", testList.getPrint(0));
    }

    @Test
    public void ManyTasksMark() throws DukeException {
        TaskList testList = new TaskList();
        Storage storage = new Storage("data/tasks.txt");
        Ui ui = new Ui();
        new AddCommand("todo read book", 'T').execute(testList, ui, storage);
        new AddCommand("event project meeting /from 2019-09-02 18:00 /to 2019-09-02 19:00", 'E')
                .execute(testList, ui, storage);
        new AddCommand("deadline return book /by 2019-09-02 18:00", 'D').execute(testList, ui, storage);
        new AddCommand("todo join sports", 'T').execute(testList, ui, storage);
        new AddCommand("todo homework", 'T').execute(testList, ui, storage);
        new AddCommand("todo buy food", 'T').execute(testList, ui, storage);
        new MarkCommand(2).execute(testList, ui, storage);
        assertEquals("[D][X] return book  (by: Sep 02 2019 18:00)", testList.getPrint(2));
    }

    @Test
    public void InvalidMarkIndex() {
        TaskList testList = new TaskList();
        Storage storage = new Storage("data/tasks.txt");
        Ui ui = new Ui();
        assertThrows(DukeException.class, () ->
                new MarkCommand(0).execute(testList, ui, storage));
    }

    @Test
    public void ExitTest() throws DukeException {
        TaskList testList = new TaskList();
        Storage storage = new Storage("data/tasks.txt");
        Ui ui = new Ui();
        new AddCommand("todo read book", 'T').execute(testList, ui, storage);
        Command c = new MarkCommand(0);
        c.execute(testList, ui, storage);
        assertEquals("[T][X] read book", testList.getPrint(0));
        boolean isExit = c.isExit();
        assertEquals(false, isExit);
    }
}
