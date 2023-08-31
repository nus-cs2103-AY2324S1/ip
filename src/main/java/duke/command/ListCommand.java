package duke.command;
import duke.ui.Ui;
import duke.task.TaskList;
import duke.storage.Storage;
import duke.DukeException;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command, displaying the list of tasks.
     *
     * @param list    The TaskList object managing the list of tasks.
     * @param ui      The Ui object to interact with the user interface.
     * @param storage The Storage object to manage data storage.
     * @throws DukeException If there is an issue executing the command.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        ui.showList(list.getList());
    }

    /**
     * Indicates whether this command is an exit command.
     *
     * @return False, as this command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
