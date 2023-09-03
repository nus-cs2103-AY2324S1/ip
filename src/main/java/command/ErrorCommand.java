package command;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class ErrorCommand extends Command {
    String errorMessage;
    public ErrorCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError(this.errorMessage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
