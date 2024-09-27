package command;

import duke.Ui;
import storage.FileHandler;
import storage.TaskList;
import taskmanager.Task;


/**
 * A command to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Constructs a `DeleteCommand` object with the provided index.
     *
     * @param index The index of the task to be deleted.
     * @throws IllegalArgumentException If the provided index is not positive (greater than 0).
     */
    public DeleteCommand(int index) {
        assert index > 0 : "index cannot be negative or zero";
        this.index = index;
    }

    /**
     * Executes the command to delete a task from the task list and updates the file storage.
     *
     * @param t  The task list from which the task will be deleted.
     * @param ui The user interface.
     * @param f  The file handler for storing tasks.
     *
     * @return   A string representation of delete message.
     */
    @Override
    public String execute(TaskList t, Ui ui, FileHandler f) {
        try {
            Task deleted = t.get(index - 1);
            t.remove(index - 1);
            FileHandler.writeTasksToFile(t);
            return "Helped you deleted this task\n           " + deleted.toString()
                    + "\nNow you have " + t.size() + " task(s) in the list.";
        } catch (IndexOutOfBoundsException e) {
            return "Please enter the correct task's index number.";
        }
    }
}
