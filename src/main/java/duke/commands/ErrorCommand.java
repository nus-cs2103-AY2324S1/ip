package duke.commands;

import duke.ui.Ui;

/**
 * A command creates an error.
 */
public class ErrorCommand extends Command {
    protected Ui ui;
    private String error;

    /**
     * Error command constructor.
     *
     * @param error The error to be displayed.
     * @param ui The Ui to display the error.
     */
    public ErrorCommand(String error, Ui ui) {
        this.error = error;
        this.ui = ui;
    }

    /**
     * A function to execute the error.
     */
    @Override
    public String execute() {
        return ui.printError(error);
    }
}
