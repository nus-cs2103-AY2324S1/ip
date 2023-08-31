package duke.command;
import duke.ui.Ui;
import duke.task.TaskList;
import duke.task.Task;
import duke.storage.Storage;
import duke.DukeException;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private final String type;
    private final String description;

    /**
     * Constructs an AddCommand object.
     *
     * @param type        The type of the task to add (e.g., "todo", "deadline", "event").
     * @param description The description of the task.
     */
    public AddCommand(String type, String description) {
        this.type = type;
        this.description = description;
    }

    /**
     * Executes the add command, adding the task to the task list and displaying relevant messages.
     *
     * @param list    The TaskList object managing the list of tasks.
     * @param ui      The Ui object to interact with the user interface.
     * @param storage The Storage object to manage data storage.
     * @throws DukeException If there is an issue executing the command.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        Task newTask = list.addTask(this.type, this.description);
        ui.showTaskAdded(newTask, list.getList());
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
