package kevin.commands;

import kevin.exceptions.CommandDetailException;
import kevin.exceptions.StorageException;
import kevin.storage.Storage;
import kevin.task.TaskList;
import kevin.ui.Ui;

/**
 * Represents a command in the Duke application.
 * This is an abstract class and serves as a blueprint for various commands in the application.
 */
public abstract class Command {
    /**
     * Constructor for the Command class.
     */
    public Command() {
    }

    /**
     * Executes the command with the specified task list, UI, and storage.
     * Implementation will vary based on the specific command type.
     *
     * @param tasks   The list of tasks that the command will operate on or use.
     * @param ui      The UI component that the command will interact with.
     * @param storage The storage component that the command will use for loading or saving data.
     * @throws StorageException       If there is an issue with storing or retrieving tasks.
     * @throws CommandDetailException If there is an issue with the details or parameters of the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage)
            throws StorageException, CommandDetailException;

    public abstract String executeGui(TaskList tasks, Ui ui, Storage storage)
            throws StorageException, CommandDetailException;

    /**
     * Checks if the command instructs the application to exit.
     *
     * @return true if the command instructs the application to exit, false otherwise.
     */
    public abstract boolean isExit();
}
