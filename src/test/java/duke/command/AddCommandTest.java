package duke.command;

import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddCommandTest {
    @Test
    public void InvalidDate() {
        TaskList testList = new TaskList();
        Storage storage = new Storage("data/tasks.txt");
        Ui ui = new Ui();
        String fullCommand ="event project meeting /from asdasda /to bdf9dbfdf";
        String[] words = fullCommand.split(" ", 2);

        assertThrows(duke.exception.DukeException.class,
                () -> new AddCommand(words, words[0]).execute(testList, ui, storage));
    }

    @Test
    public void InvalidInput() {
        TaskList testList = new TaskList();
        Storage storage = new Storage("data/tasks.txt");
        Ui ui = new Ui();
        String fullCommand ="blah";
        String[] words = fullCommand.split(" ", 2);

        assertThrows(duke.exception.DukeException.class,
                () -> new AddCommand(words, words[0]).execute(testList, ui, storage));
    }
}
