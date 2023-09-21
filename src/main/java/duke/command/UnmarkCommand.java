package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeTaskNotFoundException;

/**
 * Represents a command that marks a task as undone.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Creates a UnmarkCommand object.
     *
     * @param taskIndex The index of the task to be marked as undone.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command: marks the task as undone and displays a "Undone" message.
     *
     * @param tasks   The list of tasks.
     * @param storage The storage.
     * @return The undone message.
     * @throws DukeTaskNotFoundException If the task to be marked as undone does not exist.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeTaskNotFoundException {
        try {
            return String.format("OK, I've marked this task as not done yet:\n  %s\n",
                    tasks.getTask(taskIndex).markAsUndone());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeTaskNotFoundException();
        }
    }

    /**
     * Returns false.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
