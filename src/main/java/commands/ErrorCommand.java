package commands;

import functions.*;

/**
 * Represents a command to display an error message.
 */
public class ErrorCommand extends Command {

    protected String errorMsg;

    /**
     * Constructs an ErrorCommand with an error message.
     *
     * @param errorMsg The error message to be displayed.
     */
    public ErrorCommand(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * Executes the ErrorCommand to display an error message.
     *
     * @param tasks   The TaskList (not used in this command).
     * @param ui      The user interface for displaying messages.
     * @param storage The storage interface (not used in this command).
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showErrorMsg(this.errorMsg);
    }
}
