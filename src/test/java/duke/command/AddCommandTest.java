package duke.command;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddCommandTest {
    @Test
    public void AddSingleTodo() throws DukeException {
        TaskList testList = new TaskList();
        new AddCommand("todo read book", 'T')
                .execute(testList, new Ui(), new Storage("data/tasks.txt"));
        assertEquals(1, testList.size());
    }

    @Test
    public void AddManyTasks() throws DukeException {
        TaskList testList = new TaskList();
        Storage storage = new Storage("data/tasks.txt");
        Ui ui = new Ui();
        new AddCommand("todo read book", 'T').execute(testList, ui, storage);
        new AddCommand("event project meeting /from 2019-09-02 18:00 /to 2019-09-02 19:00", 'E')
                .execute(testList, ui, storage);
        new AddCommand("deadline return book /by 2019-09-02 18:00", 'D')
                .execute(testList, ui, storage);
        new AddCommand("todo join sports", 'T').execute(testList, ui, storage);
        new AddCommand("todo homework", 'T').execute(testList, ui, storage);
        new AddCommand("todo buy food", 'T').execute(testList, ui, storage);
        assertEquals(6, testList.size());
    }

    @Test
    public void InvalidDate() {
        TaskList testList = new TaskList();
        Storage storage = new Storage("data/tasks.txt");
        Ui ui = new Ui();
        assertThrows(DateTimeParseException.class, () ->
                new AddCommand("event project meeting /from 2019-09-02 18:00 /to 2019-09 19:00", 'E')
                        .execute(testList, ui, storage));
    }

    @Test
    public void ExitTest() throws DukeException {
        TaskList testList = new TaskList();
        Command c = new AddCommand("todo read book", 'T');
        c.execute(testList, new Ui(), new Storage("data/tasks.txt"));;
        boolean isExit = c.isExit();
        assertEquals(false, isExit);
    }
}
