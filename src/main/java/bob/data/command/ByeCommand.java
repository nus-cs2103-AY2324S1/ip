package bob.data.command;

import bob.data.task.TaskList;
import bob.storage.Storage;
import bob.ui.Ui;

import java.util.ArrayList;

public class ByeCommand extends Command {
    public ByeCommand(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public void execute(Storage storage, TaskList list, Ui ui) {
        list.close();
        ui.end();
    }
}
