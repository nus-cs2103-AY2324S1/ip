package commands;

import exceptions.JamesBondException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class IncorrectCommand extends Command {
    public final String messageToUser;

    public IncorrectCommand(String messageToUser) {
        this.messageToUser = messageToUser;
    }

    @Override
    public void runCommand(TaskList taskList, Ui ui, Storage storage) {
        ui.showErrorMessage(this.messageToUser);
    }
}
