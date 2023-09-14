package alice.command;

import alice.exception.DukeException;
import alice.storage.Storage;
import alice.task.Task;
import alice.task.TaskList;
import alice.ui.Ui;

/**
 * Represents a command issued by the user to delete a task from the list.
 */
public class DeleteCommand extends Command {
    private final int taskIndex; // The index of the task to be deleted.

    /**
     * Constructs a DeleteCommand with the given index.
     *
     * @param taskIndex The index (0-based) of the task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Constructs a DeleteCommand with the given argument.
     *
     * @param argument The argument of the command.
     * @throws DukeException If the argument is invalid.
     */
    public DeleteCommand(String argument) throws DukeException {
        try {
            this.taskIndex = Integer.parseInt(argument) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(Command.INDEX_NOT_NUMBER_ERROR_MESSAGE);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.get(this.taskIndex);
        tasks.delete(this.taskIndex);
        storage.save(tasks.toFileString());
        return ui.showDeleteTask(task, tasks.size());
    }
}
