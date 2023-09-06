package duke.command;
import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;




/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final String pos;

    /**
     * Constructs a DeleteCommand object.
     *
     * @param pos The position of the task to be deleted in the list.
     */

    public DeleteCommand(String pos) {
        this.pos = pos;
    }

    /**
     * Executes the delete command, deleting the task from the task list and displaying relevant messages.
     *
     * @param list    The TaskList object managing the list of tasks.
     * @param ui      The Ui object to interact with the user interface.
     * @param storage The Storage object to manage data storage.
     * @throws DukeException If there is an issue executing the command.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        Task deletedTask = list.deleteTask(this.pos);
        return ui.showDelete(deletedTask, list.getList());
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
