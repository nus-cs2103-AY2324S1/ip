package command;


import exception.DialogixException;
import main.Storage;
import main.TaskList;
import main.Ui;

public class Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DialogixException {
    }

    public boolean isExit() {
        return false;
    }
}

