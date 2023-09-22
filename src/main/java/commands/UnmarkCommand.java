package commands;

import functional.DukeException;
import functional.TaskList;
import functional.Ui;
import tasks.Task;

/**
 * A command to mark a task as not done.
 */
public class UnmarkCommand extends Command {

    /**
     * Constructs a `UnmarkCommand` object
     */
    public UnmarkCommand() {
        super();
    }

    /**
     * Marks the specified task as not done.
     *
     * @param tasks  The task list containing the tasks.
     * @param ui     The user interface to display the result.
     * @param status always false - intended for use by AddCommand
     */

    public String execute(TaskList<Task> tasks, Ui ui, boolean... status) throws DukeException {
        int index = Integer.parseInt(ui.get(1)) - 1;
        if (index >= tasks.size()) {
            throw new DukeException("index out of bounds");
        }
        Task job = tasks.get(index);
        job = job.unmark();
        tasks.set(index, job);
        return ui.showLine() + "\n"
                + "OK, I've marked this task as not done yet:\n" + job.toString() + "\n"
                + ui.showLine();
    }
}
