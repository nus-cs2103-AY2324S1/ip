package command;
import duke.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * The `ListCommand` class represents a command to list and display tasks in the Duke application.
 * It extends the `Command` class and is responsible for displaying the current list of tasks to the user.
 * This class encapsulates the behavior of listing tasks and showing them through the user interface.
 *
 * @author raydenlim
 * @version 0.0.0
 */
public class ListCommand extends Command {
    /**
     * Executes the "List" command by displaying the current list of tasks to the user through the user interface.
     *
     * @param taskList The task list to be displayed.
     * @param ui       The user interface for displaying the list of tasks.
     * @param storage  The storage component (not used in this command).
     * @returns A message displaying the list of tasks at hand.
     * @throws DukeException An exception may be thrown if there is an error executing the command (e.g., UI error).
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return ui.showTasks(taskList);
    }
}
