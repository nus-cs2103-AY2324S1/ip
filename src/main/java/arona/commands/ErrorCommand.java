package arona.commands;

import arona.task.TaskList;
import arona.ui.Ui;

/**
 * Represents an Error Command.
 */
public class ErrorCommand extends Command {
    private Exception exception;

    /**
     * Initiates a new InvalidCommand due to invalid command keyword.
     *
     * @param taskList
     * @param ui
     * @param exception
     */
    public ErrorCommand(TaskList taskList, Ui ui, Exception exception) {
        super(taskList, ui);
        this.exception = exception;
    }

    /**
     * Executes the ErrorCommand
     *
     * @return The Exception message from the ErrorCommand.
     */
    public String execute() {
        return ui.showErrorMessage(exception);
    }
}
