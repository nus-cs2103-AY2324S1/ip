package Commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class InvalidCommand implements Command {
    private String errorMessage;

    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

    }
}
