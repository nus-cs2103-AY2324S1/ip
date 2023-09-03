package bareum.commands;

import bareum.Storage;
import bareum.TaskList;
import bareum.Ui;

public class ByeCommand extends Command {
    public ByeCommand() {

    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        storage.saveAllTasks(taskList);
        ui.showGoodbyeMessage();
    }
}
