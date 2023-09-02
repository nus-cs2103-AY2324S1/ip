package Comm;
import Ui.Ui;
import Storage.TaskList;
import Storage.FileHandler;
import TaskManager.Tasks;

/**
 * A command to mark a task as done.
 */
public class MarkCommand extends Command{

    private int index;

    /**
     * Constructs a `MarkCommand` object with the specified task index.
     *
     * @param index The index of the task to mark as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Mark the specified task as done.
     *
     * @param t  The task list containing the tasks.
     * @param ui The user interface to display the result.
     * @param f  The file handler (not used in this command).
     */
    @Override
    public void execute(TaskList t, Ui ui, FileHandler f) {
        try {
            t.get(index - 1).markDone();
            FileHandler.writeTasksToFile(t);
            ui.mark(index);
        } catch (IndexOutOfBoundsException e) {
            ui.IOOBExceptionMessage();
        }
    }

    /**
     * Check whether the command is an exit command.
     *
     * @return `false` because this command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}