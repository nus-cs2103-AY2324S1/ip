package duke.command;
import java.io.IOException;

import duke.DukeException;
import duke.TaskList;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Represents a command to delete a task from the task list in the Duke program.
 */
public class DeleteCommand extends Command {

    /** Index of the task to be deleted from the task list. */
    private final int taskIndex;

    /**
     * Constructs a new DeleteCommand with the specified task index.
     *
     * @param taskIndex Index of the task to be deleted.
     */
    public DeleteCommand(int taskIndex) {

        this.taskIndex = taskIndex;
    }

    /**
     * Determines if the Duke program should exit.
     *
     * @return false since the delete command should not terminate the program.
     */
    @Override
    public boolean isExit() {

        return false;
    }

    /**
     * Executes the delete command, removing a task from the task list,
     * saving the updated list and then notifying the user.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface handler.
     * @param storage The storage handler.
     * @throws IOException If there's an error saving the tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            if (taskIndex < 0 || taskIndex >= tasks.getSize()) {
                throw new DukeException("Invalid task number!");
            }

            Task removedTask = tasks.deleteTask(taskIndex);
            storage.save(tasks);
            ui.showDeletedTask(removedTask);

        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }
}
