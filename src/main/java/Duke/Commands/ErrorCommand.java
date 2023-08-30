package Duke.Commands;

import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

public class ErrorCommand extends Command{
    protected Ui ui;
    public String error;
    public ErrorCommand(String error, Ui ui) {
        this.error = error;
        this.ui = ui;
    }
    @Override
    public void execute() throws DukeException {
        ui.printError(error);
    }
}
