package command;

import duke.Ui;
import storage.FileHandler;
import storage.TaskList;

/**
 * An error command.
 */
public class ErrorCommand extends Command {

    private String errorMessage;
    /**
     * Constructs an `ErrorCommand` object.
     * @param errorMessage The error message to be displayed.
     * @throws IllegalArgumentException If the provided error message is null.
     */
    public ErrorCommand(String errorMessage) {
        assert errorMessage != null : "errorMessage must not be null";
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
}
