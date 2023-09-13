package duke.command;
import duke.DukeException;
import duke.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;


/**
 * Represents a command to list out all the tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by printing out all the tasks.
     *
     * @param tasks   The list of tasks to be printed.
     * @param ui      The user interface handler.
     * @param storage The storage handler.
     * @throws DukeException If there's an issue executing the list command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        StringBuilder result = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.getSize(); i++) {
           result.append((i + 1)).append(".").append(tasks.getTask(i).toString()).append("\n");
       }
        return result.toString();
    }

    /**
     * Indicates whether the command should exit the application.
     *
     * @return false, as 'list' command doesn't terminate the application.
     */
    @Override
    public boolean isExit() {

        return false;
    }
}
