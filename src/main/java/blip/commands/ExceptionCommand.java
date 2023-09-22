package blip.commands;

import blip.exceptions.EmptyDescriptionException;
import blip.exceptions.EmptyTaskNumberException;
import blip.exceptions.DateTimeFormatException;
import blip.storage.BlipStorage;
import blip.tasks.TaskList;
import blip.ui.BlipUI;

public class ExceptionCommand extends Command {
    /**
     * The exception type.
     */
    Exception exception;

    /**
     * The exception message.
     */
    String exceptionMessage;

    /**
     * Creates an instance of ExceptionCommand.
     *
     * @param exception The exception type to execute exception handling for
     * @param exceptionMessage The execution message displayed for this exception thrown
     */
    public ExceptionCommand(Exception exception, String exceptionMessage) {
        this.exception = exception;
        this.exceptionMessage = exceptionMessage;
    }



    /**
     * Executes the exception commands where Blip will inform user of errors encountered.
     *
     * @param taskList The Array List of tasks to do commands on
     * @param ui The user interface of Blip
     * @param storage The storage for Blip
     * @return String representation of error messages
     */
    public String execute(TaskList taskList, BlipUI ui, BlipStorage storage) {
        if (exception instanceof EmptyTaskNumberException) {
            return exceptionMessage + ui.showEmptyTaskNumErr();
        }
        if (exception instanceof EmptyDescriptionException) {
            return exceptionMessage + ui.showEmptyDescErr();
        }
        if (exception instanceof DateTimeFormatException) {
            return exceptionMessage + ui.showDateTimeFormatErr();
        }
        return "";
    }
}
