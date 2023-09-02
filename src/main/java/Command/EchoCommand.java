package command;
import duke.DukeException;
import storage.Storage;
import taskList.TaskList;
import ui.Ui;

/**
 * The `EchoCommand` class represents a command to display an echo message to the user in the Duke application.
 * It extends the `Command` class and is responsible for displaying a user-specified message as an echo response.
 * This class encapsulates the behavior of echoing a message to the user.
 *
 * @author raydenlim
 * @version 0.0.0
 */
public class EchoCommand extends Command {
    private String message;

    /**
     * Constructs a new `EchoCommand` instance with the specified message to be echoed.
     *
     * @param message The message to be echoed to the user.
     */
    public EchoCommand(String message) {
        this.message = message;
    }

    /**
     * Executes the "Echo" command by displaying the specified message as an echo response to
     * the user through the user interface.
     *
     * @param taskList The task list (not used in this command).
     * @param ui       The user interface for displaying the echo message.
     * @param storage  The storage component (not used in this command).
     * @throws DukeException An exception may be thrown if there is an error executing the command (e.g., UI error).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showEcho(message);
    }

    /**
     * Indicates whether this command should exit the application.
     *
     * @return `false` because the "Echo" command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
