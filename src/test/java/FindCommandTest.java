import duke.Storage;
import duke.Ui;
import duke.command.DeadlineCommand;
import duke.command.FindCommand;
import duke.exceptions.InvalidCommandException;
import duke.task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindCommandTest {

    @Test
    public void run_validFind_success() throws InvalidCommandException {
        String correctOutput =
                "[D][ ] fill house (by: 06:00 PM)\n" +
                "[D][ ] open house (by: 06:00 PM)";

        Storage storage = new Storage("");
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        new DeadlineCommand("deadline fill house /by 18:00").execute(storage, ui, taskList);
        new DeadlineCommand("deadline open house /by 18:00").execute(storage, ui, taskList);
        new DeadlineCommand("deadline open floor /by 18:00").execute(storage, ui, taskList);
        String output = new FindCommand("find house").execute(storage, ui, taskList);
        assertEquals(correctOutput, output);
    }

    @Test
    public void run_validFind2_success() throws InvalidCommandException {
        String correctOutput = "[D][ ] open house (by: Sep 23 2000)";

        Storage storage = new Storage("");
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        new DeadlineCommand("deadline fill house /by 18:00").execute(storage, ui, taskList);
        new DeadlineCommand("deadline open house /by 2000-09-23").execute(storage, ui, taskList);
        new DeadlineCommand("deadline open floor /by 18:00").execute(storage, ui, taskList);
        String output = new FindCommand("find 23").execute(storage, ui, taskList);
        assertEquals(correctOutput, output);
    }

    @Test
    public void run_noDescription_invalidCommandException (){
        String correctOutput = "Invalid input. Usage: find <description to match>";

        try {
            Storage storage = new Storage("");
            Ui ui = new Ui();
            TaskList taskList = new TaskList();
            new FindCommand("find").execute(storage, ui, taskList);
        } catch (InvalidCommandException e) {
            assertEquals(correctOutput, e.getMessage());
        }
    }
}
