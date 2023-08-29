package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to mark a task as undone in the task list.
 * Inherits from the Command class.
 */
public class MarkAsUndoneCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a MarkAsUndoneCommand object with the given task index.
     *
     * @param taskIndex The index of the task to be marked as undone.
     */
    public MarkAsUndoneCommand(int taskIndex) {
        this.taskIndex = taskIndex - 1;
    }

    /**
     * Executes the MarkAsUndoneCommand by marking the task as undone in the task list.
     *
     * @param tasks The task list.
     * @param ui The user interface.
     * @param storage The storage component.
     * @throws DukeException If an error occurs while executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.markTaskAsUndone(taskIndex);
            ui.showTaskMarkedAsUndone(tasks, taskIndex);
        } else {
            throw new DukeException("Invalid task number.");
        }
    }

    /**
     * Returns a boolean indicating whether the command is an exit command.
     *
     * @return A boolean indicating whether the command is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
