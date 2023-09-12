package chatty.command;

import chatty.task.TaskList;
import chatty.utils.Storage;
import chatty.utils.Ui;

public class ListCommand extends Command {

    public ListCommand() {
        super(false);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showList(taskList);
    }
}
