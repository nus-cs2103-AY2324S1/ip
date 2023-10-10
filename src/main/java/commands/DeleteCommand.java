package commands;

import functional.DukeException;
import functional.TaskList;
import functional.Ui;
import tasks.Task;

/**
 * A command to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    /**
     * Constructs a `DeleteCommand` object
     */
    public DeleteCommand() {
        super();
    }

    /**
     * Executes the command to delete a task from the task list and updates the file storage.
     *
     * @param tasks  The task list from which the task will be deleted.
     * @param ui     The user interface.
     * @param status always false - intended for use by AddCommand
     */
    public String execute(TaskList tasks, Ui ui, boolean... status) throws DukeException {
        int index;
        try {
            index = Integer.parseInt(ui.get(1)) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("NumberFormatException: index not a number");
        }
        Task job;
        try {
            job = (Task) tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("IndexOutOfBoundsException");
        }
        tasks.remove(index);
        return ui.showLine() + "\n"
                + "Noted, I've removed this task:\n" + job.toString() + "\n"
                + String.format("Now you have %d tasks in the list.\n", tasks.size())
                + ui.showLine();
    }
}
