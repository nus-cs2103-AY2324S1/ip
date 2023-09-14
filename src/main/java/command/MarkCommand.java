package command;

import duke.Ui;
import storage.FileHandler;
import storage.TaskList;

/**
 * A command to mark a task as done.
 */
public class MarkCommand extends Command {

    private int index;

    /**
     * Constructs a `MarkCommand` object with the specified task index.
     *
     * @param index The index of the task to mark as done.
     * @throws IllegalArgumentException If the provided index is not positive (greater than 0).
     */
    public MarkCommand(int index) {
        assert index > 0 : "index cannot be negative or zero";
        this.index = index;
    }

    /**
     * Marks the specified task as done.
     *
     * @param t  The task list containing the tasks.
     * @param ui The user interface to display the result.
     * @param f  The file handler (not used in this command).
     *
     * @return   A string representation of mark message.
     */
    @Override
    public String execute(TaskList t, Ui ui, FileHandler f) {
        try {
            if (t.get(index - 1).isDone()) {
                return "The task has been marked as done.";
            }
            t.get(index - 1).markDone();
            FileHandler.writeTasksToFile(t);
            return "Well done! I've marked this task as done :\n"
                    + "    " + t.get(index - 1).toString();
        } catch (IndexOutOfBoundsException e) {
            return "Please enter the correct task's index number.";
        }
    }

    /**
     * Checks whether the command is an exit command.
     *
     * @return `false` because this command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
