package avalon.command;

import avalon.utility.Storage;
import avalon.task.TaskList;
import avalon.utility.Ui;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        ui.showTasksList(taskList);
        return ui.getOutput();
    }
}
