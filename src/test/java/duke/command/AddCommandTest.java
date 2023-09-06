package duke.command;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddCommandTest {
    @Test
    public void execute_invalidDate_exceptionThrown() {
        TaskList testList = new TaskList();
        Storage storage = new Storage("data/tasks.txt");
        Ui ui = new Ui();
        String fullCommand = "event project meeting /from asdasda /to bdf9dbfdf";
        String[] words = fullCommand.split(" ", 2);

        assertThrows(duke.exception.DukeException.class, () ->
                new AddCommand(words, words[0]).execute(testList, ui, storage));
    }

    @Test
    public void execute_invalidInput_exceptionThrown() {
        TaskList testList = new TaskList();
        Storage storage = new Storage("data/tasks.txt");
        Ui ui = new Ui();
        String fullCommand = "blah";
        String[] words = fullCommand.split(" ", 2);

        assertThrows(duke.exception.DukeException.class, () ->
                new AddCommand(words, words[0]).execute(testList, ui, storage));
    }
}
