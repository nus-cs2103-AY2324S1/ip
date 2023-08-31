package duke.command;

import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.data.task.Task;
import duke.data.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to delete the specified task.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    private int taskIndex;

    public DeleteCommand(String taskIndex) {
        this.taskIndex = Integer.parseInt(taskIndex);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        if (taskIndex < 1 || taskIndex > tasks.size()) {
            throw new DukeException("No such task exists.");
        }

        Task task = tasks.remove(taskIndex);

        storage.save(tasks);
        ui.showMessage(
                "Noted. I've removed this task:",
                "\t " + task
        );
    }
}
