package Comm;
import Ui.Ui;
import Storage.TaskList;
import Storage.FileHandler;
import TaskManager.Tasks;

/**
 * A command to delete a task from the task list.
 */
public class DeleteCommand extends Command{

    private int index;

    /**
     * Constructs a `DeleteCommand` object with the provided index.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to delete a task from the task list and updates the file storage.
     *
     * @param t  The task list from which the task will be deleted.
     * @param ui The user interface.
     * @param f  The file handler for storing tasks.
     */
    @Override
    public void execute(TaskList t, Ui ui, FileHandler f) {
        try {
            Tasks deleted = t.get(index - 1);
            t.remove(index - 1);
            FileHandler.writeTasksToFile(t);
            ui.delete(deleted);
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
