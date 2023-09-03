package commands;
import exceptions.JamesBondException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;
/**
 * The `IncorrectCommand` class represents a command used to handle incorrect or invalid user inputs.
 * When executed, it displays an error message to the user.
 */
public class IncorrectCommand extends Command {
    /**
     * The error message to be displayed to the user.
     */
    public final String messageToUser;

    /**
     * Constructs an `IncorrectCommand` object with the specified error message.
     *
     * @param messageToUser The error message to be displayed to the user.
     */
    public IncorrectCommand(String messageToUser) {
        this.messageToUser = messageToUser;
    }

    /**
     * Executes the `IncorrectCommand` by displaying the specified error message to the user.
     *
     * @param taskList The task list (not used in this command).
     * @param ui       The user interface responsible for displaying the error message.
     * @param storage  The storage component (not used in this command).
     */
    @Override
    public void runCommand(TaskList taskList, Ui ui, Storage storage) {
        ui.showErrorMessage(this.messageToUser);
    }
}

