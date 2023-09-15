package alice.command;

import alice.exception.DukeException;
import alice.storage.Storage;
import alice.task.TaskList;
import alice.ui.Ui;

/**
 * Represents a command issued by the user to untag a task.
 */
public class UntagCommand extends Command {
    private final int taskIndex; // The index of the task to be untagged.

    /**
     * Constructs a UntagCommand with the given index.
     *
     * @param taskIndex The index (0-based) of the task to be untagged.
     */
    public UntagCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Constructs a UntagCommand with the given argument.
     *
     * @param argument The argument of the command.
     * @throws DukeException If the argument is invalid.
     */
    public UntagCommand(String argument) throws DukeException {
        try {
            this.taskIndex = Integer.parseInt(argument) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(INDEX_NOT_NUMBER_ERROR_MESSAGE);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.untag(this.taskIndex);
        storage.save(tasks.toFileString());
        return ui.showUntagTask(tasks.get(this.taskIndex));
    }
}
