import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Storage;
import duke.Ui;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.exceptions.InvalidCommandException;
import duke.task.TaskList;

public class DeleteCommandTest {

    @Test
    public void run_validDelete_success() throws InvalidCommandException {
        String correctOutput = "Task successfully deleted";

        Storage storage = new Storage("");
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        new DeadlineCommand("deadline fill house /by 18:00").execute(storage, ui, taskList);
        new DeadlineCommand("deadline open house /by 18:00").execute(storage, ui, taskList);
        new DeadlineCommand("deadline open floor /by 18:00").execute(storage, ui, taskList);
        String output = new DeleteCommand("delete 3").execute(storage, ui, taskList);
        assertEquals(correctOutput, output);
    }

    @Test
    public void run_noDescription_invalidCommandException (){
        String correctOutput = "Invalid input. Usage: delete <task_index>";

        try {
            Storage storage = new Storage("");
            Ui ui = new Ui();
            TaskList taskList = new TaskList();
            new DeleteCommand("delete").execute(storage, ui, taskList);
        } catch (InvalidCommandException e) {
            assertEquals(correctOutput, e.getMessage());
        }
    }
}
