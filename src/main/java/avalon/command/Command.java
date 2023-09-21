package avalon.command;

import avalon.AvalonException;
import avalon.utility.Storage;
import avalon.task.TaskList;
import avalon.utility.Ui;

/**
 * Represents an abstract Command in the Avalon application.
 * Concrete command classes should extend this class and provide an implementation for the 'execute' method.
 * Commands are responsible for performing actions on the task list and interacting with the user interface and storage.
 */
public abstract class Command {
    /**
     * Executes the command, performing the associated action on the task list.
     *
     * @param taskList The TaskList to operate on.
     * @param storage  The Storage instance for reading/writing task data.
     * @param ui       The Ui instance for user interaction.
     * @return A string message indicating the result of the command's execution.
     * @throws AvalonException If there is an error while executing the command.
     */
    public abstract String execute(TaskList taskList, Storage storage, Ui ui) throws AvalonException;
}
