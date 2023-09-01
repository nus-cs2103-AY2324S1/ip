package command;

import duke.Storage;
import duke.Ui;
import task.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printTaskList(tasks.getList());
    }
}
