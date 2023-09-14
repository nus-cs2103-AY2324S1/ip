package alice.command;

import alice.exception.DukeException;
import alice.storage.Storage;
import alice.task.TaskList;
import alice.ui.Ui;

/**
 * Represents a command issued by the user to mark a task as not done.
 */
public class UnmarkAsDoneCommand extends Command {
    private final int taskIndex; // The index of the task to be marked as not done.

    /**
     * Constructs a UnmarkAsDoneCommand with the given index.
     *
     * @param taskIndex The index (0-based) of the task to be marked as not done.
     */
    public UnmarkAsDoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Constructs a UnmarkAsDoneCommand with the given argument.
     *
     * @param argument The argument of the command.
     * @throws DukeException If the argument is invalid.
     */
    public UnmarkAsDoneCommand(String argument) throws DukeException {
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
        tasks.unmarkAsDone(this.taskIndex);
        storage.save(tasks.toFileString());
        return ui.showUnmarkTaskAsDone(tasks.get(this.taskIndex));
    }
}
