package corgi.commands;

import corgi.storage.Storage;
import corgi.tasks.Task;
import corgi.tasks.TaskList;
import corgi.ui.TextRenderer;

/**
 * An abstract class to represent command.
 */
public abstract class Command {
    /**
     * A flag indicating whether this command should exit the application.
     */
    private boolean isExit;

    /**
     * Initializes a new Command instance with the specified exit flag and command type.
     *
     * @param isExit The flag indicating whether this command should exit the application.
     * @param type The type of command.
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Executes the command, performing its intended action on the provided task list,
     * text renderer, and storage.
     *
     * @param list The task list to perform the command action on.
     * @param renderer The text renderer to return formatted message.
     * @param storage The storage for saving and loading tasks (if applicable).
     * @throws CommandExecutionException If an error occurs during command execution.
     */
    public abstract String execute(TaskList list, TextRenderer renderer, Storage<Task> storage)
            throws CommandExecutionException;

    /**
     * Checks whether this command should exit the application.
     *
     * @return True if this command should exit, else false.
     */
    public boolean isExit() {
        return this.isExit;
    }
}
