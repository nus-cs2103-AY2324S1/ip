package avalon.command;

import avalon.utility.Storage;
import avalon.task.TaskList;
import avalon.utility.Ui;

public class ExitCommand extends Command{
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        storage.saveTasks(taskList);
        ui.byeMessage();
        return ui.getOutput();
    }
}
