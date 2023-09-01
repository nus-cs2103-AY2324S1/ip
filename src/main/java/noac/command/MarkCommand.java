package noac.command;


import noac.NoacException;
import noac.Storage;
import noac.TaskList;
import noac.Ui;

/**
 * For executing the mark/unmark command.
 */
public class MarkCommand extends Command {

    private int taskIndex;
    private boolean isMark;


    /**
     * Create the mark command class.
     *
     * @param taskIndex Which task to mark/unmark.
     * @param isMark Whether to mark/unmark.
     */
    public MarkCommand(int taskIndex, boolean isMark) {
        this.taskIndex = taskIndex;
        this.isMark = isMark;
    }

    /**
     * Mark/unmark the task and update the user, task list and save file.
     *
     * @param tasks List of all the task.
     * @param ui UI for printing result to user.
     * @param storage Storage class meant for saving to file.
     * @throws NoacException For any errors that needs to be displayed to user.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoacException {

        if (taskIndex + 1 > tasks.size() || taskIndex < 0) {
            throw new NoacException("☹ OOPS!!! Please enter a task in your list!");
        }

        if (isMark) {
            tasks.getTask(this.taskIndex).markAsDone();
        } else {
            tasks.getTask(this.taskIndex).unmarkAsDone();
        }

        ui.showMarkOrUnmark(tasks.getTask(this.taskIndex), this.isMark);
        storage.save(tasks);

    }
}
