package command;

import storage.FileHandler;
import storage.TaskList;

import duke.Ui;

/**
 * An error command.
 */
public class ErrorCommand extends Command {

    private String errorMessage;
    /**
     * Constructs an `ErrorCommand` object.
     */
    public ErrorCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Executes the error command
     *
     * @param t  The task list (not used in this command).
     * @param ui The user interface. (not used in this command).
     * @param f  The file handler (not used in this command).
     *
     * @return   The string representation of the error.
     */
    @Override
    public String execute(TaskList t, Ui ui, FileHandler f) {
        return errorMessage;
    }

    /**
     * Checks whether the command is an exit command.
     *
     * @return `false` because this command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
