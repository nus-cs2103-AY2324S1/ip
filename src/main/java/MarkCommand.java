/**
 * The MarkCommand class represents a command to mark a task as done in a TaskList.
 * When executed, it sets the specified task as completed and displays a confirmation message to the user.
 */
public class MarkCommand extends Command {
    /**
     * The task number (in string format) to be marked as done.
     */
    protected String taskNumberToMark;

    /**
     * Constructs a MarkCommand with the specified task number to mark as done.
     *
     * @param taskNumberToMark The task number (in string format) to be marked as done.
     */
    public MarkCommand(String taskNumberToMark) {
        this.taskNumberToMark = taskNumberToMark;
    }

    /**
     * Executes the MarkCommand to mark a task as done in the TaskList, updates the UI, and optionally the storage.
     *
     * @param tasksList The TaskList containing the tasks to be managed.
     * @param ui The user interface for displaying messages to the user.
     * @param storage The storage component for interacting with task data storage.
     */
    @Override
    public void execute(TaskList tasksList, Ui ui, Storage storage) {
        // Marks a task as done
        int taskNumberInList = Integer.parseInt(this.taskNumberToMark) - 1;
        tasksList.get(taskNumberInList).markAsDone();
        ui.showText("Nice! I've marked this task as done:");
        ui.showText("  " + tasksList.get(taskNumberInList).toString());
        ui.showNewLine();
    }
}
