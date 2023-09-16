package bob.data.command;

import bob.data.task.TaskList;
import bob.storage.Storage;
import bob.ui.Ui;

public class ByeCommand extends Command {
    public ByeCommand() {
        super();
    }

    @Override
    public String execute(Storage storage, TaskList list, Ui ui) {
        list.close();
        ui.end();
        return "Bye!";
    }
}
