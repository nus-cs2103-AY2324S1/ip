package bob.data.command;

import bob.data.task.TaskList;
import bob.storage.Storage;
import bob.ui.Ui;

public class ListCommand extends Command {
    public ListCommand() {
        super();
    }
    @Override
    public String execute(Storage storage, TaskList list, Ui ui) {
        return list.toString();
    }
}
