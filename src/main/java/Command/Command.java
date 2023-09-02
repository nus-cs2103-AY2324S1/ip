package command;
import duke.DukeException;
import storage.Storage;
import taskList.TaskList;
import ui.Ui;

/**
 * The `Command` abstract class represents a base class for various commands in the Duke application.
 * Subclasses of this class implement specific commands, such as adding tasks or listing tasks.
 * Each concrete command class must provide implementations for the `execute` method and the `isExit` method.
 * The `execute` method defines the behavior of the command, and the `isExit` method indicates whether
 * the command should exit the application.
 * This abstract class ensures that all command classes share a common interface for execution and exit status.
 *
 * @author raydenlim
 * @version 0.0.0
 */
public abstract class Command {
    /**
     * Executes the specific command by performing the associated action on the task list, user interface, and storage.
     * Subclasses override this method to define the behavior of their respective commands.
     *
     * @param taskList The task list on which the command should operate.
     * @param ui       The user interface for displaying feedback to the user.
     * @param storage  The storage component for saving or retrieving data (not used in all commands).
     * @throws DukeException An exception may be thrown if there is an error executing the command.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Indicates whether this command should exit the application.
     * Subclasses override this method to specify whether the command should trigger an application exit.
     *
     * @return `true` if the command should exit the application, `false` otherwise.
     */
    public abstract boolean isExit();
}
