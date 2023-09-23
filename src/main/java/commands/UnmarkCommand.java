package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Represents a command to unmark a task as not done in the task list.
 */
public class UnmarkCommand implements Command {

    /**
     * The regular expression pattern for matching the format of an unmark command.
     */
    public static final String UNMARK_PATTERN = "^(unmark)\\s+\\d+$";

    private int pos;

    /**
     * Constructs an UnmarkCommand with the specified position of the task to unmark.
     *
     * @param pos The position of the task to be unmarked.
     */
    public UnmarkCommand(int pos) {
        this.pos = pos;
    }

    /**
     * Executes the command to unmark the task at the specified position as not done in the task list.
     * If the position is invalid, an error message is displayed through the user interface.
     *
     * @param tasks   The task list in which the task will be unmarked.
     * @param ui      The user interface to display error messages.
     * @param storage The storage to update as needed by the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String res = "";
        if (pos > tasks.size() || pos <= 0) {
            ui.showError("Invalid index. Please enter again.");
            return "Invalid index. Please enter again.";
        } else {
            res = tasks.unmark(pos);
            Storage.refresh(tasks);
        }
        return res;
    }
}
