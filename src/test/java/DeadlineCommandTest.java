import duke.exceptions.InvalidCommandException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.command.DeadlineCommand;
import duke.task.TaskList;
import duke.Storage;
import duke.Ui;

public class DeadlineCommandTest {

    @Test
    public void run_validDeadline_success() throws InvalidCommandException {
        String correctOutput = "1. [D][ ] fill house (by: 06:00 PM)";

        Storage storage = new Storage("");
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        String output = new DeadlineCommand("deadline fill house /by 18:00").execute(storage, ui, taskList);
        assertEquals(correctOutput, output);
    }

    @Test
    public void run_noDescription_invalidCommandException (){
        String correctOutput = "Invalid use of deadline. Usage: deadline <task description> /by <date & time>";

        try {
            Storage storage = new Storage("");
            Ui ui = new Ui();
            TaskList taskList = new TaskList();
            new DeadlineCommand("deadline /by 18:00").execute(storage, ui, taskList);
        } catch (InvalidCommandException e) {
            assertEquals(correctOutput, e.getMessage());
        }
    }

    @Test
    public void run_invalidDateTimeFormat_invalidCommandException (){
        String correctOutput = "Invalid date time format: YYYY-MM-DD / HH:mm / a combination of both";

        try {
            Storage storage = new Storage("");
            Ui ui = new Ui();
            TaskList taskList = new TaskList();
            new DeadlineCommand("deadline hui /by 109").execute(storage, ui, taskList);
        } catch (InvalidCommandException e) {
            assertEquals(correctOutput, e.getMessage());
        }
    }
}
