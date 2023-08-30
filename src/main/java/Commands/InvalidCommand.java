package Commands;

import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

public class InvalidCommand implements Command {
    private String errorMessage;

    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

    }
}
