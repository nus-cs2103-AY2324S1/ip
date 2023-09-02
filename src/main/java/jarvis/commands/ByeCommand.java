package jarvis.commands;

import jarvis.Storage;
import jarvis.TaskList;
import jarvis.Ui;

public class ByeCommand implements Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printBye();
        storage.saveTasks(taskList.getTaskList());
        System.exit(0);
    }
}
