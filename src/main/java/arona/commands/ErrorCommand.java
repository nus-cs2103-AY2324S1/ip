package arona.commands;

import arona.task.TaskList;
import arona.ui.Ui;

/**
 * Represents an Error Command that handles exceptions and displays error messages to the user.
 */
public class ErrorCommand extends Command {
    private Exception exception;

    /**
     * Initializes a new instance of the ErrorCommand class with the specified task list, user interface,
     * and an exception that represents the error.
     *
     * @param taskList   The task list associated with the error command.
     * @param ui         The user interface for displaying error messages.
     * @param exception  The exception that represents the error.
     */
    public ErrorCommand(TaskList taskList, Ui ui, Exception exception) {
        super(taskList, ui);
        this.exception = exception;
    }

    /**
     * Executes the ErrorCommand by displaying the error message contained in the exception to the user interface.
     *
     * @return A string message indicating the error message to be displayed in the GUI.
     */
    public String execute() {
        return ui.showErrorMessage(exception);
    }
}
