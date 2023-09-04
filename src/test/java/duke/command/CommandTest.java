package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeTaskNotFoundException;
import duke.task.Todo;

public class CommandTest {
    @Test
    public void testSeriesOfAddCommands() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("data/duke.txt");
        new AddCommand(new Todo("a")).execute(tasks, ui, storage);
        new AddCommand(new Todo("b")).execute(tasks, ui, storage);
        new AddCommand(new Todo("c")).execute(tasks, ui, storage);

        assertEquals(tasks.stringifyTasks(), "T; ;a\nT; ;b\nT; ;c");
    }

    @Test
    public void testDeleteCommandWhenEmpty() {
        Command command = new DeleteCommand(0);

        // storage is not used in deleteCommand, so the filePath is not important
        Exception exception = assertThrows(DukeTaskNotFoundException.class, () ->
                command.execute(new TaskList(), new Ui(), new Storage("data/duke.txt")));

        assertEquals("☹ OOPS!!! I can't find that task!\n", exception.getMessage());
    }

    @Test
    public void testMarkUnmarkCommands() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("data/duke.txt");
        new AddCommand(new Todo("a")).execute(tasks, ui, storage);
        new AddCommand(new Todo("b")).execute(tasks, ui, storage);

        try {
            new MarkCommand(0).execute(tasks, ui, storage);
            new MarkCommand(1).execute(tasks, ui, storage);

            new UnmarkCommand(0).execute(tasks, ui, storage);
            new UnmarkCommand(1).execute(tasks, ui, storage);
        } catch (DukeTaskNotFoundException e) {
            // should not reach here
        }

        assertEquals(tasks.stringifyTasks(), "T; ;a\nT; ;b");
    }

    @Test
    public void testOutOfBoundsMarkCommand() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("data/duke.txt");

        new AddCommand(new Todo("a")).execute(tasks, ui, storage);
        new AddCommand(new Todo("b")).execute(tasks, ui, storage);
        Command command = new MarkCommand(2);

        Exception exception = assertThrows(DukeTaskNotFoundException.class, () ->
                command.execute(tasks, ui, storage));

        assertEquals("☹ OOPS!!! I can't find that task!\n", exception.getMessage());
    }
}
