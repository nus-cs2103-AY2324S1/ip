package command;

import duke.Ui;
import storage.FileHandler;
import storage.TaskList;

/**
 * A command to mark a task as not done.
 */
public class UnmarkCommand extends Command {

    private int index;

    /**
     * Constructs a `UnmarkCommand` object with the specified task index.
     *
     * @param index The index of the task to mark as done.
     * @throws IllegalArgumentException If the provided index is not positive (greater than 0).
     */
    public UnmarkCommand(int index) {
        assert index > 0 : "index cannot be negative or zero";
        this.index = index;
    }

    /**
     * Marks the specified task as not done.
     *
     * @param t  The task list containing the tasks.
     * @param ui The user interface to display the result.
     * @param f  The file handler (not used in this command).
     *
     * @return   A string representation of ynmark message.
     */
    @Override
    public String execute(TaskList t, Ui ui, FileHandler f) {
        try {
            if (!t.get(index - 1).isDone()) {
                return "The task has been unmarked";
            }
            t.get(index - 1).markNotDone();
            FileHandler.writeTasksToFile(t);
            return "Alright, I've marked this task as not done yet:\n"
                    + "    " + t.get(index - 1).toString();
        } catch (IndexOutOfBoundsException e) {
            return "Please enter the correct task's index number.";
        }
    }
}
