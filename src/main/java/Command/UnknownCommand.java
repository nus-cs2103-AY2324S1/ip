package command;
import duke.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * The `UnknownCommand` class represents a command to handle unknown or invalid
 * user commands in the BloopBot application.
 * It extends the `Command` class and is responsible for displaying an error message
 * when an unknown command is encountered.
 * This class is used to provide feedback to the user when an unrecognized command is entered.
 *
 * @author raydenlim
 * @version 0.0.0
 */
public class UnknownCommand extends Command {
    private String unknownCommand;

    /**
     * Constructs a new `UnknownCommand` instance with the specified unknown command string.
     *
     * @param unknownCommand The unknown command entered by the user.
     */
    public UnknownCommand(String unknownCommand) {
        this.unknownCommand = unknownCommand;
    }

    /**
     * Executes the unknown command by displaying an error message indicating that the command is unknown.
     *
     * @param taskList The task list (not used in this command).
     * @param ui       The user interface for displaying error messages.
     * @param storage  The storage component (not used in this command).
     * @return An error message indicating that the command is unknown.
     * @throws DukeException This exception is not thrown in this command.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return ui.showError(new DukeException("Unknown command: " + unknownCommand));
    }
}
