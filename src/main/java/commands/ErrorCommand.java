package commands;

import functions.*;

public class ErrorCommand extends Command {
    protected String errorMsg;
    public ErrorCommand(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showErrorMsg(this.errorMsg);
    }
}
