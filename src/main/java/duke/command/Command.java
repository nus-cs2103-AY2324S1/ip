package duke.command;

import duke.task.TaskStorage;

/**
 * Abstract implementation of a user input {@code Command}.
 */
public abstract class Command {
    /**
     * Executes {@code Command}.
     *
     * @param tasks the {@code TaskStorage} that this {@code Command} will operate upon.
     * @return the immediate feedback for user.
     */
    public abstract String execute(TaskStorage tasks);

    /**
     * Checks if this {@code Command} is an exit command.
     *
     * @return {@code true} if this {@code Command} is an exit command.
     */
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return getClass().getName();
    }
}
