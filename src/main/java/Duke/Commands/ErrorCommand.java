package Duke.Commands;

import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

public class ErrorCommand extends Command{
    protected Ui ui;
    public String error;

    /**
     * Error command constructor
     *
     * @param error The error to be displayed
     * @param ui The Ui to display the error
     */
    public ErrorCommand(String error, Ui ui) {
        this.error = error;
        this.ui = ui;
    }

    /**
     * A function to execute the error
     */
    @Override
    public void execute()  {
        ui.printError(error);
    }
}
