package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AddCommandTest {
    @Test
    public void unknownCommand_exceptionThrown(){
        try {
            Command command = new AddCommand("testing");
            command.execute(new TaskList(), new Ui(), new Storage());
            fail();
        } catch (DukeException e) {
            assertEquals("I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }

    @Test
    public void invalidDeadline_exceptionThrown(){
        try {
            Command command = new AddCommand("deadline playing mobile games");
            command.execute(new TaskList(), new Ui(), new Storage());
            fail();
        } catch (DukeException e) {
            assertEquals("Deadline must contain /by", e.getMessage());
        }
    }
}