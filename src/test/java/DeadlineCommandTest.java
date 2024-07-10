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
        String correctOutput = "Got it. I've added this task:\n" +
                "[D][ ] fill house (by: 06:00 PM)\n" +
                String.format("Now you have %d tasks in your list", 1);

        Storage storage = new Storage("");
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        String output = new DeadlineCommand("deadline fill house /by 18:00").execute(storage, ui, taskList);
        assertEquals(correctOutput, output);
    }

    @Test
    public void run_validDeadline_success2() throws InvalidCommandException {
        String correctOutput = "Got it. I've added this task:\n" +
                "[D][ ] fill house (by: Aug 8 2000 06:00 PM)\n" +
                String.format("Now you have %d tasks in your list", 1);

        Storage storage = new Storage("");
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        String output = new DeadlineCommand("deadline fill house /by 2000-08-08 18:00").execute(storage, ui, taskList);
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
