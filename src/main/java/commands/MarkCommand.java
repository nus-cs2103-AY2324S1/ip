package commands;

import functional.TaskList;
import functional.Ui;
import tasks.Task;

/**
 * A command to mark a task as done.
 */
public class MarkCommand extends commands.Command {

    /**
     * Constructs a `MarkCommand` object
     */
    public MarkCommand() {
        super();
    }


    /**
     * Marks the specified task as done.
     *
     * @param tasks  The task list containing the tasks.
     * @param ui The user interface to display the result.
     * @param marked always false - intended for use by AddCommand
     * @param load always false - intended for use by AddCommand
     */
public void execute(TaskList<Task> tasks, Ui ui, boolean marked, boolean load) throws functional.DukeException {
        int index = Integer.parseInt(ui.get(1)) - 1;
        if (index >= tasks.size()) {
            throw new functional.DukeException();
        }
        Task job = (Task) tasks.get(index);
        job = job.mark();
        tasks.set(index, job);
        System.out.println("____________________________________________________________\n" +
                "OK, I've marked this task as done:\n" + job.toString() + "\n" +
                "____________________________________________________________");
    }
}
