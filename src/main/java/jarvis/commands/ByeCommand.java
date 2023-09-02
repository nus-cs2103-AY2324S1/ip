package jarvis.commands;

import jarvis.Storage;
import jarvis.Ui;
import jarvis.tasks.TaskList;

public class ByeCommand implements Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printBye();
        storage.saveTasks(taskList.getTaskList());
        System.exit(0);
    }
}
