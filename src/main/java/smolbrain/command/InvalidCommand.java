package smolbrain.command;

import smolbrain.Storage;
import smolbrain.Ui;
import smolbrain.exception.InvalidCommandException;
import smolbrain.task.TaskList;

public class InvalidCommand implements Command {

    private boolean loading;

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError(new InvalidCommandException());
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void setLoading() {
        this.loading = true;
    }

}
