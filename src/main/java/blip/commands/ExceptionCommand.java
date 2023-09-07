package blip.commands;

import blip.exceptions.EmptyDescriptionException;
import blip.exceptions.EmptyTaskNumberException;
import blip.exceptions.DateTimeFormatException;
import blip.storage.BlipStorage;
import blip.tasks.TaskList;
import blip.ui.BlipUI;

public class ExceptionCommand extends Command {
    Exception exception;

    String exceptionMessage;

    public ExceptionCommand(Exception exception, String exceptionMessage) {
        this.exception = exception;
        this.exceptionMessage = exceptionMessage;
    }



    /**
     *
     * @param taskList The Array List of tasks to do commands on
     * @param ui The user interface of Blip
     * @param storage The storage for Blip
     * @return
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
