package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand implements Command {

    private int pos;

    /**
     * Constructs a MarkCommand with the specified position of the task to mark.
     *
     * @param pos The position of the task to be marked.
     */
    public MarkCommand(int pos) {
        this.pos = pos;
    }

    /**
     * The regular expression pattern for matching the format of a mark command.
     */
    public static final String MARK_PATTERN = "^(mark)\\s+\\d+$";

    /**
     * Executes the command to mark the task at the specified position as done in the task list.
     * If the position is invalid, an error message is displayed.
     *
     * @param tasks   The task list in which the task will be marked as done.
     * @param ui      The user interface to display messages to the user.
     * @param storage The storage to update as needed by the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (pos > tasks.size() || pos <= 0) {
            ui.showError("Invalid index. Please enter again.");
        } else {
            tasks.mark(pos);        // Mark the task as done in the task list
            Storage.refresh(tasks); // Update storage with the modified task list
        }
    }
}
