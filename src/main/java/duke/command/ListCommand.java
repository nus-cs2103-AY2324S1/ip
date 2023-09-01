package duke.command;
import duke.Ui;
import duke.task.TaskList;
import duke.Storage;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the ListCommand to display the list of tasks from the task list.
     *
     * @param tasks   The TaskList containing tasks.
     * @param ui      The Ui for user interface interactions.
     * @param storage The Storage for loading and saving tasks (not used in this command).
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return false since ListCommand is not an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
