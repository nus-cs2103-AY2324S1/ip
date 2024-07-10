import duke.exceptions.InvalidCommandException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.command.EventCommand;
import duke.task.Event;
import duke.task.TaskList;
import duke.Storage;
import duke.Ui;

public class EventCommandTest {

    @Test
    public void run_validEvent_success() throws InvalidCommandException {
        String correctOutput = "Got it. I've added this task:\n" +
                "[E][ ] fill house (from: 06:00 PM to: 07:00 PM)\n" +
                String.format("Now you have %d tasks in your list", 1);

        Storage storage = new Storage("");
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        String output = new EventCommand("event fill house /from 18:00 /to 19:00").execute(storage, ui, taskList);
        assertEquals(correctOutput, output);
    }

    @Test
    public void run_validEvent_success2() throws InvalidCommandException {
        String correctOutput = "Got it. I've added this task:\n" +
                "[E][ ] fill house (from: Aug 8 2000 06:00 PM to: 09:00 PM)\n" +
                String.format("Now you have %d tasks in your list", 1);

        Storage storage = new Storage("");
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        String output = new EventCommand("event fill house /from 2000-08-08 18:00 /to 21:00").execute(storage, ui, taskList);
        assertEquals(correctOutput, output);
    }

    @Test
    public void run_noDescription_invalidCommandException (){
        String correctOutput = "Invalid use of event. Usage: event <task description> /from <date & time> /to <date & time>";

        try {
            Storage storage = new Storage("");
            Ui ui = new Ui();
            TaskList taskList = new TaskList();
            new EventCommand("event /by 18:00").execute(storage, ui, taskList);
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
            new EventCommand("event hui /from 109 /to 899").execute(storage, ui, taskList);
        } catch (InvalidCommandException e) {
            assertEquals(correctOutput, e.getMessage());
        }
    }
}
