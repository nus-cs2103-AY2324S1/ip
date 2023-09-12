package chatty.command;

import chatty.task.TaskList;
import chatty.utils.Storage;
import chatty.utils.Ui;

public class CommandNotFound extends Command {

    public CommandNotFound() {
        super(false);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showInvalid();
    }
}
