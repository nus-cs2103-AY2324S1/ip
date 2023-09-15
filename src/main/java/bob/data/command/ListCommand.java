package bob.data.command;

import bob.data.task.TaskList;
import bob.storage.Storage;
import bob.ui.Ui;

public class ListCommand extends Command {
    public ListCommand(String taskDescription) {
        super(taskDescription);
    }
    @Override
    public void execute(Storage storage, TaskList list, Ui ui) {
        ui.print(list.toString());
    }
}
