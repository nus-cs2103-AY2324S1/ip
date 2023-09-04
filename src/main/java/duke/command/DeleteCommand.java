package duke.command;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This command functions to delete a task at specified index.
 */
public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid task index");
        }
        Task task = tasks.remove(index);
        ui.deleteTask(tasks, task);
    }
}
