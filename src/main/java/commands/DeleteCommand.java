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
     * @param marked always false - intended for use by AddCommand
     * @param load   always false - intended for use by AddCommand
     */
    public String execute(TaskList tasks, Ui ui, boolean marked, boolean load) throws DukeException {
        int index = Integer.parseInt(ui.get(1)) - 1;
        if (index >= tasks.size()) {
            throw new DukeException("index out of bounds");
        }
        Task job = (Task) tasks.get(index);
        tasks.remove(index);
        return ui.showLine() + "\n" +
                "Noted, I've removed this task:\n" + job.toString() + "\n" +
                String.format("Now you have %d tasks in the list\n", tasks.size())
                + ui.showLine();
    }
}
